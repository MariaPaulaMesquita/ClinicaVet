package cadastro;

import animais.Animal;
import animais.Cachorro;
import animais.Gato;
import excecoes.AnimalInvalidoException;
import excecoes.TutorInvalidoException;
import pessoas.Tratamento;
import pessoas.Tutor;

import java.io.*;

import java.time.LocalDate;
import java.util.Set;
import java.util.TreeSet;

public class CadastroClientes {
    public static Set<Tutor> tutores = new TreeSet<>() ;
    public static Set<Tutor> getTutores(){
        return tutores;
    }
    //cadastros -------------------------------------------------------
    public static void cadastrarTutor(Tutor tutor){
        if(tutor == null){
            throw new TutorInvalidoException("Digite um tutor valido");}
       else tutores.add(tutor);
    }
    public static void cadastrarAnimal(Animal animal, Tutor tutor){
        // tipo isso pode dar excecao mas n sei se faz sentido ter no codigo se n tiver so apaga essa linha msm if(!tutor.getAnimais().contains(animal)){
            //throw new AnimalInvalidoException("Animal desconhecido")
        if(animal == null){//se aquela primeira linha valer isso vira um else if
            throw new AnimalInvalidoException("Digite um animal valido");}
        else tutor.adicionarAnimal(animal);
    }

    public static Set<Animal> getTodosAnimais(){
        Set<Animal> todosAnimais = new TreeSet<>();
        for(Tutor t : tutores){
            Set<Animal> animals = t.getAnimais();
            todosAnimais.addAll(animals);
        }
        return todosAnimais;
    }

    public static Animal pesquisarAnimal(String nome){
        for(Animal a : getTodosAnimais()){
            String animal = a.getNome()+" | "+a.getTutor().getNome()+" | "+a.getID();
            if(animal.equals(nome)){
                return a;
            }
        }
        return null;
    }

    public static Tutor pesquisarTutor(String nome){
        for(Tutor t : getTutores()){
            String animal = t.getNome()+" | "+t.getCpf();
            if(animal.equals(nome)){
                return t;
            }
        }
        return null;
    }

    //listagens -------------------------------------------------------
    public void listarTutores(){
        System.out.println("---------------\nTUTORES: \n---------------");
        if (tutores.isEmpty()) {
            System.out.println("Nenhum tutor cadastrado.");
            return;
        }
        for (Tutor t : tutores){
            System.out.println("Tutor: " + t.getNome());
            System.out.println("CPF: " + t.getCpf());
            System.out.println("Telefone: " + t.getTelefone());
            System.out.println("Endereço: " + t.getEndereco());
            System.out.println("Animais cadastrados: ");

            t.mostrarAnimais();

            System.out.println("---------------");
        }
    }
 // persistência
 public static Tutor buscarTutorPorCpf(String cpf) {
     for (Tutor t : tutores) {
         if (t.getCpf().equals(cpf)) {
             return t;
         }
     }
     return null;
 }

    public static void salvarTutores() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("tutores.txt"))) {

            for (Tutor t : tutores) {
                pw.println(
                        t.getNome() + ";" +
                                t.getCpf() + ";" +
                                t.getTelefone() + ";" +
                                t.getEndereco() + ";" +
                                t.getTratamento()
                );
            }

        } catch (IOException e) {
            System.out.println("Erro ao salvar tutores.");
        }
    }
    public static void carregarTutores() {
        try (BufferedReader br = new BufferedReader(new FileReader("tutores.txt"))) {

            String linha;
            while ((linha = br.readLine()) != null) {

                String[] d = linha.split(";");

                Tutor t = new Tutor(
                        d[0],                     // nome
                        d[1],                     // cpf
                        d[2],                     // telefone
                        d[3],                     // endereco
                        Tratamento.valueOf(d[4]) // enum
                );

                tutores.add(t);
            }

        } catch (IOException e) {
            System.out.println("Arquivo de tutores não encontrado.");
        }
    }
    public static void salvarAnimais() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("animais.txt"))) {
            for (Tutor t : tutores) {
                for (Animal a : t.getAnimais()) {
                    if (a instanceof Gato g) {
                        pw.println(
                                "Gato;" +
                                        g.getNome() + ";" +
                                        g.getSexo() + ";" +
                                        g.getDataNascimento() + ";" +
                                        g.getRaca() + ";" +
                                        g.getPelagem() + ";" +
                                        g.isAcessoARua() + ";" +
                                        t.getCpf()
                        );
                    }

                    if (a instanceof Cachorro c) {
                        pw.println(
                                "Cachorro;" +
                                        c.getNome() + ";" +
                                        c.getSexo() + ";" +
                                        c.getDataNascimento() + ";" +
                                        c.getRaca() + ";" +
                                        c.getPelagem() + ";" +
                                        c.getPorte() + ";" +
                                        t.getCpf()
                        );
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Erro ao salvar animais.");
        }
    }
    public static void carregarAnimais() {
        try (BufferedReader br = new BufferedReader(new FileReader("animais.txt"))) {

            String linha;
            while ((linha = br.readLine()) != null) {
                String[] d = linha.split(";");
                String tipo = d[0];
                String cpfTutor = d[d.length - 1];
                Tutor tutor = buscarTutorPorCpf(cpfTutor);
                if (tutor == null) {
                    continue; // tutor não existe, ignora esse animal
                }
                Animal animal = null;
                if (tipo.equals("Gato")) {
                    animal = new Gato(
                            d[1],               // nome
                            d[2],               // sexo
                            LocalDate.parse(d[3]), // dataNascimento (String)
                            d[4],               // raça
                            d[5],                // pelagem
                            Boolean.parseBoolean(d[6])
                    );
                }
                else if (tipo.equals("Cachorro")) {
                    animal = new Cachorro(
                            d[1],               // nome
                            d[2],               // sexo
                            LocalDate.parse(d[3]), // dataNascimento (String)
                            d[4],               // raça
                            d[5],               // pelagem
                            d[6]                // porte
                    );
                }
                if (animal != null) {
                    tutor.adicionarAnimal(animal);
                }
            }

        } catch (IOException e) {
            System.out.println("Arquivo de animais não encontrado.");
        }
    }
    public static void salvarTudo() {
        salvarTutores();
        salvarAnimais();
    }






}
