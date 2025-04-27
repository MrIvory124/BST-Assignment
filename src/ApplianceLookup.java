import java.io.*;
import java.util.Arrays;
import java.util.Objects;

public class ApplianceLookup {

    // defining console colors
    final String ANSI_RESET = "\u001B[0m";
    final String ANSI_GRAY = "\u001B[90m";
    final String ANSI_RED = "\u001B[91m";
    final String ANSI_GREEN = "\u001B[32m";
    final String ANSI_BLUE = "\u001B[94m";
    final String ANSI_YELLOW = "\u001B[33m";

    final int WAITTIME = 5;

    // console related things
    boolean isValidConsole = true;
    final Console console;

    // bst related things
    ApplianceBST applianceBST = null;

    /**
     * Public entry into the class
     */
    public ApplianceLookup() {
        // set up the console
        Console javaConsole = System.console();
        console = javaConsole;
        if (javaConsole == null) {
            isValidConsole = false;
        }

        System.out.println("Are you in valid console environment? " + ANSI_GREEN + isValidConsole + ANSI_RESET);
        sleep(1);
        clearScreen();
        System.out.println("Loading program");
        sleep(0.2);
        // if we are in an invalid console, tell user and quit execution
        if (!isValidConsole) {
            printRed("Invalid console environment, please use CMD or Powershell");
            System.exit(-1);
        }

        // prompt user for csv
        boolean isValidFile = false;
        while (!isValidFile) {
            // check said csv and load it into bst
            applianceBST = new ApplianceBST();
            isValidFile = isCSVCheckRead();
        }
    }

    /**
     * @return Returns boolean based on execution status
     */
    /*This method checks the csv then reads it**/
    private boolean isCSVCheckRead() {
        String filePath;
        // prompt user for input
        clearScreen();
        printGray("Place csv in same directory as this jar, then enter its name with file extension");
        printGray("Typing 'exit' will exit gracefully.");
        printGray("If no input is entered, will attempt to use 'appliances.csv'. ");
        filePath = console.readLine("Enter path of csv: ");

        // no input from the user, set default
        if (filePath == null || filePath.trim().isEmpty()) {
            filePath = "appliances.csv";
        }

        File file = new File(filePath);
        // if the user wants to quit, close the program
        if (filePath.equals("exit")) {
            closeProgram();
        }
        // should the file exist, attempt to read it
        else if (file.exists() && file.isFile()) {
            return readCSV(filePath);
        }
        // should this return any errors, inform the user and start file selection process again
        else {
            clearScreen();
            printRed("Invalid file, please try again");
            sleep(1);
            return false;
        }
        return false;
    }

