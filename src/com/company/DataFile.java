package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class DataFile {

    private File file;

    DataFile(String fileName) {
        this.file = new File(fileName);
    }

    void save(Person person) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(person.getId() + "\r\n" + person.getFirstName() + "\r\n" + person.getSecondName() + "\r\n" +
                    person.getPhoneNumber() + "\r\n" + person.getEmail() + "\r\n" + person.getAddress() + "\r\n\r\n");
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    List<Person> loadAll() throws IOException {
        List<Person> people = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while ((reader.readLine()) != null) {
                Person person = new Person(reader.readLine(), reader.readLine(), reader.readLine(), reader.readLine(),
                        reader.readLine());
                people.add(person);                                 //dodaje osoobę do listy
                reader.readLine();                                  //wczytuje pustą linię
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return people;
    }
}
