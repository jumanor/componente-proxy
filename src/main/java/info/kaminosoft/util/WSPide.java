package info.kaminosoft.util;

import java.util.ArrayList;
import java.util.Base64;

import java.util.GregorianCalendar;
import java.util.List;


import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;


import org.jboss.logging.Logger;

import info.kaminosoft.bean.JIOConsultaTramite;
import info.kaminosoft.bean.JIODespacho;
import info.kaminosoft.bean.JIOEntidadBean;
import info.kaminosoft.bean.JIORecepcion;
import info.kaminosoft.bean.JIORespuestaConsultaTramite;
import info.kaminosoft.bean.JIOTipoDocumentoTramite;
import info.kaminosoft.bean.Modo;
import info.kaminosoft.service.exceptions.ErrorCargoResponse;
import info.kaminosoft.service.exceptions.ErrorWSDespachoResponse;
import info.kaminosoft.service.exceptions.ErrorWSCargoResponse;
import info.kaminosoft.service.exceptions.ErrorWSConsultaTramiteResponse;


public class WSPide {

    private static Logger depurador = Logger.getLogger(WSPide.class.getName());
    
    private static Modo modo = Modo.fromString(
        Utilitarios.ObtenerDatosPropertiesUserHome("configuracion", "MODO")
    );

	public static JIORespuestaConsultaTramite wsConsultarTramiteResponse(JIOConsultaTramite consultaTramite) throws Exception{
		if(modo==Modo.PROD){
			return wsConsultarTramiteResponseProd(consultaTramite);
		}
		else if (modo==Modo.DEV){
			return wsConsultarTramiteResponseDev(consultaTramite);
		}
		else if (modo==Modo.DEV_LOCALHOST){
			return wsConsultarTramiteRespondeDevLocal(consultaTramite.getVcuo());
		}
		else{
			throw new UnsupportedOperationException("No se ha implementado el servicio");
		}
	}
	private static JIORespuestaConsultaTramite wsConsultarTramiteResponseProd(JIOConsultaTramite consultaTramite) throws Exception{
		prod2.pe.gob.segdi.wsiopidetramite.ws.PcmIMgdTramite srv=new prod2.pe.gob.segdi.wsiopidetramite.ws.PcmIMgdTramite();
		prod2.pe.gob.segdi.wsiopidetramite.ws.ConsultaTramite consulta=new prod2.pe.gob.segdi.wsiopidetramite.ws.ConsultaTramite();
		consulta.setVrucentrem(consultaTramite.getVrucentrem());
		consulta.setVrucentrec(consultaTramite.getVrucentrec());
		consulta.setVcuo(consultaTramite.getVcuo());

		try{
			prod2.pe.gob.segdi.wsiopidetramite.ws.RespuestaConsultaTramite respuesta=srv.getPcmIMgdTramiteHttpsSoap11Endpoint().consultarTramiteResponse(consulta);
			depurador.info("wsConsultarTramiteResponse(vcuo="+consultaTramite.getVcuo()+",vrucentrec="+consultaTramite.getVrucentrec()+") ==> vcodres="+respuesta.getVcodres()+" vdesres="+respuesta.getVdesres());
			
			if(!respuesta.getVcodres().equals("0000")){
				depurador.error("Error en el servicio PIDE de consulta trámite rucentrec="+consultaTramite.getVrucentrec()+" cuo="+consultaTramite.getVcuo()+" codres="+respuesta.getVcodres()+" desres="+respuesta.getVdesres());
                throw new ErrorWSConsultaTramiteResponse(respuesta.getVcodres(),respuesta.getVdesres());
            }
			JIORespuestaConsultaTramite jioRespuestaConsultaTramite=new JIORespuestaConsultaTramite();
			jioRespuestaConsultaTramite.setVcodres(respuesta.getVcodres());
			jioRespuestaConsultaTramite.setVdesres(respuesta.getVdesres());

			//jioRespuestaConsultaTramite.setVcuo(respuesta.getVcuo());
			//jioRespuestaConsultaTramite.setVcuoref(respuesta.getVcuoref());
			jioRespuestaConsultaTramite.setVnumregstd(respuesta.getVnumregstd());
			jioRespuestaConsultaTramite.setVanioregstd(respuesta.getVanioregstd());
			jioRespuestaConsultaTramite.setVuniorgstd(respuesta.getVuniorgstd());
			jioRespuestaConsultaTramite.setDfecregstd(respuesta.getDfecregstd());
			jioRespuestaConsultaTramite.setVusuregstd(respuesta.getVusuregstd());

			//Jackson convertira en forma automatica de byte[] decodificado a String 
			//byte[] debe ser el original
			if(respuesta.getBcarstd()!=null) {
				byte[] bcarstd=Base64.getDecoder().decode(respuesta.getBcarstd());
				jioRespuestaConsultaTramite.setBcarstd(bcarstd);
			}
			jioRespuestaConsultaTramite.setVobs(respuesta.getVobs());
			jioRespuestaConsultaTramite.setCflgest(respuesta.getCflgest());		

			return jioRespuestaConsultaTramite;
			
		}
		catch (ErrorWSConsultaTramiteResponse e) {
            throw e;
        }  
		catch (Exception e) {
            depurador.error(e.getMessage(),e);
            throw new ErrorWSConsultaTramiteResponse("-1", "Error en el servicio de la entidad receptora");
        }
	}

