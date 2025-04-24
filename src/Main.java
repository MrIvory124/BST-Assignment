public class Main {
    public static void main(String[] args) {
        ApplianceBST bst = new ApplianceBST();

        Appliance a = new Appliance("Aircon", 1030.20f, "Heat pump");
        Appliance b = new Appliance("Fridge", 1000.20f, "Stand Freezer");
        Appliance c = new Appliance("Fridge", 10430.20f, "Stander Freezest");
        Appliance d = new Appliance("Oven", 100.20f, "Candle");
        Appliance e = new Appliance("Oven", 10200.20f, "Gas Stove");

        bst.insert(b);
        bst.insert(a);
        bst.insert(c);
        bst.insert(e);
        bst.insert(d);

        bst.printNodeChildren();

        System.out.println("Has element " + d.getName() + ": "+ bst.search(d));
        System.out.println("Has element " + a.getName() + ": "+ bst.search(a));

        System.out.println("Sorted elements");
        bst.print();

        System.out.println("Height: " + bst.getHeight());

        System.out.println("Min: " + bst.getMinimum());
        System.out.println("Max: " + bst.getMaximum());

        System.out.println(" ");
        System.out.println("Looking for things in category oven");
        bst.printCategory("Fridge");

        System.out.println(" ");
        System.out.println("Looking for things in category oven between 0 and 200 in price");
        bst.printCategoryWithPriceRange("Oven", 0.00f, 200.00f);

        System.out.println(" ");
        System.out.println("Looking for things in category oven under 200");
        bst.printCategoryBelowPrice("Oven", 200.00f);

        System.out.println(" ");
        System.out.println("Looking for things in category oven above 200");
        bst.printCategoryAbovePrice("Oven", 200.00f);

    }
}