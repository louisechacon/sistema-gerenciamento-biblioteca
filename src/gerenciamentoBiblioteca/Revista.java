package gerenciamentoBiblioteca;

public class Revista extends ItemBiblioteca {

    private String periodicidade;

    public Revista(String titulo, String codigo, String periodicidade) {
        super(titulo, codigo);
        this.periodicidade = periodicidade;
    }

    public String getPeriodicidade() {
        return periodicidade;
    }

    @Override
    public int diasEmprestimo() {
        return 7;
    }

    @Override
    public String exibirDados() {
        return "Revista: " + getTitulo() + " | Periodicidade: " + periodicidade + " | Código: " + getCodigo();
    }
}