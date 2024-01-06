package pl.edu.pw.mini.zpoif.zespol9.People;

import java.security.SecureRandom;
import java.util.Random;

public class SignInData {

    private String login;
    private String password;

    public SignInData() {

        this.login = generateLogin();
        this.password = generatePassword();
    }

    private String generatePassword() {
        return login + generateRandomString();
    }

    private String generateLogin() {
        Random random = new Random();

        StringBuilder loginBuilder = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            int randomDigit = random.nextInt(10);
            loginBuilder.append(randomDigit);
        }

        return loginBuilder.toString();
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    private static String generateRandomString() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomString = new StringBuilder(10);

        SecureRandom secureRandom = new SecureRandom();
        for (int i = 0; i < 10; i++) {
            int randomIndex = secureRandom.nextInt(characters.length());
            randomString.append(characters.charAt(randomIndex));
        }
        return randomString.toString();
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
