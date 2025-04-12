package info.kaminosoft.bean;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class JSDocumentoAnexo {
    private String vnomdoc;

	public String getVnomdoc() {
		return vnomdoc;
	}

	public void setVnomdoc(String vnomdoc) {
		this.vnomdoc = vnomdoc;
	}
}
