package info.kaminosoft.bean;


import java.util.Date;
import java.util.List;


public class JIODocumentoDespachado {
    
	private long siddocext;
	
    private String vnumregstd;
    private String vanioregstd;
    private String vrucentrec;
    private String vnomentrec;
    private String ctipdociderem;
    private String vnumdociderem; 
    private String vcoduniorgrem;
    private String vuniorgrem;
    private String vusureg;
    private String vcuo;
    private String vcuoref;
    private String cflgest;
    private long sidemiext;

    
    private String vnomentemi;
    private String ccodtipdoc;
    private String vnumdoc;
	private Date dfecdoc;
    private String vuniorgdst;
    private String vnomdst;
    private String vnomcardst;
    private String vasu;
    private int snumanx;
    private Integer snumfol;
    private String vurldocanx;

	private String vnomdoc;
    private byte[] bpdfdoc;
    
    private List<JIODocumentoAnexo> lstDocAnexo;

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

    public String getVnumregstd() {
        return vnumregstd;
    }

    public void setVnumregstd(String vnumregstd) {
        this.vnumregstd = vnumregstd;
    }

    public String getVanioregstd() {
        return vanioregstd;
    }

    public void setVanioregstd(String vanioregstd) {
        this.vanioregstd = vanioregstd;
    }

   

    
   

    public String getVcuo() {
        return vcuo;
    }

    public void setVcuo(String vcuo) {
        this.vcuo = vcuo;
    }

    public String getVcuoref() {
        return vcuoref;
    }

    public void setVcuoref(String vcuoref) {
        this.vcuoref = vcuoref;
    }

   

    public String getCflgest() {
        return cflgest;
    }

    public void setCflgest(String cflgest) {
        this.cflgest = cflgest;
    }

    
  

	public String getVnomentrec() {
		return vnomentrec;
	}

	public void setVnomentrec(String vnomentrec) {
		this.vnomentrec = vnomentrec;
	}

	public String getVcoduniorgrem() {
		return vcoduniorgrem;
	}

	public void setVcoduniorgrem(String vcoduniorgrem) {
		this.vcoduniorgrem = vcoduniorgrem;
	}

	public String getVusureg() {
		return vusureg;
	}

	public void setVusureg(String vusureg) {
		this.vusureg = vusureg;
	}

	public long getSidemiext() {
		return sidemiext;
	}

	public void setSidemiext(long sidemiext) {
		this.sidemiext = sidemiext;
	}

	public String getVnomentemi() {
		return vnomentemi;
	}

	public void setVnomentemi(String vnomentemi) {
		this.vnomentemi = vnomentemi;
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

	public Date getDfecdoc() {
		return dfecdoc;
	}

	public void setDfecdoc(Date dfecdoc) {
		this.dfecdoc = dfecdoc;
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

	public int getSnumanx() {
		return snumanx;
	}

	public void setSnumanx(int snumanx) {
		this.snumanx = snumanx;
	}

	public Integer getSnumfol() {
		return snumfol;
	}

	public void setSnumfol(Integer snumfol) {
		this.snumfol = snumfol;
	}

	public String getVurldocanx() {
		return vurldocanx;
	}

	public void setVurldocanx(String vurldocanx) {
		this.vurldocanx = vurldocanx;
	}

	public String getVnomdoc() {
		return vnomdoc;
	}

	public void setVnomdoc(String vnomdoc) {
		this.vnomdoc = vnomdoc;
	}

	public byte[] getBpdfdoc() {
		return bpdfdoc;
	}

	public void setBpdfdoc(byte[] bpdfdoc) {
		this.bpdfdoc = bpdfdoc;
	}

	public List<JIODocumentoAnexo> getLstDocAnexo() {
		return lstDocAnexo;
	}

	public void setLstDocAnexo(List<JIODocumentoAnexo> lstDocAnexo) {
		this.lstDocAnexo = lstDocAnexo;
	}

	public long getSiddocext() {
		return siddocext;
	}

	public void setSiddocext(long siddocext) {
		this.siddocext = siddocext;
	}
	
	//private DocumentoExterno documentoExterno;
}
