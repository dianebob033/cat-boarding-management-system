import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.TreeMap;

/**
 * Lead Author(s): Jiaqi Zhang
 *
 * References:
 * Morelli, R., & Walde, R. (2016). Java, Java, Java:
 * Object-Oriented Problem Solving.
 * https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
 *
 * Oracle ArrayList Documentation:
 * https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html
 *
 * Oracle Queue Documentation:
 * https://docs.oracle.com/javase/8/docs/api/java/util/Queue.html
 *
 * Oracle LinkedList Documentation:
 * https://docs.oracle.com/javase/8/docs/api/java/util/LinkedList.html
 *
 * Oracle HashMap Documentation:
 * https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html
 *
 * Oracle TreeMap Documentation:
 * https://docs.oracle.com/javase/8/docs/api/java/util/TreeMap.html
 *
 * Oracle Scanner Documentation:
 * https://docs.oracle.com/javase/8/docs/api/java/util/Scanner.html
 *
 * Oracle FileWriter Documentation:
 * https://docs.oracle.com/javase/8/docs/api/java/io/FileWriter.html
 *
 * Oracle try-with-resources Tutorial:
 * https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html
 *
 * Responsibilities of class:
 * BoardingManager owns the cat list, searching, saving, and loading.
 */
public class BoardingManager
{
	private static final int CALENDAR_DAYS = 30;
	private static final int BOARDING_ROOMS = 6;
	private static final String BACKUP_FILE_NAME = "cats_backup.txt";

	// BoardingManager has-many Cat objects.
	private ArrayList<Cat> catList;

	// Rows represent days and columns represent boarding rooms.
	private String[][] boardingCalendar;

	// Queue fits a wait list because the first cat waiting should be first out.
	private Queue<String> waitingList;

	// LinkedList works well for a growing activity history.
	private LinkedList<String> activityLog;

	// Stack supports undoing the most recent add without searching history.
	private Stack<Cat> recentlyAddedCats;

	// HashMap gives fast name lookup and prevents duplicate cats.
	private HashMap<String, Cat> catLookup;

	// TreeMap keeps the boarding schedule sorted by start date.
	private TreeMap<LocalDate, ArrayList<Cat>> catsByStartDate;

	/**
	 * Creates a BoardingManager object with an empty cat list and calendar.
	 */
	public BoardingManager()
	{
		catList = new ArrayList<Cat>();

		// The small 2D calendar shows room planning without making this class
		// too large for the assignment.
		boardingCalendar = new String[CALENDAR_DAYS][BOARDING_ROOMS];
		waitingList = new LinkedList<String>();
		activityLog = new LinkedList<String>();
		recentlyAddedCats = new Stack<Cat>();
		catLookup = new HashMap<String, Cat>();
		catsByStartDate = new TreeMap<LocalDate, ArrayList<Cat>>();
	}

	/**
	 * Adds a cat to the boarding system.
	 *
	 * @param cat the Cat object to add
	 * @return true if the cat was added
	 */
	public boolean addCat(Cat cat)
	{
		boolean added = false;

		if (cat != null && !hasCatNamed(cat.getName()))
		{
			catList.add(cat);
			indexCat(cat);
			placeCatOnCalendar(cat);
			recentlyAddedCats.push(cat);
			addActivity("Added " + cat.getName() + " for "
					+ cat.getBoardingDays() + " day(s).");
			added = true;
		}

		return added;
	}

	/**
	 * Removes a cat by name.
	 *
	 * @param name the name of the cat
	 * @return true if removed successfully
	 */
	public boolean removeCat(String name)
	{
		boolean removed = false;
		String cleanName = cleanText(name);

		if (!cleanName.isEmpty())
		{
			for (int index = 0; index < catList.size() && !removed; index++)
			{
				if (catList.get(index).getName().equalsIgnoreCase(cleanName))
				{
					Cat removedCat = catList.remove(index);
					removeCatFromIndexes(removedCat);
					rebuildCalendar();
					addActivity("Removed " + removedCat.getName() + ".");
					removed = true;
				}
			}
		}

		return removed;
	}

	/**
	 * Searches for the first cat whose name contains the search text.
	 * This method is recursive to demonstrate recursive problem solving.
	 *
	 * @param name the cat name or partial name
	 * @return the cat if found, otherwise null
	 */
	public Cat recursiveSearchByName(String name)
	{
		return recursiveSearchByName(cleanText(name), 0);
	}

	/**
	 * Helper recursive method for searching by name.
	 *
	 * @param name  cat name or partial name
	 * @param index current index
	 * @return matching cat or null
	 */
	private Cat recursiveSearchByName(String name, int index)
	{
		Cat result = null;

		if (!name.isEmpty() && index < catList.size())
		{
			String currentName = catList.get(index).getName().toLowerCase();

			if (currentName.contains(name.toLowerCase()))
			{
				result = catList.get(index);
			}
			else
			{
				// The recursive call keeps the search example small and clear.
				result = recursiveSearchByName(name, index + 1);
			}
		}

		return result;
	}

