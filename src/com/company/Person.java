package com.company;

import java.util.concurrent.atomic.AtomicInteger;

public class Person {

    private static AtomicInteger nextID = new AtomicInteger(1);

    private int id ;
    private String firstName;
    private String secondName;
    private String phoneNumber;
    private String email;
    private String address;

    private Person(int id, String firstName, String secondName, String phoneNumber, String email, String address) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }

    Person(String firstName, String secondName, String phoneNumber, String email, String address) {
        this(nextID.getAndIncrement(), firstName, secondName, phoneNumber, email, address);
    }

    int getId() {
        return id;
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
        return "\n\nID: " + getId() +"\nImię: " + getFirstName() + "\nNazwisko: " + getSecondName() + "\nNr tel.: " + getPhoneNumber() + "\nEmail: " +
                getEmail() + "\nAdres: " + getAddress();
    }


}
