package cadastro;

import excecoes.VeterinarioInvalidoException;
import pessoas.Tutor;
import pessoas.Veterinario;

import java.util.Set;
import java.util.TreeSet;

public class CadastroFuncionarios {
    public static Set<Veterinario> veterinarios = new TreeSet<>();
    public static Set<Veterinario> getVeterinarios(){
        return veterinarios;
    }
    //cadastros -------------------------------------------------------
    public void cadastrarVeterinario(Veterinario vet) {
        if(vet==null){
            throw new VeterinarioInvalidoException("Digite um veterinario valido");}
        veterinarios.add(vet);
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
