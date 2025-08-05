package info.kaminosoft.ws;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import info.kaminosoft.bean.JIOConsultaTramite;
import info.kaminosoft.bean.JIODespacho;
import info.kaminosoft.bean.JIODocumentoAnexo;
import info.kaminosoft.bean.JIODocumentoExterno;
import info.kaminosoft.bean.JIODocumentoPrincipal;
import info.kaminosoft.bean.JIOEntidadBean;
import info.kaminosoft.bean.JIORecepcion;
import info.kaminosoft.bean.JIORespuestaConsultaTramite;
import info.kaminosoft.bean.JSAutenticacion;
import info.kaminosoft.bean.JSCargo;

import info.kaminosoft.bean.JSDespacho;
import info.kaminosoft.bean.JSDocumentoAnexo;
import info.kaminosoft.bean.JSRespuesta;
import info.kaminosoft.bean.Modo;
import info.kaminosoft.dao.exceptions.ErrorDuplicadoCuoDespacho;
import info.kaminosoft.dao.exceptions.ErrorDuplicadoCuoRefDespacho;
import info.kaminosoft.dao.exceptions.ErrorDuplicadoNumRegStdDespacho;
import info.kaminosoft.service.IDespachoExternaService;
import info.kaminosoft.service.IRecepcionExternaService;
import info.kaminosoft.service.exceptions.ErrorCargoResponse;
import info.kaminosoft.service.exceptions.ErrorChangeStateDespacho;
import info.kaminosoft.service.exceptions.ErrorDespachoResponse;
import info.kaminosoft.service.exceptions.ErrorWSCargoResponse;
import info.kaminosoft.service.exceptions.ErrorWSConsultaTramiteResponse;
import info.kaminosoft.service.exceptions.ErrorWSDespachoResponse;
import info.kaminosoft.util.JwtUtil;
import info.kaminosoft.util.Utilitarios;
import info.kaminosoft.util.WSPide;

import org.jboss.logging.Logger;




@Path("/pide")
public class TramitePide {
	
	private static Logger depurador = Logger.getLogger(TramitePide.class.getName());
	
	private static final ApplicationContext beanFactory;
	
	static {
	    try {
	        beanFactory = new ClassPathXmlApplicationContext("/applicationContext.xml");
	    } catch (Exception e) {
	        depurador.error("Error inicializando contexto", e);
	        throw new RuntimeException(e);
	    }
	}
	
	private IDespachoExternaService getDespachoExternaServiceBean() {
	    return beanFactory.getBean("iDespachoExternaService", IDespachoExternaService.class);
	}
    private IRecepcionExternaService getRecepcionServiceBean(){
		return beanFactory.getBean("iRecepcionExternaService",IRecepcionExternaService.class);
	}
	
	@GET
	@RolesAllowed({"sin_restriccion"})
    @Path("/ping")
    @Produces(MediaType.TEXT_PLAIN)
	public String ping(){
		Modo modo = Modo.fromString(Utilitarios.ObtenerDatosPropertiesUserHome("configuracion", "MODO"));
		return "pong modo: "+modo.toString();
	}

	 @POST
	 @RolesAllowed({"sin_restriccion"})
	 @Produces({ MediaType.APPLICATION_JSON })
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Path("/autenticacion") 
	 public Response autenticacion(JSAutenticacion autenticacion) {
		//una forma muy simple de autenticar el servicio,para generar JWT se puede mejorar
		String userAccessApi = Utilitarios.ObtenerDatosPropertiesUserHome("configuracion", "USER_ACCESS_API");
		
		if(autenticacion.getUserAccessApi() == null || autenticacion.getUserAccessApi().isEmpty()){
			return Response.status(Response.Status.UNAUTHORIZED).entity("No se encontro usuario").build();
		}
		if(!autenticacion.getUserAccessApi().equals(userAccessApi)){
			return Response.status(Response.Status.UNAUTHORIZED).entity(userAccessApi+" no autorizado").build();
		}

		String token=JwtUtil.generateToken("componente-proxy");

		JSRespuesta respuesta = new JSRespuesta();
		respuesta.setData(token);
		respuesta.setEstado("0000");
		respuesta.setError(null);

		return Response.status(Response.Status.OK).entity(respuesta).build();
	}

