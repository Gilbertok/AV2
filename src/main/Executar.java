package main;

import java.util.List;

import dao.TaxiCorrida;
import util.Cronometro;
import util.FileReadTaxiLogs;
import util.MongoUtil;
import util.MysqlUtil;

public class Executar {

	public static void main(String[] args) {
		Double latitude = 116.48658;
		Double longitude = 39.88153;
		List<TaxiCorrida> taxisLogs = FileReadTaxiLogs.getRegistrosLogs();
		gravaArquivosMysql(taxisLogs);
		gravaArquivosMongo(taxisLogs);
		getFromGeolocalizacaoMySql(latitude, longitude);
		getFromGeolocalizacaoMongo(latitude, longitude);
		dropDatabases();
	}
	
	private static void dropDatabases() {
		MongoUtil.limparBanco();
		MysqlUtil.limparBanco();
	}

	/* Select para buscar a maior seguencia de latitudes iguais
	 * select 
			taxi.latitude,
			taxi.longitude,
			(select count(*) from taxicorrida sub where sub.latitude = taxi.latitude 
				and sub.longitude = taxi.longitude) qtde
		from taxicorrida taxi
		group by 
			taxi.latitude,
			taxi.longitude
		order by 3 desc
	 */

	private static void gravaArquivosMysql(List<TaxiCorrida> taxisLogs) {
		System.out.println("Registros: "+taxisLogs.size());
		System.out.println("Gravando Dados no MySQL");
		Cronometro.start();
		MysqlUtil.SalvarDados(taxisLogs);
		Cronometro.stop();
		System.out.println(Cronometro.elapsedTimeInMiliseconds()+ " ms para gravar no MySQL");
	}
	
	private static void getFromGeolocalizacaoMySql(Double latitude, Double longitude) {
		System.out.println("Buscando Dados no MySQL");
		Cronometro.start();
		System.out.println("Resultado: "+MysqlUtil.BuscarDados(latitude, longitude).size());
		Cronometro.stop();
		System.out.println(Cronometro.elapsedTimeInMiliseconds()+ " ms para ler no MySQL");
	}
	
	private static void gravaArquivosMongo(List<TaxiCorrida> taxisLogs) {
		System.out.println("Registros: "+taxisLogs.size());
		Cronometro.start();
		MongoUtil.SalvarDados(taxisLogs);
		Cronometro.stop();
		System.out.println(Cronometro.elapsedTimeInMiliseconds()+ " ms para gravar no MongoDB");
	}
	
	private static void getFromGeolocalizacaoMongo(Double latitude, Double longitude) {
		System.out.println("Buscando Dados no MongoDB");
		Cronometro.start();
		System.out.println("Resultado: "+MongoUtil.BuscarDados(latitude, longitude).size());
		Cronometro.stop();
		System.out.println(Cronometro.elapsedTimeInMiliseconds()+ " ms para ler no MongoDB");
	}

}
