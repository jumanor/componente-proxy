package info.kaminosoft.dao.impl;

import java.sql.ResultSet;

import java.sql.Timestamp;
import java.time.ZoneId;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.CannotAcquireLockException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Repository;


import info.kaminosoft.bean.JICargoRecepcion;
import info.kaminosoft.bean.JIORecepcion;
import info.kaminosoft.dao.IRecepcionDao;

import info.kaminosoft.dao.exceptions.ErrorSinRegistroRecepcion;
import info.kaminosoft.dao.exceptions.ErrorAdquirirBloqueoExclusivo;

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
		append(" vobs = ? ,").
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
			"vobs=" + (recepcion.getVobs() == null ? "null" : recepcion.getVobs().trim()) + ", "+
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
	/* 
	@Override
	public JIORecepcion getRecepcionWithPDFByNumRegStd(String vnumregstd) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT sidrecext, vnumregstd, vanioregstd, vuniorgstd, ccoduniorgstd, vusuregstd, ")
		.append("vobs, cflgest, dfecregstd, vcuo, vrucentrem, bcarstd ")
		.append("FROM esq_iotramite.IOTDTC_RECEPCION ")
		.append("WHERE vnumregstd = ?");

		return queryForObject(sql.toString(), 
				new BeanPropertyRowMapper<>(JIORecepcion.class),
				vnumregstd);

	}
	*/
	@Override
	public JIORecepcion getRecepcionWithPDFByNumRegStd(String vnumregstd) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT sidrecext, vnumregstd, vanioregstd, vuniorgstd, ccoduniorgstd, vusuregstd, ")
		.append("vobs, cflgest, dfecregstd, vcuo, vrucentrem, bcarstd ")
		.append("FROM esq_iotramite.IOTDTC_RECEPCION ")
		.append("WHERE vnumregstd = ?");
	
		return queryForObject(
			sql.toString(),
			(ResultSet rs, int rowNum) -> {
				JIORecepcion recepcion = new JIORecepcion();
	
				// Mapeo de campos String
				recepcion.setSidrecext(rs.getObject("sidrecext", Integer.class));
				recepcion.setVnumregstd(rs.getString("vnumregstd"));
				recepcion.setVanioregstd(rs.getString("vanioregstd"));
				recepcion.setVuniorgstd(rs.getString("vuniorgstd"));
				recepcion.setCcoduniorgstd(rs.getString("ccoduniorgstd"));
				recepcion.setVusuregstd(rs.getString("vusuregstd"));
				recepcion.setVobs(rs.getString("vobs"));
				recepcion.setCflgest(rs.getString("cflgest"));
				recepcion.setVcuo(rs.getString("vcuo"));
				recepcion.setVrucentrem(rs.getString("vrucentrem"));
	
				// Mapeo de campo binario (BLOB)
				recepcion.setBcarstd(rs.getBytes("bcarstd")); // Asumiendo que bcarstd es BYTEA o similar
	
				// Mapeo de fecha con conversión a ZonedDateTime
				Timestamp timestamp = rs.getTimestamp("dfecregstd");
				if (timestamp != null) {
					recepcion.setDfecregstd(timestamp.toLocalDateTime().atZone(ZoneId.systemDefault()));
				} else {
					recepcion.setDfecregstd(null);
				}
	
				return recepcion;
			},
			vnumregstd
		);
	}
	
	@Override
	public int updEstadoRecepcionByNumRegStd(String vnumregstd,String cflgest) throws Exception {

		StringBuilder sql = new StringBuilder();
		sql.append(" update esq_iotramite.iotdtc_recepcion").
		append(" set cflgest = ?, ").
		append(" dfecmod = CASE WHEN ? IN ('R', 'O') THEN NOW() ELSE dfecmod END ").
		append(" where ").
		append(" vnumregstd = ? ");
			
		Integer flag = 0;
		flag=update(sql.toString(),
					cflgest,
					cflgest,
					vnumregstd
		);
		
		return flag;
	}

	@Override
	public JICargoRecepcion getCargoByNumRegStd(String vnumregstd) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT vnumregstd, vanioregstd, vuniorgstd, ccoduniorgstd, dfecregstd, ")
		.append("vusuregstd, bcarstd, cflgest, vobs, vcuo, vcuoref ")
		.append("FROM esq_iotramite.IOTDTC_RECEPCION ")
		.append("WHERE vnumregstd = ?");

		try{

			return queryForObject(sql.toString(), 
					new BeanPropertyRowMapper<>(JICargoRecepcion.class),
					vnumregstd);

		}catch(EmptyResultDataAccessException ex){
			throw new ErrorSinRegistroRecepcion("registro no encontrado");
		}
	}
	@Override
	public String lockRecepcionForUpdate(String vnumregstd) throws Exception{
		try{
			String sql = "SELECT cflgest FROM esq_iotramite.IOTDTC_RECEPCION WHERE vnumregstd = ? FOR UPDATE NOWAIT";
			return queryForObject(sql, String.class, vnumregstd);
		}catch(CannotAcquireLockException e){
			throw new ErrorAdquirirBloqueoExclusivo("El registro está siendo modificado por otro proceso");
		}
	}
}