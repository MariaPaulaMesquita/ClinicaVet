package pessoas;

import servicos.Servico;
import java.util.Set;
import java.util.TreeSet;

public abstract class Pessoa {
    protected String nome;
    protected String cpf;
    protected int telefone;
    private Set<Servico> agendamentos;

    public Pessoa(String nome, String cpf, int telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.agendamentos = new TreeSet<>();
    }

    public String getNome() { return nome; }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public Set<Servico> getAgendamentos() {
        return agendamentos;
    }

    public void adicionarAgendamento(Servico servico){
        this.agendamentos.add(servico);
    }
}
