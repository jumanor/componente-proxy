package info.kaminosoft.service;

import info.kaminosoft.bean.JIODespacho;

public interface IDespachoExternaService {
	
	void removeDespacho(String vnumregstd) throws Exception;
	JIODespacho getDespachoByNumRegStd(String vnumregstd)throws Exception;
	void updEstadoDespacho(String vnumregstd,String cflgest,String vnumregstdref) throws Exception;
	JIODespacho insDespacho(JIODespacho despacho,String vnumregstdref) throws Exception;
}
