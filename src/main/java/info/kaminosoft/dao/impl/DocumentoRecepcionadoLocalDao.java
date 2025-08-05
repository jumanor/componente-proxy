package info.kaminosoft.dao.impl;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import info.kaminosoft.bean.JIODocumentoRecepcionado;
import info.kaminosoft.dao.IDocumentoRecepcionadoLocalDao;
import info.kaminosoft.dao.exceptions.ErrorSinRegistroRecepcion;

@Repository("iDocumentoRecepcionadoLocalDao")
public class DocumentoRecepcionadoLocalDao extends JdbcTemplate implements IDocumentoRecepcionadoLocalDao{
	
	@Autowired
	DocumentoRecepcionadoLocalDao(DataSource dataSource) {
	super.setDataSource(dataSource);
	}

	@Override
	public JIODocumentoRecepcionado getDocumentoRecepcionado(String vnumregstd) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT des.sidrecext, des.vnumregstd, vrucentrem, ")
		.append("des.vuniorgrem, des.ctipdociderem, des.vnumdociderem, ")
		.append("des.vrucentrem, des.vuniorgrem, des.ctipdociderem, des.vnumdociderem, ")
		.append("des.vcuo, des.vcuoref, des.cflgest, ")
		
		
		.append("de.siddocext, de.vnomentemi, de.ccodtipdoc, de.vnumdoc,de.dfecdoc, de.vuniorgdst, de.vnomdst, ")
		.append("de.vnomcardst, de.vasu, de.snumanx,de.snumfol, de.vurldocanx, ")
		
		.append("pr.vnomdoc, pr.bpdfdoc ")
		
		.append("FROM esq_iotramite.IOTDTC_RECEPCION des ")
		.append("INNER JOIN esq_iotramite.IOTDTM_DOC_EXTERNO de ON des.sidrecext = de.sidrecext ")
		.append("INNER JOIN esq_iotramite.IOTDTD_DOC_PRINCIPAL pr ON de.siddocext = pr.siddocext ")
		.append("WHERE vnumregstd = ?");
		
		
		try {
			
			return queryForObject(sql.toString(), 
					
					new BeanPropertyRowMapper<>(JIODocumentoRecepcionado.class),
					vnumregstd);	
			
		}catch(EmptyResultDataAccessException ex) {
			
			throw new ErrorSinRegistroRecepcion("registro no encontrado");
		}
	}

}
