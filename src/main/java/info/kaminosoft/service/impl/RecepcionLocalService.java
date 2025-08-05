package info.kaminosoft.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import info.kaminosoft.bean.JICargoRecepcion;
import info.kaminosoft.bean.JIODocumentoAnexo;
import info.kaminosoft.bean.JIODocumentoRecepcionado;
import info.kaminosoft.dao.IRecepcionDao;
import info.kaminosoft.dao.IDocumentoAnexoDao;
import info.kaminosoft.dao.IDocumentoRecepcionadoLocalDao;
import info.kaminosoft.service.IRecepcionLocalService;

@Service("iRecepcionLocalService")
public class RecepcionLocalService implements IRecepcionLocalService{
	
	@Autowired
	IDocumentoRecepcionadoLocalDao iDocumentoRecepcionadoLocalDao;

	@Autowired
	IDocumentoAnexoDao iDocumentoAnexoDao;
	
	@Autowired
	IRecepcionDao iRecepcionDao;
	
	@Override
	public JIODocumentoRecepcionado getDocumentoRecepcionado(String vnumregstd) throws Exception {
		
		JIODocumentoRecepcionado documentoRecepcionado=iDocumentoRecepcionadoLocalDao.getDocumentoRecepcionado(vnumregstd);
		
		List<JIODocumentoAnexo> anexos=iDocumentoAnexoDao.getDocumentosAnexosByIddocext(documentoRecepcionado.getSiddocext());

		if(anexos.size() > 0) {
			documentoRecepcionado.setLstDocAnexo(anexos);
		}

		return documentoRecepcionado;
	}

	@Override
	public JICargoRecepcion getCargoRecepcionado(String vnumregstd) throws Exception {
		return iRecepcionDao.getCargoByNumRegStd(vnumregstd);
	}

}
