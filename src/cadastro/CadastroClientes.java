package cadastro;

import animais.Animal;
import pessoas.Tutor;

import java.util.Set;
import java.util.TreeSet;

public class CadastroClientes {
    public Set<Tutor> tutores = new TreeSet<>() ;

    //cadastros -------------------------------------------------------
    public void cadastrarTutor(Tutor tutor){
        tutores.add(tutor); //excecao neles
    }
    public void cadastrarAnimal(Animal animal, Tutor tutor){
        tutor.adicionarAnimal(animal); //excecao neles
    }

    //listagens -------------------------------------------------------
    public void listarTutores(){
        if (tutores.isEmpty()) {
            System.out.println("Nenhum tutor cadastrado.");
            return;
        }
        for (Tutor t : tutores){
            System.out.println("Tutor: " + t.getNome());
            System.out.println("CPF: " + t.getCpf());
            System.out.println("Telefone: " + t.getTelefone());
            System.out.println("Endereço: " + t.getEndereco());
            System.out.println("Animais cadastrados: ");
            if (t.getAnimais().isEmpty()) {
                System.out.println("Nenhum animal cadastrado.");
            } else {
                for (Animal a : t.getAnimais()){
                    System.out.println("- " + a.getNome());
                    System.out.println("Idade: ");
                    a.mostrarIdade();
                    System.out.println();
                }
            }
        }
    }


}
