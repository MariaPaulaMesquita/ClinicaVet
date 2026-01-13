package cadastro;
import animais.*;
import pessoas.*;
import servicos.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class CadastroGeral { //to na dúvida nos static
    public static Set<Tutor> tutores = new TreeSet<>() ;
    public static Set<Veterinario> veterinarios = new TreeSet<>();
    public static Set<Servico> servicosAgendados = new TreeSet<>();

    public static void cadastrarTutor(Tutor tutor){
        tutores.add(tutor);
    }
     public static void cadastrarAnimal(Animal animal, Tutor tutor){
        tutor.adicionarAnimal(animal);
     }

     public static void cadastrarVeterinario(Veterinario veterinario){
        veterinarios.add(veterinario);
     }

}
