/**@author RyanBulcraig*/
public class Appliance {

    private final String category;
    private final float price;
    private final String name;


    /** the constructor method for this class.
     * Order is 1. Category, 2. Price, 3. Name
     */
    public Appliance(String category, float price, String name) {
        this.category = category;
        this.price = price;
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public float getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    /** Compares 2 appliance objects. Starts lexicographically, then moves to price (cheap to expensive), finishes with name lexicographically.
     * @param other The other appliance to be checked against this one
     * @return negative means less, 0 means same, positive means greater than*/
    public int compareTo(Appliance other) {
        // compare categories
        int categoryCompare = getCategory().compareToIgnoreCase(other.getCategory());

        if (categoryCompare == 0) {
            if (getPrice() == other.getPrice()) {
                return getName().compareTo(other.getName());
            }
            else{
                return getPrice() < other.getPrice() ? -1 : 1;
            }
        }
        return categoryCompare;
    }

    public String toString(){
        return "%-20s | %-40s | %s".formatted(getCategory(), getName(), getPrice());
    }

}
