package tela;

import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import banco.ClienteDao;
import banco.ProdutoDao;
import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.SortedList;
import ca.odell.glazedlists.swing.AutoCompleteSupport;
import dominio.Cliente;
import dominio.Produto;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class CadastrarProduto extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNome;
	private JTextField textFieldValidade;
    private JComboBox comboBoxCliente;
    
	private SortedList<Cliente> clientesSugeridos = new SortedList<Cliente>(new BasicEventList<Cliente>());
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastrarProduto frame = new CadastrarProduto();
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
	public CadastrarProduto() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 153, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setBounds(59, 59, 46, 14);
		contentPane.add(lblNewLabel);

		JLabel lblCargaHorria = new JLabel("Ano de Validade:");
		lblCargaHorria.setBounds(35, 113, 107, 14);
		contentPane.add(lblCargaHorria);

		JLabel lblAluno = new JLabel("Cliente:");
		lblAluno.setBounds(59, 166, 83, 14);
		contentPane.add(lblAluno);

		textFieldNome = new JTextField();
		textFieldNome.setBounds(140, 56, 184, 20);
		contentPane.add(textFieldNome);
		textFieldNome.setColumns(10);

		textFieldValidade = new JTextField();
		textFieldValidade.setBounds(140, 110, 184, 20);
		contentPane.add(textFieldValidade);
		textFieldValidade.setColumns(10);

		comboBoxCliente = new JComboBox();
		comboBoxCliente.setEditable(true);
		comboBoxCliente.setBounds(140, 162, 184, 22);
		contentPane.add(comboBoxCliente);

		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadastrarProduto();
			}
		});
		btnNewButton.setBounds(160, 215, 152, 23);
		contentPane.add(btnNewButton);

		AutoCompleteSupport.install(comboBoxCliente, clientesSugeridos);
		
		JLabel lblNewLabel_1 = new JLabel("CADASTRAR PRODUTO");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(104, 21, 238, 23);
		contentPane.add(lblNewLabel_1);

		comboBoxCliente.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				try {
					buscaCliente();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		});		
		
	}

	protected void cadastrarProduto() {

		Produto produto = new Produto();
		produto.setNomeProduto(textFieldNome.getText());
		produto.setValidade(Integer.parseInt(textFieldValidade.getText()));

		Cliente c = (Cliente) comboBoxCliente.getSelectedItem();
		produto.setCliente(c);

		try {
			ProdutoDao dao = new ProdutoDao();
			dao.cadastrarProduto(produto);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro no Sistema");
			e.printStackTrace();
		}
	}

	protected void buscaCliente() throws ClassNotFoundException {
		if (comboBoxCliente.getEditor().getItem() != null
				&& comboBoxCliente.getEditor().getItem().toString().length() >= 3) {
			ClienteDao dao = new ClienteDao();
			List<Cliente> clientesEncontrados = new ArrayList<>();

			try {
				String nomeCliente = comboBoxCliente.getEditor().getItem().toString();
				clientesEncontrados = dao.buscarClientePeloNome(nomeCliente);

				clientesSugeridos.clear();
				clientesSugeridos.addAll(clientesEncontrados);

				comboBoxCliente.showPopup();
			} catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Erro no Sistema");
			}
		}
	}
}
