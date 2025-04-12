package info.kaminosoft.bean;

import java.util.List;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class JSDespacho {

    private String vnumregstd;
    private byte[] bpdfdoc;

    private String vnomentrec;
    private String vrucentrec;
    private String ccodtipdoc;
    private String vnumdoc;

    private String vuniorgrem;
    private String vcoduniorgrem;
    private String ctipdociderem;
    private String vnumdociderem;

    private String vuniorgdst;
    private String vnomdst;
    private String vnomcardst;
    private String vasu;
    private String vnomdoc;
    private int snumfol;
    private String vnumregstdref;

    //con anexos
    private List<JSDocumentoAnexo> lstanexos;
    private String vurldocanx;

    private String vusureg;
    
    //private String vrucentrem;
    //private String vnomentemi;
    //private String vcuo;        //no se usa
    //private String vcuoref;
    
    public String getVcoduniorgrem() {
        return vcoduniorgrem;
    }

    public void setVcoduniorgrem(String vcoduniorgrem) {
        this.vcoduniorgrem = vcoduniorgrem;
    }
    public String getVnomentrec() {
        return vnomentrec;
    }

    public void setVnomentrec(String vnomentrec) {
        this.vnomentrec = vnomentrec;
    }

    public String getVusureg() {
        return vusureg;
    }

    public void setVusureg(String vusureg) {
        this.vusureg = vusureg;
    }

    public String getVnumregstd() {
        return vnumregstd;
    }

    public void setVnumregstd(String vnumregstd) {
        this.vnumregstd = vnumregstd;
    }

    

    public String getVrucentrec() {
        return vrucentrec;
    }

    public void setVrucentrec(String vrucentrec) {
        this.vrucentrec = vrucentrec;
    }

   
    public String getVuniorgrem() {
        return vuniorgrem;
    }

    public void setVuniorgrem(String vuniorgrem) {
        this.vuniorgrem = vuniorgrem;
    }

   

    public String getCcodtipdoc() {
        return ccodtipdoc;
    }

    public void setCcodtipdoc(String ccodtipdoc) {
        this.ccodtipdoc = ccodtipdoc;
    }

    public String getVnumdoc() {
        return vnumdoc;
    }

    public void setVnumdoc(String vnumdoc) {
        this.vnumdoc = vnumdoc;
    }

    
    public String getVuniorgdst() {
        return vuniorgdst;
    }

    public void setVuniorgdst(String vuniorgdst) {
        this.vuniorgdst = vuniorgdst;
    }

    public String getVnomdst() {
        return vnomdst;
    }

    public void setVnomdst(String vnomdst) {
        this.vnomdst = vnomdst;
    }

    public String getVnomcardst() {
        return vnomcardst;
    }

    public void setVnomcardst(String vnomcardst) {
        this.vnomcardst = vnomcardst;
    }

    public String getVasu() {
        return vasu;
    }

    public void setVasu(String vasu) {
        this.vasu = vasu;
    }

    public String getVurldocanx() {
        return vurldocanx;
    }

    public void setVurldocanx(String vurldocanx) {
        this.vurldocanx = vurldocanx;
    }

    public byte[] getBpdfdoc() {
        return bpdfdoc;
    }

    public void setBpdfdoc(byte[] bpdfdoc) {
        this.bpdfdoc = bpdfdoc;
    }

    public String getVnomdoc() {
        return vnomdoc;
    }

    public void setVnomdoc(String vnomdoc) {
        this.vnomdoc = vnomdoc;
    }

    public int getSnumfol() {
        return snumfol;
    }

    public void setSnumfol(int snumfol) {
        this.snumfol = snumfol;
    }

    public List<JSDocumentoAnexo> getLstanexos() {
        return lstanexos;
    }

    public void setLstanexos(List<JSDocumentoAnexo> lstanexos) {
        this.lstanexos = lstanexos;
    }

    public String getCtipdociderem() {
        return ctipdociderem;
    }

    public void setCtipdociderem(String ctipdociderem) {
        this.ctipdociderem = ctipdociderem;
    }

    public String getVnumdociderem() {
        return vnumdociderem;
    }

    public void setVnumdociderem(String vnumdociderem) {
        this.vnumdociderem = vnumdociderem;
    }

	public String getVnumregstdref() {
		return vnumregstdref;
	}

	public void setVnumregstdref(String vnumregstdref) {
		this.vnumregstdref = vnumregstdref;
	}


	
}
