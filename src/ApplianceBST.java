public class ApplianceBST {

    /**
     * Nodes used for storing data in the BST
     */
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

    /**
     * Insert methods places appliances that are greater to the right
     */
    public void insert(Appliance a) {
        root = insertR(a, root);
    }

    /**
     * the insert recursive method
     */
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

    /**
     * for removing things from the bst\
     *
     * @param a The appliance we want to remove
     */
    public void remove(Appliance a) {
        root = removeR(a, root);
    }

    /**
     * the recursive part for remove
     */
    private Node removeR(Appliance a, Node current) {
        // cases for recursion
        if (current == null) {
            return null;
        }

        if (a.compareTo(current.appliance) < 0) {
            current.left = removeR(a, current.left);
        } else if (a.compareTo(current.appliance) > 0) {
            current.right = removeR(a, current.right);
        } else {
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

            // two children: replace with the smallest appliance from right subtree
            Node smallest = getSuccessor(current);
            current.appliance = smallest.appliance;
            current.right = removeR(current.appliance, current.right);
        }
        return current;
    }

    /**
     * figures out what node to take as the next successor for removing a root node
     */
    private Node getSuccessor(Node current) {
        current = current.right;
        while (current != null && current.left != null) {
            current = current.left;
        }
        return current;
    }

    /**
     * Recursively searches the tree.
     *
     * @param a The appliance you are looking for
     */
    public boolean search(Appliance a) {
        Node current = root;
        if (current == null) {
            return false;
        }
        return searchR(current, a);
    }

    /**
     * recursive method for searching
     */
    private boolean searchR(Node current, Appliance a) {
        // base cases
        if (current == null) {
            return false;
        }
        if (current.appliance.equals(a)) {
            return true;
        }
        // recursion cases
        if (current.appliance.compareTo(a) < 0) {
            return searchR(current.right, a);
        }
        if (current.appliance.compareTo(a) > 0) {
            return searchR(current.left, a);
        }
        // we found it
        return current.appliance.compareTo(a) == 0;
    }


    /**
     * @return Gives the node-based height of the bst
     */
    public int getHeight() {
        return getHeightR(root);
    }

    /**
     * recursive method for retrieving the height of a bst
     */
    private int getHeightR(Node current) {
        if (current == null) {
            return 0;
        }
        return 1 + Math.max(getHeightR(current.left), getHeightR(current.right));
    }

    /**
     * @return Gives the smallest appliance stored in the bst
     */
    public Appliance getMinimum() {
        return getMinimumR(root);
    }

    /**
     * Goes as far left as possible to get the minimum node
     */
    private Appliance getMinimumR(Node current) {
        if (current == null) {
            return null;
        }
        while (current.left != null) {
            current = current.left;
        }
        return current.appliance;
    }

    /**
     * @return Gives the biggest appliance stored in the bst
     */
    public Appliance getMaximum() {
        return getMaximumR(root);
    }

    /**
     * Recursively goes as far right to find biggest
     */
    private Appliance getMaximumR(Node current) {
        if (current == null) {
            return null;
        }
        while (current.right != null) {
            current = current.right;
        }
        return current.appliance;
    }

    /**
     * This prints the BST currently using InOrder traversal
     */
    public void print() {
        printInOrder(root);
    }

    /**
     * Recursively prints the tree
     */
    private void printInOrder(Node current) {
        if (current != null) {
            printInOrder(current.left);
            System.out.println(current.appliance.toString());
            printInOrder(current.right);
        }

    }

    /**
     * Prints based on category and no other information
     */
    public void printCategory(String c) {
        if (root == null) {
            return;
        }
        printCategoryR(root, c, Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY);
    }

    /**
     * Prints based on category as well as a range
     *
     * @param min The lower bound of the range you wish to define
     * @param max The upper bound of the range you wish to define
     */
    public void printCategoryWithPriceRange(String c, float min, float max) {
        if (root == null) {
            return;
        }
        printCategoryR(root, c, min, max);
    }

    /**
     * Prints based on category and a lower bound
     */
    public void printCategoryAbovePrice(String c, float min) {
        printCategoryR(root, c, min, Float.POSITIVE_INFINITY);
    }

    /**
     * Prints based on category and an upper bound
     */
    public void printCategoryBelowPrice(String c, float max) {
        printCategoryR(root, c, Float.NEGATIVE_INFINITY, max);
    }

    /**
     * Recursive method for all variations of printing with a category
     */
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

    /**
     * This was used to debug by showing what nodes connect to what in the BST
     */
    public void printNodeChildren() {
        printNodeChildrenR(root);
    }

    /**
     * The recursive method for printing into the console
     */
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

