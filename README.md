# Cat Boarding Management System

## Overview

This is my CISC 191 final project. I made a Java Swing program for managing cat boarding information. I chose this topic because it connects to my real interest in cat boarding, so it was easier for me to think about useful features.

The program can add cats, remove cats, search for cats, save and load data from a text file, and display results in a GUI. Later in the project, I also added Smart Fill, automatic category classification, a simple boarding calendar, backup saving, recursive search, schedule reports, and undo support.

---

## Features

- Add, remove, and search cats
- Store owner and care information
- Smart Fill from pasted text
- Automatic category classification
- File save and load
- Backup file saving
- Simple boarding calendar using a 2D array
- Recursive search by cat name
- Schedule report sorted by date
- Waiting list support
- Undo Add feature
- Activity log
- Java Swing GUI with buttons, text fields, and output area

---

## Technologies and Concepts Used

- Java
- Java Swing
- Object-oriented design
- ArrayList
- HashMap
- TreeMap
- Queue
- Stack
- LinkedList
- Two-dimensional array
- Recursion
- Exception handling
- Text file I/O

---

## LO Coverage

### LO1 - Object-Oriented Design

I separated the project into different classes with different jobs. For example, `Cat` stores cat information, `Owner` stores owner contact information, `BoardingManager` manages the data, `CatBoardingGUI` handles the GUI, and `SmartCatParser` handles the Smart Fill text parsing.

This project also shows has-a relationships:
- `Cat` has-an `Owner`
- `CatBoardingGUI` has-a `BoardingManager`
- `BoardingManager` has-many `Cat` objects

I separated the GUI and data logic because it makes the project easier to organize and maintain.

---

### LO2 - Arrays and Multidimensional Arrays

I used a two-dimensional array in `BoardingManager` as a simple boarding calendar.

Examples:
- `private String[][] boardingCalendar;`
- `assignCatToCalendar(String catName, int day, int room)`

I used a 2D array because a boarding business has multiple days and multiple rooms.

---

### LO3 - Classes and Aggregation

The project uses several classes and object relationships. A `Cat` has an `Owner`, so this shows aggregation.

Example:
- `private Owner owner;`

I used a separate `Owner` class because owner information may grow later and should not all stay inside the `Cat` class.

---

### LO4 - Inheritance, Interfaces, and Polymorphism

`SpecialCat` extends `Cat`, and `Cat` implements the `Feedable` interface.

Examples:
- `public class SpecialCat extends Cat`
- `public class Cat implements Feedable`

`SpecialCat` overrides methods such as:
- `getCategory()`
- `feed()`
- `getFeedMessage()`

This also allows a `SpecialCat` object to be stored as a `Cat` object in an `ArrayList<Cat>`.

This demonstrates polymorphism because both regular cats and special-care cats respond differently to the same method call.

---

### LO5 - Generic Collections And Data Structures

The project uses several collections for different jobs.

Examples:
- `ArrayList<Cat>` stores the main cat list
- `HashMap<String, Cat>` helps prevent duplicate cat names and improves searching
- `TreeMap<LocalDate, ArrayList<Cat>>` sorts schedules by date
- `Queue<String>` represents a waiting list
- `Stack<Cat>` supports undoing the most recent add
- `LinkedList<String>` stores the activity log

I selected different data structures based on the problem each feature needed to solve.

---

### LO6 - GUI and Event-Driven Programming

The GUI uses Java Swing components such as:
- `JFrame`
- `JPanel`
- `JLabel`
- `JTextField`
- `JTextArea`
- `JButton`

The buttons use `ActionListener` objects to respond to user actions.

Examples:
- Add Cat
- Search Cat
- Save File
- Load File
- Schedule
- Undo Add

I also added Smart Fill so the user can paste text instead of typing every field manually.

---

### LO7 - Exception Handling

The program uses exception handling for invalid input and file problems.

Examples:
- `DateTimeParseException` for invalid dates or times
- `IOException` for file problems
- `IllegalArgumentException` for invalid user input

The GUI shows user-friendly messages instead of crashing.

