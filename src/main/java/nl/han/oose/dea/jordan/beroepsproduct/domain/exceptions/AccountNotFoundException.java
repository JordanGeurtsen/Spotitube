package nl.han.oose.dea.jordan.beroepsproduct.domain.exceptions;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException() {
        super("Account not found");
    }
}
