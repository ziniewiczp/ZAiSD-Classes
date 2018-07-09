public class Main {

    public static void main(String[] args) {

        double[][] graph = FileManager.buildGraphArrayFromFile("graph.txt", 5);

        double maxFlowBetweenNodes = Algorithm.fordFulkerson(graph, 0, 5);
        System.out.println("Maximum flow between 0 and 5: " + maxFlowBetweenNodes);

        Algorithm.fordFulkersonFindMaxTarget(graph, 0);
    }
}
