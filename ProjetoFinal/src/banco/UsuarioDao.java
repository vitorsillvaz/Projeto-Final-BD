package banco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dominio.Usuario;

public class UsuarioDao {

	public Usuario encontrarUsuarioPorEmailESenha(String email, String SenhaCriptografada)
			throws SQLException, ClassNotFoundException {

		Connection conexao = FabricaConexao.criarConexao();

		String sql = " SELECT * FROM usuario u WHERE u.email LIKE ? AND u.senha = ? ";

		PreparedStatement comando = conexao.prepareStatement(sql);

		comando.setString(1, email);
		comando.setString(2, SenhaCriptografada);

		ResultSet resultado = comando.executeQuery();

		if (resultado.next()) {
			Usuario usuario = new Usuario();
			usuario.setId(resultado.getInt("id_usuario"));
			usuario.setNome(resultado.getString("nome"));
			usuario.setEmail(resultado.getString("email"));
			usuario.setSenha(resultado.getString("senha"));

			return usuario;
		}

		comando.close();
		conexao.close();

		return null;
	}
}