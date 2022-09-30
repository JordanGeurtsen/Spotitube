package nl.han.oose.dea.jordan.beroepsproduct.resources.exceptions;

import jakarta.ws.rs.core.Response;
import nl.han.oose.dea.jordan.beroepsproduct.services.exceptions.AccountNotFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountNotFoundMapperTest {

    @Test
    void test() {
        //Arrange
        AccountNotFoundMapper sut = new AccountNotFoundMapper();

        //Act
        Response response = sut.toResponse(new AccountNotFoundException());

        //Assert;
        assertEquals(401, response.getStatus());
    }
}