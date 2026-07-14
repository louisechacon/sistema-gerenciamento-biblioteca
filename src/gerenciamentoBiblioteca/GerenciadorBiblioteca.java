package gerenciamentoBiblioteca;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

public class GerenciadorBiblioteca {

    private Map<String, Livro> livros;
    private Map<String, Revista> revistas;
    private Map<String, Usuario> usuarios;
    private List<Emprestimo> emprestimos;

    public GerenciadorBiblioteca() {
        this.livros = new HashMap<>();
        this.revistas = new HashMap<>();
        this.usuarios = new HashMap<>();
        this.emprestimos = new ArrayList<>();
    }


    public void cadastrarLivro(Livro livro) {
        livros.put(livro.getTitulo(), livro);
    }

    public void deletarLivro(String titulo) throws ItemNaoCadastradoException {
        if (!livros.containsKey(titulo)) {
            throw new ItemNaoCadastradoException();
        }
        livros.remove(titulo);
    }

    public Livro consultarLivro(String titulo) throws ItemNaoCadastradoException {
        Livro livro = livros.get(titulo);
        if (livro == null) {
            throw new ItemNaoCadastradoException();
        }
        return livro;
    }


    public void cadastrarUsuario(Usuario usuario) {
        usuarios.put(usuario.getNome(), usuario);
    }

    public void deletarUsuario(String nome) throws UsuarioNaoCadastradoException {
        if (!usuarios.containsKey(nome)) {
            throw new UsuarioNaoCadastradoException();
        }
        usuarios.remove(nome);
    }

    public Usuario consultarUsuario(String nome) throws UsuarioNaoCadastradoException {
        Usuario usuario = usuarios.get(nome);
        if (usuario == null) {
            throw new UsuarioNaoCadastradoException();
        }
        return usuario;
    }


    public void cadastrarRevista(Revista revista) {
        revistas.put(revista.getTitulo(), revista);
    }

    public void deletarRevista(String titulo) throws ItemNaoCadastradoException {
        if (!revistas.containsKey(titulo)) {
            throw new ItemNaoCadastradoException();
        }
        revistas.remove(titulo);
    }

    public Revista consultarRevista(String titulo) throws ItemNaoCadastradoException {
        Revista revista = revistas.get(titulo);
        if (revista == null) {
            throw new ItemNaoCadastradoException();
        }
        return revista;
    }
    
    public List<Livro> listarLivrosEmprestados() {
    	
        List<Livro> livrosEmprestados = new ArrayList<>();
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getDataDevolucaoReal() == null && emprestimo.getItem() instanceof Livro livro) {
                livrosEmprestados.add(livro);
            }
        }
        return livrosEmprestados;
    }
    
    public List<Revista> listarRevistasEmprestadas() {
    	
        List<Revista> revistasEmprestadas = new ArrayList<>();
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getDataDevolucaoReal() == null && emprestimo.getItem() instanceof Revista revista) {
                revistasEmprestadas.add(revista);
            }
        }
        return revistasEmprestadas;
    }
    
    public boolean itemEstaDisponivel(Emprestavel item) {
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getItem().equals(item) && emprestimo.getDataDevolucaoReal() == null) {
                return false;
            }
        }
        return true;
    }

    public Emprestimo realizarEmprestimo(String usuarioNome, String itemTitulo, String tipoItem, String dataEmprestimo)
            throws UsuarioNaoCadastradoException, ItemNaoCadastradoException, ItemJaEmprestadoException {

        Usuario usuario = consultarUsuario(usuarioNome);

        Emprestavel item;
        if (tipoItem.equalsIgnoreCase("livro")) {
            item = consultarLivro(itemTitulo);
        } else {
            item = consultarRevista(itemTitulo);
        } 

        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getItem().equals(item) && emprestimo.getDataDevolucaoReal() == null) {
                throw new ItemJaEmprestadoException();
            }
        }

        LocalDate data = LocalDate.parse(dataEmprestimo);
        Emprestimo novoEmprestimo = new Emprestimo(usuario, item, data);
        emprestimos.add(novoEmprestimo);
        return novoEmprestimo;
    } 
}