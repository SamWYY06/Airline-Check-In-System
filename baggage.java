package aerocheck;
import java.util.*;

public class baggage {
    private int numberOfBags;
    private double totalWeight;
    private boolean hasOversized;
    private String baggageTag;

    // Constructor to initialize baggage info
    public baggage(int numberOfBags, double totalWeight, boolean hasOversized) {
        this.numberOfBags = numberOfBags;
        this.totalWeight = totalWeight;
        this.hasOversized = hasOversized;
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

    // Method to collect baggage info
      public void collectBaggageInfo(Scanner scanner) {
        // Limit number of bags to 5
        int maxBags = 5;

        System.out.print("Enter total number of bags (Max 5): ");
        numberOfBags = scanner.nextInt();
        if (numberOfBags > maxBags) {
            System.out.println("You can only check in a maximum of " + maxBags + " bags.");
            numberOfBags = maxBags;  // Set to max allowed if the input is greater
        }

        System.out.print("Enter total weight (kg): ");
        totalWeight = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        // Ask if there are oversized bags
        System.out.print("Are there any oversized bags? (Yes/No): ");
        String oversizedResponse = scanner.nextLine();
        hasOversized = oversizedResponse.equalsIgnoreCase("yes");

        // Generate a random baggage tag for tracking purposes and store it
        baggageTag = generateBaggageTag();
        System.out.println("Your baggage tag for tracking: " + baggageTag);
    }

    // Method to display baggage info
    public void displayBaggageInfo() {
        System.out.println("---------------------------------------");
        System.out.println("Baggage Information:");
        System.out.println("Number of Bags: " + numberOfBags);
        System.out.println("Total Weight: " + totalWeight + " kg");
        System.out.println("Oversized Bags: " + (hasOversized ? "Yes" : "No"));
        System.out.println("Baggage Tag: " + baggageTag); // Display the stored baggage tag
    }
}
