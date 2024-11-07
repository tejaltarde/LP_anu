import java.util.Scanner;

public class knapsack01 {

    // Function to solve the 0-1 Knapsack problem using dynamic programming
    public static int knapSack(int capacity, int[] weights, int[] values, int n) {
        // Create a DP table where dp[i][w] represents the maximum value that can be
        // obtained
        // with the first i items and a knapsack capacity of w
        int[][] dp = new int[n + 1][capacity + 1];

        // Build the DP table
        for (int i = 1; i <= n; i++) { // Loop over each item
            for (int w = 1; w <= capacity; w++) { // Loop over each capacity from 1 to capacity
                if (weights[i - 1] <= w) {
                    // If the item can fit in the current knapsack capacity w
                    // Decide to include or exclude the item to get the maximum value
                    dp[i][w] = Math.max(dp[i - 1][w], values[i - 1] + dp[i - 1][w - weights[i - 1]]);
                } else {
                    // If the item cannot fit, the maximum value is the same as excluding the item
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        // Return the maximum value that can be obtained with all items and the given
        // capacity
        return dp[n][capacity];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input the number of items
        System.out.print("Enter the number of items: ");
        int n = scanner.nextInt();

        int[] weights = new int[n]; // Array to store weights of items
        int[] values = new int[n]; // Array to store values of items

        // Input weights and values for each item
        System.out.println("Enter the weights and values of the items:");
        for (int i = 0; i < n; i++) {
            System.out.print("Weight of item " + (i + 1) + ": ");
            weights[i] = scanner.nextInt();
            System.out.print("Value of item " + (i + 1) + ": ");
            values[i] = scanner.nextInt();
        }

        // Input the knapsack capacity
        System.out.print("Enter the capacity of the knapsack: ");
        int capacity = scanner.nextInt();
        scanner.close();

        // Calculate and display the maximum value achievable
        int maxValue = knapSack(capacity, weights, values, n);
        System.out.println("Maximum value that can be obtained: " + maxValue);
    }
}
