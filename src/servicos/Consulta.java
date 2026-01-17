package servicos;
import java.time.LocalDateTime;
import animais.Animal;
import pessoas.Tratamento;
import pessoas.Veterinario;
import pessoas.Tutor;

public class Consulta extends Servico implements Preco{
    public Consulta(LocalDateTime dataHoraInicio, Animal animal, Veterinario veterinario) {
        super(dataHoraInicio, dataHoraInicio.plusMinutes(20), animal, veterinario); // valor base da consulta
        this.valorBase = 120.0;
    }

    @Override
    public double calcularPreco(Tutor tutor) {
        if (tutor.getTratamento() == Tratamento.MENSALISTA){
            return getValorBase()*0.7; // Desconto de 30%
        }
        else return getValorBase();
    }

    @Override
    public String tipoServico() {
        return "Consulta";
    }

}
