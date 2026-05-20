import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

/**
 * Lead Author(s): Jiaqi Zhang
 * 
 * Responsibilities of class:
 * The Cat class stores basic information about a cat.
 */
public class Cat implements Feedable
{
	private String name;
	private LocalDate birthDate;
	private Owner owner;
	private String careNotes;
	private LocalDate startDate;
	private LocalDate endDate;
	private LocalTime dropOffTime;
	private LocalTime pickUpTime;

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

	public long getBoardingDays()
	{
		return ChronoUnit.DAYS.between(startDate, endDate) + 1;
	}

	public String getCategory()
	{
		if (getBoardingDays() >= 14)
		{
			return "Long Stay Cat";
		}
		return "Regular Cat";
	}

	public int getAge()
	{
		return Period.between(birthDate, LocalDate.now()).getYears();
	}

	@Override
	public void feed()
	{
		System.out.println(
				name + " should be fed according to the regular care notes.");
	}

	public String getFeedMessage()
	{
		return name + " should be fed according to the regular care notes.";
	}

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