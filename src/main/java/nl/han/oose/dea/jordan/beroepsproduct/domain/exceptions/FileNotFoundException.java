package nl.han.oose.dea.jordan.beroepsproduct.domain.exceptions;

public class FileNotFoundException extends RuntimeException {
    public FileNotFoundException(String message) {
        super(message);
    }
}
