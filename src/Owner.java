/**
 * Lead Author(s): Jiaqi Zhang
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
		return name + " (" + phoneNumber + ")";
	}
}