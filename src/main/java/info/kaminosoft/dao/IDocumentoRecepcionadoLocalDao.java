package info.kaminosoft.dao;

import info.kaminosoft.bean.JIODocumentoRecepcionado;

public interface IDocumentoRecepcionadoLocalDao {
	JIODocumentoRecepcionado getDocumentoRecepcionado(String vnumregstd) throws Exception;
}
