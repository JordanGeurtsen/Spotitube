package nl.han.oose.dea.jordan.beroepsproduct.data.dao;

import nl.han.oose.dea.jordan.beroepsproduct.data.utils.DatabaseConnector;
import nl.han.oose.dea.jordan.beroepsproduct.domain.dto.PlaylistDTO;
import nl.han.oose.dea.jordan.beroepsproduct.domain.dto.TrackDTO;
import nl.han.oose.dea.jordan.beroepsproduct.domain.dto.TracklistDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TracklistDAOTest {

    private TracklistDAO sut;
    private DatabaseConnector mockedDatabaseConnector;
    private Connection mockedConnection;
    private PreparedStatement mockedPreparedStatement;
    private ResultSet mockedResultSet;


    @BeforeEach
    public void setUp() throws SQLException {
        sut = new TracklistDAO();
        mockedDatabaseConnector = mock(DatabaseConnector.class);
        mockedConnection = mock(Connection.class);
        mockedPreparedStatement = mock(PreparedStatement.class);
        mockedResultSet = mock(ResultSet.class);

        when(mockedDatabaseConnector.getConnection()).thenReturn(mockedConnection);
        sut.setDatabaseConnector(mockedDatabaseConnector);
    }

    @Test
    public void insertPlaylistWithTrackAndOfflineAvailableTest() throws SQLException {
        // Arrange
        String expectedQuery = "INSERT INTO spotitube.tracklist (playlist, track, offline_available) VALUES (?, ?, ?)";

        PlaylistDTO playlistDTO = new PlaylistDTO();
        playlistDTO.setId(1);

        TrackDTO trackDTO = new TrackDTO();
        trackDTO.setId(1);

        when(mockedConnection.prepareStatement(anyString())).thenReturn(mockedPreparedStatement);

        // Act
        sut.insertWithPlaylistTrackAndOfflineAvailable(playlistDTO.getId(), trackDTO.getId(), true);

        // Assert
        verify(mockedConnection).prepareStatement(expectedQuery);
        verify(mockedPreparedStatement).setInt(1, playlistDTO.getId());
        verify(mockedPreparedStatement).setInt(2, trackDTO.getId());
        verify(mockedPreparedStatement).setBoolean(3, true);
        verify(mockedPreparedStatement).executeUpdate();
    }

    @Test
    public void deletePlaylistWithTrackTest() throws SQLException {
        // Arrange
        String expectedQuery = "DELETE FROM spotitube.tracklist WHERE playlist = ? AND track = ?";

        PlaylistDTO playlistDTO = new PlaylistDTO();
        playlistDTO.setId(1);

        TrackDTO trackDTO = new TrackDTO();
        trackDTO.setId(1);

        when(mockedConnection.prepareStatement(expectedQuery)).thenReturn(mockedPreparedStatement);

        // Act
        sut.deleteWithPlayListAndTrack(playlistDTO.getId(), trackDTO.getId());

        // Assert
        verify(mockedConnection).prepareStatement(expectedQuery);
        verify(mockedPreparedStatement).setInt(1, playlistDTO.getId());
        verify(mockedPreparedStatement).setInt(2, trackDTO.getId());
        verify(mockedPreparedStatement).executeUpdate();
    }

    @Test
    public void getTracklistTest() throws SQLException {
        // Arrange
        String expectedQuery = "SELECT * FROM spotitube.tracklist tl INNER JOIN spotitube.tracks t on t.id = tl.track WHERE playlist = ?";

        PlaylistDTO playlistDTO = new PlaylistDTO();
        playlistDTO.setId(1);

        TracklistDTO tracklistDTO = new TracklistDTO();

        TrackDTO trackDTO = new TrackDTO();
        trackDTO.setId(1);
        trackDTO.setTitle("test");
        trackDTO.setPerformer("test");
        trackDTO.setDuration(1);
        trackDTO.setAlbum("test");
        trackDTO.setPlaycount(1);
        trackDTO.setPublicationDate("test");
        trackDTO.setDescription("test");
        trackDTO.setOfflineAvailable(true);
        tracklistDTO.getTracks().add(trackDTO);
        playlistDTO.setTracks(tracklistDTO);

        when(mockedConnection.prepareStatement(expectedQuery)).thenReturn(mockedPreparedStatement);
        when(mockedPreparedStatement.executeQuery()).thenReturn(mockedResultSet);
        when(mockedResultSet.next()).thenReturn(true, false);
        when(mockedResultSet.getInt("track")).thenReturn(trackDTO.getId());
        when(mockedResultSet.getBoolean("offline_available")).thenReturn(true);

        // Act
        sut.get(playlistDTO.getId());

        // Assert
        verify(mockedConnection).prepareStatement(expectedQuery);
        verify(mockedPreparedStatement).setInt(1, playlistDTO.getId());
        verify(mockedPreparedStatement).executeQuery();
    }

    @Test
    public void getGetAllStatementReturnsNull() throws SQLException {
        assertNull(sut.getGetAllStatement(mockedConnection));
    }

    @Test
    public void getInsertStatementReturnsNull() throws SQLException {
        assertNull(sut.getInsertStatement(mockedConnection, new TracklistDTO()));
    }

    @Test
    public void getUpdateStatementReturnsNull() throws SQLException {
        assertNull(sut.getUpdateStatement(mockedConnection, new TracklistDTO()));
    }

    @Test
    public void getDeleteStatementReturnsNull() throws SQLException {
        assertNull(sut.getDeleteStatement(mockedConnection, 1));
    }
}