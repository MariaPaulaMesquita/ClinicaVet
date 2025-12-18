public abstract class Animal {
    protected String nome;
    protected int idadeMeses;
    protected String raca;
    protected String especie;

    public Animal(String nome, int idadeMeses, String raca, String especie) {
        this.nome = nome;
        this.idadeMeses = idadeMeses;
        this.raca = raca;
        this.especie = especie;
    }
    public abstract String calcularFaixaEtaria();
    public void mostrarIdade(int idadeMeses) {
        int idadeAnos = idadeMeses/12;
        if (idadeAnos==0) System.out.println("O pet tem " + idadeMeses + " meses.");
        else System.out.println("O pet tem " + idadeAnos + " e " + idadeMeses%12 + " meses.");
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdadeMeses() {
        return idadeMeses;
    }

    public void setIdadeMeses(int idadeMeses) {
        this.idadeMeses = idadeMeses;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

}
