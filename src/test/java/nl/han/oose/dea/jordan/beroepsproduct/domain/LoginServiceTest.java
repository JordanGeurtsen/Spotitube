package nl.han.oose.dea.jordan.beroepsproduct.domain;

import nl.han.oose.dea.jordan.beroepsproduct.data.dao.UserDAO;
import nl.han.oose.dea.jordan.beroepsproduct.domain.dto.LoginRequestDTO;
import nl.han.oose.dea.jordan.beroepsproduct.domain.dto.UserDTO;
import nl.han.oose.dea.jordan.beroepsproduct.domain.exceptions.AccountNotFoundException;
import nl.han.oose.dea.jordan.beroepsproduct.domain.exceptions.UnauthorizedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LoginServiceTest {

    private LoginService sut;
    private UserDAO mockedUserDAO;
    private UserDTO userDTO;
    private LoginRequestDTO loginRequestDTO;

    @BeforeEach
    void setUp() {
        sut = new LoginService();
        mockedUserDAO = mock(UserDAO.class);
        userDTO = new UserDTO();
        loginRequestDTO = new LoginRequestDTO();
        sut.setUserDAO(mockedUserDAO);
    }

    @Test
    void loginReturnsUserDTO() {
        // Arrange
        loginRequestDTO.setUser("jordan");
        loginRequestDTO.setPassword("password");

        userDTO.setUser("jordan");
        userDTO.setToken("token");

        when(mockedUserDAO.getUserWithLoginRequest(loginRequestDTO)).thenReturn(userDTO);

        // Act
        UserDTO actual = sut.login(loginRequestDTO);

        // Assert
        assertEquals(userDTO, actual);
    }

    @Test
    void loginThrowsAccountNotFoundException() {
        // Arrange
        loginRequestDTO.setUser("jordan");
        loginRequestDTO.setPassword("password");

        when(mockedUserDAO.getUserWithLoginRequest(loginRequestDTO)).thenReturn(null);

        // Act
        assertThrows(AccountNotFoundException.class, () -> sut.login(loginRequestDTO));
    }

    @Test
    void authorizeReturnsUserDTO() {
        // Arrange
        userDTO.setUser("jordan");
        userDTO.setToken("token");

        when(mockedUserDAO.getUserWithToken("token")).thenReturn(Optional.ofNullable(userDTO));

        // Act
        sut.authorize("token");

        // Assert
        assertEquals(userDTO, sut.getCurrentUser());
    }

    @Test
    void authorizeThrowsUnauthorizedException() {
        // Arrange
        when(mockedUserDAO.getUserWithToken("token")).thenReturn(Optional.empty());

        // Act
        assertThrows(UnauthorizedException.class, () -> sut.authorize("token"));
    }
}