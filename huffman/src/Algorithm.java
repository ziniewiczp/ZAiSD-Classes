import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;

public class Algorithm {

    public static PriorityQueue<Tree> initializeTree(Map<Character, Integer> symbolOccurrenceMap) {
        PriorityQueue<Tree> trees = new PriorityQueue<Tree>();

        Iterator iterator = symbolOccurrenceMap.entrySet().iterator();

        while( iterator.hasNext() ) {
            Map.Entry entry = (Map.Entry) iterator.next();

            trees.offer(new Leaf((int) entry.getValue(), (char) entry.getKey()));
        }

        return trees;
    }

    public static Tree buildTree(PriorityQueue<Tree> trees) {
        Tree finalTree;

        if( trees.size() == 2 ) {
            Tree a = trees.poll();
            Tree b = trees.poll();

            finalTree = new Node(a, b);

        } else {
            Tree a = trees.poll();
            Tree b = trees.poll();

            trees.offer(new Node(a, b));

            finalTree = buildTree(trees);
        }

        return finalTree;
    }

    public static HashMap<Character, String> generateCodesMap(Tree tree, StringBuffer prefix, HashMap<Character, String> codesMap) {
        if (tree instanceof Leaf) {
            Leaf leaf = (Leaf) tree;

            codesMap.put(leaf.value, prefix.toString());

        } else if (tree instanceof Node) {
            Node node = (Node)tree;

            // traverse left
            prefix.append('0');
            generateCodesMap(node.left, prefix, codesMap);
            prefix.deleteCharAt(prefix.length()-1);

            // traverse right
            prefix.append('1');
            generateCodesMap(node.right, prefix, codesMap);
            prefix.deleteCharAt(prefix.length()-1);
        }

        return codesMap;
    }
}
