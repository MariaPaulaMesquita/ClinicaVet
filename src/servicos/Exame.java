package servicos;
import java.time.LocalDateTime;
import animais.Animal;
import pessoas.Veterinario;
import pessoas.Tutor;
// exames : Hemograma, Raio-X, Ultrassom
public class Exame extends Servico implements Preco{
    private TipoExame tipo;
    public Exame(LocalDateTime dataHora, Animal animal, Veterinario veterinario, double valorBase, TipoExame tipo) {
        super(dataHora, animal, veterinario, valorBase);
        this.tipo=tipo;
    }

    @Override
    public double calcularPreco(Tutor tutor) {
        if (tutor.isMensalista()){
            return getValorBase()*0.75; // Desconto de 25%
        }
        else return getValorBase();

    }

    @Override
    public String tipoServico() {
        return "Exame - " + tipo.getNome();
    }
}
