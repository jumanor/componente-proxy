package info.kaminosoft.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import info.kaminosoft.bean.JIODocumentoAnexo;

import info.kaminosoft.dao.IDocumentoAnexoDao;

@Repository("iDocumentoAnexo")
public class DocumentoAnexoDao extends JdbcTemplate implements IDocumentoAnexoDao{
     @Autowired
     DocumentoAnexoDao(DataSource dataSource) {
        super.setDataSource(dataSource);
    }

    @Override
    public int insDocumentoAnexo(JIODocumentoAnexo documentoAnexo) throws Exception {
        
        StringBuilder sql = new StringBuilder();
		sql.append(" INSERT INTO esq_iotramite.IOTDTD_ANEXO(").
		append(" siddocanx, siddocext, vnomdoc)").
		append(" VALUES(").
		append(" nextval('esq_iotramite.NU_INT_ANX'), ?, ? ) returning siddocanx");
		
		Integer id_column = 0;
		
		id_column=queryForObject(sql.toString(), Integer.class,
				
				documentoAnexo.getSiddocext(),
				documentoAnexo.getVnomdoc()
		);
		
		return id_column;
    }

	@Override
	public int removeDocumentoAnexoByIddocext(long siddocext) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM esq_iotramite.IOTDTD_ANEXO ")
		.append("WHERE siddocext = ?");

		return update(sql.toString(), siddocext);
	}
	
	@Override
	public List<JIODocumentoAnexo> getDocumentosAnexosByIddocext(long siddocext) throws Exception {
        
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT vnomdoc ")
		.append("FROM esq_iotramite.IOTDTD_ANEXO ")
        .append("WHERE siddocext = ?");
		
		return query(sql.toString(), 
                new BeanPropertyRowMapper<>(JIODocumentoAnexo.class),
                siddocext);
		
    }
}
