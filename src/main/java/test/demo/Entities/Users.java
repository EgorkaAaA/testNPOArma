package test.demo.Entities;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long user_id;

    private String login;

    private String password;

    public Users() {
    }

    private boolean banned;

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public Users(String user_id, String login, String password) {
        this.user_id = Integer.parseInt(user_id);
        this.login = login;
        this.password = password;
        this.banned = false;
    }

    public long getUser_id() {
        return user_id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

}
