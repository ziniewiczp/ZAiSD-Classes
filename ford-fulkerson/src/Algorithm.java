import java.util.ArrayList;

public class Algorithm {

    public static double fordFulkerson(double[][] graph, int s, int t) {

        double maxFlow = 0;
        double flowGraph[][] = new double[graph.length][graph.length];
        double residualGraph[][];
        boolean maxFlowForPathReached;

        if(s == t)
            return 0;

        for(int u = 0; u < graph.length; u++) {
            for(int v = 0; v < graph.length; v++) {
                flowGraph[u][v] = 0;
            }
        }

        residualGraph = graph.clone();

        ArrayList<ArrayList<Integer>> allPathsBetweenNodes = findAllPathsBetweenNodes(graph, s, t);

        for( ArrayList<Integer> path : allPathsBetweenNodes ) {
            double maxFlowForPath = 999999;
            maxFlowForPathReached = false;

            for (int i = 0; i < path.size() - 1; i++) {
                if( residualGraph[path.get(i)][path.get(i+1)] > 0) {
                    if (residualGraph[path.get(i)][path.get(i + 1)] < maxFlowForPath)
                        maxFlowForPath = residualGraph[path.get(i)][path.get(i + 1)];
                } else
                    maxFlowForPathReached = true;
            }

            if( maxFlowForPathReached )
                continue;

            for (int i = 0; i < path.size() - 1; i++) {
                flowGraph[path.get(i)][path.get(i+1)] = flowGraph[path.get(i)][path.get(i+1)] + maxFlowForPath;
                flowGraph[path.get(i+1)][path.get(i)] = flowGraph[path.get(i+1)][path.get(i)] - maxFlowForPath;

                residualGraph[path.get(i)][path.get(i+1)] = residualGraph[path.get(i)][path.get(i+1)] - maxFlowForPath;
                residualGraph[path.get(i+1)][path.get(i)] = residualGraph[path.get(i+1)][path.get(i)] + maxFlowForPath;
            }

            maxFlow += maxFlowForPath;
        }
        return maxFlow;
    }

    public static void fordFulkersonFindMaxTarget(double[][] graph, int source) {

        double maxFlow = 0;
        double currentFlow;
        int bestTarget = source;

        for(int i = 0; i < graph.length; i++) {

            currentFlow = fordFulkerson(graph, source, i);

            if(currentFlow > maxFlow ) {
                maxFlow = currentFlow;
                bestTarget = i;
            }
        }

        System.out.println("Best target: " + bestTarget + "(with flow " + maxFlow + ")");
    }

    private static ArrayList<ArrayList<Integer>> findAllPathsBetweenNodes(double[][] graph, int source, int target) {
        // initialization
        ArrayList<ArrayList<Integer>> allPaths = new ArrayList<>();
        ArrayList<Integer> path = new ArrayList<>();
        boolean[] visited = new boolean[graph.length];

        for (int i = 0; i < graph.length; i++) {
            visited[i] = false;
        }

        // invoking actual method
        actualFindAllPathsBetweenNodes(graph, source, target, visited, path, allPaths);

        return allPaths;
    }

    private static void actualFindAllPathsBetweenNodes(double[][] graph, int current, int destination, boolean visited[],
                                                 ArrayList<Integer> path,  ArrayList<ArrayList<Integer>> allPaths) {

        visited[current] = true;
        path.add(current);

        if(current == destination) {
            allPaths.add(new ArrayList<>(path));

        } else {
            for(int i = 0; i < graph.length; i++) {
                if(graph[current][i] > 0 && !visited[i]) {
                    actualFindAllPathsBetweenNodes(graph, i, destination, visited, path, allPaths);
                }
            }
        }

        visited[current] = false;
        path.remove((Integer) current);
    }
}
