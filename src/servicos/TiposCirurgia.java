package servicos;

import java.util.HashMap;
import java.util.Map;

public class TiposCirurgia {
    public static Map<String, Map<String, Double>> cirurgias = new HashMap<>(); //map de maps

    static {
        //cirurgias para cachorros
        Map<String, Double> cachorro = new HashMap<>();
        cachorro.put("Castração", 300.00);
        cachorro.put("Cirurgia Ortopédica", 5000.00);
        cachorro.put("Catarata", 2500.00);
        cachorro.put("Tartarectomia", 300.00);
        cachorro.put("Piometra", 600.00);
        cachorro.put("Remoção de Tumores", 1000.00);
        cachorro.put("Cirurgia Cardíaca", 15000.00);

        //cirurgias para gatos
        Map<String, Double> gato = new HashMap<>();
        gato.put("Castração", 200.00);
        gato.put("Cirurgia Ortopédica", 4000.00);
        gato.put("Catarata", 2500.00);
        gato.put("Tartarectomia", 300.00);
        gato.put("Desobstrução urinária", 300.00);
        gato.put("Remoção de Tumores", 800.00);
        gato.put("Cirurgia Cardíaca", 13000.00);

        //adicionando as cirurgias de gato e de cachorro no map principal
        cirurgias.put("Cachorro", cachorro);
        cirurgias.put("Gato", gato);

    }
    public static double getPreco(String especie, String nomeCirurgia) {
        Map<String, Double> lista = cirurgias.get(especie);
        if (lista != null && lista.containsKey(nomeCirurgia)) {
            return lista.get(nomeCirurgia);
        }
        return 0.0; // pode retornar exceção,se quiser.
    }

}
