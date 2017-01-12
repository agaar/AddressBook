package com.company;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        AddressBookController controller = new AddressBookController("Addresses.txt");
        controller.run();
    }
}
