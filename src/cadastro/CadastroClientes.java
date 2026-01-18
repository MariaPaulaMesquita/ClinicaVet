package cadastro;

import animais.Animal;
import animais.Cachorro;
import animais.Gato;
import excecoes.AnimalInvalidoException;
import excecoes.TutorInvalidoException;
import pessoas.Tutor;

import java.util.Set;
import java.util.TreeSet;

public class CadastroClientes {
    public static Set<Tutor> tutores = new TreeSet<>() ;
    public static Set<Tutor> getTutores(){
        return tutores;
    }
    //cadastros -------------------------------------------------------
    public static void cadastrarTutor(Tutor tutor){
        if(tutor == null){
            throw new TutorInvalidoException("Digite um tutor valido");}
       else tutores.add(tutor);
    }
    public static void cadastrarAnimal(Animal animal, Tutor tutor){
        // tipo isso pode dar excecao mas n sei se faz sentido ter no codigo se n tiver so apaga essa linha msm if(!tutor.getAnimais().contains(animal)){
            //throw new AnimalInvalidoException("Animal desconhecido")
        if(animal == null){//se aquela primeira linha valer isso vira um else if
            throw new AnimalInvalidoException("Digite um animal valido");}
        else tutor.adicionarAnimal(animal);
    }

    public static Set<Animal> getTodosAnimais(){
        Set<Animal> todosAnimais = new TreeSet<>();
        for(Tutor t : tutores){
            Set<Animal> animals = t.getAnimais();
            todosAnimais.addAll(animals);
        }
        return todosAnimais;
    }

    public static Animal pesquisarAnimal(String nome){
        for(Animal a : getTodosAnimais()){
            String animal = a.getNome()+" | "+a.getTutor().getNome()+" | "+a.getID();
            if(animal.equals(nome)){
                return a;
            }
        }
        return null;
    }

    public static Tutor pesquisarTutor(String nome){
        for(Tutor t : getTutores()){
            String animal = t.getNome()+" | "+t.getCpf();
            if(animal.equals(nome)){
                return t;
            }
        }
        return null;
    }

    //listagens -------------------------------------------------------
    public void listarTutores(){
        System.out.println("---------------\nTUTORES: \n---------------");
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

            t.mostrarAnimais();

            System.out.println("---------------");
        }
    }


}
