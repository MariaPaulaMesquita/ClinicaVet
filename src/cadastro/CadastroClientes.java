package cadastro;

import animais.Animal;
import animais.Cachorro;
import animais.Gato;
import pessoas.Tutor;

import java.util.Set;
import java.util.TreeSet;

public class CadastroClientes {
    public Set<Tutor> tutores = new TreeSet<>() ;

    //cadastros -------------------------------------------------------
    public void cadastrarTutor(Tutor tutor){
        if(tutor == null){
            throw new TutorInvalidoException("Digite um tutor valido");}
       else tutores.add(tutor); //excecao neles
    }
    public void cadastrarAnimal(Animal animal, Tutor tutor){
        // tipo isso pode dar excecao mas n sei se faz sentido ter no codigo se n tiver so apaga essa linha msm if(!tutor.getAnimais().contains(animal)){
            //throw new AnimalInvalidoException("Animal desconhecido")
        if(animal == null){//se aquela primeira linha valer isso vira um else if
            throw new AnimalInvalidoException("Digite um animal valido");}
        else tutor.adicionarAnimal(animal); //excecao neles
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

            /*
            if (t.getAnimais().isEmpty()) {
                System.out.println("    - Nenhum animal cadastrado.");
            } else {
                String especie = "";
                int qtdAnimal = 1;
                for (Animal a : t.getAnimais()){
                    if(qtdAnimal > 1){
                        System.out.println(); //separa animais por uma linha em branco entre eles
                    }
                    if(a instanceof Cachorro) especie = "Cachorro";
                    if(a instanceof Gato) especie = "Gato";
                    System.out.println("    - " + a.getNome() + " (" + especie + ")");
                    System.out.println("    Sexo: " + a.getSexo());
                    System.out.print("    Idade: ");
                    a.mostrarIdade();
                    System.out.println("    Raca: " + a.getRaca());
                    System.out.println("    Pelagem: " + a.getPelagem());

                    qtdAnimal++;
                }

            }
            */
            System.out.println("---------------");
        }
    }


}