	private void wsEstadoEntidadRemota(String vcuo,String vrucentrec) throws Exception{
		
		String ruc_entidad = Utilitarios.ObtenerDatosPropertiesUserHome("configuracion", "RUC_ENTIDAD");
			
		//Consultamos el Documento en el remoto
		JIOConsultaTramite jioConsultaTramite = new JIOConsultaTramite();
		jioConsultaTramite.setVrucentrec(vrucentrec);
		jioConsultaTramite.setVcuo(vcuo);
		jioConsultaTramite.setVrucentrem(ruc_entidad);

		try{
			
			WSPide.wsConsultarTramiteResponse(jioConsultaTramite);
		
		}catch(ErrorWSConsultaTramiteResponse e){
			//Esta respuesta no esta documentada pero de acuerdo a los logs
			//vcodres:-1 vdesres:Error en el servicio de la entidad receptora
			//vcodres:0003 vdesres:Error en el servicio de la entidad receptora
			//En algunos otros casos se lanza una XMLStreamException cuando el servicio no esta habilitado
			//vcodres:0001 vdesres:DATOS NO ENCONTRADOS
			

			// MENSAJES DE RESPUESTA DE LA PIDE (COMPONENTE DE INTEROPERABILIDAD)
			//
			// CODIGO_RESPUESTA_CONSULTA = 0000
			// MENSAJE_CONSULTA_TRAMITE = DATOS ENCONTRADOS

			// CODIGO_RESPUESTA_CONSULTA2 = 0001
			// MENSAJE_CONSULTA_TRAMITE2 = DATOS NO ENCONTRADOS
			if(e.getMessage().contains("Error en el servicio de la entidad receptora") ){
				throw e;
			}
			else{
				//La unica posibilidad es 0001 DATOS NO ENCONTRADOS
				//XMLStreamException los controla wsDocumentoDespachadoEnRemoto()
			}
			
		}
		
	}
	
	private boolean wsDocumentoDespachadoEnRemoto(String vcuo,String vrucentrec) throws Exception{
			
			String ruc_entidad = Utilitarios.ObtenerDatosPropertiesUserHome("configuracion", "RUC_ENTIDAD");
			
			//Consultamos el Documento en el remoto
			JIOConsultaTramite jioConsultaTramite = new JIOConsultaTramite();
			jioConsultaTramite.setVrucentrec(vrucentrec);
			jioConsultaTramite.setVcuo(vcuo);
			jioConsultaTramite.setVrucentrem(ruc_entidad);
			
			try{

				JIORespuestaConsultaTramite jioRespuestaConsultaTramite = WSPide.wsConsultarTramiteResponse(jioConsultaTramite);
				String cflgestRemoto=jioRespuestaConsultaTramite.getCflgest();
				
				//redundate segun documentacion de la PIDE
				//cflgestRemoto puede ser P, R, O
				if(cflgestRemoto.equals("P") || cflgestRemoto.equals("R") || cflgestRemoto.equals("O")){
					return true;
				}

			}catch(ErrorWSConsultaTramiteResponse e){
				if(e.getMessage().contains("Error en el servicio de la entidad receptora") ){
					throw e;
				}
				else{
					return false;
					//La unica posibilidad es 0001 DATOS NO ENCONTRADOS
					//XMLStreamException los controla wsDocumentoDespachadoEnRemoto()
				}
			}

			return false;
	}

