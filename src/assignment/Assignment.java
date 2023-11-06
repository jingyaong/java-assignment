package assignment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Assignment {

    private static Map<Integer, Double> tableOrderPrices = new HashMap<>();
    private static final String STAFF_FILENAME = "staff.txt"; // Text file to store staff information
    private static final String FOOD_FILENAME = "food.txt"; // Text file to store food information
    private static final String BEVERAGE_FILENAME = "beverage.txt"; // Text file to store food information
    private static final String CUSTOMER_FILENAME = "customer.txt";

    public static void logo() {
        System.out.println(
                " _______  __   __  __   __  __   __  _______  _______  __   __  _______  _______  ___      _______  _______  ___   __    _ ");
        System.out.println(
                "|       ||  |_|  ||  | |  ||  | |  ||   _   ||       ||  |_|  ||       ||       ||   |    |       ||  _    ||   | |  |  | |");
        System.out.println(
                "|   _   ||       ||  |_|  ||  |_|  ||  |_|  ||    ___||       ||   _   ||    ___||   |    |   _   || |_|   ||   | |   |_| |");
        System.out.println(
                "|  | |  ||       ||       ||       ||       ||   |___ |       ||  | |  ||   | __ |   |    |  | |  ||       ||   | |       |");
        System.out.println(
                "|  |_|  | |     | |_     _||       ||       ||    ___||       ||  |_|  ||   ||  ||   |___ |  |_|  ||  _   | |   | |  _    |");
        System.out.println(
                "|       ||   _   |  |   |  |   _   ||   _   ||   |___ | ||_|| ||       ||   |_| ||       ||       || |_|   ||   | | | |   |");
        System.out.println(
                "|_______||__| |__|  |___|  |__| |__||__| |__||_______||_|   |_||_______||_______||_______||_______||_______||___| |_|  |__|");
        System.out.println(
                " _______  _______  _______  _______  _______  _______           _______  __   __  _______  _______                                ");
        System.out.println(
                "|       ||       ||       ||       ||       ||       |         |       ||  | |  ||       ||       |                               ");
        System.out.println(
                "|       ||   _   ||    ___||    ___||    ___||    ___|         |  _____||  |_|  ||   _   ||    _  |                               ");
        System.out.println(
                "|       ||  | |  ||   |___ |   |___ |   |___ |   |___          | |_____ |       ||  | |  ||   |_| |                               ");
        System.out.println(
                "|      _||  |_|  ||    ___||    ___||    ___||    ___|         |_____  ||       ||  |_|  ||    ___|                               ");
        System.out.println(
                "|     |_ |       ||   |    |   |    |   |___ |   |___           _____| ||   _   ||       ||   |                                   ");
        System.out.println(
                "|_______||_______||___|    |___|    |_______||_______|         |_______||__| |__||_______||___|                                   \n\n");
    }

    public static void main(String[] args) {
        open();
    }

    public static void open() {
        Scanner scanner = new Scanner(System.in);
        logo();

        boolean exitProgram = false; // Flag to control the program loop

        while (!exitProgram) {
            System.out.println("1. Customer");
            System.out.println("2. Staff");
            System.out.println("3. Exit");
            System.out.print("Enter your choice (1-3): ");

            int choice = 0; // Initialize choice to 0
            boolean validInput = false;

            // Loop until valid input is provided
            while (!validInput) {
                try {
                    choice = Integer.parseInt(scanner.nextLine()); // Read the user's input as a string and parse it as
                                                                   // an integer
                    if (choice >= 1 && choice <= 3) {
                        validInput = true; // Input is valid, exit the loop
                    } else {
                        System.out.println("Invalid choice! Please enter a number between 1 and 3.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Please enter a valid integer between 1 and 3.");
                }
            }

            switch (choice) {
                case 1:
                    customerStart();
                    break; // Exit the switch and display the main menu again
                case 2:
                    start();
                    break; // Exit the switch and display the main menu again
                case 3:
                    System.out.println("Goodbye!");
                    exitProgram = true; // Set the flag to exit the program loop
                    System.exit(0);
                    break; // Exit the switch and the program loop
            }
        }

        scanner.close();
    }

    public static void customerStart() {
        Scanner scanner = new Scanner(System.in);
        boolean exitCustomerMenu = false; // Flag to control the customer menu loop

        while (!exitCustomerMenu) {
            System.out.println("       * -------- *       ");
            System.out.println("+===== | Customer | =====+");
            System.out.println("|      * -------- *      |");
            System.out.println("|                        |");
            System.out.println("| 1. Login               |");
            System.out.println("| 2. Register            |");
            System.out.println("| 3. Back to Main Menu   |"); // Added option to go back
            System.out.println("|                        |");
            System.out.println("+========================+\n");
            System.out.print("Enter your choice (1-3): ");

            String input = scanner.nextLine();

            if (input.matches("[0-9]+")) {
                int choice = Integer.parseInt(input);

                if (choice >= 1 && choice <= 3) {
                    switch (choice) {
                        case 1:
                            customerLogin();
                            break;
                        case 2:
                            customerRegister();
                            break;
                        case 3:
                            exitCustomerMenu = true; // Set the flag to exit the customer menu loop
                            break; // Exit the switch and the customer menu loop
                    }
                } else {
                    System.out.println("Invalid choice. Please enter a number between 1 and 3.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number between 1 and 3.");
            }
        }
    }

    private static void customerLogin() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter customer ID(X=exit): ");
        String customerID = scanner.nextLine();
        if (customerID.equals("X") || customerID.equals("x")) {
            return;
        }
        System.out.print("Enter password(X=exit): ");
        String password = scanner.nextLine();
        if (password.equals("X") || password.equals("x")) {
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(CUSTOMER_FILENAME))) {
            String line;
            boolean loggedIn = false;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String fileCustomerID = parts[0];
                    String filePassword = parts[1];
                    double balance = Double.parseDouble(parts[3]);
                    if (customerID.equals(fileCustomerID) && password.equals(filePassword)) {
                        String name = parts[2];
                        loggedIn = true;
                        System.out.println("Login successful!");
                        System.out.println("Welcome, " + name + "!");
                        Customer customer = new Customer(customerID, password, name, balance);
                        customer.setCustomerID(customerID);
                        customer.setPassword(password);
                        customer.setName(name);
                        customer.setBalance(balance);
                        reader.close();
                        customer(customerID, password, name, balance);
                    }
                }
            }

            if (!loggedIn) {
                System.out.println("Login failed. Please check your credentials.");
            }
        } catch (IOException e) {

        }
    }

    private static void customerRegister() {
        Scanner scanner = new Scanner(System.in);

        String customerID;
        boolean isCustomerIDTaken;

        do {
            System.out.print("Enter customer ID (X=exit)(The first character must start with 'C'): ");
            customerID = scanner.nextLine();
            if (customerID.equals("X") || customerID.equals("x")) {
                return;
            }
            isCustomerIDTaken = isCustomerIDTaken(customerID); // Check if the ID is already taken
            if (isCustomerIDTaken) {
                System.out.println("Customer ID already taken. Please choose a different one.");
            } else if (!customerID.toUpperCase().startsWith("C")) {
                System.out.println("Customer ID must start with 'C'. Please try again.");
            }
        } while (isCustomerIDTaken || !customerID.toUpperCase().startsWith("C"));

        String password;
        String confirmPassword;

        do {
            System.out.print("Enter password (X=exit)(at least 8 characters): ");
            password = scanner.nextLine();
            if (password.equals("X") || password.equals("x")) {
                return;
            }
            if (password.length() < 8) {
                System.out.println("Password must be at least 8 characters long. Please try again.");
            }
        } while (password.length() < 8);

        String name;

        do {
            System.out.print("Enter name(X=exit): ");
            name = scanner.nextLine();
            if (name.equals("X") || name.equals("x")) {
                return;
            }
            if (name.trim().isEmpty()) {
                System.out.println("Name cannot be left blank. Please try again.");
            }
        } while (name.trim().isEmpty());

        double balance;

        while (true) {
            System.out.print("Enter balance(X=exit): ");
            String balanceInput = scanner.nextLine();
            if (balanceInput.equals("X") || balanceInput.equals("x")) {
                return;
            }
            try {
                balance = Double.parseDouble(balanceInput);
                break; // Exit the loop if a valid numeric balance is entered
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid numeric balance.");
            }
        }

        Customer customer = new Customer(customerID, password, name, balance);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CUSTOMER_FILENAME, true))) {
            writer.write(customer.getCustomerID() + "," + customer.getPassword() + "," + customer.getName() + ","
                    + customer.getBalance());
            writer.newLine();
            System.out.println("Registration successful!");
        } catch (IOException e) {
            System.err.println("Error writing to the staff file.");
        }
    }

    private static boolean isCustomerIDTaken(String customerID) {
        try (BufferedReader reader = new BufferedReader(new FileReader(CUSTOMER_FILENAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 0 && parts[0].equals(customerID)) {

                    return true;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading customer data from file.");
        }

        return false;
    }

    private static void editCustomer(Customer customer) {
        Scanner scanner = new Scanner(System.in);

        boolean continueEditing = true;

        do {
            System.out.println("Current Customer Information:");
            System.out.println("Customer Name: " + customer.getName());

            System.out.println("""
                    What do you want to edit?

                    a. Customer Name
                    b. Customer Password
                    x. Exit
                    """);
            System.out.println("Enter your choice:");
            char choice = scanner.nextLine().charAt(0);

            switch (choice) {
                case 'a' -> {
                    System.out.print("Enter new customer name: ");
                    String newName = scanner.nextLine();
                    customer.setName(newName);
                }
                case 'b' -> {
                    String newPassword;
                    do {
                        System.out.print("Enter new customer password (at least 8 characters): ");
                        newPassword = scanner.nextLine();
                        if (newPassword.length() < 8) {
                            System.out.println("Password must be at least 8 characters long. Please try again.");
                        }
                    } while (newPassword.length() < 8);
                    customer.setPassword(newPassword);
                }
                case 'x' -> {
                    continueEditing = false; // Set the flag to exit the loop
                }
                default ->
                    System.out.println("Invalid choice. No changes were made.");
            }

            // Save the updated customer information
            saveCustomer(customer);

            System.out.println("Customer edited successfully!");
        } while (continueEditing); // Continue looping until continueEditing is false
    }

    public static void saveCustomer(Customer editedCustomer) {
        try {
            File file = new File(CUSTOMER_FILENAME);
            if (!file.exists()) {
                System.err.println("Customer file not found.");
                return;
            }

            // Load all customer information from the file into a list
            List<String> customerLines = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    customerLines.add(line);
                }
            }

            // Construct the updated customer information in the same format as your file
            String updatedCustomerInfo = editedCustomer.getCustomerID() + ","
                    + editedCustomer.getPassword() + ","
                    + editedCustomer.getName() + ","
                    + editedCustomer.getBalance();

            // Find and replace the line corresponding to the edited customer
            for (int i = 0; i < customerLines.size(); i++) {
                String[] parts = customerLines.get(i).split(","); // Use the same delimiter here
                if (parts.length == 4 && parts[0].equals(editedCustomer.getCustomerID())) {
                    customerLines.set(i, updatedCustomerInfo); // Replace with updated customer information
                    break; // No need to continue searching
                }
            }

            // Rewrite all customer information back to the same text file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (String customerLine : customerLines) {
                    writer.write(customerLine);
                    writer.newLine();
                }
            }

            System.out.println("Customer saved successfully!");

        } catch (IOException e) {
            System.err.println("An error occurred while saving the customer: " + e.getMessage());
        }
    }

    public static Customer loadCustomer(String customerID) {
        try (BufferedReader reader = new BufferedReader(new FileReader(CUSTOMER_FILENAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(","); // Use the same delimiter here
                if (parts[0].equals(customerID)) {
                    System.out.println("Found customer with ID: " + customerID);
                    return new Customer(parts[0], parts[1], parts[2], Double.parseDouble(parts[3]));
                }
            }
            System.out.println("Customer not found in loadCustomer method.");
        } catch (IOException e) {
            System.err.println("Error reading from the customer file: " + e.getMessage());
        }
        return null;
    }

    public static void customer(String customerID, String password, String name, double balance) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        Customer customer = new Customer(customerID, password, name, balance);

        while (!exit) {
            System.out.println("       * -------------------- *       ");
            System.out.println("+===== | Customer Information | =====+");
            System.out.println("|      * -------------------- *      |");
            System.out.println("|                                    |");
            System.out.println("| 1. Top up account                  |");
            System.out.println("| 2. Purchase                        |");
            System.out.println("| 3. Check Account                   |");
            System.out.println("| 4. Edit Account                    |");
            System.out.println("| 5. Exit                            |");
            System.out.println("|                                    |");
            System.out.println("+====================================|");
            System.out.print("Enter your choice (1-5): ");

            // Read the input as a string
            String input = scanner.nextLine().trim();

            try {
                // Try to parse the input as an integer
                int choice = Integer.parseInt(input);

                switch (choice) {
                    case 1 -> {
                        topUp(scanner, customerID, customer);
                    }
                    case 2 -> {
                        purchase(customer.getBalance(), customerID, customer);
                    }
                    case 3 -> {
                        customer.displayInfo();
                    }
                    case 4 -> {
                        editCustomer(customer);
                    }
                    case 5 -> {
                        System.out.println("Goodbye!");
                        exit = true;
                    }
                    default -> {
                        System.out.println("Invalid choice! Please try again.");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid integer (1-5).");
            }
        }
    }

    private static void topUp(Scanner scanner, String customerID, Customer customer) {
        // Read the existing food items and create a temporary file for editing
        String inputFile = "customer.txt";
        String tempFile = "temp_customer.txt";

        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            double balanceToTopUp = 0.0;
            boolean validInput = false;

            while (!validInput) {
                System.out.print("Enter Balance To Top Up(X=exit): ");
                String input = scanner.next();
                if (input.equals("X") || input.equals("x")) {
                    return;
                }
                if (input.matches("\\d+(\\.\\d+)?")) {
                    balanceToTopUp = Double.parseDouble(input);
                    validInput = true;
                } else {
                    System.out.println("Invalid input. Please enter a valid numeric value.");
                }
            }

            System.out.print("Are you sure you want to top up? (y/n): ");
            String confirm = scanner.next().toLowerCase();
            scanner.nextLine();
            if (confirm.equals("y")) {
                String line;

                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 4 && parts[0].equalsIgnoreCase(customerID)) {
                        found = true;

                        double currentBalance = Double.parseDouble(parts[3]);
                        double newBalance = currentBalance + balanceToTopUp;
                        String formattedBalance = String.format("%.2f", newBalance);
                        writer.write(customerID + "," + parts[1] + "," + parts[2] + "," + formattedBalance);

                        writer.newLine();
                        System.out.println("Balance Top Up successfully!");
                        customer.setBalance(newBalance);
                    } else {
                        writer.write(line);
                        writer.newLine();
                    }
                }

                if (!found) {
                    System.out.println("Customer ID not found");
                }
            } else if (confirm.equals("n")) {
                System.out.println("Top-up canceled.");
                return;
            } else {
                System.out.println("Invalid input. Please enter 'y' or 'n'.");
            }
        } catch (IOException e) {
            System.err.println("Error top up balance " + e.getMessage());
        }

        // Perform file renaming after editing
        renameFiles(inputFile, tempFile);

    }

    private static void login() {
        Scanner scanner = new Scanner(System.in);
        // Ask the user if they want to proceed with login
        String confirmation;
        do {
            System.out.print("Do you want to login? (y/n): ");
            confirmation = scanner.nextLine();

            // If user inputs 'n' or 'N', return immediately without further processing
            if (confirmation.equalsIgnoreCase("n")) {
                return;
            }

            // If the user inputs an invalid choice, display an error message and loop again
            if (!confirmation.equalsIgnoreCase("y") && !confirmation.equalsIgnoreCase("n")) {
                System.out.println("Invalid input. Please enter y or n.");
            }

        } while (!confirmation.equalsIgnoreCase("y") && !confirmation.equalsIgnoreCase("n"));
        System.out.print("Enter staff ID(X=exit): ");
        String staffID = scanner.nextLine();
        if (staffID.equals("X") || staffID.equals("x")) {
            return;
        }
        System.out.print("Enter password(X=exit): ");
        String password = scanner.nextLine();
        if (password.equals("X") || password.equals("x")) {
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(STAFF_FILENAME))) {
            String line;
            boolean loggedIn = false;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String fileStaffID = parts[0];
                    String filePassword = parts[3];

                    if (staffID.equals(fileStaffID) && password.equals(filePassword)) {
                        String name = parts[1];
                        String phone = parts[2];
                        loggedIn = true;
                        System.out.println("Login successful!");
                        System.out.println("Welcome, " + name + "!");
                        StaffAss staff = new StaffAss(staffID, name, phone, password);
                        staff.setStaffID(staffID);
                        staff.setPassword(password);
                        staff.setName(name);
                        staff.setPhone(phone);
                        menu(staffID, name, phone, password);
                    }
                }
            }

            if (!loggedIn) {
                System.out.println("Login failed. Please check your credentials.");
            }
        } catch (IOException e) {

        }
    }

    private static void register() {
        Scanner scanner = new Scanner(System.in);
        String confirmation;
        String staffID;
        boolean isIDTaken;

        do {
            System.out.print("Do you want to register? (y/n): ");
            confirmation = scanner.nextLine();

            // If user inputs 'n' or 'N', return immediately without further processing
            if (confirmation.equalsIgnoreCase("n")) {
                return;
            }

            // If the user inputs an invalid choice, display an error message and loop again
            if (!confirmation.equalsIgnoreCase("y") && !confirmation.equalsIgnoreCase("n")) {
                System.out.println("Invalid input. Please enter y or n.");
            }

        } while (!confirmation.equalsIgnoreCase("y") && !confirmation.equalsIgnoreCase("n"));
        do {
            System.out.print("Enter staff ID (The first character must start with 'S' or enter 'x' to exit): ");
            staffID = scanner.nextLine();

            if (staffID.equalsIgnoreCase("x")) {
                return;
            }

            isIDTaken = isStaffIDTaken(staffID); // Check if the ID is already taken
            if (isIDTaken) {
                System.out.println("Staff ID already taken. Please choose a different one.");
            } else if (!staffID.toUpperCase().startsWith("S")) {
                System.out.println("Staff ID must start with 'S'. Please try again.");
            }
        } while (isIDTaken || !staffID.toUpperCase().startsWith("S"));

        String password;
        String confirmPassword;

        do {
            System.out.print("Enter password (at least 8 characters) or enter 'x' to exit: ");
            password = scanner.nextLine();

            if (password.equalsIgnoreCase("x")) {
                return;
            }

            if (password.length() < 8) {
                System.out.println("Password must be at least 8 characters long. Please try again.");
            }
        } while (password.length() < 8);

        do {
            System.out.print("Confirm password or enter 'x' to exit: ");
            confirmPassword = scanner.nextLine();

            if (confirmPassword.equalsIgnoreCase("x")) {
                return;
            }

            if (!password.equals(confirmPassword)) {
                System.out.println("Passwords do not match. Please try again.");
            }
        } while (!password.equals(confirmPassword));

        String name;

        do {
            System.out.print("Enter name or enter 'x' to exit: ");
            name = scanner.nextLine();

            if (name.equalsIgnoreCase("x")) {
                return;
            }

            if (name.trim().isEmpty()) {
                System.out.println("Name cannot be left blank. Please try again.");
            }
        } while (name.trim().isEmpty());

        String phone;

        do {
            System.out.print("Enter phone (10 characters) or enter 'x' to exit: ");
            phone = scanner.nextLine();

            if (phone.equalsIgnoreCase("x")) {
                return;
            }

            if (phone.length() != 10) {
                System.out.println("Phone number must be 10 characters long. Please try again.");
            }
        } while (phone.length() != 10);

        StaffAss staff = new StaffAss(staffID, name, phone, password);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(STAFF_FILENAME, true))) {
            writer.write(
                    staff.getStaffID() + "," + staff.getName() + "," + staff.getPhone() + "," + staff.getPassword());
            writer.newLine();
            System.out.println("Registration successful!");
        } catch (IOException e) {
            System.err.println("Error writing to the staff file.");
        }
    }

    private static boolean isStaffIDTaken(String staffID) {
        try (BufferedReader reader = new BufferedReader(new FileReader(STAFF_FILENAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 0 && parts[0].equals(staffID)) {
                    // Staff ID is already taken
                    return true;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading staff data from file.");
        }
        // Staff ID is not taken
        return false;
    }

    public static void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("       * ----- *       ");
            System.out.println("+===== | Staff | =====+");
            System.out.println("|      * ----- *      |");
            System.out.println("|                     |");
            System.out.println("| 1. Login            |");
            System.out.println("| 2. Register         |");
            System.out.println("| 3. Exit             |");
            System.out.println("|                     |");
            System.out.println("+=====================+\n");
            System.out.print("Enter your choice (1-3): ");

            String input = scanner.nextLine();

            // Use a regular expression to check if input is a numeric digit
            if (input.matches("[0-9]+")) {
                int choice = Integer.parseInt(input);

                // Check if the choice is within the valid range
                if (choice >= 1 && choice <= 3) {
                    switch (choice) {
                        case 1 ->
                            login();
                        case 2 ->
                            register();
                        case 3 -> {
                            open();
                        }
                    }
                } else {
                    System.out.println("Invalid choice. Please enter a number between 1 and 3.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number between 1 and 3.");
            }
        }
    }

    public static void menu(String staffID, String name, String phone, String password) {
        int choice = 0;
        Scanner scan = new Scanner(System.in);
        StaffAss staff = new StaffAss(staffID, name, phone, password);
        logo();
        do {
            System.out.print("""

                              * ------------------------------------ *
                    +======== | Oxyhaemoglobin Coffee Shop Main Menu | ========+
                    |         * ------------------------------------ *         |
                    |                                                          |
                    | 1. Food and beverage                                     |
                    | 2. Staff Account                                         |
                    | 3. Staff                                                 |
                    | 4. Summary Report                                        |
                    | 5. Display customer                                      |
                    | 6. Exit                                                  |
                    |                                                          |
                    +==========================================================+

                    """);
            boolean continueInput = true;
            do {
                try {
                    System.out.print("Enter a choice: ");
                    choice = scan.nextInt();
                    scan.nextLine();
                    continueInput = false;
                } catch (Exception ex) {
                    System.err.println("Invalid Input");
                    scan.nextLine();
                }
            } while (continueInput);

            switch (choice) {
                case 1 ->
                    food(staffID, name, phone, password);// Call the 'food()' method or implement food-related
                                                         // functionality
                case 2 -> {
                    staff.displayInfo();
                }
                case 3 -> {
                    staff();
                }
                case 4 -> {
                    displaySummaryReportFromFile("summary_report.txt");
                }
                case 5 -> {
                    displayCustomers(CUSTOMER_FILENAME, scan);
                }
                case 6 -> {
                    start();
                }
                default ->
                    System.out.println("Invalid choice. Please try again.");
            }
            // Call the 'purchase()' method or implement purchase-related functionality
            // Call the 'sales()' method or implement summary report functionality
        } while (choice != 6);
    }

    public static void displayCustomers(String fileName, Scanner scanner) {
        while (true) {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String line;
                System.out.println("=================================================================");
                System.out.println("                       <Customer List>");
                System.out.println("=================================================================");

                // Define column headers with formatting
                System.out.printf("%-15s %-20s %-15s %-15s%n", "Customer ID", "Name", "Balance", "Password");

                System.out.println("=================================================================");

                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(","); // Use the same delimiter here
                    if (parts.length == 4) {
                        // Format and print customer information
                        System.out.printf("%-15s %-20s %-15.2f %-15s%n", parts[0], parts[2],
                                Double.parseDouble(parts[3]), parts[1]);
                    }
                }

                System.out.println("=================================================================");
                System.out.println("Press any key to return to the Main Menu.");
                scanner.nextLine(); // Wait for any input (any key press)

                return; // Return to the previous menu
            } catch (IOException e) {
                System.err.println("Error reading from the customer file: " + e.getMessage());
                System.out.print("Enter any key to exit");
                scanner.nextLine();
                return; // Return to the previous menu in case of an error
            }
        }
    }

    public static void displaySummaryReportFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("Error reading summary report from the file: " + e.getMessage());
        }
    }

    public static void generateSummaryReportToFile(String fileName) {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String currentDate = dateFormat.format(new Date());

            fileWriter.write("================================================\n");
            fileWriter.write("                <Summary Report>                \n");
            fileWriter.write("================================================\n");
            fileWriter.write("Date: " + currentDate + "\n"); // Add current date and time
            fileWriter.write("================================================\n");
            fileWriter.write(" Table Number  Total Order Price \n");
            fileWriter.write("================================================\n");

            double totalSum = 0.0; // Initialize a variable to store the total sum

            for (Map.Entry<Integer, Double> entry : tableOrderPrices.entrySet()) {
                int tableNumber = entry.getKey();
                double tableTotalPrice = entry.getValue();

                // Format and write the summary report as a table row
                fileWriter.write(String.format(" %-12d  $%.2f           \n", tableNumber, tableTotalPrice));

                // Add the current tableTotalPrice to the totalSum
                totalSum += tableTotalPrice;
            }

            fileWriter.write("================================================\n");

            // Write the total sum
            fileWriter.write(String.format(" %-12s  $%.2f           \n", "Total Sum", totalSum));
            fileWriter.write("================================================\n");

            System.out.println("Summary report has been written to " + fileName);
        } catch (IOException e) {
            System.err.println("Error writing summary report to the file: " + e.getMessage());
        }
    }

    public static void food(String staffID, String name, String phone, String password) {
        int choice = 0;
        Scanner scan = new Scanner(System.in);
        logo();
        do {
            System.out.print("""

                             * --------------- *
                    +======= | Food & Beverage | =======+
                    |        * --------------- *        |
                    |                                   |
                    | 1. Food                           |
                    | 2. Beverage                       |
                    | 3. Exit                           |
                    |                                   |
                    +===================================+

                    """);
            boolean continueInput = true;
            do {
                try {
                    System.out.print("Enter a choice: ");
                    choice = scan.nextInt();
                    scan.nextLine();
                    continueInput = false;
                } catch (Exception ex) {
                    System.err.println("Invalid Input");
                    scan.nextLine();
                }
            } while (continueInput);

            switch (choice) {
                case 1 ->
                    foodModule(staffID, name, phone, password);// Call the 'food()' method or implement food-related
                                                               // functionality
                case 2 ->
                    beverageModule(staffID, name, phone, password);// Call the 'purchase()' method or implement
                                                                   // purchase-related functionality
                case 3 -> {
                    menu(staffID, name, phone, password);
                }
                default ->
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 3);
    }

    public static void foodModule(String staffID, String name, String phone, String password) {
        // Menu loop
        try (Scanner scanner = new Scanner(System.in)) {
            // Menu loop
            while (true) {
                System.out.println("           * ---- *           ");
                System.out.println("+========= | Food | =========+");
                System.out.println("|          * ---- *          |");
                System.out.println("|                            |");
                System.out.println("| 1. Add Vegan Food          |");
                System.out.println("| 2. Add Non-Vegan Food      |");
                System.out.println("| 3. Display Food Items      |");
                System.out.println("| 4. Edit Food Item          |");
                System.out.println("| 5. Delete Food Item        |");
                System.out.println("| 6. Search Food Item        |");
                System.out.println("| 7. Restock Food            |");
                System.out.println("| 8. Reduce Food             |");
                System.out.println("| 9. Back                    |");
                System.out.println("|                            |");
                System.out.println("+============================+\n");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline

                switch (choice) {
                    case 1 ->
                        addFood(scanner, true);
                    case 2 ->
                        addFood(scanner, false);
                    case 3 ->
                        displayFood("food_items.txt", scanner);
                    case 4 ->
                        editFood(scanner);
                    case 5 ->
                        deleteFood(scanner);
                    case 6 ->
                        searchFood();
                    case 7 ->
                        restockfood(scanner);
                    case 8 ->
                        reducefood(scanner);
                    case 9 -> {
                        food(staffID, name, phone, password);
                    }
                    default ->
                        System.out.println("Invalid choice. Please enter a valid option.");
                }
            }
        }
    }

    private static void addFood(Scanner scanner, boolean isVegan) {
        String foodID;
        String food;
        double price = 0.0;
        int quantity = -1;

        while (true) {
            System.out.print("Enter Food ID (Must start with 'V' or 'N' and cannot be blank, or 'x' to exit): ");
            foodID = scanner.nextLine().trim().toUpperCase();

            if (foodID.equals("x")) {
                System.out.println("Exiting the program.");
                return; // Exit the program
            }

            if (foodID.length() == 0 || (foodID.charAt(0) != 'V' && foodID.charAt(0) != 'N')) {
                System.out.println("Invalid Food ID. Please ensure it starts with 'V' or 'N'.");
                continue; // Start over with the loop
            }

            // Check if food ID already exists
            if (foodIdExists(foodID, "food_items.txt")) {
                System.out.println("Food ID already exists. Please enter a different Food ID.");
                continue; // Start over with the loop
            }

            System.out.print("Enter food name (cannot be blank, or 'x' to exit): ");
            food = scanner.nextLine().trim();

            if (food.equals("x")) {
                System.out.println("Exiting the program.");
                return; // Exit the program
            }

            if (food.length() == 0) {
                System.out.println("Food name cannot be blank. Please enter a valid food name.");
                continue; // Start over with the loop
            }

            while (true) {
                System.out.print("Enter price (e.g., 10.20 and cannot be blank, or 'x' to exit): ");
                String priceInput = scanner.nextLine().trim();

                if (priceInput.equals("x")) {
                    System.out.println("Exiting the program.");
                    return; // Exit the program
                }

                try {
                    price = Double.parseDouble(priceInput);
                    if (price <= 0) {
                        System.out.println("Please enter a valid price greater than 0.");
                        continue; // Start over with the loop
                    }
                    break; // Price is valid, exit the loop
                } catch (NumberFormatException e) {
                    System.out.println("Invalid price format. Please enter a valid price.");
                }
            }

            while (true) {
                System.out.print("Enter quantity (cannot be blank, or 'x' to exit): ");
                String quantityInput = scanner.nextLine().trim();

                if (quantityInput.equals("x")) {
                    System.out.println("Exiting the program.");
                    return; // Exit the program
                }

                try {
                    quantity = Integer.parseInt(quantityInput);
                    if (quantity <= 0) {
                        System.out.println("Please enter a valid quantity greater than 0.");
                        continue; // Start over with the loop
                    }
                    break; // Quantity is valid, exit the loop
                } catch (NumberFormatException e) {
                    System.out.println("Invalid quantity format. Please enter a valid quantity.");
                }
            }

            // If we reach this point, all input is valid, so break out of the loop.
            break;
        }

        FoodAss foodItem;
        if (isVegan) {
            foodItem = new Vegan(foodID, food, price, "This food does not contain meat.", quantity);
        } else {
            foodItem = new NonVegan(foodID, food, price, "This food contains meat.", quantity);
        }

        storeFood(foodItem, "food_items.txt");
    }

    // Function to check if a food ID already exists in the file
    private static boolean foodIdExists(String foodID, String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 0 && parts[0].equalsIgnoreCase(foodID)) {
                    return true; // Food ID already exists
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the food file.");
        }
        return false; // Food ID does not exist
    }

    private static void storeFood(FoodAss foodItem, String fileName) {
        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new FileWriter(fileName, true));

            // Use instanceof to determine the type of food item and cast accordingly
            if (foodItem instanceof Vegan veganFood) {
                writer.write(veganFood.getFoodID() + "," + veganFood.getFood() + "," + veganFood.getPrice() + ","
                        + veganFood.getFoodDetail() + "," + veganFood.getquantity());
            } else if (foodItem instanceof NonVegan nonVeganFood) {
                writer.write(nonVeganFood.getFoodID() + "," + nonVeganFood.getFood() + "," + nonVeganFood.getPrice()
                        + "," + nonVeganFood.getFoodDetail() + "," + nonVeganFood.getquantity());
            }
            writer.newLine();
            System.out.println("Food stored successfully!");
        } catch (IOException e) {
            System.err.println("Error writing to the food file.");
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                System.err.println("Error closing the writer.");
            }
        }
    }

    // private static void displayFood(String fileName, Scanner scanner) {
    // System.out.println("========== Displaying Food Items ==========");
    // System.out.printf("%-10s %-20s %-10s %-10s %-40s%n", "Food ID", "Food",
    // "Price", "Quantity", "Food Detail");
    //
    // BufferedReader reader = null;
    //
    // try {
    // reader = new BufferedReader(new FileReader(fileName));
    // String line;
    // while ((line = reader.readLine()) != null) {
    // String[] parts = line.split(",");
    // if (parts.length == 5) { // Assuming there are 5 parts including quantity
    // String foodID = parts[0];
    // String food = parts[1];
    // double price = Double.parseDouble(parts[2]);
    // String foodDetail = parts[3];
    // int quantity = Integer.parseInt(parts[4]);
    // // Format and print each food item along with quantity and foodDetail
    // System.out.printf("%-10s %-20s %-10.2f %-10d %-40s%n", foodID, food, price,
    // quantity, foodDetail);
    // }
    // }
    // } catch (IOException e) {
    // System.err.println("Error reading from the food file.");
    // } finally {
    // try {
    // if (reader != null) {
    // reader.close();
    // }
    // } catch (IOException e) {
    // System.err.println("Error closing the reader.");
    // }
    // }
    //
    // // Prompt user to press any key to exit
    // System.out.println("\nPress any key and hit enter to exit...");
    // scanner.nextLine(); // Wait for user input
    // }
    public static void line() {
        for (int i = 0; i < 90; i++) {
            System.out.print("=");
        }
    }

    private static void displayFood(String fileName, Scanner scanner) {
        System.out
                .println("================================== Displaying Food Items =================================");

        // Define the table header format
        String headerFormat = "\n%-10s %-20s %-10s %-10s %-40s%n";
        line();
        System.out.printf(headerFormat, "Food ID", "Food", "Price", "Quantity", "Food Detail");
        line();
        System.out.print("\n");
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) { // Assuming there are 5 parts including quantity
                    String foodID = parts[0];
                    String food = parts[1];
                    double price = Double.parseDouble(parts[2]);
                    String foodDetail = parts[3];
                    int quantity = Integer.parseInt(parts[4]);

                    // Format and print each food item along with quantity and foodDetail
                    System.out.printf("%-10s %-20s $%-9.2f %-10d %-40s%n", foodID, food, price, quantity, foodDetail);
                }
            }
            line();
        } catch (IOException e) {
            System.err.println("Error reading from the food file.");
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.err.println("Error closing the reader.");
            }
        }

        // Prompt user to press any key to exit
        System.out.println("\nPress Enter to exit...");
        scanner.nextLine(); // Wait for user input
    }

    private static void editFood(Scanner scanner) {
        displayFood("food_items.txt", scanner);
        while (true) {
            System.out.print("Enter Food ID to edit or 'x' to exit: ");
            String foodID = scanner.nextLine();

            if (foodID.equalsIgnoreCase("x")) {
                System.out.println("Exiting the editing program.");
                break; // Exit the loop if the user enters 'x'
            }

            // Read the existing food items and create a temporary file for editing
            String inputFile = "food_items.txt";
            String tempFile = "temp_food_items.txt";

            boolean found = false;

            try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                    BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 5 && parts[0].equalsIgnoreCase(foodID)) {
                        found = true;
                        System.out.println("Select an option to edit:");
                        System.out.println("-------------------------");
                        System.out.println("a. Edit food name");
                        System.out.println("b. Edit price");
                        System.out.println("c. Edit quantity");
                        System.out.print("Enter your choice: ");
                        char option = scanner.next().charAt(0);
                        scanner.nextLine(); // Consume the newline

                        switch (option) {
                            case 'a' -> {
                                System.out.print("Enter new food name: ");
                                String newFood = scanner.nextLine();
                                if (!newFood.isEmpty()) {
                                    line = foodID + "," + newFood + "," + parts[2] + "," + parts[3] + "," + parts[4];
                                } else {
                                    System.out.println("Food name cannot be empty. Food item not updated.");
                                }
                            }
                            case 'b' -> {
                                System.out.print("Enter new price (e.g., 10.20): ");
                                String priceInput = scanner.nextLine();
                                if (!priceInput.isEmpty()) {
                                    double newPrice = Double.parseDouble(priceInput);
                                    line = foodID + "," + parts[1] + "," + newPrice + "," + parts[3] + "," + parts[4];
                                } else {
                                    System.out.println("Price cannot be empty. Food item not updated.");
                                }
                            }
                            case 'c' -> {
                                System.out.print("Enter new quantity: ");
                                String quantityInput = scanner.nextLine();
                                if (!quantityInput.isEmpty()) {
                                    int newQuantity = Integer.parseInt(quantityInput);
                                    line = foodID + "," + parts[1] + "," + parts[2] + "," + parts[3] + ","
                                            + newQuantity;
                                } else {
                                    System.out.println("Quantity cannot be empty. Food item not updated.");
                                }
                            }
                            default ->
                                System.out.println("Invalid option.");
                        }

                        System.out.println("Food item updated successfully!");
                    }
                    writer.write(line); // Write the line (whether modified or not)
                    writer.newLine();
                }

                if (!found) {
                    System.out.println("Food item with ID " + foodID + " not found.");
                }
            } catch (IOException e) {
                System.err.println("Error editing food item: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.err.println("Invalid number format. Food item not updated.");
            }

            // Perform file renaming after editing
            renameFiles(inputFile, tempFile);

            // Ask the user if they want to continue editing or exit
            System.out.print("Do you want to continue editing? (y/n): ");
            String continueEditing = scanner.nextLine();
            if (!continueEditing.equalsIgnoreCase("y")) {
                break; // Exit the loop if the user doesn't want to continue editing
            }
        }
    }

    private static void deleteFood(Scanner scanner) {
        displayFood("food_items.txt", scanner);
        while (true) {
            System.out.print("Enter Food ID to delete or 'x' to exit: ");
            String foodID = scanner.nextLine();

            if (foodID.equalsIgnoreCase("x")) {
                System.out.println("Exiting the deletion program.");
                break; // Exit the loop if the user enters 'x'
            }

            // Read the existing food items and create a temporary file for deletion
            String inputFile = "food_items.txt";
            String tempFile = "temp_food_items.txt";

            boolean found = false;

            try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                    BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 5 && parts[0].equalsIgnoreCase(foodID)) {
                        found = true;
                        System.out.println("Food item with ID " + foodID + ":");
                        System.out.println("Name: " + parts[1]);
                        System.out.println("Price: " + parts[2]);
                        System.out.println("Quantity: " + parts[3]);

                        // Ask for confirmation before deletion
                        System.out.print("Are you sure you want to delete this food item? (y/n): ");
                        String confirmation = scanner.nextLine();
                        if (confirmation.equalsIgnoreCase("y")) {
                            System.out.println("Food item with ID " + foodID + " deleted.");
                        } else if (confirmation.equalsIgnoreCase("n")) {
                            // If the user doesn't confirm, don't delete and write the existing line back to
                            // the temp file
                            writer.write(line);
                            writer.newLine();
                            System.out.println("Food item not deleted.");
                        } else {
                            System.out.println("Invalid input. Please enter 'y' or 'n'.");
                        }
                    } else {
                        // Write the existing line to the temp file
                        writer.write(line);
                        writer.newLine();
                    }
                }

                if (!found) {
                    System.out.println("Food item with ID " + foodID + " not found.");
                }
            } catch (IOException e) {
                System.err.println("Error deleting food item: " + e.getMessage());
            }

            // Perform file renaming after deletion
            renameFiles(inputFile, tempFile);
        }
    }

    private static void renameFiles(String originalFile, String tempFile) {
        File originalFileObj = new File(originalFile);
        File tempFileObj = new File(tempFile);

        if (originalFileObj.exists()) {
            boolean deleteSuccess = originalFileObj.delete(); // Delete the original file
            if (deleteSuccess) {
                boolean renameSuccess = tempFileObj.renameTo(originalFileObj);
                if (renameSuccess) {
                    System.out.println("File updated successfully.");
                } else {
                    System.err.println("Error renaming temporary file to original file.");
                }
            } else {
                System.err.println("Error deleting original file.");
            }
        } else {
            System.err.println("Original file not found.");
        }
    }

    public static void searchFood() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter Food ID to search (e.g.: F0001) or 'x' to exit: ");
            String bvIDToSearch = scanner.nextLine();

            if (bvIDToSearch.equalsIgnoreCase("x")) {
                System.out.println("Exiting the search program.");
                break; // Exit the loop if the user enters 'x'
            }

            try (BufferedReader reader = new BufferedReader(new FileReader("food_items.txt"))) {
                String line;
                boolean found = false;
                while ((line = reader.readLine()) != null) {
                    String[] bvAttributes = line.split(",");
                    if (bvAttributes[0].equals(bvIDToSearch)) {
                        System.out.println("Food ID: " + bvAttributes[0]);
                        System.out.println("Food Name: " + bvAttributes[1]);
                        System.out.println("Price: " + bvAttributes[2]);
                        System.out.println("Food Detail: " + bvAttributes[3]);
                        System.out.println("Quantity: " + bvAttributes[4]);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    System.out.println("Food ID not found!");
                }

                // Ask the user if they want to search for more food items
                System.out.print("Do you want to search for more food items? (y/n): ");
                String continueSearching = scanner.nextLine();
                if (!continueSearching.equalsIgnoreCase("y")) {
                    break; // Exit the loop if the user doesn't want to continue searching
                }
            } catch (IOException e) {
                System.err.println("Error reading from the food file.");
            }
        }
    }

    private static void restockfood(Scanner scanner) {
        displayFood("food_items.txt", scanner);
        System.out.print("Enter Food ID to add quantity: ");
        String foodID = scanner.nextLine();

        // Read the existing food items and create a temporary file for editing
        String inputFile = "food_items.txt";
        String tempFile = "temp_food_items.txt";

        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5 && parts[0].equalsIgnoreCase(foodID)) {
                    found = true;
                    int currentQuantity = Integer.parseInt(parts[4]);
                    int quantityToAdd = 0;
                    boolean validQuantity = false;

                    while (!validQuantity) {
                        System.out.print("Enter quantity to add: ");
                        String quantityInput = scanner.nextLine();

                        if (quantityInput.matches("\\d+")) { // Check if input is numeric
                            quantityToAdd = Integer.parseInt(quantityInput);
                            validQuantity = true;
                        } else {
                            System.out.println("Invalid input. Please enter a numeric quantity.");
                        }
                    }

                    int newQuantity = currentQuantity + quantityToAdd;
                    parts[4] = String.valueOf(newQuantity);

                    // Write the updated line to the temp file
                    writer.write(String.join(",", parts));
                    writer.newLine();
                    System.out.println("Quantity added to food item successfully!");
                } else {
                    // Write the existing line to the temp file if it's not the item to edit
                    writer.write(line);
                    writer.newLine();
                }
            }

            if (!found) {
                System.out.println("Food item with ID " + foodID + " not found.");
            }
        } catch (IOException e) {
            System.err.println("Error adding quantity to food item: " + e.getMessage());
        }

        // Perform file renaming after editing
        renameFiles(inputFile, tempFile);
    }

    private static void reducefood(Scanner scanner) {
        displayFood("food_items.txt", scanner);
        System.out.print("Enter Food ID to subtract quantity: ");
        String foodID = scanner.nextLine();

        // Read the existing food items and create a temporary file for editing
        String inputFile = "food_items.txt";
        String tempFile = "temp_food_items.txt";

        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5 && parts[0].equalsIgnoreCase(foodID)) {
                    found = true;
                    int currentQuantity = Integer.parseInt(parts[4]);
                    int quantityToSubtract = 0;
                    boolean validQuantity = false;

                    while (!validQuantity) {
                        System.out.print("Enter quantity to subtract: ");
                        String quantityInput = scanner.nextLine();

                        if (quantityInput.matches("\\d+")) { // Check if input is numeric
                            quantityToSubtract = Integer.parseInt(quantityInput);
                            validQuantity = true;
                        } else {
                            System.out.println("Invalid input. Please enter a numeric quantity.");
                        }
                    }

                    int newQuantity = currentQuantity - quantityToSubtract;

                    // Ensure the quantity doesn't go below zero
                    if (newQuantity < 0) {
                        newQuantity = 0;
                    }

                    parts[4] = String.valueOf(newQuantity);

                    // Write the updated line to the temp file
                    writer.write(String.join(",", parts));
                    writer.newLine();
                    System.out.println("Quantity subtracted from food item successfully!");
                } else {
                    // Write the existing line to the temp file if it's not the item to edit
                    writer.write(line);
                    writer.newLine();
                }
            }

            if (!found) {
                System.out.println("Food item with ID " + foodID + " not found.");
            }
        } catch (IOException e) {
            System.err.println("Error subtracting quantity from food item: " + e.getMessage());
        }

        // Perform file renaming after editing
        renameFiles(inputFile, tempFile);
    }

    public static void beverageModule(String staffID, String name, String phone, String password) {
        int choice = 0;
        Scanner scan = new Scanner(System.in);
        do {
            System.out.print("""

                                * -------- *
                    +========== | Beverage | ==========+
                    |           * -------- *           |
                    |                                  |
                    | 1. Add Beverage                  |
                    | 2. Display Beverage              |
                    | 3. Edit Beverage                 |
                    | 4. Delete Beverage               |
                    | 5. Search Beverage               |
                    | 6. Restock Beverage              |
                    | 7. Reduce Beverage               |
                    | 8. Back                          |
                    |                                  |
                    +==================================+

                    """);
            boolean continueInput = true;
            do {
                try {
                    System.out.print("Enter a choice: ");
                    choice = scan.nextInt();
                    scan.nextLine();
                    continueInput = false;
                } catch (Exception ex) {
                    System.err.println("Invalid Input");
                    scan.nextLine();
                }
            } while (continueInput);

            switch (choice) {
                case 1 ->
                    addBeverage();
                case 2 ->
                    displayBeverage(BEVERAGE_FILENAME, scan);
                case 3 ->
                    editBeverage();
                case 4 ->
                    deleteBeverage();
                case 5 ->
                    searchBeverage();
                case 6 ->
                    restock();
                case 7 ->
                    reducebv();
                case 8 -> {
                    food(staffID, name, phone, password);
                }
                default ->
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 8);
    }

    public static void addBeverage() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print(
                    "Enter 'x' to exit or enter beverage ID (Must start with 'B' and has at least 4 digits, Eg. B0001): ");
            String beverageID = scanner.nextLine().trim().toUpperCase();

            if (beverageID.equals("X")) {
                System.out.println("Exiting beverage input.");
                break; // Exit the loop
            }

            if (!isValidBeverageID(beverageID)) {
                System.out.println(
                        "Invalid Beverage ID format. Please ensure it starts with 'B' and has at least 4 digits, Eg. B0001.");
                continue; // Start over with the loop
            }

            // Check if beverage ID already exists
            if (beverageIdExists(beverageID, BEVERAGE_FILENAME)) {
                System.out.println("Beverage ID already exists. Please enter a different ID.");
                continue; // Start over with the loop
            }

            System.out.print("Enter Beverage (e.g: watermelon juice): ");
            String beverage = scanner.nextLine();

            if (beverage.isEmpty()) {
                System.out.println("Beverage name cannot be blank. Please enter a valid name.");
                continue; // Start over with the loop
            }

            double beveragePrice = 0.0;
            while (true) {
                System.out.print("Enter Beverage price (e.g: 10.20 or 'x' to exit): ");
                String priceInput = scanner.nextLine();

                if (priceInput.equalsIgnoreCase("x")) {
                    System.out.println("Exiting beverage input.");
                    return; // Exit the method
                }

                try {
                    beveragePrice = Double.parseDouble(priceInput);
                    if (beveragePrice <= 0) {
                        System.out.println("Please enter a valid price greater than 0.");
                        continue; // Start over with the loop
                    }
                    break; // Price is valid, exit the loop
                } catch (NumberFormatException e) {
                    System.out.println("Invalid price format. Please enter a valid price.");
                }
            }

            int beverageQuantity = -1;
            while (true) {
                System.out.print("Enter Beverage quantity (e.g: 10 or 'x' to exit): ");
                String quantityInput = scanner.nextLine();

                if (quantityInput.equalsIgnoreCase("x")) {
                    System.out.println("Exiting beverage input.");
                    return; // Exit the method
                }

                try {
                    beverageQuantity = Integer.parseInt(quantityInput);
                    if (beverageQuantity < 0) {
                        System.out.println("Please enter a valid quantity greater than or equal to 0.");
                        continue; // Start over with the loop
                    }
                    break; // Quantity is valid, exit the loop
                } catch (NumberFormatException e) {
                    System.out.println("Invalid quantity format. Please enter a valid quantity.");
                }
            }

            // Create a FoodAss object and write it to the file
            FoodAss beverageItem = new FoodAss(beverageID, beverage, beveragePrice, beverageQuantity);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(BEVERAGE_FILENAME, true))) {
                writer.write(beverageItem.getFoodID() + "," + beverageItem.getFood() + "," + beverageItem.getPrice()
                        + "," + beverageItem.getquantity());
                writer.newLine();
                System.out.println("Beverage stored successfully!");
            } catch (IOException e) {
                System.err.println("Error writing to the beverage file.");
            }
        }
    }

    // Function to check if a beverage ID is in the correct format
    private static boolean isValidBeverageID(String beverageID) {
        // Implement your format validation logic here
        // For example, you can check if it follows a certain pattern or meets specific
        // criteria
        return beverageID.length() == 5 && (beverageID.charAt(0) == 'B' || beverageID.charAt(0) == 'b');
    }

    // Function to check if a beverage ID already exists in the file
    private static boolean beverageIdExists(String beverageID, String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 0 && parts[0].equalsIgnoreCase(beverageID)) {
                    return true; // Beverage ID already exists
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the beverage file.");
        }
        return false; // Beverage ID does not exist
    }

    public static void editBeverage() {

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        displayBeverage(BEVERAGE_FILENAME, scanner);

        while (!exit) {
            System.out.print("Enter Beverage ID to edit (e.g., B0001) or 'x' to exit: ");
            String beverageID = scanner.nextLine();

            if (beverageID.equalsIgnoreCase("x")) {
                System.out.println("Exiting the beverage editing program.");
                break; // Exit the loop if the user enters 'x'
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(BEVERAGE_FILENAME))) {
                File tempFile = new File("temp_beverage_items.txt");
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

                boolean found = false;
                String line;

                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 4) {
                        String currentBeverageID = parts[0];

                        if (currentBeverageID.equals(beverageID)) {
                            found = true;

                            System.out.println("""

                                    What do you want to edit?
                                    -------------------------

                                    a. Beverage Name
                                    b. Beverage Price
                                    c. Beverage Quantity
                                    """);
                            System.out.print("Enter your choice:");
                            char choice = scanner.nextLine().charAt(0);

                            boolean correct = false;
                            while (correct == false) {
                                switch (choice) {
                                    case 'a' -> {
                                        System.out.print("Enter new Beverage Name: ");
                                        String newName = scanner.nextLine();
                                        line = parts[0] + "," + newName + "," + parts[2] + "," + parts[3];
                                        correct = true;
                                    }
                                    case 'b' -> {
                                        try {
                                            System.out.print("Enter new Beverage Price: ");
                                            double newPrice = scanner.nextDouble();
                                            scanner.nextLine(); // Consume the newline character
                                            line = parts[0] + "," + parts[1] + "," + newPrice + "," + parts[3];
                                            correct = true;
                                        } catch (InputMismatchException e) {
                                            System.out.println("Invalid input for price. Please enter a valid number.");
                                            scanner.nextLine(); // Consume the invalid input
                                        }
                                    }
                                    case 'c' -> {
                                        try {
                                            System.out.print("Enter new Beverage Quantity: ");
                                            int newQuantity = scanner.nextInt();
                                            scanner.nextLine(); // Consume the newline character
                                            line = parts[0] + "," + parts[1] + "," + parts[2] + "," + newQuantity;
                                            correct = true;
                                        } catch (InputMismatchException e) {
                                            System.out.println(
                                                    "Invalid input for quantity. Please enter a valid number.");
                                            scanner.nextLine(); // Consume the invalid input
                                        }
                                    }
                                    default -> {
                                        System.out.println("Invalid choice. No changes were made.");
                                        correct = true;
                                    }
                                }
                            }
                            System.out.println("Beverage item updated successfully!");
                        }
                    }

                    writer.write(line);
                    writer.newLine();
                }

                writer.close();
                reader.close();

                // Delete the original file
                File originalFile = new File(BEVERAGE_FILENAME);
                if (!originalFile.delete()) {
                    System.err.println("Error deleting the original file.");
                    return;
                }

                // Rename the temporary file to the original file name
                if (!tempFile.renameTo(originalFile)) {
                    System.err.println("Error renaming the temporary file.");
                    return;
                }

                if (found) {
                    System.out.println("Beverage edited successfully!");
                } else {
                    System.out.println("Beverage item not found. No changes were made.");
                }
            } catch (IOException e) {
                System.err.println("Error reading/writing to the beverage file.");
            }
        }
    }

    public static void savebaverage(FoodAss baverage) {
        try {
            File file = new File("baverage.txt");
            if (!file.exists()) {
                file.createNewFile();
            }

            // Appending to the file to avoid overwriting existing data
            FileWriter fw = new FileWriter(file, true);
            try (BufferedWriter bw = new BufferedWriter(fw)) {
                bw.write(baverage.toString());
                bw.newLine();
            }

            System.out.println("Food saved successfully!");

        } catch (IOException e) {
            System.out.println("An error occurred while saving the baverage: " + e.getMessage());
        }
    }

    public static FoodAss loadBaverage(String baverageID) {
        try (BufferedReader reader = new BufferedReader(new FileReader(BEVERAGE_FILENAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(baverageID)) {
                    return new FoodAss(parts[0], parts[1], Double.parseDouble(parts[2]), Integer.parseInt(parts[3]));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from the beverage file.");
        }
        return null;
    }

    public static void deleteBeverage() {
        Scanner scanner = new Scanner(System.in);
        displayBeverage(BEVERAGE_FILENAME, scanner);
        System.out.print("Enter ID to delete (e.g. B0001): ");
        String beverageIDToDelete = scanner.nextLine();

        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(BEVERAGE_FILENAME))) {
            File tempFile = new File("temp1.txt");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 4) {
                        String beverageID = parts[0];
                        if (beverageID.equals(beverageIDToDelete)) {
                            found = true;

                            // Display what will be deleted in a formatted way
                            System.out.println("You are about to delete the following beverage:");
                            System.out.println("============================================================");
                            System.out.println("ID\t\tBeverage\t\tPrice\t\tQuantity");
                            System.out.println("============================================================");
                            System.out.println(parts[0] + "\t\t" + parts[1] + "\t\t" + parts[2] + "\t\t" + parts[3]);

                            // Ask for confirmation
                            System.out.print("Do you want to delete this beverage? (y/n): ");
                            String confirmation = scanner.nextLine().trim().toLowerCase();
                            if (confirmation.equals("y")) {
                                // User confirmed deletion, do not write this line to the temp file.
                                continue;
                            }
                        }
                    }
                    // Write the line to the temp file if it's not the one to be deleted.
                    writer.write(line);
                    writer.newLine();
                }
            }
            reader.close();

            // Delete the original file
            File originalFile = new File(BEVERAGE_FILENAME);
            if (!originalFile.delete()) {
                System.err.println("Error deleting the original file.");
                return;
            }

            // Rename the temporary file to the original file name
            if (!tempFile.renameTo(originalFile)) {
                System.err.println("Error renaming the temporary file.");
                return;
            }

            if (found) {
                System.out.println("Beverage deleted successfully!");
            } else {
                System.out.println("Beverage not found. No changes were made.");
            }

        } catch (IOException e) {
            System.err.println("Error reading/writing to the beverage file.");
        }
    }

    public static void displayBeverage(String fileName, Scanner scanner) {
        try (BufferedReader reader = new BufferedReader(new FileReader(BEVERAGE_FILENAME))) {
            String line;
            boolean found = false;

            System.out.println(
                    "=================================== Displaying Beverage ==================================");
            line();
            System.out.printf("\n%-15s %-20s %-10s %-10s%n", "BeverageID", "Beverage", "Price", "Quantity");
            line();
            System.out.print("\n");
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String id = parts[0];
                    String beverage = parts[1];
                    double price = Double.parseDouble(parts[2]);
                    int quantity = Integer.parseInt(parts[3]);

                    // Format and print each beverage as a table row
                    System.out.printf("%-15s %-20s $%-10.2f %-10d%n", id, beverage, price, quantity);
                    found = true;
                }
            }

            if (!found) {
                System.out.println("No beverages found.");
            }

            line();

        } catch (IOException e) {
            System.err.println("Error reading the beverage file.");
        }

        System.out.println("\nPress Enter to exit...");
        scanner.nextLine(); // Wait for user input
    }

    public static void searchBeverage() {
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.print("Enter Beverage ID to search (e.g.: B0001): ");
            String bvIDToSearch = scanner.nextLine();
            boolean found = false;

            try (BufferedReader reader = new BufferedReader(new FileReader(BEVERAGE_FILENAME))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] bvAttributes = line.split(",");
                    if (bvAttributes[0].equals(bvIDToSearch)) {
                        System.out.println("Beverage ID: " + bvAttributes[0]);
                        System.out.println("Beverage Name: " + bvAttributes[1]);
                        System.out.println("Price: " + bvAttributes[2]);
                        System.out.println("Quantity: " + bvAttributes[3]);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    System.out.println("Beverage ID not found!");
                }
            } catch (IOException e) {
                System.err.println("Error reading from the beverage file.");
            }

            boolean validResponse = false;
            while (!validResponse) {
                System.out.print("Do you want to search for more beverages? (Y/N): ");
                String userInput = scanner.nextLine();

                if (userInput.equalsIgnoreCase("Y")) {
                    validResponse = true; // User wants to continue searching
                } else if (userInput.equalsIgnoreCase("N")) {
                    return; // User does not want to search more
                } else {
                    System.out.println("Invalid input. Please enter 'Y' or 'N'.");
                }
            }

        } while (true); // Continue searching until the user chooses to exit
    }

    public static void restock() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Beverage ID to add quantity: ");
        String beverageID = scanner.nextLine();

        try (BufferedReader reader = new BufferedReader(new FileReader(BEVERAGE_FILENAME))) {
            File tempFile = new File("temp1.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            boolean found = false;
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String currentBeverageID = parts[0];

                    if (currentBeverageID.equals(beverageID)) {
                        found = true;

                        int quantityToAdd;

                        while (true) {
                            System.out.print("Enter quantity to add: ");
                            String input = scanner.nextLine();

                            // Check if the input is a digit
                            if (input.matches("\\d+")) {
                                quantityToAdd = Integer.parseInt(input);

                                // Check if the quantity is not less than 0
                                if (quantityToAdd >= 0) {
                                    break; // Valid input, exit the loop
                                } else {
                                    System.out.println("Quantity cannot be less than 0. Please try again.");
                                }
                            } else {
                                System.out.println("Invalid input. Please enter a valid digit.");
                            }
                        }

                        int currentQuantity = Integer.parseInt(parts[3]);
                        int newQuantity = currentQuantity + quantityToAdd;

                        line = parts[0] + "," + parts[1] + "," + parts[2] + "," + newQuantity;
                    }
                }

                writer.write(line);
                writer.newLine();
            }

            writer.close();
            reader.close();

            // Delete the original file
            File originalFile = new File(BEVERAGE_FILENAME);
            if (!originalFile.delete()) {
                System.err.println("Error deleting the original file.");
                return;
            }

            // Rename the temporary file to the original file name
            if (!tempFile.renameTo(originalFile)) {
                System.err.println("Error renaming the temporary file.");
                return;
            }

            if (found) {
                System.out.println("Quantity added to Beverage successfully!");
            } else {
                System.out.println("Beverage not found. No changes were made.");
            }

        } catch (IOException e) {
            System.err.println("Error reading/writing to the beverage file.");
        }
    }

    public static void reducebv() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Beverage ID to subtract quantity: ");
        String beverageID = scanner.nextLine();

        try (BufferedReader reader = new BufferedReader(new FileReader(BEVERAGE_FILENAME))) {
            File tempFile = new File("temp1.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            boolean found = false;
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String currentBeverageID = parts[0];

                    if (currentBeverageID.equals(beverageID)) {
                        found = true;

                        int quantityToSubtract;

                        while (true) {
                            System.out.print("Enter quantity to subtract: ");
                            String input = scanner.nextLine();

                            // Check if the input is a digit
                            if (input.matches("\\d+")) {
                                quantityToSubtract = Integer.parseInt(input);

                                // Check if the quantity is not less than 0
                                if (quantityToSubtract >= 0) {
                                    break; // Valid input, exit the loop
                                } else {
                                    System.out.println("Quantity cannot be less than 0. Please try again.");
                                }
                            } else {
                                System.out.println("Invalid input. Please enter a valid digit.");
                            }
                        }

                        var currentQuantity = Integer.parseInt(parts[3]);
                        int newQuantity = currentQuantity - quantityToSubtract;

                        // Ensure the quantity doesn't go below zero
                        if (newQuantity < 0) {
                            newQuantity = 0;
                        }

                        line = parts[0] + "," + parts[1] + "," + parts[2] + "," + newQuantity;
                    }
                }

                writer.write(line);
                writer.newLine();
            }

            writer.close();
            reader.close();

            // Delete the original file
            File originalFile = new File(BEVERAGE_FILENAME);
            if (!originalFile.delete()) {
                System.err.println("Error deleting the original file.");
                return;
            }

            // Rename the temporary file to the original file name
            if (!tempFile.renameTo(originalFile)) {
                System.err.println("Error renaming the temporary file.");
                return;
            }

            if (found) {
                System.out.println("Quantity subtracted from Beverage successfully!");
            } else {
                System.out.println("Beverage not found. No changes were made.");
            }

        } catch (IOException e) {
            System.err.println("Error reading/writing to the beverage file.");
        }
    }

    public static void staff() {
        int choice = 0;
        Scanner scan = new Scanner(System.in);
        logo();
        do {
            System.out.print("""

                             * --------------- *
                    +======= | Food & Beverage | =======+
                    |        * --------------- *        |
                    |                                   |
                    | 1. Add staff                      |
                    | 2. Display staff                  |
                    | 3. Edit staff                     |
                    | 4. Delete staff                   |
                    | 5. Search staff                   |
                    | 6. Back                           |
                    |                                   |
                    +===================================+

                    """);
            boolean continueInput = true;
            do {
                try {
                    System.out.print("Enter a choice: ");
                    choice = scan.nextInt();
                    scan.nextLine();
                    continueInput = false;
                } catch (Exception ex) {
                    System.err.println("Invalid Input");
                    scan.nextLine();
                }
            } while (continueInput);

            switch (choice) {
                case 1 ->
                    addStaff();
                case 2 ->
                    displayStaff(STAFF_FILENAME, scan);
                case 3 ->
                    editStaff();
                case 4 ->
                    deleteStaff();
                case 5 -> {
                    searchstaff();
                }
                case 6 -> {
                    return;
                }
                default ->
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);
    }

    public static void displayStaff(String fileName, Scanner scanner) {
        try (BufferedReader reader = new BufferedReader(new FileReader(STAFF_FILENAME))) {
            System.out.println("=================================================================");
            System.out.println("                         <Staff Members>");
            System.out.println("=================================================================");

            // Define column headers with formatting
            System.out.printf("%-10s %-20s %-15s %-15s%n", "ID", "Name", "Phone", "Password");

            System.out.println("=================================================================");

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String staffId = parts[0];
                    String name = parts[1];
                    String phone = parts[2];
                    String password = parts[3];

                    // Use formatting to align columns
                    System.out.printf("%-10s %-20s %-15s %-15s%n", staffId, name, phone, password);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from the staff file.");
        }

        System.out.println("\nPress any key and hit enter to exit...");
        scanner.nextLine(); // Wait for user input
    }

    private static void editStaff() {
        Scanner scanner = new Scanner(System.in);
        String staffID;
        displayStaff(STAFF_FILENAME, scanner);
        do {
            System.out.print("Enter Staff ID to edit (enter 'x' to exit): ");
            staffID = scanner.nextLine();

            // Check if 'x' was entered to exit the function
            if (staffID.equalsIgnoreCase("x")) {
                break; // Exit the loop and the function
            }

            // Load the staff information using encapsulation
            StaffAss staff = loadStaff(staffID);

            if (staff == null) {
                System.out.println("Staff not found. No changes were made.");
                continue; // Restart the loop if staff is not found
            }

            System.out.println("Current Staff Information:");
            System.out.println("--------------------------");
            System.out.println("Staff Name: " + staff.getName());
            System.out.println("Staff Phone: " + staff.getPhone());
            System.out.println("Staff Password: " + staff.getPassword());

            System.out.println("""

                    What do you want to edit?
                    -------------------------

                    a. Staff Name
                    b. Staff Phone Number
                    c. Password
                    x. Exit

                    """);
            System.out.println("Enter your choice:");
            char choice = scanner.nextLine().charAt(0);

            switch (choice) {
                case 'a' -> {
                    System.out.print("Enter new staff name: ");
                    String newName = scanner.nextLine();
                    staff.setName(newName);
                }
                case 'b' -> {
                    String newPhone;
                    do {
                        System.out.print("Enter new staff phone number (10 digits): ");
                        newPhone = scanner.nextLine();
                    } while (newPhone.length() != 10); // Check for 8-digit phone number
                    staff.setPhone(newPhone);
                }
                case 'c' -> {
                    String newPassword;
                    do {
                        System.out.print("Enter new staff password (at least 8 characters): ");
                        newPassword = scanner.nextLine();
                    } while (newPassword.length() < 8); // Check for minimum 8-character password
                    staff.setPassword(newPassword);
                }
                case 'x' -> {
                    return;
                }
                default ->
                    System.out.println("Invalid choice. No changes were made.");
            }

            // Save the updated staff information
            saveStaff(staff);

            System.out.println("Staff edited successfully!");
        } while (true); // Keep looping until 'x' is entered
    }

    public static void saveStaff(StaffAss editedStaff) {
        try {
            File file = new File(STAFF_FILENAME);
            if (!file.exists()) {
                System.err.println("Staff file not found.");
                return;
            }

            // Load all staff information from the file into a list
            List<String> staffLines = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    staffLines.add(line);
                }
            }

            // Construct the updated staff information in the same format as your file
            String updatedStaffInfo = editedStaff.getStaffID() + ","
                    + editedStaff.getName() + ","
                    + editedStaff.getPhone() + ","
                    + editedStaff.getPassword();

            // Find and replace the line corresponding to the edited staff member
            for (int i = 0; i < staffLines.size(); i++) {
                String[] parts = staffLines.get(i).split(","); // Use the same delimiter here
                if (parts.length == 4 && parts[0].equals(editedStaff.getStaffID())) {
                    staffLines.set(i, updatedStaffInfo); // Replace with updated staff information
                    break; // No need to continue searching
                }
            }

            // Rewrite all staff information back to the same text file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (String staffLine : staffLines) {
                    writer.write(staffLine);
                    writer.newLine();
                }
            }

            System.out.println("Staff saved successfully!");

        } catch (IOException e) {
            System.err.println("An error occurred while saving the staff: " + e.getMessage());
        }
    }

    public static StaffAss loadStaff(String staffID) {
        try (BufferedReader reader = new BufferedReader(new FileReader(STAFF_FILENAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(","); // Use the same delimiter here
                if (parts[0].equals(staffID)) {
                    System.out.println("Found staff with ID: " + staffID);
                    return new StaffAss(parts[0], parts[1], parts[2], parts[3]);
                }
            }
            System.out.println("Staff not found in loadStaff method.");
        } catch (IOException e) {
            System.err.println("Error reading from the staff file: " + e.getMessage());
        }
        return null;
    }

    public static void addStaff() {
        Scanner scanner = new Scanner(System.in);
        String confirmation;
        String staffID;
        boolean isIDTaken;

        do {
            System.out.print("Do you want to register? (y/n): ");
            confirmation = scanner.nextLine();

            // If user inputs 'n' or 'N', return immediately without further processing
            if (confirmation.equalsIgnoreCase("n")) {
                return;
            }

            // If the user inputs an invalid choice, display an error message and loop again
            if (!confirmation.equalsIgnoreCase("y") && !confirmation.equalsIgnoreCase("n")) {
                System.out.println("Invalid input. Please enter y or n.");
            }

        } while (!confirmation.equalsIgnoreCase("y") && !confirmation.equalsIgnoreCase("n"));
        do {
            System.out.print("Enter staff ID (The first character must start with 'S' or enter 'x' to exit): ");
            staffID = scanner.nextLine();

            if (staffID.equalsIgnoreCase("x")) {
                return;
            }

            isIDTaken = isStaffIDTaken(staffID); // Check if the ID is already taken
            if (isIDTaken) {
                System.out.println("Staff ID already taken. Please choose a different one.");
            } else if (!staffID.toUpperCase().startsWith("S")) {
                System.out.println("Staff ID must start with 'S'. Please try again.");
            }
        } while (isIDTaken || !staffID.toUpperCase().startsWith("S"));

        String password;
        String confirmPassword;

        do {
            System.out.print("Enter password (at least 8 characters) or enter 'x' to exit: ");
            password = scanner.nextLine();

            if (password.equalsIgnoreCase("x")) {
                return;
            }

            if (password.length() < 8) {
                System.out.println("Password must be at least 8 characters long. Please try again.");
            }
        } while (password.length() < 8);

        do {
            System.out.print("Confirm password or enter 'x' to exit: ");
            confirmPassword = scanner.nextLine();

            if (confirmPassword.equalsIgnoreCase("x")) {
                return;
            }

            if (!password.equals(confirmPassword)) {
                System.out.println("Passwords do not match. Please try again.");
            }
        } while (!password.equals(confirmPassword));

        String name;

        do {
            System.out.print("Enter name or enter 'x' to exit: ");
            name = scanner.nextLine();

            if (name.equalsIgnoreCase("x")) {
                return;
            }

            if (name.trim().isEmpty()) {
                System.out.println("Name cannot be left blank. Please try again.");
            }
        } while (name.trim().isEmpty());

        String phone;

        do {
            System.out.print("Enter phone (10 characters) or enter 'x' to exit: ");
            phone = scanner.nextLine();

            if (phone.equalsIgnoreCase("x")) {
                return;
            }

            if (phone.length() != 10) {
                System.out.println("Phone number must be 8 characters long. Please try again.");
            }
        } while (phone.length() != 10);

        StaffAss staff = new StaffAss(staffID, name, phone, password);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(STAFF_FILENAME, true))) {
            writer.write(
                    staff.getStaffID() + "," + staff.getName() + "," + staff.getPhone() + "," + staff.getPassword());
            writer.newLine();
            System.out.println("Registration successful!");
        } catch (IOException e) {
            System.err.println("Error writing to the staff file.");
        }

    }

    private static void deleteStaff() {
        Scanner scanner = new Scanner(System.in);
        displayStaff(STAFF_FILENAME, scanner);
        do {
            System.out.print("Enter Staff ID to delete (enter 'x' to exit): ");
            String staffIDToDelete = scanner.nextLine();

            // Check if 'x' was entered to exit the function
            if (staffIDToDelete.equalsIgnoreCase("x")) {
                return;
            }

            // Check if the staff ID exists
            boolean staffExists = isStaffIDTaken(staffIDToDelete);

            if (!staffExists) {
                System.out.println("Staff ID not found. No changes were made.");
            } else {
                boolean confirmDeletion = false;
                while (true) {
                    // Confirm deletion
                    System.out.print("Are you sure you want to delete this staff member? (y/n): ");
                    String confirmation = scanner.nextLine().toLowerCase();

                    if (confirmation.equals("y")) {
                        confirmDeletion = true;
                        break;
                    } else if (confirmation.equals("n")) {
                        break; // Exit the confirmation loop and continue with the next staff ID
                    } else {
                        System.out.println("Invalid input. Please enter 'y' or 'n'.");
                    }
                }

                if (confirmDeletion) {
                    // Delete the staff member
                    deleteStaffRecord(staffIDToDelete);
                    System.out.println("Staff deleted successfully!");
                } else {
                    System.out.println("Staff not deleted.");
                }
            }
        } while (true); // Keep looping until 'x' is entered
    }
    // Function to delete the staff record from the file

    public static void deleteStaffRecord(String staffIDToDelete) {
        Scanner scanner = new Scanner(System.in);
        displayStaff(STAFF_FILENAME, scanner);
        try {
            File file = new File(STAFF_FILENAME);
            if (!file.exists()) {
                System.err.println("Staff file not found.");
                return;
            }

            // Load all staff information from the file into a list
            List<String> staffLines = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    staffLines.add(line);
                }
            }

            // Remove the line corresponding to the staff member to be deleted
            staffLines.removeIf(line -> line.startsWith(staffIDToDelete + ","));

            // Rewrite all staff information back to the same text file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (String staffLine : staffLines) {
                    writer.write(staffLine);
                    writer.newLine();
                }
            }

        } catch (IOException e) {
            System.err.println("An error occurred while deleting the staff: " + e.getMessage());
        }
    }

    public static void searchstaff() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter Staff ID to search (e.g.: S0001) or 'x' to exit: ");
            String staffIDToSearch = scanner.nextLine();

            // Check if 'x' was entered to exit the function
            if (staffIDToSearch.equalsIgnoreCase("x")) {
                return;
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(STAFF_FILENAME))) {
                String line;
                boolean found = false;
                while ((line = reader.readLine()) != null) {
                    String[] staffAttributes = line.split(",");
                    if (staffAttributes[0].equals(staffIDToSearch)) {
                        System.out.println("Searched Staff");
                        System.out.println("--------------");
                        System.out.println("\nStaff ID: " + staffAttributes[0]);
                        System.out.println("Staff Name: " + staffAttributes[1]);
                        System.out.println("Phone number: " + staffAttributes[2]);
                        System.out.println("Password: " + staffAttributes[3]);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    System.out.println("Staff ID not found!");
                }
            } catch (IOException e) {
                System.err.println("Error reading from the staff file.");
            }

            // Ask if the user wants to search for more staff IDs
            String searchMore;
            while (true) {
                System.out.print("Do you want to search for more staff IDs? (y/n): ");
                searchMore = scanner.nextLine().toLowerCase();
                if (searchMore.equals("y") || searchMore.equals("n")) {
                    break;
                } else {
                    System.out.println("Invalid input. Please enter 'y' or 'n'.");
                }
            }

            if (searchMore.equals("n")) {
                return; // Return if the user doesn't want to search more
            }
        }
    }

    public static void purchase(double balance, String customerID, Customer customer) {
        Scanner scan = new Scanner(System.in);
        char verification;
        do {
            System.out.printf("Does customer want to do purchase? (Y = Yes / N = No) : ");
            verification = scan.nextLine().charAt(0);
            if (Character.toUpperCase(verification) != 'Y' && Character.toUpperCase(verification) != 'N') {
                System.out.println("Invalid Input\n");
            }
        } while (Character.toUpperCase(verification) != 'Y' && Character.toUpperCase(verification) != 'N');
        if (Character.toUpperCase(verification) == 'Y') {
            displayFood("food_items.txt", scan);
            displayBeverage(BEVERAGE_FILENAME, scan);
            makePurchase("food_items.txt", "beverage.txt", balance, customerID, customer);

        }

    }

    public static void makePurchase(String foodFileName, String beverageFileName, double balance, String customerID,
            Customer customer) {
        Scanner scan = new Scanner(System.in);
        List<FoodAss> foodItems = readFoodItemsFromFile(foodFileName);
        List<baverage> beverages = readBeveragesFromFile(beverageFileName);
        String temp_foodFileName = "temp_food.txt";
        String temp_beverageFileName = "temp_beverage.txt";

        // Create an Order object to store order details
        Order order = new Order();

        // Initialize the foodItems and beverageItems lists
        order.setFoodItems(new ArrayList<>());
        order.setBeverageItems(new ArrayList<>());

        Map<String, Integer> purchasedQuantities = new HashMap<>();
        int tableNumber;

        while (true) {
            try {
                System.out.print("Enter the table number (1-10): ");
                tableNumber = scan.nextInt();
                scan.nextLine(); // Consume the newline character

                if (tableNumber < 1 || tableNumber > 10) {
                    System.out.println("Invalid table number. Please enter a number between 1 and 10.");
                } else {
                    break; // Exit the loop if a valid number is entered
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number between 1 and 10.");
                scan.nextLine(); // Consume the invalid input
            }
        }

        while (true) {
            System.out.print("\nEnter the product ID to add to the order or 'Q' to finish the order: ");
            String input = scan.nextLine();

            if (input.equalsIgnoreCase("X")) {
                return;
            } else {
                if (input.equalsIgnoreCase("Q")) {
                    break; // Exit the loop if 'Q' is entered
                }
            }

            boolean found = false;
            int quantity = getQuantityFromUser1(scan);
            purchasedQuantities.put(input, quantity); // Store purchased quantity

            // Check for food items
            for (FoodAss foodItem : foodItems) {
                if (foodItem.getFoodID().equalsIgnoreCase(input)) {
                    System.out.println("Selected Food Item: " + foodItem.getFood());

                    if (foodItem.getquantity() >= quantity) {
                        foodItem.setquantity(foodItem.getquantity() - quantity);
                        double itemPrice = quantity * foodItem.getPrice();
                        foodItem.setPrice(itemPrice); // Update item price
                        order.getFoodItems().add(foodItem);
                        order.setTotalPrice(order.getTotalPrice() + itemPrice); // Update total price
                        found = true;
                        double ttlPrice1 = order.getTotalPrice() + itemPrice;

                        if (balance < ttlPrice1) {
                            System.out.println(
                                    "Insufficient balance. You cannot use the balance to cover the entire amount.");
                            return;
                        }
                        // Reduce the quantity in the file
                        reducefoods(foodFileName, temp_foodFileName, foodItem.getFoodID(), quantity);
                    } else {
                        System.out.println("Insufficient quantity available for " + foodItem.getFood());
                    }
                    break; // Exit the loop after processing the selection
                }
            }

            // Check for beverage items
            if (!found) {
                for (baverage beverage : beverages) {
                    if (beverage.getbaverageId().equalsIgnoreCase(input)) {
                        System.out.println("Selected Beverage: " + beverage.getbaverage());

                        if (beverage.getquantity() >= quantity) {
                            beverage.setquantity(beverage.getquantity() - quantity);
                            double itemPrice = quantity * beverage.getPrice1();
                            beverage.setPrice1(itemPrice); // Update item price
                            order.getBeverageItems().add(beverage);
                            order.setTotalPrice(order.getTotalPrice() + itemPrice); // Update total price
                            double ttlPrice = order.getTotalPrice() + itemPrice;
                            found = true;
                            if (balance < ttlPrice) {
                                System.out.println(
                                        "Insufficient balance. You cannot use the balance to cover the entire amount.");
                                return;
                            }
                            // Reduce the quantity in the file
                            reducebeverages(beverageFileName, temp_beverageFileName, beverage.getbaverageId(),
                                    quantity);
                        } else {
                            System.out.println("Insufficient quantity available for " + beverage.getbaverage());
                        }
                        break; // Exit the loop after processing the selection
                    }
                }
            }

            if (!found) {
                System.out.println("Invalid product ID.");
            }
        }

        // Update the total order price for the table
        double tableTotalPrice = tableOrderPrices.getOrDefault(tableNumber, 0.0);
        tableTotalPrice += order.getTotalPrice();
        tableOrderPrices.put(tableNumber, tableTotalPrice);
        if (balance < tableTotalPrice) {
            System.out.println("Insufficient balance. You cannot use the balance to cover the entire amount.");
            return;
        }

        System.out.println("\n===== Receipt =====");
        System.out.println("Table Number: " + tableNumber);
        for (FoodAss orderItem : order.getFoodItems()) {

            int purchasedQuantity = purchasedQuantities.getOrDefault(orderItem.getFoodID(), 0); // Get purchased
                                                                                                // quantity
            System.out.println("Product: " + orderItem.getFood()
                    + "\nQuantity: " + purchasedQuantity
                    + // Print purchased quantity
                    "\nPrice: $" + String.format("%.2f", orderItem.getPrice())
                    + "\n-------------------------");
        }

        for (baverage orderItem : order.getBeverageItems()) {
            int purchasedQuantity = purchasedQuantities.getOrDefault(orderItem.getbaverageId(), 0); // Get purchased
                                                                                                    // quantity
            System.out.println("Product: " + orderItem.getbaverage()
                    + "\nQuantity: " + purchasedQuantity
                    + // Print purchased quantity
                    "\nPrice: $" + String.format("%.2f", orderItem.getPrice1())
                    + "\n-------------------------");
        }

        System.out.printf("Total Price: $%.2f\n", order.getTotalPrice());
        generateSummaryReportToFile("summary_report.txt");
        // Handle payment
        if (balance < order.getTotalPrice()) {
            System.out.println("Insufficient balance. You cannot use the balance to cover the entire amount.");
            return;
        } else {
            balance -= order.getTotalPrice();
        }
        System.out.printf("Balance:%.2f\n", balance);
        String inputFile = "customer.txt";
        String tempFile = "temp_customer.txt";
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4 && parts[0].equalsIgnoreCase(customerID)) {

                    double newBalance = balance;
                    writer.write(customerID + "," + parts[1] + "," + parts[2] + "," + newBalance);

                    writer.newLine();
                    System.out.println("Balance update successfully!");
                    customer.setBalance(newBalance);
                } else {
                    writer.write(line);
                    writer.newLine();
                }
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("Error update balance " + e.getMessage());
        }

        // Perform file renaming after editing
        renameFiles(inputFile, tempFile);

        System.out.println("Thank you for your payment.");
        return;
        // handlePayment(order, balance, customerID, customer);
    }

    public static void handlePayment(Order order, double balance, String customerID, Customer customer) {
        Scanner scan = new Scanner(System.in);
        double payment;

        do {
            System.out.println("Balance: " + balance);
            System.out.print("\nEnter payment amount: $");
            payment = scan.nextDouble();
            scan.nextLine(); // Consume the newline character

            if (balance < order.getTotalPrice()) {
                System.out.println("Insufficient balance. You cannot use the balance to cover the entire amount.");
            } else if (payment < order.getTotalPrice()) {
                System.out.println(
                        "Insufficient payment. Please enter an amount equal to or greater than the total price.");
            } else if (payment == order.getTotalPrice() || payment > order.getTotalPrice()) {
                double change = payment - order.getTotalPrice();

                System.out.printf("Change: $%.2f\n", change);
                balance = (balance - payment) + change;
                System.out.printf("balance:$%.2f\n", balance);
                String inputFile = "customer.txt";
                String tempFile = "temp_customer.txt";
                String line;
                try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split(",");
                        if (parts.length == 4 && parts[0].equalsIgnoreCase(customerID)) {

                            double newBalance = balance;
                            writer.write(customerID + "," + parts[1] + "," + parts[2] + "," + newBalance);

                            writer.newLine();
                            System.out.println("Balance update successfully!");
                            customer.setBalance(newBalance);
                        } else {
                            writer.write(line);
                            writer.newLine();
                        }
                    }
                    reader.close();
                } catch (IOException e) {
                    System.err.println("Error update balance " + e.getMessage());
                }

                // Perform file renaming after editing
                renameFiles(inputFile, tempFile);

                System.out.println("Thank you for your payment.");
                System.out.println(
                        "\n**********************************\nPress any key to go back to the main menu\n**********************************");
                return;
            } else {
                System.out.println("Payment received. Thank you!");
            }
        } while (payment != order.getTotalPrice());
    }

    private static void reducefoods(String foodFileName, String tempFileName, String foodID, int quantityToSubtract) {
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(foodFileName));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFileName))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5 && parts[0].equalsIgnoreCase(foodID)) {
                    found = true;
                    int currentQuantity = Integer.parseInt(parts[4]);
                    int newQuantity = currentQuantity - quantityToSubtract;

                    // Ensure the quantity doesn't go below zero
                    if (newQuantity < 0) {
                        newQuantity = 0;
                    }

                    line = foodID + "," + parts[1] + "," + parts[2] + "," + parts[3] + "," + newQuantity;

                    writer.write(line);
                    writer.newLine();
                    System.out.println("Quantity subtracted from food item successfully!");
                } else {
                    // Write the existing line to the temp file if it's not the item to edit
                    writer.write(line);
                    writer.newLine();
                }
            }

            if (!found) {
                System.out.println("Food item with ID " + foodID + " not found.");
            }
        } catch (IOException e) {
            System.err.println("Error subtracting quantity from food item: " + e.getMessage());
        }

        // Perform file renaming after editing
        renameFiles(foodFileName, tempFileName);
    }

    private static void reducebeverages(String beverageFileName, String tempFileName1, String beverageID,
            int quantityToSubtract) {

        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(beverageFileName));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFileName1))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4 && parts[0].equalsIgnoreCase(beverageID)) {
                    found = true;
                    int currentQuantity = Integer.parseInt(parts[3]);
                    int newQuantity = currentQuantity - quantityToSubtract;

                    // Ensure the quantity doesn't go below zero
                    if (newQuantity < 0) {
                        newQuantity = 0;
                    }

                    line = beverageID + "," + parts[1] + "," + parts[2] + "," + newQuantity;

                    writer.write(line);
                    writer.newLine();
                    System.out.println("Quantity subtracted from food item successfully!");
                } else {
                    // Write the existing line to the temp file if it's not the item to edit
                    writer.write(line);
                    writer.newLine();
                }
            }

            if (!found) {
                System.out.println("Food item with ID " + beverageID + " not found.");
            }
        } catch (IOException e) {
            System.err.println("Error subtracting quantity from food item: " + e.getMessage());
        }

        // Perform file renaming after editing
        renameFiles(beverageFileName, tempFileName1);
    }

    public static int getQuantityFromUser1(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Enter quantity to purchase: ");
                String input = scanner.nextLine();
                int quantity = Integer.parseInt(input);
                return quantity;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    public static List<FoodAss> readFoodItemsFromFile(String fileName) {
        List<FoodAss> foodItems = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String foodID = parts[0];
                    String foodName = parts[1];
                    double price = Double.parseDouble(parts[2]);
                    String foodDetail = parts[3];
                    int quantity = Integer.parseInt(parts[4]);
                    FoodAss foodItem = new FoodAss(foodID, foodName, price, quantity);
                    foodItems.add(foodItem);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from the file: " + fileName);
        }

        return foodItems;
    }

    public static List<baverage> readBeveragesFromFile(String fileName) {
        List<baverage> beverages = new ArrayList<>();

        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String baverageId = parts[0];
                    String baverageName = parts[1];
                    double price = Double.parseDouble(parts[2]);
                    int quantity = Integer.parseInt(parts[3]);

                    // Create a baverage object and add it to the list
                    baverage beverage = new baverage(baverageId, baverageName, price, quantity);
                    beverages.add(beverage);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from the beverage file.");
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.err.println("Error closing the reader.");
            }
        }

        return beverages;
    }

}
