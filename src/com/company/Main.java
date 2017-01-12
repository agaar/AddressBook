package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class Main {
    private static Scanner sc = new Scanner(System.in);
    static List<Person> people = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        File file = new File("Addresses.txt");
        readPeopleListFromFile(file);
        showMainMenu();
        askForInput(file);
    }


    private static void findPerson(File file) throws IOException {
        System.out.println("1. Wyszukaj po imieniu");
        System.out.println("2. Wyszukaj po nazwisku");

        String choice;
        do {
            switch (choice = sc.nextLine().trim()) {
                case "1":
                    findByFirstName();
                    break;
                case "2":
                    findBySecondName();
                    break;
                default:
                    System.out.print("Wybierz 1 lub 2: ");
            }
        } while (!choice.equals("1") && !choice.equals("2"));
        System.out.println();
        showMainMenu();
        askForInput(file);
    }

    private static void findByFirstName() {
        System.out.print("Podaj imię: ");
        String firstNameToFind = sc.nextLine().trim();
        int matches = 0;
        for (Person person : people) {
            if (person.getFirstName().equalsIgnoreCase(firstNameToFind)) {
                System.out.println(person);
                matches++;
            }
        }
        if (matches <= 0) {
            System.out.println("Nie ma osoby o takim imieniu");
        }
    }

    private static void findBySecondName() {
        System.out.print("Podaj nazwisko: ");
        String secondNameToFind = sc.nextLine().trim();
        int matches = 0;
        for (Person person : people) {
            if (person.getSecondName().equalsIgnoreCase(secondNameToFind)) {
                System.out.println(person);
                matches++;
            }
        }
        if (matches <= 0) {
            System.out.println("Nie ma osoby o takim nazwisku");
        }
    }

    private static void addPerson(File file) throws IOException {
        System.out.println("Podaj imie: ");
        String firstName = sc.nextLine();
        System.out.println("Podaj nazwisko: ");
        String secondName = sc.nextLine();
        System.out.println("Podaj numer telefonu: ");
        String phoneNumber = sc.nextLine();
        System.out.println("Podaj email: ");
        String email = sc.nextLine();
        System.out.println("Podaj adres: ");
        String address = sc.nextLine();

        Person person = new Person(firstName, secondName, phoneNumber, email, address);
        addToFile(person, file);
        people.add(person);
        System.out.println("Dodano osobę nr: " + person.getId() + person);
        System.out.println();
        showMainMenu();
        askForInput(file);
    }

    private static void addToFile(Person person, File file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(person.getId() + "\r\n" +person.getFirstName() + "\r\n" + person.getSecondName() + "\r\n" + person.getPhoneNumber() + "\r\n" + person.getEmail() +
                    "\r\n" + person.getAddress() + "\r\n\r\n");
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private static List<Person> readPeopleListFromFile(File file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            //String name = null;
            while ((reader.readLine()) != null) {
                Person person = new Person(reader.readLine(), reader.readLine(), reader.readLine(), reader.readLine(), reader.readLine());
                people.add(person);                                 //dodaje osoobę do listy
                reader.readLine();                                  //wczytuje pustą linię
            }
            return people;
        } catch (IOException e) {
            System.out.println(e);
        }
        return null;
    }

    private static void askForInput(File file) throws IOException {
        String choice;
        do {
            choice = sc.nextLine().trim();
            switch (choice) {
                case "1":
                    addPerson(file);
                    break;
                case "2":
                    findPerson(file);
                    break;
                case "3":
                    System.out.println(people);
                    System.out.println();
                    showMainMenu();
                    askForInput(file);
                    break;
                case "4":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Podaj numer od 1 do 4");
            }
        } while (!choice.equals("4"));
    }

    private static void showMainMenu() {
        System.out.println("1. Dodaj osobę");
        System.out.println("2. Wyszukaj osobę");
        System.out.println("3. Wyświetl wszystkie osoby");
        System.out.println("4. Zakończ program");
    }
}
