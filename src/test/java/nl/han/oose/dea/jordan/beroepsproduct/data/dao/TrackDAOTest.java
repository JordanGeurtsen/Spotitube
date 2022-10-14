package nl.han.oose.dea.jordan.beroepsproduct.data.dao;

import nl.han.oose.dea.jordan.beroepsproduct.data.utils.DatabaseConnector;
import nl.han.oose.dea.jordan.beroepsproduct.domain.dto.TrackDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class TrackDAOTest {

    private TrackDAO sut;
    private DatabaseConnector mockedDatabaseConnector;
    private Connection mockedConnection;
    private PreparedStatement mockedPreparedStatement;
    private ResultSet mockedResultSet;

    @BeforeEach
    public void setup() throws SQLException {
        sut = new TrackDAO();
        mockedDatabaseConnector = mock(DatabaseConnector.class);
        mockedConnection = mock(Connection.class);
        mockedPreparedStatement = mock(PreparedStatement.class);
        mockedResultSet = mock(ResultSet.class);

        when(mockedDatabaseConnector.getConnection()).thenReturn(mockedConnection);

        sut.setDatabaseConnector(mockedDatabaseConnector);
    }

    @Test
    public void getAllTracksTest() throws SQLException {
        // Arrange
        List<TrackDTO> expected = new ArrayList<>();
        TrackDTO track = new TrackDTO();
        track.setId(1);
        track.setTitle("title 1");
        track.setPerformer("performer 1");
        track.setDuration(1);
        track.setAlbum("album 1");
        track.setPlaycount(1);
        track.setPublicationDate("2022");
        track.setDescription("description 1");
        expected.add(track);

        when(mockedConnection.prepareStatement(anyString())).thenReturn(mockedPreparedStatement);
        when(mockedPreparedStatement.executeQuery()).thenReturn(mockedResultSet);
        when(mockedResultSet.next()).thenReturn(true, false);
        when(mockedResultSet.getInt("id")).thenReturn(track.getId());
        when(mockedResultSet.getString("title")).thenReturn(track.getTitle());
        when(mockedResultSet.getString("performer")).thenReturn(track.getPerformer());
        when(mockedResultSet.getInt("duration")).thenReturn(track.getDuration());
        when(mockedResultSet.getString("album")).thenReturn(track.getAlbum());
        when(mockedResultSet.getInt("playcount")).thenReturn(track.getDuration());
        when(mockedResultSet.getString("publication_date")).thenReturn(track.getPublicationDate());
        when(mockedResultSet.getString("description")).thenReturn(track.getDescription());

        // Act
        List<TrackDTO> result = sut.getAll();

        // Assert
        assertEquals(expected.get(0).getId(), result.get(0).getId());
        assertEquals(expected.get(0).getTitle(), result.get(0).getTitle());
        assertEquals(expected.get(0).getPerformer(), result.get(0).getPerformer());
        assertEquals(expected.get(0).getDuration(), result.get(0).getDuration());
        assertEquals(expected.get(0).getAlbum(), result.get(0).getAlbum());
        assertEquals(expected.get(0).getPlaycount(), result.get(0).getPlaycount());
        assertEquals(expected.get(0).getPublicationDate(), result.get(0).getPublicationDate());
        assertEquals(expected.get(0).getDescription(), result.get(0).getDescription());
    }

    @Test
    public void getAllTracksNotInPlaylist() throws SQLException {
        // Arrange
        List<TrackDTO> expected = new ArrayList<>();
        TrackDTO track = new TrackDTO();
        track.setId(1);
        track.setTitle("title 1");
        track.setPerformer("performer 1");
        track.setDuration(1);
        track.setAlbum("album 1");
        track.setPlaycount(1);
        track.setPublicationDate("2022");
        track.setDescription("description 1");
        expected.add(track);

        when(mockedConnection.prepareStatement(anyString())).thenReturn(mockedPreparedStatement);
        when(mockedPreparedStatement.executeQuery()).thenReturn(mockedResultSet);
        when(mockedResultSet.next()).thenReturn(true, false);
        when(mockedResultSet.getInt("id")).thenReturn(track.getId());
        when(mockedResultSet.getString("title")).thenReturn(track.getTitle());
        when(mockedResultSet.getString("performer")).thenReturn(track.getPerformer());
        when(mockedResultSet.getInt("duration")).thenReturn(track.getDuration());
        when(mockedResultSet.getString("album")).thenReturn(track.getAlbum());
        when(mockedResultSet.getInt("playcount")).thenReturn(track.getDuration());
        when(mockedResultSet.getString("publication_date")).thenReturn(track.getPublicationDate());
        when(mockedResultSet.getString("description")).thenReturn(track.getDescription());

        // Act
        List<TrackDTO> result = sut.getAllTracksNotInPlaylist(1);

        // Assert
        assertEquals(expected.get(0).getId(), result.get(0).getId());
        assertEquals(expected.get(0).getTitle(), result.get(0).getTitle());
        assertEquals(expected.get(0).getPerformer(), result.get(0).getPerformer());
        assertEquals(expected.get(0).getDuration(), result.get(0).getDuration());
        assertEquals(expected.get(0).getAlbum(), result.get(0).getAlbum());
        assertEquals(expected.get(0).getPlaycount(), result.get(0).getPlaycount());
        assertEquals(expected.get(0).getPublicationDate(), result.get(0).getPublicationDate());
        assertEquals(expected.get(0).getDescription(), result.get(0).getDescription());
    }

    @Test
    public void getTrackTest() throws SQLException {
        // Arrange
        TrackDTO expected = new TrackDTO();
        expected.setId(1);
        expected.setTitle("title 1");
        expected.setPerformer("performer 1");
        expected.setDuration(1);
        expected.setAlbum("album 1");
        expected.setPlaycount(1);
        expected.setPublicationDate("2022");
        expected.setDescription("description 1");

        when(mockedConnection.prepareStatement(anyString())).thenReturn(mockedPreparedStatement);
        when(mockedPreparedStatement.executeQuery()).thenReturn(mockedResultSet);
        when(mockedResultSet.next()).thenReturn(true, false);
        when(mockedResultSet.getInt("id")).thenReturn(expected.getId());
        when(mockedResultSet.getString("title")).thenReturn(expected.getTitle());
        when(mockedResultSet.getString("performer")).thenReturn(expected.getPerformer());
        when(mockedResultSet.getInt("duration")).thenReturn(expected.getDuration());
        when(mockedResultSet.getString("album")).thenReturn(expected.getAlbum());
        when(mockedResultSet.getInt("playcount")).thenReturn(expected.getDuration());
        when(mockedResultSet.getString("publication_date")).thenReturn(expected.getPublicationDate());
        when(mockedResultSet.getString("description")).thenReturn(expected.getDescription());

        // Act
        TrackDTO result = sut.get(1).get();

        // Assert
        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getTitle(), result.getTitle());
        assertEquals(expected.getPerformer(), result.getPerformer());
        assertEquals(expected.getDuration(), result.getDuration());
        assertEquals(expected.getAlbum(), result.getAlbum());
        assertEquals(expected.getPlaycount(), result.getPlaycount());
        assertEquals(expected.getPublicationDate(), result.getPublicationDate());
        assertEquals(expected.getDescription(), result.getDescription());
    }

    @Test
    public void insertTrackReturnsNull() throws SQLException {
        assertNull(sut.getInsertStatement(mockedConnection, new TrackDTO()));
    }

    @Test
    public void updateTrackReturnsNull() throws SQLException {
        assertNull(sut.getUpdateStatement(mockedConnection, new TrackDTO()));
    }

    @Test
    public void deleteTrackReturnsNull() throws SQLException {
        assertNull(sut.getDeleteStatement(mockedConnection, 1));
    }
}