package gerenciamentoBiblioteca;

public class ItemJaEmprestadoException extends BibliotecaException {

    public ItemJaEmprestadoException() {
        super("Item já emprestado");
    }
}