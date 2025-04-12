package info.kaminosoft.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class JSAutenticacion {
    
	private String userAccessApi;

	public String getUserAccessApi() {
		return userAccessApi;
	}

	public void setUserAccessApi(String userAccessApi) {
		this.userAccessApi = userAccessApi;
	}

	
}
