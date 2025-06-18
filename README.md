# Appliance Lookup System

This project implements a command-line application in Java for managing and searching household appliances. It uses a Binary Search Tree (BST) for fast lookups based on unique appliance IDs.
This is based on an assignment that I did in university and I am rather proud of it. 

## Features

* Insert appliances into a BST for organized storage.
* Search for appliances by ID.
* View appliance details like type, brand, model, power rating, and estimated usage.
* Interactive command-line interface.

## File Overview

* **Main.java** - Entry point. Handles user interaction. Was only used for testing
* **Appliance.java** - Defines the appliance object structure.
* **ApplianceBST.java** - Manages appliance data using a binary search tree.
* **ApplianceLookup.java** - Acts as the interface between the user input and BST logic, as per assignment is the main entry method.

## How to Run

1. Make sure you have Java installed.

2. Compile the project:

   ```
   javac *.java
   ```

3. Run the program:

   ```
   java Main
   ```

4. Follow the prompts to search for appliances.

## Notes

* Data is preloaded or created at runtime - there's no external database. All data must be in csv format, see example file included. 
* IDs must be unique for the BST to function properly.
* If an ID isn’t found, the user is notified.

## License

For educational use only.