	private static JIORespuestaConsultaTramite wsConsultarTramiteResponseDev(JIOConsultaTramite consultaTramite) throws Exception{
		dev2.pe.gob.segdi.wsiopidetramite.ws.IOTramiteService srv=new dev2.pe.gob.segdi.wsiopidetramite.ws.IOTramiteService();
		dev2.pe.gob.segdi.wsiopidetramite.ws.ConsultaTramite consulta=new dev2.pe.gob.segdi.wsiopidetramite.ws.ConsultaTramite();
		consulta.setVrucentrem(consultaTramite.getVrucentrem());
		consulta.setVrucentrec(consultaTramite.getVrucentrec());
		consulta.setVcuo(consultaTramite.getVcuo());

		try{
			dev2.pe.gob.segdi.wsiopidetramite.ws.RespuestaConsultaTramite respuesta=srv.getIOTramitePort().consultarTramiteResponse(consulta);
			depurador.info("wsConsultarTramiteResponse(vcuo="+consultaTramite.getVcuo()+",vrucentrec="+consultaTramite.getVrucentrec()+") ==> vcodres="+respuesta.getVcodres()+" vdesres="+respuesta.getVdesres());
			
			if(!respuesta.getVcodres().equals("0000")){
				depurador.error("Error en el servicio PIDE de consulta trámite rucentrec="+consultaTramite.getVrucentrec()+" cuo="+consultaTramite.getVcuo()+" codres="+respuesta.getVcodres()+" desres="+respuesta.getVdesres());
                throw new ErrorWSConsultaTramiteResponse(respuesta.getVcodres(),respuesta.getVdesres());
            }

			JIORespuestaConsultaTramite jioRespuestaConsultaTramite=new JIORespuestaConsultaTramite();
			jioRespuestaConsultaTramite.setVcodres(respuesta.getVcodres());
			jioRespuestaConsultaTramite.setVdesres(respuesta.getVdesres());
			
			//jioRespuestaConsultaTramite.setVcuo(respuesta.getVcuo());
			//jioRespuestaConsultaTramite.setVcuoref(respuesta.getVcuoref());
			jioRespuestaConsultaTramite.setVnumregstd(respuesta.getVnumregstd());
			jioRespuestaConsultaTramite.setVanioregstd(respuesta.getVanioregstd());
			jioRespuestaConsultaTramite.setVuniorgstd(respuesta.getVuniorgstd());
			jioRespuestaConsultaTramite.setDfecregstd(respuesta.getDfecregstd());
			jioRespuestaConsultaTramite.setVusuregstd(respuesta.getVusuregstd());
			//Jackson convertira en forma automatica de byte[] decodificado a String 
			//byte[] debe ser el original
			if(respuesta.getBcarstd()!=null) {
				byte[] bcarstd=Base64.getDecoder().decode(respuesta.getBcarstd());
				jioRespuestaConsultaTramite.setBcarstd(bcarstd);
			}
			jioRespuestaConsultaTramite.setVobs(respuesta.getVobs());
			jioRespuestaConsultaTramite.setCflgest(respuesta.getCflgest());		

			return jioRespuestaConsultaTramite;
		
		}
		catch (ErrorWSConsultaTramiteResponse e) {
            throw e;
        } 
		catch (Exception e) {
            depurador.error(e.getMessage(),e);
            throw new ErrorWSConsultaTramiteResponse("-1", "Error en el servicio de la entidad receptora");
        }
	}

	private static JIORespuestaConsultaTramite wsConsultarTramiteRespondeDevLocal(String vcuo) throws Exception{
		local2.pe.gob.segdi.wsiotramite.ws.Tramite_Service srv=new local2.pe.gob.segdi.wsiotramite.ws.Tramite_Service();
		
		try{
			local2.pe.gob.segdi.wsiotramite.ws.RespuestaConsultaTramite respuesta=srv.getTramitePort().consultarTramiteResponse(vcuo);
			depurador.info("wsConsultarTramiteResponse(vcuo="+vcuo+") ==> vcodres="+respuesta.getVcodres()+" vdesres="+respuesta.getVdesres());
			
			if(!respuesta.getVcodres().equals("0000")){
				depurador.error("Error en el servicio PIDE de consulta trámite cuo="+vcuo+" codres="+respuesta.getVcodres()+" desres="+respuesta.getVdesres());
                throw new ErrorWSConsultaTramiteResponse(respuesta.getVcodres(),respuesta.getVdesres());
            }
			JIORespuestaConsultaTramite jioRespuestaConsultaTramite=new JIORespuestaConsultaTramite();
			jioRespuestaConsultaTramite.setVcodres(respuesta.getVcodres());
			jioRespuestaConsultaTramite.setVdesres(respuesta.getVdesres());
			
			//jioRespuestaConsultaTramite.setVcuo(respuesta.getVcuo());
			//jioRespuestaConsultaTramite.setVcuoref(respuesta.getVcuoref());
			jioRespuestaConsultaTramite.setVnumregstd(respuesta.getVnumregstd());
			jioRespuestaConsultaTramite.setVanioregstd(respuesta.getVanioregstd());
			jioRespuestaConsultaTramite.setVuniorgstd(respuesta.getVuniorgstd());
			jioRespuestaConsultaTramite.setDfecregstd(respuesta.getDfecregstd());
			jioRespuestaConsultaTramite.setVusuregstd(respuesta.getVusuregstd());
			//Jackson convertira en forma automatica de byte[] decodificado a String 
			//byte[] debe ser el original
			if(respuesta.getBcarstd()!=null) {
				byte[] bcarstd=Base64.getDecoder().decode(respuesta.getBcarstd());
				jioRespuestaConsultaTramite.setBcarstd(bcarstd);
			}
			jioRespuestaConsultaTramite.setVobs(respuesta.getVobs());
			jioRespuestaConsultaTramite.setCflgest(respuesta.getCflgest());		

			return jioRespuestaConsultaTramite;
		
		} 
		catch (ErrorWSConsultaTramiteResponse e) {
            throw e;
        } 
		catch (Exception e) {
            depurador.error(e.getMessage(),e);
            throw new ErrorWSConsultaTramiteResponse("-1", "Error en el servicio de la entidad receptora");
        }
	}

