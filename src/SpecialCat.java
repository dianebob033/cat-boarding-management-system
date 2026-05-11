/**
 * Lead Author(s): Jiaqi Zhang
 * 
 * Responsibilities of class:
 * The SpecialCat class represents a cat with special care needs.
 * 
 * This class demonstrates inheritance because SpecialCat IS-A Cat.
 */
public class SpecialCat extends Cat {
    private String medicalNote;

    /**
     * Creates a SpecialCat object.
     *
     * @param name the cat's name
     * @param age the cat's age
     * @param owner the cat's owner
     * @param careNotes general care notes
     * @param medicalNote medical or special care notes
     */
    public SpecialCat(String name, int age, Owner owner, String careNotes, String medicalNote) {
        super(name, age, owner, careNotes);
        this.medicalNote = medicalNote;
    }

    /**
     * Gets the medical note.
     *
     * @return medical note
     */
    public String getMedicalNote() {
        return medicalNote;
    }

    /**
     * Returns special feeding instructions.
     *
     * This method overrides the feed method from Cat.
     *
     * @return special feeding instruction String
     */
    @Override
    public String feed() {
        return getName() + " needs special feeding instructions: " + medicalNote;
    }

    /**
     * Returns special cat information as a String.
     *
     * @return special cat information
     */
    @Override
    public String toString() {
        return super.toString() + " | Medical Note: " + medicalNote;
    }
}