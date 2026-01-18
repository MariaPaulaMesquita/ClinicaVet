package servicos;

import animais.Animal;
import pessoas.*;
import java.time.LocalDateTime;

public class Vacinacao extends Servico implements Preco{
    private String nomeVacina;

    public Vacinacao(LocalDateTime dataHoraInicio, Animal animal, Veterinario veterinario, String nomeVacina){
        super(dataHoraInicio, dataHoraInicio.plusMinutes(10), animal, veterinario);
        this.nomeVacina = nomeVacina;
        this.valorBase = TiposVacinas.vacinas.get(nomeVacina);
    }

    public double calcularPreco(Tutor tutor){
        if(tutor.getTratamento() == Tratamento.MENSALISTA){
            return this.valorBase*0.7; //desconto 30%
        }else{
            return this.valorBase;
        }
    }

    public String getNomeVacina(){
        return this.nomeVacina;
    }

    public String tipoServico(){
        return "Vacina - " + this.nomeVacina;
    }

}
