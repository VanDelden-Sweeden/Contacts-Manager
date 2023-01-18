import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class ContactsManager {
    public static void displayList(List<String> contactCollection) {
        for (int i = 0; i < contactCollection.size(); i += 1) {
            System.out.println((i + 1) + ": " + contactCollection.get(i));
        }
    }
    public static ArrayList<String> addContact(ArrayList<String> contactCollection, String userInput){
       contactCollection.add(userInput);
       return contactCollection;
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
        displayList(contactCollection);
        System.out.println();
        addContact((ArrayList<String>) contactCollection, "Luke 55555555");
        displayList(contactCollection);
    }
}
