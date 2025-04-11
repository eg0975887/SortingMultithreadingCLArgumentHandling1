public class Main {

    public static void main(String[] args) {

        // Check if exactly 5 numbers are provided
        if (args.length != 5) {
            System.out.println("Error: Please provide exactly five integer arguments.");
            return;
        }

        int[] argsNumbers = new int[5];

        try {
            // Parse the command line arguments as integers
            for (int i = 0; i < args.length; i++) {
                argsNumbers[i] = Integer.parseInt(args[i]);
            }
        } catch (NumberFormatException e) {
            // If parsing fails, print an error message and exit
            System.out.println("Error: All arguments must be valid integers.");
            return;
        }

        // Create the two threads: one for printing unsorted numbers, another for sorted numbers
        Thread unsortedThread = new Thread(new UnsortedPrinter(argsNumbers));
        Thread sortedThread = new Thread(new SortedPrinter(argsNumbers));

        // Start both threads
        unsortedThread.start();
        sortedThread.start();

        // Wait for both threads to finish before proceeding with sum calculation
        try {
            unsortedThread.join(); // Wait for unsorted thread to finish
            sortedThread.join();   // Wait for sorted thread to finish
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Calculate the sum of the numbers
        int sum = 0;
        for (int val : argsNumbers) {
            sum += val;
        }
        // Print the sum of numbers
        System.out.println("Sum of Numbers: " + sum);
    }
}

// Thread class to print the unsorted array
class UnsortedPrinter implements Runnable {
    private final int[] arr;

    public UnsortedPrinter(int[] arr) {
        this.arr = arr.clone(); // Clone the array to prevent changes from affecting original array
    }

    @Override
    public void run() {
        try {
            // Adding a small delay to simulate multithreading and allow interleaving
            Thread.sleep(10); // Sleep for 10 milliseconds
        } catch (InterruptedException e) {
            e.printStackTrace(); // Handle the interrupted exception if sleep is interrupted
        }

        // Construct a string to print the unsorted numbers
        StringBuilder sb = new StringBuilder("Unsorted Numbers: ");
        for (int num : arr) {
            sb.append(num).append(" ");
        }
        // Output the unsorted numbers
        System.out.println(sb.toString().trim());
    }
}

// Thread class to print the sorted array
class SortedPrinter implements Runnable {
    private final int[] arr;

    public SortedPrinter(int[] arr) {
        this.arr = arr.clone(); // Clone the array to avoid modification of the original array
        bubbleSort(this.arr); // Sort the array before printing
    }

    // Bubble sort algorithm to sort the array
    private void bubbleSort(int[] a) {
        int n = a.length;
        // Perform the bubble sort
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                // Swap elements if they are in the wrong order
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
    }

    @Override
    public void run() {
        try {
            // Adding a small delay to simulate multithreading and allow interleaving
            Thread.sleep(10); // Sleep for 10 milliseconds
        } catch (InterruptedException e) {
            e.printStackTrace(); // Handle the interrupted exception if sleep is interrupted
        }

        // Construct a string to print the sorted numbers
        StringBuilder sb = new StringBuilder("Sorted Numbers: ");
        for (int num : arr) {
            sb.append(num).append(" ");
        }
        // Output the sorted numbers
        System.out.println(sb.toString().trim());
    }
}
