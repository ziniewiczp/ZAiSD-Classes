import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileManager {

    public static String readFile(String filename) {

        try {
            return new String(Files.readAllBytes(Paths.get(filename)));

        } catch(IOException e) {

            e.printStackTrace();
            return null;
        }
    }
}