package nl.han.oose.dea.jordan.beroepsproduct.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import nl.han.oose.dea.jordan.beroepsproduct.services.dto.LoginRequestDTO;
import nl.han.oose.dea.jordan.beroepsproduct.services.dto.UserDTO;
import nl.han.oose.dea.jordan.beroepsproduct.services.exceptions.AccountNotFoundException;
import nl.han.oose.dea.jordan.beroepsproduct.services.exceptions.InvalidTokenException;

import java.util.UUID;

@Default
@ApplicationScoped
public class HardCodedLoginService implements LoginService {

    @Override
    public UserDTO login(String user, String password) {
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
        loginRequestDTO.setUser("JordanGeurtsen");
        loginRequestDTO.setPassword("Koet");

        if (user.equals(loginRequestDTO.getUser()) && password.equals(loginRequestDTO.getPassword())) {
            UserDTO userDTO = new UserDTO();
            userDTO.setToken("1234-1234-1234-1234");
//            userDTO.setToken(UUID.randomUUID().toString());
            userDTO.setUser("Jordan Geurtsen");
            return userDTO;
        } else {
            throw new AccountNotFoundException();
        }
    }

    @Override
    public void authorize(String token) {
        if (!token.equals("1234-1234-1234-1234")) {
            throw new InvalidTokenException();
        }
    }
}
