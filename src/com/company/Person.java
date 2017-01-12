package com.company;

public class Person {
    static int id = Main.people.size();   //TODO zrobic to inaczej
    private String firstName;
    private String secondName;
    private String phoneNumber;
    private String email;
    private String address;

    Person(String firstName, String secondName, String phoneNumber, String email, String address) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        id++;
    }

    String getFirstName() {
        return firstName;
    }

    String getSecondName() {
        return secondName;
    }

    String getPhoneNumber() {
        return phoneNumber;
    }

    String getEmail() {
        return email;
    }

    String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "\n\nImię: " + getFirstName() + "\nNazwisko: " + getSecondName() + "\nNr tel.: " + getPhoneNumber() + "\nEmail: " +
                getEmail() + "\nAdres: " + getAddress();
    }
}
