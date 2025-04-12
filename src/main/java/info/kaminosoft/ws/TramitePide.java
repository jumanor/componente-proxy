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
import info.kaminosoft.dao.exceptions.ErrorDuplicadoNumRegStdDespacho;
import info.kaminosoft.service.IDespachoExternaService;
import info.kaminosoft.service.IRecepcionExternaService;
import info.kaminosoft.service.exceptions.ErrorCargoResponse;
import info.kaminosoft.service.exceptions.ErrorChangeStateDespacho;
import info.kaminosoft.service.exceptions.ErrorDespachoResponse;
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

	private Response insDespachoEstado(JSDespacho despacho,String cflgest,String vnumregstdref){ 
		
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
			jioDespacho.setCflgest("E");//siempre E: Enviado			
			

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

			String respuestaSuccessPide=getDespachoExternaServiceBean().insDespacho(jioDespacho,cflgest,vnumregstdref);

			respuesta.setEstado("0000");
			respuesta.setData(respuestaSuccessPide);
			respuesta.setError(null);

		}catch(ErrorChangeStateDespacho e){

			String codigoError="0001";
			depurador.error("Error "+codigoError,e);

			respuesta.setData(null);
			respuesta.setEstado(codigoError);
			respuesta.setError(e.getMessage());

		}catch(ErrorDespachoResponse e){

			String codigoError="0001";
			depurador.error("Error "+codigoError,e);

			respuesta.setData(null);
			respuesta.setEstado(codigoError);
			respuesta.setError(e.getMessage());
		}
		catch(ErrorDuplicadoCuoDespacho e){

			String codigoError="0001";
			depurador.error("Error "+codigoError,e);

			respuesta.setData(null);
			respuesta.setEstado(codigoError);
			respuesta.setError(e.getMessage());

		}
		catch(ErrorDuplicadoNumRegStdDespacho e){

			String codigoError="0001";
			depurador.error("Error "+codigoError,e);

			respuesta.setData(null);
			respuesta.setEstado(codigoError);
			respuesta.setError(e.getMessage());

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
		return insDespachoEstado(despacho,"E",null);
    }
	@POST
	@RolesAllowed({"restringido"})
    @Path("/despacho/subsanado")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public Response insDespachoSubsando(JSDespacho despacho) {
		// con vcuoref actualimos el estado del despacho anterior
		return insDespachoEstado(despacho,"S",despacho.getVnumregstdref());
    }

	private Response insCargoEstado(JSCargo cargo,String cflgest,String vobs){

		depurador.info("insertar cargo de recepcion: "+cargo.getVnumregstd());	
		
		JSRespuesta respuesta = new JSRespuesta();
		try {

			String anioActual = String.valueOf(LocalDate.now().getYear());

			JIORecepcion rec = new JIORecepcion();
			rec.setVnumregstd(cargo.getVnumregstd());//unico en la tabla
			rec.setVanioregstd(anioActual);
			rec.setVuniorgstd(cargo.getVuniorgstd());
			rec.setCcoduniorgstd(cargo.getCcoduniorgstd());
			rec.setVusuregstd(cargo.getVusuregstd());

			//Jackson convierte el String a byte[] decodificado
			rec.setBcarstd(cargo.getBcarstd());
			rec.setVobs(vobs);
			rec.setCflgest(cflgest);

			ZonedDateTime fechaActual = ZonedDateTime.now();
			rec.setDfecregstd(fechaActual);
		
			String respuestaSuccessPide=getRecepcionServiceBean().insCargo(rec);

			respuesta.setData(respuestaSuccessPide);
			respuesta.setEstado("0000");
			respuesta.setError(null);

		} catch (ErrorCargoResponse e) {
			String codigoError="0001";
			depurador.error("Error "+codigoError,e);

			respuesta.setData(null);
			respuesta.setEstado(codigoError);
			respuesta.setError(e.getMessage());

		} catch (Exception e) {
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
		return insCargoEstado(cargo,"R",null);
	}

	@POST
	@RolesAllowed({"restringido"})
    @Path("/cargo/observado")
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public Response insCargoObservado(JSCargo cargo) {
		return insCargoEstado(cargo,"O",cargo.getVobs());
	}

	@GET
    @RolesAllowed({"restringido"})
    @Path("/consultar/recepcion/{vrucentrec}/{vnumregstd}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getConsultarDocumentoEnviado(@PathParam("vrucentrec") String vrucentrec, @PathParam("vnumregstd") String vnumregstd) {
		
		JSRespuesta respuesta = new JSRespuesta();

		try{

			String ruc_entidad = Utilitarios.ObtenerDatosPropertiesUserHome("configuracion", "RUC_ENTIDAD");
			
			String vcuo=getDespachoExternaServiceBean().getCuoByNumRegStd(vnumregstd);

			JIOConsultaTramite jioConsultaTramite = new JIOConsultaTramite();
			jioConsultaTramite.setVrucentrec(vrucentrec);
			jioConsultaTramite.setVcuo(vcuo);
			jioConsultaTramite.setVrucentrem(ruc_entidad);
			JIORespuestaConsultaTramite jioRespuestaConsultaTramite = WSPide.wsConsultarTramiteResponse(jioConsultaTramite);
			
			
			if(!jioRespuestaConsultaTramite.getVcodres().equals("0000")){

	
				respuesta.setData(null);
				respuesta.setEstado("0001");
				respuesta.setError(jioRespuestaConsultaTramite.getVdesres());
			}
			else{
				
				respuesta.setData(jioRespuestaConsultaTramite);
				respuesta.setEstado("0000");
				respuesta.setError(null);
			}

		}catch(Exception e){

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
