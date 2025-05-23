package info.kaminosoft.dao;

import info.kaminosoft.bean.JICargoDespacho;
import info.kaminosoft.bean.JIODespacho;

public interface IDespachoDao {
    
    public int removeDespacho(long sidemiext) throws Exception;
    int insDespacho(JIODespacho despacho) throws Exception;
    int updEstadoDespachoByNumRegStd(String vnumregstd,String cflgest) throws Exception;
    String[] getCuoAndEstadoByNumRegStd(String vnumregstd) throws Exception;
    JIODespacho getDespachoByNumRegStd(String vnumregstd) throws Exception;
    JICargoDespacho getCargoByNumRegStd(String vnumregstd) throws Exception;
}
