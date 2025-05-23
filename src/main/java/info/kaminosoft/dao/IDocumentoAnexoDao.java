package info.kaminosoft.dao;

import java.util.List;

import info.kaminosoft.bean.JIODocumentoAnexo;

public interface IDocumentoAnexoDao {
    int insDocumentoAnexo(JIODocumentoAnexo documentoAnexo) throws Exception;
    int removeDocumentoAnexoByIddocext(long siddocext) throws Exception;
    List<JIODocumentoAnexo> getDocumentosAnexosByIddocext(long siddocext) throws Exception;
}
