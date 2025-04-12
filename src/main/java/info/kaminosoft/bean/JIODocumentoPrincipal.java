package info.kaminosoft.bean;

public class JIODocumentoPrincipal {
 
    private int siddocext  ;
    private String vnomdoc;
    private byte[] bpdfdoc;

    public String getVnomdoc() {
        return vnomdoc;
    }

    public void setVnomdoc(String vnomdocpri) {
        this.vnomdoc = vnomdocpri;
    }

    public byte[] getBpdfdoc() {
        return bpdfdoc;
    }

    public void setBpdfdoc(byte[] bpdfdoc) {
        this.bpdfdoc = bpdfdoc;
    }

	public int getSiddocext() {
		return siddocext;
	}

	public void setSiddocext(int siddocext) {
		this.siddocext = siddocext;
	}

	
}