	/**
	 * Gets all cats in the system.
	 *
	 * @return a copy of the cat list
	 */
	public ArrayList<Cat> getAllCats()
	{
		// Returning a copy protects this class from outside code changing the
		// real list by accident.
		return new ArrayList<Cat>(catList);
	}

	/**
	 * Adds a cat name to the wait list.
	 *
	 * @param name cat name
	 * @return true if the cat was added to the wait list
	 */
	public boolean addToWaitingList(String name)
	{
		boolean added = false;
		String cleanName = cleanText(name);

		if (!cleanName.isEmpty())
		{
			waitingList.add(cleanName);
			addActivity("Added " + cleanName + " to the waiting list.");
			added = true;
		}

		return added;
	}

	/**
	 * Removes the next cat from the wait list.
	 *
	 * @return next waiting cat name or a message
	 */
	public String getNextWaitingCat()
	{
		String result = "No cats are waiting.";

		if (!waitingList.isEmpty())
		{
			result = waitingList.remove();
			addActivity("Moved " + result + " from the waiting list.");
		}

		return result;
	}

	/**
	 * Undoes the most recent add action.
	 *
	 * @return true if a cat was removed by undo
	 */
	public boolean undoLastAdd()
	{
		boolean undone = false;

		if (!recentlyAddedCats.isEmpty())
		{
			Cat cat = recentlyAddedCats.pop();
			undone = removeCat(cat.getName());

			if (undone)
			{
				addActivity("Undid the add for " + cat.getName() + ".");
			}
		}

		return undone;
	}

	/**
	 * Searches for a cat by exact name.
	 *
	 * @param name the cat name to search for
	 * @return cat information if found, or a not found message
	 */
	public String searchCatByName(String name)
	{
		String result = "Cat not found.";
		String cleanName = cleanText(name);

		if (cleanName.isEmpty())
		{
			result = "Error: Please enter a cat name to search.";
		}
		else
		{
			Cat cat = catLookup.get(cleanName.toLowerCase());

			if (cat != null)
			{
				result = formatCatWithFeeding(cat);
			}
		}

		return result;
	}

	/**
	 * Searches for all cats whose names contain the search text.
	 *
	 * @param name the search text
	 * @return all matching cats as a String
	 */
	public String searchAllCatsByName(String name)
	{
		String result;
		String cleanName = cleanText(name);

		if (cleanName.isEmpty())
		{
			result = "Error: Please enter a cat name to search.";
		}
		else
		{
			StringBuilder matches = new StringBuilder();

			for (Cat cat : catList)
			{
				if (cat.getName().toLowerCase().contains(cleanName.toLowerCase()))
				{
					matches.append(formatCatWithFeeding(cat)).append("\n\n");
				}
			}

			result = matches.toString().trim();

			if (result.isEmpty())
			{
				result = "Cat not found.";
			}
		}

		return result;
	}

	/**
	 * Gets cats by category.
	 *
	 * @param category the category keyword
	 * @return cats that match the category
	 */
	public String searchByCategory(String category)
	{
		String cleanCategory = cleanText(category);
		StringBuilder result = new StringBuilder();

		for (Cat cat : catList)
		{
			if (cat.getCategory().equalsIgnoreCase(cleanCategory))
			{
				result.append(formatCatWithFeeding(cat)).append("\n\n");
			}
		}

		String categoryResult = result.toString().trim();

		if (categoryResult.isEmpty())
		{
			categoryResult = "No cats found in this category.";
		}

		return categoryResult;
	}

	/**
	 * Creates a String containing all cat information.
	 *
	 * @return all cats as a formatted String
	 */
	public String getAllCatsAsString()
	{
		String result;

		if (catList.isEmpty())
		{
			result = "No cats are currently in the system.";
		}
		else
		{
			StringBuilder allCats = new StringBuilder();

			for (Cat cat : catList)
			{
				allCats.append(formatCatWithFeeding(cat)).append("\n\n");
			}

			result = allCats.toString().trim();
		}

		return result;
	}

	/**
	 * Builds a schedule report sorted by boarding start date.
	 *
	 * @return schedule report
	 */
	public String getScheduleReport()
	{
		String result;

		if (catsByStartDate.isEmpty())
		{
			result = "No scheduled cats are currently in the system.";
		}
		else
		{
			StringBuilder report = new StringBuilder();

			for (Map.Entry<LocalDate, ArrayList<Cat>> entry : catsByStartDate
					.entrySet())
			{
				report.append(entry.getKey()).append(":\n");

				for (Cat cat : entry.getValue())
				{
					report.append("  ").append(cat.getName()).append(" - ")
							.append(cat.getCategory()).append("\n");
				}
			}

			result = report.toString().trim();
		}

		return result;
	}

