package training.web.bean;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String login;
    private String email;
    private int roleId;
    private String image;

    public User() {
    }

    public User(int id, String login, String email, int roleId, String image) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.roleId = roleId;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public int getRoleId() {
        return roleId;
    }

    public String getImage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
