
package prod2.pe.gob.segdi.wsiopidetramite.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para RespuestaConsultaTramite complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="RespuestaConsultaTramite">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="vcodres" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vdesres" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vcuo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vcuoref" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vnumregstd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vanioregstd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vuniorgstd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dfecregstd" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="vusuregstd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bcarstd" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="vobs" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cflgest" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RespuestaConsultaTramite", propOrder = {
    "vcodres",
    "vdesres",
    "vcuo",
    "vcuoref",
    "vnumregstd",
    "vanioregstd",
    "vuniorgstd",
    "dfecregstd",
    "vusuregstd",
    "bcarstd",
    "vobs",
    "cflgest"
})
public class RespuestaConsultaTramite {

    protected String vcodres;
    protected String vdesres;
    protected String vcuo;
    protected String vcuoref;
    protected String vnumregstd;
    protected String vanioregstd;
    protected String vuniorgstd;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dfecregstd;
    protected String vusuregstd;
    protected byte[] bcarstd;
    protected String vobs;
    protected String cflgest;

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

    /**
     * Obtiene el valor de la propiedad vcuoref.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVcuoref() {
        return vcuoref;
    }

    /**
     * Define el valor de la propiedad vcuoref.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVcuoref(String value) {
        this.vcuoref = value;
    }

    /**
     * Obtiene el valor de la propiedad vnumregstd.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVnumregstd() {
        return vnumregstd;
    }

    /**
     * Define el valor de la propiedad vnumregstd.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVnumregstd(String value) {
        this.vnumregstd = value;
    }

    /**
     * Obtiene el valor de la propiedad vanioregstd.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVanioregstd() {
        return vanioregstd;
    }

    /**
     * Define el valor de la propiedad vanioregstd.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVanioregstd(String value) {
        this.vanioregstd = value;
    }

    /**
     * Obtiene el valor de la propiedad vuniorgstd.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVuniorgstd() {
        return vuniorgstd;
    }

    /**
     * Define el valor de la propiedad vuniorgstd.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVuniorgstd(String value) {
        this.vuniorgstd = value;
    }

    /**
     * Obtiene el valor de la propiedad dfecregstd.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDfecregstd() {
        return dfecregstd;
    }

    /**
     * Define el valor de la propiedad dfecregstd.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDfecregstd(XMLGregorianCalendar value) {
        this.dfecregstd = value;
    }

    /**
     * Obtiene el valor de la propiedad vusuregstd.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVusuregstd() {
        return vusuregstd;
    }

    /**
     * Define el valor de la propiedad vusuregstd.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVusuregstd(String value) {
        this.vusuregstd = value;
    }

    /**
     * Obtiene el valor de la propiedad bcarstd.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getBcarstd() {
        return bcarstd;
    }

    /**
     * Define el valor de la propiedad bcarstd.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setBcarstd(byte[] value) {
        this.bcarstd = value;
    }

    /**
     * Obtiene el valor de la propiedad vobs.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVobs() {
        return vobs;
    }

    /**
     * Define el valor de la propiedad vobs.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVobs(String value) {
        this.vobs = value;
    }

    /**
     * Obtiene el valor de la propiedad cflgest.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCflgest() {
        return cflgest;
    }

    /**
     * Define el valor de la propiedad cflgest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCflgest(String value) {
        this.cflgest = value;
    }

}
