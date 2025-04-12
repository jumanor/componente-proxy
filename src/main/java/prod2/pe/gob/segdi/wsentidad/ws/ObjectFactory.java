
package prod2.pe.gob.segdi.wsentidad.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the prod2.pe.gob.segdi.wsentidad.ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetListaEntidad_QNAME = new QName("http://ws.wsentidad.segdi.gob.pe/", "getListaEntidad");
    private final static QName _ValidarEntidad_QNAME = new QName("http://ws.wsentidad.segdi.gob.pe/", "validarEntidad");
    private final static QName _GetListaEntidadResponse_QNAME = new QName("http://ws.wsentidad.segdi.gob.pe/", "getListaEntidadResponse");
    private final static QName _ValidarEntidadResponse_QNAME = new QName("http://ws.wsentidad.segdi.gob.pe/", "validarEntidadResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: prod2.pe.gob.segdi.wsentidad.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetListaEntidadResponse }
     * 
     */
    public GetListaEntidadResponse createGetListaEntidadResponse() {
        return new GetListaEntidadResponse();
    }

    /**
     * Create an instance of {@link GetListaEntidad }
     * 
     */
    public GetListaEntidad createGetListaEntidad() {
        return new GetListaEntidad();
    }

    /**
     * Create an instance of {@link ValidarEntidad }
     * 
     */
    public ValidarEntidad createValidarEntidad() {
        return new ValidarEntidad();
    }

    /**
     * Create an instance of {@link ValidarEntidadResponse }
     * 
     */
    public ValidarEntidadResponse createValidarEntidadResponse() {
        return new ValidarEntidadResponse();
    }

    /**
     * Create an instance of {@link EntidadBean }
     * 
     */
    public EntidadBean createEntidadBean() {
        return new EntidadBean();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetListaEntidad }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.wsentidad.segdi.gob.pe/", name = "getListaEntidad")
    public JAXBElement<GetListaEntidad> createGetListaEntidad(GetListaEntidad value) {
        return new JAXBElement<GetListaEntidad>(_GetListaEntidad_QNAME, GetListaEntidad.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidarEntidad }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.wsentidad.segdi.gob.pe/", name = "validarEntidad")
    public JAXBElement<ValidarEntidad> createValidarEntidad(ValidarEntidad value) {
        return new JAXBElement<ValidarEntidad>(_ValidarEntidad_QNAME, ValidarEntidad.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetListaEntidadResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.wsentidad.segdi.gob.pe/", name = "getListaEntidadResponse")
    public JAXBElement<GetListaEntidadResponse> createGetListaEntidadResponse(GetListaEntidadResponse value) {
        return new JAXBElement<GetListaEntidadResponse>(_GetListaEntidadResponse_QNAME, GetListaEntidadResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidarEntidadResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.wsentidad.segdi.gob.pe/", name = "validarEntidadResponse")
    public JAXBElement<ValidarEntidadResponse> createValidarEntidadResponse(ValidarEntidadResponse value) {
        return new JAXBElement<ValidarEntidadResponse>(_ValidarEntidadResponse_QNAME, ValidarEntidadResponse.class, null, value);
    }

}
