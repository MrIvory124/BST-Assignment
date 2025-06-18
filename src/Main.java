public class Main {
    public static void main(String[] args) {
        ApplianceLookup console = new ApplianceLookup();

        // main loop for running the console
        // appears to never exit. Program close is handled in the applianceLookup.java file
        while(true) {
            console.printMenu();
            try {
                console.handleMenuInput();
            } catch (Exception e) {
                System.out.println("Menu interrupted");
            }
        }

    }
}