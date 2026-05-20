import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Lead Author(s): Jiaqi Zhang
 * 
 * References:
 * Morelli, R., & Walde, R. (2016). Java, Java, Java:
 * Object-Oriented Problem Solving.
 * Retrieved from:
 * https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
 * 
 * Oracle Java Documentation:
 * https://docs.oracle.com/javase/8/docs/api/
 * 
 * Responsibilities of class:
 * The MainTest class is used to test the model classes before building the GUI.
 */
public class MainTest
{
	public static void main(String[] args)
	{
		// testing my classes
		Owner owner1 = new Owner("Lucy", "123-456-7890");
		Owner owner2 = new Owner("Kevin", "555-222-3333");

		Cat cat1 = new Cat("Mimi", LocalDate.parse("2022-05-01"), owner1,
				"Shy at first.", LocalDate.parse("2026-05-01"),
				LocalDate.parse("2026-05-07"), LocalTime.parse("10:00"),
				LocalTime.parse("17:00"));

		// this one needs medicine
		SpecialCat cat2 = new SpecialCat("Luna", LocalDate.parse("2021-03-02"),
				owner2, "Needs quiet space.", LocalDate.parse("2026-05-01"),
				LocalDate.parse("2026-05-20"), LocalTime.parse("09:00"),
				LocalTime.parse("18:00"), "Give medicine at night.");

		BoardingManager bm = new BoardingManager();

		bm.addCat(cat1);
		bm.addCat(cat2);

		System.out.println(bm.getAllCatsAsString());

		bm.saveToFile("cats.txt");

		System.out.println("Loaded from file:");
		System.out.println(bm.loadFromFile("cats.txt"));
	}
}