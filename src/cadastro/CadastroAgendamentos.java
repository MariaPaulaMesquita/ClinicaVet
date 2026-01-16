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
        if(servico==null){
            throw new ServicoInvalidoException ("Digite um serviço valido");}
       else servicosAgendados.add(servico);
        servico.getVeterinario().adicionarAgendamento(servico);//excecao pra vet indisponivel
        servico.getAnimal().getTutor().adicionarAgendamento(servico);//excecao pra animal indisponivel
    }

    //mudanças -------------------------------------------------------
    public void cancelarServico(Servico servico){
        servico.cancelar();
    }

    public void alterarDataServico(Servico servico, LocalDateTime dataNova){
        if(servico == null || !servicosAgendados.contains(servico)){
            throw new ServicoInvalidoException("Seu serviço não tinha sido agendado previamente por favor agende-o");}
       // to meio na duvida de como eu checaria o horario pq eu precisaria comparar com os elementos da lista else if(servico.getVeterinario()
       else servico.setDataHora(dataNova);// excecao neles pq tem q ver se o vet e o animal vai estar disponivel
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
