package servicos;

import animais.Animal;
import pessoas.*;
import java.util.HashMap;
import java.time.LocalDateTime;

public class Vacinacao extends Servico implements Preco{
    private String nomeVacina;

    Vacinacao(LocalDateTime dataHora, Animal animal, Veterinario veterinario, String nomeVacina){
        super(dataHora, animal, veterinario);
        this.nomeVacina = nomeVacina;
        this.valorBase = TiposVacinas.vacinas.get(nomeVacina);
    }

    public double calcularPreco(Tutor tutor){
        if(tutor.isMensalista()){
            return this.valorBase*0.7; //desconto 30%
        }else{
            return this.valorBase;
        }
    }

    public String getNomeVacina(){
        return this.nomeVacina;
    }

    public String tipoServico(){
        return "Vacina " + this.nomeVacina;
    }

}
