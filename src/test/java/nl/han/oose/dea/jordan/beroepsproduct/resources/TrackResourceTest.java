package nl.han.oose.dea.jordan.beroepsproduct.resources;

import nl.han.oose.dea.jordan.beroepsproduct.domain.LoginService;
import nl.han.oose.dea.jordan.beroepsproduct.domain.TrackService;
import nl.han.oose.dea.jordan.beroepsproduct.domain.dto.TracklistDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TrackResourceTest {

    private TrackResource sut;
    private TrackService mockedTrackService;
    private LoginService mockedLoginService;

    @BeforeEach
    void setUp() {
        sut = new TrackResource();
        mockedTrackService = mock(TrackService.class);
        mockedLoginService = mock(LoginService.class);
        sut.setTrackResource(mockedTrackService);
        sut.setLoginService(mockedLoginService);
    }

    @Test
    void checkIfReturnsTracklistTest() {
        // Arrange
        String token = "1234-1234-1234-1234";

        doNothing().when(mockedLoginService).authorize(token);
        when(mockedTrackService.getTracksSuitableForPlaylist(1)).thenReturn(new TracklistDTO());

        // Act
        var response = sut.getTracksForPlaylist(token, 1);

        // Assert

    }
}