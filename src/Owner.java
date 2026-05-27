/**
 * Lead Author(s): Jiaqi Zhang
 *
 * References:
 * Morelli, R., & Walde, R. (2016). Java, Java, Java:
 * Object-Oriented Problem Solving.
 * https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
 *
 * Responsibilities of class:
 * Owner stores contact information for the person who owns a cat.
 */
public class Owner
{
	// Owner has-a name.
	private String name;

	// Owner has-a phone number.
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
		// This format is short enough for the GUI output area.
		return name + " (" + phoneNumber + ")";
	}
}