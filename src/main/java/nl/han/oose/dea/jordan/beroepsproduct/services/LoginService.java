package nl.han.oose.dea.jordan.beroepsproduct.services;

import jakarta.enterprise.context.ApplicationScoped;
import nl.han.oose.dea.jordan.beroepsproduct.services.dto.UserDTO;
import nl.han.oose.dea.jordan.beroepsproduct.services.exceptions.AccountNotFoundException;


public class LoginService {

    public UserDTO login(String user, String password) {
        if (user.equals("JordanGeurtsen") && password.equals("ASuperSecretPassword")) {
            return new UserDTO("1234-1234-1234", "Jordan Geurtsen");
        } else {
            throw new AccountNotFoundException();
        }
    }
}
