package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import Conexao.Conexao;
import Model.Usuario;
public class UsuarioDAO {
	private Connection conexao;
    private PreparedStatement stmt;
    public UsuarioDAO() {
        this.conexao = new Conexao().getConexao();
    }
    public void Cadastro(Usuario usuario) {
        String sql = "INSERT INTO usuario (login, senha, email, endereco,NumeroContato) VALUES (?,?,?,?,?)";
        try {
            stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, usuario.getLogin());
            stmt.setString(2, usuario.getSenha());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getEndereco());
            stmt.setInt(5, usuario.getNumeroContato());
            stmt.execute();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            usuario.setIdUsuario(rs.getInt(1));
            System.out.println("Cadastro realizado com sucesso!!");
            stmt.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Usuario> consultarCadastro(String Consulta) {
        String sql = "SELECT login,endereco FROM usuario WHERE login LIKE ?";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, "%" + Consulta + "%");
            ResultSet rs = stmt.executeQuery();
            ArrayList<Usuario> lista = new ArrayList<Usuario>();
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setNome(rs.getString("login"));
                u.setEndereco(rs.getString("endereco"));
                lista.add(u);
            }
            stmt.close();
            return lista;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public boolean login(Usuario u) {
    	boolean login = false;
    	try {
    	Scanner Scan = new Scanner(System.in);
    	System.out.println("Insira o Login:");
		String ScanStr = Scan.nextLine();
		u.setLogin(ScanStr);
		System.out.println("Insira a Senha da sua conta: ");
		ScanStr = Scan.nextLine();
		u.setSenha(ScanStr);
    	String sql = "SELECT idUsuario, login, senha FROM usuario WHERE login = ? AND senha = ?";
    	stmt = conexao.prepareStatement(sql);
        stmt.setString(1, u.getLogin());
        stmt.setString(2, u.getSenha());
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
        			login = true;
        			u.setIdUsuario(rs.getInt(1));
        }
        stmt.close();
    	}catch (Exception e) {
            throw new RuntimeException(e);
        }
		return login;
    }
}