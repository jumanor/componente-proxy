package info.kaminosoft.dao;

import info.kaminosoft.bean.JIODocumentoExterno;

public interface IDocumentoExternoDao {
    
    public int insDocumentoExterno(JIODocumentoExterno documentoExterno) throws Exception;
    public JIODocumentoExterno getDocumentoExternoByIdemiext(long sidemiext) throws Exception;
    public int removeDocumentoExterno(long siddocext) throws Exception;
}
