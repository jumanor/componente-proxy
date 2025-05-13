package info.kaminosoft.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import info.kaminosoft.bean.JIODocumentoAnexo;
import info.kaminosoft.bean.JIODocumentoExterno;
import info.kaminosoft.dao.IDocumentoAnexoDao;
import info.kaminosoft.dao.IDocumentoExternoDao;
import info.kaminosoft.dao.IDocumentoPrincipalDao;
import info.kaminosoft.service.IDocumentoExternoService;

@Service("iDocumentoExternoService")
public class DocumentoExternoService implements IDocumentoExternoService {

    @Autowired
	IDocumentoExternoDao iDocumentoExternoDao;
    @Autowired
	IDocumentoPrincipalDao iDocumentoPrincipalDao;
	@Autowired
	IDocumentoAnexoDao iDocumentoAnexoDao;

	/**
	 * Metodo que inserta un documento externo y sus documentos anexos
	 * <b>
	 * En caso de error en las inserciones se lanzara una excepcion desde la capa dao
	 * @param documentoExterno JIODocumentoExterno
	 * @throws Exception
	 */
	@Override
	public void insDocumentoExterno(JIODocumentoExterno documentoExterno) throws Exception {
		
		int siddocext = iDocumentoExternoDao.insDocumentoExterno(documentoExterno);
		documentoExterno.getDocumentoPrincipal().setSiddocext(siddocext);
		iDocumentoPrincipalDao.insDocumentoPrincipal(documentoExterno.getDocumentoPrincipal());
		List<JIODocumentoAnexo> lista = documentoExterno.getLstDocAnexo();
		if(lista != null && lista.size() > 0) {
			// Se inserta cada uno de los documentos anexos
			for (JIODocumentoAnexo a : lista) {
				a.setSiddocext(siddocext);
				iDocumentoAnexoDao.insDocumentoAnexo(a);
			}
		}
		
	}
	@Override
	public void removeDocumentoExternoByIdEmiExt(long sidemiext) throws Exception {
			
		JIODocumentoExterno documentoExterno=iDocumentoExternoDao.getDocumentoExternoByIdemiext(sidemiext);
		long siddocext=documentoExterno.getSiddocext();
		
		iDocumentoPrincipalDao.removeDocumentoPrincipalByIddocext(siddocext);
		iDocumentoAnexoDao.removeDocumentoAnexoByIddocext(siddocext);
		iDocumentoExternoDao.removeDocumentoExterno(siddocext);
		
	}
}