	private Response insDespachoEstado(JSDespacho despacho,String vnumregstdref){ 
		
		JSRespuesta respuesta = new JSRespuesta();
		try{

			String nombre_entidad = Utilitarios.ObtenerDatosPropertiesUserHome("configuracion", "NOMBRE_ENTIDAD");
		
			JIODespacho jioDespacho = new JIODespacho();
			
			jioDespacho.setVnumregstd(despacho.getVnumregstd());
			jioDespacho.setVrucentrec(despacho.getVrucentrec());
			jioDespacho.setVnomentrec(despacho.getVnomentrec());
			jioDespacho.setCtipdociderem(despacho.getCtipdociderem());
			jioDespacho.setVnumdociderem(despacho.getVnumdociderem());
			jioDespacho.setVcoduniorgrem(despacho.getVcoduniorgrem());
			jioDespacho.setVuniorgrem(despacho.getVuniorgrem());
			jioDespacho.setVusureg(despacho.getVusureg());
			jioDespacho.setVcuo(WSPide.wsGetCuo());
			//jioDespacho.setVcuoref(vcuoref);
			jioDespacho.setCflgest("P");//siempre P: Pendiente			
			

			JIODocumentoExterno documentoExterno = new JIODocumentoExterno();
			documentoExterno.setVnomentemi(nombre_entidad);
			documentoExterno.setCcodtipdoc(despacho.getCcodtipdoc());
			documentoExterno.setVnumdoc(despacho.getVnumdoc());
			
			documentoExterno.setVuniorgdst(despacho.getVuniorgdst());
			documentoExterno.setVnomdst(despacho.getVnomdst());
			documentoExterno.setVnomcardst(despacho.getVnomcardst());
			documentoExterno.setVasu(despacho.getVasu());
			
			documentoExterno.setSnumfol(despacho.getSnumfol());
			documentoExterno.setVurldocanx(despacho.getVurldocanx());

			ZonedDateTime fechaActual = ZonedDateTime.now();
			documentoExterno.setDfecdoc(fechaActual);

			String anioActual = String.valueOf(LocalDate.now().getYear());
			jioDespacho.setVanioregstd(anioActual);

			JIODocumentoPrincipal documentoPrincipal = new JIODocumentoPrincipal();
			
			//Jackson convierte el String a byte[] decodificado
			documentoPrincipal.setBpdfdoc(despacho.getBpdfdoc());
			documentoPrincipal.setVnomdoc(despacho.getVnomdoc());

			documentoExterno.setDocumentoPrincipal(documentoPrincipal);
			jioDespacho.setDocumentoExterno(documentoExterno);

			List<JIODocumentoAnexo> lsDocumentoAnexo = new ArrayList<>();
			for(JSDocumentoAnexo anexo: despacho.getLstanexos()){
				JIODocumentoAnexo documentoAnexo = new JIODocumentoAnexo();
				documentoAnexo.setVnomdoc(anexo.getVnomdoc());
				lsDocumentoAnexo.add(documentoAnexo);
			}
			documentoExterno.setSnumanx(lsDocumentoAnexo.size());
			documentoExterno.setLstDocAnexo(lsDocumentoAnexo);

			if(lsDocumentoAnexo.size()>0){
				documentoExterno.setVurldocanx(despacho.getVurldocanx());
			}
			
			String codigoSuccess="0000";
			String respuestaSuccessPide="";
			JIODespacho resDespacho=getDespachoExternaServiceBean().getDespachoByNumRegStd(jioDespacho.getVnumregstd());
			if(resDespacho==null) {

				//hack para verificar que el remoto esta habilitado
				//enviamos un cuo que no existe
				//si no esta habilitado el remoto lanzara una Exception
				//se han encotrado muchas estidades que estan que estan deshbilitadas
				wsEstadoEntidadRemota("999999999A",jioDespacho.getVrucentrec());
				
				depurador.info("documento vnumregstd: "+jioDespacho.getVnumregstd()+ " ==> creamos documento(pdf) y enviamos a remoto el documento(pdf)");
				//transaccion
				JIODespacho jioDespachoRes=getDespachoExternaServiceBean().insDespacho(jioDespacho,vnumregstdref);
				//remoto
				respuestaSuccessPide=WSPide.wsRecepcionarTramiteResponse(jioDespachoRes);
				//transaccion
				getDespachoExternaServiceBean().updEstadoDespacho(jioDespachoRes.getVnumregstd(), "E",vnumregstdref);
			}
			else { 

				//El documento se encuentra el local con estado pendiente
				if(resDespacho.getCflgest().equals("P")){

					String vcuo=resDespacho.getVcuo();
					boolean estado=wsDocumentoDespachadoEnRemoto(vcuo, jioDespacho.getVrucentrec());
					if(estado==true) {
						depurador.info("documento vnumregstd: "+jioDespacho.getVnumregstd()+ " ==> existe documento(pdf) y el remoto tiene el documento(pdf) con estado P entonces sincronizamos estado");
						respuestaSuccessPide="El Documento (sincronizado) Nº CUO "+vcuo+
						" se encuentra a disposición para la recepción formal de la entidad destinataria "+
						jioDespacho.getVnomentrec()+
						" en los horarios de atención de su Mesa de Partes.";
						
						codigoSuccess="0001";//Codigo 0001: sincronizamos el documento con estado Pendiente
											//Importante: El documental recibido se descarta !!!
						
						
						//transaccion
						getDespachoExternaServiceBean().updEstadoDespacho(jioDespacho.getVnumregstd(), "E",vnumregstdref);
					}
					else {
						depurador.info("documento vnumregstd: "+jioDespacho.getVnumregstd()+ " ==> eliminamos documento(pdf) y creamos documento(pdf) entonce enviamos documento(pdf) a remoto");
						//removemos el documento con estado pendiente
						getDespachoExternaServiceBean().removeDespacho(jioDespacho.getVnumregstd());
						//transaccion
						JIODespacho jioDespachoRes=getDespachoExternaServiceBean().insDespacho(jioDespacho,vnumregstdref);
						//remoto
						respuestaSuccessPide=WSPide.wsRecepcionarTramiteResponse(jioDespachoRes);
						//transaccion
						getDespachoExternaServiceBean().updEstadoDespacho(jioDespachoRes.getVnumregstd(), "E",vnumregstdref);
					}

				}
				else{
					String vnomentrec=resDespacho.getVnomentrec();
					String vnumregstd=resDespacho.getVnumregstd();
					throw new ErrorDespachoResponse("El documento "+vnumregstd+" ya es envio a la entidad destinataria "+vnomentrec);
				}
				
			}
			
			
			respuesta.setEstado(codigoSuccess);
			respuesta.setData(respuestaSuccessPide);
			respuesta.setError(null);

		}catch(ErrorChangeStateDespacho e){

			String codigoError="E003";
			depurador.error("Error "+codigoError,e);

			respuesta.setData(null);
			respuesta.setEstado(codigoError);
			respuesta.setError(e.getMessage()==null?"Ocurrio un error inesperado":e.getMessage());

		}catch (ErrorWSDespachoResponse e) {
			String codigoError="E004";
			depurador.error("Error "+codigoError,e);

			respuesta.setData(null);
			respuesta.setEstado(codigoError);
			respuesta.setError(e.getMessage()==null || e.getMessage().trim().equals("") ? "Error en el servicio de la entidad receptora ":e.getMessage());

		}
		catch (ErrorWSConsultaTramiteResponse e) {
			String codigoError="E005";
			depurador.error("Error "+codigoError,e);

			respuesta.setData(null);
			respuesta.setEstado(codigoError);
			respuesta.setError(e.getMessage()==null || e.getMessage().trim().equals("") ? "Error en el servicio de la entidad receptora ":e.getMessage());

		}
		catch(ErrorDespachoResponse e){

			String codigoError="E002";
			depurador.error("Error "+codigoError,e);

			respuesta.setData(null);
			respuesta.setEstado(codigoError);
			respuesta.setError(e.getMessage()==null?"Ocurrio un error inesperado":e.getMessage());
		}
		catch(ErrorDuplicadoCuoDespacho e){

			String codigoError="E001";
			depurador.error("Error "+codigoError,e);

			respuesta.setData(null);
			respuesta.setEstado(codigoError);
			respuesta.setError(e.getMessage()==null?"Ocurrio un error inesperado":e.getMessage());

		}
		catch(ErrorDuplicadoCuoRefDespacho e){

			String codigoError="E001";
			depurador.error("Error "+codigoError,e);

			respuesta.setData(null);
			respuesta.setEstado(codigoError);
			respuesta.setError(e.getMessage()==null?"Ocurrio un error inesperado":e.getMessage());

		}
		catch(ErrorDuplicadoNumRegStdDespacho e){

			String codigoError="E001";
			depurador.error("Error "+codigoError,e);

			respuesta.setData(null);
			respuesta.setEstado(codigoError);
			respuesta.setError(e.getMessage()==null?"Ocurrio un error inesperado":e.getMessage());

		}
		catch(Exception e){
			String codigoError="-1";
			depurador.error("Error "+codigoError,e);

			respuesta.setData(null);
			respuesta.setEstado(codigoError);
			respuesta.setError("Error inesperado en el servicio de insercion de documento de despacho ");
		}

		return Response.status(Response.Status.OK).entity(respuesta).build();
	}
    
