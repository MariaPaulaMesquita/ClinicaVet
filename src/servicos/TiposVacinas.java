package servicos;

import java.util.HashMap;
import java.util.Map;

public class TiposVacinas {
    public static Map<String, Double> vacinas = new HashMap<>();

    static {
        vacinas.put("V5", 130.00);
        vacinas.put("V10", 90.00);
        vacinas.put("Raiva", 40.00);
        vacinas.put("Gripe canina", 100.00);
        vacinas.put("Giardia", 80.00);
    }


}

