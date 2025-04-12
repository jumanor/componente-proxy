package info.kaminosoft.dao;

import info.kaminosoft.bean.JIORecepcion;

public interface IRecepcionDao {
    
    public int updRecepcion(JIORecepcion recepcion); 
    public String[] getCuoAndRucEntRemByNumRegStd(String numRegStd);
}