    @POST
    @RolesAllowed({"restringido"})
    @Path("/despacho/enviado")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public Response insDespachoEnviado(JSDespacho despacho) {
		return insDespachoEstado(despacho,null);
    }
	@POST
	@RolesAllowed({"restringido"})
    @Path("/despacho/subsanado")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public Response insDespachoSubsando(JSDespacho despacho) {
		// con vcuoref actualimos el estado del despacho anterior
		return insDespachoEstado(despacho,despacho.getVnumregstdref());
    }
	
	private String wsCargoEnRemoto(JIORecepcion recepcion,String vcuo,String vrucentrem){
		
		try {
			
			String respuestaSuccessPide=WSPide.wsCargoResponse(recepcion,vcuo,vrucentrem);
			
			return respuestaSuccessPide;
		}
		catch(ErrorWSCargoResponse e){
			return "RECEPCION DE CARGO EXITOSO (*)";
		}
		catch(ErrorCargoResponse e){
			return "RECEPCION DE CARGO EXITOSO (**)";
		}
		catch(Exception e){
			return "RECEPCION DE CARGO EXITOSO (***)";
		}
	}
	
	private Response insCargoEstado(JSCargo cargo,String cflgest,String vobs){

		depurador.info("insertar cargo de recepcion: "+cargo.getVnumregstd());	
		
		JSRespuesta respuesta = new JSRespuesta();
		try {

			String anioActual = String.valueOf(LocalDate.now().getYear());

			JIORecepcion newRecepcion = new JIORecepcion();
			newRecepcion.setVnumregstd(cargo.getVnumregstd());//unico en la tabla
			newRecepcion.setVanioregstd(anioActual);
			newRecepcion.setVuniorgstd(cargo.getVuniorgstd());
			newRecepcion.setCcoduniorgstd(cargo.getCcoduniorgstd());
			newRecepcion.setVusuregstd(cargo.getVusuregstd());

			//Jackson convierte el String a byte[] decodificado
			newRecepcion.setBcarstd(cargo.getBcarstd());
			newRecepcion.setVobs(vobs);
			newRecepcion.setCflgest(cflgest);//R/O
			
			ZonedDateTime fechaActual = ZonedDateTime.now();
			newRecepcion.setDfecregstd(fechaActual);
			
			
			String codigoSuccess="0000";
			String respuestaSuccessPide="";
			JIORecepcion oldRecepcion=getRecepcionServiceBean().getDespachoByNumRegStd(newRecepcion.getVnumregstd());
			if(oldRecepcion==null) {
				
				throw new ErrorCargoResponse("El documento "+newRecepcion.getVnumregstd()+" no se encuentra registrado ");
			}
			else {
				
				if(oldRecepcion.getCflgest().equals("P")) {
					
					getRecepcionServiceBean().insCargo(newRecepcion);//RECEPCIONADO / OBSERVADO
					String vcuo = oldRecepcion.getVcuo();
					String vrucentrem = oldRecepcion.getVrucentrem();
					
					/*
						Debido a que algunas Entidades no estan enviando los mensajes estandar de la PIDE 
						Ahora las entidades receptoras se encargaran de la sincronizacion
						Ver video https://youtu.be/JVE9fJWGQR8 para mayores detalles de la implementacion
					
						Ahora enviamos el cargo al remoto, no verificamos si el cargo llega al remoto
					*/
					respuestaSuccessPide=wsCargoEnRemoto(newRecepcion,vcuo,vrucentrem);
					depurador.info("cargo de vcuo:"+vcuo+" vnumregstd: "+oldRecepcion.getVnumregstd()+ " respuestaEnviada:"+respuestaSuccessPide+" ==> creamos cargo(pdf) y enviamos a remoto el cargo(pdf)");
					
				}
				else { //R - O
					
					String vnumregstd=oldRecepcion.getVnumregstd();
					throw new ErrorCargoResponse("El cargo de recepción del documento "+vnumregstd+" ya se envio a la entidad receptora");
				}
			}
		

			respuesta.setData(respuestaSuccessPide);
			respuesta.setEstado(codigoSuccess);
			respuesta.setError(null);

		
		} 
		catch (ErrorCargoResponse e) {
			String codigoError="E001";
			depurador.error("Error "+codigoError,e);

			respuesta.setData(null);
			respuesta.setEstado(codigoError);
			respuesta.setError(e.getMessage()==null?"Ocurrio un error inesperado":e.getMessage());

		} 
		catch (Exception e) {
			String codigoError="-1";
			depurador.error("Error "+codigoError,e);

			respuesta.setData(null);
			respuesta.setEstado(codigoError);
			respuesta.setError("Error inesperado en el servicio de insercion de cargo de recepcion ");

		}

        return Response.status(Response.Status.OK).entity(respuesta).build();
	}

