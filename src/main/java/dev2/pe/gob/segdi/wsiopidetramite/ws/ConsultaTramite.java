
package dev2.pe.gob.segdi.wsiopidetramite.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ConsultaTramite complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConsultaTramite">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="vrucentrem" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="vrucentrec" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="vcuo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConsultaTramite", propOrder = {
    "vrucentrem",
    "vrucentrec",
    "vcuo"
})
public class ConsultaTramite {

    @XmlElement(required = true)
    protected String vrucentrem;
    @XmlElement(required = true)
    protected String vrucentrec;
    @XmlElement(required = true)
    protected String vcuo;

    /**
     * Obtiene el valor de la propiedad vrucentrem.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVrucentrem() {
        return vrucentrem;
    }

    /**
     * Define el valor de la propiedad vrucentrem.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVrucentrem(String value) {
        this.vrucentrem = value;
    }

    /**
     * Obtiene el valor de la propiedad vrucentrec.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVrucentrec() {
        return vrucentrec;
    }

    /**
     * Define el valor de la propiedad vrucentrec.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVrucentrec(String value) {
        this.vrucentrec = value;
    }

    /**
     * Obtiene el valor de la propiedad vcuo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVcuo() {
        return vcuo;
    }

    /**
     * Define el valor de la propiedad vcuo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVcuo(String value) {
        this.vcuo = value;
    }

}
