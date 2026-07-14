package gerenciamentoBiblioteca;

import java.time.LocalDate;

public class Emprestimo {

    private Usuario usuario;
    private Emprestavel item;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucaoPrevista;
    private LocalDate dataDevolucaoReal;

    public Emprestimo(Usuario usuario, Emprestavel item, LocalDate dataEmprestimo) {
        this.usuario = usuario;
        this.item = item;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucaoPrevista = calcularDevolucao();
        this.dataDevolucaoReal = null;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Emprestavel getItem() {
        return item;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }

    public LocalDate getDataDevolucaoReal() {
        return dataDevolucaoReal;
    }

    public void setDataDevolucaoReal(LocalDate dataDevolucaoReal) {
        this.dataDevolucaoReal = dataDevolucaoReal;
    }

    public LocalDate calcularDevolucao() {
        return dataEmprestimo.plusDays(item.diasEmprestimo());
    }

    public boolean estaAtrasado() {
        if (dataDevolucaoReal == null) {
            return LocalDate.now().isAfter(dataDevolucaoPrevista);
        }
        return dataDevolucaoReal.isAfter(dataDevolucaoPrevista);
    }

    public double calcularMulta() { 
        if (!estaAtrasado()) {
            return 0.0;
        }

        LocalDate referencia;
        if (dataDevolucaoReal != null) {
            referencia = dataDevolucaoReal;
        } else {
            referencia = LocalDate.now();
        }

        int diasAtraso = dataDevolucaoPrevista.until(referencia).getDays();
        double valorPorDia = 2.0;
        return diasAtraso * valorPorDia;
    }
}