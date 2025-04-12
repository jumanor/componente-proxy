package info.kaminosoft.dao.impl;

import java.sql.Timestamp;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import info.kaminosoft.bean.JIORecepcion;
import info.kaminosoft.dao.IRecepcionDao;

@Repository("iRecepcionDao")
public class RecepcionDao extends JdbcTemplate implements IRecepcionDao{

    @Autowired
    RecepcionDao(DataSource dataSource) {
        super.setDataSource(dataSource);
    }

    @Override
    public int updRecepcion(JIORecepcion recepcion) {
        
        StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE esq_iotramite.IOTDTC_RECEPCION").
		//append(" set vnumregstd = ?,").
		append(" set vanioregstd = ?,").
		append(" vuniorgstd = ?,").
		append(" ccoduniorgstd = ?,").
		append(" dfecregstd = ?,").//Este campo lo actualizamos
		append(" vusuregstd = ?,").
		append(" bcarstd = ?,").
		append(" vobs = ?,").
		append(" cflgest = ?").
		//append(" cflgenvstdintergrt = ?,").
		//append(" vidusuregstdintergrt = ?").
		append(" WHERE").
		append(" vnumregstd = ?");//crear un index para este campo


		logger.info("Parámetros Entrada updRecepcion(): [" +
			"vnumregstd=" + recepcion.getVnumregstd() + ", " +
			"vanioregstd=" + recepcion.getVanioregstd() + ", " +
			"vuniorgstd=" + recepcion.getVuniorgstd() + ", " +
			"ccoduniorgstd=" + recepcion.getCcoduniorgstd() + ", " +
			"dfecregstd=" + recepcion.getDfecregstd() + ", " +
			"vusuregstd=" + recepcion.getVusuregstd() + ", " +
			"bcarstd=" + recepcion.getBcarstd() + ", " +
			"vobs=" + (recepcion.getVobs() == null ? "null" : recepcion.getVobs().trim()) + ", " +
			"cflgest=" + recepcion.getCflgest() + "]"
		);

		Timestamp dfecregstd = Timestamp.from(recepcion.getDfecregstd().toInstant());
		
		Integer flag = 0;
		flag=update(sql.toString(),
				
				//recepcion.getVnumregstd(),
				recepcion.getVanioregstd(),
				recepcion.getVuniorgstd(),
				recepcion.getCcoduniorgstd(),
				dfecregstd,
				recepcion.getVusuregstd(),
				recepcion.getBcarstd(),
				recepcion.getVobs()==null?null:recepcion.getVobs().trim(),
				recepcion.getCflgest(),
				//cflgenvstdintergrt,
				//vidusuregstdintergrt,
				recepcion.getVnumregstd()
		);
		
		return flag;//numero de filas afectadas
    }

	@Override
	public String[] getCuoAndRucEntRemByNumRegStd(String numRegStd) {
		
		StringBuilder sql = new StringBuilder();
    	sql.append("SELECT vcuo,vrucentrem ")
       .append("FROM esq_iotramite.IOTDTC_RECEPCION ")
       .append("WHERE vnumregstd = ?");

	   return queryForObject(sql.toString(), 
        (rs, rowNum) -> new String[]{
            rs.getString("vcuo"),
            rs.getString("vrucentrem")
        }, 
        numRegStd
    );
	}
    
}
