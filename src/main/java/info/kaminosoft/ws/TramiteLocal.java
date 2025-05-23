package info.kaminosoft.ws;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.logging.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import info.kaminosoft.bean.JICargoDespacho;
import info.kaminosoft.bean.JIODocumentoDespachado;
import info.kaminosoft.bean.JSRespuesta;
import info.kaminosoft.dao.exceptions.ErrorSinRegistroDespacho;
import info.kaminosoft.service.IDespachoLocalService;


@Path("/local")
public class TramiteLocal {
	
	private static final ApplicationContext beanFactory;
	
	private static Logger depurador = Logger.getLogger(TramiteLocal.class.getName());

	static {
		try {
			beanFactory = new ClassPathXmlApplicationContext("/applicationContext.xml");
		} catch (Exception e) {
			depurador.error("Error inicializando contexto", e);
			throw new RuntimeException(e);
		}
	}
	
	private IDespachoLocalService getDespachLocalServiceBean() {
		return beanFactory.getBean("iDespachoLocalService", IDespachoLocalService.class);
	}
	
	@GET
	@RolesAllowed({"restringido"})
    @Path("/documento/despachado/{vnumregstd}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDocumentoDespachadoLocalByNumRegStd(@PathParam("vnumregstd") String vnumregstd,@PathParam("token") String token){
		
		JSRespuesta respuesta = new JSRespuesta();
		
		try {
			
			JIODocumentoDespachado despacho=getDespachLocalServiceBean().getDocumentoDespachado(vnumregstd);
			respuesta.setData(despacho);
			respuesta.setEstado("0000");
			respuesta.setError(null);
		
		}catch(ErrorSinRegistroDespacho e){
			
			String codigoError="E001";
			depurador.error("Error "+codigoError,e);

			respuesta.setData(null);
			respuesta.setEstado(codigoError);
			respuesta.setError("registro no encontrado");
		
		}catch (Exception e) {
			String codigoError="-1";
			depurador.error("Error "+codigoError,e);

			respuesta.setData(null);
			respuesta.setEstado(codigoError);
			respuesta.setError("Error inesperado al recuperar documento de despacho ");
		}
		
		
		return Response.status(Response.Status.OK).entity(respuesta).build();
	}

	@GET
	@RolesAllowed({"restringido"})
    @Path("/cargo/despachado/{vnumregstd}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCargoDespachadoLocalByNumRegStd(@PathParam("vnumregstd") String vnumregstd,@PathParam("token") String token){
		
		JSRespuesta respuesta = new JSRespuesta();
		
		try {
			
			JICargoDespacho cargo=getDespachLocalServiceBean().getCargoDespachado(vnumregstd);
			respuesta.setData(cargo);
			respuesta.setEstado("0000");
			respuesta.setError(null);
		
		}catch(ErrorSinRegistroDespacho e){
			
			String codigoError="E001";
			depurador.error("Error "+codigoError,e);

			respuesta.setData(null);
			respuesta.setEstado(codigoError);
			respuesta.setError("registro no encontrado");
		
		}catch (Exception e) {
			String codigoError="-1";
			depurador.error("Error "+codigoError,e);

			respuesta.setData(null);
			respuesta.setEstado(codigoError);
			respuesta.setError("Error inesperado al recuperar documento de despacho ");
		}
		
		
		return Response.status(Response.Status.OK).entity(respuesta).build();
	}
	
}
