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
 * Oracle Inheritance Tutorial:
 * https://docs.oracle.com/javase/tutorial/java/IandI/subclasses.html
 *
 * Responsibilities of class:
 * SpecialCat represents a cat with extra medical or special-care needs.
 */
public class SpecialCat extends Cat
{
	private static final long LONG_STAY_DAYS = 14;

	// SpecialCat has-a medical note.
	private String medicalNote;

	/**
	 * Creates a SpecialCat object with regular cat information and a medical
	 * note.
	 *
	 * @param name        the cat's name
	 * @param birthDate   the cat's birth date
	 * @param owner       the owner of the cat
	 * @param careNotes   regular care notes
	 * @param startDate   boarding start date
	 * @param endDate     boarding end date
	 * @param dropOffTime drop-off time
	 * @param pickUpTime  pick-up time
	 * @param medicalNote medical note for special care
	 */
	public SpecialCat(String name, LocalDate birthDate, Owner owner,
			String careNotes, LocalDate startDate, LocalDate endDate,
			LocalTime dropOffTime, LocalTime pickUpTime, String medicalNote)
	{
		super(name, birthDate, owner, careNotes, startDate, endDate,
				dropOffTime, pickUpTime);
		this.medicalNote = medicalNote;
	}

	/**
	 * Gets the category for a special-care cat.
	 *
	 * @return special-care category
	 */
	@Override
	public String getCategory()
	{
		String category = "Special Care Cat";

		if (getBoardingDays() >= LONG_STAY_DAYS)
		{
			category = "Special Care + Long Stay Cat";
		}

		return category;
	}

	/**
	 * Prints the feeding instructions.
	 */
	@Override
	public void feed()
	{
		System.out.println(getFeedMessage());
	}

	/**
	 * Gets feeding instructions for a special-care cat.
	 *
	 * @return feeding message
	 */
	@Override
	public String getFeedMessage()
	{
		// Polymorphism lets the GUI ask every Cat for a feeding message without
		// checking which kind of cat it is.
		return getName() + " needs special feeding instructions: "
				+ medicalNote;
	}

	public String getMedicalNote()
	{
		return medicalNote;
	}

	/**
	 * Creates one line of text for saving this special cat to a file.
	 *
	 * @return file-friendly special cat information
	 */
	@Override
	public String toFileString()
	{
		return "SPECIAL|" + getName() + "|" + getBirthDate() + "|"
				+ getOwner().getName() + "|" + getOwner().getPhoneNumber() + "|"
				+ getCareNotes() + "|" + getStartDate() + "|" + getEndDate()
				+ "|" + getDropOffTime() + "|" + getPickUpTime() + "|"
				+ medicalNote;
	}
}