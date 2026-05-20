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
 * Responsibilities of interface:
 * The Feedable interface represents objects that can be fed.
 * It allows different classes to define their own feeding behavior
 * while sharing a common method name.
 * 
 * This interface helps demonstrate abstraction and polymorphism.
 */
public interface Feedable
{
	/**
	 * Performs feeding behavior.
	 */
	void feed();
}