public class Main {

    public static void main(String[] args) {

        int[][] graphArray = FileManager.buildGraphArrayFromFile("graph.txt", 20);
        Algorithm.floydWarshall(graphArray, 20);
        Algorithm.printDistanceAndPathBetweenNodes(1, 20);
    }
}
