package nl.han.oose.dea.jordan.beroepsproduct.domain;

import nl.han.oose.dea.jordan.beroepsproduct.data.dao.TrackDAO;
import nl.han.oose.dea.jordan.beroepsproduct.data.dao.TracklistDAO;
import nl.han.oose.dea.jordan.beroepsproduct.domain.dto.TrackDTO;
import nl.han.oose.dea.jordan.beroepsproduct.domain.dto.TracklistDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TrackServiceTest {

    private TrackService sut;
    private TracklistDAO mockedTracklistDAO;
    private TrackDAO mockedTrackDAO;

    @BeforeEach
    public void setup() {
        sut = new TrackService();
        mockedTracklistDAO = mock(TracklistDAO.class);
        mockedTrackDAO = mock(TrackDAO.class);
        sut.setTrackListDAO(mockedTracklistDAO);
        sut.setTrackDAO(mockedTrackDAO);
    }

    @Test
    public void getTracksFromPlaylistReturnsTracklistDTO() {
        // Arrange
        List<TrackDTO> tracks = new ArrayList<>();
        TrackDTO trackDTO = new TrackDTO();
        trackDTO.setDuration(100);
        tracks.add(trackDTO);


        TracklistDTO tracklist = new TracklistDTO();
        tracklist.setTracks(tracks);

        when(mockedTracklistDAO.get(1)).thenReturn(Optional.of(tracklist));

        // Act
        var result = sut.getTracksFromPlaylist(1);

        // Assert
        verify(mockedTracklistDAO, times(2)).get(1);
        assertEquals(trackDTO.getDuration(), result.getTracks().get(0).getDuration());
        assertEquals(100, result.getLength());
    }

    @Test
    public void getTracksFromPlaylistReturnsEmptyTracklistDTO() {
        // Arrange
        when(mockedTracklistDAO.get(1)).thenReturn(Optional.empty());

        // Act
        var result = sut.getTracksFromPlaylist(1);

        // Assert
        verify(mockedTracklistDAO, times(1)).get(1);
        assertEquals(0, result.getLength());
    }

    @Test
    public void getSuitableTracksForPlaylistTest() {
        // Arrange
        List<TrackDTO> tracks = new ArrayList<>();
        TrackDTO trackDTO = new TrackDTO();
        trackDTO.setDuration(100);
        tracks.add(trackDTO);

        when(mockedTrackDAO.getAllTracksNotInPlaylist(1)).thenReturn(tracks);

        // Act
        TracklistDTO result = sut.getTracksSuitableForPlaylist(1);

        // Assert
        verify(mockedTrackDAO).getAllTracksNotInPlaylist(1);
        assertEquals(trackDTO.getDuration(), result.getTracks().get(0).getDuration());
    }
}