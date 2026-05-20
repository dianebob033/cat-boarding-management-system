import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

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
 * The Cat class stores basic information about a cat.
 */
public class Cat implements Feedable
{
	// A Cat has-a name.
	private String name;

	// A Cat has-a birth date.
	private LocalDate birthDate;

	// A Cat has-a Owner object (aggregation).
	private Owner owner;

	// A Cat has-a care note.
	private String careNotes;

	// A Cat has-a boarding start date.
	private LocalDate startDate;

	// A Cat has-a boarding end date.
	private LocalDate endDate;

	// A Cat has-a drop-off time.
	private LocalTime dropOffTime;

	// A Cat has-a drop-up time.
	private LocalTime pickUpTime;

	/**
	 * Creates a Cat object with boarding information.
	 * 
	 * Purpose:
	 * 
	 * @param name
	 * @param birthDate
	 * @param owner
	 * @param careNotes
	 * @param startDate
	 * @param endDate
	 * @param dropOffTime
	 * @param pickUpTime
	 */
	public Cat(String name, LocalDate birthDate, Owner owner, String careNotes,
			LocalDate startDate, LocalDate endDate, LocalTime dropOffTime,
			LocalTime pickUpTime)
	{
		this.name = name;
		this.birthDate = birthDate;
		this.owner = owner;
		this.careNotes = careNotes;
		this.startDate = startDate;
		this.endDate = endDate;
		this.dropOffTime = dropOffTime;
		this.pickUpTime = pickUpTime;
	}

	/**
	 * Gets the cat's name.
	 * 
	 * @return the cat's name.
	 */

	public String getName()
	{
		return name;
	}

	/**
	 * Calculates the total number of boarding days.
	 *
	 * @return total boarding days
	 */

	public long getBoardingDays()
	{
		// +1 because boarding includes both start and end day
		return ChronoUnit.DAYS.between(startDate, endDate) + 1;
	}

	/**
	 * Determines the boarding category of the cat.
	 *
	 * @return the category of the cat
	 */

	public String getCategory()
	{
		// school requirement: need to separate long stay (>2 weeks) from
		// regular
		if (getBoardingDays() >= 14)
		{
			return "Long Stay Cat";
		}
		return "Regular Cat";
	}

	/**
	 * Calculates the age of the cat.
	 * Using Period instead of manual calculation because it handles leap year
	 * and month lengths automatically
	 * 
	 * @return the cat's age in years
	 */
	public int getAge()
	{
		return Period.between(birthDate, LocalDate.now()).getYears();
	}

	/**
	 * Prints feeding instructions for the cat.
	 */

	@Override
	public void feed()
	{
		// just print to console for now, but in real app this would connect
		// to feeding schedule system
		System.out.println(
				name + " should be fed according to the regular care notes.");
	}

	/**
	 * Returns all cat information as a String.
	 * 
	 * @return formatted cat information
	 */
	// this is duplicated from feed() but interface requires it
	// could refactor later but fine for now
	public String getFeedMessage()
	{
		return name + " should be fed according to the regular care notes.";
	}

	@Override
	public String toString()
	{
		// Combine all cat information into one formatted String.
		// keeping everything in one line so file output is easier to parse
		return name + " | Age: " + getAge() + " | Birth Date: " + birthDate
				+ " | Owner: " + owner + " | Dates: " + startDate + " to "
				+ endDate + " | Drop-off: " + dropOffTime + " | Pick-up: "
				+ pickUpTime + " | Days: " + getBoardingDays() + " | Category: "
				+ getCategory() + " | Notes: " + careNotes;
	}
}