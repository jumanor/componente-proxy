package info.kaminosoft.dao.impl;

import java.sql.Timestamp;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import info.kaminosoft.bean.JIODocumentoExterno;
import info.kaminosoft.dao.IDocumentoExternoDao;

@Repository("iDocumentoExterno")
public class DocumentoExterno extends JdbcTemplate implements IDocumentoExternoDao{

     @Autowired
     DocumentoExterno(DataSource dataSource) {
        super.setDataSource(dataSource);
    }

	@Override
	public int insDocumentoExterno(JIODocumentoExterno documentoExterno) throws Exception {
		
		logger.info("Parámetros entrada insDocumentoExterno(): [" +
		"sidemiext=" + documentoExterno.getSidemiext() + ", " +
		"vnomentemi=" + documentoExterno.getVnomentemi() + ", " +
		"ccodtipdoc=" + documentoExterno.getCcodtipdoc() + ", " +
		"vnumdoc=" + documentoExterno.getVnumdoc() + ", " +
		"dfecdoc=" + documentoExterno.getDfecdoc() + ", " +
		"vuniorgdst=" + documentoExterno.getVuniorgdst() + ", " +
		"vnomdst=" + documentoExterno.getVnomdst() + ", " +
		"vnomcardst=" + documentoExterno.getVnomcardst() + ", " +
		"vasu=" + documentoExterno.getVasu() + ", " +
		"snumanx=" + documentoExterno.getSnumanx() + ", " +
		"snumfol=" + documentoExterno.getSnumfol() + ", " +
		"vurldocanx=" + documentoExterno.getVurldocanx() + "]"
		);
		
		StringBuilder sql = new StringBuilder();
		sql.append(" INSERT INTO esq_iotramite.IOTDTM_DOC_EXTERNO(").
		append(" siddocext, sidemiext, vnomentemi, ccodtipdoc, vnumdoc,").
		append(" dfecdoc, vuniorgdst, vnomdst, vnomcardst,").
		append(" vasu, snumanx, snumfol, vurldocanx)").
		append(" VALUES(").
		append(" nextval('esq_iotramite.NU_INT_DOC_EXT'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) returning siddocext");
		
		Timestamp dfecdoc = Timestamp.from(documentoExterno.getDfecdoc().toInstant());

		Integer id_column = 0;
		
		id_column=queryForObject(sql.toString(), Integer.class,
				
			documentoExterno.getSidemiext(),
			documentoExterno.getVnomentemi(),
			documentoExterno.getCcodtipdoc(),
			documentoExterno.getVnumdoc(),
			dfecdoc,
			documentoExterno.getVuniorgdst(),
			documentoExterno.getVnomdst(),
			documentoExterno.getVnomcardst(),
			documentoExterno.getVasu(),
			documentoExterno.getSnumanx(),
			documentoExterno.getSnumfol(),
			documentoExterno.getVurldocanx()
		);

		
			
		return id_column.intValue();
	}
    
	@Override
	public JIODocumentoExterno getDocumentoExternoByIdemiext(long sidemiext) throws Exception {
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT siddocext ")
		.append("FROM esq_iotramite.IOTDTM_DOC_EXTERNO ")
		.append("WHERE sidemiext = ?");

		return queryForObject(sql.toString(), 
				new BeanPropertyRowMapper<>(JIODocumentoExterno.class),
				sidemiext);
	}

	@Override
	public int removeDocumentoExterno(long siddocext) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM esq_iotramite.IOTDTM_DOC_EXTERNO ")
		.append("WHERE siddocext = ?");

		return update(sql.toString(), siddocext);
	}
    
}
