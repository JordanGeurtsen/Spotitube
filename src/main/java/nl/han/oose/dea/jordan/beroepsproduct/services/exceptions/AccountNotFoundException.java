package nl.han.oose.dea.jordan.beroepsproduct.services.exceptions;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException() {
        super("Account not found");
    }
}
