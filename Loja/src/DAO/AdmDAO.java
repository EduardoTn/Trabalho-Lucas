package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import Conexao.Conexao;
import Model.*;
public class AdmDAO {
	private Connection conexao;
    private PreparedStatement stmt;
    public AdmDAO() {
        this.conexao = new Conexao().getConexao();
    }
    
    public void CadAdm(Administrador usuario) {
        String sql = "INSERT INTO administrador (Funcionario_idFuncionario) VALUES (?)";
        try {
            stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, usuario.getIdFuncionario());
            stmt.execute();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            usuario.setIdAdm(rs.getInt(1));
            System.out.println("Cadastro realizado com sucesso!!");
            stmt.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public boolean VerfAdm(Administrador u) {
    	boolean login = false;
    	try {
    	String sql = "SELECT idAdministrador FROM administrador WHERE Funcionario_idFuncionario = ?";
    	stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, u.getIdFuncionario());
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
        			login = true;
        			u.setIdAdm(rs.getInt(1));
        }
        stmt.close();
    	}catch (Exception e) {
            throw new RuntimeException(e);
        }
		return login;
    }

}
