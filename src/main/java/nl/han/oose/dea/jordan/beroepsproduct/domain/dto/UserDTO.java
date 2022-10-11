package nl.han.oose.dea.jordan.beroepsproduct.domain.dto;

public class UserDTO {
    private int id;
    private String token;
    private String user;

    public UserDTO() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
