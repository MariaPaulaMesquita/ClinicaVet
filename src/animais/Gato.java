package animais;

import java.time.Period;

public class Gato extends Animal{
    private boolean acessoARua;

    public Gato(String nome, int dia, int mes, int ano, String raca, String pelagem, boolean acessoARua) {
        super(nome, dia, mes, ano, raca, pelagem);
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
        Period p = getIdade();
        int anos = p.getYears();
        int meses = p.getMonths();

        if (anos<1 && meses<7){ // de 0 a 6 meses
            return "filhote";}
        else if (anos<3){ // de 7 meses a 2 anos
            return "jovem";
        }
        else if (anos<7){ //de 3 a 6 anos
            return "adulto jovem";
        }
        else if (anos<11){ // de 7 a 10 anos
            return "maduro";}
        else if (anos<15){ // de 11 a 14 anos
            return "senior";}
        else if (anos>=15 && anos<40 ){ // +15 e menor que uma idade que ultrapassa o limite de vida.
            return "geriátrico";
        }
        else throw IdadeInvalidaException("Idade invalida")//msm coisa da parte do cachorro
    }
}
