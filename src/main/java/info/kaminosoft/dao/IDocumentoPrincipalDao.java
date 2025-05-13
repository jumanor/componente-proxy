package info.kaminosoft.dao;

import info.kaminosoft.bean.JIODocumentoPrincipal;

public interface IDocumentoPrincipalDao {

    public int insDocumentoPrincipal(JIODocumentoPrincipal documentoPrincipal) throws Exception;
    public int removeDocumentoPrincipalByIddocext(long siddocext) throws Exception;
    
}
