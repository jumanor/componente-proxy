package info.kaminosoft.service;

import info.kaminosoft.bean.JIORecepcion;

public interface IRecepcionExternaService {
    public void insCargo(JIORecepcion cargo) throws Exception;
    public JIORecepcion getDespachoByNumRegStd(String vnumregstd)throws Exception;
}
