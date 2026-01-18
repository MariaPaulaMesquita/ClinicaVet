package cadastro;

import animais.Animal;
import excecoes.*;
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

    private static void verificarDisponibilidadeVeterinario(Servico servico, LocalDateTime inicio, LocalDateTime fim) throws VeterinarioIndisponivelException {
        for (Servico s : servicosAgendados) {
            if (s == servico) continue;
            if (s.getVeterinario().equals(servico.getVeterinario()) && s.conflitaCom(inicio, fim)) {
             throw new VeterinarioIndisponivelException("O veterinário não está disponível nesse horário");
            }
        }
    }

    private static void verificarDisponibilidadeAnimal(Servico servico, LocalDateTime inicio, LocalDateTime fim) throws AnimalIndisponivelException {
        for (Servico s : servicosAgendados) {
            if (s == servico) continue;
            if (s.getAnimal().equals(servico.getAnimal()) && s.conflitaCom(inicio, fim)) { throw new AnimalIndisponivelException("O animal já possui um serviço nesse horário");
            }
        }
    }


    //cadastro -------------------------------------------------------
    public static void agendarServico(Servico servico) throws VeterinarioIndisponivelException, AnimalIndisponivelException {
        if (servico == null) throw new ServicoInvalidoException("Serviço inválido");
        verificarDisponibilidadeVeterinario(servico, servico.getDataHoraInicio(), servico.getDataHoraFinal());
        verificarDisponibilidadeAnimal(servico, servico.getDataHoraInicio(), servico.getDataHoraFinal());

        servicosAgendados.add(servico);
        servico.getVeterinario().adicionarAgendamento(servico);
        servico.getAnimal().getTutor().adicionarAgendamento(servico);
    }


    //mudanças -------------------------------------------------------
    public void cancelarServico(Servico servico){
        servico.cancelar();
    }

    public void alterarDataServico(Servico servico, LocalDateTime novoInicio, LocalDateTime novoFim) throws VeterinarioIndisponivelException, AnimalIndisponivelException {
        if (servico == null || !servicosAgendados.contains(servico)) throw new ServicoInvalidoException("Serviço não estava previamente agendado");
        verificarDisponibilidadeVeterinario(servico, novoInicio, novoFim);
        verificarDisponibilidadeAnimal(servico, novoInicio, novoFim);
        servico.setDataHoraInicio(novoInicio);
        servico.setDataHoraFinal(novoFim);
    }


    //listagens -------------------------------------------------------

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
