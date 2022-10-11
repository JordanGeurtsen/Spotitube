package nl.han.oose.dea.jordan.beroepsproduct.datasource.dao;

import nl.han.oose.dea.jordan.beroepsproduct.domain.dto.TrackDTO;
import nl.han.oose.dea.jordan.beroepsproduct.domain.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrackDAO extends DAOBase<TrackDTO> {

    public List<TrackDTO> getAllTracksNotInPlaylist(int id) {
        try {
            Connection connection = getConnection();
            List<TrackDTO> result = resultSetMapper(executeResultStatement(getGetAllNotInPlaylistStatement(connection, id)));
            connection.close();
            return result;
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public PreparedStatement getGetAllNotInPlaylistStatement(Connection connection, int playlistID) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM spotitube.tracks WHERE id NOT IN (SELECT track FROM spotitube.tracklist WHERE playlist = ?)");
        statement.setInt(1, playlistID);
        return statement;
    }

    @Override
    public PreparedStatement getGetAllStatement(Connection connection) throws SQLException {
        return connection.prepareStatement("SELECT * FROM spotitube.tracks");
    }

    @Override
    public PreparedStatement getGetStatement(Connection connection, int id) throws SQLException {
        return connection.prepareStatement("SELECT * FROM spotitube.tracks WHERE id = ?");
    }

    @Override
    public PreparedStatement getInsertStatement(Connection connection, TrackDTO trackDTO) throws SQLException {
        return null;
    }

    @Override
    public PreparedStatement getUpdateStatement(Connection connection, TrackDTO trackDTO) throws SQLException {
        return null;
    }

    @Override
    public PreparedStatement getDeleteStatement(Connection connection, int id) throws SQLException {
        return null;
    }

    @Override
    public List<TrackDTO> resultSetMapper(ResultSet resultSet) throws SQLException {
        List<TrackDTO> result = new ArrayList<>();
        while (resultSet.next()) {
            TrackDTO track = new TrackDTO();
            track.setId(resultSet.getInt("id"));
            track.setTitle(resultSet.getString("title"));
            track.setPerformer(resultSet.getString("performer"));
            track.setDuration(resultSet.getInt("duration"));
            track.setAlbum(resultSet.getString("album"));
            track.setPlaycount(resultSet.getInt("playcount"));
            track.setPublicationDate(resultSet.getString("publication_date"));
            track.setDescription(resultSet.getString("description"));
            result.add(track);
        }
        return result;
    }
}