package animais;

import excecoes.IdadeInvalidaException;
import java.time.Period;

public class Cachorro extends Animal {
    private String porte;

    public Cachorro(String nome, String sexo, int dia,  int mes, int ano, String raca, String pelagem, String porte) {
        super(nome, sexo, dia, mes, ano, raca, pelagem);
        this.porte = porte;
    }
    public Cachorro(String nome, String sexo, int mes, int ano, String raca, String pelagem, String porte) {
        super(nome, sexo, mes, ano, raca, pelagem);
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
        Period p = getIdade();
        int anos = p.getYears();
        if (anos<1){
        return "filhote";}
        else if (anos<2){
            return "adolescente";
        }
        else if (anos< 7){
            return "adulto";}
        else if (anos>= 7 && anos < 40){
            return "senior";}
        else throw new IdadeInvalidaException("Idade invalida"); //A mensagem caso alguem queira mudar ta de boas e so que eu n sei muito oq colocar de mensagem pra esse caso kk
    }
}
