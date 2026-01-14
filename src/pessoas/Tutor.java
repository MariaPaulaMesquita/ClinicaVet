package pessoas;

import animais.*;
import servicos.Servico;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Tutor extends pessoas.Pessoa implements Comparable<Tutor>{
    private Set<Animal> animais;
    private String endereco;
    private boolean mensalista;
    private Tutor tutor;

    public Tutor(String nome, String cpf, int telefone, String endereco, boolean mensalista) {
        super(nome, cpf, telefone);
        this.endereco = endereco;
        this.mensalista = mensalista;
        this.animais = new TreeSet<>();
    }

    public int compareTo(Tutor tutor){
        return this.nome.compareToIgnoreCase(tutor.nome);
    }

    public Set<Animal> getAnimais() {
        return animais;
    }

    public void adicionarAnimal(Animal animal) {
        animais.add(animal);//excecao pra n ser null?
        animal.setTutor(this);
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public boolean isMensalista() {
        return mensalista;
    }

    public void setMensalista(boolean mensalista) {
        this.mensalista = mensalista;
    }

}
