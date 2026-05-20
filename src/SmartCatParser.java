import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Lead Author(s): Jiaqi Zhang
 *
 * Responsibilities of class:
 * The SmartCatParser class tries to recognize cat boarding information
 * from a paragraph of text using simple keyword matching and regular expressions.
 *
 * 中文说明：
 * SmartCatParser 用关键词和正则表达式，从一段文字里自动识别猫咪寄养信息。
 */
public class SmartCatParser {

    /**
     * Finds the cat name from text.
     *
     * @param text user pasted text
     * @return cat name or empty string
     */
    public static String findCatName(String text) {
        return findAfterKeyword(text, "(cat name is|cat named|name is|cat name:)");
    }

    /**
     * Finds owner name from text.
     *
     * @param text user pasted text
     * @return owner name or empty string
     */
    public static String findOwnerName(String text) {
        return findAfterKeyword(text, "(owner is|owner name is|owner:|owner name:)");
    }

    /**
     * Finds phone number from text.
     *
     * @param text user pasted text
     * @return phone number or empty string
     */
    public static String findPhone(String text) {
        Pattern pattern = Pattern.compile("\\d{3}[- ]?\\d{3}[- ]?\\d{4}");
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            return matcher.group();
        }

        return "";
    }

    /**
     * Finds the first date in yyyy-mm-dd format.
     *
     * @param text user pasted text
     * @return first date or empty string
     */
    public static String findFirstDate(String text) {
        return findDateByIndex(text, 0);
    }

    /**
     * Finds the second date in yyyy-mm-dd format.
     *
     * @param text user pasted text
     * @return second date or empty string
     */
    public static String findSecondDate(String text) {
        return findDateByIndex(text, 1);
    }

    /**
     * Finds the third date in yyyy-mm-dd format.
     *
     * @param text user pasted text
     * @return third date or empty string
     */
    public static String findThirdDate(String text) {
        return findDateByIndex(text, 2);
    }

    /**
     * Finds the first time in HH:mm format.
     *
     * @param text user pasted text
     * @return first time or empty string
     */
    public static String findFirstTime(String text) {
        return findTimeByIndex(text, 0);
    }

    /**
     * Finds the second time in HH:mm format.
     *
     * @param text user pasted text
     * @return second time or empty string
     */
    public static String findSecondTime(String text) {
        return findTimeByIndex(text, 1);
    }

    /**
     * Finds care notes.
     *
     * @param text user pasted text
     * @return care notes or empty string
     */
    public static String findCareNotes(String text) {
        String notes = findAfterKeyword(text, "(care notes:|notes:|care notes are|notes are)");

        if (!notes.isEmpty()) {
            return notes;
        }

        return "";
    }

    /**
     * Finds medical notes.
     *
     * @param text user pasted text
     * @return medical notes or empty string
     */
    public static String findMedicalNotes(String text) {
        String medical = findAfterKeyword(text, "(medical notes:|medical note:|medicine:|medication:|medical notes are)");

        if (!medical.isEmpty()) {
            return medical;
        }

        if (text.toLowerCase().contains("medicine") || text.toLowerCase().contains("medication")) {
            return "Needs medication or medical attention.";
        }

        return "";
    }

    /**
     * Helper method to find text after a keyword.
     *
     * @param text user pasted text
     * @param keywordRegex keyword pattern
     * @return recognized phrase
     */
    private static String findAfterKeyword(String text, String keywordRegex) {
        Pattern pattern = Pattern.compile(keywordRegex + "\\s*([A-Za-z0-9 .'-]+)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            String result = matcher.group(2).trim();

            // Stop at sentence ending if needed
            if (result.contains(".")) {
                result = result.substring(0, result.indexOf(".")).trim();
            }

            return result;
        }

        return "";
    }

    /**
     * Helper method to find date by order.
     *
     * @param text user pasted text
     * @param index date order
     * @return date string
     */
    private static String findDateByIndex(String text, int index) {
        Pattern pattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
        Matcher matcher = pattern.matcher(text);

        int count = 0;

        while (matcher.find()) {
            if (count == index) {
                return matcher.group();
            }
            count++;
        }

        return "";
    }

    /**
     * Helper method to find time by order.
     *
     * @param text user pasted text
     * @param index time order
     * @return time string
     */
    private static String findTimeByIndex(String text, int index) {
        Pattern pattern = Pattern.compile("\\d{1,2}:\\d{2}");
        Matcher matcher = pattern.matcher(text);

        int count = 0;

        while (matcher.find()) {
            if (count == index) {
                return matcher.group();
            }
            count++;
        }

        return "";
    }
}