import java.io.*;

class StudentRecords {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public void addRecords() throws IOException {
        // Create or modify a file for the database
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("studentRecords.txt", true)));
        String name, studentClass;
        int rollNo, gradeNumber;
        boolean addMore;

        do {
            // Reading data from the user
            System.out.print("\nEnter Name: ");
            name = br.readLine();

            System.out.print("Enter Class: ");
            studentClass = br.readLine();

            System.out.print("Enter Roll Number: ");
            rollNo = Integer.parseInt(br.readLine());

            System.out.print("Enter Grade Number: ");
            gradeNumber = Integer.parseInt(br.readLine());

            // Writing data to the file
            pw.println(name);
            pw.println(studentClass);
            pw.println(rollNo);
            pw.println(gradeNumber);

            System.out.println("\nRecord added successfully!");

            // Ask if the user wants to add more records
            System.out.print("Do you want to add more records? (yes/no): ");
            String response = br.readLine();
            addMore = response.equalsIgnoreCase("yes");
        } while (addMore);

        pw.close();
    }

    public void readRecords() throws IOException {
        try {
            // Open the file for reading
            BufferedReader file = new BufferedReader(new FileReader("studentRecords.txt"));
            String name;
            int recordNumber = 1;

            // Read records from the file and display them
            while ((name = file.readLine()) != null) {
                System.out.println("Record No: " + recordNumber++);
                System.out.println("----------------");
                System.out.println("Name: " + name);
                System.out.println("Class: " + file.readLine());
                System.out.println("Roll Number: " + file.readLine());
                System.out.println("Grade Number: " + file.readLine());
                System.out.println();
            }

            file.close();
        } catch (FileNotFoundException e) {
            System.out.println("\nERROR: File not found!");
        }
    }

    public void clearRecords() throws IOException {
        // Create a blank file to clear existing records
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("studentRecords.txt")));
        pw.close();
        System.out.println("\nAll records have been cleared successfully!");
    }

    public void showMenu() throws IOException {
        boolean continueProgram = true;

        while (continueProgram) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Records");
            System.out.println("2. Display Records");
            System.out.println("3. Clear All Records");
            System.out.println("4. Exit");

            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(br.readLine());

            switch (choice) {
                case 1:
                    addRecords();
                    break;
                case 2:
                    readRecords();
                    break;
                case 3:
                    clearRecords();
                    break;
                case 4:
                    System.out.println("Exiting the program. Goodbye!");
                    continueProgram = false;
                    break;
                default:
                    System.out.println("\nInvalid choice! Please try again.");
            }

            if (continueProgram) {
                System.out.print("\nDo you want to perform another operation? (yes/no): ");
                String response = br.readLine();
                continueProgram = response.equalsIgnoreCase("yes");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        StudentRecords program = new StudentRecords();
        program.showMenu();
    }
}
