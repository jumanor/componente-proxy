
package prod2.pe.gob.segdi.wsentidad.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para EntidadBean complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="EntidadBean">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sident" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="sidpadent" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="vnoment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vrucent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EntidadBean", propOrder = {
    "sident",
    "sidpadent",
    "vnoment",
    "vrucent"
})
public class EntidadBean {

    protected int sident;
    protected int sidpadent;
    protected String vnoment;
    protected String vrucent;

    /**
     * Obtiene el valor de la propiedad sident.
     * 
     */
    public int getSident() {
        return sident;
    }

    /**
     * Define el valor de la propiedad sident.
     * 
     */
    public void setSident(int value) {
        this.sident = value;
    }

    /**
     * Obtiene el valor de la propiedad sidpadent.
     * 
     */
    public int getSidpadent() {
        return sidpadent;
    }

    /**
     * Define el valor de la propiedad sidpadent.
     * 
     */
    public void setSidpadent(int value) {
        this.sidpadent = value;
    }

    /**
     * Obtiene el valor de la propiedad vnoment.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVnoment() {
        return vnoment;
    }

    /**
     * Define el valor de la propiedad vnoment.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVnoment(String value) {
        this.vnoment = value;
    }

    /**
     * Obtiene el valor de la propiedad vrucent.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVrucent() {
        return vrucent;
    }

    /**
     * Define el valor de la propiedad vrucent.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVrucent(String value) {
        this.vrucent = value;
    }

}
