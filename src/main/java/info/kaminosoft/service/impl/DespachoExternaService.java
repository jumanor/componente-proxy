package info.kaminosoft.service.impl;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import info.kaminosoft.bean.JIODespacho;
import info.kaminosoft.dao.IDespachoDao;
import info.kaminosoft.service.IDespachoExternaService;
import info.kaminosoft.service.IDocumentoExternoService;
import info.kaminosoft.service.exceptions.ErrorChangeStateDespacho;
import info.kaminosoft.service.exceptions.ErrorChangeStateRecepcion;

@Service("iDespachoExternaService")
public class DespachoExternaService implements IDespachoExternaService{
	private static Logger depurador = Logger.getLogger(DespachoExternaService.class.getName());
	

	@Autowired
	IDocumentoExternoService iDocumentoExternoService;

	@Autowired
	IDespachoDao iDespachoDao;
	
	@Override
	public JIODespacho getDespachoByNumRegStd(String vnumregstd)throws Exception{
		
		try{
			JIODespacho result=iDespachoDao.getDespachoByNumRegStd(vnumregstd);
			
			return result;
			
		}
		catch(EmptyResultDataAccessException e){
			return null;
		}
		
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updEstadoDespacho(String vnumregstd,String cflgest,String vnumregstdref) throws Exception {
		
		int row=iDespachoDao.updEstadoDespachoByNumRegStd(vnumregstd, cflgest);
		if(row==0){
            throw new ErrorChangeStateDespacho("Error al actualizar el despacho: no se encontró el registro");
        }
        else if(row>1){
            throw new ErrorChangeStateDespacho("Error al actualizar el despacho: se encontraron más de un registro");
        }
		if(vnumregstdref!=null){
			JIODespacho resultado_ref=iDespachoDao.getDespachoByNumRegStd(vnumregstdref);
			String vcuo_ref=resultado_ref.getVcuo();
			
			String cflgest_ref=resultado_ref.getCflgest();
			if(cflgest_ref.equals("O")){
				int numRow=iDespachoDao.updEstadoDespachoByNumRegStd(vnumregstdref,"S");
				if(numRow==0){
					depurador.error("Error al actualizar el estado a Subsanado: no se encontró vnumregstdref :"+vnumregstdref+" vcuo: "+vcuo_ref);
					throw new ErrorChangeStateDespacho("Error al actualizar el estado a Subsanado: no se encontró "+ 
					"el documento vnumregstdref : "+vnumregstdref);
				}	
			}
			else{
				depurador.error("Error al actualizar vnumregstdref : "+vnumregstdref+" vcuo: "+vcuo_ref+" al estado a Subsanado: el estado es: "+cflgest_ref+ "y debe ser: O");
				throw new ErrorChangeStateDespacho("Error al actualizar el estado a Subsanado: el estado actual del documento "+
				"vnumregstdref : "+vnumregstdref+" referenciado debe ser OBSERVADO");
			}
		}
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void removeDespacho(String vnumregstd) throws Exception{
		
		JIODespacho despacho=iDespachoDao.getDespachoByNumRegStd(vnumregstd);
		long sidemiext=despacho.getSidemiext();
		iDocumentoExternoService.removeDocumentoExternoByIdEmiExt(sidemiext);
		iDespachoDao.removeDespacho(sidemiext);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public JIODespacho insDespacho(JIODespacho despacho,String vnumregstdref) throws Exception {
		
		if(vnumregstdref!=null){
			String[] resultado=iDespachoDao.getCuoAndEstadoByNumRegStd(vnumregstdref);
			String vcuo=resultado[0];

			despacho.setVcuoref(vcuo);//actualizamos al registro actual
		}
		
		int sidemiext = iDespachoDao.insDespacho(despacho);	
		despacho.getDocumentoExterno().setSidemiext(sidemiext);
		iDocumentoExternoService.insDocumentoExterno(despacho.getDocumentoExterno());

		return despacho;
	}

	@Override
	public String getCuoByNumRegStd(String vnumregstd) throws Exception {
		return iDespachoDao.getCuoAndEstadoByNumRegStd(vnumregstd)[0];
	}
	

}
