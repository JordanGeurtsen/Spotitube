package nl.han.oose.dea.jordan.beroepsproduct.services.exceptions;

import nl.han.oose.dea.jordan.beroepsproduct.services.HardCodedLoginService;
import nl.han.oose.dea.jordan.beroepsproduct.services.dto.LoginRequestDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AccountNotFoundExceptionTest {

    @Test
    public void testIfExceptionMessageIsGivenWhenThrown(){
        // Arrange
        HardCodedLoginService mockedHardCodedLoginService = Mockito.mock(HardCodedLoginService.class);

        LoginRequestDTO credentials = new LoginRequestDTO();
        credentials.setUser("jordan");
        credentials.setPassword("123");

        Mockito.when(mockedHardCodedLoginService.login(credentials.getUser(), credentials.getPassword())).thenThrow(new AccountNotFoundException());

        //Act
        Exception exception = assertThrows(AccountNotFoundException.class, () -> {
            mockedHardCodedLoginService.login(credentials.getUser(), credentials.getPassword());
        });

        // Arrange
        assertEquals("Account not found", exception.getMessage());
    }
}
