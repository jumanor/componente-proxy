
package local2.pe.gob.segdi.wsiotramite.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para documentoAnexo complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="documentoAnexo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dfecreg" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="siddocanx" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="siddocext" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
@XmlType(name = "documentoAnexo", propOrder = {
    "dfecreg",
    "siddocanx",
    "siddocext",
    "vnomdoc"
})
public class DocumentoAnexo {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dfecreg;
    protected int siddocanx;
    protected int siddocext;
    protected String vnomdoc;

    /**
     * Obtiene el valor de la propiedad dfecreg.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDfecreg() {
        return dfecreg;
    }

    /**
     * Define el valor de la propiedad dfecreg.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDfecreg(XMLGregorianCalendar value) {
        this.dfecreg = value;
    }

    /**
     * Obtiene el valor de la propiedad siddocanx.
     * 
     */
    public int getSiddocanx() {
        return siddocanx;
    }

    /**
     * Define el valor de la propiedad siddocanx.
     * 
     */
    public void setSiddocanx(int value) {
        this.siddocanx = value;
    }

    /**
     * Obtiene el valor de la propiedad siddocext.
     * 
     */
    public int getSiddocext() {
        return siddocext;
    }

    /**
     * Define el valor de la propiedad siddocext.
     * 
     */
    public void setSiddocext(int value) {
        this.siddocext = value;
    }

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
