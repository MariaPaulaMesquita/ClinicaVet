package servicos;
import java.time.LocalDateTime;
import animais.Animal;
import pessoas.Veterinario;

public abstract class Servico {
    protected LocalDateTime dataHora;
    protected Animal animal;
    protected Veterinario veterinario;
    protected double valorBase;
    protected boolean cancelado;

    public Servico(LocalDateTime dataHora, Animal animal, Veterinario veterinario, double valorBase) {
        this.dataHora = dataHora;
        this.animal = animal;
        this.veterinario = veterinario;
        this.valorBase = valorBase;
        this.cancelado = false;
    }

    public void alterarData(LocalDateTime dataHora){ //nova data
        this.dataHora = dataHora; // atualiza a data
    }

    public void cancelar(){
        this.cancelado = true;
    }

    public abstract String tipoServico();

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }

    public double getValorBase() {
        return valorBase;
    }

    public void setValorBase(double valorBase) {
        this.valorBase = valorBase;
    }

    public boolean isCancelado() {
        return cancelado;
    }


}
