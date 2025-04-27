public class Main {
    public static void main(String[] args) {
        ApplianceLookup console = new ApplianceLookup();

        // this test below is to show output matches the example output given in assignment
        // if you are testing something more extensive change the below var to false to skip
        if (false) {

            System.out.println("Printing test output from examples");
            System.out.println("---------------------------------------");
            ApplianceBST bst = new ApplianceBST();

            // creating appliances
            Appliance a = new Appliance("Aircon", 1030.20f, "Heat pump");
            Appliance b = new Appliance("Fridge", 1000.20f, "Stand Freezer");
            Appliance c = new Appliance("Fridge", 10430.20f, "Stander Freezest");
            Appliance d = new Appliance("Oven", 100.20f, "Candle");
            Appliance e = new Appliance("Oven", 10200.20f, "Gas Stove");

            // using bst insert
            bst.insert(b);
            bst.insert(a);
            bst.insert(c);
            bst.insert(e);
            bst.insert(d);

            // showing structure of tree
            bst.printNodeChildren();

            // testing has element
            System.out.println("Has element " + d.getName() + ": " + bst.search(d));
            System.out.println("Has element " + a.getName() + ": " + bst.search(a));

            System.out.println("Sorted elements");
            bst.print();

            System.out.println("Testing remove");
            bst.remove(a);

            System.out.println("Printing tree for remove");
            bst.print();

            System.out.println("Height: " + bst.getHeight());

            System.out.println("Min: " + bst.getMinimum());
            System.out.println("Max: " + bst.getMaximum());

            System.out.println(" ");
            System.out.println("Looking for things in category");
            // change below to change category
            bst.printCategory("Fridge");

            System.out.println(" ");
            System.out.println("Looking for things in category oven between 0 and 200 in price");
            // change below to change category
            bst.printCategoryWithPriceRange("Oven", 0.00f, 200.00f);

            System.out.println(" ");
            System.out.println("Looking for things in category oven under 200");
            // change below to change category
            bst.printCategoryBelowPrice("Oven", 200.00f);

            System.out.println(" ");
            System.out.println("Looking for things in category oven above 200");
            // change below to change category
            bst.printCategoryAbovePrice("Oven", 200.00f);

            System.out.println("Finished");
            System.out.println("---------------------------------------");

        }

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