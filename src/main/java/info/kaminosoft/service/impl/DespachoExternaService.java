package info.kaminosoft.service.impl;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import info.kaminosoft.bean.JIODespacho;
import info.kaminosoft.dao.IDespachoDao;
import info.kaminosoft.service.IDespachoExternaService;
import info.kaminosoft.service.IDocumentoExternoService;
import info.kaminosoft.service.exceptions.ErrorChangeStateDespacho;
import info.kaminosoft.util.WSPide;

@Service("iDespachoExternaService")
public class DespachoExternaService implements IDespachoExternaService{
	private static Logger depurador = Logger.getLogger(DespachoExternaService.class.getName());
	

	@Autowired
	IDocumentoExternoService iDocumentoExternoService;

	@Autowired
	IDespachoDao iDespachoDao;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String insDespacho(JIODespacho despacho,String cflgest,String vnumregstdref) throws Exception {
		
		if(cflgest.equals("S")){//buscamos registro anterior para actualizar
			
			String[] resultado=iDespachoDao.getCuoAndEstadoByNumRegStd(vnumregstdref);
			String vcuo=resultado[0];
			String cflgesttmp=resultado[1];
			
			despacho.setVcuoref(vcuo);//actualizamos al registro actual
			
			if(cflgesttmp.equals("O")){
				int numRow=iDespachoDao.updEstadoDespachoByNumRegStd(vnumregstdref,"S");
				if(numRow==0){
					depurador.error("Error al actualizar el estado a Subsanado: no se encontró vnumregstdref :"+vnumregstdref);
					throw new ErrorChangeStateDespacho("Error al actualizar el estado a Subsanado: no se encontró vcuoref ");
				}	
			}
			else{
				depurador.error("Error al actualizar el estado a Subsanado: el estado es: "+cflgest+ "y debe ser: O");
				throw new ErrorChangeStateDespacho("Error al actualizar el estado a Subsanado: el estado actual debe ser OBSERVADO");
			}
			
		}

		int sidemiext = iDespachoDao.insDespacho(despacho);	
		despacho.getDocumentoExterno().setSidemiext(sidemiext);
		iDocumentoExternoService.insDocumentoExterno(despacho.getDocumentoExterno());

		return WSPide.wsRecepcionarTramiteResponse(despacho);
	}

	@Override
	public String getCuoByNumRegStd(String vnumregstd) throws Exception {
		return iDespachoDao.getCuoAndEstadoByNumRegStd(vnumregstd)[0];
	}
	
	

}
