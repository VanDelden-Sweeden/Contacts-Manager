import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContactsManager {
    public static Scanner scanner = new Scanner(System.in);
    public static String userIn;
    public static String userIn2;
    private static int count;
    private static List<String> newContactCollection;
    private static ArrayList<Contact> newContactCollection2;

    public static String delete;

    public static String hypenNumber(String phoneNumber) {
        String str = phoneNumber;
        String str1 = str.substring(0, 3);
        String str2 = str.substring(3, phoneNumber.length());
        return str1 + "-" + str2;
    }

    public static void displayMenu(ArrayList<Contact> contactCollection) throws IOException {
        System.out.println("Please select a menu option: ");
        System.out.println("1 - Display Contacts");
        System.out.println("2 - Add Contact");
        System.out.println("3 - Search for a Contact");
        System.out.println("4 - Delete a Contact");
        System.out.println("5 - Exit");
        System.out.println("Enter an option (1, 2, 3, 4 or 5):");
        userIn = scanner.nextLine();
        if (userIn.equals("1")) {
            displayList(contactCollection);
        } else if (userIn.equals("2")) {
            addContact(contactCollection);
        } else if (userIn.equals("3")) {
            searchContact(contactCollection);
        } else if (userIn.equals("4")) {
            deleteContact(contactCollection);
        } else if (userIn.equals("5")) {
            System.out.println("Thank you for using Contacts Manager!");
            newContactCollection = new ArrayList<>();
            for (int i = 0; i < contactCollection.size(); i++) {
                newContactCollection.add(contactCollection.get(i).getName());
                newContactCollection.add(contactCollection.get(i).getPhoneNumber());
            }
            Path filepath = Paths.get("data", "contacts.txt");
            Files.write(filepath, newContactCollection);
        } else {
            System.out.println("Please select a valid entry.");
            displayMenu(contactCollection);
        }
    }

    public static void displayList(ArrayList<Contact> contactCollection) throws IOException {
        System.out.println("Name    |   Phone Number");
        System.out.println("------------------------");
        for (int i = 0; i < contactCollection.size(); i += 1) {
            System.out.println((i + 1) + ": " + contactCollection.get(i).getName() + "   |   " + hypenNumber(contactCollection.get(i).getPhoneNumber()));
        }
        System.out.println("------------------------");
        displayMenu(contactCollection);
    }

    public static void addContact(ArrayList<Contact> contactCollection) throws IOException {
        System.out.println("--------------------");
        System.out.println("Enter name: ");
        userIn = scanner.nextLine();
        System.out.println("Enter Phone: ");
        userIn2 = scanner.nextLine();
        contactCollection.add(new Contact(userIn, userIn2));
        System.out.println(userIn + " | " + hypenNumber(userIn2) + " has been added to contacts.");
        System.out.println("---------------------");
        displayMenu(contactCollection);
    }

    public static void searchContact(ArrayList<Contact> contactCollection) throws IOException {
        System.out.println("--------------------");
        System.out.println("Search for: ");
        userIn = scanner.nextLine();
        userIn = userIn.toLowerCase();
        System.out.println("Name    |   Phone Number");
        System.out.println("--------------------");
        for (Contact contact : contactCollection) {
            if (contact.getName().toLowerCase().contains(userIn) || contact.getPhoneNumber().contains(userIn)) {
                System.out.println((count + 1) + ": " + contact.getName() + " | " + hypenNumber(contact.getPhoneNumber()));
                count++;
            }
        }
        if (count == 0) {
            System.out.println("No contact contains " + userIn);
        }
        System.out.println("--------------------");
        displayMenu(contactCollection);
    }

    public static void deleteContact(ArrayList<Contact> contactCollection) throws IOException {
        System.out.println("--------------------");
        for (int i = 0; i < contactCollection.size(); i += 1) {
            System.out.println((i + 1) + ": " + contactCollection.get(i).getName() + "  |  " + hypenNumber(contactCollection.get(i).getPhoneNumber()));
        }
        System.out.println("Type the name of your contact and/or a full phone number: ");
        System.out.println("Contact to delete: ");
        userIn = scanner.nextLine();
        userIn = userIn.toLowerCase();
        count = 0;
        newContactCollection2 = new ArrayList<>();
        for (Contact contact : contactCollection) {
            if (contact.getPhoneNumber().contains(userIn) || contact.getName().toLowerCase().contains(userIn)) {
                System.out.println("{y/n} Do you wish to delete " + contact.getName() + " | " + hypenNumber(contact.getPhoneNumber()) + "?");
                count++;
                delete = scanner.nextLine();
                delete = delete.toLowerCase();
                if (delete.equals("y")) {
                    for (Contact contactD : contactCollection) {
                        if (!contactD.getPhoneNumber().contains(userIn) && !contactD.getName().toLowerCase().contains(userIn)) {
                            newContactCollection2.add(contactD);
                        }
                    }
                    System.out.println(contact.getName() + " | " + hypenNumber(contact.getPhoneNumber()) + " deleted.");
                    System.out.println("--------------------");
                    displayMenu(newContactCollection2);
                    break;
                } else {
                    System.out.println("Nothing deleted.");
                    System.out.println("Did you want to still delete a contact? (y/n)");
                    delete = scanner.nextLine();
                    delete = delete.toLowerCase();
                    if (delete.equals("y")) {
                        deleteContact(contactCollection);
                    } else {
                        System.out.println("--------------------");
                        displayMenu(contactCollection);
                    }
                }
            }
            if (count == 0) {
                System.out.println("No contacts found.");
                System.out.println("Did you want to still delete a contact? (y/n)");
                delete = scanner.nextLine();
                delete = delete.toLowerCase();
                if (delete.equals("y")) {
                    deleteContact(contactCollection);
                } else {
                    System.out.println("--------------------");
                    displayMenu(contactCollection);
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {
        String directory = "data";
        String contactsList = "contacts.txt";

        Path dataDirectory = Paths.get(directory);
        Path dataFile = Paths.get(directory, contactsList);

        if (Files.notExists(dataDirectory)) {
            Files.createDirectories(dataDirectory);
        }

        if (!Files.exists(dataFile)) {
            Files.createFile(dataFile);
        }

        Path filepath = Paths.get("data", "contacts.txt");
        List<String> stringCollection = Files.readAllLines(filepath);
        ArrayList<Contact> contactCollection = new ArrayList<>();

        for (int i = 0; i < stringCollection.size(); i += 2) {
            contactCollection.add(new Contact(stringCollection.get(i), stringCollection.get(i + 1)));
        }

//        displayList(contactCollection);
        System.out.println();
//        addContact((ArrayList<String>) contactCollection);
//        displayList(contactCollection);
        displayMenu(contactCollection);
    }
}
