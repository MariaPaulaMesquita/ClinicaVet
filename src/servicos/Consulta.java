package servicos;
import java.time.LocalDateTime;
import animais.Animal;
import pessoas.Tratamento;
import pessoas.Veterinario;
import pessoas.Tutor;

public class Consulta extends Servico implements Preco{
    private double peso;
    private double temperatura;
    public Consulta(LocalDateTime dataHora, Animal animal, Veterinario veterinario, double peso, double temperatura) {
        super(dataHora, animal, veterinario); // valor base da consulta
        this.valorBase=120.0;
        this.peso=peso;
        this.temperatura=temperatura;
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

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) { //peso deve ser maior que 0 e menor que algum valor absurdo.
        this.peso = peso;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) { // a temperatura deve estar dentro de um limite aceitavel tipo 32 a 48 graus
        this.temperatura = temperatura;
    }
}
