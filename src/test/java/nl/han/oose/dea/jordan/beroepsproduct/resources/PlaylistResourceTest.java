package nl.han.oose.dea.jordan.beroepsproduct.resources;

import jakarta.ws.rs.core.Response;
import nl.han.oose.dea.jordan.beroepsproduct.services.HardCodedLoginService;
import nl.han.oose.dea.jordan.beroepsproduct.services.HardCodedPlaylistService;
import nl.han.oose.dea.jordan.beroepsproduct.services.LoginService;
import nl.han.oose.dea.jordan.beroepsproduct.services.PlaylistService;
import nl.han.oose.dea.jordan.beroepsproduct.services.dto.PlaylistDTO;
import nl.han.oose.dea.jordan.beroepsproduct.services.dto.PlaylistResponseDTO;
import nl.han.oose.dea.jordan.beroepsproduct.services.dto.TracklistDTO;
import nl.han.oose.dea.jordan.beroepsproduct.services.exceptions.InvalidTokenException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PlaylistResourceTest {

    private PlaylistResource sut;
    private PlaylistService mockedPlaylistService;
    private HardCodedLoginService loginService;
    private TracklistDTO tracklist;

    @BeforeEach
    public void setup() {
        sut = new PlaylistResource();
        mockedPlaylistService = Mockito.mock(PlaylistService.class);
        loginService = new HardCodedLoginService();
        sut.setPlaylistService(mockedPlaylistService);
        tracklist = new TracklistDTO();
    }

    @Test
    public void testGetPlaylists() {
        // Arrange
        String token = "1234-1234-1234-1234";

        sut.setLoginService(loginService);

        PlaylistResponseDTO expected = new PlaylistResponseDTO();
        PlaylistDTO playlistOne = new PlaylistDTO();
        tracklist.setTracks(new ArrayList<>());
        playlistOne.setTracks(tracklist);
        playlistOne.setId(1);
        playlistOne.setName("Playlist 1");
        playlistOne.setOwner(true);
        expected.addPlaylist(playlistOne);

        PlaylistDTO playlistTwo = new PlaylistDTO();
        tracklist.setTracks(new ArrayList<>());
        playlistTwo.setTracks(tracklist);
        playlistTwo.setId(2);
        playlistTwo.setName("Playlist 2");
        playlistTwo.setOwner(false);
        expected.addPlaylist(playlistTwo);

        Mockito.when(mockedPlaylistService.getAllPlaylists()).thenReturn(expected);

        // Act
        Response response = sut.getPlaylists(token);

        // Assert
        assertEquals(200, response.getStatus());
        assertEquals(expected, response.getEntity());
    }


    @Test
    public void testIfExceptionIsThrownWhenTokenIsWrong(){
        // Arrange
        String token = "1234-1234-14-1234";

        sut.setLoginService(loginService);

        //Act
        Exception exception = assertThrows(InvalidTokenException.class, () -> {
            sut.getPlaylists(token);
        });

        // Arrange
        String expectedMessage = "Invalid token";
        String actualMessage = exception.getMessage();

        // Assert
        assertTrue(actualMessage.contains(expectedMessage));

    }
}