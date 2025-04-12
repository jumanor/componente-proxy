package info.kaminosoft.service;

import info.kaminosoft.bean.JIODespacho;

public interface IDespachoExternaService {
	
	String insDespacho(JIODespacho despacho,String cflgest,String vnumregstdref) throws Exception;
	String getCuoByNumRegStd(String vnumregstd) throws Exception;
}
