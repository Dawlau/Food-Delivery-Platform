package ro.localhost.Models;

import java.util.ArrayList;

public abstract class Person {

    protected String firstName;
    protected String lastName;
    protected String birthday;
    protected String phoneNumber;
    protected String email;

    public Person(){
        this("", "", "", "", "");
    }

    public Person(String firstName, String lastName, String birthday, String phoneNumber, String email) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setBirthday(birthday);
        this.setPhoneNumber(phoneNumber);
        this.setEmail(email);
    }

    public Person(Person other){
        this(other.firstName, other.lastName, other.birthday, other.phoneNumber, other.email);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
