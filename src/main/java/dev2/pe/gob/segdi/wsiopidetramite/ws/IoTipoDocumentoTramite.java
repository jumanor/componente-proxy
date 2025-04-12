
package dev2.pe.gob.segdi.wsiopidetramite.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ioTipoDocumentoTramite complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ioTipoDocumentoTramite">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ccodtipdoctra" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vnomtipdoctra" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ioTipoDocumentoTramite", propOrder = {
    "ccodtipdoctra",
    "vnomtipdoctra"
})
public class IoTipoDocumentoTramite {

    protected String ccodtipdoctra;
    protected String vnomtipdoctra;

    /**
     * Obtiene el valor de la propiedad ccodtipdoctra.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCcodtipdoctra() {
        return ccodtipdoctra;
    }

    /**
     * Define el valor de la propiedad ccodtipdoctra.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCcodtipdoctra(String value) {
        this.ccodtipdoctra = value;
    }

    /**
     * Obtiene el valor de la propiedad vnomtipdoctra.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVnomtipdoctra() {
        return vnomtipdoctra;
    }

    /**
     * Define el valor de la propiedad vnomtipdoctra.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVnomtipdoctra(String value) {
        this.vnomtipdoctra = value;
    }

}
