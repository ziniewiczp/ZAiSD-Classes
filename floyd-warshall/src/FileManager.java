import java.io.*;

public class FileManager {

    public static int[][] buildGraphArrayFromFile(String filename, int graphSize) {

        String line = "";
        String separator = "; ";
        String[] parsedLine;

        int[][] graphArray = new int[graphSize+1][graphSize+1];

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

            while ((line = br.readLine()) != null) {

                if(!line.equals("")) {
                    parsedLine = line.split(separator);

                    graphArray[Integer.valueOf(parsedLine[0])][Integer.valueOf(parsedLine[1])] = Integer.valueOf(parsedLine[2]);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return graphArray;
    }
}
