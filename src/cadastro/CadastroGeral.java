package cadastro;

import animais.*;
import pessoas.*;
import servicos.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeSet;

public class CadastroGeral { //to na dúvida nos static
    public static Set<Tutor> tutores = new TreeSet<>() ;
    public static Set<Veterinario> veterinarios = new TreeSet<>();
    public static Set<Servico> servicosAgendados = new TreeSet<>();

    //cadastros -------------------------------------------------------
    public static void cadastrarTutor(Tutor tutor){
        tutores.add(tutor); //excecao neles
    }
    public static void cadastrarAnimal(Animal animal, Tutor tutor){
        tutor.adicionarAnimal(animal); //excecao neles
    }
    public static void cadastrarVeterinario(Veterinario vet) {
        veterinarios.add(vet); //excecao neles
    }

    //buscas -------------------------------------------------------
    public Set<Animal> buscarAnimaisTutor(Tutor tutor){
        return tutor.getAnimais();
    }

    public Set<Servico> buscarServicosVet (Veterinario vet){
        return vet.getAgendamentos();
    }

    public Set<Servico> buscarServicosAnimal (Animal animal){
        return animal.getAgendamentos();
    }

    //servicos -------------------------------------------------------
    public void agendarServico(Servico servico){
        servicosAgendados.add(servico);
        servico.getVeterinario().adicionarAgendamento(servico);
        servico.getAnimal().adicionarAgendamento(servico);
        servico.getAnimal().getTutor().adicionarAgendamento(servico);
    }//essa parte ta dando errado dps q o jp botou as excecoes e eu n to entendendo o q ta errado, mas n acho q o problema seja as excecoes

    public void cancelarServico(Servico servico){
        servico.cancelar();
    }

    public void alterarDataServico(Servico servico, LocalDateTime dataNova){
        servico.setDataHora(dataNova);// excecao neles pq tem q ver se o vet vai estar disponivel
    }

}
