package aerocheck;
import java.util.*;

public class baggage {
    private int numberOfBags;
    private double totalWeight;
    private String baggageTag;
    private String bagWeight;
    public static boolean baggageScreened = false;

    // Constructor to initialize baggage info
    public baggage(int numberOfBags, double totalWeight) {
        this.numberOfBags = numberOfBags;
        this.totalWeight = totalWeight;
    }

    private String generateBaggageTag() {
        Random rand = new Random();
        StringBuilder tag = new StringBuilder();
        // Generate a random tag with a combination of letters and numbers
        for (int i = 0; i < 6; i++) {
            int randChoice = rand.nextInt(2); // Randomly choose between letter (0) and number (1)
            if (randChoice == 0) {
                char letter = (char) ('A' + rand.nextInt(26)); // Random letter from A to Z
                tag.append(letter);
            } else {
                int digit = rand.nextInt(10); // Random digit from 0 to 9
                tag.append(digit);
            }
        }
        return tag.toString();
    }

    // Method to collect solo baggage info with validation
    public void collectSoloBaggageInfo(Scanner scanner) {
        int maxBags = 2;
        
        // Loop until valid number of bags is entered
        while (true) {
            System.out.print("Enter total number of bags (Max 2): ");
            if (scanner.hasNextInt()) {
                numberOfBags = scanner.nextInt();
                if (numberOfBags <= maxBags && numberOfBags > 0) {
                    break;
                } else {
                    System.out.println("Error: You can only check in a maximum of " + maxBags + " bags.");
                }
            } else {
                System.out.println("Error: Please enter a valid number.");
                scanner.next(); // consume invalid input
            }
        }

        // Loop until valid weight is entered
        while (true) {
            System.out.print("Enter total weight (kg): ");
            if (scanner.hasNextDouble()) {
                totalWeight = scanner.nextDouble();
                if (totalWeight >= 0) {
                    break; 
                } else {
                    System.out.println("Error: Weight cannot be negative.");
                }
            } else {
                System.out.println("Error: Please enter a valid weight in numbers.");
                scanner.next(); // consume invalid input
            }
        }
        scanner.nextLine(); // consume newline after number input

        if (totalWeight > (23 * numberOfBags)) {
            bagWeight = "Overweight";
        } 
        else {
            bagWeight = "Normal";
        }
        
        // Generate and display baggage tag
        baggageTag = generateBaggageTag();
    }
    
    // Method to collect group baggage info with validation
    public void collectGroupBaggageInfo(Scanner scanner) {
        int maxBags = 10;

        while (true) {
            System.out.print("Enter total number of bags for the group (Max " + maxBags + "): ");
            if (scanner.hasNextInt()) {
                numberOfBags = scanner.nextInt();
                if (numberOfBags > 0 && numberOfBags <= maxBags) {
                    break;
                } else {
                    System.out.println("Error: You can only check in a maximum of " + maxBags + " bags.");
                }
            } else {
                System.out.println("Error: Please enter a valid number.");
                scanner.next();
            }
        }

        while (true) {
            System.out.print("Enter total baggage weight(kg): ");
            if (scanner.hasNextDouble()) {
                totalWeight = scanner.nextDouble();
                if (totalWeight >= 0) {
                    break;
                } else {
                    System.out.println("Error: Weight cannot be negative.");
                }
            } else {
                System.out.println("Error: Please enter a valid weight in numbers.");
                scanner.next();
            }
        }
        scanner.nextLine();

        if (totalWeight > (23 * numberOfBags)) {
            bagWeight = "Overweight";
        } else {
            bagWeight = "Normal";
        }

        baggageTag = generateBaggageTag();
    }

        // Method to screen baggage
        public static void screenBaggage() {
            System.out.println("Please wait a moment for your baggage to be screened...");
            for (int i = 8; i >= 1; i--) {
                System.out.println("Baggage screening in session... " + i + " seconds remaining");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Timer interrupted");
                }
            }
            System.out.println("Screening completed! You may check in.");
            baggageScreened = true; // set flag to true
        }
    
    

    // Method to display baggage info
    public void displayBaggageInfo() {
        System.out.println("---------------------------------------");
        System.out.println("Baggage Information:");
        System.out.println("Number of Bags: " + numberOfBags);
        System.out.println("Total Weight: " + totalWeight + " kg");
        System.out.println("Size: " + bagWeight);
        System.out.println("Baggage Tag: " + baggageTag); // Display the stored baggage tag
    }
}
