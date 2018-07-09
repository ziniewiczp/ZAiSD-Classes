import java.util.*;

public class Algorithm {

    public static Stack<Point> findConvexHull(ArrayList<Point> points) {
        Stack<Point> convexHull = new Stack<>();
        int i = 2;
        Point PT1, PT2;

        convexHull.push(points.get(0));
        convexHull.push(points.get(1));

        while(i < points.size()) {

            PT1 = convexHull.peek();

            if(PT1.equals(points.get(0))) {
                convexHull.push(points.get(i));
                i++;
            }

            PT2 = convexHull.get(convexHull.size()-2);

            if(Sorter.isLeft(points.get(i), PT2, PT1)) {
                convexHull.push(points.get(i));
                i++;

            } else {
                convexHull.pop();
            }
        }

        return convexHull;
    }
}
