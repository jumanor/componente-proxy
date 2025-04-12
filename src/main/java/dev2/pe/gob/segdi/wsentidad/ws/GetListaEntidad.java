
package dev2.pe.gob.segdi.wsentidad.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para getListaEntidad complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="getListaEntidad">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="vipent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sidcatent" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getListaEntidad", propOrder = {
    "vipent",
    "sidcatent"
})
public class GetListaEntidad {

    protected String vipent;
    protected int sidcatent;

    /**
     * Obtiene el valor de la propiedad vipent.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVipent() {
        return vipent;
    }

    /**
     * Define el valor de la propiedad vipent.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVipent(String value) {
        this.vipent = value;
    }

    /**
     * Obtiene el valor de la propiedad sidcatent.
     * 
     */
    public int getSidcatent() {
        return sidcatent;
    }

    /**
     * Define el valor de la propiedad sidcatent.
     * 
     */
    public void setSidcatent(int value) {
        this.sidcatent = value;
    }

}
