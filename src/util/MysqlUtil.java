package util;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import dao.TaxiCorrida;
import databases.ConexaoMysql;

public class MysqlUtil {
	
	private static EntityManager entityManager = null;
	private static EntityTransaction entityTransaction = null;
	
	private MysqlUtil() {}
	
	private static void criaConexao() {
		if (entityManager == null || (!entityManager.isOpen())) {
			entityManager = JPAUtil.getEntityManager();
			entityTransaction = entityManager.getTransaction();
		}
	}
	
	public static void SalvarDados(List<TaxiCorrida> taxisLogs) {
		MysqlUtil.criaConexao();
		for (TaxiCorrida taxi : taxisLogs) {
			entityTransaction.begin();
			entityManager.persist(new TaxiCorrida(taxi.getCodigoTaxi(), taxi.getDataCorrida(), taxi.getLatitude(), taxi.getLongitude()));
			entityTransaction.commit();
		}
		entityManager.close();
	}
	
	@SuppressWarnings("unchecked")
	public static List<TaxiCorrida> BuscarDados(Double latitude, Double longitude) {
		MysqlUtil.criaConexao();
		String sql = "select * from TaxiCorrida where latitude = :latitude and longitude = :longitude";
		Query sqlQuery = entityManager.createNativeQuery(sql, TaxiCorrida.class);
		sqlQuery.setParameter("latitude", latitude);
		sqlQuery.setParameter("longitude", longitude);
		List<TaxiCorrida> corridas = sqlQuery.getResultList();
		entityManager.close();
		return corridas;
	}

	public static void limparBanco() {
		ConexaoMysql.dropDatble("TaxiCorrida");
	}

}
