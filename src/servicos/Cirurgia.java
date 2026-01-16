package servicos;

import animais.*;
import pessoas.Tratamento;
import pessoas.Tutor;
import pessoas.Veterinario;

import java.time.LocalDateTime;

public class Cirurgia extends Servico implements Preco{
    private String nomeCirurgia;
    public Cirurgia(LocalDateTime dataHora, Animal animal, Veterinario veterinario, String nomeCirurgia){
        super(dataHora, animal, veterinario);
        this.nomeCirurgia = nomeCirurgia;
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
        else if (animal instanceof Cachorro) {
            return "Cachorro";
        }
        else throw  new AnimalInvalidoException("Espécie não suportada");
    }

    @Override
    public double calcularPreco(Tutor tutor) {
        if(tutor.getTratamento() == Tratamento.MENSALISTA){
            return this.valorBase*0.5; //desconto 50%
        }else{
            return this.valorBase;
        }
    }

    @Override
    public String tipoServico() {
        return nomeCirurgia + " em " + descobrirEspecie(animal);
    }
}
