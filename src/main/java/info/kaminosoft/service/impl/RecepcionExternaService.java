package info.kaminosoft.service.impl;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import info.kaminosoft.bean.JIORecepcion;
import info.kaminosoft.dao.IRecepcionDao;
import info.kaminosoft.service.IRecepcionExternaService;
import info.kaminosoft.service.exceptions.ErrorCargoResponse;
import info.kaminosoft.service.exceptions.ErrorChangeStateRecepcion;



@Service("iRecepcionExternaService")
public class RecepcionExternaService implements IRecepcionExternaService {
    //private static Logger depurador = Logger.getLogger(RecepcionExternaService.class.getName());

    @Autowired
	IRecepcionDao iRecepcionDao;
    
    @Override
    public JIORecepcion getDespachoByNumRegStd(String vnumregstd)throws Exception{
		
		try{
			
			JIORecepcion result=iRecepcionDao.getRecepcionWithPDFByNumRegStd(vnumregstd);
			return result;
			
		}
		catch(EmptyResultDataAccessException e){
			return null;
		}
		
	}
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updEstadoRecepccion(String vnumregstd,String cflgest) throws Exception {
		
		int row=iRecepcionDao.updEstadoRecepcionByNumRegStd(vnumregstd, cflgest);
		if(row==0){
            throw new ErrorChangeStateRecepcion("Error al actualizar la recepción: no se encontró el registro");
        }
        else if(row>1){
            throw new ErrorChangeStateRecepcion("Error al actualizar la recepción: se encontraron más de un registro");
        }
		
	}

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void insCargo(JIORecepcion rec) throws ErrorCargoResponse  {

        int row=iRecepcionDao.updRecepcion(rec);//actualiza, pero no modifica estado
        if(row==0){
            throw new ErrorCargoResponse("Error al actualizar la recepción: no se encontró el registro");
        }
        else if(row>1){
            throw new ErrorCargoResponse("Error al actualizar la recepción: se encontraron más de un registro");
        }
    }
   
    public static void main(String[] args)  {
		try {
		
	      
		} catch (Exception e) {
			//depurador.error(null,e);
		}
	}

}
