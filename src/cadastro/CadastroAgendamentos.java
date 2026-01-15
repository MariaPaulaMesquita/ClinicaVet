package cadastro;

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
    //TODO listar agendamentos de cada um



}
