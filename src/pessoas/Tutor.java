package pessoas;

import animais.*;
import servicos.Servico;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Tutor extends pessoas.Pessoa implements Comparable<Tutor>{
    private Set<Animal> animais;
    private String endereco;
    private Tratamento tratamento;

    public Tutor(String nome, String cpf, String telefone, String endereco, Tratamento tratamento) {
        super(nome, cpf, telefone);
        this.endereco = endereco;
        this.tratamento = tratamento;
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

    public void mostrarAnimais(){
        Gato gato = null;
        Cachorro cachorro = null;
        if (this.getAnimais().isEmpty()) {
            System.out.println("    - Nenhum animal cadastrado.");
        } else {
            String especie = "";
            int qtdAnimal = 1;
            for (Animal a : this.getAnimais()){
                if(qtdAnimal > 1){
                    System.out.println(); //separa animais por uma linha em branco entre eles
                }
                if(a instanceof Cachorro) {
                    especie = "Cachorro";
                    cachorro = (Cachorro) a;
                }
                if(a instanceof Gato) {
                    especie = "Gato";
                    gato = (Gato) a;
                }
                System.out.println("    - " + a.getNome() + " (" + especie + ")");
                System.out.println("    Sexo: " + a.getSexo());
                System.out.print("    Idade: ");
                a.mostrarIdade();
                System.out.println("    Raca: " + a.getRaca());
                System.out.println("    Pelagem: " + a.getPelagem());

                //para gatos
                if(especie.equalsIgnoreCase("Gato")) {
                    System.out.println("    Acesso à rua: " + gato.isAcessoARua());
                }

                //para cachorros
                if(especie.equalsIgnoreCase("Cachorro")) {
                    System.out.println(cachorro.getPorte());
                }

                qtdAnimal++;
            }
        }
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Tratamento getTratamento() {
        return tratamento;
    }

    public void setTratamento(Tratamento tratamento) {
        this.tratamento = tratamento;
    }
}
