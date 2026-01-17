package servicos;

import animais.Animal;
import pessoas.Tratamento;
import pessoas.Veterinario;
import pessoas.Tutor;
import java.time.LocalDateTime;

public class Exame extends Servico implements Preco{
    private String nomeExame;

    Exame(LocalDateTime dataHoraInicio, LocalDateTime dataHoraFinal, Animal animal, Veterinario veterinario, String nomeExame){
        super(dataHoraInicio, dataHoraFinal, animal, veterinario);
        this.nomeExame = nomeExame;
        this.valorBase = TiposExames.exames.get(nomeExame);
    }

    public double calcularPreco(Tutor tutor){
        if(tutor.getTratamento() == Tratamento.MENSALISTA){
            return this.valorBase*0.75; //desconto 25%
        }else{
            return this.valorBase;
        }
    }

    public String getNomeExame(){
        return this.nomeExame;
    }

    public String tipoServico(){
        return "Exame - " + this.nomeExame;
    }

}
