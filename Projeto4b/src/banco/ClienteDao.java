package banco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dominio.Cliente;
import dominio.Produto;

public class ClienteDao {

	public List<Cliente> buscarClientePeloNome(String nome) throws SQLException, ClassNotFoundException {

		Connection conexao = FabricaConexao.criarConexao();

		String sql = " SELECT * FROM cliente WHERE 1 = 1 ";

		if (nome != null && !nome.isEmpty()) {
			sql += " AND upper(nome) LIKE ? ";
		}

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		int i = 1;

		if (nome != null && !nome.isEmpty()) {
			comando.setString(i++, "%" + nome.toUpperCase() + "%");
		}

		ResultSet resultado = comando.executeQuery();

		List<Cliente> clientesCadastrados = new ArrayList<>();

		while (resultado.next()) {
			Cliente c = new Cliente();
			c.setId(resultado.getInt("id_cliente"));
			c.setNome(resultado.getString("nome"));
			c.setEndereco(resultado.getString("endereco"));
			c.setTelefone(resultado.getString("telefone"));

			clientesCadastrados.add(c);
		}

		return clientesCadastrados;
	}

	public List<Cliente> buscarClientes(String nome, String endereco, String telefone)
			throws SQLException, ClassNotFoundException {
		Connection conexao = FabricaConexao.criarConexao();
		
		String sql = " SELECT c.id_cliente, d.id_produto, c.nome, c.endereco, c.telefone, p.nome as nomed, p.validade FROM cliente a join produto p on c.id_cliente = p.id_produto WHERE 1 = 1 ";

		if (nome != null && !nome.isEmpty()) {
			sql += " AND c.nome LIKE ? ";
		}

		if (endereco != null && !endereco.isEmpty()) {
			sql += " AND c.endereco = ? ";
		}

		if (telefone != null && !telefone.isEmpty()) {
			sql += " AND c.telefone = ? ";
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

		ResultSet resultado = comando.executeQuery();

		List<Cliente> clientesCadastrados = new ArrayList<>();

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
	        
			clientesCadastrados.add(c);
		}

		return clientesCadastrados;

	}
}
