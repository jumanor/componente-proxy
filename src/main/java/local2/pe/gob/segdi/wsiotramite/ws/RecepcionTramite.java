
package local2.pe.gob.segdi.wsiotramite.ws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para RecepcionTramite complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="RecepcionTramite">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="vrucentrem" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vrucentrec" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vnomentemi" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vuniorgrem" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vcuo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vcuoref" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ccodtipdoc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vnumdoc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dfecdoc" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="vuniorgdst" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vnomdst" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vnomcardst" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vasu" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="snumanx" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="snumfol" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="vurldocanx" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bpdfdoc" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="vnomdoc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lstanexos" type="{http://ws.wsiotramite.segdi.gob.pe/}documentoAnexo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ctipdociderem" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vnumdociderem" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RecepcionTramite", propOrder = {
    "vrucentrem",
    "vrucentrec",
    "vnomentemi",
    "vuniorgrem",
    "vcuo",
    "vcuoref",
    "ccodtipdoc",
    "vnumdoc",
    "dfecdoc",
    "vuniorgdst",
    "vnomdst",
    "vnomcardst",
    "vasu",
    "snumanx",
    "snumfol",
    "vurldocanx",
    "bpdfdoc",
    "vnomdoc",
    "lstanexos",
    "ctipdociderem",
    "vnumdociderem"
})
public class RecepcionTramite {

    protected String vrucentrem;
    protected String vrucentrec;
    protected String vnomentemi;
    protected String vuniorgrem;
    protected String vcuo;
    protected String vcuoref;
    protected String ccodtipdoc;
    protected String vnumdoc;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dfecdoc;
    protected String vuniorgdst;
    protected String vnomdst;
    protected String vnomcardst;
    protected String vasu;
    protected int snumanx;
    protected int snumfol;
    protected String vurldocanx;
    protected byte[] bpdfdoc;
    protected String vnomdoc;
    @XmlElement(nillable = true)
    protected List<DocumentoAnexo> lstanexos;
    protected String ctipdociderem;
    protected String vnumdociderem;

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
     * Obtiene el valor de la propiedad vnomentemi.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVnomentemi() {
        return vnomentemi;
    }

    /**
     * Define el valor de la propiedad vnomentemi.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVnomentemi(String value) {
        this.vnomentemi = value;
    }

    /**
     * Obtiene el valor de la propiedad vuniorgrem.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVuniorgrem() {
        return vuniorgrem;
    }

    /**
     * Define el valor de la propiedad vuniorgrem.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVuniorgrem(String value) {
        this.vuniorgrem = value;
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
     * Obtiene el valor de la propiedad ccodtipdoc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCcodtipdoc() {
        return ccodtipdoc;
    }

    /**
     * Define el valor de la propiedad ccodtipdoc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCcodtipdoc(String value) {
        this.ccodtipdoc = value;
    }

    /**
     * Obtiene el valor de la propiedad vnumdoc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVnumdoc() {
        return vnumdoc;
    }

    /**
     * Define el valor de la propiedad vnumdoc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVnumdoc(String value) {
        this.vnumdoc = value;
    }

    /**
     * Obtiene el valor de la propiedad dfecdoc.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDfecdoc() {
        return dfecdoc;
    }

    /**
     * Define el valor de la propiedad dfecdoc.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDfecdoc(XMLGregorianCalendar value) {
        this.dfecdoc = value;
    }

    /**
     * Obtiene el valor de la propiedad vuniorgdst.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVuniorgdst() {
        return vuniorgdst;
    }

    /**
     * Define el valor de la propiedad vuniorgdst.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVuniorgdst(String value) {
        this.vuniorgdst = value;
    }

    /**
     * Obtiene el valor de la propiedad vnomdst.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVnomdst() {
        return vnomdst;
    }

    /**
     * Define el valor de la propiedad vnomdst.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVnomdst(String value) {
        this.vnomdst = value;
    }

    /**
     * Obtiene el valor de la propiedad vnomcardst.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVnomcardst() {
        return vnomcardst;
    }

    /**
     * Define el valor de la propiedad vnomcardst.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVnomcardst(String value) {
        this.vnomcardst = value;
    }

    /**
     * Obtiene el valor de la propiedad vasu.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVasu() {
        return vasu;
    }

    /**
     * Define el valor de la propiedad vasu.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVasu(String value) {
        this.vasu = value;
    }

    /**
     * Obtiene el valor de la propiedad snumanx.
     * 
     */
    public int getSnumanx() {
        return snumanx;
    }

    /**
     * Define el valor de la propiedad snumanx.
     * 
     */
    public void setSnumanx(int value) {
        this.snumanx = value;
    }

    /**
     * Obtiene el valor de la propiedad snumfol.
     * 
     */
    public int getSnumfol() {
        return snumfol;
    }

    /**
     * Define el valor de la propiedad snumfol.
     * 
     */
    public void setSnumfol(int value) {
        this.snumfol = value;
    }

    /**
     * Obtiene el valor de la propiedad vurldocanx.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVurldocanx() {
        return vurldocanx;
    }

    /**
     * Define el valor de la propiedad vurldocanx.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVurldocanx(String value) {
        this.vurldocanx = value;
    }

    /**
     * Obtiene el valor de la propiedad bpdfdoc.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getBpdfdoc() {
        return bpdfdoc;
    }

    /**
     * Define el valor de la propiedad bpdfdoc.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setBpdfdoc(byte[] value) {
        this.bpdfdoc = value;
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

    /**
     * Gets the value of the lstanexos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the lstanexos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLstanexos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DocumentoAnexo }
     * 
     * 
     */
    public List<DocumentoAnexo> getLstanexos() {
        if (lstanexos == null) {
            lstanexos = new ArrayList<DocumentoAnexo>();
        }
        return this.lstanexos;
    }

    /**
     * Obtiene el valor de la propiedad ctipdociderem.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCtipdociderem() {
        return ctipdociderem;
    }

    /**
     * Define el valor de la propiedad ctipdociderem.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCtipdociderem(String value) {
        this.ctipdociderem = value;
    }

    /**
     * Obtiene el valor de la propiedad vnumdociderem.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVnumdociderem() {
        return vnumdociderem;
    }

    /**
     * Define el valor de la propiedad vnumdociderem.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVnumdociderem(String value) {
        this.vnumdociderem = value;
    }

}
