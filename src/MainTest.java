/**
 * Lead Author(s): Jiaqi Zhang
 * 
 * Responsibilities of class:
 * The MainTest class is used to test the model classes before building the GUI.
 */
public class MainTest {
    public static void main(String[] args) {
        Owner owner1 = new Owner("Lucy", "123-456-7890");
        Owner owner2 = new Owner("Kevin", "555-222-3333");

        Cat cat1 = new Cat("Mimi", 3, owner1, "Shy at first.");

        SpecialCat cat2 = new SpecialCat(
                "Luna",
                5,
                owner2,
                "Needs quiet space.",
                "Give medicine at night."
        );

        BoardingManager manager = new BoardingManager();

        System.out.println("TEST 1: Add cats");
        manager.addCat(cat1);
        manager.addCat(cat2);
        System.out.println(manager.getAllCatsAsString());

        System.out.println("TEST 2: Remove an existing cat");
        manager.removeCat("Mimi");
        System.out.println(manager.getAllCatsAsString());

        System.out.println("TEST 3: Remove a cat that does not exist");
        manager.removeCat("UnknownCat");
        System.out.println(manager.getAllCatsAsString());

        System.out.println("TEST 4: Try to add a null cat");
        manager.addCat(null);
        System.out.println(manager.getAllCatsAsString());

        System.out.println("TEST 5: Save cat data to file");
        manager.saveToFile("cats.txt");

        System.out.println("TEST 6: Load cat data from file");
        System.out.println(manager.loadFromFile("cats.txt"));
    }
}