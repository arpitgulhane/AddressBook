package addressBook;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class AddressBook {
    //---------------------- use in contacts
    public static ArrayList<Contact> cotacts_array = new ArrayList<>();
    private Map<String, List<Contact>> cityDictionary = new HashMap<>();
    private Map<String, List<Contact>> stateDictionary = new HashMap<>();


    //------------------------Book Logic Start ---------------------
    AddressBook addressBook ;
    public static ArrayList<String> addressBook_arr = new ArrayList<String>();
    public static Hashtable<String, ArrayList<Contact>> addressBook_hashtable = new Hashtable<String, ArrayList<Contact>>();
    static void addAddressBook() {

        Scanner get = new Scanner(System.in);
        int getMoreBook;

        do {
            System.out.println("\n       ~ ~ ~ Enter BOOK Name ~ ~ ~         ");
            String getBookName = get.next();

// ---------- To Add AddressBook 2nd time (check)----------------------------------------------
            for (String addBook : addressBook_arr) {
//                System.out.println("Nothing" + addBook);
                if (getBookName.equals(addBook)) {
                    System.out.println(" AddressBook already present ");
                    return;
                }}
// ---------- To Add AddressBook 1st time (check)----------------------------------------------
                AddressBook addressBook = new AddressBook();
                addressBook_arr.add(getBookName);
                System.out.println("Address Book Added ...2"+addressBook_arr);

                //--------T0 add contact
                Scanner sc = new Scanner(System.in);
                System.out.println("\n             ~ ~ ~ ADD NEW CONTACT press 1-9  ::  To Exit press (0 ZERO) ~ ~ ~             ");
                int exit = sc.nextInt();
                Scanner sc2 = new Scanner(System.in);
                while (exit != 0) {
                    System.out.print("Enter command \t\t( Add , Edit , Display , Searchcitystate ," +
                            "\t SortByName , SortByCity , SortByState , SortByZip , \n" +
                            "\t \t\t \t\t \tContactsByCity , ContactsByState , ContactCountByCity , ContactCountByState ," +
                            "WriteToFile , "+" ReadFromFile "+" readCSV ,"+" writeCSV ,"+
                            "\t Delete , Exit ): \n");
                    String command = sc2.nextLine();
                    switch (command) {
                        case "Add":
                            addressBook.addPersonDetails();
                            break;
                        case "Edit":
                            addressBook.editPersonDetails();
                            break;
                        case "Display":
                            addressBook.display();
                            break;
                        case "Searchcitystate":
                            addressBook.searchDetailsByCityOrState();
                            break;
                        case "SortByName":
                            addressBook.sortByName();
                            break;
                        case "SortByCity":
                            addressBook.sortByCity();
                            break;
                        case "SortByState":
                            addressBook.sortByState();
                            break;
                        case "SortByZip":
                            addressBook.sortByZip();
                            break;
                        case "ContactsByCity":
                            addressBook.getContactsByCity();
                            break;
                        case "ContactsByState":
                            addressBook.getContactsByState();
                            break;
                        case "ContactCountByCity":
                            addressBook.getContactCountByCity();
                            break;
                        case "ContactCountByState":
                            addressBook.getContactCountByState();
                            break;
                        case "WriteToFile":
                            addressBook.writeToFile();
                            break;
                        case "ReadFromFile":
                            addressBook.readFromFile();
                            break;
                        case "readCSV":
                            try {
                                addressBook.readCSV();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            } catch (CsvException e) {
                                throw new RuntimeException(e);
                            }
                            break;
                        case "writeCSV":
                            try {
                                addressBook.writeCSV();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            } catch (CsvException e) {
                                throw new RuntimeException(e);
                            }
                            break;
                        case "Delete":
                            addressBook.deletePersonDetails();
                            break;
                        case "Exit":
                            break;
//                                   addressBook.printContacts();
                    }
                    System.out.println("\n             ~ ~ ~ ADD NEW CONTACT press 1-9  ::  To EXIT press (0 ZERO) ~ ~ ~             ");
                    exit = sc.nextInt();
                }
            System.out.println("\n             ~ ~ ~ press 1 to 9 add onemore ADDRESS BOOK  ::  Exit ( 0 ZERO ) ~ ~ ~              ");
            getMoreBook = get.nextInt();
        } while (getMoreBook == 1);
    }
 //------------------------Book Logic End  ---------------------

                //------------------        Adding person Details       -----------------------
    void addPersonDetails() {
//        AddressBook addressBook=new AddressBook();
        Scanner sc = new Scanner(System.in);
        System.out.println("          Enter First Name :");
        String name = sc.nextLine();
        if (isDuplicate(name)) {
            System.out.println(name + " these  Already in contacts ...");
            return;
        }
        System.out.println("          Enter Last Name :");
        String last_Name = sc.nextLine();
        System.out.println("          Enter City :");
        String city = sc.nextLine();
        System.out.println("          Enter State :");
        String state = sc.nextLine();
        System.out.println("          Enter Number :");
        String number = sc.nextLine();
        System.out.println("          Enter email :");
        String email = sc.nextLine();
        System.out.println("          Enter address :");
        String address = sc.nextLine();
        System.out.println("          Enter zip :");
        String zip = sc.nextLine();
        Contact contact = new Contact(name, last_Name, city, state, number, email, address, zip);
        addContactToDictionary(contact);
        cotacts_array.add(contact);
        System.out.println("Contact Added Successfully...");
    }

    //------------------        Edit person Details      -----------------------
    static void editPersonDetails() {
        System.out.println("\n    Enter existing 'Email' and 'Number' to EDIT    \n");
        Scanner sc = new Scanner(System.in);
        String checkE = sc.nextLine();
        String checkNum = sc.nextLine();

        for (Contact contact : cotacts_array) {
            if (checkE.equals(contact.getEmail()) && checkNum.equals(contact.getNumber())) {
                System.out.println("          Enter Update First Name :");
                String New_name = sc.nextLine();
                System.out.println("          Enter Update Last Name :");
                String New_last_Name = sc.nextLine();
                System.out.println("          Enter Update City :");
                String New_city = sc.nextLine();
                System.out.println("          Enter Update Number :");
                String New_number = sc.nextLine();
                System.out.println("          Enter Update email :");
                String New_email = sc.nextLine();
                System.out.println("          Enter Update Address :");
                String new_address = sc.nextLine();
                System.out.println("          Enter Update zip :");
                String new_zip = sc.nextLine();
                contact.setName(New_name);
                contact.setLast_N(New_last_Name);
                contact.setCity(New_city);
                contact.setState(New_city);
                contact.setNumber(New_number);
                contact.setEmail(New_email);
                contact.setAddress(new_address);
                contact.setZip(new_zip);
                System.out.println("Update ...");
            } else {
                System.out.println("Sorry arpit Data Not found");
            }
        }
    }

    //------------------        Delete person Details       -----------------------
    static void deletePersonDetails() {
        System.out.println("\n    Enter Name to delete that CONTACT : \n");
        Scanner sc = new Scanner(System.in);
        String deleteNameContact = sc.nextLine();
//        Contact contact;
        for (Contact contact : cotacts_array) {
            if (deleteNameContact.equals(contact.getName())) {
                cotacts_array.remove(contact);
                System.out.println("Contact Deleted ....");
                return;
            } else {
                System.out.println("arpit Values you enter Not Found");
            }
        }
    }

    boolean isDuplicate(String checkDuplicate) {
        checkDuplicate = checkDuplicate.toLowerCase();
        for (Contact contact : cotacts_array) {
            if (checkDuplicate.equals(contact.getName().toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    static void searchDetailsByCityOrState() {
        System.out.println("\n    Enter City Or State to search :\n");
        Scanner sc = new Scanner(System.in);
        String search_CityOrState = sc.nextLine();
        int i = 1;
        for (Contact contact : cotacts_array) {
            if (search_CityOrState.equals(contact.getCity()) || search_CityOrState.equals(contact.getState())) {
                System.out.println(i + " Contacts are :");
                System.out.println(contact.getName());
                System.out.println(contact.getLast_N());
                System.out.println(contact.getCity());
                System.out.println(contact.getState());
                System.out.println(contact.getEmail());
                System.out.println(contact.getNumber());
                System.out.println(contact.getAddress());
                System.out.println(contact.getZip());
                i++;
            } else {
                System.out.println("Not Found By " + search_CityOrState);
            }
        }
    }

    public void addContactToDictionary(Contact contact) {
        String city = contact.getCity();
        String state = contact.getState();
        if (!cityDictionary.containsKey(city)) {
            cityDictionary.put(city, new ArrayList<>());
        }
        cityDictionary.get(city).add(contact);

        if (!stateDictionary.containsKey(state)) {
            stateDictionary.put(state, new ArrayList<>());
        }
        stateDictionary.get(state).add(contact);
    }

    //--------------------New by Streams ------------------------------
    public void getContactsByCity() {
        System.out.println("\nTo get contact by city enter City Name :\n");
        Scanner sc = new Scanner(System.in);
        String city = sc.nextLine();
        cityDictionary.getOrDefault(city, Collections.emptyList()).forEach(n -> System.out.println(n));
    }

    public void getContactsByState() {
        System.out.println("\nTo get contact by city enter City Name :\n");
        Scanner sc = new Scanner(System.in);
        String state = sc.nextLine();
        stateDictionary.getOrDefault(state, Collections.emptyList()).forEach(n -> System.out.println(n));
    }

    public void getContactCountByCity() {
        System.out.println("\nTo get count enter City Name :\n");
        Scanner sc = new Scanner(System.in);
        String city = sc.nextLine();
        int count = cityDictionary.getOrDefault(city, Collections.emptyList()).size();
        System.out.println(count);
    }

    public void getContactCountByState() {
        System.out.println("\nTo get count enter State Name :\n");
        Scanner sc = new Scanner(System.in);
        String state = sc.nextLine();
        int count = stateDictionary.getOrDefault(state, Collections.emptyList()).size();
        System.out.println(count);
    }


    // Sort contacts by Zip using Java Streams
    public void sortByZip() {
        cotacts_array.stream()
                .sorted(Comparator.comparing(Contact::getZip))
                .collect(Collectors.toList());
        System.out.println("\n After Sort Zip : \n");
        this.display();
    }
    public void sortByName() {
        Comparator<Contact> comparator = Comparator.comparing(Contact::getName)
                .thenComparing(Contact::getLast_N);
        Collections.sort(this.cotacts_array, comparator);
        System.out.println("\n After Sort Name : \n");
        this.display();
    }

    // Sort contacts by City using Java Streams
    public void sortByCity() {
        cotacts_array.stream()
                .sorted(Comparator.comparing(Contact::getCity))
                .collect(Collectors.toList());
        System.out.println("\n After Sort City : \n");
        this.display();
    }

    // Sort contacts by State using Java Streams
    public void sortByState() {
        cotacts_array.stream()
                .sorted(Comparator.comparing(Contact::getState))
                .collect(Collectors.toList());
        System.out.println("\n After Sort State : \n");
        this.display();

    }

    //-------------------------------------------------------------------------
    // Write the address book to a file
    public void writeToFile() {
        System.out.println("\n Enter File Path to Write : ex: C:\\Users\\USER\\Desktop\\arpit2.txt\n");
        Scanner sc = new Scanner(System.in);
        String filepath = sc.nextLine();
////        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
        try (FileWriter fileWriter = new FileWriter(filepath);) {
            fileWriter.write(String.valueOf(cotacts_array));
            System.out.println("Write Contacts To File ~ "+filepath);
        } catch (IOException e) {
            System.out.println("path not valid");
            e.printStackTrace();
        }
    }

    public void readFromFile() {
        System.out.println("\n Enter file Path to read : ex: C:\\Users\\USER\\Desktop\\arpit2.txt\n");
        Scanner sc = new Scanner(System.in);
        String filepath = sc.nextLine();

//        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
        try (BufferedReader br  = new BufferedReader(new FileReader(filepath));) {
            String str;
            System.out.println("Read from "+filepath);
            while ((str = br.readLine()) != null){
                System.out.println(str);
            }
            br.close();
        } catch (IOException e) {
            System.out.println("path not valid");
            e.printStackTrace();
        }
    }

    public void display() {
        if (cotacts_array.isEmpty()) {
            System.out.println("No contacts in address book");
        } else {
            cotacts_array.stream().forEach(n -> System.out.println(n));
            }
//        for (Contact contact : cotacts_array) {
//            if (contact.getName() != null) {
////                cotacts_array.remove(contact);
//                System.out.println(contact);
//            } else {
//                System.out.println("Values Not Found");
//                return;
//            }
//        }
    }

    public void readCSV() throws IOException, CsvException {
        System.out.println("Enter CSV Path to read ex: C:\\Users\\USER\\Desktop\\NCR_Faculty Profile.csv");
        Scanner sc = new Scanner(System.in);
        String psthCSV = sc.nextLine();
        FileReader fileReader = new FileReader(psthCSV);
        //create csvReader object //stream i
        CSVReader csvReader = new CSVReaderBuilder(fileReader).build();
        List<String[]> allData = csvReader.readAll();
        for (String[] row : allData) {
            for (String cell : row) {
                System.out.print(cell+"         ");
            }
            System.out.println();
        }
        csvReader.close();
    }

    public void writeCSV() throws IOException, CsvException {
        System.out.println("Enter CSV Path to write ex: C:\\Users\\USER\\Desktop\\NCR_Faculty Profile.csv");
        Scanner sc = new Scanner(System.in);
        String psthCSV = sc.nextLine();
        FileWriter outputfile = new FileWriter(psthCSV);
        CSVWriter writter = new CSVWriter(outputfile);

        List<String[]> lines = new ArrayList<>();
        String[] line =new String[]{
          "NAME","LAST NAME","CITY","STATE","NUMBER","EMAIL","ADDRESS","ZIP"
        };
        lines.add(line);
        for (Contact person : cotacts_array) {
            line = new String[] {
                    person.getName(),
                    person.getLast_N(),
                    person.getCity(),
                    person.getState(),
                    person.getNumber(),
                    person.getEmail(),
                    person.getAddress(),
                    person.getZip()
            };
            lines.add(line);
        }
        writter.writeAll(lines);
        writter.flush();
        writter.close();
    }


}
