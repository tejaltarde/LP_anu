import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Node class representing each character and its frequency in the Huffman Tree
class HuffmanNode {
    char character; // Character being represented in the node
    int frequency; // Frequency of the character
    HuffmanNode left, right; // Left and right children of the node in the Huffman Tree

    // Constructor to initialize a HuffmanNode with a character and its frequency
    public HuffmanNode(char character, int frequency) {
        this.character = character;
        this.frequency = frequency;
    }
}

public class HuffmanEncoding {

    // Method to build the Huffman Tree and generate the Huffman codes for each
    // character
    public static Map<Character, String> buildHuffmanTree(Map<Character, Integer> frequencies) {
        // Priority queue to store nodes of the Huffman Tree, sorted by frequency
        PriorityQueue<HuffmanNode> queue = new PriorityQueue<>((a, b) -> a.frequency - b.frequency);

        // Step 1: Create a leaf node for each character and add it to the priority
        // queue
        for (Map.Entry<Character, Integer> entry : frequencies.entrySet()) {
            queue.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        // Step 2: Build the Huffman Tree by combining nodes with the lowest frequency
        while (queue.size() > 1) {
            // Remove two nodes with the lowest frequency from the queue
            HuffmanNode left = queue.poll();
            HuffmanNode right = queue.poll();

            // Create a new internal node with a combined frequency
            HuffmanNode parent = new HuffmanNode('\0', left.frequency + right.frequency);
            parent.left = left;
            parent.right = right;

            // Add the new node back to the priority queue
            queue.add(parent);
        }

        // Step 3: Traverse the Huffman Tree to generate codes for each character
        Map<Character, String> huffmanCodes = new HashMap<>();
        generateCodes(queue.poll(), "", huffmanCodes); // Start from the root of the Huffman Tree
        return huffmanCodes;
    }

    // Recursive function to generate codes for each character
    private static void generateCodes(HuffmanNode node, String code, Map<Character, String> huffmanCodes) {
        // Base case: if the node is null, return
        if (node == null)
            return;

        // If the node is a leaf node, store the character and its code
        if (node.left == null && node.right == null) {
            huffmanCodes.put(node.character, code);
        }

        // Traverse the left and right children, appending "0" for left and "1" for
        // right
        generateCodes(node.left, code + "0", huffmanCodes);
        generateCodes(node.right, code + "1", huffmanCodes);
    }

    public static void main(String[] args) {
        // Input from user
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter text to encode: ");
        String text = scanner.nextLine();
        scanner.close();

        // Step 1: Calculate frequency of each character in the input text
        Map<Character, Integer> frequencies = new HashMap<>();
        for (char character : text.toCharArray()) {
            frequencies.put(character, frequencies.getOrDefault(character, 0) + 1);
        }

        // Step 2: Build the Huffman Tree and generate the Huffman codes
        Map<Character, String> huffmanCodes = buildHuffmanTree(frequencies);

        // Output the generated Huffman codes
        System.out.println("Huffman Codes: " + huffmanCodes);

        // Step 3: Encode the input text using the generated Huffman codes
        StringBuilder encodedText = new StringBuilder();
        for (char character : text.toCharArray()) {
            encodedText.append(huffmanCodes.get(character));
        }

        // Output the encoded text
        System.out.println("Encoded Text: " + encodedText);
    }
}
