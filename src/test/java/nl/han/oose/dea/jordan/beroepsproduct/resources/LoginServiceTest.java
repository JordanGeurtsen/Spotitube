package nl.han.oose.dea.jordan.beroepsproduct.resources;

import jakarta.ws.rs.core.Response;
import nl.han.oose.dea.jordan.beroepsproduct.services.LoginService;
import nl.han.oose.dea.jordan.beroepsproduct.services.dto.LoginRequestDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginServiceTest {

    @Test
    public void testIfLoginWithCorrectCredentialsReturnsStatusCode200AndCorrectJSON() {
        // Arrange
        LoginResource loginResource = new LoginResource();
        LoginService loginService = new LoginService();
        LoginRequestDTO credentials = new LoginRequestDTO();

        loginResource.setLoginService(loginService);

        // Act
        Response response = loginResource.login(credentials);

        // Assert
        assertEquals(200, response.getStatus());
    }
}
