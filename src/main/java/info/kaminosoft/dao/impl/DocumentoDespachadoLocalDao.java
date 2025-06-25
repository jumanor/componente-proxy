package info.kaminosoft.dao.impl;



import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import info.kaminosoft.bean.JIODocumentoDespachado;
import info.kaminosoft.dao.IDocumentoDespachadoLocalDao;
import info.kaminosoft.dao.exceptions.ErrorSinRegistroDespacho;

@Repository("iDocumentoDespachadoLocalDao")
public class DocumentoDespachadoLocalDao extends JdbcTemplate implements IDocumentoDespachadoLocalDao{
	
	 @Autowired
	 DocumentoDespachadoLocalDao(DataSource dataSource) {
	    super.setDataSource(dataSource);
	 }

	@Override
	public JIODocumentoDespachado getDocumentoDespachado(String vnumregstd) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT des.sidemiext, des.vnumregstd, des.vanioregstd, des.vrucentrec, ")
		.append("des.vnomentrec, des.ctipdociderem, des.vnumdociderem, ")
		.append("des.vcoduniorgrem, des.vuniorgrem, des.vusureg, ")
		.append("des.vcuo, des.vcuoref, des.cflgest, ")
		
		.append("de.siddocext, de.vnomentemi, de.ccodtipdoc, de.vnumdoc,de.dfecdoc, de.vuniorgdst, de.vnomdst, ")
		//.append("de.vnomentemi, de.ccodtipdoc, de.vnumdoc, de.vuniorgdst, de.vnomdst, ")
		.append("de.vnomcardst, de.vasu, de.snumanx,de.snumfol, de.vurldocanx, ")
		
		.append("pr.vnomdoc, pr.bpdfdoc ")
		
		.append("FROM esq_iotramite.IOTDTC_DESPACHO des ")
		.append("INNER JOIN esq_iotramite.IOTDTM_DOC_EXTERNO de ON des.sidemiext = de.sidemiext ")
		.append("INNER JOIN esq_iotramite.IOTDTD_DOC_PRINCIPAL pr ON de.siddocext = pr.siddocext ")
		.append("WHERE vnumregstd = ?");
		
		
		try {
			
			return queryForObject(sql.toString(), 
					
					new BeanPropertyRowMapper<>(JIODocumentoDespachado.class),
					vnumregstd);	
			
		}catch(EmptyResultDataAccessException ex) {
			
			throw new ErrorSinRegistroDespacho("registro no encontrado");
		}
		
		
	}
}
