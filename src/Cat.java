/**
 * Lead Author(s): Jiaqi Zhang
 * 
 * Responsibilities of class:
 * The Cat class stores basic information about a cat.
 * 
 * This class demonstrates aggregation because a Cat HAS-A Owner.
 * This class also implements the Feedable interface.
 */
public class Cat implements Feedable {
    private String name;
    private int age;
    private Owner owner;      // Aggregation: Cat HAS-A Owner
    private String careNotes;

    /**
     * Creates a Cat object.
     *
     * @param name the cat's name
     * @param age the cat's age
     * @param owner the cat's owner
     * @param careNotes care notes for the cat
     */
    public Cat(String name, int age, Owner owner, String careNotes) {
        this.name = name;
        this.age = age;
        this.owner = owner;
        this.careNotes = careNotes;
    }

    /**
     * Gets the cat's name.
     *
     * @return cat name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the cat's age.
     *
     * @return cat age
     */
    public int getAge() {
        return age;
    }

    /**
     * Gets the cat's owner.
     *
     * @return owner object
     */
    public Owner getOwner() {
        return owner;
    }

    /**
     * Gets the care notes.
     *
     * @return care notes
     */
    public String getCareNotes() {
        return careNotes;
    }

    /**
     * Returns basic feeding instructions.
     *
     * @return feeding instruction String
     */
    @Override
    public String feed() {
        return name + " should be fed according to the regular care notes.";
    }

    /**
     * Returns cat information as a String.
     *
     * @return cat information
     */
    @Override
    public String toString() {
        return name 
                + " | Age: " + age 
                + " | Owner: " + owner 
                + " | Notes: " + careNotes;
    }
}