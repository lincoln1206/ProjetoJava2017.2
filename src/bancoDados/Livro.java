package bancoDados;

public class Livro {
	
	int isbn;
    String titulo;
	String editora;
	int ano;
	
	public Livro() {

	}
	
	public Livro(int isbn, String titulo, String editora, int ano) {
		super();
		this.isbn = isbn;
		this.titulo = titulo;
		this.editora = editora;
		this.ano = ano;
	}

	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}
	
}
