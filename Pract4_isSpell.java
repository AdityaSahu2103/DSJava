import java.util.*;
import java.io.*;
public class Pract4_isSpell {
    public static void loadDictionary(Hashtable<String, Integer> dictionary){
        try(BufferedReader reader = new BufferedReader(new FileReader("dictionary.txt"))){
            String line;
            while((line=reader.readLine())!=null){
                dictionary.put(line.trim().toLowerCase(), 1);
            }
        }
        catch(IOException e){
            System.out.println("Error loading dictionary:"+e.getMessage());}
        }
    public static void addWordsToDictionary(Hashtable<String, Integer> dictionary, String word){
        dictionary.put(word.trim().toLowerCase(), 1);
        System.out.println("Word added to dictionary successfully");
    }
    public static void searchWordInDictionary(Hashtable<String, Integer> dictionary, String word) {
        if (dictionary.containsKey(word.trim().toLowerCase())) {
        System.out.println("Word found in dictionary.");
        } else {
        System.out.println("Word not found in dictionary");
        }
        }
        public static void removeWordFromDictionary(Hashtable<String, Integer> dictionary, String word) {
            if (dictionary.containsKey(word.trim().toLowerCase())) {
            dictionary.remove(word.trim().toLowerCase());
            System.out.println("Word removed from dictionary.");
            } else {
            System.out.println("Word not found in dictionary.");
            }
            }
            public static void saveDictionaryToFile(Hashtable<String, Integer> dictionary) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("dictionary.txt"))) {
                for (String word : dictionary.keySet()) {
                writer.write(word);
                writer.newLine();
                }
                } catch (IOException e) {
                System.out.println("Error saving dictionary to file:" + e.getMessage());
                }
                }
    public static void main(String[] args) {
        Hashtable <String, Integer> dictionary=new Hashtable<>();
        Scanner scanner = new Scanner(System.in);
        loadDictionary(dictionary);
        while (true) {
            System.out.println("1. Enter Word");
            System.out.println("2. Search Word");
            System.out.println("3. Remove Word");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (choice) {
            case 1:
            System.out.print("Enter the word to add to dictionary: ");
            String wordToAdd = scanner.nextLine();
            addWordsToDictionary(dictionary, wordToAdd);
            saveDictionaryToFile(dictionary);
            break;
            case 2:
            System.out.print("Enter the word to search in dictionary:");
            String wordToSearch = scanner.nextLine();
            searchWordInDictionary(dictionary, wordToSearch);
            break;
            case 3:
            System.out.print("Enter the word to remove from dictionary: ");
            String wordToRemove = scanner.nextLine();
            removeWordFromDictionary(dictionary, wordToRemove);
            saveDictionaryToFile(dictionary);
            break;
            case 4:
            System.out.println("Exiting program...");
            System.exit(0);
            break;
            default:
            System.out.println("Invalid choice. Please enter a valid option.");
            }
            }
    }
    
}
