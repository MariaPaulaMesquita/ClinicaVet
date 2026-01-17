package servicos;
import java.time.LocalDateTime;
import animais.Animal;
import pessoas.Veterinario;

public abstract class Servico implements Comparable<Servico> {
    protected LocalDateTime dataHoraInicio;
    protected LocalDateTime dataHoraFinal;
    protected Animal animal;
    protected Veterinario veterinario;
    protected double valorBase;
    protected boolean cancelado;

    public Servico(LocalDateTime dataHoraInicio, LocalDateTime dataHoraFinal, Animal animal, Veterinario veterinario) {
        this.dataHoraInicio = dataHoraInicio;
        this.dataHoraFinal = dataHoraFinal;
        this.animal = animal;
        this.veterinario = veterinario;
        this.cancelado = false;
    }

    public int compareTo(Servico servico){
        if(this.dataHoraInicio.isAfter(servico.dataHoraInicio)) return 1;
        if(this.dataHoraInicio.isBefore(servico.dataHoraInicio)) return -1;
        else return 0;
    }

    public void cancelar(){
        this.cancelado = true;
    }

    public abstract String tipoServico();

    public void mostrarDataHoraInicio(){
        System.out.print("Dia: "+ getDataHoraInicio().getDayOfMonth() +"/" + getDataHoraInicio().getMonthValue() + ", Horário: " + getDataHoraInicio().getHour() + ":" + getDataHoraInicio().getMinute());
    }
    public void mostrarDataHoraFinal(){
        System.out.print("Dia: "+ getDataHoraFinal().getDayOfMonth() +"/" + getDataHoraFinal().getMonthValue() + ", Horário: " + getDataHoraFinal().getHour() + ":" + getDataHoraFinal().getMinute());
    }

    //gets e sets
    public LocalDateTime getDataHoraInicio() {
        return dataHoraInicio;
    }

    public void setDataHoraInicio(LocalDateTime dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    public LocalDateTime getDataHoraFinal() {
        return dataHoraFinal;
    }

    public void setDataHoraFinal(LocalDateTime dataHoraFinal) {
        this.dataHoraFinal = dataHoraFinal;
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
