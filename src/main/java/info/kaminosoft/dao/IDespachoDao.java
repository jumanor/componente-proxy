package info.kaminosoft.dao;

import info.kaminosoft.bean.JIODespacho;

public interface IDespachoDao {
    
    int insDespacho(JIODespacho despacho) throws Exception;
    int updEstadoDespachoByNumRegStd(String vnumregstd,String cflgest) throws Exception;
    String[] getCuoAndEstadoByNumRegStd(String vnumregstd) throws Exception;
}
