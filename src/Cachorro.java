public class Cachorro extends Animal {
    private String porte;

    public Cachorro(String nome, int idadeMeses, String raca, String especie, String pelagem, String porte) {
        super(nome, idadeMeses, raca, especie, pelagem);
        this.porte = porte;
    }

    public String getPorte() {
        return porte;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }

    @Override
    public String calcularFaixaEtaria() {
        if (idadeMeses<12){
        return "filhote";}
        else if (idadeMeses>= 12 && idadeMeses<=(12*6)){
            return "adulto jovem";
        }
        else if (idadeMeses<= (12*11) && idadeMeses>(12*6)){
            return "adulto";}
        else return "idoso";
    }
}
