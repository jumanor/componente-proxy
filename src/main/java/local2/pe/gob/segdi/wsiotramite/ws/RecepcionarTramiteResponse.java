
package local2.pe.gob.segdi.wsiotramite.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para recepcionarTramiteResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="recepcionarTramiteResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="recepcionRequest" type="{http://ws.wsiotramite.segdi.gob.pe/}RecepcionTramite" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "recepcionarTramiteResponse", propOrder = {
    "recepcionRequest"
})
public class RecepcionarTramiteResponse {

    protected RecepcionTramite recepcionRequest;

    /**
     * Obtiene el valor de la propiedad recepcionRequest.
     * 
     * @return
     *     possible object is
     *     {@link RecepcionTramite }
     *     
     */
    public RecepcionTramite getRecepcionRequest() {
        return recepcionRequest;
    }

    /**
     * Define el valor de la propiedad recepcionRequest.
     * 
     * @param value
     *     allowed object is
     *     {@link RecepcionTramite }
     *     
     */
    public void setRecepcionRequest(RecepcionTramite value) {
        this.recepcionRequest = value;
    }

}
