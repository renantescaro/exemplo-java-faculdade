package entidade;

public class Livro {
	private int id;
	private Escritor escritor;
	private Editora editora;
	private String nome;
	private String descricao;
	private String idioma;
	private int numeroPaginas;
	private long ISBN;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Escritor getEscritor() {
		return escritor;
	}
	public void setEscritor(Escritor escritor) {
		this.escritor = escritor;
	}
	public Editora getEditora() {
		return editora;
	}
	public void setEditora(Editora editora) {
		this.editora = editora;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String descricao) {
		this.nome = descricao;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getIdioma() {
		return idioma;
	}
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	public int getNumeroPaginas() {
		return numeroPaginas;
	}
	public void setNumeroPaginas(int numeroPaginas) {
		this.numeroPaginas = numeroPaginas;
	}
	public long getISBN() {
		return ISBN;
	}
	public void setISBN(long iSBN) {
		ISBN = iSBN;
	}
}
