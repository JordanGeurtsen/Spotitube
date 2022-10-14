package nl.han.oose.dea.jordan.beroepsproduct.domain;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import nl.han.oose.dea.jordan.beroepsproduct.data.dao.UserDAO;
import nl.han.oose.dea.jordan.beroepsproduct.domain.dto.LoginRequestDTO;
import nl.han.oose.dea.jordan.beroepsproduct.domain.dto.UserDTO;
import nl.han.oose.dea.jordan.beroepsproduct.domain.exceptions.AccountNotFoundException;
import nl.han.oose.dea.jordan.beroepsproduct.domain.exceptions.UnauthorizedException;

import java.util.UUID;

@Default
@ApplicationScoped
public class LoginService {

    private UserDAO userDAO;
    private UserDTO currentUser;

    public UserDTO login(LoginRequestDTO login) {
        UserDTO userDTO = userDAO.getUserWithLoginRequest(login);
        if(userDTO == null) {
            throw new AccountNotFoundException();
        }
        userDTO.setToken(UUID.randomUUID().toString());
        userDAO.update(userDTO);
        currentUser = userDTO;
        return userDTO;
    }

    public void authorize(String token) {
        if (userDAO.getUserWithToken(token).isEmpty()) { throw new UnauthorizedException(); }
        currentUser = userDAO.getUserWithToken(token).get();
    }

    public UserDTO getCurrentUser() {
        return currentUser;
    }

    @Inject
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
