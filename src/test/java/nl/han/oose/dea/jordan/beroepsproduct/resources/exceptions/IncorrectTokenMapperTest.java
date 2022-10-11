package nl.han.oose.dea.jordan.beroepsproduct.resources.exceptions;

import jakarta.ws.rs.core.Response;
import nl.han.oose.dea.jordan.beroepsproduct.domain.exceptions.UnauthorizedException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IncorrectTokenMapperTest {

    @Test
    void test() {
        //Arrange
        IncorrectTokenMapper sut = new IncorrectTokenMapper();

        //Act
        Response response = sut.toResponse(new UnauthorizedException());

        //Assert;
        assertEquals(403, response.getStatus());
    }

}