	public static List<JIOEntidadBean> wsGetListaEntidad(int categoria){
		if(modo==Modo.PROD){
			return wsGetListaEntidadProd(categoria);
		}
		else if (modo==Modo.DEV){
			return wsGetListaEntidadDev(categoria);
		}
		else if (modo==Modo.DEV_LOCALHOST){
			return wsGetListaEntidadDevLocal(categoria);
		}
		else{
			throw new UnsupportedOperationException("No se ha implementado el servicio");
		}
	}
	private static List<JIOEntidadBean> wsGetListaEntidadProd(int categoria){
		prod2.pe.gob.segdi.wsentidad.ws.PcmIMgdEntidad servicio=new prod2.pe.gob.segdi.wsentidad.ws.PcmIMgdEntidad();
		List<prod2.pe.gob.segdi.wsentidad.ws.EntidadBean> lista=servicio.getPcmIMgdEntidadHttpsSoap11Endpoint().getListaEntidad(categoria);
		
		List<JIOEntidadBean> listaRes=new ArrayList<JIOEntidadBean>(); 
		for (prod2.pe.gob.segdi.wsentidad.ws.EntidadBean ioEntidad : lista) {
			JIOEntidadBean entidad=new JIOEntidadBean();
			entidad.setVnoment(ioEntidad.getVnoment());	
			entidad.setVrucent(ioEntidad.getVrucent());
			listaRes.add(entidad);
		}
		return listaRes;
	}
	private static List<JIOEntidadBean> wsGetListaEntidadDev(int categoria){
		dev2.pe.gob.segdi.wsentidad.ws.EntidadService servicio=new dev2.pe.gob.segdi.wsentidad.ws.EntidadService();
		List<dev2.pe.gob.segdi.wsentidad.ws.EntidadBean> lista=servicio.getEntidadPort().getListaEntidad("123456",categoria);
		
		List<JIOEntidadBean> listaRes=new ArrayList<JIOEntidadBean>(); 
		for (dev2.pe.gob.segdi.wsentidad.ws.EntidadBean ioEntidad : lista) {
			JIOEntidadBean entidad=new JIOEntidadBean();
			entidad.setVnoment(ioEntidad.getVnoment());	
			entidad.setVrucent(ioEntidad.getVrucent());
			listaRes.add(entidad);
		}
		return listaRes;
	}
	private static List<JIOEntidadBean> wsGetListaEntidadDevLocal(int categoria){
		List<JIOEntidadBean> list=new ArrayList<JIOEntidadBean>();
		if(categoria==1){ //PODER EJECUTIVO

			JIOEntidadBean entidad1=new JIOEntidadBean();
			entidad1.setVnoment("Ministerio de la Produccion");	
			entidad1.setVrucent("30100000001");
			list.add(entidad1);

			JIOEntidadBean entidad2=new JIOEntidadBean();
			entidad2.setVnoment("Ministerio de Economía y Finanzas");	
			entidad2.setVrucent("30100000002");
			list.add(entidad2);
			
			JIOEntidadBean entidad3=new JIOEntidadBean();
			entidad3.setVnoment("Ministerio de Cultura");	
			entidad3.setVrucent("31100000004");
			list.add(entidad3);
			
			return list;

		}else if(categoria==2){//PODER LEGISLATIVO
			JIOEntidadBean entidad1=new JIOEntidadBean();
			entidad1.setVnoment("Congreso de la República");	
			entidad1.setVrucent("23100000001");
			list.add(entidad1);

			return list;
		}
		else if(categoria==3){//PODER JUDICIAL
			JIOEntidadBean entidad1=new JIOEntidadBean();
			entidad1.setVnoment("Poder Judicial");	
			entidad1.setVrucent("22100000001");
			list.add(entidad1);

			return list;
		}
		else{
			return list;
		}
	}

