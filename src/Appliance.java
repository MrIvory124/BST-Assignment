/**@author RyanBulcraig*/
public class Appliance {

    private String category;
    private float price;
    private String name;


    /**
     * @param category
     * @param price
     * @param name
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
        return "%-20s | %-20s | %s".formatted(getCategory(), getName(), getPrice());
    }

}
