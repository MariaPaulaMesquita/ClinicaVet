public class Gato extends Animal{
    private boolean acessoRua;

    public Gato(String nome, int idadeMeses, String raca, String pelagem, boolean acessoRua){
        super(nome, idadeMeses, raca, pelagem);
        this.acessoRua = acessoRua;
    }

    public boolean isAcessoRua() {
        return acessoRua;
    }

    public void setAcessoRua(boolean acessoRua) {
        this.acessoRua = acessoRua;
    }

    public String calcularFaixaEtaria() {
        String faixa;
        if (this.idadeMeses < 12) faixa = "Filhote";
        else if (this.idadeMeses < 12 * 8) faixa = "Adulto";
        else if (this.idadeMeses < 12 * 12) faixa = "Idoso";
        else faixa = "Geriatrico";

        return faixa;
    }

}
