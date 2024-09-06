public class Cliente {
    private String nome;
    private String rg;
    private String endereco;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
    private String nascimento;

    public Cliente(String nome, String rg, String endereco, String bairro, String cidade, String estado, String cep, String nascimento) {
        this.nome = nome;
        this.rg = rg;
        this.endereco = endereco;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.nascimento = nascimento;
    }
}