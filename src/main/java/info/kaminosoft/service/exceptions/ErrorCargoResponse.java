package info.kaminosoft.service.exceptions;

public class ErrorCargoResponse  extends Exception {
	
	
	private String vdesres=null;
	
    public ErrorCargoResponse(String message) {
        super(message);
    }
    
    public ErrorCargoResponse(String message,String vdesres) {
        super(message);
        this.vdesres=vdesres.trim();
    }

	
	public String getVdesres() {
		return vdesres;
	}

}
