import animais.*;
import pessoas.Pessoa;
import pessoas.Tratamento;
import pessoas.Tutor;
import cadastro.*;
import pessoas.Veterinario;
import servicos.Consulta;
import java.time.LocalDateTime;

public class TesteClinica {
    public static void main(String[] args) {
        CadastroClientes cadastro = new CadastroClientes();
        CadastroFuncionarios funcionarios = new CadastroFuncionarios();
        CadastroAgendamentos agendamentos = new CadastroAgendamentos();
        Tutor p1 = new Tutor("Maria", "10575614567", "85986970704", "Av. Bezerra de Menezes, Parquelândia - Fortaleza", Tratamento.MENSALISTA );
        Tutor p2 = new Tutor("Marcio", "76878954369", "85986895932", "Sargento Hermínio - Fortaleza", Tratamento.TRADICIONAL );
        Animal a1 = new Gato("Lino", "macho", 20, 2, 2025, "SRD", "preto", false);
        Animal a2 = new Cachorro("Mulan", "femea", 3, 2020, "SRD", "branca com manchas pretas", "grande");
        Animal a3 = new Gato("Pingo", "macho", 14, 8, 2017, "SRD", "branco", false);
        Veterinario v1 = new Veterinario("Caio", "69267490277", "85988673398", "873-CE",2021, 2016);
        Veterinario v2 = new Veterinario("Amanda", "86467490246", "85985946020", "1230-CE",2021, 2018);
        funcionarios.cadastrarVeterinario(v1);
        funcionarios.cadastrarVeterinario(v2);
        p1.adicionarAnimal(a1);
        p2.adicionarAnimal(a2);
        p2.adicionarAnimal(a3);
        cadastro.cadastrarTutor(p1);
        cadastro.cadastrarTutor(p2);
        cadastro.listarTutores();
        funcionarios.listarVeterinarios();
        agendamentos.agendarServico(new Consulta(LocalDateTime.of(2026, 1, 24, 14, 30),a1, v1));
        agendamentos.listarAgendamentosDoTutor(p1);



    }
}
