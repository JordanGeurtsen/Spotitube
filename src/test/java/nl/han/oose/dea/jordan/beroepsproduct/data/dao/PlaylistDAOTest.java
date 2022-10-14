package nl.han.oose.dea.jordan.beroepsproduct.data.dao;

import nl.han.oose.dea.jordan.beroepsproduct.data.utils.DatabaseConnector;
import nl.han.oose.dea.jordan.beroepsproduct.domain.dto.PlaylistDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PlaylistDAOTest {

    private PlaylistDAO sut;
    private DatabaseConnector mockedDatabaseConnector;
    private Connection mockedConnection;
    private PreparedStatement mockedPreparedStatement;
    private ResultSet mockedResultSet;

    @BeforeEach
    public void setup() throws SQLException {
        sut = new PlaylistDAO();
        mockedDatabaseConnector = mock(DatabaseConnector.class);
        mockedConnection = mock(Connection.class);
        mockedPreparedStatement = mock(PreparedStatement.class);
        mockedResultSet = mock(ResultSet.class);

        when(mockedDatabaseConnector.getConnection()).thenReturn(mockedConnection);

        sut.setDatabaseConnector(mockedDatabaseConnector);
    }

    @Test
    public void getAllPlaylistsTest() throws SQLException {
        // Arrange
        List<PlaylistDTO> expected = new ArrayList<>();
        PlaylistDTO playlist = new PlaylistDTO();
        playlist.setId(1);
        playlist.setName("test");
        playlist.setOwner(true);
        expected.add(playlist);

        when(mockedConnection.prepareStatement(anyString())).thenReturn(mockedPreparedStatement);
        when(mockedPreparedStatement.executeQuery()).thenReturn(mockedResultSet);
        when(mockedResultSet.next()).thenReturn(true, false);
        when(mockedResultSet.getInt("id")).thenReturn(1);
        when(mockedResultSet.getString("name")).thenReturn("test");
        when(mockedResultSet.getBoolean("owner")).thenReturn(true);

        // Act
        List<PlaylistDTO> actual = sut.getAll();

        // Assert
        assertEquals(expected.get(0).getId(), actual.get(0).getId());
        assertEquals(expected.get(0).getName(), actual.get(0).getName());
        assertEquals(expected.get(0).getOwner(), actual.get(0).getOwner());
    }

    @Test
    public void getPlaylistTest() throws SQLException {
        // Arrange
        PlaylistDTO expected = new PlaylistDTO();
        expected.setId(1);
        expected.setName("Test");
        expected.setOwner(true);

        when(mockedConnection.prepareStatement(anyString())).thenReturn(mockedPreparedStatement);
        when(mockedPreparedStatement.executeQuery()).thenReturn(mockedResultSet);
        when(mockedResultSet.next()).thenReturn(true).thenReturn(false);
        when(mockedResultSet.getInt("id")).thenReturn(expected.getId());
        when(mockedResultSet.getString("name")).thenReturn(expected.getName());
        when(mockedResultSet.getBoolean("owner")).thenReturn(expected.getOwner());

        // Act
        PlaylistDTO actual = sut.get(1).get();

        // Assert
        assertEquals(actual.getId(), expected.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getOwner(), actual.getOwner());
    }

    @Test
    public void insertPlaylistTest() throws SQLException {
        // Arrange
        PlaylistDTO playlistDTO = new PlaylistDTO();
        playlistDTO.setName("test");
        playlistDTO.setOwner(true);

        when(mockedConnection.prepareStatement(anyString())).thenReturn(mockedPreparedStatement);

        // Act
        sut.insert(playlistDTO);

        // Assert
        verify(mockedPreparedStatement).executeUpdate();
    }

    @Test
    public void updatePlaylistTest() throws SQLException {
        // Arrange
        String expectedQuery = "UPDATE spotitube.playlists SET name = ? WHERE id = ?";

        PlaylistDTO playlistDTO = new PlaylistDTO();
        playlistDTO.setId(1);
        playlistDTO.setName("name");

        when(mockedConnection.prepareStatement(expectedQuery)).thenReturn(mockedPreparedStatement);

        doNothing().when(mockedPreparedStatement).setInt(anyInt(), anyInt());
        doNothing().when(mockedPreparedStatement).setString(anyInt(), anyString());

        // Act
        sut.update(playlistDTO);

        // Assert
        verify(mockedConnection).prepareStatement(expectedQuery);
        verify(mockedPreparedStatement).setString(1, playlistDTO.getName());
        verify(mockedPreparedStatement).setInt(2, playlistDTO.getId());
        verify(mockedPreparedStatement).executeUpdate();
    }

    @Test
    public void deletePlaylistTest() throws SQLException {
        // Arrange
        String expectedQuery = "DELETE FROM spotitube.playlists WHERE id = ?";

        when(mockedConnection.prepareStatement(expectedQuery)).thenReturn(mockedPreparedStatement);

        doNothing().when(mockedPreparedStatement).setInt(anyInt(), anyInt());

        // Act
        sut.delete(1);

        // Assert
        verify(mockedConnection).prepareStatement(expectedQuery);
        verify(mockedPreparedStatement).setInt(1, 1);
        verify(mockedPreparedStatement).executeUpdate();
    }
}