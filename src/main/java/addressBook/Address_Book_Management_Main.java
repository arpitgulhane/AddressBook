package addressBook;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Address_Book_Management_Main {

    //    public static ArrayList<Contact> cotacts_array=new ArrayList<>();
    List<Contact> contactsInAnycity;

    public static void main(String[] args) throws IOException {
        System.out.println("      ~ Welcome to Address Book ~      ");
        AddressBook addressBook = new AddressBook();
        addressBook.addAddressBook();
    }


}
