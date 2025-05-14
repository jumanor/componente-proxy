package info.kaminosoft.service;

import info.kaminosoft.bean.JIORecepcion;
import info.kaminosoft.service.exceptions.ErrorCargoResponse;

public interface IRecepcionExternaService {
    public void insCargo(JIORecepcion cargo) throws ErrorCargoResponse;
    public JIORecepcion getDespachoByNumRegStd(String vnumregstd)throws Exception;
    public void updEstadoRecepccion(String vnumregstd,String cflgest) throws Exception;
}