	@POST
	@RolesAllowed({"restringido"})
    @Path("/cargo/recepcionado")
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public Response insCargoRecepcionado(JSCargo cargo) {

		JSRespuesta respuesta = new JSRespuesta();
		if(cargo.getVobs()!=null){
			respuesta.setData(null);
			respuesta.setEstado("EV01");
			respuesta.setError("El campo vobs no debe ser enviado");
			return Response.status(Response.Status.OK).entity(respuesta).build();
		}

		return insCargoEstado(cargo,"R",null);
	}

	@POST
	@RolesAllowed({"restringido"})
    @Path("/cargo/observado")
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public Response insCargoObservado(JSCargo cargo) {

		JSRespuesta respuesta = new JSRespuesta();
		if(cargo.getVobs()==null || cargo.getVobs().trim().isEmpty()){
			respuesta.setData(null);
			respuesta.setEstado("EV01");
			respuesta.setError("El campo vobs no debe ser vacio");
			return Response.status(Response.Status.OK).entity(respuesta).build();
		}

		return insCargoEstado(cargo,"O",cargo.getVobs());
	}
	
	private int insCargoRecepcionSincronizado(JIORespuestaConsultaTramite cargo,String vcuo,String vnumregstd) {
		
		int row=0;
		try {
			
			row=getDespachoExternaServiceBean().insCargoRecepcion(cargo,vcuo,vnumregstd);
			
		} catch (Exception e) {
			
			depurador.error("cargo no sincronizado ==> message="+e.getMessage(),e);
		}
		return row;
	}
	
