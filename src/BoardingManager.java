import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Lead Author(s): Jiaqi Zhang
 * 
 * Responsibilities of class:
 * The BoardingManager class manages all cats in the boarding system.
 * 
 * 中文说明：
 * BoardingManager 类负责管理所有寄养猫咪，包括添加、删除、查询、文件保存、文件读取、递归查询和二维数组寄养日历。
 */
public class BoardingManager
{
	private ArrayList<Cat> catList;

	// Two-dimensional array for boarding calendar
	// 用二维数组模拟寄养日历
	private String[][] boardingCalendar;

	/**
	 * Creates a BoardingManager object with an empty cat list.
	 * 创建一个空的猫咪管理系统。
	 */
	public BoardingManager()
	{
		catList = new ArrayList<Cat>();

		// 30 days x 5 rooms
		// 30天 x 5个房间
		boardingCalendar = new String[30][5];
	}

	/**
	 * Adds a cat to the boarding system.
	 * 添加猫到寄养系统。
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

		// Assign the cat to the calendar automatically
		// 自动把猫安排到寄养日历中
		assignCatToCalendar(cat.getName(), 0, catList.size() % 5);

		System.out.println(cat.getName() + " was added successfully.");
	}

	/**
	 * Assigns a cat to the boarding calendar.
	 * 将猫咪安排到寄养日历。
	 *
	 * @param catName the cat name
	 * @param day     boarding day
	 * @param room    room number
	 */
	public void assignCatToCalendar(String catName, int day, int room)
	{
		if (day >= 0 && day < boardingCalendar.length && room >= 0
				&& room < boardingCalendar[0].length)
		{

			boardingCalendar[day][room] = catName;
		}
	}

	/**
	 * Removes a cat by name.
	 * 根据名字删除猫。
	 *
	 * @param name the name of the cat
	 * @return true if removed successfully
	 */
	public boolean removeCat(String name)
	{
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
	 * Recursively searches for a cat by name.
	 * 使用递归方式查找猫咪。
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
	 * 递归辅助方法。
	 *
	 * @param name  cat name
	 * @param index current index
	 * @return matching cat or null
	 */
	private Cat recursiveSearchByName(String name, int index)
	{
		if (index >= catList.size())
		{
			return null;
		}

		if (catList.get(index).getName().equalsIgnoreCase(name))
		{
			return catList.get(index);
		}

		return recursiveSearchByName(name, index + 1);
	}

	/**
	 * Gets all cats in the system.
	 * 获取所有猫咪。
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
	 * Gets cats by category.
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
	 * 创建包含所有猫咪信息的字符串。
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
	 * 保存所有猫咪信息到文本文件。
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

			// Auto backup
			// 自动备份
			saveBackupFile();

			System.out.println("Cat data saved successfully.");
		}
		catch (IOException e)
		{
			System.out.println("Error: Could not save cat data to the file.");
		}
	}

	/**
	 * Saves a backup file automatically.
	 * 自动保存备份文件。
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
	 * 从文本文件读取猫咪信息并返回字符串。
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