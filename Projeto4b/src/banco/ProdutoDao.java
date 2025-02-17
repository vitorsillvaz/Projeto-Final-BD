package banco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import dominio.Cliente;
import dominio.Produto;

public class ProdutoDao {

	
	public void cadastrarProduto(Produto d) throws SQLException, ClassNotFoundException {
		
		Connection conexao = FabricaConexao.criarConexao();

	
		System.out.println("Inserindo dados...");

		String sql = " INSERT INTO produto (nome,validade,id_cliente) VALUES (?,?,?) ";

		PreparedStatement comando = conexao.prepareStatement(sql);
		comando.setString(1, d.getNomeProduto());
		comando.setInt(2, d.getValidade());
		comando.setInt(3, d.getCliente().getId());
		comando.execute();

		System.out.println("Fechando Conexão");

		comando.close();
		conexao.close();

		JOptionPane.showMessageDialog(null, "Produto Cadastrado com Sucesso");
		
	}

	
	public List<Produto> buscarProdutoPeloNome(String nome) throws SQLException, ClassNotFoundException {

		Connection conexao = FabricaConexao.criarConexao();
		String sql = " SELECT * FROM produto WHERE 1 = 1 ";

		if (nome != null && !nome.isEmpty()) {
			sql += " AND upper(nome) LIKE ? ";
		}

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		int i = 1;

		if (nome != null && !nome.isEmpty()) {
			comando.setString(i++, "%" + nome.toUpperCase() + "%");
		}

		ResultSet resultado = comando.executeQuery();

		List<Produto> produtosCadastrados = new ArrayList<>();

		while (resultado.next()) {
			Produto p = new Produto();
			p.setIdProduto(i);
			p.setNomeProduto(resultado.getString("nome"));
			p.setValidade(resultado.getInt("validade"));

			produtosCadastrados.add(p);
		}

		return produtosCadastrados;

	}

	public List<Produto> buscarProduto(String nome, String endereco, String telefone, String produto, String validade)
			throws SQLException, ClassNotFoundException {
		
		Connection conexao = FabricaConexao.criarConexao();
		
		String sql = " SELECT c.id_cliente, p.id_produto, c.nome, c.endereco, c.telefone, p.nome as nomed, p.validade FROM cliente c join produto p on c.id_cliente = p.id_produto WHERE 1 = 1 ";

		if (nome != null && !nome.isEmpty()) {
			sql += " AND c.nome LIKE ? ";
		}

		if (endereco != null && !endereco.isEmpty()) {
			sql += " AND c.endereco = ? ";
		}

		if (telefone != null && !telefone.isEmpty()) {
			sql += " AND c.telefone = ? ";
		}
		if (produto != null && !produto.isEmpty()) {
			sql += " AND p.nome = ? ";
		}
		if (validade != null && !validade.isEmpty()) {
			sql += " AND p.validade = ? ";
		}
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		int i = 1;

		if (nome != null && !nome.isEmpty()) {
			comando.setString(i, "%" + nome.toUpperCase() + "%");
			i++;
		}

		if (endereco != null && !endereco.isEmpty()) {
			comando.setString(i, endereco);
			i++;
		}


		if (telefone != null && !telefone.isEmpty()) {
			comando.setString(i, telefone);
			i++;
		}
		
		if (produto != null && !produto.isEmpty()) {
			comando.setString(i, produto);
			i++;
		}
	
		if (validade != null && !validade.isEmpty()) {
			comando.setString(i, validade);
			i++;
		}
		
		ResultSet resultado = comando.executeQuery();

		List<Produto> produtosCadastrados = new ArrayList<>();

		while (resultado.next()) {
			
			Cliente c = new Cliente();
			c.setId(resultado.getInt("id_aluno"));
			c.setNome(resultado.getString("nome"));
			c.setEndereco(resultado.getString("endereco"));
			c.setTelefone(resultado.getString("telefone"));
			

	        Produto p = new Produto();
	        
	        p.setIdProduto(resultado.getInt("id_produto"));
	        p.setNomeProduto(resultado.getString("nomep"));
	        p.setValidade(resultado.getInt("validade"));
	        p.setCliente(c);

	        // Associando o produto ao cliente
	        c.setProduto(p);
	        
	        produtosCadastrados.add(p);
		}

		return produtosCadastrados;

	}
}
