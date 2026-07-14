package gerenciamentoBiblioteca;

public abstract class ItemBiblioteca implements Emprestavel {

    private String titulo;
    private String codigo;

    public ItemBiblioteca(String titulo, String codigo) {
        this.titulo = titulo;
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getCodigo() {
        return codigo;
    }
}