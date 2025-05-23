package info.kaminosoft.service;

import info.kaminosoft.bean.JICargoDespacho;
import info.kaminosoft.bean.JIODocumentoDespachado;

public interface IDespachoLocalService {

	JIODocumentoDespachado getDocumentoDespachado(String vnumregstd) throws Exception;
	JICargoDespacho getCargoDespachado(String vnumregstd) throws Exception;
}
