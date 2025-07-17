package info.kaminosoft.service.exceptions;

public class ErrorWSConsultaTramiteResponse extends Exception{
	
	private String code;
	
	public ErrorWSConsultaTramiteResponse(String code,String message) {
		   super(message);
	       this.code=code;
	}
	
	public String getCode() {
		return code;
	}
}
