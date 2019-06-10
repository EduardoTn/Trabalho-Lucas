package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import Conexao.Conexao;
import Model.*;
import Conexao.Conexao;

public class ProdutoDAO {
	private Connection conexao;
    private PreparedStatement stmt;
    public ProdutoDAO() {
        this.conexao = new Conexao().getConexao();
    }
    public void InserirProduto(Produto produto) {
        String sql = "INSERT INTO produto (NomeProduto, TipoProduto, Peso, QuantEstoque, Preco) VALUES (?,?,?,?,?)";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, produto.getNomeProduto());
            stmt.setString(2, produto.getTipoProduto());
            stmt.setFloat(3, produto.getPeso());
            stmt.setInt(4, produto.getQuant());
            stmt.setFloat(5, produto.getPreco());
            stmt.execute();
            System.out.println("Cadastrado com sucesso!!");
            stmt.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void ReporEstoque(Produto produto) {
    	String sql = "UPDATE produto SET QuantEstoque = ? + QuantEstoque WHERE idProduto = ?";
    	 try {
             stmt = conexao.prepareStatement(sql);
             stmt.setInt(1, produto.getQuant());
             stmt.setInt(2, produto.getCodProduto());
             stmt.execute();
             System.out.println("O estoque foi atualizado com sucesso!!");
             stmt.close();
         } catch (Exception e) {
             throw new RuntimeException(e);
         }
    }
    public ArrayList<Produto> ConsultaProduto(){
    	String sql = "SELECT idProduto,NomeProduto, QuantEstoque, Preco FROM produto WHERE NomeProduto";
        try {
            stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Produto> lista = new ArrayList<Produto>();
            while (rs.next()) {
                Produto p = new Produto();
                p.setCodProduto(rs.getInt("idProduto"));
                p.setNomeProduto(rs.getString("NomeProduto"));
                p.setQuant(rs.getInt("QuantEstoque"));
                p.setPreco(rs.getFloat("Preco"));
                lista.add(p);
            }
            stmt.close();
            return lista;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
