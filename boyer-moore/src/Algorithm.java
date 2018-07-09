import java.util.ArrayList;
import java.util.Arrays;

public class Algorithm {

    public static ArrayList<String> boyerMooreMatcher(String text, String pattern, boolean caseSensitive) {

        int last[] = new int[256];
        Arrays.fill(last, -1);

        for (int i = 0; i < pattern.length(); i++) {
            last[pattern.charAt(i)] = i;
        }

        String lowerCasePattern = pattern.toLowerCase();
        String lowerCaseText = text.toLowerCase();
        char currentCharInText;

        ArrayList<String> wordsContainingPattern = new ArrayList<>();

        int textIndex = pattern.length() - 1;
        int patternIndex = pattern.length() - 1;

        while (textIndex < text.length()) {

            if (caseSensitive && (pattern.charAt(patternIndex) == text.charAt(textIndex)) ||
                    !caseSensitive && (lowerCasePattern.charAt(patternIndex) == lowerCaseText.charAt(textIndex))) {

                if (patternIndex == 0) {
                    String wordContainingPattern = findWordContainingPattern(textIndex, text);
                    wordsContainingPattern.add(wordContainingPattern);

                    textIndex += pattern.length();
                    patternIndex = pattern.length() - 1;

                } else {
                    textIndex--;
                    patternIndex--;
                }

            } else {
                currentCharInText = (caseSensitive) ? text.charAt(textIndex) : lowerCaseText.charAt(textIndex);

                textIndex += pattern.length() - Math.min(patternIndex, 1 + last[currentCharInText]);
                patternIndex = pattern.length() - 1;
            }
        }

        return wordsContainingPattern;
    }

    private static String findWordContainingPattern(int currentIndex, String text) {

        while ((text.charAt(currentIndex - 1) > 65) &&
                ((text.charAt(currentIndex - 1) < 90) || (text.charAt(currentIndex - 1) > 97)) &&
                ((text.charAt(currentIndex - 1) < 122))) {

            currentIndex--;
        }

        String wordContainingPattern = "";

        while ((text.charAt(currentIndex) > 65) &&
                ((text.charAt(currentIndex) < 90) || (text.charAt(currentIndex) > 97)) &&
                ((text.charAt(currentIndex) < 122))) {

            wordContainingPattern = new StringBuilder(wordContainingPattern).append(text.charAt(currentIndex)).toString();
            currentIndex++;
        }

        return wordContainingPattern;
    }
}