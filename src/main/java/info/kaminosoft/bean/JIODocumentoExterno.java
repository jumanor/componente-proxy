package info.kaminosoft.bean;

import java.time.ZonedDateTime;
import java.util.List;



public class JIODocumentoExterno {
    
    //private int siddocext;
    private int sidemiext;
    //private int sidrecext;
    private String vnomentemi;
    private String ccodtipdoc;
    private String vnumdoc;
    private ZonedDateTime dfecdoc;
    private String vuniorgdst;
    private String vnomdst;
    private String vnomcardst;
    private String vasu;
    private int snumanx;
    private Integer snumfol;
    private String vurldocanx;
    
    
    private JIODocumentoPrincipal documentoPrincipal;
    private List<JIODocumentoAnexo> lstDocAnexo;

    public JIODocumentoPrincipal getDocumentoPrincipal() {
        return documentoPrincipal;
    }

    public void setDocumentoPrincipal(JIODocumentoPrincipal documentoPrincipal) {
        this.documentoPrincipal = documentoPrincipal;
    }

    public List<JIODocumentoAnexo> getLstDocAnexo() {
        return lstDocAnexo;
    }

    public void setLstDocAnexo(List<JIODocumentoAnexo> lstDocAnexo) {
        this.lstDocAnexo = lstDocAnexo;
    }
    /* 
    public int getSiddocext() {
        return siddocext;
    }

    public void setSiddocext(int siddocext) {
        this.siddocext = siddocext;
    }
    */

    public int getSidemiext() {
        return sidemiext;
    }

    public void setSidemiext(int sidemiext) {
        this.sidemiext = sidemiext;
    }   

    public String getVnumdoc() {
        return vnumdoc;
    }

    public void setVnumdoc(String vnumdoc) {
        this.vnumdoc = vnumdoc;
    }

    public String getCcodtipdoc() {
        return ccodtipdoc;
    }

    public void setCcodtipdoc(String ccodtipdoc) {
        this.ccodtipdoc = ccodtipdoc;
    }

    public String getVnomentemi() {
        return vnomentemi;
    }

    public void setVnomentemi(String vnomentemi) {
        this.vnomentemi = vnomentemi;
    }

    public String getVuniorgdst() {
        return vuniorgdst;
    }

    public void setVuniorgdst(String vuniorgdst) {
        this.vuniorgdst = vuniorgdst;
    }

    public String getVasu() {
        return vasu;
    }

    public void setVasu(String vasu) {
        this.vasu = vasu;
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

    public ZonedDateTime getDfecdoc() {
        return dfecdoc;
    }

    public void setDfecdoc(ZonedDateTime dfecdoc) {
        this.dfecdoc = dfecdoc;
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
	
}
