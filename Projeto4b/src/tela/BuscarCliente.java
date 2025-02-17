package tela;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import banco.ClienteDao;
import dominio.Cliente;
import dominio.Produto;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import java.awt.Color;

public class BuscarCliente extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNome;
	private JTextField textFieldEndereco;

	private JPanel panel_1;
	private JScrollPane scrollPane;

	private JButton btnBuscar;
	private JTable tabelaClientes;
	private JTextField textFieldTelefone;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastrarCliente frame = new CadastrarCliente();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BuscarCliente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 773, 349);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 153, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Cadastrar Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 29, 231, 267);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNome = new JLabel("Nome: ");
		lblNome.setBounds(10, 45, 46, 14);
		panel.add(lblNome);

		JLabel lblEndereco = new JLabel("Endereço:");
		lblEndereco.setBounds(10, 93, 60, 14);
		panel.add(lblEndereco);

		textFieldNome = new JTextField();
		textFieldNome.setBounds(80, 42, 125, 20);
		panel.add(textFieldNome);
		textFieldNome.setColumns(10);

		textFieldEndereco = new JTextField();
		textFieldEndereco.setColumns(10);
		textFieldEndereco.setBounds(80, 87, 125, 20);
		panel.add(textFieldEndereco);

		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					buscarCliente();
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnBuscar.setBounds(80, 192, 125, 23);
		panel.add(btnBuscar);

		textFieldTelefone = new JTextField();
		textFieldTelefone.setColumns(10);
		textFieldTelefone.setBounds(80, 143, 125, 20);
		panel.add(textFieldTelefone);

		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(10, 146, 46, 14);
		panel.add(lblTelefone);

		panel_1 = new JPanel();
		panel_1.setBorder(
				new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Listagem de Clientes", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(251, 29, 485, 267);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(20, 26, 431, 215);
		panel_1.add(scrollPane);

		tabelaClientes = new JTable();
		tabelaClientes.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Endereço", "Nome", "Telefone"}));
		scrollPane.setViewportView(tabelaClientes);

	}

	protected void buscarCliente() throws ClassNotFoundException, SQLException {

		ClienteDao dao = new ClienteDao();

		List<Cliente> clientesEncontrados = new ArrayList<Cliente>();

		clientesEncontrados = dao.buscarClientes(textFieldNome.getText(), textFieldEndereco.getText(), textFieldTelefone.getText());

		DefaultTableModel modelo = new DefaultTableModel(
				new String[] { "Endereço", "Nome", "Telefone" }, 0);

		for (int i = 0; i < clientesEncontrados.size(); i++) {
			Cliente cliente = clientesEncontrados.get(i);
	      			
			modelo.addRow(new String[] { cliente.getEndereco(), cliente.getNome(), cliente.getTelefone() });
		}

		tabelaClientes.setModel(modelo);

	}
}
