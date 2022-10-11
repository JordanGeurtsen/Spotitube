package nl.han.oose.dea.jordan.beroepsproduct.resources;

import jakarta.ws.rs.core.Response;
import nl.han.oose.dea.jordan.beroepsproduct.domain.LoginService;
import nl.han.oose.dea.jordan.beroepsproduct.domain.dto.LoginRequestDTO;
import nl.han.oose.dea.jordan.beroepsproduct.domain.dto.UserDTO;
import nl.han.oose.dea.jordan.beroepsproduct.domain.exceptions.AccountNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class LoginResourceTest {

    private LoginResource sut;
    private LoginService mockedLoginService;

    @BeforeEach
    public void setup() {
        sut = new LoginResource();
        mockedLoginService = Mockito.mock(LoginService.class);
        sut.setLoginService(mockedLoginService);
    }

    @Test
    public void testIfLoginWithCorrectCredentialsReturnsStatusCode200AndCorrectJSON() {
        // Arrange
        LoginRequestDTO credentials = new LoginRequestDTO();
        credentials.setUser("JordanGeurtsen");
        credentials.setPassword("ASuperSecretPassword");

        UserDTO userDTO = new UserDTO();
        userDTO.setUser("Jordan Geurtsen");
        userDTO.setToken("1234-1234-1234-1234");

        Mockito.when(mockedLoginService.login(credentials)).thenReturn(userDTO);

        // Act
        Response response = sut.login(credentials);

        // Assert
        assertEquals(200, response.getStatus());
        assertEquals(userDTO, response.getEntity());
    }

    @Test
    public void testIfAccountNotFoundExceptionIsThrownWhenCredentialsAreWrong(){
        // Arrange
        LoginRequestDTO credentials = new LoginRequestDTO();
        credentials.setUser("jordan");
        credentials.setPassword("123");

        Mockito.when(mockedLoginService.login(credentials)).thenThrow(new AccountNotFoundException());

        //Act
        Exception exception = assertThrows(AccountNotFoundException.class, () -> {
          sut.login(credentials);
        });

        // Arrange
        String expectedMessage = "Account not found";
        String actualMessage = exception.getMessage();

        // Assert
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
