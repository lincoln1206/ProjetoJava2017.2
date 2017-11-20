package bancoDados;

public class Livro {
	
	int codigo;
    String titulo;
	String editora;
	String autor;
	String ano;
	int ano_;

	public Livro() {

	}
	
	public Livro(String ano) {
		super();
		this.ano = ano;
	}

	public Livro(int codigo, String titulo, String editora, String autor, int ano_) {
		super();
		this.codigo = codigo;
		this.titulo = titulo;
		this.editora = editora;
		this.autor = autor;
		this.ano_ = ano_;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
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

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}
	
	public String toString() {
		return ano;
	}
	
	public int getAno_() {
		return ano_;
	}

	public void setAno_(int ano_) {
		this.ano_ = ano_;
	}
	
}
