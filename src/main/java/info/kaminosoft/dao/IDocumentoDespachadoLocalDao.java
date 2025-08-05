package info.kaminosoft.dao;

import info.kaminosoft.bean.JIODocumentoDespachado;

public interface IDocumentoDespachadoLocalDao {
	JIODocumentoDespachado getDocumentoDespachado(String vnumregstd) throws Exception;
}
