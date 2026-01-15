package pessoas;

import java.time.Year;
import excecoes.AnoInvalidoException;

public class Veterinario extends Pessoa implements Comparable<Veterinario> {
    private String crmv;
    private int anoContrato;
    private int anoFormacao;

    public Veterinario(String nome, String cpf, String telefone, String crmv, int anoContrato, int anoFormacao) {
        super(nome, cpf, telefone);
        this.crmv = crmv;
        this.anoContrato = anoContrato;
        this.anoFormacao = anoFormacao;
    }

    public int compareTo(Veterinario vet){
        return this.nome.compareToIgnoreCase(vet.nome);
    }

    public int getAnosContratado(){
        if(anoContrato>Year.now().getValue() || anoContrato<1926){
            throw new AnoInvalidoException("Ano de contrato invalido");}
        else return (Year.now().getValue() - anoContrato);
    }

    public int getAnosExperiencia(){
         if(anoFormacao>Year.now().getValue() || anoFormacao<1916){
            throw new AnoInvalidoException("Ano de formacao invalido");}
      else return (Year.now().getValue() - anoFormacao);
    }
   

    public String getCrmv() {
        return crmv;
    }

    public void setCrmv(String crmv) {
        this.crmv = crmv;
    }

    public int getAnoContrato() {
        return anoContrato;
    }

    public void setAnoContrato(int anoContrato) {
         if(anoContrato>Year.now().getValue() || anoContrato<1926){
            throw new AnoInvalidoException("Ano de contrato invalido");}
        else this.anoContrato = anoContrato;
    }
   

    public int getAnoFormacao() {
        return anoFormacao;
    }

    public void setAnoFormacao(int anoFormacao) {
        if(anoFormacao>Year.now().getValue() || anoFormacao<1916){
            throw new AnoInvalidoException("Ano de formacao invalido");}
        else this.anoFormacao = anoFormacao;
    }
}
