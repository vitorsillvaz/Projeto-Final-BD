package dominio;

import java.util.List;

public class Cliente implements Comparable<Cliente>{

	@Override
	public String toString() {
		return nome + " ( " + endereco + " ) ";
	}

	private int id;
	private String nome;
	private String endereco;
	private String telefone;
    private Produto produto; // Criando a produto dentro de cliente


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getEndereco() {
		return endereco;
	}


	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}


	public String getTelefone() {
		return telefone;
	}


	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}


	@Override
	public int compareTo(Cliente c) {
		return nome.compareTo(c.getNome());
	}
	
	// Getters e Setteres de produto
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto droduto) {
		this.produto = produto;
	}


    
}
