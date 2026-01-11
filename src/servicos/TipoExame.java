package servicos;

import java.util.HashMap;
import java.util.Map;

public class TipoExame {
    public static Map<String, Double> exames = new HashMap<>();

    static {
        exames.put("Teste Fiv/Felv", 150.00);
        exames.put("Hemograma", 85.00);
        exames.put("Ultrassom", 130.00);
        exames.put("Raio-X", 120.00);
        exames.put("Exame de Fezes", 74.00);
        exames.put("Exame de Urina", 44.00);
    }
/*
public class TipoExame {
    private String nome;
    private double precoBase;

    public TipoExame(String nome, double precoBase) {
        this.nome = nome;
        this.precoBase = precoBase;
    }

    public String getNome() {
        return nome;
    }

    public double getPrecoBase() {
        return precoBase;
    }
    */
}
