package diary;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DiaryApp {
    private static final String FILE_NAME = "diary_entries.ser";
    private List<DiaryEntry> entries;
    private Scanner scanner;

    public DiaryApp() {
        entries = loadEntries();
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        DiaryApp app = new DiaryApp();
        app.run();
    }

    private void run() {
        while (true) {
            System.out.println("Diary Application");
            System.out.println("1. Add Entry");
            System.out.println("2. Edit Entry");
            System.out.println("3. Delete Entry");
            System.out.println("4. View Entries");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addEntry();
                    break;
                case 2:
                    editEntry();
                    break;
                case 3:
                    deleteEntry();
                    break;
                case 4:
                    viewEntries();
                    break;
                case 5:
                    saveEntries();
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addEntry() {
        System.out.print("Enter date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        System.out.print("Enter note: ");
        String note = scanner.nextLine();
        entries.add(new DiaryEntry(date, note));
        System.out.println("Entry added.");
    }

    private void editEntry() {
        System.out.print("Enter the date of the entry to edit: ");
        String date = scanner.nextLine();
        for (DiaryEntry entry : entries) {
            if (entry.getDate().equals(date)) {
                System.out.print("Enter new note: ");
                String newNote = scanner.nextLine();
                entry.setNote(newNote);
                System.out.println("Entry updated.");
                return;
            }
        }
        System.out.println("Entry not found.");
    }

    private void deleteEntry() {
        System.out.print("Enter the date of the entry to delete: ");
        String date = scanner.nextLine();
        entries.removeIf(entry -> entry.getDate().equals(date));
        System.out.println("Entry deleted.");
    }

    private void viewEntries() {
        if (entries.isEmpty()) {
            System.out.println("No entries found.");
        } else {
            for (DiaryEntry entry : entries) {
                System.out.println(entry);
                System.out.println("--------------------");
            }
        }
    }

    private List<DiaryEntry> loadEntries() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<DiaryEntry>) ois.readObject();
        } catch (FileNotFoundException e) {
            return new ArrayList<>(); // No file found, return empty list
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>(); // Return empty list on error
        }
    }

    private void saveEntries() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(entries);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}