package tela;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import banco.FabricaConexao;
import dominio.Cliente;
import javax.swing.border.EtchedBorder;
import java.awt.Color;

public class CadastrarCliente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNome;
	private JTextField textFieldEndereco;
	private JTextField textFieldTelefone;
	private JList listaClientes;

	private Cliente clienteEdicao;
	private JButton btnNewButtonCadastrar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {
					CadastrarCliente frame = new CadastrarCliente();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CadastrarCliente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 730, 427);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 64, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		textFieldNome = new JTextField();
		textFieldNome.setBounds(77, 46, 123, 20);
		contentPane.add(textFieldNome);
		textFieldNome.setColumns(10);

		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setBounds(119, 28, 61, 14);
		contentPane.add(lblNewLabel);

		btnNewButtonCadastrar = new JButton("Cadastrar Cliente");
		btnNewButtonCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cadastrarCliente();
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}

			}
		});
		btnNewButtonCadastrar.setBounds(72, 268, 128, 38);
		contentPane.add(btnNewButtonCadastrar);

		JPanel panel = new JPanel();
		panel.setBorder(
				new TitledBorder(null, "Cadastro de Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 318, 362);
		contentPane.add(panel);
		panel.setLayout(null);
		
				textFieldEndereco = new JTextField();
				textFieldEndereco.setBounds(67, 105, 123, 20);
				panel.add(textFieldEndereco);
				textFieldEndereco.setToolTipText("");
				textFieldEndereco.setColumns(10);
				
						JLabel lblMatrcula = new JLabel("Endereço");
						lblMatrcula.setBounds(99, 80, 61, 14);
						panel.add(lblMatrcula);
						
								JLabel lblTelefone = new JLabel("Telefone");
								lblTelefone.setBounds(109, 161, 61, 14);
								panel.add(lblTelefone);
								
										textFieldTelefone = new JTextField();
										textFieldTelefone.setBounds(67, 186, 123, 20);
										panel.add(textFieldTelefone);
										textFieldTelefone.setColumns(10);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(
				new TitledBorder(null, "Listagem de Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(338, 11, 366, 362);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 21, 346, 293);
		panel_1.add(scrollPane);

		listaClientes = new JList();
		scrollPane.setViewportView(listaClientes);

		JButton btnNewButtonExibir = new JButton("Exibir Dados");
		btnNewButtonExibir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cliente clienteSelecionado = (Cliente) listaClientes.getSelectedValue();

				String msg = "Nome: " + clienteSelecionado.getNome() + "\nEndereco: " + clienteSelecionado.getEndereco()
						 + "\nTelefone: " + clienteSelecionado.getTelefone();

				exibirMensagem(msg);
			}
		});
		btnNewButtonExibir.setBounds(4, 325, 114, 23);
		panel_1.add(btnNewButtonExibir);

		JButton Remover = new JButton("Remover Dados");
		Remover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					removerDados();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		Remover.setBounds(242, 325, 114, 23);
		panel_1.add(Remover);

		JButton btnNewButtonEditar = new JButton("Editar Dados");
		btnNewButtonEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iniciarEdicaoCliente();
			}
		});
		btnNewButtonEditar.setBounds(117, 325, 125, 23);
		panel_1.add(btnNewButtonEditar);

		try {
			atualizarListagemClientes();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	protected void removerDados() throws ClassNotFoundException, SQLException {
		if (listaClientes.getSelectedIndex() == -1) {
			exibirMensagemErro("Selecione o Usuário");
			return;
		}

		clienteEdicao = (Cliente) listaClientes.getSelectedValue();

		Connection conexao = FabricaConexao.criarConexao();

		String sql = "DELETE FROM CLIENTE WHERE ID_CLIENTE = ?";

		PreparedStatement comando = conexao.prepareStatement(sql);
		comando.setInt(1, clienteEdicao.getId());
		comando.executeUpdate();

		exibirMensagem("Usuário deletado");

		atualizarListagemClientes();

		comando.close();
		conexao.close();

	}

	protected void iniciarEdicaoCliente() {
		if (listaClientes.getSelectedIndex() == -1) {
			exibirMensagemErro("Selecione o Usuário");
			return;
		}

		clienteEdicao = (Cliente) listaClientes.getSelectedValue();

		textFieldNome.setText(clienteEdicao.getNome());
		textFieldEndereco.setText(clienteEdicao.getEndereco());
		textFieldTelefone.setText(clienteEdicao.getTelefone());

		btnNewButtonCadastrar.setText("Editar");

	}

	protected void cadastrarCliente() throws ClassNotFoundException, SQLException {

		if (textFieldNome.getText() == null || textFieldNome.getText().isEmpty()) {
			exibirMensagemErro("Nome não pode ser vazio");
			return;
		}

		if (textFieldEndereco.getText() == null || textFieldEndereco.getText().isEmpty()) {
			exibirMensagemErro("Endereço não pode ser vazio");
			return;
		}

		if (textFieldTelefone.getText() == null || textFieldTelefone.getText().isEmpty()) {
			exibirMensagemErro("Telefone não pode ser vazio");
			return;
		}

		if (btnNewButtonCadastrar.getText().equals("Cadastrar Cliente")) {

			Connection conexao = FabricaConexao.criarConexao();

			JOptionPane.showMessageDialog(null, "Inserindo Dados");

			String sql = "INSERT INTO cliente (nome, endereco,telefone) VALUES (?,?,?)";

			Cliente c = new Cliente();
			c.setNome(textFieldNome.getText());
			c.setEndereco(textFieldEndereco.getText());
			c.setTelefone(textFieldTelefone.getText());

			PreparedStatement comando = conexao.prepareStatement(sql);
			comando.setString(1, c.getNome());
			comando.setString(2, c.getEndereco());
			comando.setString(3, c.getTelefone());
			comando.execute();

			exibirMensagem("Dados Cadastrados");

			comando.close();
			conexao.close();

		} else if (btnNewButtonCadastrar.getText().equals("Editar")) {

			clienteEdicao.setNome(textFieldNome.getText());
			clienteEdicao.setEndereco(textFieldEndereco.getText());
			clienteEdicao.setTelefone(textFieldTelefone.getText());

			exibirMensagem("Editando Dados");

			Connection conexao = FabricaConexao.criarConexao();

			String sql = "UPDATE CLIENTE SET NOME=?, ENDERECO=?, TELEFONE=? WHERE ID_CLIENTE = ?";

			PreparedStatement comando = conexao.prepareStatement(sql);
			comando.setString(1, clienteEdicao.getNome());
			comando.setString(2, clienteEdicao.getEndereco());
			comando.setString(3, clienteEdicao.getTelefone());
			comando.setInt(4, clienteEdicao.getId());
			comando.executeUpdate();

			exibirMensagem("Dados Alterados");

			comando.close();
			conexao.close();

			clienteEdicao = null;

		}

		btnNewButtonCadastrar.setText("Cadastrar Cliente");
		;

		atualizarListagemClientes();

		textFieldNome.setText("");
		textFieldEndereco.setText("");
		textFieldTelefone.setText("");
	}

	private void exibirMensagemErro(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Erro", JOptionPane.ERROR_MESSAGE);
	}

	private void exibirMensagem(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Info", JOptionPane.INFORMATION_MESSAGE);
	}

	private void atualizarListagemClientes() throws ClassNotFoundException, SQLException {

		Connection conexao = FabricaConexao.criarConexao();

		String sql = "SELECT * FROM CLIENTE";

		PreparedStatement comando = conexao.prepareStatement(sql);

		System.out.println("Executando Comando...");

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

		DefaultListModel<Cliente> modelo = new DefaultListModel<>();

		for (int i = 0; i < clientesCadastrados.size(); i++) {
			Cliente c = clientesCadastrados.get(i);
			modelo.addElement(c);

		}

		listaClientes.setModel(modelo);

		comando.close();
		conexao.close();
	}
}
