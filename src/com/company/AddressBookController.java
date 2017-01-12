package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


class AddressBookController {

    private enum Action {
        ADD_PERSON,
        FIND_PERSON,
        DISPLAY_ALL,
        EXIT
    }

    private enum FilterOption {
        FIRST_NAME,
        SECOND_NAME
    }

    private DataFile dataFile;
    private Scanner in;
    private List<Person> people;

    private AddressBookController(DataFile dataFile) {
        in = new Scanner(System.in);
        this.dataFile = dataFile;
        try {
            people = dataFile.loadAll();        //dodaje wszystkie osoby z pliku do listy 'people'
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    AddressBookController(String fileName) {
        this(new DataFile(fileName));
    }

    private List<Person> findPerson(String searchString, FilterOption filter) {
        switch (filter) {
            case FIRST_NAME:
                return people.stream().filter(person -> person.getFirstName().equalsIgnoreCase(searchString)).collect(Collectors.toList());
            case SECOND_NAME:
                return people.stream().filter(person -> person.getSecondName().equalsIgnoreCase(searchString)).collect(Collectors.toList());
            default:
                System.out.println("Invalid filter option");
                return new ArrayList<>();
        }
    }

    void run() {
        while (true) {
            System.out.println();
            showMainMenu();
            System.out.println();
            Action action = askForInput();                                                  //wybiera opcje z menu glownego
            switch (action) {
                case ADD_PERSON:
                    Person person = getPersonInformation();
                    dataFile.save(person);                                                  //dodaje osobe do pliku
                    people.add(person);                                                     //dodaje osobe do listy 'people'
                    break;
                case FIND_PERSON:
                    System.out.println("Enter name: ");
                    FilterOption selectedFilter = showAndChooseFilterOption();
                    String searchString = in.nextLine().trim();                             //nazwa do wyszukania
                    List<Person> filteredPeople = findPerson(searchString, selectedFilter); //tworzy liste wyszukanych osob
                    if (filteredPeople.size() == 0) {
                        System.out.println("No matches");
                    } else {
                        for (Person p : filteredPeople)                                     //wyswietla wyszukane osoby
                            System.out.println(p);
                    }
                    break;
                case DISPLAY_ALL:
                    System.out.println(people);
                    System.out.println();
                    break;
                case EXIT:
                    System.out.println("Exiting Program");
                    System.exit(0);
                    break;
            }
        }
    }

    private static void showMainMenu() {
        System.out.println("1. Dodaj osobę");
        System.out.println("2. Wyszukaj osobę");
        System.out.println("3. Wyświetl wszystkie osoby");
        System.out.println("4. Zakończ program");
    }

    private Action askForInput() {
        String choice;
        do {
            choice = in.nextLine().trim();
            switch (choice) {
                case "1":
                    return Action.ADD_PERSON;
                case "2":
                    return Action.FIND_PERSON;
                case "3":
                    return Action.DISPLAY_ALL;
                case "4":
                    return Action.EXIT;
                default:
                    System.out.println("Podaj numer od 1 do 4");
            }
        } while (!choice.equals("4"));
        return null; //should never reach here
    }

    private Person getPersonInformation() {
        System.out.println("Podaj imie: ");
        String firstName = in.nextLine();
        System.out.println("Podaj nazwisko: ");
        String secondName = in.nextLine();
        System.out.println("Podaj numer telefonu: ");
        String phoneNumber = in.nextLine();
        System.out.println("Podaj email: ");
        String email = in.nextLine();
        System.out.println("Podaj adres: ");
        String address = in.nextLine();
        return new Person(firstName, secondName, phoneNumber, email, address);
    }

    private FilterOption showAndChooseFilterOption() {
        System.out.println("1. Find with first name");
        System.out.println("2. Find with second name");
        System.out.println();
        String choice;
        do {
            choice = in.nextLine();
            switch (choice) {
                case "1":
                    return FilterOption.FIRST_NAME;
                case "2":
                    return FilterOption.SECOND_NAME;
                default:
                    System.out.print("Choose 1 or 2");
            }
        } while (!choice.equals("1") && !choice.equals("2"));
        return null; //should never reach here
    }
}
