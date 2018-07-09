import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Reader {

    public static ArrayList<Point> readCSV() {

        String csvFile = "points.csv";
        String line = "";
        String separator = ",";
        String[] parsedLine;

        ArrayList<Point> points = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {

                if(!line.equals("")) {
                    parsedLine = line.split(separator);

                    Point point = new Point();
                    point.setX(Float.valueOf(parsedLine[0]));
                    point.setY(Float.valueOf(parsedLine[1]));

                    points.add(point);
                }
            }

            return points;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