---

### LO8 - Text File I/O

`BoardingManager` saves and loads cat information using text files.

Examples:
- `FileWriter` in `saveToFile()`
- `Scanner` in `loadFromFile()`
- backup file in `saveBackupFile()`

The program uses try-with-resources so file resources close safely.

The loading process also skips invalid lines so one bad line does not crash the whole loading process.

---

### LO9 - Recursion

`BoardingManager` uses recursion to search for a cat by name.

Examples:
- `recursiveSearchByName(String name)`
- helper method `recursiveSearchByName(String name, int index)`

The recursive method checks one cat and then calls itself again for the next index.

I used loops for most list work because they are easier to read, but I included recursion to demonstrate recursive problem solving.

---

## Weekly Development Progress

### Week 1 - Proposal and Design

I planned the main idea for the project and chose a cat boarding management system. I created the first GUI mock-up, CRC cards, and UML diagram. I also planned the main classes: `Cat`, `Owner`, `SpecialCat`, `BoardingManager`, `CatBoardingGUI`, and `Feedable`.

---

### Week 2 - Basic Model Classes

I started writing the model classes. I worked on `Owner`, `Cat`, and `SpecialCat` first because they store the main information for the program. I also tested constructors, getters, and `toString()` methods.

---

### Week 3 - Manager Class and File Planning

I worked on the `BoardingManager` class and used an `ArrayList` to store multiple cat objects. I added methods to add, remove, and display cats. I also started planning where file saving and loading should go.

---

### Week 4 - Testing and Debugging

I focused on testing the model classes and fixing logic problems. I checked whether cats could be added and removed correctly, and I reviewed the relationship between the classes.

---

### Week 5 - GUI Layout

I started building the Java Swing GUI. I added labels, text fields, buttons, and an output area. At this point, the GUI was mostly layout, but it helped me see how the user would interact with the program.

---

### Week 6 - Functional GUI

I connected the GUI buttons to the program logic. Add Cat, Remove Cat, Search Cat, Save File, and Load File became functional. I also added Smart Fill, which uses keyword matching and regular expressions to fill in cat information from pasted text.

---

### Week 7 - Advanced Features and Final Testing

I added recursive search and a two-dimensional array for a simple boarding calendar. I also added backup saving, schedule reports, undo support, and continued testing the GUI. Most of this week was debugging small problems and making the project more stable.

---

### Week 8 - Final Reflection and Project Review

During the final week, I focused on reviewing the whole project and improving the final details. I checked the GUI, comments, file loading, and exception handling to make the project more stable and easier to understand.

One important thing I learned is that senior-level programming is not only about adding more code. It is also about organizing the code clearly and choosing the right design for the problem. For example, I separated the GUI and data logic by using `BoardingManager`. This made the program easier to maintain and test.

I also learned more about selecting data structures for different jobs. I used `HashMap` for faster searching, `TreeMap` for sorted schedules, `Queue` for the waiting list, and `Stack` for undoing the most recent add.

The hardest part was connecting many learning outcomes into one realistic project without making the code feel forced. I tried to make every feature fit the cat boarding scenario naturally.

If I continue this project later, I would like to improve the room calendar system and make the GUI more advanced with a table view for cats and schedules.

Overall, this project helped me connect object-oriented design, GUI programming, data structures, recursion, exception handling, and file I/O into one practical application.

---

## References

- Morelli, R., & Walde, R. (2016). *Java, Java, Java: Object-Oriented Problem Solving*  
https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving

- Oracle JFrame Documentation  
https://docs.oracle.com/javase/8/docs/api/javax/swing/JFrame.html

- Oracle Scanner Documentation  
https://docs.oracle.com/javase/8/docs/api/java/util/Scanner.html

- Oracle LocalDate Documentation  
https://docs.oracle.com/javase/8/docs/api/java/time/LocalDate.html

- Oracle Java Inheritance Tutorial  
https://docs.oracle.com/javase/tutorial/java/IandI/subclasses.html

---

## GitHub Repository

https://github.com/dianebob033/cat-boarding-management-system
