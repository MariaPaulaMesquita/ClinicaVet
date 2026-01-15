package cadastro;

import animais.Animal;
import pessoas.Tutor;
import servicos.Servico;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeSet;

public class CadastroAgendamentos {
    public Set<Servico> servicosAgendados = new TreeSet<>();

    //cadastro -------------------------------------------------------
    public void agendarServico(Servico servico){
        servicosAgendados.add(servico);
        servico.getVeterinario().adicionarAgendamento(servico);
        servico.getAnimal().getTutor().adicionarAgendamento(servico);
    }

    //mudanças -------------------------------------------------------
    public void cancelarServico(Servico servico){
        servico.cancelar();
    }

    public void alterarDataServico(Servico servico, LocalDateTime dataNova){
        servico.setDataHora(dataNova);// excecao neles pq tem q ver se o vet vai estar disponivel
    }

    //listagens -------------------------------------------------------

    public void listarAgendamentosDoTutor(Tutor tutor){
        double valorTotal = 0.0;
        for(Servico s : servicosAgendados){
            if (s.getAnimal().getTutor().equals(tutor)){
                System.out.println("Tutor: " + tutor.getNome());
                System.out.println("Serviços agendados: ");
                System.out.print(s.tipoServico() + " - ");
                s.mostrarDataHora();
                System.out.println(" - Animal: " + s.getAnimal().getNome());
                System.out.println("Valor: " + s.getValorBase());
                System.out.println("--------------------------");
                valorTotal += s.getValorBase();
            }
        }
        System.out.println("Valor Total: " + valorTotal);

    }
    public void listarAgendamentosDoAnimal(Animal a){
        double valorTotal = 0.0;
        for(Servico s : servicosAgendados){
            if (s.getAnimal().equals(a)){
                System.out.println("Animal: " + a.getNome() + " - Tutor: " + a.getTutor());
                System.out.println("Serviços agendados: ");
                System.out.print(s.tipoServico() + " - ");
                s.mostrarDataHora();
                valorTotal += s.getValorBase();
            } System.out.println("Valor Total: " + valorTotal);
        }
    }

    //TODO listar agendamentos de cada um



}
