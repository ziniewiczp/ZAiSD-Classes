import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {

        Map<Character, Integer> symbolOccurrenceMap = FileManager.createSymbolOccurrenceMapFromFile();

        PriorityQueue<Tree> treesQueue = Algorithm.initializeTree(symbolOccurrenceMap);

        Tree huffmanTree = Algorithm.buildTree(treesQueue);

        HashMap<Character, String> codesMap = new HashMap<>();
        codesMap = Algorithm.generateCodesMap(huffmanTree, new StringBuffer(), codesMap);

        FileManager.encodeAndSaveToFile(codesMap);

        FileManager.decodeAndSaveToFile(huffmanTree);

        File originalFile = new File("seneca.txt");
        File encodedFile = new File("encoded_seneca.txt");
        File decodedFile = new File("decoded_seneca.txt");

        double compressionGrade = (originalFile.length() - encodedFile.length());
        compressionGrade = compressionGrade/originalFile.length();

        System.out.println("Compression ratio: " + compressionGrade);
        System.out.println();
        System.out.println("Original file size: " + originalFile.length());
        System.out.println("Encoded file size: " + encodedFile.length());
        System.out.println("Decoded file size: " + decodedFile.length());
    }
}
