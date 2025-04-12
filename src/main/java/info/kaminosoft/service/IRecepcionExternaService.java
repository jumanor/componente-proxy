package info.kaminosoft.service;

import info.kaminosoft.bean.JIORecepcion;
import info.kaminosoft.service.exceptions.ErrorCargoResponse;

public interface IRecepcionExternaService {
    public String insCargo(JIORecepcion cargo) throws ErrorCargoResponse;
}
