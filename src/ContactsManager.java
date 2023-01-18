import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ContactsManager {
    public Scanner scanner;
    public static String userIn;

    public static void displayList(List<String> contactCollection) {
        for (int i = 0; i < contactCollection.size(); i += 1) {
            System.out.println((i + 1) + ": " + contactCollection.get(i));
        }
        displayMenu(contactCollection);
    }

    public static void addContact(List<String> contactCollection) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name and phone number: ");
        userIn = scanner.nextLine();
        contactCollection.add(userIn);
        displayMenu(contactCollection);
    }

    public static void displayMenu(List<String> contactCollection) {
        System.out.println("Please select a menu option: ");
        System.out.println("1 - Display Contacts");
        System.out.println("2 - Add Contact");
        System.out.println("3 - ");
        System.out.println("4 - ");
        System.out.println("5 - Exit");
//        userIn = "1";
        Scanner scanner = new Scanner(System.in);
        userIn = scanner.nextLine();
        if (userIn.equals("1")) {
            displayList(contactCollection);
        } else if (userIn.equals("2")) {
            addContact(contactCollection);
        } else if (userIn.equals("3")) {
            displayMenu(contactCollection);
        } else if (userIn.equals("4")) {
            displayMenu(contactCollection);
        } else if (userIn.equals("5")) {
            displayMenu(contactCollection);
        } else {
            displayMenu(contactCollection);
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
