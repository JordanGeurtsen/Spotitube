package nl.han.oose.dea.jordan.beroepsproduct.services;

import nl.han.oose.dea.jordan.beroepsproduct.services.dto.UserDTO;

public interface LoginService {
    UserDTO login(String user, String password);

    void authorize(String token);
}
