import java.time.LocalDate;
import java.time.LocalTime;

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
 * The SpecialCat class represents a cat with special care needs.
 * 
 */
public class SpecialCat extends Cat
{
	private String medicalNote;

	public SpecialCat(String name, LocalDate birthDate, Owner owner,
			String careNotes, LocalDate startDate, LocalDate endDate,
			LocalTime dropOffTime, LocalTime pickUpTime, String medicalNote)
	{
		super(name, birthDate, owner, careNotes, startDate, endDate,
				dropOffTime, pickUpTime);
		this.medicalNote = medicalNote;
	}

	@Override
	public String getCategory()
	{
		// long stay + special care = both categories
		if (getBoardingDays() >= 14)
		{
			return "Special Care + Long Stay Cat";
		}
		return "Special Care Cat";
	}

	@Override
	public void feed()
	{
		// just reuse getFeedMessage() to avoid writing the same string twice
		System.out.println(getFeedMessage());
	}

	@Override
	public String getFeedMessage()
	{
		// same as feed() but returns string instead of printing
		// (interface requires this method)
		return getName() + " needs special feeding instructions: "
				+ medicalNote;
	}

	/**
	 * Gets the medical note for the special-care cat.
	 * 
	 * @return medical note
	 */
	public String getMedicalNote()
	{
		return medicalNote;
	}

	/**
	 * Creates one line of text for saving this special cat to a file.
	 * This format helps rebuild the object later when loading the file.
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