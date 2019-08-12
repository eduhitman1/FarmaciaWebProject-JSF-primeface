package br.com.farmacia.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {
//	private static final String USUARIO = "postgres";
//	private static final String SENHA = "eduhit00";
//	private static final String URL = "jdbc:postgresql://localhost:5432/farmaciaweb";
	private final static String driver = "org.postgresql.Driver";

	public static Connection conectar() throws SQLException {
		System.setProperty("jdbc.Drivers",driver);
		try{
	        Class.forName("org.postgresql.Driver");
	        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/farmaciaweb","postgres","eduhit00");
	    }catch(Exception ex){
	    throw new RuntimeException("Erro: "+ex);    
	    }
	//	Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
	//	return conexao;
	}

	public static void main(String[] args) throws SQLException {
		try {	
			Connection conexao = ConexaoFactory.conectar();
			System.out.println("Conectado com sucesso!");

		} catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println("Conex�o falhou!");
		}
	}


}
