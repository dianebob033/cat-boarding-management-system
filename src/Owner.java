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
 * https://docs.oracle.com/javase/8/docs/api/ *
 * 
 * Responsibilities of class:
 * The Owner class stores information about the owner of a cat,
 * including the owner's name and phone number.
 */
public class Owner
{
	private String name;
	private String phoneNumber;

	/**
	 * Creates an Owner object with a name and phone number.
	 *
	 * @param name        the owner's name
	 * @param phoneNumber the owner's phone number
	 */
	public Owner(String name, String phoneNumber)
	{
		this.name = name;
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Gets the owner's name.
	 *
	 * @return the owner's name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Gets the owner's phone number.
	 *
	 * @return the owner's phone number
	 */
	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	/**
	 * Returns the owner information as a String.
	 *
	 * @return owner information
	 */
	@Override
	public String toString()
	{
		// format: "Name (123-456-7890)" - easier to read in GUI list
		return name + " (" + phoneNumber + ")";
	}
}