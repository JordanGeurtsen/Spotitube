package nl.han.oose.dea.jordan.beroepsproduct.services;

import nl.han.oose.dea.jordan.beroepsproduct.services.dto.LoginRequestDTO;
import nl.han.oose.dea.jordan.beroepsproduct.services.dto.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class HardcodedLoginServiceTest {

    private HardCodedLoginService hardCodedLoginService;

    @BeforeEach
    public void setup() {
        hardCodedLoginService = new HardCodedLoginService();
    }

    @Test
    public void logInGivesTheRightUserTest() {
        // Arrange
        LoginRequestDTO credentials = new LoginRequestDTO();
        credentials.setUser("JordanGeurtsen");
        credentials.setPassword("Koet");

        UserDTO expectedUser = new UserDTO();
        expectedUser.setUser("Jordan Geurtsen");
        expectedUser.setToken("1234-1234-1234-1234");

        // Act
        UserDTO user = hardCodedLoginService.login(credentials.getUser(), credentials.getPassword());

        // Assert
        assertEquals(expectedUser.getUser(), user.getUser());
        assertEquals(expectedUser.getToken(), user.getToken());
    }

    @Test
    public void authorizeSucceedsWithRightTokenTest() {
        // Arrange
        String token = "1234-1234-1234-1234";

        // Act
        hardCodedLoginService.authorize(token);

        // Assert
        // No exception is thrown
    }
}
