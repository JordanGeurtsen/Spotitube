package nl.han.oose.dea.jordan.beroepsproduct.data.dao;

import nl.han.oose.dea.jordan.beroepsproduct.data.utils.DatabaseConnector;
import nl.han.oose.dea.jordan.beroepsproduct.domain.dto.LoginRequestDTO;
import nl.han.oose.dea.jordan.beroepsproduct.domain.dto.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class UserDAOTest {

    private UserDAO sut;
    private DatabaseConnector mockedDatabaseConnector;
    private Connection mockedConnection;
    private PreparedStatement mockedPreparedStatement;
    private ResultSet mockedResultSet;

    @BeforeEach
    public void setup() throws SQLException {
        sut = new UserDAO();
        mockedDatabaseConnector = mock(DatabaseConnector.class);
        mockedConnection = mock(Connection.class);
        mockedPreparedStatement = mock(PreparedStatement.class);
        mockedResultSet = mock(ResultSet.class);

        when(mockedDatabaseConnector.getConnection()).thenReturn(mockedConnection);

        sut.setDatabaseConnector(mockedDatabaseConnector);
    }

    @Test
    public void getUserWithTokenTest() throws SQLException {
        // Arrange
        String token = "Test";

        when(mockedConnection.prepareStatement(anyString())).thenReturn(mockedPreparedStatement);
        when(mockedPreparedStatement.executeQuery()).thenReturn(mockedResultSet);
        when(mockedResultSet.next()).thenReturn(true, false);
        when(mockedResultSet.getInt("id")).thenReturn(1);
        when(mockedResultSet.getString("user")).thenReturn("Test");
        when(mockedResultSet.getString("token")).thenReturn("Test");

        // Act
        UserDTO actual = sut.getUserWithToken(token).get();

        // Assert
        assertEquals(token, actual.getToken());
    }

    @Test
    public void getUserWithLoginRequest() throws SQLException {
        // Arrange
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
        loginRequestDTO.setUser("Test");
        loginRequestDTO.setPassword("Test");

        UserDTO expected = new UserDTO();
        expected.setUser("Test");
        expected.setToken("Test");

        when(mockedConnection.prepareStatement(anyString())).thenReturn(mockedPreparedStatement);
        when(mockedPreparedStatement.executeQuery()).thenReturn(mockedResultSet);
        when(mockedResultSet.next()).thenReturn(true, false);
        when(mockedResultSet.getInt("id")).thenReturn(1);
        when(mockedResultSet.getString("name")).thenReturn(expected.getUser());
        when(mockedResultSet.getString("token")).thenReturn(expected.getToken());

        // Act
        UserDTO actual = sut.getUserWithLoginRequest(loginRequestDTO);

        // Assert
        assertEquals(expected.getUser(), actual.getUser());
    }

    @Test
    public void updateUserTest() throws SQLException {
        // Arrange
        String expectedQuery = "UPDATE spotitube.users SET name = ?, token = ? WHERE id = ?";

        UserDTO userDTO = new UserDTO();
        userDTO.setId(1);
        userDTO.setUser("Test");
        userDTO.setToken("Test");

        when(mockedConnection.prepareStatement(anyString())).thenReturn(mockedPreparedStatement);

        // Act
        sut.update(userDTO);

        // Assert
        verify(mockedConnection).prepareStatement(expectedQuery);
        verify(mockedPreparedStatement).setString(1, userDTO.getUser());
        verify(mockedPreparedStatement).setString(2, userDTO.getToken());
        verify(mockedPreparedStatement).setInt(3, userDTO.getId());
        verify(mockedPreparedStatement).executeUpdate();
    }

    @Test
    public void getGetAllStatementReturnsNull() throws SQLException {
        assertNull(sut.getGetAllStatement(mockedConnection));
    }

    @Test
    public void getGetStatementReturnsNull() throws SQLException {
        assertNull(sut.getGetStatement(mockedConnection, 1));
    }

    @Test
    public void getInsertStatementReturnsNull() throws SQLException {
        assertNull(sut.getInsertStatement(mockedConnection, new UserDTO()));
    }

    @Test
    public void getDeleteStatementReturnsNull() throws SQLException {
        assertNull(sut.getDeleteStatement(mockedConnection, 1));
    }
}