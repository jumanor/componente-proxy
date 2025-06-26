package info.kaminosoft.dao;

import info.kaminosoft.bean.JICargoRecepcion;
import info.kaminosoft.bean.JIORecepcion;

public interface IRecepcionDao {
    
    public int updRecepcion(JIORecepcion recepcion); 
    public String[] getCuoAndRucEntRemByNumRegStd(String numRegStd);
    public JIORecepcion getRecepcionWithPDFByNumRegStd(String vnumregstd)throws Exception;
    public int updEstadoRecepcionByNumRegStd(String vnumregstd,String cflgest) throws Exception;
    JICargoRecepcion getCargoByNumRegStd(String vnumregstd) throws Exception;
}