	/**
	 * Gets a report of the wait list.
	 *
	 * @return wait list report
	 */
	public String getWaitingListAsString()
	{
		String result = "No cats are waiting.";

		if (!waitingList.isEmpty())
		{
			StringBuilder report = new StringBuilder("Waiting List:\n");

			for (String catName : waitingList)
			{
				report.append(catName).append("\n");
			}

			result = report.toString().trim();
		}

		return result;
	}

	/**
	 * Gets the activity log.
	 *
	 * @return activity log report
	 */
	public String getActivityLogAsString()
	{
		String result = "No activity has been recorded yet.";

		if (!activityLog.isEmpty())
		{
			StringBuilder report = new StringBuilder("Activity Log:\n");

			for (String activity : activityLog)
			{
				report.append(activity).append("\n");
			}

			result = report.toString().trim();
		}

		return result;
	}

	/**
	 * Saves all cat information to a text file.
	 *
	 * @param fileName the name of the file to save to
	 * @throws IOException if the file cannot be written
	 */
	public void saveToFile(String fileName) throws IOException
	{
		writeCatsToFile(fileName);
		writeCatsToFile(BACKUP_FILE_NAME);
	}

	/**
	 * Loads cat information from a text file and rebuilds Cat objects.
	 *
	 * @param fileName the name of the file to load from
	 * @return loaded cat information as a String
	 * @throws IOException if the file cannot be read
	 */
	public String loadFromFile(String fileName) throws IOException
	{
		String result;
		File file = new File(fileName);

		if (!file.exists() || !file.canRead())
		{
			result = "Error: File does not exist or cannot be read.";
		}
		else
		{
			ArrayList<Cat> loadedCats = new ArrayList<Cat>();
			int skippedLines = loadCatsFromScanner(file, loadedCats);

			catList = loadedCats;
			rebuildAllIndexes();
			recentlyAddedCats.clear();
			addActivity("Loaded cat data from " + fileName + ".");

			result = getAllCatsAsString();

			if (skippedLines > 0)
			{
				result += "\n\nNote: " + skippedLines
						+ " saved line(s) were skipped because they were not valid.";
			}
		}

		return result;
	}

	/**
	 * Checks if a cat name is already in the system.
	 *
	 * @param name cat name
	 * @return true if the name already exists
	 */
	public boolean hasCatNamed(String name)
	{
		String cleanName = cleanText(name);
		return catLookup.containsKey(cleanName.toLowerCase());
	}

	/**
	 * Writes cats to a file.
	 *
	 * @param fileName file name
	 * @throws IOException if the file cannot be written
	 */
	private void writeCatsToFile(String fileName) throws IOException
	{
		// try-with-resources closes the writer even when saving has a problem.
		try (FileWriter writer = new FileWriter(fileName))
		{
			for (Cat cat : catList)
			{
				writer.write(cat.toFileString() + "\n");
			}
		}
	}

	/**
	 * Loads cats from a scanner.
	 *
	 * @param file       file to read
	 * @param loadedCats list that receives loaded cats
	 * @return number of skipped lines
	 * @throws IOException if the file cannot be read
	 */
	private int loadCatsFromScanner(File file, ArrayList<Cat> loadedCats)
			throws IOException
	{
		int skippedLines = 0;

		// Scanner is closed automatically, which is safer for repeated loads.
		try (Scanner scanner = new Scanner(file))
		{
			while (scanner.hasNextLine())
			{
				String line = scanner.nextLine();
				Cat cat = buildCatFromFileLine(line);

				if (cat == null || hasDuplicateInList(loadedCats, cat.getName()))
				{
					skippedLines++;
				}
				else
				{
					loadedCats.add(cat);
				}
			}
		}

		return skippedLines;
	}

	/**
	 * Rebuilds a Cat or SpecialCat object from one saved file line.
	 *
	 * @param line one saved file line
	 * @return rebuilt Cat object, or null if the line is not valid
	 */
	private Cat buildCatFromFileLine(String line)
	{
		Cat cat = null;

		if (isSavedCatLine(line))
		{
			String[] parts = line.split("\\|", -1);

			if (parts.length >= 10)
			{
				cat = buildCatFromParts(parts);
			}
		}

		return cat;
	}

