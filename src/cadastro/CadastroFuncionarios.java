package cadastro;

import pessoas.Tutor;
import pessoas.Veterinario;

import java.util.Set;
import java.util.TreeSet;

public class CadastroFuncionarios {
    public Set<Veterinario> veterinarios = new TreeSet<>();

    //cadastros -------------------------------------------------------
    public void cadastrarVeterinario(Veterinario vet) {
        veterinarios.add(vet); //excecao neles
    }

    //listagens -------------------------------------------------------
    //TODO listar funcionarios
    public void listarVeterinarios() {
        System.out.println("---------------\nVETERINÁRIOS: \n---------------");
        if (veterinarios.isEmpty()) {
            System.out.println("Nenhum veterinário cadastrado.");
            return;
        }
        for (Veterinario v : veterinarios) {
            System.out.println("Veterinário: " + v.getNome());
            System.out.println("CPF: " + v.getCpf());
            System.out.println("Telefone: " + v.getTelefone());
            System.out.println("CRMV: " + v.getCrmv());
            System.out.println("---------------");
        }
    }
}