package servicos;

import animais.*;
import pessoas.Tutor;
import pessoas.Veterinario;

import java.time.LocalDateTime;

public class Cirurgia extends Servico implements Preco{
    private String nomeCirurgia;
    public Cirurgia(LocalDateTime dataHora, Animal animal, Veterinario veterinario, String nomeCirurgia){
        super(dataHora, animal, veterinario);
        this.nomeCirurgia=nomeCirurgia;
        String especie= descobrirEspecie(animal);
        this.valorBase = TiposCirurgia.getPreco(especie, nomeCirurgia);

    }
    public Cirurgia(LocalDateTime dataHora, Animal animal, Veterinario veterinario, String nomeCirurgia, double valorBase){
        super(dataHora, animal, veterinario);
        this.nomeCirurgia=nomeCirurgia;
        this.valorBase = valorBase;

    }
    private String descobrirEspecie(Animal animal) {
        if (animal instanceof Gato) {
            return "Gato";
        }
        if (animal instanceof Cachorro) {
            return "Cachorro";
        }
        throw new IllegalArgumentException("Espécie não suportada");
    }

    @Override
    public double calcularPreco(Tutor tutor) {
        if(tutor.isMensalista()){
            return this.valorBase*0.5; //desconto 25%
        }else{
            return this.valorBase;
        }
    }

    @Override
    public String tipoServico() {
        return nomeCirurgia + " em " + descobrirEspecie(animal);
    }
}