    /**Method for handling the reading of the csv assuming its valid*/
    public boolean readCSV(String filePath) {
        clearScreen();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                // process values
                Appliance current;
                try {
                    current = new Appliance(values[1], Float.parseFloat(values[2]), values[0]);
                    applianceBST.insert(current);
                    System.out.println(ANSI_GREEN + "Added appliance " + current );
                } catch (Exception e) {
                    printRed("Invalid appliance " + Arrays.toString(values));
                }

            }
        } catch (IOException e) {
            System.out.println("There was an issue with the file provided: ");
            printRed("Error reading file: " + e.getMessage());
            System.out.println("Please try again");
            promptToContinue();
            return false;
        }
        // reset console color
        System.out.println(ANSI_RESET);
        return true;
    }

    /**
     * This will initialise the console area and print the main menu
     */
    public void printMenu() {
        clearScreen();
        System.out.println(ANSI_BLUE + "=== Main Menu ===" + ANSI_RESET);
        System.out.println("1. Search for appliance");
        System.out.println("2. Add new appliance");
        System.out.println("3. Remove an appliance");
        System.out.println("4. Print all the appliances in a category");
        System.out.println("5. Print all the appliances in a category with range");
        System.out.println("6. Print all items");
        System.out.println("7." + ANSI_RED + " Exit" + ANSI_RESET);
    }

    /** This is the main menu/entry into user input in the program.*/
    public void handleMenuInput() {
        while (true) {
            System.out.print("Enter your option: ");
            switch (console.readLine().trim()) {
                case "1":
                    searchInputAppliance(); return;
                case "2":
                    addNewAppliance(); return;
                case "3":
                    removeAppliance(); return;
                case "4":
                    printSelectedCategory(); return;
                case "5":
                    printSelectedCategoryWithRange(); return;
                case "6":
                    printAllStoredItems(); return;
                case "7", "exit", "quit", "Exit", "close":
                    closeProgram();
                    return;
                // I want to put a case in here for null, but that would require java 21
                // null only happens upon someone doing ctrl + c
                default:
                    clearScreen();
                    printRed("Invalid input, try again.");
                    promptToContinue();
                    clearScreen();
                    printMenu();
            }
        }
    }

    private void printAllStoredItems() {
        clearScreen();
        System.out.println(ANSI_BLUE + "Appliance List" + ANSI_RESET);
        printGreen("%-20s | %-40s | %s".formatted("Category", "Name", "Price"));
        applianceBST.print();
        promptToContinue();
    }

    /// Below are all the methods that are options in the menu
    private void searchInputAppliance() {
        clearScreen();

        // get user input
        Appliance current = getAppliance();

        //process input
        try {
            boolean exists = applianceBST.search(current);
            // print the output, with color changed for the true or false
            String color = exists ? ANSI_GREEN : ANSI_RED;
            System.out.printf("Does the appliance "
                    + ANSI_BLUE + current.getName() + ANSI_RESET
                    + " exist? "
                    + color + exists + ANSI_RESET);
            System.out.println();
            promptToContinue();
        } catch (Exception e) {
            // should there be an internal error, let the user know
            printRed("Error processing request: " + e.getMessage());
        }
    }

    private void addNewAppliance() {
        clearScreen();

        // get user input
        Appliance current = getAppliance();

        // if we have a valid appliance, add to bst
        if (current != null) {
            applianceBST.insert(current);
            printGreen("Added appliance " + ANSI_BLUE + current.getName() + ANSI_GREEN + " with category and price: " + ANSI_BLUE + current.getCategory() + ", " + current.getPrice() + ANSI_RESET);
            promptToContinue();
        }
    }

    private void removeAppliance() {
        clearScreen();

        // get user input
        Appliance current = getAppliance();

        if (current != null) {
            try {
                if (!applianceBST.search(current)){
                    printGreen("Appliance " + ANSI_BLUE + current.getName() + ANSI_GREEN + " does not exist");
                    promptToContinue();
                    return;
                }
                applianceBST.remove(current);
                printGreen("Removed appliance " + ANSI_BLUE + current.getName() + ANSI_GREEN + " with category and price: " + ANSI_BLUE + current.getCategory() + ", " + current.getPrice());
                promptToContinue();
                return;
            } catch (Exception e) {
                printRed("Error removing: " + e.getMessage());
                System.out.println("Please try again");
                promptToContinue();
            }
        }
    }

    private void printSelectedCategory() {
        clearScreen();

        // get user input
        printGray("type '!exit' to exit");
        String category = console.readLine("Enter the category you want to print: ");

        // exit if required
        if (category.equals("!exit")) {
            return;
        }

        // print bst
        printGreen("%-20s | %-40s | %s".formatted("Category", "Name", "Price"));
        applianceBST.printCategory(category);

        promptToContinue();
    }

    private void printSelectedCategoryWithRange() {
        clearScreen();

        // get user input
        String category;
        while(true){
            clearScreen();
            printGray("Type '!exit' to exit");
            category = console.readLine("Enter the category you want to print: ");
            if (category.isEmpty()) {
                printRed("Please enter a valid category");
                sleep(1);
            }else if (category.equals("!exit")) {
                return;
            }else{
                break;
            }
        }

        // setup variables for future reference
        float upper = 0;
        boolean hasUpper = false;
        float lower = 0;
        boolean hasLower = false;

        // prompt user for the lower range they want to use
        Float lowerInput = getFloatInput("Lower");
        if (lowerInput != null) {
            lower = lowerInput;
            hasLower = true;
        }

        // prompt user for upper range
        Float upperInput = getFloatInput("Upper");
        if (upperInput != null) {
            upper = upperInput;
            hasUpper = true;
        }

        /// This is a stupid way to do this, but the assignment requires us to have different methods for
        /// looking for values that do not have an upper or lower range. I have made it so they have the same functionality
        /// under the hood anyway, and the only difference is the upper or lower range is set functionally to inf.

        printGreen("%-20s | %-40s | %s".formatted("Category", "Name", "Price"));
        // switch into the case where it has ranges
        if (hasUpper && hasLower) {
            applianceBST.printCategoryWithPriceRange(category, lower, upper);
        }
        else if (hasUpper && !hasLower) {
            applianceBST.printCategoryBelowPrice(category, upper);
        }
        else if (!hasUpper && hasLower) {
            applianceBST.printCategoryAbovePrice(category, lower);
        }else { // in the case they entered no range, warn them but still show results
            applianceBST.printCategory(category);
            printYellow("WARN: In future if no range, use option 4 instead");
        }
        promptToContinue();
    
    }

    private Float getFloatInput(String type){
        while (true){
            clearScreen();
            System.out.println(type + " range?");
            printGray("Format of xxxx.xx \nLeave this empty to have no range.");
            String inputRange = console.readLine();
            // parse input
            if (inputRange.isEmpty() || inputRange.equals(" ")) {
                return null;
            } else {
                try {
                    return Float.parseFloat(inputRange);
                }catch (NumberFormatException e) {
                    printRed("Please enter a valid number...");
                    promptToContinue();
                }
            }
        }
    }

    ///  Below are all the methods that are used for functionality of the console
    ///  If we were permitted to use more classes these would go in a seperate class

    /**Method for validating the input of an appliance from the user*/
    private Appliance getAppliance() {
        boolean validInput = false;
        Appliance current = null;
        while (!validInput){
            clearScreen();
            printGray("Type '!exit' to exit at any time");
            String input = console.readLine("Input appliance name: ");
            if (Objects.equals(input, "!exit")){ return null; }
            String input1 = console.readLine("Input appliance price (format: xxxx.xx): ");
            if (Objects.equals(input1, "!exit")){ return null; }
            String input2 = console.readLine("Input appliance category: ");
            if (Objects.equals(input2, "!exit")){ return null; }

            try {
                float input1Converted = Float.parseFloat(input1);
                if (input1Converted < 0) {
                    throw new Exception("Price cannot be negative");
                }
                current = new Appliance(input2, input1Converted, input);
                validInput = true;
            }catch (Exception e)
            {
                printRed("Invalid data entered:\n" + e.getMessage());
                promptToContinue();
            }
        }
        return current; // never returns null because it does not exist without valid appliance object
    }

    private void closeProgram() {
        String confirm = console.readLine("Are you sure you want to exit? (y/n): ");
        if (confirm.equalsIgnoreCase("y")) {
            clearScreen();
            String goodbye = ANSI_RED + "G" + ANSI_YELLOW + "o" + ANSI_GREEN + "o" + ANSI_BLUE + "d" + ANSI_RED + "b" + ANSI_YELLOW + "y" + ANSI_GREEN + "e" + ANSI_BLUE + "!" + ANSI_RESET;

            System.out.println(goodbye);
            System.exit(0);
        }else{
            printYellow("Returning to main menu");
            provideCountdown(2);
            clearScreen();
        }
    }

    private void provideCountdown(int seconds){
        System.out.println();
        for (int i = seconds; i > 0; i--) {
            System.out.print("                                  \rResetting page in: " + ANSI_YELLOW + i + "s" + ANSI_RESET);
            sleep(1);
        }
    }

    /**Method for doing sleeping in a thread
     * @param seconds The number of seconds you want the program to sleep for*/
    private void sleep(double seconds){
        try {
            Thread.sleep((long) (seconds * 1000));
        } catch (InterruptedException e) {
            printRed("Error with sleep");
            System.out.print("                \r");
        }
    }

    /**A method that waits for the user to confirm they are ready to move on
     * @implNote Currently only waits for y to be typed, in future could pass a letter to it*/
    private void promptToContinue() {
        console.readLine("Press Enter to continue...");
    }
    
    /**
     * Obtains the OS, prints special characters and resets the console
     */
    private static void clearScreen() {
        try {
            String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // for linux, macOS
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("Could not clear the screen.");
        }
    }

    /// Section for formatting of words into the console
    private void printRed(String c){
        System.out.println(ANSI_RED + c + ANSI_RESET);
    }

    private void printGreen(String c){
        System.out.println(ANSI_GREEN + c + ANSI_RESET);
    }

    private void printGray(String c){
        System.out.println(ANSI_GRAY + c + ANSI_RESET);
    }

    private void printYellow(String c){
        System.out.println(ANSI_YELLOW + c + ANSI_RESET);
    }

}
