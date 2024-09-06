public class Hospedagem {
    private int codChale;
    private String estado;
    private String dataInicio;
    private String dataFim;
    private int qtdePessoas;
    private double valorFinal;
    private int codCliente;
    private double desconto; // Novo campo

    public Hospedagem(int codChale, String estado, String dataInicio, String dataFim, int qtdePessoas, double valorFinal, int codCliente, double desconto) {
        this.codChale = codChale;
        this.estado = estado;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.qtdePessoas = qtdePessoas;
        this.valorFinal = valorFinal;
        this.codCliente = codCliente;
        this.desconto = desconto; // Novo campo
    }
}