package info.kaminosoft.dao.impl;

import javax.sql.DataSource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import info.kaminosoft.bean.JICargoDespacho;
import info.kaminosoft.bean.JIODespacho;
import info.kaminosoft.dao.IDespachoDao;
import info.kaminosoft.dao.exceptions.ErrorDuplicadoCuoDespacho;
import info.kaminosoft.dao.exceptions.ErrorDuplicadoCuoRefDespacho;
import info.kaminosoft.dao.exceptions.ErrorDuplicadoNumRegStdDespacho;
import info.kaminosoft.dao.exceptions.ErrorSinRegistroDespacho;

@Repository("iDespachoDao")
public class DespachoDao extends JdbcTemplate implements IDespachoDao {

    @Autowired
    DespachoDao(DataSource dataSource) {
        super.setDataSource(dataSource);
    }

    @Override
    public int insDespacho(JIODespacho despacho) throws Exception {
        
		logger.info("Parámetros Entrada insDespacho(): [" +
            "vnumregstd=" + despacho.getVnumregstd() + ", " +
            "vanioregstd=" + despacho.getVanioregstd() + ", " +
            "vrucentrec=" + despacho.getVrucentrec() + ", " +
            "vnomentrec=" + despacho.getVnomentrec() + ", " +
            "ctipdociderem=" + despacho.getCtipdociderem() + ", " +
            "vnumdociderem=" + despacho.getVnumdociderem() + ", " +
            "vcoduniorgrem=" + despacho.getVcoduniorgrem() + ", " +
            "vuniorgrem=" + despacho.getVuniorgrem() + ", " +
            "vusureg=" + despacho.getVusureg() + ", " +
            "vcuo=" + despacho.getVcuo() + ", " +
            "vcuoref=" + despacho.getVcuoref() + ", " +
            "cflgest=" + despacho.getCflgest() + "]"
        );

        Integer id_column = 0;
		
		final StringBuilder sql = new StringBuilder();
		sql.append(" INSERT INTO esq_iotramite.IOTDTC_DESPACHO( ").
		append(" sidemiext,vnumregstd, vanioregstd, vrucentrec, vnomentrec, ").
		append(" ctipdociderem, vnumdociderem, vcoduniorgrem, vuniorgrem, ").
		append(" vusureg, vcuo, vcuoref, cflgest) ").
		append(" VALUES( ").
		append(" nextval('esq_iotramite.nu_int_des_ext'),?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) returning sidemiext ");
		
		try{
			id_column=queryForObject(sql.toString(), Integer.class,
				
			
					despacho.getVnumregstd(),		//jumanor: numero de registro std
					despacho.getVanioregstd(),
					despacho.getVrucentrec(),		//jumanor: ruc entidad receptora
					despacho.getVnomentrec(),		//jumanor: nombre entidad receptora
					despacho.getCtipdociderem().charAt(0),	//jumanor: tipo doc ent del remitent 1:DNI 2:CE
					despacho.getVnumdociderem(), 	//jumanor: numero doc ent del remitente
					despacho.getVcoduniorgrem(), 	//jumanor: codigo unico organica del remitente
					despacho.getVuniorgrem(),		//jumanor: unid org remitente
					despacho.getVusureg(),			//jumanor: usuario de registro
					despacho.getVcuo(),				//jumanor: cuo
					despacho.getVcuoref(),			
					despacho.getCflgest().charAt(0)
			);
		}catch(DuplicateKeyException e){
			
			if(e.getMessage().contains("unique_vcuo_despacho")){
				throw new ErrorDuplicadoCuoDespacho("duplicado de CUO");
			}
			if(e.getMessage().contains("unique_vcuoref_despacho")){
				throw new ErrorDuplicadoCuoRefDespacho("duplicado de CUOREF");
			}
			if(e.getMessage().contains("unique_vnumregstd_despacho")){
				throw new ErrorDuplicadoNumRegStdDespacho("duplicado de NUMREGSTD");
			}
			throw e;
		}

		logger.info("Parámetros Salida insDespacho(): [" +
            " " + id_column + "]"
        );

        return id_column;
    }

	@Override
	public int updEstadoDespachoByNumRegStd(String vnumregstd,String cflgest) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" update esq_iotramite.iotdtc_despacho").
		append(" set cflgest = ? ").
		append(" where ").
		append(" vnumregstd = ? ");
			
		Integer flag = 0;
		flag=update(sql.toString(),
				
					cflgest,
					vnumregstd
		);
		
		return flag;
	}


	@Override
	public String[] getCuoAndEstadoByNumRegStd(String vnumregstd) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT vcuo,cflgest ")
		.append("FROM esq_iotramite.IOTDTC_DESPACHO ")
		.append("WHERE vnumregstd = ?");

		return queryForObject(sql.toString(), 
				(rs, rowNum) -> new String[]{
					rs.getString("vcuo"),
					rs.getString("cflgest")
				}, 
				vnumregstd
			);
	}

	@Override
	public JIODespacho getDespachoByNumRegStd(String vnumregstd) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT sidemiext, vcuo, cflgest, vcuoref, ")
		.append("vnumregstd, vrucentrec, vnomentrec ")
		.append("FROM esq_iotramite.IOTDTC_DESPACHO ")
		.append("WHERE vnumregstd = ?");

		return queryForObject(sql.toString(), 
				new BeanPropertyRowMapper<>(JIODespacho.class),
				vnumregstd);
	}
    
	@Override
	public int removeDespacho(long sidemiext) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM esq_iotramite.IOTDTC_DESPACHO ")
		.append("WHERE sidemiext = ?");

		return update(sql.toString(), sidemiext);
	}

	@Override
	public JICargoDespacho getCargoByNumRegStd(String vnumregstd) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT vnumregstdrec, vanioregstdrec, dfecregstdrec, vuniorgstdrec, vusuregstdrec, ")
		.append("bcarstdrec, cflgest, vobs ")
		.append("FROM esq_iotramite.IOTDTC_DESPACHO ")
		.append("WHERE vnumregstd = ?");

		try{

			return queryForObject(sql.toString(), 
					new BeanPropertyRowMapper<>(JICargoDespacho.class),
					vnumregstd);

		}catch(EmptyResultDataAccessException ex){
			throw new ErrorSinRegistroDespacho("registro no encontrado");
		}
	}
}
