package gerenciamentoBiblioteca;

public class Livro extends ItemBiblioteca {

    private String genero;
    private Autor autor;

    public Livro(String titulo, String codigo, String genero, Autor autor) {
        super(titulo, codigo);
        this.genero = genero;
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }
    
    public Autor getAutor() {
    	return autor;
    }

    @Override 
    public int diasEmprestimo() {
        return 14;
    }

    @Override
    public String exibirDados() {
        return "Livro: " + getTitulo() + " | Gênero: " + genero + " | Autor: " + autor.getNome() + " | Código: " + getCodigo();
    }
}