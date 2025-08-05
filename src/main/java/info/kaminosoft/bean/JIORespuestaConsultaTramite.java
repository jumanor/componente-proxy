package info.kaminosoft.bean;


import javax.xml.datatype.XMLGregorianCalendar;

public class JIORespuestaConsultaTramite {
    
    private String vcodres;
    private String vdesres;
    private String vnumregstd;
    private String vanioregstd;
    private String vuniorgstd;
    private XMLGregorianCalendar dfecregstd;
    private String vusuregstd;
    private byte[] bcarstd;
    private String vobs;
    private String cflgest;

    public String getVcodres() {
        return vcodres;
    }

    public void setVcodres(String vcodres) {
        this.vcodres = vcodres;
    }

    public String getVdesres() {
        return vdesres;
    }

    public void setVdesres(String vdesres) {
        this.vdesres = vdesres;
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

    public String getVuniorgstd() {
        return vuniorgstd;
    }

    public void setVuniorgstd(String vuniorgstd) {
        this.vuniorgstd = vuniorgstd;
    }

    public XMLGregorianCalendar getDfecregstd() {
        return dfecregstd;
    }

    public void setDfecregstd(XMLGregorianCalendar dfecregstd) {
        this.dfecregstd = dfecregstd;
    }

    public String getVusuregstd() {
        return vusuregstd;
    }

    public void setVusuregstd(String vusuregstd) {
        this.vusuregstd = vusuregstd;
    }

    public byte[] getBcarstd() {
        return bcarstd;
    }

    public void setBcarstd(byte[] bcarstd) {
        this.bcarstd = bcarstd;
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
}
