package tela;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import javax.swing.UIManager;

public class Inicial extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicial frame = new Inicial();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Inicial() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 564, 430);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 153, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Cadastrar Cliente");
		btnNewButton.setFont(UIManager.getFont("Button.font"));
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastrarUsuario cad = new CadastrarUsuario();
				cad.setVisible(true);
				cad.setLocationRelativeTo(null);
				cad.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			}
		});
		btnNewButton.setBounds(376, 206, 143, 54);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Login");
		btnNewButton_1.setFont(UIManager.getFont("Button.font"));
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login l = new Login();
				l.setVisible(true);
				l.setLocationRelativeTo(null);
				l.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			}
		});
		btnNewButton_1.setBounds(376, 123, 143, 54);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Sistema de Gerenciamento de Clientes");
		lblNewLabel.setFont(new Font("Sitka Display", Font.BOLD, 25));
		lblNewLabel.setBounds(71, 31, 414, 37);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Inicial.class.getResource("/imagem/pngwing.com.png")));
		lblNewLabel_2.setBounds(10, 65, 404, 315);
		contentPane.add(lblNewLabel_2);
	}
}
