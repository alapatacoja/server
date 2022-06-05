package es.ieslvareda.server.dto;

public class LoginDTO {

    private String user;
    private String password;

    public LoginDTO(String user, String password) {
        this.user = user;
        this.password = password;
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

    @Override
    public String toString() {
        return "LoginDTO{" +
                "user='" + user + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
