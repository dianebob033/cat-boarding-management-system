# Cat Boarding Management System - Week 8 Update

Lead Author: Jiaqi Zhang

## Week 8 Project Update

This week I worked on making my Cat Boarding Management System meet the Senior Developer level requirements. I did not only add more buttons. I tried to connect each course learning outcome to a real part of the program.

The biggest improvement is in `BoardingManager`. Before, the project mainly used an `ArrayList` and file saving. Now the manager uses several data structures for different reasons:

- `ArrayList<Cat>` stores the main cat records.
- `String[][] boardingCalendar` represents days and boarding rooms.
- `HashMap<String, Cat>` helps prevent duplicate cat names and makes exact search faster.
- `TreeMap<LocalDate, ArrayList<Cat>>` creates a schedule report sorted by boarding start date.
- `Queue<String>` represents a waiting list.
- `Stack<Cat>` supports undoing the most recent add.
- `LinkedList<String>` stores the activity log.

I also improved the GUI by adding a `Schedule` button and an `Undo Add` button. The GUI still does not directly store cat data. It sends user actions to `BoardingManager`, which keeps the program more object-oriented and easier to maintain.

## Screenshots To Include

### Screenshot 1: Main GUI

Add a screenshot showing the main Cat Boarding GUI with the cat input fields, Smart Import box, buttons, and output area.

Suggested caption:
This screenshot shows the Swing GUI. The user can enter cat, owner, date, time, care note, and medical note information. The GUI uses event-driven programming because each button has an action listener.

### Screenshot 2: Added Regular Cat And Special Cat

Add a screenshot after adding one regular cat and one special-care cat.

Suggested caption:
This screenshot shows polymorphism. Both cats are stored as `Cat` objects, but the special-care cat uses the overridden methods from `SpecialCat`.

### Screenshot 3: Schedule Report

Add a screenshot after clicking the `Schedule` button.

Suggested caption:
This screenshot shows the schedule report. The program uses a `TreeMap` so the cats are displayed in sorted date order.

### Screenshot 4: Save And Load

Add a screenshot showing that saved data can be loaded back into the program.

Suggested caption:
This screenshot shows file I/O. The program writes cat data to a text file and loads it back later, so the data is persistent.

### Screenshot 5: Error Handling

Add a screenshot with a wrong date, wrong phone number, or missing required field.

Suggested caption:
This screenshot shows graceful error handling. The program gives the user a clear message instead of crashing.

## Learning Outcomes Evidence

### LO1: Object-Oriented Design

My project uses OOP design in a larger scenario because the program is divided into classes with different responsibilities. `Cat` stores cat boarding information. `Owner` stores owner contact information. `SpecialCat` extends `Cat` for cats with medical needs. `BoardingManager` manages the data and business logic. `CatBoardingGUI` displays the interface and sends requests to the manager.

This design shows has-a relationships:

- `Cat has-a Owner`
- `CatBoardingGUI has-a BoardingManager`
- `BoardingManager has-many Cat objects`

This makes the program easier to understand because the GUI does not directly control the cat list.

### LO2: Arrays

The project uses a two-dimensional array:

```java
private String[][] boardingCalendar;
```

I use this array to represent boarding days and rooms. The rows represent days, and the columns represent rooms. This is a meaningful array because a boarding business needs to know which rooms are being used.

### LO3: Classes And Aggregation

The project uses multiple classes in a larger scenario. `Owner` is its own class instead of only storing owner name and phone number as separate strings in `Cat`. This is aggregation because a cat has an owner, but the owner is still a separate object.

This is better design because owner information can be changed or expanded later without making the `Cat` class too messy.

### LO4: Inheritance, Interface, And Polymorphism

The project uses inheritance because:

```java
public class SpecialCat extends Cat
```

The project uses an interface because:

```java
public class Cat implements Feedable
```

The project uses polymorphism because regular cats and special cats both respond to `getFeedMessage()`, but `SpecialCat` gives a different message. The manager can call the same method without needing to know every detail of the object type.

### LO5: Generic Collections And Data Structures

I selected different data structures for different jobs:

- `ArrayList` for the main list because cats are added and displayed often.
- `HashMap` for fast lookup and duplicate prevention.
- `TreeMap` for sorted schedule reports by date.
- `Queue` for a first-in, first-out waiting list.
- `Stack` for undoing the most recent add.
- `LinkedList` for the activity log.

This shows senior-level thinking because the data structure is chosen based on the job, not only because it is familiar.

### LO6: GUI And Event-Driven Programming

The project uses Swing for the GUI. Buttons such as `Add Cat`, `Search Cat`, `Save File`, `Load File`, `Schedule`, and `Undo Add` use action listeners. This makes the program interactive because the user controls the flow by clicking buttons.

The GUI also has spacing, labels, default example values, Smart Import, and an output area. I tried to make it easier for a user who is not a programmer.

### LO7: Exception Handling

The program handles errors gracefully. For example:

- Wrong date or time format is handled with `DateTimeParseException`.
- File problems are handled with `IOException`.
- Bad user input uses clear messages with `IllegalArgumentException`.

Instead of crashing, the GUI shows a helpful message in the output area.

### LO8: Text File I/O

The project saves and loads cat data using text files. `BoardingManager` writes to `cats.txt` and also creates `cats_backup.txt`. It uses try-with-resources with `FileWriter` and `Scanner`, so the file resources close correctly.

When loading, the program skips invalid lines. This is important because one bad line should not destroy the whole loading process.

### Recursion And Iteration

The project has both recursion and iteration. I use iteration for most list work, like showing all cats, because loops are simple and readable for processing every item.

I also include a recursive search method:

```java
public Cat recursiveSearchByName(String name)
```

This method searches one cat, then calls itself to search the next cat. Recursion is appropriate here as a learning example because the problem can be divided into a smaller version of the same problem. In a larger real application, I would usually use iteration or a `HashMap` for performance and readability.

## Week 8 Final Project Reflection

This week was the last week of my Cat Boarding Management System project. I mainly worked on checking the whole project and thinking about what I learned from it.

At first, my project was more simple. It could store cat information and show it in the GUI. Later, I added more parts, such as file saving and loading, exception handling, inheritance, polymorphism, recursion, and different data structures. This helped me understand that a bigger program is not only about adding more code. The code also needs to be organized clearly.

One thing I learned is the importance of separating responsibilities. In my project, CatBoardingGUI is mainly for the screen and buttons. BoardingManager is mainly for the data and logic. This makes the project easier to understand. If I need to change how cats are stored, I do not have to change every part of the GUI.

The hardest part was making the course topics fit naturally into one project. I did not want to only add code for the requirement. I tried to connect each idea to a real cat boarding situation. For example, TreeMap makes sense for a schedule, Queue makes sense for a waiting list, Stack makes sense for undo, and HashMap helps with checking duplicate cat names.

I also learned that debugging takes a lot of time. I had problems with file loading, old saved file formats, invalid dates, and GUI layout. Fixing these problems helped me understand why exception handling and clear method names are important.

If I continue this project, I want to improve the room calendar. Right now, the 2D array shows the idea of days and rooms, but it is still simple. In the future, I would like to assign cats to real rooms for every day of their stay. I would also like to add a table view so the cat information is easier to read.

Overall, this project helped me connect many topics from CISC 191 into one practical program. I feel I understand object-oriented design better now because I used it in a real project, not only in small exercises.