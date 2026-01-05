import java.time.Year;
public class Veterinario extends Pessoa {
    private String crmv;
    private int anoContrato;
    private int anoFormacao;

    public Veterinario(String nome, String cpf, int telefone, String crmv, int anoContrato, int anoFormacao) {
        super(nome, cpf, telefone);
        this.crmv = crmv;
        this.anoContrato = anoContrato;
        this.anoFormacao = anoFormacao;
    }

    public int getAnosContratado(){
        return (Year.now().getValue() - anoContrato);
    }
    public int getAnosExperiencia(){
        return (Year.now().getValue() - anoFormacao);
    }
    //levantar exceções se anoFormacao ou anoContrato > ano atual

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
        this.anoContrato = anoContrato;
    }
    public int getAnoFormacao() {
        return anoFormacao;
    }

    public void setAnoFormacao(int anoFormacao) {
        this.anoFormacao = anoFormacao;
    }

}
