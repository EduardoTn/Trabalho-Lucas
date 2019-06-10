package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
	private static final String DSN = "jdbc:mysql://localhost/loja?useTimezone=true&serverTimezone=UTC";
	private static final String USUARIO = "aluno";
	private static final String SENHA = "123456789";
	
	public Connection getConexao() {
		try {
			return DriverManager.getConnection(DSN, USUARIO, SENHA);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}