	@GET
    @RolesAllowed({"restringido"})
    @Path("/consultar/recepcion/{vrucentrec}/{vnumregstd}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getConsultarDocumentoEnviado(@PathParam("vrucentrec") String vrucentrec, @PathParam("vnumregstd") String vnumregstd) {
		
		JSRespuesta respuesta = new JSRespuesta();

		try{

			String ruc_entidad = Utilitarios.ObtenerDatosPropertiesUserHome("configuracion", "RUC_ENTIDAD");
			
			JIODespacho despacho=getDespachoExternaServiceBean().getDespachoByNumRegStd(vnumregstd);
			if(despacho==null) {
				
				respuesta.setData(null);
				respuesta.setEstado("E001");
				respuesta.setError("Documento no encontrado");
				
			}
			else {
				
				String vcuo=despacho.getVcuo();
				JIOConsultaTramite jioConsultaTramite = new JIOConsultaTramite();
				jioConsultaTramite.setVrucentrec(vrucentrec);
				jioConsultaTramite.setVcuo(vcuo);
				jioConsultaTramite.setVrucentrem(ruc_entidad);
				JIORespuestaConsultaTramite jioRespuestaConsultaTramite = WSPide.wsConsultarTramiteResponse(jioConsultaTramite);
				
				depurador.info("consulta remota cargo vrucentrec="+vrucentrec+" vcuo="+vcuo+" ruc_entidad="+ruc_entidad);
				
				String estado="0000";
				/*
					El cargo del remoto, lo persistimos manualmente cuando se realiza la
					consulta del estado del documento enviado
					*/
				if(despacho.getCflgest().equals("E")) {
					if(jioRespuestaConsultaTramite.getCflgest().equals("R") || jioRespuestaConsultaTramite.getCflgest().equals("O")){
						int row=insCargoRecepcionSincronizado(jioRespuestaConsultaTramite,vcuo,vnumregstd);
						if(row==0) {//No se pudo sincronizar
							estado="0001";
						}
					}
				}
				
				respuesta.setData(jioRespuestaConsultaTramite);
				respuesta.setEstado(estado);
				respuesta.setError(null);
				
			}

		}
		catch (ErrorWSConsultaTramiteResponse e) {
			String codigoError="E001";
			depurador.error("Error "+codigoError,e);

			respuesta.setData(null);
			respuesta.setEstado(codigoError);
			respuesta.setError(e.getMessage()==null || e.getMessage().trim().equals("") ? "Error en el servicio de la entidad receptora ":e.getMessage());

		}
		catch(Exception e){

			String codigoError="-1";
			depurador.error("Error "+codigoError,e);

			respuesta.setData(null);
			respuesta.setEstado(codigoError);
			respuesta.setError("Error inesperado en el servicio de consulta de recepcion ");
		}

		return Response.status(Response.Status.OK).entity(respuesta).build();
    }
	@GET
    @RolesAllowed({"restringido"})
    @Path("/consultar/lista/entidades/{sidcatent}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getListaEntidad(@PathParam("sidcatent") int sidcatent) {
			
		JSRespuesta respuesta = new JSRespuesta();
		try{
			List<JIOEntidadBean> listEntidades=WSPide.wsGetListaEntidad(sidcatent);

			respuesta.setData(listEntidades);
			respuesta.setEstado("0000");
			respuesta.setError(null);

		}catch(Exception e){

			String codigoError="-1";
			depurador.error("Error "+codigoError,e);

			respuesta.setData(null);
			respuesta.setEstado(codigoError);
			respuesta.setError("Error inesperado en el servicio de consulta de lista de entidades ");
		}

		return Response.status(Response.Status.OK).entity(respuesta).build();
    }
}
