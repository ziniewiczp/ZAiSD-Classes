abstract class Tree implements Comparable<Tree> {
    public final int frequency;
    public Tree(int freq) { frequency = freq; }

    public int compareTo(Tree tree) {
        return frequency - tree.frequency;
    }
}

class Leaf extends Tree {
    public final char value;

    public Leaf(int freq, char val) {
        super(freq);
        value = val;
    }
}

class Node extends Tree {
    public final Tree left, right;

    public Node(Tree l, Tree r) {
        super(l.frequency + r.frequency);
        left = l;
        right = r;
    }
}