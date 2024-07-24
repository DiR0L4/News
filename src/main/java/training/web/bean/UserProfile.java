package training.web.bean;

import java.io.Serializable;
import java.util.Objects;

public class UserProfile implements Serializable {
    private static final long serialVersionUID = 1L;

    private String login;
    private String name;
    private String email;
    private String surname;
    private String country;
    private String role;

    public UserProfile() {
    }

    public UserProfile(String login, String name, String email, String surname, String country, String role) {
        this.login = login;
        this.name = name;
        this.email = email;
        this.surname = surname;
        this.country = country;
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProfile that = (UserProfile) o;
        return Objects.equals(login, that.login) && Objects.equals(name, that.name) && Objects.equals(email, that.email) && Objects.equals(surname, that.surname) && Objects.equals(country, that.country) && Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, name, email, surname, country, role);
    }
}
