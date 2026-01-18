package cadastro;

import animais.Animal;
import excecoes.ServicoInvalidoException;
import pessoas.Tutor;
import pessoas.Veterinario;
import servicos.Servico;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeSet;

public class CadastroAgendamentos {
    public static Set<Servico> servicosAgendados = new TreeSet<>();
    public static Set<Servico> getServicosAgendados(){
        return servicosAgendados;
    }
// Metodos para as exceções------------------------------------------
private static void verificarDisponibilidadeVeterinario(Servico servico) throws VeterinarioIndisponivelException{
     for(Servico s: servicosAgendados){
        if(s.getVeterinario().equals(servico.getVeterinario()){
           if(s.compareTo(servico)==-1 && s.getDataHoraFinal().isAfter(servico.getDataHoraInicio()){
               throw new VeterinarioIndisponivelException(O veterinario não esta disponivel);
               break;}}}}
    private static void verificarDisponibilidadeAnimal(Servico servico) throws AnimalIndisponivelException{
     for(Servico s: servicosAgendados){
        if(s.getAnimal().equals(servico.getAnimal()){
           if(s.compareTo(servico)==-1 && s.getDataHoraFinal().isAfter(servico.getDataHoraInicio()){
               throw new AnimalIndisponivelException(O seu animal ja tem uma consulta durante esse horario);
               break;}}}}
    

    
    //cadastro -------------------------------------------------------
    public static void agendarServico(Servico servico) throws VeterinarioIndisponivelException , AnimalIndisponivelException{
        if(servico==null){
            throw new ServicoInvalidoException("Digite um serviço valido");}
       verificarDisponibilidadeVeterinario(servico);
        verificarDisponibilidadeAnimal(servico);
         
        servicosAgendados.add(servico);
        servico.getVeterinario().adicionarAgendamento(servico);
        servico.getAnimal().getTutor().adicionarAgendamento(servico);
    }

    //mudanças -------------------------------------------------------
    public void cancelarServico(Servico servico){
        servico.cancelar();
    }

    public void alterarDataServico(Servico servico, LocalDateTime dataNovaInicio, LocalDateTime dataNovaFinal){
        if(servico == null || !servicosAgendados.contains(servico)){
            throw new ServicoInvalidoException("Seu serviço não tinha sido agendado previamente por favor agende-o");}
           Servico s = new Servico(//ajeitar os bichos dentro);
        verificarDisponibilidadeVeterinario(s);
         verificarDisponibilidadeAnimal(s);
        
        servico.setDataHoraInicio(dataNovaInicio);
           servico.setDataHoraFinal(dataNovaFinal);
        }
    }

    //listagens -------------------------------------------------------
    //TODO listar agendamentos de cada um
    public void listarAgendamentosDoTutor(Tutor tutor){
        System.out.println("---------------\nAGENDAMENTOS: \n---------------");
        double valorTotal = 0.0;
        for(Servico s : tutor.getAgendamentos()){
            System.out.println("Tutor: " + tutor.getNome());
            System.out.println("Serviços agendados: ");
            System.out.print(s.tipoServico() + " - ");
            s.mostrarDataHoraInicio();
            System.out.print(" até ");
            s.mostrarDataHoraFinal();
            System.out.println(" - Animal: " + s.getAnimal().getNome() + " - Veterinário: " + s.getVeterinario().getNome());
            System.out.println("Valor: " + s.getValorBase());
            System.out.println("---------------");
            valorTotal += s.getValorBase();
        }
        System.out.println("Valor Total: " + valorTotal);
        System.out.println("---------------");
    }

    public void listarAgendamentosDoAnimal(Animal a){
        System.out.println("---------------\nAGENDAMENTOS: \n---------------");
        double valorTotal = 0.0;
        for(Servico s : servicosAgendados){
            if (s.getAnimal().equals(a)){
                System.out.println("Animal: " + a.getNome() + " - Tutor: " + a.getTutor().getNome());
                System.out.println("Serviços agendados: ");
                System.out.print(s.tipoServico() + " - ");
                s.mostrarDataHoraInicio();
                System.out.print(" até ");
                s.mostrarDataHoraFinal();
                System.out.println(" - Veterinário: " + s.getVeterinario().getNome());
                System.out.println("---------------");
                valorTotal += s.getValorBase();
            }
            System.out.println("Valor Total: " + valorTotal);
            System.out.println("---------------");
        }
    }

    public void listarAgendamentosDoVeterinario(Veterinario vet){
        System.out.println("---------------\nAGENDAMENTOS: \n---------------");
        double valorTotal = 0.0;
        for(Servico s : vet.getAgendamentos()){
            System.out.println("Veterinário: " + vet.getNome());
            System.out.println("Serviços agendados: ");
            System.out.print(s.tipoServico() + " - ");
            s.mostrarDataHoraInicio();
            System.out.print(" até ");
            s.mostrarDataHoraFinal();
            System.out.println(" - Animal: " + s.getAnimal().getNome() + " - Tutor: " + s.getAnimal().getTutor().getNome());
            System.out.println("Valor: " + s.getValorBase());
            System.out.println("---------------");
            valorTotal += s.getValorBase();
        }
        System.out.println("Valor Total: " + valorTotal);
        System.out.println("---------------");
    }



}
