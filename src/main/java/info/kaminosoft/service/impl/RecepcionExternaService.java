package info.kaminosoft.service.impl;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import info.kaminosoft.bean.JIORecepcion;
import info.kaminosoft.dao.IRecepcionDao;
import info.kaminosoft.service.IRecepcionExternaService;
import info.kaminosoft.service.exceptions.ErrorCargoResponse;
import info.kaminosoft.util.WSPide;



@Service("iRecepcionExternaService")
public class RecepcionExternaService implements IRecepcionExternaService {
    //private static Logger depurador = Logger.getLogger(RecepcionExternaService.class.getName());

    @Autowired
	IRecepcionDao iRecepcionDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String insCargo(JIORecepcion rec) throws ErrorCargoResponse  {

        int row=iRecepcionDao.updRecepcion(rec);
        if(row==0){
            throw new ErrorCargoResponse("Error al actualizar la recepción: no se encontró el registro");
        }
        else if(row>1){
            throw new ErrorCargoResponse("Error al actualizar la recepción: se encontraron más de un registro");
        }
        String[] resultado=iRecepcionDao.getCuoAndRucEntRemByNumRegStd(rec.getVnumregstd());
        String vcuo = resultado[0];
        String vrucentrem = resultado[1];

        return WSPide.wsCargoResponse(rec,vcuo,vrucentrem);
    }    

    public static void main(String[] args)  {
		try {
		
	      
		} catch (Exception e) {
			//depurador.error(null,e);
		}
	}

}
