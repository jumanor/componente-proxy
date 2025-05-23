package info.kaminosoft.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import info.kaminosoft.bean.JICargoDespacho;
import info.kaminosoft.bean.JIODocumentoAnexo;
import info.kaminosoft.bean.JIODocumentoDespachado;
import info.kaminosoft.dao.IDespachoDao;
import info.kaminosoft.dao.IDocumentoAnexoDao;
import info.kaminosoft.dao.IDocumentoDespachadoLocalDao;
import info.kaminosoft.service.IDespachoLocalService;


@Service("iDespachoLocalService")
public class DespachoLocalService implements IDespachoLocalService{
	
	@Autowired
	IDocumentoDespachadoLocalDao iDocumentoDespachadoLocalDao;

	@Autowired
	IDocumentoAnexoDao iDocumentoAnexoDao;

	@Autowired
	IDespachoDao iDespachoDao;
	
	@Override
	public JIODocumentoDespachado getDocumentoDespachado(String vnumregstd) throws Exception {
		
		JIODocumentoDespachado documentoDespachado=iDocumentoDespachadoLocalDao.getDocumentoDespachado(vnumregstd);
		
		List<JIODocumentoAnexo> anexos=iDocumentoAnexoDao.getDocumentosAnexosByIddocext(documentoDespachado.getSidemiext());

		if(anexos.size() > 0) {
			documentoDespachado.setLstDocAnexo(anexos);
		}

		return documentoDespachado;
	}

	@Override
	public JICargoDespacho getCargoDespachado(String vnumregstd) throws Exception {
		return iDespachoDao.getCargoByNumRegStd(vnumregstd);
	}

}
