## Overview

The Smart Cat Boarding Management System is a Java Swing application designed to help manage cat boarding information through a graphical user interface.

The project demonstrates object-oriented programming concepts including inheritance, interfaces, collections, arrays, recursion, exception handling, and file I/O.

# Features

- Add cats
- Remove cats
- Search cats
- Smart Fill automatic parser
- Automatic category classification
- Recursive search
- File save/load
- Automatic backup file
- Boarding calendar using 2D arrays
- GUI event handling

  # Technologies Used

- Java
- Java Swing
- ArrayList
- Multidimensional Arrays
- Recursion
- File I/O
- Exception Handling

---

# LO Coverage

## LO1
Object-Oriented Design

## LO2
Arrays and multidimensional arrays

## LO3
Classes and aggregation

## LO4
Inheritance, interfaces, and polymorphism

## LO5
Generic collections using ArrayList

## LO6
GUI and event-driven programming

## LO7
Exception handling

## LO8
Text file I/O

## LO9
Recursion

Note: I worked on this project locally in Eclipse, but I did not correctly submit weekly Canvas snapshots or push weekly GitHub commits. This section summarizes the development stages of my project.
# Weekly Development Progress

## Week 1 — Proposal and Design

This week, I planned the main idea for my project. I chose a cat boarding management system because it connects to a real situation and gives me a clear reason to build the program.

I created the first version of the GUI mock-up, CRC cards, and UML diagram. I also planned the main classes, including Cat, Owner, SpecialCat, BoardingManager, CatBoardingGUI, and Feedable.


---

## Week 2 — Basic Model Classes

This week, I started writing the model classes. I worked on Owner, Cat, and SpecialCat first because these classes store the main information for the system.

I also tested constructors, getter methods, and toString methods. At this stage, the program was still simple, but it helped me make sure the basic object relationships worked.


---

## Week 3 — Manager Class and File Planning

This week, I worked on the BoardingManager class. I used an ArrayList to store multiple Cat objects and added methods to add, remove, and display cats.

I also started planning file saving and loading. I had to think about where file I/O should belong in the design, and I decided to keep it in BoardingManager because that class manages the cat data.


---

## Week 4 — Testing and Debugging

This week, I focused on testing the model classes and fixing logic issues. I checked whether cats could be added and removed correctly, and I also reviewed the relationships between the classes.

I made small design changes after testing, especially around how the manager class should organize the cat list.


---

## Week 5 — GUI Layout

This week, I started building the GUI with Java Swing. I added labels, text fields, buttons, and an output area.

At this point, the GUI was mostly about layout. The buttons did not do everything yet, but the visual structure helped me see how the user would interact with the program.


---

## Week 6 — Functional GUI

This week, I connected the GUI buttons to the program logic. The Add Cat, Remove Cat, Search Cat, Save File, and Load File buttons became functional.

I also added Smart Fill, which uses keyword matching and regular expressions to fill in cat information from pasted text. This was one of the harder parts because the text input could vary.


---

## Week 7 — Advanced Features and Final Testing

This week, I added the final advanced features. I added recursive search for cats by name and a two-dimensional array to represent a simple boarding calendar.

I also added automatic backup when saving files and continued testing the GUI. Most of the work this week was debugging small problems and making the project more stable.

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
