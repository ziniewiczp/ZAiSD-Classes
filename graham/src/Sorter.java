import java.util.ArrayList;
import java.util.Collections;

public class Sorter {
    public static int getMostRightPointIndex(ArrayList<Point> points) {
        int mostRightPointIndex = 0;

        for(int i = 1; i < points.size(); i++) {
            if(points.get(i).getX() > points.get(mostRightPointIndex).getX())
                mostRightPointIndex = i;

            else if( (points.get(i).getX() == points.get(mostRightPointIndex).getX()) && points.get(i).getY() > points.get(mostRightPointIndex).getY())
                mostRightPointIndex = i;
        }

        return mostRightPointIndex;
    }

    public static boolean isLeft(Point a, Point b, Point p) {
        float d = (p.getX() - a.getX()) * (b.getY() - a.getY()) - (p.getY() - a.getY()) * (b.getX() - a.getX());

        return d < 0;
    }

    public static ArrayList<Point> sort(ArrayList<Point> points) {

        int elementsLeftToSort = points.size();

        do {
            for(int i = 1; i < elementsLeftToSort-1; i++) {
                if( !isLeft(points.get(i), points.get(i+1), points.get(0))) {
                    Collections.swap(points, i,i+1);
                }
            }
            elementsLeftToSort--;

        } while (elementsLeftToSort > 1);

        return points;
    }
}
