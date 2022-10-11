package nl.han.oose.dea.jordan.beroepsproduct.domain.exceptions;

import nl.han.oose.dea.jordan.beroepsproduct.domain.LoginService;
import nl.han.oose.dea.jordan.beroepsproduct.domain.dto.LoginRequestDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AccountNotFoundExceptionTest {

    @Test
    public void testIfExceptionMessageIsGivenWhenThrown(){
        // Arrange
        LoginService mockedLoginService = Mockito.mock(LoginService.class);

        LoginRequestDTO credentials = new LoginRequestDTO();
        credentials.setUser("jordan");
        credentials.setPassword("123");

        Mockito.when(mockedLoginService.login(credentials)).thenThrow(new AccountNotFoundException());

        //Act
        Exception exception = assertThrows(AccountNotFoundException.class, () -> {
            mockedLoginService.login(credentials);
        });

        // Arrange
        assertEquals("Account not found", exception.getMessage());
    }
}
