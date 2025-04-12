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

	@Override
	public int insDocumentoExterno(JIODocumentoExterno documentoExterno) throws Exception {
		int flagDocExt = 0;
   		
		int siddocext = 0;
		siddocext = iDocumentoExternoDao.insDocumentoExterno(documentoExterno);
    	if(siddocext != 0){
    		documentoExterno.getDocumentoPrincipal().setSiddocext(siddocext);
    		int flag = 0;
    		flag = iDocumentoPrincipalDao.insDocumentoPrincipal(documentoExterno.getDocumentoPrincipal());
    		if(flag == 1){
    			List<JIODocumentoAnexo> lista = documentoExterno.getLstDocAnexo();
    			if(lista != null){
	                for (JIODocumentoAnexo a : lista) {
	                    a.setSiddocext(siddocext);
	                    flag = iDocumentoAnexoDao.insDocumentoAnexo(a);
	                    if (flag == 1) {
	                    	flagDocExt = 1; 
	                    }
	                }
    			}
    		}
    	}
		
		return flagDocExt;
	}
    
}
