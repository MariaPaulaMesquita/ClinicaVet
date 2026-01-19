package servicos;
import java.time.LocalDateTime;
import animais.Animal;
import pessoas.Veterinario;
import pessoas.Tutor;
import java.util.concurrent.atomic.AtomicLong;

public abstract class Servico implements Comparable<Servico> {
    private static final AtomicLong contador = new AtomicLong(0);
    private final long id;

    protected LocalDateTime dataHoraInicio;
    protected LocalDateTime dataHoraFinal;
    protected Animal animal;
    protected Veterinario veterinario;
    protected double valorBase;
    protected boolean cancelado;

    public Servico(LocalDateTime dataHoraInicio, LocalDateTime dataHoraFinal, Animal animal, Veterinario veterinario) {
        this.id = contador.incrementAndGet(); // ID único para cada serviço
        this.dataHoraInicio = dataHoraInicio;
        this.dataHoraFinal = dataHoraFinal;
        this.animal = animal;
        this.veterinario = veterinario;
        this.cancelado = false;
    }

    @Override
    public int compareTo(Servico servico) {
        // Primeiro compara por data/hora de início
        if (this.dataHoraInicio.isAfter(servico.dataHoraInicio)) return 1;
        if (this.dataHoraInicio.isBefore(servico.dataHoraInicio)) return -1;

        // Se as datas são iguais, compara por animal
        int comparacaoAnimal = this.animal.getNome().compareToIgnoreCase(servico.animal.getNome());
        if (comparacaoAnimal != 0) return comparacaoAnimal;

        // Se os animais são iguais, compara por veterinário
        int comparacaoVet = this.veterinario.getNome().compareToIgnoreCase(servico.veterinario.getNome());
        if (comparacaoVet != 0) return comparacaoVet;

        // Desempate final por ID único (garante que nunca retorna 0 para objetos diferentes)
        return Long.compare(this.id, servico.id);
    }

    public void cancelar(){
        this.cancelado = true;
    }

    public abstract String tipoServico();

    public abstract double calcularPreco(Tutor tutor);

    public void mostrarDataHoraInicio(){
        System.out.print("Dia: "+ getDataHoraInicio().getDayOfMonth() +"/" + getDataHoraInicio().getMonthValue() + ", Horário: " + getDataHoraInicio().getHour() + ":" + getDataHoraInicio().getMinute());
    }

    public void mostrarDataHoraFinal(){
        System.out.print("Dia: "+ getDataHoraFinal().getDayOfMonth() +"/" + getDataHoraFinal().getMonthValue() + ", Horário: " + getDataHoraFinal().getHour() + ":" + getDataHoraFinal().getMinute());
    }

    public boolean conflitaCom(LocalDateTime inicio, LocalDateTime fim) {
        return inicio.isBefore(this.dataHoraFinal) &&
                fim.isAfter(this.dataHoraInicio);
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

    public long getId() {
        return id;
    }
}