	public static String wsGetCuo(){
		if(modo==Modo.PROD){
			return wsGetCuoProd();
		}
		else if (modo==Modo.DEV){
			return wsGetCuoDev();
		}
		else if (modo==Modo.DEV_LOCALHOST){
			return wsGetCuoDevLocal();
		}
		else{
			throw new UnsupportedOperationException("No se ha implementado el servicio");
		}
	}
	private static String wsGetCuoProd(){
		prod2.pe.gob.pide.pcm.webservice.PcmCuo srv=new prod2.pe.gob.pide.pcm.webservice.PcmCuo();
		
		String ruc_entidad = Utilitarios.ObtenerDatosPropertiesUserHome("configuracion", "RUC_ENTIDAD");
		return srv.getPcmCuoHttpsSoap11Endpoint().getCUO(ruc_entidad, "3011");
		
	}
	private static String wsGetCuoDev(){
		// Generar número aleatorio entre 0 y 999990
		long numero = (long) (Math.random() * 1_000_000_000L);
		return String.format("%010d", numero);
	}
	private static String wsGetCuoDevLocal(){
		// Generar número aleatorio entre 0 y 999990
		int numero = (int) (Math.random() * 1000000);
		// Formatear el número a 6 dígitos rellenando con ceros a la izquierda
		return String.format("%06d", numero);
	}

	public static List<JIOTipoDocumentoTramite> getWSTipoDocumento(){
		if(modo==Modo.PROD){
			return getWSTipoDocumentoProd();
		}
		else if (modo==Modo.DEV){
			return getWSTipoDocumentoDev();
		}
		else if (modo==Modo.DEV_LOCALHOST){
			throw new UnsupportedOperationException("No se ha implementado el servicio para el modo DEV_LOCALHOST");
		}
		else{
			throw new UnsupportedOperationException("No se ha implementado el servicio");
		}
	}
	private static List<JIOTipoDocumentoTramite> getWSTipoDocumentoProd(){
		
		prod2.pe.gob.segdi.wsiopidetramite.ws.PcmIMgdTramite srv=new prod2.pe.gob.segdi.wsiopidetramite.ws.PcmIMgdTramite();
		
		List<prod2.pe.gob.segdi.wsiopidetramite.ws.IoTipoDocumentoTramite>lista = srv.getPcmIMgdTramiteHttpsSoap11Endpoint().getTipoDocumento();
		

		List<JIOTipoDocumentoTramite> lst=new ArrayList<JIOTipoDocumentoTramite>();
			
		for (prod2.pe.gob.segdi.wsiopidetramite.ws.IoTipoDocumentoTramite ioTipoDocumentoTramite : lista) {	
			JIOTipoDocumentoTramite t=new JIOTipoDocumentoTramite();
			t.setCcodtipdoctra(ioTipoDocumentoTramite.getCcodtipdoctra());
			t.setVnomtipdoctra(ioTipoDocumentoTramite.getVnomtipdoctra());
			
			lst.add(t);
		}
			
		return lst;
	}
	private static List<JIOTipoDocumentoTramite> getWSTipoDocumentoDev(){
		dev2.pe.gob.segdi.wsiopidetramite.ws.IOTramiteService srv=new dev2.pe.gob.segdi.wsiopidetramite.ws.IOTramiteService();
		List<dev2.pe.gob.segdi.wsiopidetramite.ws.IoTipoDocumentoTramite>  lista=srv.getIOTramitePort().getTipoDocumento();

		List<JIOTipoDocumentoTramite> lst=new ArrayList<JIOTipoDocumentoTramite>();
			
		for (dev2.pe.gob.segdi.wsiopidetramite.ws.IoTipoDocumentoTramite ioTipoDocumentoTramite : lista) {	
			JIOTipoDocumentoTramite t=new JIOTipoDocumentoTramite();
			t.setCcodtipdoctra(ioTipoDocumentoTramite.getCcodtipdoctra());
			t.setVnomtipdoctra(ioTipoDocumentoTramite.getVnomtipdoctra());
			
			lst.add(t);
		}
			
		return lst;
	}

