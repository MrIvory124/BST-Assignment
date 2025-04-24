public class ApplianceBST {

    private static class Node {
        Appliance appliance;
        Node left;
        Node right;

        private Node(Appliance appliance) {
            this.appliance = appliance;
            this.left = null;
            this.right = null;
        }
    }

    Node root;

    /**Insert methods places appliances that are greater to the right*/
    public void insert(Appliance a) {
        root = insertR(a, root);
    }

    private Node insertR(Appliance value, Node current) {
        if (current == null) {
            return new Node(value);
        }
        if (value.compareTo(current.appliance) < 0) {
            current.left = insertR(value, current.left);
        } else if (value.compareTo(current.appliance) > 0) {
            current.right = insertR(value, current.right);
        }
        return current;
    }

    public void remove(Appliance a) {
        root = removeR(a, root);
    }

    private Node removeR(Appliance a, Node current) {
        // cases for recursion
        if (current == null) { return null; }

        if (current.appliance.compareTo(a) < 0) {
            current.left = removeR(a, current.left);
        }
        else if (current.appliance.compareTo(a) > 0) {
            current.right = removeR(a, current.right);
        }
        else {
            if (current.left == null && current.right == null) {
                return null;
            }

            // one child
            if (current.left == null) {
                return current.right;
            }
            if (current.right == null) {
                return current.left;
            }

            // Two children: replace with smallest appliance from right subtree
            Node smallest = getSuccessor(current);
            current.appliance = smallest.appliance;
            current.right = removeR(current.appliance, current.right);
        }
    return current;
    }

    private Node getSuccessor(Node current){
        current = current.right;
        while (current != null && current.left != null) {
            current = current.left;
        }
        return current;
    }

    /**Recursively searches the tree.
     * @param a The appliance you are looking for*/
    public boolean search(Appliance a) {
        Node current = root;
        if (current == null) { return false; }
        return searchR(current, a);
    }

    private boolean searchR(Node current, Appliance a) {
        // base cases
        if (current == null) { return false; }
        if (current.appliance.equals(a)) { return true; }
        // recursion cases
        if (current.appliance.compareTo(a) < 0) { return searchR(current.right, a); }
        if (current.appliance.compareTo(a) > 0) { return searchR(current.left, a); }
        // we found it
        return current.appliance.compareTo(a) == 0;
    }


    /**
     * @return Gives the node-based height of the bst
     */
    public int getHeight() {
        return getHeightR(root);
    }

    private int getHeightR(Node current) {
        if (current == null) { return 0; }
        return 1 + Math.max(getHeightR(current.left), getHeightR(current.right));
    }

    /**
     * @return Gives the smallest appliance stored in the bst
     */
    public Appliance getMinimum() {
        return getMinimumR(root);
    }

    //todo fix
    private Appliance getMinimumR(Node current) {
        if (current == null) { return null; }
        Appliance currentMin = current.appliance;
        // eval left of tree
        Appliance leftMin = getMinimumR(current.left);
        if (leftMin != null && leftMin.compareTo(currentMin) < 0) {
            currentMin = leftMin;
        }
        // eval right of tree
        Appliance rightMin = getMinimumR(current.right);
        if (rightMin != null && rightMin.compareTo(currentMin) < 0) {
            currentMin = rightMin;
        }
        return currentMin;
    }

    /**
     * @return Gives the biggest appliance stored in the bst
     */
    public Appliance getMaximum() {
        return getMaximumR(root);
    }

    private Appliance getMaximumR(Node current) {
        if (current == null) { return null; }
        Appliance currentMax = current.appliance;
        // eval left of tree
        Appliance leftMax = getMaximumR(current.left);
        if (leftMax != null && leftMax.compareTo(currentMax) > 0) {
            currentMax = leftMax;
        }
        // eval right of tree
        Appliance rightMax = getMaximumR(current.right);
        if (rightMax != null && rightMax.compareTo(currentMax) > 0) {
            currentMax = rightMax;
        }
        return currentMax;
    }

    /**This prints the BST currently using InOrder traversal*/
    public void print() {
        System.out.println("Printing in order");
        printInOrder(root);
    }

    private void printInOrder(Node current){
        if (current != null) {
            printInOrder(current.left);
            System.out.println(current.appliance.toString());
            printInOrder(current.right);
        }

    }

    // Part 2 TODO: look at a better way of comparing than creating a new obj
    public void printCategory(String c) {
        if (root == null) { return; }
        printCategoryR(root, c, Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY);
    }

    public void printCategoryWithPriceRange(String c, float min, float max){
        if (root == null) { return; }
        printCategoryR(root, c, min, max);
    }

    private void printCategoryR(Node current, String c, float lower, float upper) {
        if (current == null) {
            return;
        }

        int desired = current.appliance.getCategory().compareToIgnoreCase(c);

        if (desired < 0) {
            printCategoryR(current.right, c, lower, upper);
        } else if (desired > 0) {
            printCategoryR(current.left, c, lower, upper);
        } else {
            if (current.appliance.getPrice() > lower && current.appliance.getPrice() < upper) {
                System.out.println(current.appliance.toString());
            }
            printCategoryR(current.right, c, lower, upper);
            printCategoryR(current.left, c, lower, upper);
        }
    }

    public void printCategoryAbovePrice(String c, float min){
        printCategoryR(root, c, min, Float.POSITIVE_INFINITY);
    }

    public void printCategoryBelowPrice(String c, float max){
        printCategoryR(root, c, Float.NEGATIVE_INFINITY, max);
    }

    /**remove this before sending in*/
    public void printNodeChildren() {
        printNodeChildrenR(root);
    }

    private void printNodeChildrenR(Node current) {
        if (current == null) {
            return;
        }

        System.out.println(current.appliance.getName() + ":");
        System.out.println("  Left -> " + (current.left != null ? current.left.appliance.getName() : "null"));
        System.out.println("  Right -> " + (current.right != null ? current.right.appliance.getName() : "null"));
        System.out.println();  // blank line for spacing

        // Recursively check children
        printNodeChildrenR(current.left);
        printNodeChildrenR(current.right);
    }



}

