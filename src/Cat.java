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
 * https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
 *
 * Oracle LocalDate Documentation:
 * https://docs.oracle.com/javase/8/docs/api/java/time/LocalDate.html
 *
 * Oracle LocalTime Documentation:
 * https://docs.oracle.com/javase/8/docs/api/java/time/LocalTime.html
 *
 * Oracle Period Documentation:
 * https://docs.oracle.com/javase/8/docs/api/java/time/Period.html
 *
 * Responsibilities of class:
 * Cat stores the boarding information for one regular cat.
 */
public class Cat implements Feedable
{
	private static final long LONG_STAY_DAYS = 14;

	// Cat has-a name.
	private String name;

	// Cat has-a birth date.
	private LocalDate birthDate;

	// Cat has-a Owner. This shows aggregation.
	private Owner owner;

	// Cat has-a care note.
	private String careNotes;

	// Cat has-a boarding start date.
	private LocalDate startDate;

	// Cat has-a boarding end date.
	private LocalDate endDate;

	// Cat has-a drop-off time.
	private LocalTime dropOffTime;

	// Cat has-a pick-up time.
	private LocalTime pickUpTime;

	/**
	 * Creates a Cat object with boarding information.
	 *
	 * @param name        the cat's name
	 * @param birthDate   the cat's birth date
	 * @param owner       the owner of the cat
	 * @param careNotes   regular care notes
	 * @param startDate   boarding start date
	 * @param endDate     boarding end date
	 * @param dropOffTime drop-off time
	 * @param pickUpTime  pick-up time
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

	public String getName()
	{
		return name;
	}

	public LocalDate getBirthDate()
	{
		return birthDate;
	}

	public Owner getOwner()
	{
		return owner;
	}

	public String getCareNotes()
	{
		return careNotes;
	}

	public LocalDate getStartDate()
	{
		return startDate;
	}

	public LocalDate getEndDate()
	{
		return endDate;
	}

	public LocalTime getDropOffTime()
	{
		return dropOffTime;
	}

	public LocalTime getPickUpTime()
	{
		return pickUpTime;
	}

	/**
	 * Calculates the total number of boarding days.
	 *
	 * @return total boarding days
	 */
	public long getBoardingDays()
	{
		// Boarding includes both the first day and the last day.
		return ChronoUnit.DAYS.between(startDate, endDate) + 1;
	}

	/**
	 * Determines the boarding category of the cat.
	 *
	 * @return the category of the cat
	 */
	public String getCategory()
	{
		String category = "Regular Cat";

		if (getBoardingDays() >= LONG_STAY_DAYS)
		{
			category = "Long Stay Cat";
		}

		return category;
	}

	/**
	 * Calculates the age of the cat.
	 *
	 * @return the cat's age in years
	 */
	public int getAge()
	{
		// Period understands real calendar dates, so it is safer than
		// subtracting years by hand.
		return Period.between(birthDate, LocalDate.now()).getYears();
	}

	/**
	 * Prints feeding instructions for the cat.
	 */
	@Override
	public void feed()
	{
		System.out.println(getFeedMessage());
	}

	/**
	 * Returns feeding instructions as a String.
	 *
	 * @return feeding message
	 */
	public String getFeedMessage()
	{
		return name + " should be fed according to the regular care notes.";
	}

	/**
	 * Creates one line of text for saving this cat to a file.
	 *
	 * @return file-friendly cat information
	 */
	public String toFileString()
	{
		// The pipe format is simple for this class project and still easy to
		// rebuild into objects later.
		return "REGULAR|" + name + "|" + birthDate + "|" + owner.getName() + "|"
				+ owner.getPhoneNumber() + "|" + careNotes + "|" + startDate
				+ "|" + endDate + "|" + dropOffTime + "|" + pickUpTime;
	}

	/**
	 * Returns cat information for display.
	 *
	 * @return formatted cat information
	 */
	@Override
	public String toString()
	{
		return name + " | Age: " + getAge() + " | Birth Date: " + birthDate
				+ " | Owner: " + owner + " | Dates: " + startDate + " to "
				+ endDate + " | Drop-off: " + dropOffTime + " | Pick-up: "
				+ pickUpTime + " | Days: " + getBoardingDays() + " | Category: "
				+ getCategory() + " | Notes: " + careNotes;
	}
}