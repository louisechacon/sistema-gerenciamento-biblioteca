package gerenciamentoBiblioteca;

public class ItemNaoCadastradoException extends BibliotecaException {

    public ItemNaoCadastradoException() {
        super("Item não cadastrado");
    }
}