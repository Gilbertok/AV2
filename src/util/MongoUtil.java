package util;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import dao.TaxiCorrida;

public class MongoUtil {
	
	private static MongoCollection<Document> collection = null;
	private static MongoClient mongoClient;
	private static MongoDatabase banco = null;
	private MongoUtil() {}	
	
	private static void criaConexao() {
		mongoClient = new MongoClient("localhost" , 27017);
		banco = mongoClient.getDatabase("av2");
		collection = banco.getCollection("taxiCorrida");
	}
	
	private static void fecharConexao() {
		mongoClient.close();
	}
	
	public static void limparBanco() {
		criaConexao();
		banco.drop();
		fecharConexao();
	}
	
	public static void SalvarDados(List<TaxiCorrida> taxisLogs) {
		MongoUtil.criaConexao();
		for (TaxiCorrida taxi : taxisLogs) {
			Document corrida = new Document();
			corrida.append("codigoTaxi", taxi.getCodigoTaxi());
			corrida.append("dataCorrida", taxi.getDataCorrida());
			corrida.append("longitude", taxi.getLongitude());
			corrida.append("latitude", taxi.getLatitude());
			collection.insertOne(corrida);
		}
		fecharConexao();
	}
	
	public static List<TaxiCorrida> BuscarDados(Double latit, Double longit) {
		List<TaxiCorrida> dados = new ArrayList<TaxiCorrida>();
		MongoUtil.criaConexao();
		Document whereQuery = new Document().append("latitude", latit).append("longitude", longit);
		MongoCursor<Document> cursor = collection.find(whereQuery).iterator();
		while (cursor.hasNext()) {
			Document document = cursor.next();
			TaxiCorrida model = new TaxiCorrida(document.getInteger("codigoTaxi"), document.getDate("dataCorrida"), document.getDouble("longitude"), document.getDouble("latitude"));
			dados.add(model);
		}
		fecharConexao();
		return dados;
	}

}