	/**
	 * Builds one cat from split file parts.
	 *
	 * @param parts saved file parts
	 * @return Cat object or null
	 */
	private Cat buildCatFromParts(String[] parts)
	{
		Cat cat = null;

		try
		{
			String type = parts[0];
			String name = parts[1];
			LocalDate birthDate = LocalDate.parse(parts[2]);
			Owner owner = new Owner(parts[3], parts[4]);
			String careNotes = parts[5];
			LocalDate startDate = LocalDate.parse(parts[6]);
			LocalDate endDate = LocalDate.parse(parts[7]);
			LocalTime dropOffTime = LocalTime.parse(parts[8]);
			LocalTime pickUpTime = LocalTime.parse(parts[9]);

			if (type.equals("SPECIAL") && parts.length >= 11)
			{
				cat = new SpecialCat(name, birthDate, owner, careNotes,
						startDate, endDate, dropOffTime, pickUpTime, parts[10]);
			}
			else if (type.equals("REGULAR"))
			{
				cat = new Cat(name, birthDate, owner, careNotes, startDate,
						endDate, dropOffTime, pickUpTime);
			}
		}
		catch (DateTimeParseException dateProblem)
		{
			// One bad date should not stop the whole saved file from loading.
			cat = null;
		}

		return cat;
	}

	/**
	 * Checks whether a saved line looks like the current file format.
	 *
	 * @param line one line from the saved file
	 * @return true if the line can be parsed by this program
	 */
	private boolean isSavedCatLine(String line)
	{
		return line != null && !line.trim().isEmpty()
				&& (line.startsWith("REGULAR|") || line.startsWith("SPECIAL|"));
	}

	/**
	 * Places a cat on the simple calendar.
	 *
	 * @param cat cat to place
	 */
	private void placeCatOnCalendar(Cat cat)
	{
		int room = (catList.size() - 1) % boardingCalendar[0].length;
		boardingCalendar[0][room] = cat.getName();
	}

	/**
	 * Rebuilds calendar after loading or removing cats.
	 */
	private void rebuildCalendar()
	{
		boardingCalendar = new String[CALENDAR_DAYS][BOARDING_ROOMS];

		for (int index = 0; index < catList.size(); index++)
		{
			int room = index % boardingCalendar[0].length;
			boardingCalendar[0][room] = catList.get(index).getName();
		}
	}

	/**
	 * Rebuilds every helper structure after loading from a file.
	 */
	private void rebuildAllIndexes()
	{
		catLookup.clear();
		catsByStartDate.clear();
		rebuildCalendar();

		for (Cat cat : catList)
		{
			indexCat(cat);
		}
	}

	/**
	 * Adds a cat to lookup structures.
	 *
	 * @param cat cat to index
	 */
	private void indexCat(Cat cat)
	{
		catLookup.put(cat.getName().toLowerCase(), cat);

		if (!catsByStartDate.containsKey(cat.getStartDate()))
		{
			catsByStartDate.put(cat.getStartDate(), new ArrayList<Cat>());
		}

		catsByStartDate.get(cat.getStartDate()).add(cat);
	}

	/**
	 * Removes a cat from lookup structures.
	 *
	 * @param cat cat to remove
	 */
	private void removeCatFromIndexes(Cat cat)
	{
		catLookup.remove(cat.getName().toLowerCase());

		ArrayList<Cat> catsOnDate = catsByStartDate.get(cat.getStartDate());

		if (catsOnDate != null)
		{
			catsOnDate.remove(cat);

			if (catsOnDate.isEmpty())
			{
				catsByStartDate.remove(cat.getStartDate());
			}
		}
	}

	/**
	 * Adds one activity message.
	 *
	 * @param message activity text
	 */
	private void addActivity(String message)
	{
		// Keeping a short log makes the project history visible inside the app.
		activityLog.add(message);
	}

	/**
	 * Formats one cat with its feeding message.
	 *
	 * @param cat cat to format
	 * @return formatted text
	 */
	private String formatCatWithFeeding(Cat cat)
	{
		// This uses polymorphism because regular and special cats answer the
		// same method in different ways.
		return cat.toString() + "\nFeeding: " + cat.getFeedMessage();
	}

	/**
	 * Cleans text before comparing user input.
	 *
	 * @param text text to clean
	 * @return trimmed text or empty string
	 */
	private String cleanText(String text)
	{
		String cleanText = "";

		if (text != null)
		{
			cleanText = text.trim();
		}

		return cleanText;
	}

	/**
	 * Checks duplicates in a temporary list.
	 *
	 * @param cats list to check
	 * @param name cat name
	 * @return true if duplicate exists
	 */
	private boolean hasDuplicateInList(ArrayList<Cat> cats, String name)
	{
		boolean duplicate = false;

		for (Cat cat : cats)
		{
			if (cat.getName().equalsIgnoreCase(name))
			{
				duplicate = true;
			}
		}

		return duplicate;
	}
}
