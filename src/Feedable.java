/**
 * Lead Author(s): Jiaqi Zhang
 *
 * References:
 * Morelli, R., & Walde, R. (2016). Java, Java, Java:
 * Object-Oriented Problem Solving.
 * https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
 *
 * Oracle Interface Tutorial:
 * https://docs.oracle.com/javase/tutorial/java/concepts/interface.html
 *
 * Responsibilities of interface:
 * Feedable marks objects that can give feeding behavior.
 */
public interface Feedable
{
	/**
	 * Performs feeding behavior for an object.
	 */
	void feed();
}