    public static String wsCargoResponse(JIORecepcion recepcion,String vcuo,String vrucentrem) throws Exception {
		if(modo==Modo.PROD){

            return wsCargoResponseProd(recepcion, vcuo, vrucentrem);
		}
		else if (modo==Modo.DEV){

            return wsCargoResponseDev(recepcion, vcuo, vrucentrem);
		}
		else if (modo==Modo.DEV_LOCALHOST){
            
			return wsCargoResponseDevLocal(recepcion, vcuo);
		}
		else {
			throw new UnsupportedOperationException("No se ha implementado el servicio");
		}
	}
    private static String wsCargoResponseProd(JIORecepcion recepcion,String vcuo,String vrucentrec) throws Exception {
        String ruc_entidad = Utilitarios.ObtenerDatosPropertiesUserHome("configuracion", "RUC_ENTIDAD");

        prod2.pe.gob.segdi.wsiopidetramite.ws.PcmIMgdTramite locator=new prod2.pe.gob.segdi.wsiopidetramite.ws.PcmIMgdTramite();

        try {
            prod2.pe.gob.segdi.wsiopidetramite.ws.CargoTramite cargoRequest=new prod2.pe.gob.segdi.wsiopidetramite.ws.CargoTramite();
            
            byte[] pdfDocEncode = Base64.getEncoder().encode(recepcion.getBcarstd());
            GregorianCalendar dfecregstd = GregorianCalendar.from(recepcion.getDfecregstd());
			XMLGregorianCalendar dfecregstdxml = DatatypeFactory.newInstance().newXMLGregorianCalendar(dfecregstd);

            cargoRequest.setVrucentrem(ruc_entidad);
            cargoRequest.setVrucentrec(vrucentrec);

            cargoRequest.setVcuo(vcuo);
            //cargoRequest.setVcuoref("");no se usa
            cargoRequest.setVnumregstd(recepcion.getVnumregstd());
            cargoRequest.setVanioregstd(recepcion.getVanioregstd());
            cargoRequest.setDfecregstd(dfecregstdxml);
            cargoRequest.setVuniorgstd(recepcion.getVuniorgstd());
            cargoRequest.setVusuregstd(recepcion.getVusuregstd());
            cargoRequest.setBcarstd(pdfDocEncode);
            cargoRequest.setVobs(recepcion.getVobs());
            cargoRequest.setCflgest(recepcion.getCflgest());
            //cargoRequest.setVdesanxstdrec("null");no se usa
            
            prod2.pe.gob.segdi.wsiopidetramite.ws.RespuestaCargoTramite respuesta=locator.getPcmIMgdTramiteHttpsSoap11Endpoint().cargoResponse(cargoRequest);
            depurador.info("wsCargoResponse(vcuo="+vcuo+",vrucentrec="+vrucentrec+") ==> vcodres="+respuesta.getVcodres()+" vdesres="+respuesta.getVdesres());
			
            if(!respuesta.getVcodres().equals("0000")){
				depurador.error("Error en el servicio PIDE de cargo de trámite rucentrec="+vrucentrec+" cuo="+vcuo+" codres="+respuesta.getVcodres()+" desres="+respuesta.getVdesres());
                throw new ErrorWSCargoResponse(respuesta.getVcodres(),respuesta.getVdesres());
            }
            
            return respuesta.getVdesres();
            
        } catch (ErrorWSCargoResponse e){
            throw e;
        } catch (Exception e) {
            depurador.error(e.getMessage(),e);
            throw new ErrorCargoResponse(e.getMessage());
        }

    }
    private static String wsCargoResponseDev(JIORecepcion recepcion,String vcuo,String vrucentrec) throws Exception {

        String ruc_entidad = Utilitarios.ObtenerDatosPropertiesUserHome("configuracion", "RUC_ENTIDAD");
    	
        dev2.pe.gob.segdi.wsiopidetramite.ws.IOTramiteService locator=new dev2.pe.gob.segdi.wsiopidetramite.ws.IOTramiteService();
        
        try {
            dev2.pe.gob.segdi.wsiopidetramite.ws.CargoTramite cargoRequest=new dev2.pe.gob.segdi.wsiopidetramite.ws.CargoTramite();
            
            byte[] pdfDocEncode = Base64.getEncoder().encode(recepcion.getBcarstd());
            GregorianCalendar dfecregstd = GregorianCalendar.from(recepcion.getDfecregstd());
            XMLGregorianCalendar dfecregstdxml = DatatypeFactory.newInstance().newXMLGregorianCalendar(dfecregstd);
                
            cargoRequest.setVrucentrem(ruc_entidad);
            cargoRequest.setVrucentrec(vrucentrec);

            cargoRequest.setVcuo(vcuo);
            //cargoRequest.setVcuoref("");no se usa
            cargoRequest.setVnumregstd(recepcion.getVnumregstd());
            cargoRequest.setVanioregstd(recepcion.getVanioregstd());
            cargoRequest.setDfecregstd(dfecregstdxml);
            cargoRequest.setVuniorgstd(recepcion.getVuniorgstd());
            cargoRequest.setVusuregstd(recepcion.getVusuregstd());
            cargoRequest.setBcarstd(pdfDocEncode);
            cargoRequest.setVobs(recepcion.getVobs());
            cargoRequest.setCflgest(recepcion.getCflgest());
            //cargoRequest.setVdesanxstdrec("null");no se usa
            
            dev2.pe.gob.segdi.wsiopidetramite.ws.RespuestaCargoTramite respuesta=locator.getIOTramitePort().cargoResponse(cargoRequest);
            depurador.info("wsCargoResponse(vcuo="+vcuo+",vrucentrec="+vrucentrec+") ==> vcodres="+respuesta.getVcodres()+" vdesres="+respuesta.getVdesres());
			
            if(!respuesta.getVcodres().equals("0000")){
                depurador.error("Error en el servicio PIDE de cargo de trámite: "+respuesta.getVcodres()+" "+respuesta.getVdesres());
                throw new ErrorWSCargoResponse(respuesta.getVcodres(),respuesta.getVdesres());
            }

			return respuesta.getVdesres();
            
        } catch (ErrorWSCargoResponse e){
            throw e;
        } catch (Exception e) {
            depurador.error(e.getMessage(),e);
            throw new ErrorCargoResponse(e.getMessage());
        }
    
    }
    private static String wsCargoResponseDevLocal(JIORecepcion recepcion,String vcuo) throws Exception {

        local2.pe.gob.segdi.wsiotramite.ws.Tramite_Service locator=new local2.pe.gob.segdi.wsiotramite.ws.Tramite_Service();
			try {
				local2.pe.gob.segdi.wsiotramite.ws.CargoTramite cargoRequest=new local2.pe.gob.segdi.wsiotramite.ws.CargoTramite();
                
				byte[] pdfDocEncode = Base64.getEncoder().encode(recepcion.getBcarstd());
                GregorianCalendar dfecregstd = GregorianCalendar.from(recepcion.getDfecregstd());
                XMLGregorianCalendar dfecregstdxml = DatatypeFactory.newInstance().newXMLGregorianCalendar(dfecregstd);
                
                cargoRequest.setVcuo(vcuo);
                //cargoRequest.setVcuoref("");no se usa
                cargoRequest.setVnumregstd(recepcion.getVnumregstd());
                cargoRequest.setVanioregstd(recepcion.getVanioregstd());
                cargoRequest.setDfecregstd(dfecregstdxml);
                cargoRequest.setVuniorgstd(recepcion.getVuniorgstd());
                cargoRequest.setVusuregstd(recepcion.getVusuregstd());
                cargoRequest.setBcarstd(pdfDocEncode);
                cargoRequest.setVobs(recepcion.getVobs());
                cargoRequest.setCflgest(recepcion.getCflgest());
                //cargoRequest.setVdesanxstdrec("null");no se usa

				local2.pe.gob.segdi.wsiotramite.ws.RespuestaCargoTramite respuesta=locator.getTramitePort().cargoResponse(cargoRequest);
				depurador.info("wsCargoResponse(vcuo="+vcuo+") ==> vcodres="+respuesta.getVcodres()+" vdesres="+respuesta.getVdesres());
				
                if(!respuesta.getVcodres().equals("0000")){
                    depurador.error("Error en el servicio PIDE de cargo de trámite: "+respuesta.getVcodres()+" "+respuesta.getVdesres());
                    throw new ErrorWSCargoResponse(respuesta.getVcodres(),respuesta.getVdesres());
                }

				return respuesta.getVdesres();
				
			} catch (ErrorWSCargoResponse e){
				
                throw e;
            } catch (Exception e) {
				depurador.error(e.getMessage(),e);
				throw new ErrorCargoResponse(e.getMessage());
            }
    }

