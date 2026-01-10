package pessoas;

import animais.*;
import java.util.ArrayList;
import java.util.List;

public class Tutor extends pessoas.Pessoa {
    private List<Animal> animais;
    private String endereco;
    private boolean mensalista;

    public Tutor(String nome, String cpf, int telefone, String endereco, boolean mensalista) {
        super(nome, cpf, telefone);
        this.endereco = endereco;
        this.mensalista = mensalista;
        this.animais = new ArrayList<>();
    }

    public List<Animal> getAnimais() {
        return animais;
    }

    public void adicionarAnimal(Animal animal) {
        animais.add(animal);
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
