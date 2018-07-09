import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

public class FileManager {

    public static Map<Character, Integer> createSymbolOccurrenceMapFromFile() {

        Map<Character, Integer> symbolOccurrenceMap = new HashMap<>();
        int data;

        try (InputStream in = new FileInputStream("seneca.txt")) {

            while ((data = in.read()) != -1) {
                if (symbolOccurrenceMap.containsKey((char) data))
                    symbolOccurrenceMap.replace((char) data, symbolOccurrenceMap.get((char) data) + 1);
                else
                    symbolOccurrenceMap.put((char) data, 1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return symbolOccurrenceMap;
    }

    public static void encodeAndSaveToFile(HashMap<Character, String> codesMap) {

        try (InputStream fis = new FileInputStream("seneca.txt");
             FileOutputStream fos = new FileOutputStream("encoded_seneca.txt")) {

            int data;

            String currentSymbolCode;
            BitSet bitBuffer = new BitSet();
            int bitIndex = 0;

            while ((data = fis.read()) != -1) {

                currentSymbolCode = codesMap.get((char) data);

                for (int i = 0; i < currentSymbolCode.length(); i++) {
                    if (currentSymbolCode.charAt(i) == '1')
                        bitBuffer.set(bitIndex);
                    else
                        bitBuffer.clear(bitIndex);

                    bitIndex++;
                }
            }

            byte[] byteArray = bitBuffer.toByteArray();

            fos.write(byteArray);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void decodeAndSaveToFile(Tree tree) {

        try (BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream("decoded_seneca.txt"))) {

            Tree currentTree = tree;

            byte[] bytes = Files.readAllBytes(Paths.get("encoded_seneca.txt"));
            BitSet bitBuffer = BitSet.valueOf(bytes);

            for (int i = 0; i < bitBuffer.length(); i++) {
                Node node = (Node) currentTree;

                if (!bitBuffer.get(i))
                    currentTree = node.left;
                else
                    currentTree = node.right;

                if (currentTree instanceof Leaf) {
                    Leaf leaf = (Leaf) currentTree;
                    fos.write(leaf.value);

                    currentTree = tree;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
