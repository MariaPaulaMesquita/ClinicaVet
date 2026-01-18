package cadastro;

import animais.Animal;
import excecoes.*;
import pessoas.Tutor;
import pessoas.Veterinario;
import servicos.*;
import servicos.Servico;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.TreeSet;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeSet;

public class CadastroAgendamentos {
    public static Set<Servico> servicosAgendados = new TreeSet<>();
    public static Set<Servico> getServicosAgendados(){
        return servicosAgendados;
    }
    private static final String ARQUIVO = "agendamentos.txt";
    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

// Metodos para as exceções------------------------------------------

    private static void verificarDisponibilidadeVeterinario(Servico servico, LocalDateTime inicio, LocalDateTime fim) throws VeterinarioIndisponivelException {
        for (Servico s : servicosAgendados) {
            if (s == servico) continue;
            if (s.getVeterinario().equals(servico.getVeterinario()) && s.conflitaCom(inicio, fim)) {
             throw new VeterinarioIndisponivelException("O veterinário não está disponível nesse horário");
            }
        }
    }

    private static void verificarDisponibilidadeAnimal(Servico servico, LocalDateTime inicio, LocalDateTime fim) throws AnimalIndisponivelException {
        for (Servico s : servicosAgendados) {
            if (s == servico) continue;
            if (s.getAnimal().equals(servico.getAnimal()) && s.conflitaCom(inicio, fim)) { throw new AnimalIndisponivelException("O animal já possui um serviço nesse horário");
            }
        }
    }


    //cadastro -------------------------------------------------------
    public static void agendarServico(Servico servico) throws VeterinarioIndisponivelException, AnimalIndisponivelException {
        if (servico == null) throw new ServicoInvalidoException("Serviço inválido");
        verificarDisponibilidadeVeterinario(servico, servico.getDataHoraInicio(), servico.getDataHoraFinal());
        verificarDisponibilidadeAnimal(servico, servico.getDataHoraInicio(), servico.getDataHoraFinal());

        servicosAgendados.add(servico);
        servico.getVeterinario().adicionarAgendamento(servico);
        servico.getAnimal().getTutor().adicionarAgendamento(servico);
    }


    //mudanças -------------------------------------------------------
    public void cancelarServico(Servico servico){
        servico.cancelar();
    }

    public void alterarDataServico(Servico servico, LocalDateTime novoInicio, LocalDateTime novoFim) throws VeterinarioIndisponivelException, AnimalIndisponivelException {
        if (servico == null || !servicosAgendados.contains(servico)) throw new ServicoInvalidoException("Serviço não estava previamente agendado");
        verificarDisponibilidadeVeterinario(servico, novoInicio, novoFim);
        verificarDisponibilidadeAnimal(servico, novoInicio, novoFim);
        servico.setDataHoraInicio(novoInicio);
        servico.setDataHoraFinal(novoFim);
    }


    //listagens -------------------------------------------------------

    public void listarAgendamentosDoTutor(Tutor tutor){
        System.out.println("---------------\nAGENDAMENTOS: \n---------------");
        double valorTotal = 0.0;
        for(Servico s : tutor.getAgendamentos()){
            System.out.println("Tutor: " + tutor.getNome());
            System.out.println("Serviços agendados: ");
            System.out.print(s.tipoServico() + " - ");
            s.mostrarDataHoraInicio();
            System.out.print(" até ");
            s.mostrarDataHoraFinal();
            System.out.println(" - Animal: " + s.getAnimal().getNome() + " - Veterinário: " + s.getVeterinario().getNome());
            System.out.println("Valor: " + s.getValorBase());
            System.out.println("---------------");
            valorTotal += s.getValorBase();
        }
        System.out.println("Valor Total: " + valorTotal);
        System.out.println("---------------");
    }

    public void listarAgendamentosDoAnimal(Animal a){
        System.out.println("---------------\nAGENDAMENTOS: \n---------------");
        double valorTotal = 0.0;
        for(Servico s : servicosAgendados){
            if (s.getAnimal().equals(a)){
                System.out.println("Animal: " + a.getNome() + " - Tutor: " + a.getTutor().getNome());
                System.out.println("Serviços agendados: ");
                System.out.print(s.tipoServico() + " - ");
                s.mostrarDataHoraInicio();
                System.out.print(" até ");
                s.mostrarDataHoraFinal();
                System.out.println(" - Veterinário: " + s.getVeterinario().getNome());
                System.out.println("---------------");
                valorTotal += s.getValorBase();
            }
            System.out.println("Valor Total: " + valorTotal);
            System.out.println("---------------");
        }
    }

    public void listarAgendamentosDoVeterinario(Veterinario vet){
        System.out.println("---------------\nAGENDAMENTOS: \n---------------");
        double valorTotal = 0.0;
        for(Servico s : vet.getAgendamentos()){
            System.out.println("Veterinário: " + vet.getNome());
            System.out.println("Serviços agendados: ");
            System.out.print(s.tipoServico() + " - ");
            s.mostrarDataHoraInicio();
            System.out.print(" até ");
            s.mostrarDataHoraFinal();
            System.out.println(" - Animal: " + s.getAnimal().getNome() + " - Tutor: " + s.getAnimal().getTutor().getNome());
            System.out.println("Valor: " + s.getValorBase());
            System.out.println("---------------");
            valorTotal += s.getValorBase();
        }
        System.out.println("Valor Total: " + valorTotal);
        System.out.println("---------------");
    }

    // ----------- Persistência ----------------

    public static void salvarAgendamentos() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO))) {
            for (Servico s : servicosAgendados) {
                String detalhe = "";
                if (s instanceof Exame e) detalhe = e.getNomeExame();
                else if (s instanceof Cirurgia c) detalhe = c.getNomeCirurgia();
                else if (s instanceof Vacinacao v) detalhe = v.getNomeVacina();

                String linha = s.tipoServico() + ";" +
                        s.getAnimal().getNome() + ";" +
                        s.getVeterinario().getCpf() + ";" +
                        s.getDataHoraInicio().format(FORMATO_DATA) + ";" +
                        s.getDataHoraFinal().format(FORMATO_DATA) + ";" +
                        detalhe;

                bw.write(linha);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Carregar do TXT
    public static void carregarAgendamentos() {
        servicosAgendados.clear();
        File arquivo = new File(ARQUIVO);
        if (!arquivo.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length < 5) continue; // ignorar linhas inválidas

                String tipoServico = partes[0];
                String nomeAnimal = partes[1];
                String cpfVeterinario = partes[2];
                LocalDateTime inicio = LocalDateTime.parse(partes[3], FORMATO_DATA);
                LocalDateTime fim = LocalDateTime.parse(partes[4], FORMATO_DATA);
                String detalhe = partes.length > 5 ? partes[5] : "";

                Animal animal = CadastroClientes.pesquisarAnimal(nomeAnimal);
                Veterinario vet = CadastroFuncionarios.pesquisarVeterinario(cpfVeterinario);
                if (animal == null || vet == null) continue;

                Servico s = null;
                switch (tipoServico) {
                    case "Consulta" -> s = new Consulta(inicio, animal, vet);
                    case "Exame" -> s = new Exame(inicio, fim, animal, vet, detalhe);
                    case "Cirurgia" -> s = new Cirurgia(inicio, fim, animal, vet, detalhe);
                    case "Vacinacao" -> s = new Vacinacao(inicio, animal, vet, detalhe);
                }

                if (s != null) {
                    servicosAgendados.add(s);
                    vet.adicionarAgendamento(s);
                    animal.getTutor().adicionarAgendamento(s);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


