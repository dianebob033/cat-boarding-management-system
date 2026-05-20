# Cat Boarding Management System

## Overview

This is my CISC 191 final project. I made a Java Swing program for managing cat boarding information. I chose this topic because it connects to my real interest in cat boarding, so it was easier for me to think about useful features.

The program can add cats, remove cats, search for cats, save and load data from a text file, and display results in a GUI. Later in the project, I also added Smart Fill, automatic category classification, a simple boarding calendar, backup saving, and recursive search.

## Features

- Add, remove, and search cats
- Store owner and care information
- Smart Fill from pasted text
- Automatic category classification
- File save and load
- Backup file saving
- Simple boarding calendar using a 2D array
- Recursive search by cat name
- Java Swing GUI with buttons, text fields, and output area

## Technologies and Concepts Used

- Java
- Java Swing
- Object-oriented design
- ArrayList
- Two-dimensional array
- Recursion
- Exception handling
- Text file I/O

## LO Coverage

### LO1 - Object-Oriented Design

I separated the project into different classes with different jobs. For example, `Cat` stores cat information, `Owner` stores owner contact information, `BoardingManager` manages the data, `CatBoardingGUI` handles the GUI, and `SmartCatParser` handles the Smart Fill text parsing.

### LO2 - Arrays and Multidimensional Arrays

I used a two-dimensional array in `BoardingManager` as a simple boarding calendar.

Examples:
- `private String[][] boardingCalendar;`
- `assignCatToCalendar(String catName, int day, int room)`

### LO3 - Classes and Aggregation

The project uses several classes and object relationships. A `Cat` has an `Owner`, so this shows aggregation.

Example:
- `private Owner owner;`

### LO4 - Inheritance, Interfaces, and Polymorphism

`SpecialCat` extends `Cat`, and `Cat` implements the `Feedable` interface. `SpecialCat` overrides methods such as `getCategory()`, `feed()`, and `getFeedMessage()`.

This also allows a `SpecialCat` object to be stored as a `Cat` object in an `ArrayList<Cat>`.

### LO5 - Generic Collections

`BoardingManager` uses an `ArrayList<Cat>` to store all cats in the system.

Example:
- `private ArrayList<Cat> catList;`

### LO6 - GUI and Event-Driven Programming

The GUI uses Java Swing components such as `JFrame`, `JPanel`, `JLabel`, `JTextField`, `JTextArea`, and `JButton`.

The buttons use `ActionListener` objects to respond to user actions. For example, the Add Cat button calls `handleAddCat()`, and the Search Cat button calls `handleSearchCat()`.

### LO7 - Exception Handling

The program uses exception handling for invalid input and file problems. For example, `handleAddCat()` catches invalid date or time input, and `BoardingManager` catches `IOException` when saving or loading files.

### LO8 - Text File I/O

`BoardingManager` saves and loads cat information using text files.

Examples:
- `FileWriter` in `saveToFile()`
- `Scanner` in `loadFromFile()`
- backup file in `saveBackupFile()`

### LO9 - Recursion

`BoardingManager` uses recursion to search for a cat by name.

Examples:
- `recursiveSearchByName(String name)`
- helper method `recursiveSearchByName(String name, int index)`

## Weekly Development Progress

### Week 1 - Proposal and Design

I planned the main idea for the project and chose a cat boarding management system. I created the first GUI mock-up, CRC cards, and UML diagram. I also planned the main classes: `Cat`, `Owner`, `SpecialCat`, `BoardingManager`, `CatBoardingGUI`, and `Feedable`.

### Week 2 - Basic Model Classes

I started writing the model classes. I worked on `Owner`, `Cat`, and `SpecialCat` first because they store the main information for the program. I also tested constructors, getters, and `toString()` methods.

### Week 3 - Manager Class and File Planning

I worked on the `BoardingManager` class and used an `ArrayList` to store multiple cat objects. I added methods to add, remove, and display cats. I also started planning where file saving and loading should go.

### Week 4 - Testing and Debugging

I focused on testing the model classes and fixing logic problems. I checked whether cats could be added and removed correctly, and I reviewed the relationship between the classes.

### Week 5 - GUI Layout

I started building the Java Swing GUI. I added labels, text fields, buttons, and an output area. At this point, the GUI was mostly layout, but it helped me see how the user would interact with the program.

### Week 6 - Functional GUI

I connected the GUI buttons to the program logic. Add Cat, Remove Cat, Search Cat, Save File, and Load File became functional. I also added Smart Fill, which uses keyword matching and regular expressions to fill in cat information from pasted text.

### Week 7 - Advanced Features and Final Testing

I added recursive search and a two-dimensional array for a simple boarding calendar. I also added backup saving and continued testing the GUI. Most of this week was debugging small problems and making the project more stable.

## References

- Morelli, R., & Walde, R. (2016). *Java, Java, Java: Object-Oriented Problem Solving*.  
  https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving

- Oracle Java Documentation.  
  https://docs.oracle.com/javase/8/docs/api/

## GitHub Repository

https://github.com/dianebob033/cat-boarding-management-system

------------------------------------------------
  # cat-boarding-management-system
Java GUI project for managing cat boarding
------------------------------------------------
          Cat Boarding Management System
------------------------------------------------

Cat Name:        [____________]

Age:             [____________]

Owner Name:      [____________]

Phone Number:    [____________]

Care Notes:
[______________________________]

Medical Notes:
[______________________________]

------------------------------------------------

[ Add Cat ]
[ Remove Cat ]
[ Save File ]
[ Load File ]

------------------------------------------------

Output Area:

Mimi | Age: 3 | Owner: Lucy ...

------------------------------------------------
