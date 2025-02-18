	package tela;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import banco.FabricaConexao;
import dominio.Usuario;
import util.CriptografiaUtils;
import java.awt.Color;

public class CadastrarUsuario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldEmail;
	private JTextField textFieldNome;
	private JPasswordField passwordFieldSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastrarUsuario frame = new CadastrarUsuario();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					frame.setTitle("Cadastro de Usuário");

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CadastrarUsuario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 449, 427);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 153, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Cadastro de Usu\u00E1rio");
		lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 30));
		lblNewLabel.setBounds(77, 21, 280, 33);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setBounds(200, 98, 38, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("E-mail");
		lblNewLabel_1_1.setBounds(200, 154, 38, 14);
		contentPane.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Senha");
		lblNewLabel_1_1_1.setBounds(200, 214, 38, 14);
		contentPane.add(lblNewLabel_1_1_1);

		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(123, 172, 195, 20);
		contentPane.add(textFieldEmail);

		textFieldNome = new JTextField();
		textFieldNome.setColumns(10);
		textFieldNome.setBounds(123, 116, 195, 20);
		contentPane.add(textFieldNome);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cadastrarUsuario();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnCadastrar.setBounds(123, 301, 196, 57);
		contentPane.add(btnCadastrar);

		passwordFieldSenha = new JPasswordField();
		passwordFieldSenha.setBounds(123, 232, 195, 20);
		contentPane.add(passwordFieldSenha);

	}
	// verificar se os campos são nulos

	protected void cadastrarUsuario() throws ClassNotFoundException, SQLException {

		String nome = textFieldNome.getText();
		String email = textFieldEmail.getText();
		String senha = new String(passwordFieldSenha.getPassword());
		String senhaCriptografada = CriptografiaUtils.criptografarMD5(senha);

		if (nome == null || nome.isEmpty()) {
			exibirMensagemErro("Nome não pode ser vazio");
			return;
		}

		if (email == null || email.isEmpty()) {
			exibirMensagemErro("Email não pode ser vazio");
			return;
		}

		if (senha == null || senha.isEmpty()) {
			exibirMensagemErro("Senha não pode ser vazio");
			return;
		}
		
		Usuario u = new Usuario();
		u.setNome(nome);
		u.setEmail(email);
		u.setSenha(senhaCriptografada);
		
		Connection conexao = FabricaConexao.criarConexao();
		
		String sql = "INSERT INTO usuario (nome,email,senha) VALUES (?,?,?)";

		PreparedStatement comando = conexao.prepareStatement(sql);
		
		comando.setString(1, u.getNome());
		comando.setString(2, u.getEmail());
		comando.setString(3, u.getSenha());
		comando.execute();
		
		comando.close();
		conexao.close();
		
		exibirMensagemErro("Usuário - " + nome + " - Cadastrado com Sucesso!");
		
		
	}

	private void exibirMensagemErro(String msg) {
		JOptionPane.showMessageDialog(null, msg);

	}

	

	// instanciar a classe usuario

	// criar a conexão (fabrica conexão)

	// criar o sql (insert) para inserir

	// preparar a conexão

	// executar o sql

	// limpar os campos após inserir
}
