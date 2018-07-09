import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;


public class Main {

    public static void main(String[] args) {

        ArrayList<Point> points = Reader.readCSV();

        if(points != null) {
            int mostRightPointIndex = Sorter.getMostRightPointIndex(points);
            Collections.swap(points, mostRightPointIndex,0);
            points = Sorter.sort(points);

            Stack<Point> convexHull = Algorithm.findConvexHull(points);

            VisualizationFrame.initializeVisualization(points, convexHull);
        }
    }
}
