
package prod2.pe.gob.segdi.wsiopidetramite.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para RespuestaCargoTramite complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="RespuestaCargoTramite">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="vcodres" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vdesres" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RespuestaCargoTramite", propOrder = {
    "vcodres",
    "vdesres"
})
public class RespuestaCargoTramite {

    protected String vcodres;
    protected String vdesres;

    /**
     * Obtiene el valor de la propiedad vcodres.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVcodres() {
        return vcodres;
    }

    /**
     * Define el valor de la propiedad vcodres.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVcodres(String value) {
        this.vcodres = value;
    }

    /**
     * Obtiene el valor de la propiedad vdesres.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVdesres() {
        return vdesres;
    }

    /**
     * Define el valor de la propiedad vdesres.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVdesres(String value) {
        this.vdesres = value;
    }

}
