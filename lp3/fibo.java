import java.util.*;
public class fibo {


    
        // Iterative method to calculate Fibonacci sequence up to n
        public static void fibonacciIterative(int n) {
            int a = 0, b = 1;
    
            System.out.print("Fibonacci sequence (Iterative): ");
            for (int i = 0; i <= n; i++) {
                if (i <= 1) {
                    System.out.print(i + " ");
                } else {
                    int c = a + b;
                    System.out.print(c + " ");
                    a = b;
                    b = c;
                }
            }
            System.out.println();
        }
    
        // Recursive method to calculate nth Fibonacci number
        public static int fibonacciRecursive(int n) {
            if (n <= 1) {
                return n;
            }
            return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
        }
    
        // Recursive method to print Fibonacci sequence up to n
        public static void printFibonacciRecursive(int n) {
            System.out.print("Fibonacci sequence (Recursive): ");
            for (int i = 0; i <= n; i++) {
                System.out.print(fibonacciRecursive(i) + " ");
            }
            System.out.println();
        }
    
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
    
            System.out.print("Enter the position (n) up to which you want the Fibonacci sequence: ");
            int n = scanner.nextInt();
    
            // Print Fibonacci sequence up to nth position using iterative method
            fibonacciIterative(n);
             System.out.println(" Fibonacci number (non-recursive ) : "+fibonacciRecursive(n));
            // Print Fibonacci sequence up to nth position using recursive method
            printFibonacciRecursive(n);
            System.out.println(" Fibonacci number (recursive ) : "+fibonacciRecursive(n));

            scanner.close();
        }
}