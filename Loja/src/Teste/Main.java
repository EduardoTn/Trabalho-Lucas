package Teste;
import java.util.Scanner;
import java.util.ArrayList;
import Model.*;
import DAO.*;
public class Main {
	public static void main(String[]args) {
		ArrayList<Produto> lista = new ArrayList<Produto>();
		Scanner Scan = new Scanner(System.in);
		Scanner input = new Scanner(System.in);
		Scanner input1 = new Scanner(System.in);
		int ScanInt;
		System.out.println("Ja possui Cadastro?(s/n) ");
		String ScanStr = Scan.nextLine();
		if (ScanStr.equals("n") == true) {
		Usuario u = new Usuario();
		System.out.println("Insira o Login desejado: ");
		ScanStr = Scan.nextLine();
		u.setLogin(ScanStr);
		System.out.println("Insira a Senha da sua conta: ");
		ScanStr = Scan.nextLine();
		u.setSenha(ScanStr);
		System.out.println("Insira o e-mail da sua conta: ");
		ScanStr = Scan.nextLine();
		u.setEmail(ScanStr);
		System.out.println("Insira o seu endereço: ");
		ScanStr = Scan.nextLine();
		u.setEndereco(ScanStr);
		System.out.println("Insira seu telefone: ");
		ScanInt = input.nextInt();
		u.setNumeroContato(ScanInt);
		
		UsuarioDAO ud = new UsuarioDAO();
		ud.Cadastro(u);
		
		System.out.println("Este usuario é um funcionario??(s/n): ");
		ScanStr = Scan.nextLine();
		if (ScanStr.equals("s") == true) {
			Funcionario f = new Funcionario();
			f.setIdUsuario(u.getIdUsuario());
			FuncionarioDAO fd = new FuncionarioDAO();
			fd.CadFunc(f);
			System.out.println("Este funcionario é um administrador??(s/n): ");
			ScanStr = Scan.nextLine();
			if (ScanStr.equals("s") == true) {
				Administrador a = new Administrador();
				a.setIdFuncionario(f.getIdFuncionario());
				AdmDAO ad = new AdmDAO();
				ad.CadAdm(a);
			}
		}
		}
		Usuario u = new Usuario();
		Funcionario f = new Funcionario();
		Administrador a = new Administrador();
		boolean v1, v2, v3;
		v1 = false;
		v2 = false;
		v3 = false;
		while(0<1) {
		UsuarioDAO ud2 = new UsuarioDAO();
		FuncionarioDAO fd = new FuncionarioDAO();
		AdmDAO ad = new AdmDAO();
		v1 = ud2.login(u);
		if (v1 == true) {
			f.setIdUsuario(u.getIdUsuario());
			System.out.println("Logado com sucesso!!");
			v2 = fd.VerfFuncio(f);
			if (v2 == true) {
				a.setIdFuncionario(f.getIdFuncionario());
				v3 = ad.VerfAdm(a);
			}
			break;
		}else
		{
			System.out.println("Senha ou login não encontrado, tente novamente!!");
		}
		}
		if (v3 == true) {
		while(0<1){
			System.out.println("Opções:");
			System.out.println("1- Cadastrar produto.");
			System.out.println("2- Comprar.");
			System.out.println("3- Repor estoque.");
			System.out.println("4- Consultar cadastros.");
			System.out.println("5- Consultar compras.");
			int scanInt = input.nextInt();
			
			if (scanInt == 1) {
				Produto p = new Produto();
				
				
				System.out.println("Insira o nome do produto:");
				ScanStr = Scan.nextLine();
				p.setNomeProduto(ScanStr);
				
				
				System.out.println("Insira o tipo do produto:");
				ScanStr = Scan.nextLine();
				p.setTipoProduto(ScanStr);
				
				
				System.out.println("Insira o peso do produto:");
				Float ScanFloat = input1.nextFloat();
				p.setPeso(ScanFloat);
				
				
				System.out.println("Insira a quantidade no estoque:");
				ScanInt = input.nextInt();
				p.setQuant(ScanInt);
				
				System.out.println("Insira o preço do produto:");
				ScanFloat = input1.nextFloat();
				p.setPreco(ScanFloat);
				
				ProdutoDAO pd = new ProdutoDAO();
				pd.InserirProduto(p);
			}else if (scanInt == 2) {
				ProdutoDAO pd = new ProdutoDAO();
				lista = pd.ConsultaProduto();
				for (int counter = 0; counter < lista.size(); counter++) { 		      
					 System.out.println("ID = " + lista.get(counter).getCodProduto());
			          System.out.println(lista.get(counter).getNomeProduto());
			          System.out.println("Quantidade no estoque: " + lista.get(counter).getQuant());
			          System.out.println("Preço da unidade: " + lista.get(counter).getPreco() + " R$");
				}
				Compra c = new Compra();
				CompraDAO cd = new CompraDAO();
				
				c.setIdUsuario(u.getIdUsuario());
				
				System.out.println("Insira o id do produto: ");
				ScanInt = input.nextInt();
				c.setIdProduto(ScanInt);
				
				System.out.println("Insira a quantidade do produto: ");
				ScanInt = input.nextInt();
				c.setQuantidade(ScanInt);
				
				System.out.println("Insira o tipo do pedido(entrega/presencial): ");
				ScanStr = Scan.nextLine();
				c.setTipoPedido(ScanStr);
				
				System.out.println("Insira o tipo de pagamento(credito ou a vista): ");
				ScanStr = Scan.nextLine();
				c.setInfoPagamento(ScanStr);
				
				cd.addCarrinho(c);
				cd.AddProduto(c);
				while(0<1){
				System.out.println("Deseja adicionar mais um produto?? (s/n):");
				ScanStr = Scan.nextLine();
				if (ScanStr.equals("n") == true) {
					break;
				}
				lista = pd.ConsultaProduto();
				for (int counter = 0; counter < lista.size(); counter++) { 		      
					 System.out.println("ID = " + lista.get(counter).getCodProduto());
			          System.out.println(lista.get(counter).getNomeProduto());
			          System.out.println("Quantidade no estoque: " + lista.get(counter).getQuant());
			          System.out.println("Preço da unidade: " + lista.get(counter).getPreco() + " R$");
				}
				System.out.println("Insira o id do produto: ");
				ScanInt = input.nextInt();
				c.setIdProduto(ScanInt);
				
				System.out.println("Insira a quantidade do produto: ");
				ScanInt = input.nextInt();
				c.setQuantidade(ScanInt);
				cd.AddProduto(c);
				
				}
				
				System.out.println("Deseja remover um produto?? (s/n):");
				ScanStr = Scan.nextLine();
				if (ScanStr.equals("s") == true) {
					System.out.println("Insira o id do produto: ");
					ScanInt = input.nextInt();
					c.setIdProduto(ScanInt);
					cd.removProduto(c);
					
				}
				
				cd.ColocarPreco(c);
				cd.MostrarPreco(c);
				System.out.println("O preço total é: " + c.getPrecoTotal() + " R$");
				
			}else if (scanInt == 3) {
				Produto p = new Produto();
				ProdutoDAO pd = new ProdutoDAO();
				lista = pd.ConsultaProduto();
				
				for (int counter = 0; counter < lista.size(); counter++) { 		      
			          System.out.println("ID = " + lista.get(counter).getCodProduto());
			          System.out.println(lista.get(counter).getNomeProduto());
			          System.out.println("Quantidade no estoque: " + lista.get(counter).getQuant());
			          System.out.println("Preço da unidade: " + lista.get(counter).getPreco() + " R$");
			      }
				System.out.println("Insira o id do produto para repor: ");
				ScanInt = input.nextInt();
				p.setCodProduto(ScanInt);
				System.out.println("Insira a quantidade do produto para repor: ");
				ScanInt = input.nextInt();
				p.setQuant(ScanInt);
				pd.ReporEstoque(p);
			}else if (scanInt == 4) {
				UsuarioDAO ud = new UsuarioDAO();
				System.out.println("Insira o nome da pessoa: ");
				ScanStr = Scan.nextLine();
				ArrayList<Usuario> Us = new ArrayList<Usuario>();
				Us = ud.consultarCadastro(ScanStr);
				for (int counter = 0; counter < Us.size(); counter++) { 
					System.out.println("Nome = " + Us.get(counter).getNome());
					System.out.println("Endereço = " + Us.get(counter).getEndereco());
				}
			}else if (scanInt == 5) {
				CompraDAO cd1 = new CompraDAO();
				ArrayList<Compra> cpl = new ArrayList<Compra>();
				System.out.println("Insira o tipo de compra que deseja pesquisar (entrega/presencial): ");
				ScanStr = Scan.nextLine();
				cpl = cd1.ConsultaCompras(ScanStr);
				for (int counter = 0; counter < cpl.size(); counter++) { 
					System.out.println("Endereco = " + cpl.get(counter).getEndereco());
					System.out.println("Nome Produto = " + cpl.get(counter).getNomeProduto());
					System.out.println("Quant = " + cpl.get(counter).getQuantidade());
				}
			}
				
			System.out.println("Deseja encerrar??(s/n)");
			ScanStr = Scan.nextLine();
			if (ScanStr.equals("s") == true) {
				break;
			}
		}
	}else if(v2 == true) {
		while(0<1){
			System.out.println("Opções:");
			System.out.println("1- Comprar.");
			System.out.println("2- Repor estoque.");
			System.out.println("3- Consultar compras.");
			int scanInt = input.nextInt();
			if (scanInt == 1) {
				ProdutoDAO pd = new ProdutoDAO();
				lista = pd.ConsultaProduto();
				for (int counter = 0; counter < lista.size(); counter++) { 		      
					 System.out.println("ID = " + lista.get(counter).getCodProduto());
			          System.out.println(lista.get(counter).getNomeProduto());
			          System.out.println("Quantidade no estoque: " + lista.get(counter).getQuant());
			          System.out.println("Preço da unidade: " + lista.get(counter).getPreco() + " R$");
				}
				Compra c = new Compra();
				CompraDAO cd = new CompraDAO();
				
				c.setIdUsuario(u.getIdUsuario());
				
				System.out.println("Insira o id do produto: ");
				ScanInt = input.nextInt();
				c.setIdProduto(ScanInt);
				
				System.out.println("Insira a quantidade do produto: ");
				ScanInt = input.nextInt();
				c.setQuantidade(ScanInt);
				
				System.out.println("Insira o tipo do pedido(entrega/presencial): ");
				ScanStr = Scan.nextLine();
				c.setTipoPedido(ScanStr);
				
				System.out.println("Insira o tipo de pagamento(credito ou a vista): ");
				ScanStr = Scan.nextLine();
				c.setInfoPagamento(ScanStr);
				
				cd.addCarrinho(c);
				cd.AddProduto(c);
				while(0<1){
				System.out.println("Deseja adicionar mais um produto?? (s/n):");
				ScanStr = Scan.nextLine();
				if (ScanStr.equals("n") == true) {
					break;
				}
				lista = pd.ConsultaProduto();
				for (int counter = 0; counter < lista.size(); counter++) { 		      
					 System.out.println("ID = " + lista.get(counter).getCodProduto());
			          System.out.println(lista.get(counter).getNomeProduto());
			          System.out.println("Quantidade no estoque: " + lista.get(counter).getQuant());
			          System.out.println("Preço da unidade: " + lista.get(counter).getPreco() + " R$");
				}
				System.out.println("Insira o id do produto: ");
				ScanInt = input.nextInt();
				c.setIdProduto(ScanInt);
				
				System.out.println("Insira a quantidade do produto: ");
				ScanInt = input.nextInt();
				c.setQuantidade(ScanInt);
				cd.AddProduto(c);
				
				}
				
				System.out.println("Deseja remover um produto?? (s/n):");
				ScanStr = Scan.nextLine();
				if (ScanStr.equals("s") == true) {
					System.out.println("Insira o id do produto: ");
					ScanInt = input.nextInt();
					c.setIdProduto(ScanInt);
					cd.removProduto(c);
					
				}
				
				cd.ColocarPreco(c);
				cd.MostrarPreco(c);
				System.out.println("O preço total é: " + c.getPrecoTotal() + " R$");
			}else if (scanInt == 2) {
				Produto p = new Produto();
				ProdutoDAO pd = new ProdutoDAO();
				lista = pd.ConsultaProduto();
				
				for (int counter = 0; counter < lista.size(); counter++) { 		      
			          System.out.println("ID = " + lista.get(counter).getCodProduto());
			          System.out.println(lista.get(counter).getNomeProduto());
			          System.out.println("Quantidade no estoque: " + lista.get(counter).getQuant());
			          System.out.println("Preço da unidade: " + lista.get(counter).getPreco() + " R$");
			      }
				System.out.println("Insira o id do produto para repor: ");
				ScanInt = input.nextInt();
				p.setCodProduto(ScanInt);
				System.out.println("Insira a quantidade do produto para repor: ");
				ScanInt = input.nextInt();
				p.setQuant(ScanInt);
				pd.ReporEstoque(p);
			}else if (scanInt == 3) {
				CompraDAO cd1 = new CompraDAO();
				ArrayList<Compra> cpl = new ArrayList<Compra>();
				System.out.println("Insira o tipo de compra que deseja pesquisar (entrega/presencial): ");
				ScanStr = Scan.nextLine();
				cpl = cd1.ConsultaCompras(ScanStr);
				for (int counter = 0; counter < cpl.size(); counter++) { 
					System.out.println("Endereco = " + cpl.get(counter).getEndereco());
					System.out.println("Nome Produto = " + cpl.get(counter).getNomeProduto());
					System.out.println("Quant = " + cpl.get(counter).getQuantidade());
				}
			}
			System.out.println("Deseja encerrar??(s/n)");
			ScanStr = Scan.nextLine();
			if (ScanStr.equals("s") == true) {
				break;
			}
			
		}
	}else if(v1 == true) {
		
	while(0<1){
		ProdutoDAO pd = new ProdutoDAO();
		lista = pd.ConsultaProduto();
		for (int counter = 0; counter < lista.size(); counter++) { 		      
			 System.out.println("ID = " + lista.get(counter).getCodProduto());
	          System.out.println(lista.get(counter).getNomeProduto());
	          System.out.println("Quantidade no estoque: " + lista.get(counter).getQuant());
	          System.out.println("Preço da unidade: " + lista.get(counter).getPreco() + " R$");
		}
		Compra c = new Compra();
		CompraDAO cd = new CompraDAO();
		
		c.setIdUsuario(u.getIdUsuario());
		
		System.out.println("Insira o id do produto: ");
		ScanInt = input.nextInt();
		c.setIdProduto(ScanInt);
		
		System.out.println("Insira a quantidade do produto: ");
		ScanInt = input.nextInt();
		c.setQuantidade(ScanInt);
		
		System.out.println("Insira o tipo do pedido(entrega/presencial): ");
		ScanStr = Scan.nextLine();
		c.setTipoPedido(ScanStr);
		
		System.out.println("Insira o tipo de pagamento(credito ou a vista): ");
		ScanStr = Scan.nextLine();
		c.setInfoPagamento(ScanStr);
		
		cd.addCarrinho(c);
		cd.AddProduto(c);
		while(0<1){
		System.out.println("Deseja adicionar mais um produto?? (s/n):");
		ScanStr = Scan.nextLine();
		if (ScanStr.equals("n") == true) {
			break;
		}
		lista = pd.ConsultaProduto();
		for (int counter = 0; counter < lista.size(); counter++) { 		      
			 System.out.println("ID = " + lista.get(counter).getCodProduto());
	          System.out.println(lista.get(counter).getNomeProduto());
	          System.out.println("Quantidade no estoque: " + lista.get(counter).getQuant());
	          System.out.println("Preço da unidade: " + lista.get(counter).getPreco() + " R$");
		}
		System.out.println("Insira o id do produto: ");
		ScanInt = input.nextInt();
		c.setIdProduto(ScanInt);
		
		System.out.println("Insira a quantidade do produto: ");
		ScanInt = input.nextInt();
		c.setQuantidade(ScanInt);
		cd.AddProduto(c);
		
		}
		
		System.out.println("Deseja remover um produto?? (s/n):");
		ScanStr = Scan.nextLine();
		if (ScanStr.equals("s") == true) {
			System.out.println("Insira o id do produto: ");
			ScanInt = input.nextInt();
			c.setIdProduto(ScanInt);
			cd.removProduto(c);
			
		}
		
		cd.ColocarPreco(c);
		cd.MostrarPreco(c);
		System.out.println("O preço total é: " + c.getPrecoTotal() + " R$");
			System.out.println("Deseja encerrar??(s/n)");
			ScanStr = Scan.nextLine();
			if (ScanStr.equals("s") == true) {
				break;
			}
		}	
	}
	}
}
