package gerenciamentoBiblioteca;

public class UsuarioNaoCadastradoException extends BibliotecaException {

    public UsuarioNaoCadastradoException() {
        super("Usuário não cadastrado");
    }
}