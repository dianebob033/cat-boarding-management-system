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
 * This class demonstrates the use of a generic collection because it uses
 * an ArrayList<Cat> to store multiple Cat objects.
 * 
 * This class also demonstrates file I/O and exception handling by saving
 * cat information to a text file and loading cat information from a text file.
 */
public class BoardingManager {
    private ArrayList<Cat> catList;

    /**
     * Creates a BoardingManager object with an empty cat list.
     */
    public BoardingManager() {
        catList = new ArrayList<Cat>();
    }

    /**
     * Adds a cat to the boarding system.
     * 添加猫到寄养系统。
     *
     * @param cat the Cat object to add
     */
    public void addCat(Cat cat) {

        // Check if the cat object is null
        // 检查猫对象是否为空
        if (cat == null) {
            System.out.println("Error: Cannot add a null cat.");
            return;
        }

        // Add cat to the ArrayList
        // 将猫加入列表
        catList.add(cat);

        System.out.println(cat.getName() + " was added successfully.");
    }

    /**
     * Removes a cat by name.
     * 根据名字删除猫。
     *
     * @param name the name of the cat
     * @return true if removed successfully
     */
    public boolean removeCat(String name) {

        // Check if the name is empty
        // 检查名字是否为空
        if (name == null || name.isEmpty()) {
            System.out.println("Error: Invalid cat name.");
            return false;
        }

        // Search through the list
        // 遍历列表寻找猫
        for (int i = 0; i < catList.size(); i++) {

            // Compare names ignoring uppercase/lowercase
            // 忽略大小写比较名字
            if (catList.get(i).getName().equalsIgnoreCase(name)) {

                catList.remove(i);

                System.out.println(name + " was removed successfully.");

                return true;
            }
        }

        // Cat not found
        // 没找到猫
        System.out.println("Cat not found.");

        return false;
    }

    /**
     * Gets all cats in the system.
     *
     * @return the ArrayList of cats
     */
    public ArrayList<Cat> getAllCats() {
        return catList;
    }

    /**
     * Creates a String containing all cat information.
     *
     * @return all cats as a formatted String
     */
    public String getAllCatsAsString() {
        if (catList.isEmpty()) {
            return "No cats are currently in the system.";
        }

        String result = "";

        for (Cat cat : catList) {
            result += cat.toString() + "\n";
            result += "Feeding: " + cat.feed() + "\n\n";
        }

        return result;
    }

    /**
     * Saves all cat information to a text file.
     *
     * @param fileName the name of the file to save to
     */
    public void saveToFile(String fileName) {
        try {
            FileWriter writer = new FileWriter(fileName);

            for (Cat cat : catList) {
                writer.write(cat.toString() + "\n");
                writer.write("Feeding: " + cat.feed() + "\n\n");
            }

            writer.close();
            System.out.println("Cat data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error: Could not save cat data to the file.");
        }
    }

    /**
     * Loads cat information from a text file and returns it as a String.
     *
     * @param fileName the name of the file to load from
     * @return the file contents as a String
     */
    public String loadFromFile(String fileName) {
        String result = "";

        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                result += scanner.nextLine() + "\n";
            }

            scanner.close();
        } catch (IOException e) {
            result = "Error: Could not load cat data from the file.";
        }

        return result;
    }
}