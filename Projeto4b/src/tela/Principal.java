package tela;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;

public class Principal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
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
	public Principal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 609, 377);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButtonCadastrar = new JButton("Cadastrar Cliente");
		btnNewButtonCadastrar.setBackground(new Color(255, 255, 255));
		btnNewButtonCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastrarCliente ca = null;
				ca = new CadastrarCliente();
				ca.setLocationRelativeTo(null);
				ca.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				ca.setVisible(true);
				
			}
		});
		btnNewButtonCadastrar.setBounds(20, 24, 135, 50);
		contentPane.add(btnNewButtonCadastrar);
		
		JButton btnNewButtonBuscar = new JButton("Buscar Cliente");
		btnNewButtonBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscarCliente bc = new BuscarCliente();
				bc.setLocationRelativeTo(null);
				bc.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				bc.setVisible(true);
				
			}
		});
		btnNewButtonBuscar.setBackground(new Color(255, 255, 255));
		btnNewButtonBuscar.setBounds(165, 24, 135, 50);
		contentPane.add(btnNewButtonBuscar);
		
		JButton btnCadastrarProduto = new JButton("Cadastrar Produto");
		btnCadastrarProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastrarProduto ca = new CadastrarProduto();
				ca.setLocationRelativeTo(null);
				ca.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				ca.setVisible(true);
			}
		});
		btnCadastrarProduto.setBackground(Color.WHITE);
		btnCadastrarProduto.setBounds(310, 24, 135, 50);
		contentPane.add(btnCadastrarProduto);
		
		JButton btnBuscarProduto = new JButton("Buscar Produto");
		btnBuscarProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarProduto();
			}
		});
		btnBuscarProduto.setBackground(Color.WHITE);
		btnBuscarProduto.setBounds(455, 24, 128, 50);
		contentPane.add(btnBuscarProduto);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Principal.class.getResource("/imagem/fundo.jpg")));
		lblNewLabel.setBounds(0, 0, 616, 346);
		contentPane.add(lblNewLabel);
	}

	protected void buscarProduto() {
		
	}
}
