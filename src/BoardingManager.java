import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

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
 * The BoardingManager class manages all cats in the boarding system.
 */
public class BoardingManager
{
	private ArrayList<Cat> catList;

	// The calendar uses rows for days and columns for boarding rooms.
	private String[][] boardingCalendar;

	/**
	 * Creates a BoardingManager object with an empty cat list.
	 */
	public BoardingManager()
	{
		catList = new ArrayList<Cat>();

		// 30 days, 6 rooms - TA said we need to show 2D array usage
		boardingCalendar = new String[30][6];
	}

	/**
	 * Adds a cat to the boarding system.
	 *
	 * @param cat the Cat object to add
	 */
	public void addCat(Cat cat)
	{
		if (cat == null)
		{
			System.out.println("Error: Cannot add a null cat.");
			return;
		}

		catList.add(cat);

		// put cat into calendar - using day 0 for simplicity because
		// actual date mapping would need more work. TA said it's fine
		// as long as we show we know how to use 2D arrays
		int room = catList.size() % 5;
		if (room >= 0 && room < boardingCalendar[0].length)
		{
			boardingCalendar[0][room] = cat.getName();
		}

		System.out.println(cat.getName() + " was added successfully.");
	}

	/**
	 * Removes a cat by name.
	 *
	 * @param name the name of the cat
	 * @return true if removed successfully, false otherwise
	 */
	public boolean removeCat(String name)
	{
		// name can't be null or empty, otherwise nothing to remove
		if (name == null || name.isEmpty())
		{
			System.out.println("Error: Invalid cat name.");
			return false;
		}

		for (int i = 0; i < catList.size(); i++)
		{
			if (catList.get(i).getName().equalsIgnoreCase(name))
			{
				catList.remove(i);
				System.out.println(name + " was removed successfully.");
				return true;
			}
		}

		System.out.println("Cat not found.");
		return false;
	}

	/**
	 * Recursion required by LO9 - using it for search even though
	 * loop would be simpler. but requirement is requirement
	 *
	 * @param name the cat name
	 * @return the cat if found, otherwise null
	 */
	public Cat recursiveSearchByName(String name)
	{
		return recursiveSearchByName(name, 0);
	}

	/**
	 * Helper recursive method.
	 *
	 * @param name  cat name
	 * @param index current index
	 * @return matching cat or null
	 */
	private Cat recursiveSearchByName(String name, int index)
	{
		// base case: reached end of list, not found
		if (index >= catList.size())
		{
			return null;
		}

		// base case: found
		if (catList.get(index).getName().equalsIgnoreCase(name))
		{
			return catList.get(index);
		}

		// recursive case: keep looking
		return recursiveSearchByName(name, index + 1);
	}

	/**
	 * Gets all cats in the system.
	 *
	 * @return the ArrayList of cats
	 */
	public ArrayList<Cat> getAllCats()
	{
		return catList;
	}

	/**
	 * Searches for a cat by name.
	 * 根据名字查询猫。
	 *
	 * @param name the cat name to search for
	 * @return cat information if found, or a not found message
	 */
	public String searchCatByName(String name)
	{
		if (name == null || name.isEmpty())
		{
			return "Error: Please enter a cat name to search.";
		}

		for (Cat cat : catList)
		{
			if (cat.getName().equalsIgnoreCase(name))
			{
				return cat.toString() + "\nFeeding: " + cat.getFeedMessage();
			}
		}

		return "Cat not found.";
	}

	/**
	 * Gets cats by category. (long stay vs regular)
	 * 根据自动分类查询猫。
	 *
	 * @param category the category keyword
	 * @return cats that match the category
	 */
	public String searchByCategory(String category)
	{
		String result = "";

		for (Cat cat : catList)
		{
			if (cat.getCategory().equalsIgnoreCase(category))
			{
				result += cat.toString() + "\n";
				result += "Feeding: " + cat.getFeedMessage() + "\n\n";
			}
		}

		if (result.isEmpty())
		{
			return "No cats found in this category.";
		}

		return result;
	}

	/**
	 * Creates a String containing all cat information.
	 *
	 * @return all cats as a formatted String
	 */
	public String getAllCatsAsString()
	{
		if (catList.isEmpty())
		{
			return "No cats are currently in the system.";
		}

		String result = "";

		for (Cat cat : catList)
		{
			result += cat.toString() + "\n";
			result += "Feeding: " + cat.getFeedMessage() + "\n\n";
		}

		return result;
	}

	/**
	 * Saves all cat information to a text file.
	 *
	 * @param fileName the name of the file to save to
	 */
	public void saveToFile(String fileName)
	{
		try
		{
			FileWriter writer = new FileWriter(fileName);

			for (Cat cat : catList)
			{
				writer.write(cat.toString() + "\n");
				writer.write("Feeding: " + cat.getFeedMessage() + "\n\n");
			}

			writer.close();

			// Create a backup every time the main file is saved, so the user
			// has another copy if the main file is changed or lost.
			saveBackupFile();

			System.out.println("Cat data saved successfully.");
		}
		catch (IOException e)
		{
			// File writing can fail if the path is unavailable or permission is
			// denied.
			System.out.println("Error: Could not save cat data to the file.");
		}
	}

	/**
	 * Saves a backup file automatically.
	 */
	private void saveBackupFile()
	{
		try
		{
			FileWriter backupWriter = new FileWriter("cats_backup.txt");

			for (Cat cat : catList)
			{
				backupWriter.write(cat.toString() + "\n");
				backupWriter.write("Feeding: " + cat.getFeedMessage() + "\n\n");
			}

			backupWriter.close();
		}
		catch (IOException e)
		{
			System.out.println("Error: Could not create backup file.");
		}
	}

	/**
	 * Loads cat information from a text file and returns it as a String.
	 *
	 * @param fileName the name of the file to load from
	 * @return the file contents as a String
	 */
	public String loadFromFile(String fileName)
	{
		String result = "";

		try
		{
			File file = new File(fileName);

			// Check access before using Scanner so the program gives a clear
			// message instead of failing unexpectedly.
			if (!file.exists() || !file.canRead())
			{
				return "Error: File does not exist or cannot be read.";
			}

			Scanner scanner = new Scanner(file);

			while (scanner.hasNextLine())
			{
				result += scanner.nextLine() + "\n";
			}

			scanner.close();
		}
		catch (IOException e)
		{
			result = "Error: Could not load cat data from the file.";
		}

		return result;
	}
}