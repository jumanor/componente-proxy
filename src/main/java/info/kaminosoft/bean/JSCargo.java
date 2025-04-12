package info.kaminosoft.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class JSCargo{
	private String vnumregstd;
	
	private String vuniorgstd;
	private String ccoduniorgstd;
	private String vusuregstd;
	private byte[] bcarstd;
	private String vobs;
	

	public String getVnumregstd() {
		return vnumregstd;
	}

	public void setVnumregstd(String vnumregstd) {
		this.vnumregstd = vnumregstd;
	}

	public String getVuniorgstd() {
		return vuniorgstd;
	}

	public void setVuniorgstd(String vuniorgstd) {
		this.vuniorgstd = vuniorgstd;
	}

	public String getCcoduniorgstd() {
		return ccoduniorgstd;
	}

	public void setCcoduniorgstd(String ccoduniorgstd) {
		this.ccoduniorgstd = ccoduniorgstd;
	}

	public String getVusuregstd() {
		return vusuregstd;
	}

	public void setVusuregstd(String vusuregstd) {
		this.vusuregstd = vusuregstd;
	}
	/* 
	public byte[] getBcarstd() {
		return bcarstd;
	}

	public void setBcarstd(byte[] bcarstd) {
		this.bcarstd = bcarstd;
	}
	*/
	public String getVobs() {
		return vobs;
	}

	public void setVobs(String vobs) {
		this.vobs = vobs;
	}

	public byte[] getBcarstd() {
		return bcarstd;
	}

	public void setBcarstd(byte[] bcarstd) {
		this.bcarstd = bcarstd;
	}
}