	package info.kaminosoft.service.exceptions;

public class ErrorWSCargoResponse  extends Exception {
	
	private String code;
	
    public ErrorWSCargoResponse(String code,String message) {
        super(message);
        this.code=code;
    }

	public String getCode() {
		return code;
	}
}
