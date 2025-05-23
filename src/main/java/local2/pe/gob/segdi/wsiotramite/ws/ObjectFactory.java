
package local2.pe.gob.segdi.wsiotramite.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the local2.pe.gob.segdi.wsiotramite.ws package. 
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

    private final static QName _ConsultarTramiteResponse_QNAME = new QName("http://ws.wsiotramite.segdi.gob.pe/", "consultarTramiteResponse");
    private final static QName _RecepcionarTramiteResponse_QNAME = new QName("http://ws.wsiotramite.segdi.gob.pe/", "recepcionarTramiteResponse");
    private final static QName _ConsultarTramiteResponseResponse_QNAME = new QName("http://ws.wsiotramite.segdi.gob.pe/", "consultarTramiteResponseResponse");
    private final static QName _CargoResponse_QNAME = new QName("http://ws.wsiotramite.segdi.gob.pe/", "cargoResponse");
    private final static QName _RecepcionarTramiteResponseResponse_QNAME = new QName("http://ws.wsiotramite.segdi.gob.pe/", "recepcionarTramiteResponseResponse");
    private final static QName _CargoResponseResponse_QNAME = new QName("http://ws.wsiotramite.segdi.gob.pe/", "cargoResponseResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: local2.pe.gob.segdi.wsiotramite.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CargoResponse }
     * 
     */
    public CargoResponse createCargoResponse() {
        return new CargoResponse();
    }

    /**
     * Create an instance of {@link RecepcionarTramiteResponseResponse }
     * 
     */
    public RecepcionarTramiteResponseResponse createRecepcionarTramiteResponseResponse() {
        return new RecepcionarTramiteResponseResponse();
    }

    /**
     * Create an instance of {@link CargoResponseResponse }
     * 
     */
    public CargoResponseResponse createCargoResponseResponse() {
        return new CargoResponseResponse();
    }

    /**
     * Create an instance of {@link ConsultarTramiteResponse }
     * 
     */
    public ConsultarTramiteResponse createConsultarTramiteResponse() {
        return new ConsultarTramiteResponse();
    }

    /**
     * Create an instance of {@link RecepcionarTramiteResponse }
     * 
     */
    public RecepcionarTramiteResponse createRecepcionarTramiteResponse() {
        return new RecepcionarTramiteResponse();
    }

    /**
     * Create an instance of {@link ConsultarTramiteResponseResponse }
     * 
     */
    public ConsultarTramiteResponseResponse createConsultarTramiteResponseResponse() {
        return new ConsultarTramiteResponseResponse();
    }

    /**
     * Create an instance of {@link RespuestaConsultaTramite }
     * 
     */
    public RespuestaConsultaTramite createRespuestaConsultaTramite() {
        return new RespuestaConsultaTramite();
    }

    /**
     * Create an instance of {@link RecepcionTramite }
     * 
     */
    public RecepcionTramite createRecepcionTramite() {
        return new RecepcionTramite();
    }

    /**
     * Create an instance of {@link CargoTramite }
     * 
     */
    public CargoTramite createCargoTramite() {
        return new CargoTramite();
    }

    /**
     * Create an instance of {@link RespuestaCargoTramite }
     * 
     */
    public RespuestaCargoTramite createRespuestaCargoTramite() {
        return new RespuestaCargoTramite();
    }

    /**
     * Create an instance of {@link RespuestaTramite }
     * 
     */
    public RespuestaTramite createRespuestaTramite() {
        return new RespuestaTramite();
    }

    /**
     * Create an instance of {@link DocumentoAnexo }
     * 
     */
    public DocumentoAnexo createDocumentoAnexo() {
        return new DocumentoAnexo();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultarTramiteResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.wsiotramite.segdi.gob.pe/", name = "consultarTramiteResponse")
    public JAXBElement<ConsultarTramiteResponse> createConsultarTramiteResponse(ConsultarTramiteResponse value) {
        return new JAXBElement<ConsultarTramiteResponse>(_ConsultarTramiteResponse_QNAME, ConsultarTramiteResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RecepcionarTramiteResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.wsiotramite.segdi.gob.pe/", name = "recepcionarTramiteResponse")
    public JAXBElement<RecepcionarTramiteResponse> createRecepcionarTramiteResponse(RecepcionarTramiteResponse value) {
        return new JAXBElement<RecepcionarTramiteResponse>(_RecepcionarTramiteResponse_QNAME, RecepcionarTramiteResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultarTramiteResponseResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.wsiotramite.segdi.gob.pe/", name = "consultarTramiteResponseResponse")
    public JAXBElement<ConsultarTramiteResponseResponse> createConsultarTramiteResponseResponse(ConsultarTramiteResponseResponse value) {
        return new JAXBElement<ConsultarTramiteResponseResponse>(_ConsultarTramiteResponseResponse_QNAME, ConsultarTramiteResponseResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CargoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.wsiotramite.segdi.gob.pe/", name = "cargoResponse")
    public JAXBElement<CargoResponse> createCargoResponse(CargoResponse value) {
        return new JAXBElement<CargoResponse>(_CargoResponse_QNAME, CargoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RecepcionarTramiteResponseResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.wsiotramite.segdi.gob.pe/", name = "recepcionarTramiteResponseResponse")
    public JAXBElement<RecepcionarTramiteResponseResponse> createRecepcionarTramiteResponseResponse(RecepcionarTramiteResponseResponse value) {
        return new JAXBElement<RecepcionarTramiteResponseResponse>(_RecepcionarTramiteResponseResponse_QNAME, RecepcionarTramiteResponseResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CargoResponseResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.wsiotramite.segdi.gob.pe/", name = "cargoResponseResponse")
    public JAXBElement<CargoResponseResponse> createCargoResponseResponse(CargoResponseResponse value) {
        return new JAXBElement<CargoResponseResponse>(_CargoResponseResponse_QNAME, CargoResponseResponse.class, null, value);
    }

}
