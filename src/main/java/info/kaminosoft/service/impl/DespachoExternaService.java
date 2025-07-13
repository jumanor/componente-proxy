package info.kaminosoft.service.impl;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.jboss.logging.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import info.kaminosoft.bean.JIODespacho;
import info.kaminosoft.bean.JIORespuestaConsultaTramite;
import info.kaminosoft.dao.IDespachoDao;
import info.kaminosoft.service.IDespachoExternaService;
import info.kaminosoft.service.IDocumentoExternoService;
import info.kaminosoft.service.exceptions.ErrorChangeStateDespacho;
import info.kaminosoft.service.exceptions.ExceptionEnvioCargoStdExterno;
import info.kaminosoft.util.Utilitarios;

import java.security.cert.X509Certificate;

@Service("iDespachoExternaService")
public class DespachoExternaService implements IDespachoExternaService{
	private static Logger depurador = Logger.getLogger(DespachoExternaService.class.getName());
	
	@Autowired
	IDocumentoExternoService iDocumentoExternoService;

	@Autowired
	IDespachoDao iDespachoDao;
	
	@Override
	public JIODespacho getDespachoByNumRegStd(String vnumregstd)throws Exception{
		
		try{
			JIODespacho result=iDespachoDao.getDespachoByNumRegStd(vnumregstd);
			
			return result;
			
		}
		catch(EmptyResultDataAccessException e){
			return null;
		}
		
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updEstadoDespacho(String vnumregstd,String cflgest,String vnumregstdref) throws Exception {
		
		int row=iDespachoDao.updEstadoDespachoByNumRegStd(vnumregstd, cflgest);
		if(row==0){
            throw new ErrorChangeStateDespacho("Error al actualizar el despacho: no se encontró el registro");
        }
        else if(row>1){
            throw new ErrorChangeStateDespacho("Error al actualizar el despacho: se encontraron más de un registro");
        }
		if(vnumregstdref!=null){
			JIODespacho resultado_ref=iDespachoDao.getDespachoByNumRegStd(vnumregstdref);
			String vcuo_ref=resultado_ref.getVcuo();
			
			String cflgest_ref=resultado_ref.getCflgest();
			if(cflgest_ref.equals("O")){
				int numRow=iDespachoDao.updEstadoDespachoByNumRegStd(vnumregstdref,"S");
				if(numRow==0){
					depurador.error("Error al actualizar el estado a Subsanado: no se encontró vnumregstdref :"+vnumregstdref+" vcuo: "+vcuo_ref);
					throw new ErrorChangeStateDespacho("Error al actualizar el estado a Subsanado: no se encontró "+ 
					"el documento vnumregstdref : "+vnumregstdref);
				}	
			}
			else{
				depurador.error("Error al actualizar vnumregstdref : "+vnumregstdref+" vcuo: "+vcuo_ref+" al estado a Subsanado: el estado es: "+cflgest_ref+ "y debe ser: O");
				throw new ErrorChangeStateDespacho("Error al actualizar el estado a Subsanado: el estado actual del documento "+
				"vnumregstdref : "+vnumregstdref+" referenciado debe ser OBSERVADO");
			}
		}
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void removeDespacho(String vnumregstd) throws Exception{
		
		JIODespacho despacho=iDespachoDao.getDespachoByNumRegStd(vnumregstd);
		long sidemiext=despacho.getSidemiext();
		iDocumentoExternoService.removeDocumentoExternoByIdEmiExt(sidemiext);
		iDespachoDao.removeDespacho(sidemiext);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public JIODespacho insDespacho(JIODespacho despacho,String vnumregstdref) throws Exception {
		
		if(vnumregstdref!=null){
			String[] resultado=iDespachoDao.getCuoAndEstadoByNumRegStd(vnumregstdref);
			String vcuo=resultado[0];

			despacho.setVcuoref(vcuo);//actualizamos al registro actual
		}
		
		int sidemiext = iDespachoDao.insDespacho(despacho);	
		despacho.getDocumentoExterno().setSidemiext(sidemiext);
		iDocumentoExternoService.insDocumentoExterno(despacho.getDocumentoExterno());

		return despacho;
	}


	public void event_cargo(String vnumregstd,String cflgest) throws ExceptionEnvioCargoStdExterno{
		String nombre_std = Utilitarios.ObtenerDatosPropertiesUserHome("configuracion", "NOMBRE_STD");
		String user = Utilitarios.ObtenerDatosPropertiesUserHome("configuracion", "URL_WEBHOOK_STD_USER");
    	String pass = Utilitarios.ObtenerDatosPropertiesUserHome("configuracion", "URL_WEBHOOK_STD_PASS");
    	String path_api_despacho = Utilitarios.ObtenerDatosPropertiesUserHome("configuracion", "URL_WEBHOOK_STD_PATH_EVENT_CARGO");
    	
    	TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() { return null; }
                public void checkClientTrusted(X509Certificate[] certs, String authType) {}
                public void checkServerTrusted(X509Certificate[] certs, String authType) {}
            }
        };
        
        try{
        	
	        SSLContext sslContext = SSLContext.getInstance("TLS");
	        sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
			
			HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic(user,pass);
			
			Client client = ClientBuilder.newBuilder()
	                .sslContext(sslContext)
	                .hostnameVerifier((hostname, session) -> true)
	                .build();
	        client.register(feature);
	        
			Form frm=new Form();
			frm.param("vnumregstd", vnumregstd);
			frm.param("cflgest",cflgest);
	
			WebTarget webTarget = client.target(path_api_despacho);
			
			Invocation.Builder invocationBuilder = webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON);
			Response response;

		
			response =  invocationBuilder.post(Entity.form(frm));
			if(response.getStatus()==200) {
				
				String cad=response.readEntity(String.class);
				depurador.info("respuesta de "+nombre_std+" ==>"+cad);
				
				JSONObject obj=new JSONObject(cad);
				
				if(obj.getString("estado").equals("0000")) {
						
					//Success

				}
				else {
					
					throw new ExceptionEnvioCargoStdExterno(obj.getString("error"));
					
				}
			}	
			else {
				String error=response.readEntity(String.class);
				throw new ExceptionEnvioCargoStdExterno(error);

			}
			
		}catch(Exception e) {
			String error="error la realizar peticion POST a: "+path_api_despacho;
			depurador.error(error,e);
			throw new ExceptionEnvioCargoStdExterno(error);
		}

		
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int insCargoRecepcion(JIORespuestaConsultaTramite cargo,String vcuo,String vnumregstd) throws Exception {
		
		int row=0;
		if(cargo.getCflgest().equals("R")) {
			cargo.setVobs(null);
			row=iDespachoDao.updCargo(cargo,vcuo);
			event_cargo(vnumregstd,"R");
			depurador.info("cargo sincronizado ==> row="+row+" cflgest=R");
		}
		if(cargo.getCflgest().equals("O")) {
			row=iDespachoDao.updCargo(cargo,vcuo);
			event_cargo(vnumregstd,"O");
			depurador.info("cargo sincronizado ==> row="+row+" cflgest=O");
		}
		return row;
	}

}
