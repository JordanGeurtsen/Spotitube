package nl.han.oose.dea.jordan.beroepsproduct.services;

import nl.han.oose.dea.jordan.beroepsproduct.services.dto.TracklistDTO;

public interface TrackService {

    TracklistDTO getTracksSuitableForPlaylist(int id, String token);
}
