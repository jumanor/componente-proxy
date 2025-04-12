package info.kaminosoft.dao.impl;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import info.kaminosoft.bean.JIODocumentoPrincipal;
import info.kaminosoft.dao.IDocumentoPrincipalDao;

@Repository("iDocumentoPrincipal")
public class DocumentoPrincipalDao extends JdbcTemplate implements IDocumentoPrincipalDao{
    
     @Autowired
     DocumentoPrincipalDao(DataSource dataSource) {
        super.setDataSource(dataSource);
    }

    @Override
    public int insDocumentoPrincipal(JIODocumentoPrincipal documentoPrincipal) throws Exception {
        
        StringBuilder sql = new StringBuilder();
		sql.append(" INSERT INTO esq_iotramite.IOTDTD_DOC_PRINCIPAL(").
		append(" SIDDOCPRI, siddocext, vnomdoc, bpdfdoc)").
		append(" VALUES(").
		append(" nextval('esq_iotramite.NU_INT_DOC_PRI'), ?, ?, ?) returning SIDDOCPRI");
		
		//jdbcTemplate = new JdbcTemplate(dataSource);
		Integer id_column = 0;
		
		id_column=queryForObject(sql.toString(), Integer.class,
				
				documentoPrincipal.getSiddocext(),
				documentoPrincipal.getVnomdoc(),
				documentoPrincipal.getBpdfdoc()		//a nivel de base de datos not null
		);
			
		System.out.println("DOCPRINCIPAL SUCCESS ::"+id_column);
		return id_column;
    }
}
