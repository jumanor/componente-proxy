
package dev2.pe.gob.segdi.wsiopidetramite.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para DocumentoAnexo complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="DocumentoAnexo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="vnomdoc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DocumentoAnexo", propOrder = {
    "vnomdoc"
})
public class DocumentoAnexo {

    protected String vnomdoc;

    /**
     * Obtiene el valor de la propiedad vnomdoc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVnomdoc() {
        return vnomdoc;
    }

    /**
     * Define el valor de la propiedad vnomdoc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVnomdoc(String value) {
        this.vnomdoc = value;
    }

}
