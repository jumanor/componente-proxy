package info.kaminosoft.bean;

import java.time.ZonedDateTime;

public class JIORecepcion {
    
    private String vnumregstd;
    private String vanioregstd;
    private String vuniorgstd;
    private String ccoduniorgstd;
    private String vusuregstd;
    private byte[] bcarstd;
    private String vobs;
    private String cflgest;
	private ZonedDateTime dfecregstd;


	public String getCcoduniorgstd() {
		return ccoduniorgstd;
	}

	public void setCcoduniorgstd(String ccoduniorgstd) {
		this.ccoduniorgstd = ccoduniorgstd;
	}

    /* 
	public int getSidrecext() {
		return sidrecext;
	}

	public void setSidrecext(int sidrecext) {
		this.sidrecext = sidrecext;
	}
    */

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

	public String getVuniorgstd() {
		return vuniorgstd;
	}

	public void setVuniorgstd(String vuniorgstd) {
		this.vuniorgstd = vuniorgstd;
	}

	public byte[] getBcarstd() {
		return bcarstd;
	}

	public void setBcarstd(byte[] bcarstd) {
		this.bcarstd = bcarstd;
	}

	public String getVusuregstd() {
		return vusuregstd;
	}

	public void setVusuregstd(String vusuregstd) {
		this.vusuregstd = vusuregstd;
	}

	public String getVobs() {
		return vobs;
	}

	public void setVobs(String vobs) {
		this.vobs = vobs;
	}

	public String getCflgest() {
		return cflgest;
	}

	public void setCflgest(String cflgest) {
		this.cflgest = cflgest;
	}

	public ZonedDateTime getDfecregstd() {
		return dfecregstd;
	}

	public void setDfecregstd(ZonedDateTime dfecregstd) {
		this.dfecregstd = dfecregstd;
	}

	
}
