package training.web.bean;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String login;
    private String name;
    private String email;
    private String surname;
    private String image;

    public User() {
    }

    public User(int id, String login, String name, String email, String surname, String image) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.email = email;
        this.surname = surname;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getSurname() {
        return surname;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
