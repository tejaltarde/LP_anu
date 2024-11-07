import heapq
from collections import Counter

# Node class for the Huffman Tree
class HuffmanNode:
    def __init__(self, char, freq):
        self.char = char  # character
        self.freq = freq  # frequency of the character
        self.left = None  # left child
        self.right = None  # right child

    # Comparator for the priority queue
    def __lt__(self, other):
        return self.freq < other.freq

# Function to build the Huffman tree
def build_huffman_tree(freq_map):
    # Create a priority queue (min-heap) with all characters and their frequencies
    heap = []
    for char, freq in freq_map.items():
        heapq.heappush(heap, HuffmanNode(char, freq))

    # Merge nodes to build the Huffman Tree
    while len(heap) > 1:
        # Extract the two nodes with the lowest frequencies
        left = heapq.heappop(heap)
        right = heapq.heappop(heap)

        # Create a new internal node with frequency equal to the sum of both nodes
        merged = HuffmanNode(None, left.freq + right.freq)
        merged.left = left
        merged.right = right

        # Push the merged node back into the heap
        heapq.heappush(heap, merged)

    # The final node in the heap is the root of the Huffman Tree
    return heap[0]

# Function to generate the Huffman codes
def generate_codes(node, current_code, huffman_codes):
    if node is None:
        return

    # If a leaf node is reached, store the character and its code
    if node.char is not None:
        huffman_codes[node.char] = current_code

    # Traverse the left subtree with '0' added to the current code
    generate_codes(node.left, current_code + "0", huffman_codes)

    # Traverse the right subtree with '1' added to the current code
    generate_codes(node.right, current_code + "1", huffman_codes)

# Function to encode the input text
def huffman_encode(text):
    # Step 1: Calculate frequency of each character
    freq_map = Counter(text)

    # Step 2: Build the Huffman tree
    huffman_tree_root = build_huffman_tree(freq_map)

    # Step 3: Generate Huffman codes by traversing the tree
    huffman_codes = {}
    generate_codes(huffman_tree_root, "", huffman_codes)

    # Step 4: Encode the input text
    encoded_text = ''.join(huffman_codes[char] for char in text)

    return encoded_text, huffman_codes, huffman_tree_root

# Function to decode the encoded text using the Huffman tree
def huffman_decode(encoded_text, huffman_tree_root):
    decoded_text = ""
    current_node = huffman_tree_root

    for bit in encoded_text:
        if bit == '0':
            current_node = current_node.left
        else:
            current_node = current_node.right

        # If a leaf node is reached, append the character to the decoded text
        if current_node.char is not None:
            decoded_text += current_node.char
            current_node = huffman_tree_root  # Go back to the root

    return decoded_text

# Main function to run the Huffman Encoding
if __name__ == "__main__":
    # Taking user input
    text = input("Enter the text to be encoded: ")

    # Huffman encoding
    encoded_text, huffman_codes, huffman_tree_root = huffman_encode(text)

    print("\nOriginal text:", text)
    print("Huffman Codes:", huffman_codes)
    print("Encoded text:", encoded_text)

    # Huffman decoding
    decoded_text = huffman_decode(encoded_text, huffman_tree_root)
    print("Decoded text:", decoded_text)
