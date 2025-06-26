package info.kaminosoft.service;

import info.kaminosoft.bean.JICargoRecepcion;
import info.kaminosoft.bean.JIODocumentoRecepcionado;

public interface IRecepcionLocalService {
	
	JIODocumentoRecepcionado getDocumentoRecepcionado(String vnumregstd) throws Exception;
	JICargoRecepcion getCargoRecepcionado(String vnumregstd) throws Exception;
}
