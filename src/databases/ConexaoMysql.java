package databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMysql {
	
	private final String URL = "jdbc:mysql://localhost:3306/av2?useTimezone=true&serverTimezone=UTC";
	private final String USER = "root";
	private final String PASSWORD = "master";
	private final String TP_CONEXAO = "com.mysql.cj.jdbc.Driver";
	
	private static Connection instance;
	
	private ConexaoMysql() {}
	
	public static Connection getInstance() {
		if(instance == null) {
			instance = new ConexaoMysql().criaConexao();
		}
		return instance;
	}
	
	private Connection criaConexao() {
		try {
			Class.forName(TP_CONEXAO);
			instance = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
		}
		return instance;
	}
	
	public static void fechaConexao() {
		try {
			if (instance != null && (!instance.isClosed())) {
				try {
					instance.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void dropDatble(String table) {
		getInstance();
		try {
			instance.prepareStatement("delete from "+table).execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		fechaConexao();
	}
}
