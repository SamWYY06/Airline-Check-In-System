package aerocheck;
import java.util.*;

public class Aerocheck {

    public static void main(String[] args) {

        checkin checkin = new checkin();
        Scanner scanner = new Scanner(System.in);

        passenger p = null;
        passenger g = null;
        
        // Declare the baggage object correctly
        int numberOfBags = 0;
        double totalWeight = 0.0;
        boolean hasOversized = false;
        
        baggage baggage = new baggage(numberOfBags, totalWeight, hasOversized);

        int option = 0;

        // GUI Header
        System.out.println("------------------------------------");
        System.out.println("\tWelcome to AeroCheck");
        System.out.println("------------------------------------");

        do {
            System.out.println("How would you like to check in:");
            System.out.println("1. Counter check-in");
            System.out.println("2. Kiosk check-in");
            System.out.println("3. Exit");
            System.out.print("Option: ");

            if (scanner.hasNextInt()) {
                option = scanner.nextInt();
                scanner.nextLine(); // consume newline
            } else {
                System.out.println("Invalid input. Please enter a number between 1 and 3.");
                scanner.next(); // Clear invalid input
                continue;
            }

            switch (option) {
                case 1:
                    System.out.println("Please wait a moment for counter check-in...");
                    for (int i = 5; i >= 1; i--) {
                        System.out.println("Finding an available counter... " + i + " seconds remaining");
                        try {
                            Thread.sleep(1000); // Wait 1 second
                        } catch (InterruptedException e) {
                            System.out.println("Timer interrupted");
                        }
                    }

                    Random rand = new Random();
                    int counterNumber = rand.nextInt(10) + 1; // generates number from 1 to 10
                    System.out.println("Please head to counter " + counterNumber + " to check in!");
                    break;

                case 2:
                    System.out.println("1. Passenger Check-in");
                    System.out.println("2. Group Check-in");
                    System.out.print("Option: ");

                    int checkinoption = 0;
                    if (scanner.hasNextInt()) {
                        checkinoption = scanner.nextInt();
                        scanner.nextLine(); // consume newline
                    } else {
                        System.out.println("Invalid input. Please enter 1 or 2.");
                        scanner.next(); // Clear invalid input
                        continue;
                    }

                    switch (checkinoption) {
                        case 1:
                            p = checkin.collectPassengerInfo();
                            System.out.print("Would you like to check in baggage? (yes/no): ");
                            String soloBag = scanner.nextLine();
                            if (soloBag.equalsIgnoreCase("yes")) {
                                baggage.collectBaggageInfo(scanner);
                            }

                            System.out.println("=========CHECK IN SUCCESSFUL==========");
                            System.out.println("Passenger Information:");
                            System.out.println("Name: " + p.getpName());
                            System.out.println("ID: " + p.getpID());
                            System.out.println("Passport: " + p.getPassportNum());
                            System.out.println("Phone Number: " + p.getPhoneNum());
                            System.out.println("Special Assistance Needed?: " + (p.getSpecialNeeds() ? "Yes" : "No"));

                            if (p.getSpecialNeeds()) {
                                System.out.println("Special Accommodations: " + String.join(", ", p.getSpecialNeedsTypes()));
                                System.out.println("Number of Units Required Each: " + p.getassistanceQty());
                            }
                            if (soloBag.equalsIgnoreCase("yes")) {
                                baggage.displayBaggageInfo();
                            }
                            System.out.println("=====================================");
                            break;

                        case 2:
                            g = checkin.collectGroupInfo();
                            System.out.print("Would your group like to check in baggage? (Yes/No): ");
                            String groupBag = scanner.nextLine();
                            if (groupBag.equalsIgnoreCase("yes")) {
                                baggage.collectBaggageInfo(scanner);
                            }

                            System.out.println("=========CHECK IN SUCCESSFUL==========");
                            System.out.println("Group Information:");
                            System.out.println("Representative Name: " + g.getRepName());
                            System.out.println("Representative ID: " + g.getRepID());
                            System.out.println("Representative Passport: " + g.getRepPassportNum());
                            System.out.println("Representative Phone Number: " + g.getRepPhoneNum());
                            System.out.println("Number of Pax: " + g.getPax());
                            System.out.println("Special Assistance Needed?: " + (g.getGSpecialNeeds() ? "Yes" : "No"));

                            if (g.getGSpecialNeeds()) {
                                System.out.println("Special Accommodations: " + String.join(", ", g.getSpecialNeedsTypes()));
                                System.out.println("Number of Units Required Each: " + g.getGassistanceQty());
                            }
                            if (groupBag.equalsIgnoreCase("yes")) {
                                baggage.displayBaggageInfo();
                            }
                            System.out.println("=====================================");
                            break;

                        default:
                            System.out.println("Invalid option. Please enter 1 or 2.");
                    }
                    break;

                case 3:
                    System.out.println("Thank you for using AeroCheck, have a safe flight!");
                    break;

                default:
                    System.out.println("Invalid option. Please enter a number between 1 and 3.");
            }

        } while (option != 3);

        scanner.close();
    }
}
