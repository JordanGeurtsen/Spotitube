package nl.han.oose.dea.jordan.beroepsproduct.resources;

import jakarta.ws.rs.core.Response;
import nl.han.oose.dea.jordan.beroepsproduct.domain.LoginService;
import nl.han.oose.dea.jordan.beroepsproduct.domain.PlaylistService;
import nl.han.oose.dea.jordan.beroepsproduct.domain.dto.PlaylistDTO;
import nl.han.oose.dea.jordan.beroepsproduct.domain.dto.PlaylistResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlaylistResourceTest {

    private PlaylistResource sut;
    private PlaylistService mockedPlaylistService;
    private LoginService mockedLoginService;

    @BeforeEach
    public void setup() {
        sut = new PlaylistResource();
        mockedPlaylistService = Mockito.mock(PlaylistService.class);
        mockedLoginService = Mockito.mock(LoginService.class);
        sut.setPlaylistService(mockedPlaylistService);
    }

    @Test
    public void testGetPlaylists() {
        // Arrange
        String token = "1234-1234-1234-1234";

        sut.setLoginService(mockedLoginService);

        PlaylistResponseDTO expected = new PlaylistResponseDTO();
        PlaylistDTO playlistOne = new PlaylistDTO();
        playlistOne.setId(1);
        playlistOne.setName("Playlist 1");
        playlistOne.setOwner(true);
        expected.addPlaylist(playlistOne);

        PlaylistDTO playlistTwo = new PlaylistDTO();
        playlistTwo.setId(2);
        playlistTwo.setName("Playlist 2");
        playlistTwo.setOwner(true);
        expected.addPlaylist(playlistTwo);

        Mockito.when(mockedPlaylistService.getAllPlaylists()).thenReturn(expected);

        // Act
        Response response = sut.getPlaylists(token);

        // Assert
        assertEquals(200, response.getStatus());
        assertEquals(expected, response.getEntity());
    }
}