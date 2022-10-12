package nl.han.oose.dea.jordan.beroepsproduct.domain.exceptions;

public class ClassNotFoundException extends SpotitubeException {
    public ClassNotFoundException(String message) {
        super(message, 404);
    }
}
