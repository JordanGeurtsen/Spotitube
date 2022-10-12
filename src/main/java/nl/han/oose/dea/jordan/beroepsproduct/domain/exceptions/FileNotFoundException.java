package nl.han.oose.dea.jordan.beroepsproduct.domain.exceptions;

public class FileNotFoundException extends SpotitubeException {
    public FileNotFoundException(String message) {
        super(message, 404);
    }
}