    public static String wsRecepcionarTramiteResponse(JIODespacho jioDespacho) throws Exception {
		if(modo==Modo.PROD){
            
			return wsRecepcionarTramiteResponseProd(jioDespacho);
		}
		else if (modo==Modo.DEV){

			return wsRecepcionarTramiteResponseDev(jioDespacho);
		}
		else if (modo==Modo.DEV_LOCALHOST){
            
			return wsRecepcionarTramiteResponseDevLocal(jioDespacho);
		}
		else {
			throw new UnsupportedOperationException("No se ha implementado el servicio");
		}
	}

    private static String wsRecepcionarTramiteResponseProd(JIODespacho jioDespacho) throws Exception {

		String ruc_entidad = Utilitarios.ObtenerDatosPropertiesUserHome("configuracion", "RUC_ENTIDAD");
    	prod2.pe.gob.segdi.wsiopidetramite.ws.RecepcionTramite despacho=new prod2.pe.gob.segdi.wsiopidetramite.ws.RecepcionTramite();

		GregorianCalendar dfecdoc = GregorianCalendar.from(jioDespacho.getDocumentoExterno().getDfecdoc());
		XMLGregorianCalendar dfecdocxml = DatatypeFactory.newInstance().newXMLGregorianCalendar(dfecdoc);

			byte[] pdfDocEncode = Base64.getEncoder().encode(jioDespacho.getDocumentoExterno().getDocumentoPrincipal().getBpdfdoc());

			despacho.setVrucentrem(ruc_entidad);
			despacho.setVrucentrec(jioDespacho.getVrucentrec());
			despacho.setVnomentemi(jioDespacho.getDocumentoExterno().getVnomentemi());
			despacho.setVuniorgrem(jioDespacho.getVuniorgrem());
			despacho.setVcuo(jioDespacho.getVcuo());
			despacho.setVcuoref(jioDespacho.getVcuoref());
			despacho.setCcodtipdoc(jioDespacho.getDocumentoExterno().getCcodtipdoc());	
			despacho.setVnumdoc(jioDespacho.getDocumentoExterno().getVnumdoc());
			despacho.setDfecdoc(dfecdocxml);
			
			despacho.setVuniorgdst(jioDespacho.getDocumentoExterno().getVuniorgdst());
			despacho.setVnomdst(jioDespacho.getDocumentoExterno().getVnomdst());
			despacho.setVnomcardst(jioDespacho.getDocumentoExterno().getVnomcardst());
			despacho.setVasu(jioDespacho.getDocumentoExterno().getVasu());
			despacho.setSnumanx(jioDespacho.getDocumentoExterno().getSnumanx());
			despacho.setSnumfol(jioDespacho.getDocumentoExterno().getSnumfol());
			despacho.setVurldocanx(jioDespacho.getDocumentoExterno().getVurldocanx());
			despacho.setBpdfdoc(pdfDocEncode);
			despacho.setVnomdoc(jioDespacho.getDocumentoExterno().getDocumentoPrincipal().getVnomdoc());

			despacho.setCtipdociderem(jioDespacho.getCtipdociderem());
			despacho.setVnumdociderem(jioDespacho.getVnumdociderem());

			int numAnexos=jioDespacho.getDocumentoExterno().getSnumanx();
			if(numAnexos>0){
				
				for (int j = 0; j < numAnexos; j++) {
					prod2.pe.gob.segdi.wsiopidetramite.ws.DocumentoAnexo documentoAnexo = new prod2.pe.gob.segdi.wsiopidetramite.ws.DocumentoAnexo();
					documentoAnexo.setVnomdoc(jioDespacho.getDocumentoExterno().getLstDocAnexo().get(j).getVnomdoc());
					despacho.getLstanexos().add(documentoAnexo);
				}
				
			}
			

			prod2.pe.gob.segdi.wsiopidetramite.ws.PcmIMgdTramite locator=new prod2.pe.gob.segdi.wsiopidetramite.ws.PcmIMgdTramite();
			prod2.pe.gob.segdi.wsiopidetramite.ws.RespuestaTramite respuesta=locator.getPcmIMgdTramiteHttpsSoap11Endpoint().recepcionarTramiteResponse(despacho);
			depurador.info("wsRecepcionarTramiteResponse(vcuo="+jioDespacho.getVcuo()+",vrucentrec="+jioDespacho.getVrucentrec()+") ==> vcodres="+respuesta.getVcodres()+" vdesres="+respuesta.getVdesres());
			
			if(!respuesta.getVcodres().equals("0000")){
                    depurador.error("Error no se pudo enviar el Documento de Despacho por la PIDE: "+respuesta.getVcodres()+" "+respuesta.getVdesres());
                    throw new ErrorWSDespachoResponse(respuesta.getVdesres());
            }

			return respuesta.getVdesres();
	}
	private static String wsRecepcionarTramiteResponseDev(JIODespacho jioDespacho) throws Exception {

		String ruc_entidad = Utilitarios.ObtenerDatosPropertiesUserHome("configuracion", "RUC_ENTIDAD");
    	dev2.pe.gob.segdi.wsiopidetramite.ws.RecepcionTramite despacho=new dev2.pe.gob.segdi.wsiopidetramite.ws.RecepcionTramite();

		GregorianCalendar dfecdoc = GregorianCalendar.from(jioDespacho.getDocumentoExterno().getDfecdoc());
		XMLGregorianCalendar dfecdocxml = DatatypeFactory.newInstance().newXMLGregorianCalendar(dfecdoc);
			
			byte[] pdfDocEncode = Base64.getEncoder().encode(jioDespacho.getDocumentoExterno().getDocumentoPrincipal().getBpdfdoc());
			
			despacho.setVrucentrem(ruc_entidad);
			despacho.setVrucentrec(jioDespacho.getVrucentrec());
			despacho.setVnomentemi(jioDespacho.getDocumentoExterno().getVnomentemi());
			despacho.setVuniorgrem(jioDespacho.getVuniorgrem());
			despacho.setVcuo(jioDespacho.getVcuo());
			despacho.setVcuoref(jioDespacho.getVcuoref());
			despacho.setCcodtipdoc(jioDespacho.getDocumentoExterno().getCcodtipdoc());	
			despacho.setVnumdoc(jioDespacho.getDocumentoExterno().getVnumdoc());
			despacho.setDfecdoc(dfecdocxml);
			
			despacho.setVuniorgdst(jioDespacho.getDocumentoExterno().getVuniorgdst());
			despacho.setVnomdst(jioDespacho.getDocumentoExterno().getVnomdst());
			despacho.setVnomcardst(jioDespacho.getDocumentoExterno().getVnomcardst());
			despacho.setVasu(jioDespacho.getDocumentoExterno().getVasu());
			despacho.setSnumanx(jioDespacho.getDocumentoExterno().getSnumanx());
			despacho.setSnumfol(jioDespacho.getDocumentoExterno().getSnumfol());
			despacho.setVurldocanx(jioDespacho.getDocumentoExterno().getVurldocanx());
			despacho.setBpdfdoc(pdfDocEncode);
			despacho.setVnomdoc(jioDespacho.getDocumentoExterno().getDocumentoPrincipal().getVnomdoc());

			despacho.setCtipdociderem(jioDespacho.getCtipdociderem());
			despacho.setVnumdociderem(jioDespacho.getVnumdociderem());

			int numAnexos=jioDespacho.getDocumentoExterno().getSnumanx();
			if(numAnexos>0){
				
				for (int j = 0; j < numAnexos; j++) {
					dev2.pe.gob.segdi.wsiopidetramite.ws.DocumentoAnexo documentoAnexo = new dev2.pe.gob.segdi.wsiopidetramite.ws.DocumentoAnexo();
					documentoAnexo.setVnomdoc(jioDespacho.getDocumentoExterno().getLstDocAnexo().get(j).getVnomdoc());
					despacho.getLstanexos().add(documentoAnexo);
				}
			}
			

			dev2.pe.gob.segdi.wsiopidetramite.ws.IOTramiteService locator=new dev2.pe.gob.segdi.wsiopidetramite.ws.IOTramiteService();
			dev2.pe.gob.segdi.wsiopidetramite.ws.RespuestaTramite respuesta=locator.getIOTramitePort().recepcionarTramiteResponse(despacho);
			depurador.info("wsRecepcionarTramiteResponse(vcuo="+jioDespacho.getVcuo()+",vrucentrec="+jioDespacho.getVrucentrec()+") ==> vcodres="+respuesta.getVcodres()+" vdesres="+respuesta.getVdesres());
			
			if(!respuesta.getVcodres().equals("0000")){
                    depurador.error("Error no se pudo enviar el Documento de Despacho por la PIDE: "+respuesta.getVcodres()+" "+respuesta.getVdesres());
                    throw new ErrorWSDespachoResponse(respuesta.getVdesres());
            }

			return respuesta.getVdesres();
	}
	private static String wsRecepcionarTramiteResponseDevLocal(JIODespacho jioDespacho) throws Exception {
		
		String ruc_entidad = Utilitarios.ObtenerDatosPropertiesUserHome("configuracion", "RUC_ENTIDAD");
    	
		local2.pe.gob.segdi.wsiotramite.ws.RecepcionTramite despacho=new local2.pe.gob.segdi.wsiotramite.ws.RecepcionTramite();
			
			GregorianCalendar dfecdoc = GregorianCalendar.from(jioDespacho.getDocumentoExterno().getDfecdoc());
			XMLGregorianCalendar dfecdocxml = DatatypeFactory.newInstance().newXMLGregorianCalendar(dfecdoc);

			byte[] pdfDocEncode = Base64.getEncoder().encode(jioDespacho.getDocumentoExterno().getDocumentoPrincipal().getBpdfdoc());

			despacho.setVrucentrem(ruc_entidad);
			//despacho.setVrucentrec(jioDespacho.getVrucentrec()); //no utilizado
			despacho.setVnomentemi(jioDespacho.getDocumentoExterno().getVnomentemi());
			despacho.setVuniorgrem(jioDespacho.getVuniorgrem());
			despacho.setVcuo(jioDespacho.getVcuo());
			despacho.setVcuoref(jioDespacho.getVcuoref());
			despacho.setCcodtipdoc(jioDespacho.getDocumentoExterno().getCcodtipdoc());	
			despacho.setVnumdoc(jioDespacho.getDocumentoExterno().getVnumdoc());
			despacho.setDfecdoc(dfecdocxml);
			
			despacho.setVuniorgdst(jioDespacho.getDocumentoExterno().getVuniorgdst());
			despacho.setVnomdst(jioDespacho.getDocumentoExterno().getVnomdst());
			despacho.setVnomcardst(jioDespacho.getDocumentoExterno().getVnomcardst());
			despacho.setVasu(jioDespacho.getDocumentoExterno().getVasu());
			despacho.setSnumanx(jioDespacho.getDocumentoExterno().getSnumanx());
			despacho.setSnumfol(jioDespacho.getDocumentoExterno().getSnumfol());
			despacho.setVurldocanx(jioDespacho.getDocumentoExterno().getVurldocanx());
			despacho.setBpdfdoc(pdfDocEncode);
			despacho.setVnomdoc(jioDespacho.getDocumentoExterno().getDocumentoPrincipal().getVnomdoc());

			despacho.setCtipdociderem(jioDespacho.getCtipdociderem());
			despacho.setVnumdociderem(jioDespacho.getVnumdociderem());

			int numAnexos=jioDespacho.getDocumentoExterno().getSnumanx();
			if(numAnexos>0){
				
				for (int j = 0; j < numAnexos; j++) {
					local2.pe.gob.segdi.wsiotramite.ws.DocumentoAnexo documentoAnexo = new local2.pe.gob.segdi.wsiotramite.ws.DocumentoAnexo();
					documentoAnexo.setVnomdoc(jioDespacho.getDocumentoExterno().getLstDocAnexo().get(j).getVnomdoc());
					despacho.getLstanexos().add(documentoAnexo);
				}
				
			}
			
			local2.pe.gob.segdi.wsiotramite.ws.Tramite_Service locator=new local2.pe.gob.segdi.wsiotramite.ws.Tramite_Service();
			local2.pe.gob.segdi.wsiotramite.ws.RespuestaTramite respuesta=locator.getTramitePort().recepcionarTramiteResponse(despacho);
			depurador.info("wsRecepcionarTramiteResponse(vcuo="+jioDespacho.getVcuo()+",vrucentrec="+jioDespacho.getVrucentrec()+") ==> vcodres="+respuesta.getVcodres()+" vdesres="+respuesta.getVdesres());
			
			if(!respuesta.getVcodres().equals("0000")){
                    depurador.error("Error no se pudo enviar el Documento de Despacho por la PIDE: "+respuesta.getVcodres()+" "+respuesta.getVdesres());
                    throw new ErrorWSDespachoResponse(respuesta.getVdesres());
            }

			return respuesta.getVdesres();
	}
}
