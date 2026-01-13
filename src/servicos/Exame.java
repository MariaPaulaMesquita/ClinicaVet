package servicos;

import animais.Animal;
import pessoas.*;
import java.util.HashMap;
import java.time.LocalDateTime;

public class Exame extends Servico implements Preco{
    private String nomeExame;

    Exame(LocalDateTime dataHora, Animal animal, Veterinario veterinario, String nomeExame){
        super(dataHora, animal, veterinario);
        this.nomeExame = nomeExame;
        this.valorBase = TiposExames.exames.get(nomeExame);
    }

    public double calcularPreco(Tutor tutor){
        if(tutor.isMensalista()){
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
