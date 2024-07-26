import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Timestamp;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static DatabaseHandler dbHandler = new DatabaseHandler();
    public static void main(String[] args) {

        try {

            File file = new File("src/funn.txt");
            if (!file.exists()) {
                System.out.println("File not found!");
                return;
            }

            System.out.println("File found, starting to read...");

            Scanner scanner = new Scanner(file);
            String section = "";

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                System.out.println("Reading line: " + line);

                if (line.equals("Personer:")) {
                    section = "Person";
                    scanner.nextLine(); // Skip the count line
                } else if (line.equals("Museer:")) {
                    section = "Museum";
                    scanner.nextLine(); // Skip the count line
                } else if (line.equals("Funn:")) {
                    section = "Funn";
                } else if (line.equals("-------")) {
                    // Skip separator lines
                } else {
                    switch (section) {
                        case "Person":
                            System.out.println("Processing person section...");
                            processPerson(line, scanner);
                            break;
                        case "Museum":
                            System.out.println("Processing museum section...");
                            processMuseum(line, scanner);
                            break;
                        case "Funn":
                            System.out.println("Processing funn section...");
                            processFunn(line, scanner);
                            break;
                    }
                }
            }

            System.out.println("Finished reading file.");
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void processPerson(String firstLine, Scanner scanner) {
        int id = Integer.parseInt(firstLine);
        String name = scanner.nextLine().trim();
        String phone = scanner.nextLine().trim();
        String email = scanner.nextLine().trim();
        System.out.println("Parsed Person: " + id + ", " + name + ", " + phone + ", " + email);
        dbHandler.insertPerson(id, name, phone, email);
    }

    private static void processMuseum(String firstLine, Scanner scanner) {
        int id = Integer.parseInt(firstLine);
        String name = scanner.nextLine().trim();
        String location = scanner.nextLine().trim();
        System.out.println("Parsed Museum: " + id + ", " + name + ", " + location);
        dbHandler.insertMuseum(id, name, location);
    }

    private static void processFunn(String firstLine, Scanner scanner) {
        int id = Integer.parseInt(firstLine);
        String coordinates = scanner.nextLine().trim();
        int personId = Integer.parseInt(scanner.nextLine().trim());
        String dateString = scanner.nextLine().trim();
        int estimatedYear = Integer.parseInt(scanner.nextLine().trim());

        String museumIdLine = scanner.nextLine().trim();
        Integer museumId = null;
        if (!museumIdLine.isEmpty()) {
            museumId = Integer.parseInt(museumIdLine);
        }

        String type = scanner.nextLine().trim();

        switch (type) {
            case "Mynt":
                System.out.println("Processing Mynt...");
                processMynt(id, coordinates, personId, dateString, estimatedYear, museumId, scanner);
                break;
            case "Smykke":
                System.out.println("Processing Smykke...");
                processSmykke(id, coordinates, personId, dateString, estimatedYear, museumId, scanner);
                break;
            case "Våpen":
                System.out.println("Processing Våpen...");
                processVaapen(id, coordinates, personId, dateString, estimatedYear, museumId, scanner);
                break;
        }
    }

    private static void processMynt(int id, String coordinates, int personId, String dateString, int estimatedYear, Integer museumId, Scanner scanner) {
        int diameter = Integer.parseInt(scanner.nextLine().trim());
        String material = scanner.nextLine().trim();
        System.out.println("Parsed Mynt: " + id + ", " + coordinates + ", " + personId + ", " + dateString + ", " + estimatedYear + ", " + museumId + ", " + diameter + ", " + material);
        Timestamp date = Timestamp.valueOf(dateString + " 00:00:00");
        dbHandler.insertMynt(id, coordinates, personId, date, estimatedYear, museumId, diameter, material);
    }

    private static void processSmykke(int id, String coordinates, int personId, String dateString, int estimatedYear, Integer museumId, Scanner scanner) {
        String jewelryType = scanner.nextLine().trim();
        int estimatedValue = Integer.parseInt(scanner.nextLine().trim());
        String fileName = scanner.nextLine().trim();
        System.out.println("Parsed Smykke: " + id + ", " + coordinates + ", " + personId + ", " + dateString + ", " + estimatedYear + ", " + museumId + ", " + jewelryType + ", " + estimatedValue + ", " + fileName);
        Timestamp date = Timestamp.valueOf(dateString + " 00:00:00");
        dbHandler.insertSmykke(id, coordinates, personId, date, estimatedYear, museumId, jewelryType, estimatedValue, fileName);
    }

    private static void processVaapen(int id, String coordinates, int personId, String dateString, int estimatedYear, Integer museumId, Scanner scanner) {
        String weaponType = scanner.nextLine().trim();
        String material = scanner.nextLine().trim();
        int weight = Integer.parseInt(scanner.nextLine().trim());
        System.out.println("Parsed Vaapen: " + id + ", " + coordinates + ", " + personId + ", " + dateString + ", " + estimatedYear + ", " + museumId + ", " + weaponType + ", " + material + ", " + weight);
        Timestamp date = Timestamp.valueOf(dateString + " 00:00:00");
        dbHandler.insertVaapen(id, coordinates, personId, date, estimatedYear, museumId, weaponType, material, weight);
    }
}