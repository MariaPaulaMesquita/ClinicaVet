# Trabalho Final POO
## Escopo do trabalho
### Tema e Descrição: 
Sistema de gerenciamento de uma clínica veterinária.

O projeto consiste no desenvolvimento de um sistema orientado a objetos em Java para gerenciamento de uma clínica veterinária de cães e gatos, com o objetivo de organizar e automatizar processos comuns de uma clínica, como cadastro de clientes, animais, veterinários e serviços prestados. Além disso, o sistema busca melhorar o controle das informações e facilitar o acompanhamento do histórico médico dos pacientes.

### Funcionalidades:

- Cadastro de Animais;
- Cadastro de Tutores;
- Agendar serviço (Consulta, Exames, Vacinação, Cirurgia);
- Cancelar serviço;
- Alterar data do serviço;
- Consultar serviços agendados por veterinário;
- Consultar agendamentos de animais cadastrados.

### Obrigatoriedades:

- Encapsulamento: Os atributos das classes do sistema serão encapsulados, sendo o acesso controlado por meio de métodos get e set, respeitando as relações de herança.
- Herança: As classes Gato e Cachorro herdarão da classe abstrata Animal. Assim como as classes Veterinário e Tutor herdarão da classe Pessoa.
- Polimorfismo: (de tipo): Gato e Cachorro poderão ser tratados como do tipo Animal. (de método): utilizar métodos sobrescritos das superclasses.
- Classes abstratas: Animal e Pessoa.
- Interface: A interface Servico será implementada pelas classes Consulta, Exames, Vacinacao e Cirurgia.
- Tratamento de Exceções: Não poderá haver mais de um agendamento de serviço para o mesmo veterinário no mesmo horário; Os nomes de pessoas e animais não podem ser uma string vazia.
- O histórico de serviços de um paciente (animal) será persistido em arquivo.
- Interface Gráfica: O projeto contará com uma interface gráfica voltada para uso interno da clínica veterinária, destinada aos funcionários para realização de cadastros, agendamentos e consultas de informações.
