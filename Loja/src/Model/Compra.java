package Model;
public class Compra {
	private int numCarrinho;
	private int IdProduto;
	private int quantidade;
	private String tipoPedido;
	private String InfoPagamento;
	private float PrecoTotal;
	private int idUsuario;
	private String endereco;
	private String NomeProduto;
	public String getInfoPagamento() {
		return InfoPagamento;
	}
	public void setInfoPagamento(String infoPagamento) {
		InfoPagamento = infoPagamento;
	}
	public int getNumCarrinho() {
		return numCarrinho;
	}
	public void setNumCarrinho(int numCarrinho) {
		this.numCarrinho = numCarrinho;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public String getTipoPedido() {
		return tipoPedido;
	}
	public void setTipoPedido(String tipoPedido) {
		this.tipoPedido = tipoPedido;
	}
	public int getIdProduto() {
		return IdProduto;
	}
	public void setIdProduto(int idProduto) {
		IdProduto = idProduto;
	}
	public float getPrecoTotal() {
		return PrecoTotal;
	}
	public void setPrecoTotal(float precoTotal) {
		PrecoTotal = precoTotal;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getNomeProduto() {
		return NomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		NomeProduto = nomeProduto;
	}
}
