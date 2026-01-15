package animais;

import excecoes.NomeInvalidoException;
import pessoas.Tutor;
import servicos.Servico;
import java.util.Set;
import java.util.TreeSet;
import java.time.LocalDate;
import java.time.Period;

public abstract class Animal implements Comparable<Animal> {
    protected String nome;
    protected LocalDate dataNascimento;
    protected String raca;
    protected String pelagem;
    protected Tutor tutor;
    protected Set<Servico> agendamentos;
    //fazer uma lista de agendamentos e quando precisar percorrer a lista e achar os agendamentos do animal

    // quando só sabe mês e ano
    public Animal(String nome, int mes, int ano, String raca, String pelagem) {
        this.nome = nome;
        this.dataNascimento = LocalDate.of(ano, mes, 1); //ele assume dia 1.
        this.raca = raca;
        this.pelagem = pelagem;
        this.agendamentos = new TreeSet<>();
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

    public Period getIdade() {
        return Period.between(dataNascimento, LocalDate.now());
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if(nome==null || nome ==""){
            throw new NomeInvalidoException("Digite um nome valido");}
        else this.nome = nome;// necessario? 
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

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public Set<Servico> getAgendamentos() {
        return agendamentos;
    }

    public void adicionarAgendamento(Servico servico){
        this.agendamentos.add(servico);
    }
}
