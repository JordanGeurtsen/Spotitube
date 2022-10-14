package nl.han.oose.dea.jordan.beroepsproduct.resources;

import jakarta.ws.rs.core.Response;
import nl.han.oose.dea.jordan.beroepsproduct.domain.LoginService;
import nl.han.oose.dea.jordan.beroepsproduct.domain.PlaylistService;
import nl.han.oose.dea.jordan.beroepsproduct.domain.dto.PlaylistDTO;
import nl.han.oose.dea.jordan.beroepsproduct.domain.dto.PlaylistResponseDTO;
import nl.han.oose.dea.jordan.beroepsproduct.domain.dto.TrackDTO;
import nl.han.oose.dea.jordan.beroepsproduct.domain.dto.TracklistDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PlaylistResourceTest {

    private PlaylistResource sut;
    private PlaylistService mockedPlaylistService;
    private LoginService mockedLoginService;
    private PlaylistResponseDTO playlistResponseDTO;


    @BeforeEach
    public void setup() {
        sut = new PlaylistResource();
        playlistResponseDTO = new PlaylistResponseDTO();
        mockedPlaylistService = mock(PlaylistService.class);
        mockedLoginService = mock(LoginService.class);
        sut.setPlaylistService(mockedPlaylistService);
        sut.setLoginService(mockedLoginService);
    }

    @Test
    public void doesGetAllPlaylistGiveResponseWithRightObjectTest() {
        // Arrange
        PlaylistResponseDTO expected = new PlaylistResponseDTO();
        PlaylistDTO playlist;
        playlist = new PlaylistDTO();
        playlist.setId(1);
        expected.getPlaylists().add(playlist);

        playlist = new PlaylistDTO();
        playlist.setId(2);
        expected.getPlaylists().add(playlist);

        when(mockedPlaylistService.getAllPlaylists()).thenReturn(expected);

        // Act
        Response response = sut.getPlaylists(" ");

        // Assert
        assertEquals(200, response.getStatus());
        assertEquals(expected, response.getEntity());
    }

    @Test
    public void doesAddPlaylistGiveResponseWithRightObjectTest() {
        // Arrange
        playlistResponseDTO = new PlaylistResponseDTO();
        PlaylistDTO playlist;
        playlist = new PlaylistDTO();
        playlist.setId(1);
        playlistResponseDTO.getPlaylists().add(playlist);

        playlist = new PlaylistDTO();
        playlist.setId(2);
        playlistResponseDTO.getPlaylists().add(playlist);

        playlist = new PlaylistDTO();
        playlist.setId(3);

        when(mockedPlaylistService.addPlaylist(playlist)).thenReturn(playlistResponseDTO);

        // Act
        Response response = sut.addPlaylist(playlist, " ");
        playlistResponseDTO.getPlaylists().add(playlist);

        // Assert
        assertEquals(200, response.getStatus());
        assertEquals(playlistResponseDTO, response.getEntity());
    }

    @Test
    public void doesDeletePlaylistGiveResponseWithRightObjectTest() {
        // Arrange
        playlistResponseDTO = new PlaylistResponseDTO();
        List<PlaylistDTO> playlists = new ArrayList<>();
        PlaylistDTO playlist;
        playlist = new PlaylistDTO();
        playlist.setId(1);
        playlists.add(playlist);

        playlist = new PlaylistDTO();
        playlist.setId(2);
        playlists.add(playlist);

        playlist = new PlaylistDTO();
        playlist.setId(3);
        playlistResponseDTO.setPlaylists(playlists);

        when(mockedPlaylistService.deletePlaylist(3)).thenReturn(playlistResponseDTO);

        // Act
        Response response = sut.deletePlaylist(3, " ");

        // Assert
        assertEquals(200, response.getStatus());
        assertEquals(playlistResponseDTO, response.getEntity());
    }

    @Test
    public void doesUpdatePlaylistGiveResponseWithRightObjectTest() {
        // Arrange
        playlistResponseDTO = new PlaylistResponseDTO();
        PlaylistDTO playlist;
        playlist = new PlaylistDTO();
        playlist.setId(1);
        playlistResponseDTO.getPlaylists().add(playlist);

        playlist = new PlaylistDTO();
        playlist.setId(2);
        playlistResponseDTO.getPlaylists().add(playlist);

        playlist = new PlaylistDTO();
        playlist.setId(3);
        playlistResponseDTO.getPlaylists().add(playlist);

        playlist = new PlaylistDTO();
        playlist.setId(3);

        when(mockedPlaylistService.updatePlaylist(3, playlist)).thenReturn(playlistResponseDTO);

        // Act
        Response response = sut.updatePlaylist(3, playlist, " ");
        playlistResponseDTO.getPlaylists().set(2, playlist);

        // Assert
        assertEquals(200, response.getStatus());
        assertEquals(playlistResponseDTO, response.getEntity());
    }

    @Test
    public void doesGetAllTracksFromPlaylistGiveResponseWithRightObjectTest() {
        // Arrange
        PlaylistResponseDTO expected = new PlaylistResponseDTO();
        PlaylistDTO playlist;
        playlist = new PlaylistDTO();
        playlist.setId(1);
        expected.getPlaylists().add(playlist);

        playlist = new PlaylistDTO();
        playlist.setId(2);
        expected.getPlaylists().add(playlist);

        TrackDTO track = new TrackDTO();
        expected.getPlaylists().get(0).getTracks().getTracks().add(track);

        when(mockedPlaylistService.getTracksFromPlaylist(1)).thenReturn(expected.getPlaylists().get(0).getTracks());

        // Act
        Response response = sut.getTracksFromPlaylist(1, " ");

        // Assert
        assertEquals(200, response.getStatus());
        assertEquals(expected.getPlaylists().get(0).getTracks(), response.getEntity());
    }

    @Test
    public void doesAddTrackToPlaylistGiveResponseWithRightObjectTest() {
        // Arrange
        TracklistDTO expected = new TracklistDTO();
        TrackDTO track = new TrackDTO();
        expected.getTracks().add(track);

        when(mockedPlaylistService.addTrackToPlaylist(1, track)).thenReturn(expected);

        // Act
        Response response = sut.addTrackToPlaylist(1, track, " ");

        // Assert
        assertEquals(200, response.getStatus());
        assertEquals(expected, response.getEntity());
    }

    @Test
    public void doesRemoveTrackFromPlaylistGiveResponseWithRightObjectTest() {
        // Arrange
        TracklistDTO expected = new TracklistDTO();
        TrackDTO track = new TrackDTO();
        expected.getTracks().add(track);

        when(mockedPlaylistService.removeTrackFromPlaylist(1, 1)).thenReturn(expected);

        // Act
        Response response = sut.removeTrackFromPlaylist(1, 1, " ");

        // Assert
        assertEquals(200, response.getStatus());
        assertEquals(expected, response.getEntity());
    }
}