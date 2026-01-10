public abstract class Animal {
    protected String nome;
    protected int idadeMeses;
    protected String raca;
    protected String pelagem;

    public Animal(String nome, int idadeMeses, String raca, String pelagem){
        this.nome = nome;
        this.idadeMeses = idadeMeses;
        this.raca = raca;
        this.pelagem = pelagem;
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

    public String getPelagem() {
        return pelagem;
    }

    public void setPelagem(String pelagem) {
        this.pelagem = pelagem;
    }

    public abstract String calcularFaixaEtaria();
    //TODO retorna classificacao conforme a idade (filhote, adulto...)

    public String calcularIdade(){
        int anos = this.idadeMeses/12;
        int meses = this.idadeMeses%12;
        return anos + " anos e " + meses + " meses";
    }

    
}
