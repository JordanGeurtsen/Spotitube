//package nl.han.oose.dea.jordan.beroepsproduct.datasource.dao;
//
//import nl.han.oose.dea.jordan.beroepsproduct.datasource.utils.DatabaseProperties;
//import nl.han.oose.dea.jordan.beroepsproduct.domain.dto.PlaylistDTO;
//import nl.han.oose.dea.jordan.beroepsproduct.domain.exceptions.DatabaseException;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.List;
//
//import static org.mockito.Mockito.*;
//
//class PlaylistDAOTest {
//
//    PlaylistDAO sut;
//    private DatabaseProperties mockedDatabaseProperties;
//    private Connection mockedConnection;
//
//    @BeforeEach
//    public void setUp() throws SQLException {
//        sut = new PlaylistDAO();
//        mockedConnection = mock(Connection.class);
//        mockedDatabaseProperties = mock(DatabaseProperties.class);
//        when(mockedDatabaseProperties.getDBConnectionString()).thenReturn("jdbc:mysql://localhost/spotitube?user=spotitube&password=spotitubeDBlogin&serverTimezone=UTC");
//    }
//
//    @Test
//    public void testGetAll() {
//        // Arrange
//        sut.setDatabaseProperties(mockedDatabaseProperties);
//
//        // Act
//        List<PlaylistDTO> playlists = sut.getAll();
//
//        // Assert
//        verify(mockedDatabaseProperties, times(1)).getDBConnectionString();
//
//    }
//}