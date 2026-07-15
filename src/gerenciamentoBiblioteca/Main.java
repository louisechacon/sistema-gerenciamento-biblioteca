package gerenciamentoBiblioteca;

import java.util.List;
import java.util.Scanner;

public class Main {

    static GerenciadorBiblioteca gerenciador = new GerenciadorBiblioteca();

    public static void main(String[] args) {

        System.out.println("\nIniciando Menu.\n");

        int opcao;
        do {
            exibirMenu();
            opcao = lerOpcaoInt();
            switch (opcao) {
                case 1 -> cadastrarLivro();
                case 2 -> cadastrarRevista();
                case 3 -> cadastrarUsuario();
                case 4 -> consultarLivro();
                case 5 -> consultarRevista();
                case 6 -> consultarUsuario();
                case 7 -> verificarDisponibilidade();
                case 8 -> listarLivrosEmprestados();
                case 9 -> listarRevistasEmprestadas();
                case 10 -> realizarEmprestimo();
                case 0 -> System.out.println("Até mais!");
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
            System.out.println();
        } while (opcao != 0);
    }

    static void exibirMenu() {
        System.out.println("Menu da Biblioteca");
        System.out.println("\nEscolha uma opção:");
        System.out.println("1  - Cadastrar livro");
        System.out.println("2  - Cadastrar revista");
        System.out.println("3  - Cadastrar usuário");
        System.out.println("4  - Consultar livro");
        System.out.println("5  - Consultar revista");
        System.out.println("6  - Consultar usuário");
        System.out.println("7  - Verificar disponibilidade de item");
        System.out.println("8  - Verificar livros emprestados atualmente");
        System.out.println("9  - Verificar revistas emprestadas atualmente");
        System.out.println("10 - Realizar empréstimo");
        System.out.println("0  - Sair");
    }

    static int lerOpcaoInt() {
        Scanner input = new Scanner(System.in);
        System.out.print("Opção: ");
        int valor = Integer.parseInt(input.nextLine());
        return valor;
    }

    
    static void cadastrarLivro() {
        Scanner input = new Scanner(System.in);

        System.out.print("Título: ");
        String titulo = input.nextLine();
        System.out.print("Código: ");
        String codigo = input.nextLine();
        System.out.print("Gênero: ");
        String genero = input.nextLine();
        System.out.print("Nome do(a) autor(a): ");
        String nomeAutor = input.nextLine();
        System.out.print("Nacionalidade do autor(a): ");
        String nacionalidadeAutor = input.nextLine();

        Autor autor = new Autor(nomeAutor, nacionalidadeAutor);
        Livro livro = new Livro(titulo, codigo, genero, autor);
        gerenciador.cadastrarLivro(livro);
        System.out.println("Livro cadastrado com sucesso!");
    }


    static void cadastrarRevista() {
        Scanner input = new Scanner(System.in);

        System.out.print("Título: ");
        String titulo = input.nextLine();
        System.out.print("Código: ");
        String codigo = input.nextLine();
        System.out.print("Periodicidade (semanal, quinzenal, mensal...): ");
        String periodicidade = input.nextLine();

        Revista revista = new Revista(titulo, codigo, periodicidade);
        gerenciador.cadastrarRevista(revista);
        System.out.println("Revista cadastrada com sucesso!");
    }


    static void cadastrarUsuario() {
        Scanner input = new Scanner(System.in);

        System.out.print("Nome: ");
        String nome = input.nextLine();
        System.out.print("ID: ");
        String id = input.nextLine();

        Usuario usuario = new Usuario(nome, id);
        gerenciador.cadastrarUsuario(usuario);
        System.out.println("Usuário cadastrado com sucesso!");
    }


    static void consultarLivro() {
        Scanner input = new Scanner(System.in);

        System.out.print("Título do livro: ");
        String titulo = input.nextLine();
        try {
            Livro livro = gerenciador.consultarLivro(titulo);
            System.out.println(livro.exibirDados());
        } catch (ItemNaoCadastradoException e) {
            System.out.println(e.getMessage());
        }
    }


    static void consultarRevista() {
        Scanner input = new Scanner(System.in);

        System.out.print("Título da revista: ");
        String titulo = input.nextLine();
        try {
            Revista revista = gerenciador.consultarRevista(titulo);
            System.out.println(revista.exibirDados());
        } catch (ItemNaoCadastradoException e) {
            System.out.println(e.getMessage());
        }
    }


    static void consultarUsuario() {
        Scanner input = new Scanner(System.in);

        System.out.print("Nome do usuário: ");
        String nome = input.nextLine();
        try {
            Usuario usuario = gerenciador.consultarUsuario(nome);
            System.out.println("Usuário: " + usuario.getNome() + " | ID: " + usuario.getId());
        } catch (UsuarioNaoCadastradoException e) {
            System.out.println(e.getMessage());
        }
    }


    static void verificarDisponibilidade() {
        Scanner input = new Scanner(System.in);

        System.out.print("Você deseja verificar a disponibilidade de qual dessas opções: livro ou revista? ");
        String tipo = input.nextLine();
        System.out.print("Informe o título do item: ");
        String titulo = input.nextLine();

        try {
            Emprestavel item;
            if (tipo.equalsIgnoreCase("livro")) {
                item = gerenciador.consultarLivro(titulo);
            } else {
                item = gerenciador.consultarRevista(titulo);
            }

            boolean disponivel = gerenciador.itemEstaDisponivel(item);
            if (disponivel) {
                System.out.println("O item está disponível.");
            } else {
                System.out.println("O item está emprestado no momento.");
            }

        } catch (ItemNaoCadastradoException e) {
            System.out.println(e.getMessage());
        }
    }


    static void listarLivrosEmprestados() {
        List<Livro> livros = gerenciador.listarLivrosEmprestados();
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro emprestado no momento.");
        } else {
            for (Livro livro : livros) {
                System.out.println(livro.exibirDados());
            }
        }
    }


    static void listarRevistasEmprestadas() {
        List<Revista> revistas = gerenciador.listarRevistasEmprestadas();
        if (revistas.isEmpty()) {
            System.out.println("Nenhuma revista emprestada no momento.");
        } else {
            for (Revista revista : revistas) {
                System.out.println(revista.exibirDados());
            }
        }
    }


    static void realizarEmprestimo() {
        Scanner input = new Scanner(System.in);

        System.out.print("Nome do usuário: ");
        String usuarioNome = input.nextLine();
        System.out.print("O item é um livro ou é uma revista? ");
        String tipoItem = input.nextLine();
        System.out.print("Informe o título do item: ");
        String itemTitulo = input.nextLine();
        System.out.print("Digite a data de empréstimo na ordem: ano-mês-dia no formato AAAA-MM-DD: ");
        String dataEmprestimo = input.nextLine();

        try {
            Emprestimo emprestimo = gerenciador.realizarEmprestimo(usuarioNome, itemTitulo, tipoItem, dataEmprestimo);
            System.out.println("Empréstimo realizado com sucesso! Devolução prevista: " + emprestimo.getDataDevolucaoPrevista());
        } catch (UsuarioNaoCadastradoException | ItemNaoCadastradoException | ItemJaEmprestadoException e) {
            System.out.println(e.getMessage());
        }
    }
}