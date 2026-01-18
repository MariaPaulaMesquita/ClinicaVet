package animais;

import excecoes.NomeInvalidoException;
import pessoas.Tutor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public abstract class Animal implements Comparable<Animal> {
    protected static int CriarID=0;
    protected int ID;
    protected String nome;
    protected LocalDate dataNascimento;
    protected String raca;
    protected String pelagem;
    protected Tutor tutor;
    protected String sexo;
    //fazer uma lista de agendamentos e quando precisar percorrer a lista e achar os agendamentos do animal

    // quando só sabe mês e ano
    public Animal(String nome, String sexo, int mes, int ano, String raca, String pelagem) {
        this.nome = nome;
        this.sexo = sexo;
        this.dataNascimento = LocalDate.of(ano, mes, 1); //ele assume dia 1.
        this.raca = raca;
        this.pelagem = pelagem;
        this.ID = CriarID;
        CriarID++;
    }

    // quando sabe dia, mês e ano
    public Animal(String nome, String sexo, int dia, int mes, int ano, String raca, String pelagem) {
        this.nome = nome;
        this.sexo = sexo;
        this.dataNascimento = LocalDate.of(ano, mes, dia);
        this.raca = raca;
        this.pelagem = pelagem;
        this.ID = CriarID;
        CriarID++;
    }
    public Animal(String nome, String sexo, LocalDate tempo, String raca, String pelagem) {
        this.nome = nome;
        this.sexo = sexo;
        this.dataNascimento = tempo;
        this.raca = raca;
        this.pelagem = pelagem;
        this.ID = CriarID;
        CriarID++;
    }


    public int compareTo(Animal animal){
        return this.nome.compareToIgnoreCase(animal.nome);
    }

    public abstract String calcularFaixaEtaria();

    public Period getIdade() {
        return Period.between(dataNascimento, LocalDate.now());
    }
    public void mostrarIdade() {
        Period p = getIdade();
        if (p.getYears() > 0) {
            System.out.println(p.getYears() + " anos " + "e " + p.getMonths() + " meses");
        } else System.out.println(p.getMonths() + " meses");
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if(nome==null || nome.isBlank()){
            throw new NomeInvalidoException("Digite um nome valido");}
        else this.nome = nome;// necessario? 
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
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

    public int getID(){return this.ID; }

    public void setID(int i){ this.ID=i; }

}
