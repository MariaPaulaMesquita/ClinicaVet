package cadastro;

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
}
