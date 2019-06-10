package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import Conexao.Conexao;
import Model.*;
public class FuncionarioDAO {
	private Connection conexao;
    private PreparedStatement stmt;
    public FuncionarioDAO() {
        this.conexao = new Conexao().getConexao();
    }
    
    public void CadFunc(Funcionario usuario) {
        String sql = "INSERT INTO funcionario (Usuario_idUsuario) VALUES (?)";
        try {
            stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, usuario.getIdUsuario());
            stmt.execute();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            usuario.setIdFuncionario(rs.getInt(1));
            System.out.println("Cadastro realizado com sucesso!!");
            stmt.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public boolean VerfFuncio(Funcionario u) {
    	boolean login = false;
    	try {
    	String sql = "SELECT idFuncionario FROM funcionario WHERE Usuario_idUsuario = ?";
    	stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, u.getIdUsuario());
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
        			login = true;
        			u.setIdFuncionario(rs.getInt(1));
        }
        stmt.close();
    	}catch (Exception e) {
            throw new RuntimeException(e);
        }
		return login;
    }
}
