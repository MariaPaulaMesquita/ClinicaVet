package cadastro;

import excecoes.VeterinarioInvalidoException;
import pessoas.Tutor;
import pessoas.Veterinario;

import java.io.*;
import java.util.Set;
import java.util.TreeSet;

public class CadastroFuncionarios {
    public static Set<Veterinario> veterinarios = new TreeSet<>();
    public static Set<Veterinario> getVeterinarios(){
        return veterinarios;
    }
    //cadastros -------------------------------------------------------
    public static void cadastrarVeterinario(Veterinario vet) {
        if(vet==null){
            throw new VeterinarioInvalidoException("Digite um veterinario valido");}
        veterinarios.add(vet);
    }

    //listagens -------------------------------------------------------
    //TODO listar funcionarios
    public static void listarVeterinarios() {
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

    public static Veterinario pesquisarVeterinario(String vet){
        for(Veterinario v : veterinarios){
            String veter = v.getNome()+" | "+v.getCrmv();
            if(veter.equals(vet)){
                return v;
            }
        }
        return null;
    }

    public void salvarVeterinarios() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("veterinarios.txt"))) {
            for (Veterinario v : veterinarios) {
                pw.println(
                        v.getNome() + ";" +
                                v.getCpf() + ";" +
                                v.getTelefone() + ";" +
                                v.getCrmv() + ";" +
                                v.getAnoContrato() + ";" +
                                v.getAnoFormacao()
                );
            }

        } catch (IOException e) {
            System.out.println("Erro ao salvar veterinários.");
        }
    }
    public void carregarVeterinarios() {
        try (BufferedReader br = new BufferedReader(new FileReader("veterinarios.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");

                Veterinario v = new Veterinario(
                        dados[0],                 // nome
                        dados[1],                 // cpf
                        dados[2],                 // telefone
                        dados[3],                 // crmv
                        Integer.parseInt(dados[4]),
                        Integer.parseInt(dados[5])
                );
                veterinarios.add(v);
            }

        } catch (IOException e) {
            System.out.println("Arquivo de veterinários não encontrado.");
        }
    }


}
