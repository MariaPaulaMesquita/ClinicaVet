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
    //TODO listar clientes

}
