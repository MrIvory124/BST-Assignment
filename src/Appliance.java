/**@author RyanBulcraig*/
public class Appliance {

    private String category;
    private float price;
    private String name;

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

    public int compareTo(Appliance other) {
        int categoryCompare = getCategory().compareTo(other.getCategory());
        if (categoryCompare == 0) {
            if (getPrice() == other.getPrice()) {
                return getName().compareTo(other.getName());
            }
            return getPrice() < other.getPrice() ? -1 : 1;
        }
        return categoryCompare;
    }

    public String toString(){
        return "%-20s | %-20s | %s".formatted(getCategory(), getName(), getPrice());
    }

}
