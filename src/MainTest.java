import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Lead Author(s): Jiaqi Zhang
 *
 * References:
 * Morelli, R., & Walde, R. (2016). Java, Java, Java:
 * Object-Oriented Problem Solving.
 * https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
 *
 * Oracle LocalDate Documentation:
 * https://docs.oracle.com/javase/8/docs/api/java/time/LocalDate.html
 *
 * Responsibilities of class:
 * MainTest checks the model classes before the GUI is opened.
 */
public class MainTest
{
	/**
	 * Runs simple tests for the project model classes.
	 *
	 * @param args command-line arguments
	 */
	public static void main(String[] args)
	{
		Owner firstOwner = new Owner("Lucy", "1234567890");
		Owner secondOwner = new Owner("Kevin", "5552223333");

		Cat regularCat = new Cat("Mimi", LocalDate.parse("2022-05-01"),
				firstOwner, "Shy at first.", LocalDate.parse("2026-05-01"),
				LocalDate.parse("2026-05-07"), LocalTime.parse("10:00"),
				LocalTime.parse("17:00"));

		SpecialCat specialCat = new SpecialCat("Luna",
				LocalDate.parse("2021-03-02"), secondOwner,
				"Needs quiet space.", LocalDate.parse("2026-05-01"),
				LocalDate.parse("2026-05-20"), LocalTime.parse("09:00"),
				LocalTime.parse("18:00"), "Give medicine at night.");

		BoardingManager boardingManager = new BoardingManager();
		boardingManager.addCat(regularCat);
		boardingManager.addCat(specialCat);

		// This tests polymorphism because both objects are stored as Cat.
		System.out.println(boardingManager.getAllCatsAsString());
		System.out.println();
		System.out.println("Schedule report:");
		System.out.println(boardingManager.getScheduleReport());

		boardingManager.addToWaitingList("Nori");
		boardingManager.addToWaitingList("Bean");
		System.out.println();
		System.out.println(boardingManager.getWaitingListAsString());
		System.out.println(
				"Next waiting cat: " + boardingManager.getNextWaitingCat());

		try
		{
			boardingManager.saveToFile("cats.txt");
			System.out.println("Loaded from file:");
			System.out.println(boardingManager.loadFromFile("cats.txt"));
			System.out.println();
			System.out.println(boardingManager.getActivityLogAsString());
		}
		catch (IOException problem)
		{
			System.out.println("File test failed.");
		}
	}
}