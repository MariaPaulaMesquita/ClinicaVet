public class Gato extends Animal{
    private boolean acessoARua;

    public Gato(String nome, int idadeMeses, String raca, String especie, String pelagem, boolean acessoARua) {
        super(nome, idadeMeses, raca, especie, pelagem);
        this.acessoARua = acessoARua;
    }

    public boolean isAcessoARua() {
        return acessoARua;
    }

    public void setAcessoARua(boolean acessoARua) {
        this.acessoARua = acessoARua;
    }

    @Override
    public String calcularFaixaEtaria() {
        if (idadeMeses<12){
            return "filhote";}
        else if (idadeMeses<(12*8)){
            return "adulto";
        }
        else if (idadeMeses<(12*12)){
            return "idoso";}
        else return "geriátrico";
    }
}
