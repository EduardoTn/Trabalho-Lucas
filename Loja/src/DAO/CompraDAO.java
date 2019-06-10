package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import Conexao.Conexao;
import Model.*;
import Conexao.Conexao;
import java.util.Scanner;
public class CompraDAO {
	Scanner Scan = new Scanner(System.in);
	private Connection conexao;
    private PreparedStatement stmt;
    public CompraDAO() {
        this.conexao = new Conexao().getConexao();
    }
    public void addCarrinho(Compra produto) {
    	String sql = "INSERT INTO compra (InfoPagamento,tipoPedido,Usuario_idUsuario) VALUES (?,?,?)";
        try {
            stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, produto.getInfoPagamento());
            stmt.setString(2, produto.getTipoPedido());
            stmt.setInt(3, produto.getIdUsuario());
            stmt.execute();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            produto.setNumCarrinho(rs.getInt(1));
            System.out.println("Carrinho criado com sucesso!!");
            stmt.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void AddProduto(Compra produto) {
        String sql = "INSERT INTO Produto_has_Compra (Compra_idCompra, Produto_idProduto, Quant) VALUES (?,?,?)";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, produto.getNumCarrinho());
            stmt.setInt(2, produto.getIdProduto());
            stmt.setInt(3, produto.getQuantidade());
            stmt.execute();
            System.out.println("Produto adicionado com sucesso!!");
            stmt.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public void ColocarPreco(Compra produto) {
        String sql = "Update compra set compra.PrecoTotal = (SELECT CAST(sum((produto.Preco)*(produto_has_compra.Quant)) AS DECIMAL(15,2)) from produto inner join produto_has_compra on produto.idProduto = produto_has_compra.Produto_idProduto where produto_has_compra.Compra_idCompra = ?) where compra.idCompra = ?";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, produto.getNumCarrinho());
            stmt.setInt(2, produto.getNumCarrinho());
            stmt.execute();
            stmt.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public void MostrarPreco(Compra produto) {
        String sql = "SELECT CAST(sum((produto.Preco)*(produto_has_compra.Quant)) AS DECIMAL(15,2)) from produto inner join produto_has_compra inner join compra on compra.idCompra = produto_has_compra.Compra_idCompra on produto.idProduto = produto_has_compra.Produto_idProduto where produto_has_compra.Compra_idCompra = ?"; 
        		try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, produto.getNumCarrinho());
            ResultSet rs = stmt.executeQuery();
            rs.next();
            produto.setPrecoTotal(rs.getFloat(1));
            stmt.execute();
            stmt.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    
    public void AttQuant(Compra produto) {
        String sql = "UPDATE Produto_has_Compra SET Quant = ? WHERE Compra_idCompra = ? AND Produto_idProduto = ?";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, produto.getQuantidade());
            stmt.setInt(2, produto.getNumCarrinho());
            stmt.setInt(3, produto.getIdProduto());
            stmt.execute();
            stmt.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void removProduto(Compra produto) {
    	String sql = "DELETE FROM Produto_has_Compra WHERE Compra_idCompra = ? AND Produto_idProduto = ?";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, produto.getNumCarrinho());
            stmt.setInt(2, produto.getIdProduto());
            stmt.execute();
            stmt.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public ArrayList<Compra> ConsultaCompras(String Consulta){
    	String sql = "SELECT usuario.endereco, produto.NomeProduto, produto_has_compra.Quant from usuario inner join compra inner join produto_has_compra inner join produto on produto.idProduto = produto_has_compra.Produto_idProduto on produto_has_compra.Compra_idCompra = compra.idCompra on compra.Usuario_idUsuario = usuario.idUsuario where compra.tipoPedido LIKE ?";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, "%" + Consulta + "%");
            ResultSet rs = stmt.executeQuery();
            ArrayList<Compra> lista = new ArrayList<Compra>();
            while (rs.next()) {
                Compra p = new Compra();
                p.setEndereco(rs.getString("endereco"));
                p.setNomeProduto(rs.getString("NomeProduto"));
                p.setQuantidade(rs.getInt("Quant"));
                lista.add(p);
            }
            stmt.close();
            return lista;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

