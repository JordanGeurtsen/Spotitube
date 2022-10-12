package nl.han.oose.dea.jordan.beroepsproduct.resources;

import nl.han.oose.dea.jordan.beroepsproduct.domain.LoginService;
import nl.han.oose.dea.jordan.beroepsproduct.domain.TrackService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class TrackResourceTest {

    private TrackResource sut;
    private TrackService mockedTrackService;
    private LoginService mockedLoginService;

    @BeforeEach
    void setUp() {
        sut = new TrackResource();
        mockedTrackService = mock(TrackService.class);
        mockedLoginService = mock(LoginService.class);
    }
}