package nl.han.oose.dea.jordan.beroepsproduct.services.dto;

public class LoginRequestDTO {
    private String user;
    private String password;

    public LoginRequestDTO() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
