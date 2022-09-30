package nl.han.oose.dea.jordan.beroepsproduct.resources.exceptions;

import jakarta.ws.rs.core.Response;
import nl.han.oose.dea.jordan.beroepsproduct.services.exceptions.InvalidTokenException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IncorrectTokenMapperTest {

    @Test
    void test() {
        //Arrange
        IncorrectTokenMapper sut = new IncorrectTokenMapper();

        //Act
        Response response = sut.toResponse(new InvalidTokenException());

        //Assert;
        assertEquals(401, response.getStatus());
    }

}