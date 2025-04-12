package info.kaminosoft.dao.impl;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
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
        // TODO Auto-generated method stub
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
}
