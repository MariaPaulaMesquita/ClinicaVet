package servicos;

public class TipoExame {
    private String nome;
    private double precoBase;

    public TipoExame(String nome, double precoBase) {
        this.nome = nome;
        this.precoBase = precoBase;
    }

    public String getNome() {
        return nome;
    }

    public double getPrecoBase() {
        return precoBase;
    }
}
