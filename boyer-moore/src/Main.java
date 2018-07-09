import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        String text = FileManager.readFile("seneca.txt");
        String pattern = "omne";

        ArrayList<String> wordsContainingPattern = Algorithm.boyerMooreMatcher(text, pattern, false);

        System.out.println("Pattern \"" + pattern + "\" occurred " + wordsContainingPattern.size() + " times.");

        if( wordsContainingPattern.size() > 0 ) {
            System.out.println();
            System.out.println("Words containing pattern \"" + pattern + "\":");

            for (String word : wordsContainingPattern) {
                System.out.println(word);
            }
        }
    }
}