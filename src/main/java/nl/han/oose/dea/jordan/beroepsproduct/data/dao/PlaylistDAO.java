package nl.han.oose.dea.jordan.beroepsproduct.data.dao;

import nl.han.oose.dea.jordan.beroepsproduct.domain.dto.PlaylistDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDAO extends DAOBase<PlaylistDTO> {

    @Override
    public PreparedStatement getGetAllStatement(Connection connection) throws SQLException {
        return connection.prepareStatement("SELECT * FROM spotitube.playlists");
    }

    @Override
    public PreparedStatement getGetStatement(Connection connection ,int id) throws SQLException {
        return connection.prepareStatement("SELECT * FROM spotitube.playlists WHERE id = " + id);
    }

    @Override
    public PreparedStatement getInsertStatement(Connection connection, PlaylistDTO playlistDTO) throws SQLException {
        PreparedStatement statement =  connection.prepareStatement("INSERT INTO spotitube.playlists (name, owner) VALUES (?, ?)");
        statement.setString(1, playlistDTO.getName());
        statement.setInt(2, playlistDTO.getOwnerID());
        return statement;
    }

    @Override
    public PreparedStatement getUpdateStatement(Connection connection, PlaylistDTO playlistDTO) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE spotitube.playlists SET name = ? WHERE id = ?");
        statement.setString(1, playlistDTO.getName());
        statement.setInt(2, playlistDTO.getId());
        return statement;
    }

    @Override
    public PreparedStatement getDeleteStatement(Connection connection, int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM spotitube.playlists WHERE id = ?");
        statement.setInt(1, id);
        return statement;
    }

    @Override
    public List<PlaylistDTO> resultSetMapper(ResultSet resultSet) throws SQLException {
        List<PlaylistDTO> playlistDTOList = new ArrayList<>();
        while (resultSet.next()) {
            PlaylistDTO playlistDTO = new PlaylistDTO();
            playlistDTO.setId(resultSet.getInt("id"));
            playlistDTO.setName(resultSet.getString("name"));
            playlistDTO.setOwnerID(resultSet.getInt("owner"));
            playlistDTOList.add(playlistDTO);
        }
        resultSet.close();
        return playlistDTOList;
    }
}
