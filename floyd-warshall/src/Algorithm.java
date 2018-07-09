import java.util.ArrayList;

public class Algorithm {

    private static int[][] distance;
    private static int[][] predecessor;

    public static void floydWarshall(int[][] graphArray, int graphSize) {

        distance = new int[graphSize + 1][graphSize + 1];
        predecessor = new int[graphSize + 1][graphSize + 1];

        for (int v1 = 1; v1 < graphSize + 1; v1++) {
            for (int v2 = 1; v2 < graphSize + 1; v2++) {
                distance[v1][v2] = 999999;
                predecessor[v1][v2] = -1;
            }
            distance[v1][v1] = 0;
        }

        for (int v1 = 1; v1 < graphSize + 1; v1++) {
            for (int v2 = 1; v2 < graphSize + 1; v2++) {
                if (graphArray[v1][v2] != 0) {
                    distance[v1][v2] = graphArray[v1][v2];
                    predecessor[v1][v2] = v1;
                }
            }
        }

        for (int u = 1; u < graphSize + 1; u++) {
            for (int v1 = 1; v1 < graphSize + 1; v1++) {
                for (int v2 = 1; v2 < graphSize + 1; v2++) {

                    if (distance[v1][v2] > distance[v1][u] + distance[u][v2]) {
                        distance[v1][v2] = distance[v1][u] + distance[u][v2];
                        predecessor[v1][v2] = predecessor[u][v2];
                    }
                }
            }
        }
    }

    public static void printDistanceAndPathBetweenNodes(int v1, int v2) {

        System.out.println("Distance between nodes " + v1 + " and " + v2 + ": " + distance[1][20]);
        System.out.println();


        ArrayList<Integer> path = new ArrayList<>();
        int u = predecessor[v1][v2];

        path.add(v2);
        path.add(u);

        while (u != v1) {
            u = predecessor[v1][u];
            path.add(u);
        }

        System.out.println("Path between nodes " + v1 + " and " + v2 + ":");

        for (int i = path.size() - 1; i > 0; i--) {
            System.out.print(path.get(i) + " -(" + distance[path.get(i)][path.get(i - 1)] + ")-> ");
        }

        System.out.print(path.get(0));
    }
}
