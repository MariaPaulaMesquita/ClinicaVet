import animais.*;
import pessoas.Pessoa;
import pessoas.Tratamento;
import pessoas.Tutor;
import cadastro.*;

public class TesteClinica {
    public static void main(String[] args) {
        CadastroClientes cadastro = new CadastroClientes();
        Tutor p1 = new Tutor("Maria", "10575614567", "85986970704", "Av. Bezerra de Menezes, Parquelândia - Fortaleza", Tratamento.MENSALISTA );
        Tutor p2 = new Tutor("Marcio", "76878954369", "85986895932", "Sargento Hermínio - Fortaleza", Tratamento.TRADICIONAL );
        Animal a1 = new Gato("Lino", 20, 2, 2025, "SRD", "preto", false);
        Animal a2 = new Cachorro("Mulan", 3, 2020, "SRD", "branca com manchas pretas", "grande");
        p1.adicionarAnimal(a1);
        p2.adicionarAnimal(a2);
        cadastro.cadastrarTutor(p1);
        cadastro.listarTutores();



    }
}
