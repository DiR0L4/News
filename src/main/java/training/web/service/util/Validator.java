package training.web.service.util;

import training.web.bean.AuthInfo;
import training.web.bean.RegistrationInfo;

import java.util.regex.Pattern;

public class Validator {

    // Регулярные выражения для валидации
    private final Pattern NAME_PATTERN = Pattern.compile("^[A-Za-zА-Яа-яЁё]{2,30}$");
    private final Pattern LOGIN_PATTERN = Pattern.compile("^[A-Za-z0-9_]{5,20}$");
    private final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    private final Pattern PHONE_PATTERN = Pattern.compile("^\\+?[0-9]{10,15}$");
    private final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$");

    public boolean validateRegistration(RegistrationInfo registrationInfo){
        if (!validateName(registrationInfo.getSurname())) {
            return false;
        }
        if (!validateName(registrationInfo.getName())) {
            return false;
        }
        if (!validateLogin(registrationInfo.getLogin())) {
            return false;
        }
        if (!validateEmail(registrationInfo.getEmail())) {
            return false;
        }
        if (!validatePhone(registrationInfo.getPhone())) {
            return false;
        }
        if (!validatePassword(registrationInfo.getPassword())) {
            return false;
        }
        if (!validateConfirmPassword(registrationInfo.getPassword(), registrationInfo.getConfirmPassword())) {
            return false;
        }
        return true;
    }

    public boolean validateAuth(AuthInfo authInfo){
        if (!validateEmail(authInfo.getEmail())) {
            return false;
        }
        if (!validatePassword(authInfo.getPassword())) {
            return false;
        }
        return true;
    }
    private boolean validateName(String name) {
        return NAME_PATTERN.matcher(name).matches();
    }

    private boolean validateLogin(String login) {
        return LOGIN_PATTERN.matcher(login).matches();
    }

    private boolean validateEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    private boolean validatePhone(String phone) {
        return PHONE_PATTERN.matcher(phone).matches();
    }

    private boolean validatePassword(String password) {
        return PASSWORD_PATTERN.matcher(password).matches();
    }

    private boolean validateConfirmPassword(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }
}

