public class ApplianceBST {

    private static class Node {
        Appliance value;
        Node left;
        Node right;

        private Node(Appliance value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    Node root;

    public void insert(Appliance a) {
        root = insertR(a, root);
    }

    private Node insertR(Appliance value, Node current) {
        if (current == null) {
            return new Node(value);
        }
        if (value.compareTo(current.value) < 0) {
            current.left = insertR(value, current.left);
        } else if (value.compareTo(current.value) > 0) {
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

        if (current.value.compareTo(a) < 0) {
            current.left = removeR(a, current.left);
        }
        else if (current.value.compareTo(a) > 0) {
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

            // Two children: replace with smallest value from right subtree
            Node smallest = getSuccessor(current);
            current.value = smallest.value;
            current.right = removeR(current.value, current.right);
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
        if (current.value.equals(a)) { return true; }
        // recursion cases
        if (current.value.compareTo(a) < 0) { return searchR(current.right, a); }
        if (current.value.compareTo(a) > 0) { return searchR(current.left, a); }
        // we found it
        return current.value.compareTo(a) == 0;
    }


    /**
     * @return Gives the node-based height of the bst
     */
    public int getHeight() {
        return getHeightR(root);
    }

    private int getHeightR(Node current) {
        if (current == null) { return -1; }
        return 1 + Math.max(getHeightR(current.left), getHeightR(current.right));
    }

    /**
     * @return Gives the smallest value stored in the bst
     */
    public Appliance getMinimum() {
        return getMinimumR(root);
    }

    //todo fix
    private Appliance getMinimumR(Node current) {
        if (current == null) { return null; }
        Appliance currentMin = current.value;
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
     * @return Gives the biggest value stored in the bst
     */
    public Appliance getMaximum() {
        return getMaximumR(root);
    }

    private Appliance getMaximumR(Node current) {
        if (current == null) { return null; }
        Appliance currentMax = current.value;
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
            System.out.println(current.value.toString());
            printInOrder(current.right);
        }

    }

    // Part 2
    public void printCategory(String c){
        System.out.println(c);
    }

    public void printCategoryWithPriceRange(String c, float min, float max){
        System.out.println(c);
    }

    public void printCategoryAbovePrice(String c, float min){
        System.out.println(c);
    }

    public void printCategoryBelowPrice(String c, float max){
        System.out.println(c);
    }

}

