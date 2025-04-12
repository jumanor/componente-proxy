
package prod2.pe.gob.pide.pcm.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "PcmCuoPortType", targetNamespace = "http://pcm.pide.gob.pe/webservice")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface PcmCuoPortType {


    /**
     * Retorna string CUO
     * 
     * @param ruc
     * @param servicio
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "http://pcm.pide.gob.pe/webservice#ShowArchivos")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getCUOEntidad", targetNamespace = "http://pcm.pide.gob.pe/webservice", className = "prod2.pe.gob.pide.pcm.webservice.GetCUOEntidad")
    @ResponseWrapper(localName = "getCUOEntidadResponse", targetNamespace = "http://pcm.pide.gob.pe/webservice", className = "prod2.pe.gob.pide.pcm.webservice.GetCUOEntidadResponse")
    public String getCUOEntidad(
        @WebParam(name = "ruc", targetNamespace = "")
        String ruc,
        @WebParam(name = "servicio", targetNamespace = "")
        String servicio);

    /**
     * Retorna string CUO
     * 
     * @param ruc
     * @param servicio
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "http://pcm.pide.gob.pe/webservice#ShowArchivo")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getCUO", targetNamespace = "http://pcm.pide.gob.pe/webservice", className = "prod2.pe.gob.pide.pcm.webservice.GetCUO")
    @ResponseWrapper(localName = "getCUOResponse", targetNamespace = "http://pcm.pide.gob.pe/webservice", className = "prod2.pe.gob.pide.pcm.webservice.GetCUOResponse")
    public String getCUO(
        @WebParam(name = "ruc", targetNamespace = "")
        String ruc,
        @WebParam(name = "servicio", targetNamespace = "")
        String servicio);

}
