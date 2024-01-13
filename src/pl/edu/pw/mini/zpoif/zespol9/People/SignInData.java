package pl.edu.pw.mini.zpoif.zespol9.People;

import pl.edu.pw.mini.zpoif.zespol9.System.PasswordCheckInterfejs;

import java.util.Random;

public class SignInData implements PasswordCheckInterfejs {

    private static Random random = new Random();
    private String name;
    private String surname;
    private String login;
    private String password;
    private final int id;
    private static int count = 10;

    public SignInData(String name, String surname) {
        this.name = name;
        this.surname = surname;
        id = count;
        random.setSeed(100 + count);
        this.password = generatePassword();
        count++;
        this.login = generateLogin();
    }

    private String generatePassword() {
        return generateRandomString();
    }

    private String generateLogin() {

        return name + surname + id;
    }

    public String getLogin() {
        return login;
    }

    private static String generateRandomString() {

        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomString = new StringBuilder(4);

        for (int i = 0; i < 5; i++) {
            int randomIndex = random.nextInt(characters.length());
            randomString.append(characters.charAt(randomIndex));
        }
        return randomString.toString();
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public boolean isPasswordCorrect(String string) {
        if (this.password.equals(string)) return true;
        return false;
    }
}
