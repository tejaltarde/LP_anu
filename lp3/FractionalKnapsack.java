
import java.util.Arrays;
import java.util.Scanner;

// Class representing an item with weight, value, and value-to-weight ratio
class Item {
    int weight;
    int value;
    double ratio;

    // Constructor to initialize weight, value, and calculate the ratio
    public Item(int weight, int value) {
        this.weight = weight;
        this.value = value;
        this.ratio = (double) value / weight; // Calculate value-to-weight ratio
    }
}

public class FractionalKnapsack {

    // Method to calculate the maximum value the knapsack can hold
    public static double maximizeValue(Item[] items, int capacity) {
        // Sort items by value-to-weight ratio in descending order
        Arrays.sort(items, (a, b) -> Double.compare(b.ratio, a.ratio));

        double totalValue = 0; // Variable to keep track of the total value

        // Loop through each item and add to knapsack if possible
        for (Item item : items) {
            if (capacity > 0 && item.weight <= capacity) {
                // If the item can fit fully within remaining capacity, add it completely
                capacity -= item.weight;
                totalValue += item.value;
            } else {
                // If the item can't fit fully, take a fraction of it
                double fraction = (double) capacity / item.weight; // Fraction of the item that can fit
                totalValue += item.value * fraction; // Add the fractional value to total
                break; // Knapsack is full, break out of the loop
            }
        }
        return totalValue; // Return the maximum value that can be achieved
    }

    // Main method to run the program
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input the number of items
        System.out.print("Enter the number of items: ");
        int n = scanner.nextInt();

        Item[] items = new Item[n]; // Array to hold items
        System.out.println("Enter the weight and value of each item:");

        // Input weight and value for each item and store in the array
        for (int i = 0; i < n; i++) {
            System.out.print("Item " + (i + 1) + " weight: ");
            int weight = scanner.nextInt();
            System.out.print("Item " + (i + 1) + " value: ");
            int value = scanner.nextInt();
            items[i] = new Item(weight, value); // Create item and add to array
        }

        // Input the knapsack capacity
        System.out.print("Enter the capacity of the knapsack: ");
        int capacity = scanner.nextInt();
        scanner.close();

        // Calculate and display the maximum value achievable
        double maxValue = maximizeValue(items, capacity);
        System.out.println("Maximum value that can be obtained: " + maxValue);
    }
}
