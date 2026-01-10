public class Cachorro extends Animal{
    private String porte;

    public Cachorro(String nome, int idadeMeses, String raca, String pelagem, String porte){
        super(nome, idadeMeses, raca, pelagem);
        this.porte = porte;
    }

    public String getPorte() {
        return porte;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }

    public String calcularFaixaEtaria(){
        String faixa;
        if(this.idadeMeses<12) faixa = "Filhote";
        else if (this.idadeMeses<12*6) faixa = "Adulto";
        else faixa = "Idoso";

        return faixa;
    }

}
