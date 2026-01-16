package pessoas;

import excecoes.CpfInvalidoException;
import excecoes.NomeInvalidoException;
import servicos.Servico;
import java.util.Set;
import java.util.TreeSet;

public abstract class Pessoa {
    protected String nome;
    protected String cpf;
    protected String telefone;
    private Set<Servico> agendamentos;

    public Pessoa(String nome, String cpf, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.agendamentos = new TreeSet<>();
    }

    public String getNome() { return nome; }

    public void setNome(String nome) {
        if(nome==null || nome.isBlank()){
            throw new NomeInvalidoException("Digite um nome valido");}
        else this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if(cpf==null || cpf.isBlank()){
            throw new CpfInvalidoException("Digite um CPF valido");
        }
        else this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Set<Servico> getAgendamentos() {
        return agendamentos;
    }

    public void adicionarAgendamento(Servico servico){
        this.agendamentos.add(servico);
    }
}
