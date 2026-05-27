import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Lead Author(s): Jiaqi Zhang
 *
 * References:
 * Morelli, R., & Walde, R. (2016). Java, Java, Java:
 * Object-Oriented Problem Solving.
 * https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
 *
 * Oracle Pattern Documentation:
 * https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html
 *
 * Oracle Matcher Documentation:
 * https://docs.oracle.com/javase/8/docs/api/java/util/regex/Matcher.html
 *
 * Responsibilities of class:
 * SmartCatParser reads pasted text and tries to find cat boarding fields.
 */
public class SmartCatParser
{
	public static String findCatName(String text)
	{
		return findAfterKeyword(text,
				"(cat name is|cat named|name is|cat name:)");
	}

	public static String findOwnerName(String text)
	{
		return findAfterKeyword(text,
				"(owner is|owner name is|owner:|owner name:)");
	}

	public static String findPhone(String text)
	{
		String result = "";

		// Regex helps because phone numbers have a regular shape even when the
		// user types spaces or dashes.
		Pattern pattern = Pattern.compile("\\d{3}[- ]?\\d{3}[- ]?\\d{4}");
		Matcher matcher = pattern.matcher(safeText(text));

		if (matcher.find())
		{
			result = matcher.group();
		}

		return result;
	}

	public static String findFirstDate(String text)
	{
		return findDateByIndex(text, 0);
	}

	public static String findSecondDate(String text)
	{
		return findDateByIndex(text, 1);
	}

	public static String findThirdDate(String text)
	{
		return findDateByIndex(text, 2);
	}

	public static String findFirstTime(String text)
	{
		return findTimeByIndex(text, 0);
	}

	public static String findSecondTime(String text)
	{
		return findTimeByIndex(text, 1);
	}

	public static String findCareNotes(String text)
	{
		return findAfterKeyword(text,
				"(care notes:|notes:|care notes are|notes are)");
	}

	public static String findMedicalNotes(String text)
	{
		String medicalNote = findAfterKeyword(text,
				"(medical notes:|medical note:|medicine:|medication:|medical notes are)");

		if (medicalNote.isEmpty()
				&& safeText(text).toLowerCase().contains("medicine"))
		{
			// This backup rule helps Smart Fill notice special-care cats even
			// when the sentence is not perfectly labeled.
			medicalNote = "Needs medication or medical attention.";
		}

		return medicalNote;
	}

	private static String findAfterKeyword(String text, String keywordRegex)
	{
		String result = "";

		// CASE_INSENSITIVE makes pasted text more forgiving for the user.
		Pattern pattern = Pattern.compile(
				keywordRegex + "\\s*([A-Za-z0-9 .'-]+)",
				Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(safeText(text));

		if (matcher.find())
		{
			result = matcher.group(2).trim();

			if (result.contains("."))
			{
				// Stopping at a period prevents one field from taking the whole
				// paragraph.
				result = result.substring(0, result.indexOf(".")).trim();
			}
		}

		return result;
	}

	private static String findDateByIndex(String text, int index)
	{
		String result = "";
		Pattern pattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
		Matcher matcher = pattern.matcher(safeText(text));
		int foundDates = 0;

		while (matcher.find() && result.isEmpty())
		{
			if (foundDates == index)
			{
				result = matcher.group();
			}

			foundDates++;
		}

		return result;
	}

	private static String findTimeByIndex(String text, int index)
	{
		String result = "";
		Pattern pattern = Pattern.compile("\\d{1,2}:\\d{2}");
		Matcher matcher = pattern.matcher(safeText(text));
		int foundTimes = 0;

		while (matcher.find() && result.isEmpty())
		{
			if (foundTimes == index)
			{
				result = matcher.group();
			}

			foundTimes++;
		}

		return result;
	}

	private static String safeText(String text)
	{
		String safeText = "";

		if (text != null)
		{
			safeText = text;
		}

		return safeText;
	}
}
