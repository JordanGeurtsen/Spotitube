package nl.han.oose.dea.jordan.beroepsproduct.resources.exceptions;

import jakarta.ws.rs.core.Response;
import nl.han.oose.dea.jordan.beroepsproduct.domain.exceptions.*;
import nl.han.oose.dea.jordan.beroepsproduct.domain.exceptions.ClassNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpotitubeMapperTest {

    SpotitubeMapper sut;
    SpotitubeException exception;

    @BeforeEach
    public void setUp() {
        sut = new SpotitubeMapper();
    }

    @Test
    public void testIfUnauthorizedExceptiongives403() {
        // Arrange
        exception = new UnauthorizedException();

        // Act
        Response response = sut.toResponse(exception);

        //Assert
        assertEquals(403, response.getStatus());
    }

    @Test
    public void testIfAccountNotFoundExceptiongives404() {
        // Arrange
        exception = new AccountNotFoundException();

        // Act
        Response response = sut.toResponse(exception);

        //Assert
        assertEquals(401, response.getStatus());
    }

    @Test
    public void testIfClassNotFoundExceptiongives404() {
        // Arrange
        exception = new ClassNotFoundException("test");

        // Act
        Response response = sut.toResponse(exception);

        //Assert
        assertEquals(404, response.getStatus());
    }

    @Test
    public void testIfFileNotFountExceptiongives404() {
        // Arrange
        exception = new FileNotFoundException("test");

        // Act
        Response response = sut.toResponse(exception);

        //Assert
        assertEquals(404, response.getStatus());
    }

    @Test
    public void testIfDatabaseExceptiongives500() {
        // Arrange
        exception = new DatabaseException("test");

        // Act
        Response response = sut.toResponse(exception);

        //Assert
        assertEquals(500, response.getStatus());
    }
}