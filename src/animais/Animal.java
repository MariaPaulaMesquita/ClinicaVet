package animais;

import java.time.LocalDate;
import java.time.Period;

public abstract class Animal implements Comparable<Animal> {
    protected String nome;
    protected LocalDate dataNascimento;
    protected String raca;
    protected String pelagem;

    // quando só sabe mês e ano
    public Animal(String nome, int mes, int ano, String raca, String pelagem) {
        this.nome = nome;
        this.dataNascimento = LocalDate.of(ano, mes, 1); //ele assume dia 1.
        this.raca = raca;
        this.pelagem = pelagem;
    }

    // quando sabe dia, mês e ano
    public Animal(String nome, int dia, int mes, int ano, String raca, String pelagem) {
        this.nome = nome;
        this.dataNascimento = LocalDate.of(ano, mes, dia);
        this.raca = raca;
        this.pelagem = pelagem;
    }

    public int compareTo(Animal animal){
        return this.nome.compareToIgnoreCase(animal.nome);
    }

    public abstract String calcularFaixaEtaria();
    //TODO retorna classificacao conforme a idade (filhote, adulto...)

    public Period getIdade() {
        return Period.between(dataNascimento, LocalDate.now());
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getPelagem() { return pelagem; }

    public void setPelagem(String pelagem) {
        this.pelagem = pelagem;
    }
}
