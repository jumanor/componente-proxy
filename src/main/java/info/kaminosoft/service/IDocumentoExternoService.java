package info.kaminosoft.service;

import info.kaminosoft.bean.JIODocumentoExterno;

public interface IDocumentoExternoService {
    public void insDocumentoExterno(JIODocumentoExterno documentoExterno) throws Exception;
    public void removeDocumentoExternoByIdEmiExt(long sidemiext) throws Exception;
}
