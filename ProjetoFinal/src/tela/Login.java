package tela;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import banco.UsuarioDao;
import dominio.Usuario;
import util.CriptografiaUtils;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldEmail;
	private JPasswordField passwordFieldSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
					frame.setTitle("Login");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 304, 184);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(10, 11, 35, 14);
		contentPane.add(lblEmail);

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(10, 50, 45, 17);
		contentPane.add(lblSenha);

		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(55, 8, 205, 20);
		contentPane.add(textFieldEmail);
		textFieldEmail.setColumns(10);

		passwordFieldSenha = new JPasswordField();
		passwordFieldSenha.setBounds(55, 48, 205, 20);
		contentPane.add(passwordFieldSenha);

		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					fazerLogin();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnEntrar.setBounds(55, 91, 205, 23);
		contentPane.add(btnEntrar);
	}

	protected void fazerLogin() throws ClassNotFoundException, SQLException {

		String email = textFieldEmail.getText();
		String senha = new String(passwordFieldSenha.getPassword());
		String senhaCriptografada = CriptografiaUtils.criptografarMD5(senha);

		UsuarioDao dao = new UsuarioDao();

		Usuario u = dao.encontrarUsuarioPorEmailESenha(email, senhaCriptografada);

		if (u == null) {

			JOptionPane.showMessageDialog(null, "Não foi encontrado usuários");
		} else {

			Principal ba = new Principal();
			ba.setLocationRelativeTo(null);
			ba.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			ba.setVisible(true);

		}
	}
}
