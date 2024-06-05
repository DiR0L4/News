package training.web.bean;

import java.io.Serializable;
import java.util.Objects;

public class RegistrationInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String login;
    private String name;
    private String email;
    private String surname;
    private String country;
    private String phone;
    private String password;
    private String confirmPassword;

    public RegistrationInfo() {
    }

    public RegistrationInfo(String login, String name, String email, String surname, String country, String phone, String password, String confirmPassword) {
        this.login = login;
        this.name = name;
        this.email = email;
        this.surname = surname;
        this.country = country;
        this.phone = phone;
        this.password = password;
        this.confirmPassword = confirmPassword;
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

    public String getCountry() {
        return country;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
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

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegistrationInfo that = (RegistrationInfo) o;
        return Objects.equals(login, that.login) && Objects.equals(name, that.name) && Objects.equals(email, that.email) && Objects.equals(surname, that.surname) && Objects.equals(country, that.country) && Objects.equals(phone, that.phone) && Objects.equals(password, that.password) && Objects.equals(confirmPassword, that.confirmPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, name, email, surname, country, phone, password, confirmPassword);
    }
}
