package excecoes;

public class ServicoInvalidoException extends RuntimeException {
    public ServicoInvalidoException(String message) {
        super(message);
    }
}
