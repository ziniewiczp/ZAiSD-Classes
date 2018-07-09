import javax.swing.JFrame;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import java.util.ArrayList;
import java.util.Stack;

public class VisualizationFrame extends JFrame {

    public VisualizationFrame(ArrayList<Point> points, Stack<Point> convexHull) {
        super("Graham scan - finding convex hull");

        mxGraph graph = new mxGraph();
        Object parent = graph.getDefaultParent();

        graph.getModel().beginUpdate();
        try {
            for(Point point : points) {

                graph.insertVertex(parent, null,"", point.getX()*3, point.getY()*3, 1,
                        1);
            }

            while(!convexHull.empty()) {
                Point point = convexHull.pop();

                graph.insertVertex(parent, null, "", point.getX()*3, point.getY()*3, 5,
                        5);

                System.out.println(point);
            }
        }
        finally {
            graph.getModel().endUpdate();
        }

        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        getContentPane().add(graphComponent);
    }

    public static void initializeVisualization(ArrayList<Point> points, Stack<Point> convexHull) {
        VisualizationFrame frame = new VisualizationFrame(points, convexHull);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 725);
        frame.setVisible(true);
    }

}
