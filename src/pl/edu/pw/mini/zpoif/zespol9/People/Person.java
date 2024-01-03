package pl.edu.pw.mini.zpoif.zespol9.People;

public class Person {

    protected SignInData signInData;

    public Person(){

        this.signInData = new SignInData();
    }

    public SignInData getSignInData() {
        return signInData;
    }

}
