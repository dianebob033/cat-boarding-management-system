import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Lead Author(s): Jiaqi Zhang
 * 
 * Responsibilities of class:
 * The SpecialCat class represents a cat with special care needs.
 * 
 * 中文说明：
 * SpecialCat 是 Cat 的子类，用来表示需要特殊护理的猫咪。
 */
public class SpecialCat extends Cat {
    private String medicalNote;

    public SpecialCat(String name, LocalDate birthDate, Owner owner, String careNotes,
            LocalDate startDate, LocalDate endDate,
            LocalTime dropOffTime, LocalTime pickUpTime,
            String medicalNote) {
    	super(name, birthDate, owner, careNotes, startDate, endDate, dropOffTime, pickUpTime);
    	this.medicalNote = medicalNote;
    }

    @Override
    public String getCategory() {
        if (getBoardingDays() >= 14) {
            return "Special Care + Long Stay Cat";
        }
        return "Special Care Cat";
    }

    @Override
    public void feed() {
        System.out.println(getName() + " needs special feeding instructions: " + medicalNote);
    }

    @Override
    public String getFeedMessage() {
        return getName() + " needs special feeding instructions: " + medicalNote;
    }
}