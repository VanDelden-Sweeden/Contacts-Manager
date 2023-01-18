import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContactsManager {
    public static Scanner scanner = new Scanner (System.in);
    public static String userIn;
    private static int count;
    private static List<String> newContactCollection;
    public static String delete;

    public static void displayMenu(List<String> contactCollection) throws IOException {
        System.out.println("Please select a menu option: ");
        System.out.println("1 - Display Contacts");
        System.out.println("2 - Add Contact");
        System.out.println("3 - Search for a Contact");
        System.out.println("4 - Delete a Contact");
        System.out.println("5 - Exit");
        System.out.println("Enter the number of your selection: ");
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
            Path filepath = Paths.get("data", "contacts.txt");
            Files.write(filepath, contactCollection);
        } else {
            System.out.println("Please select a valid entry.");
            displayMenu(contactCollection);
        }
    }

    public static void displayList(List<String> contactCollection) throws IOException {
        for (int i = 0; i < contactCollection.size(); i += 1) {
            System.out.println((i + 1) + ": " + contactCollection.get(i));
        }
        displayMenu(contactCollection);
    }

    public static void addContact(List<String> contactCollection) throws IOException {
        System.out.println("Enter name and phone number: ");
        userIn = scanner.nextLine();
        contactCollection.add(userIn);
        displayMenu(contactCollection);
    }

    public static void searchContact(List<String> contactCollection) throws IOException {
        System.out.println("Search for: ");
        userIn = scanner.nextLine();
        for (String contact : contactCollection) {
            if (contact.contains(userIn)) {
                System.out.println((count+1) + ": contact = " + contact);
                count++;
            }
        }
        displayMenu(contactCollection);
    }

    public static void deleteContact(List<String> contactCollection) throws IOException {
        for (int i = 0; i < contactCollection.size(); i += 1) {
            System.out.println((i + 1) + ": " + contactCollection.get(i));
        }
        System.out.println("Type the name of your contact and/or a full phone number: ");
        System.out.println("Contact to delete: ");
        userIn = scanner.nextLine();
        count = 0;
        newContactCollection = new ArrayList<>();
        for (String contact : contactCollection) {
            if (contact.contains(userIn)) {
                System.out.println("{y/n} Do you wish to delete " + contact + "?");
                count++;
                delete = scanner.nextLine();
                if (delete.equals("y")) {
                    for (String contactD : contactCollection) {
                        if (!contactD.contains(userIn)) {
                        newContactCollection.add(contactD);
                        }
                        System.out.println(newContactCollection);
                    } displayMenu(newContactCollection);
                } else {
                    deleteContact(contactCollection);
                }
            }
        }
        if (count == 0) {
            System.out.println(userIn + " was not found.");
            deleteContact(contactCollection);
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
        List<String> contactCollection = Files.readAllLines(filepath);
//        displayList(contactCollection);
        System.out.println();
//        addContact((ArrayList<String>) contactCollection);
//        displayList(contactCollection);
        displayMenu(contactCollection);
    }
}
