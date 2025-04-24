public class Main {
    public static void main(String[] args) {
        ApplianceBST bst = new ApplianceBST();

        Appliance a = new Appliance("Aircon", 1030.20f, "Heat pump");
        Appliance b = new Appliance("Fridge", 1000.20f, "Stand Freezer");
        Appliance c = new Appliance("Fridge", 10430.20f, "Stander Freezest");
        Appliance d = new Appliance("Oven", 100.20f, "Candle");
        Appliance e = new Appliance("Oven", 10200.20f, "Gas Stove");

        bst.insert(a);
        bst.insert(b);
        bst.insert(c);
        bst.insert(d);
        bst.insert(e);

        System.out.println("Has element " + d.getName() + ": "+ bst.search(d));
        System.out.println("Has element " + a.getName() + ": "+ bst.search(a));

        System.out.println("Sorted elements");
        bst.print();

        System.out.println("Height: " + bst.getHeight());

        System.out.println("Min: " + bst.getMinimum());
        System.out.println("Max: " + bst.getMaximum());

        // test
    }
}