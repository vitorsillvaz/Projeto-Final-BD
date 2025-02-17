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
import banco.ProdutoDao;
import dominio.Cliente;
import dominio.Produto;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import java.awt.Color;

public class BuscarProduto extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNome;
	private JTextField textFieldValidade;

	private JPanel panel_1;
	private JScrollPane scrollPane;

	private JButton btnBuscar;
	private JTable tabelaProdutos;
	private JTextField textFieldCliente;

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
	public BuscarProduto() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 773, 383);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Cadastrar Produto", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 29, 231, 267);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNome = new JLabel("Nome: ");
		lblNome.setBounds(10, 45, 46, 14);
		panel.add(lblNome);

		JLabel lblMatricula = new JLabel("Validade");
		lblMatricula.setBounds(10, 93, 60, 14);
		panel.add(lblMatricula);

		textFieldNome = new JTextField();
		textFieldNome.setBounds(80, 42, 125, 20);
		panel.add(textFieldNome);
		textFieldNome.setColumns(10);

		textFieldValidade = new JTextField();
		textFieldValidade.setColumns(10);
		textFieldValidade.setBounds(80, 87, 125, 20);
		panel.add(textFieldValidade);

		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					buscarProduto();
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnBuscar.setBounds(80, 192, 125, 23);
		panel.add(btnBuscar);

		textFieldCliente = new JTextField();
		textFieldCliente.setColumns(10);
		textFieldCliente.setBounds(80, 143, 125, 20);
		panel.add(textFieldCliente);

		JLabel lblTelefone = new JLabel("Cliente");
		lblTelefone.setBounds(10, 146, 46, 14);
		panel.add(lblTelefone);

		panel_1 = new JPanel();
		panel_1.setBorder(
				new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Listagem de Produto", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(251, 29, 485, 267);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(20, 26, 431, 215);
		panel_1.add(scrollPane);

		tabelaProdutos = new JTable();
		tabelaProdutos.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Endereço", "Nome", "Telefone", "Produto", "Validade" }));
		scrollPane.setViewportView(tabelaProdutos);

	}

	protected void buscarProduto() throws ClassNotFoundException, SQLException {

		ProdutoDao dao = new ProdutoDao();

		List<Cliente> produtosEncontrados = new ArrayList<Cliente>();

		produtosEncontrados = dao.buscarProduto(textFieldNome.getText(), textFieldValidade.getText(), textFieldCliente.getText());

		DefaultTableModel modelo = new DefaultTableModel(
				new String[] { "Produto", "Ano de Validade" }, 0);

		for (int i = 0; i < produtosEncontrados.size(); i++) {

			Produto produto = produtosEncontrados.get(i);
	      			
			modelo.addRow(new String[] { produto.getNomeProduto(),produto.getCliente().getNome(),
					
					String.valueOf(String.valueOf(produto.getValidade())) });
		}

		tabelaProdutos.setModel(modelo);

	}

}
