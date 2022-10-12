package nl.han.oose.dea.jordan.beroepsproduct.domain.exceptions;

public class AccountNotFoundException extends SpotitubeException {
    public AccountNotFoundException() {
        super("Account not found", 401);
    }
}
