package nl.han.oose.dea.jordan.beroepsproduct.services.dto;

import jakarta.json.bind.annotation.JsonbProperty;

public class UserDTO {
    private String token;
    private String name;

    public UserDTO(String token, String name) {
        this.token = token;
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
