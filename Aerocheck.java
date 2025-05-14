package aerocheck;

import java.util.*;

public class Aerocheck {

    public static void main(String[] args) {
        
        checkin checkin = new checkin();
        Scanner scanner = new Scanner(System.in);
        
        passenger p = null;
        passenger g = null;
        BoardingPass boardingPass = null;
        FlightDetails flightDetails = null;
        
        // Initialize baggage object
        int numberOfBags = 0;
        double totalWeight = 0.0;
        boolean hasOversized = false;
        baggage baggage = new baggage(numberOfBags, totalWeight);
        
        int option = 0;
       
        // GUI Header
        System.out.println("------------------------------------");
        System.out.println("\tWelcome to AeroCheck");
        System.out.println("------------------------------------");

        do {
            System.out.println("How would you like to check in:");
            System.out.println("1. Counter check-in");
            System.out.println("2. Kiosk check-in");
            System.out.println("3. Screen Baggage");
            System.out.println("4. Exit");
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
                    System.out.println("Thank you for using AeroCheck, have a safe flight!");
                    scanner.close();
                    return;

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
                            // Collect basic passenger information first
                            p = checkin.collectBasicPassengerInfo();
                            
                            // Generate and display flight details after verification
                            flightDetails = new FlightDetails();
                            flightDetails.displayFlightInfo();
                            
                            // Ask passenger if this is their flight
                            String proceedWithFlight = "";
                            while (true) {
                                System.out.print("\nIs the Flight Information displayed correct? (Yes/No): ");
                                proceedWithFlight = scanner.nextLine().trim();

                                if (proceedWithFlight.equalsIgnoreCase("yes") || proceedWithFlight.equalsIgnoreCase("no")) {
                                    break;
                                } else {
                                    System.out.println("Invalid input. Please type 'Yes' or 'No'.");
                                }
                            }
                            
                            if (!proceedWithFlight.equalsIgnoreCase("yes")) {
                                System.out.println("Please go to counter 10, Servia will assist you. Returning to main menu.");
                                scanner.close();
                                return;
                            }
                            
                            // Continue after confirmation
                            p = checkin.completePassengerInfo(p);
                            
                            // Check if passenger wants to check in baggage
                            String checkinbaggage = "";
                            while (true) {
                                System.out.print("Would you like to check in baggage? (Yes/No): ");
                                checkinbaggage = scanner.nextLine().trim();

                                if (checkinbaggage.equalsIgnoreCase("yes") || checkinbaggage.equalsIgnoreCase("no")) {
                                    break;
                                } else {
                                    System.out.println("Invalid input. Please enter 'Yes' or 'No'.");
                                }
                            }
                            if (checkinbaggage.equalsIgnoreCase("yes")) {
                                baggage.collectSoloBaggageInfo(scanner);
                            }
                            
                            System.out.println("=========CHECK IN SUCCESSFUL==========");
                            System.out.println("Passenger Information:");
                            System.out.println("Name: " + p.getpName());
                            System.out.println("ID: " + p.getpID());
                            System.out.println("Passport: " + p.getPassportNum());
                            System.out.println("Phone Number: " + p.getPhoneNum());
                            System.out.println("Baggage Screening Status: " + (baggage.baggageScreened ? "Screened" : "Not Screened"));
                            System.out.println("Special Assistance Needed?: " + (p.getSpecialNeeds() ? "Yes" : "No"));
                            
                            if (p.getSpecialNeeds()) {
                                System.out.println("Special Accommodations: " + p.getSpecialNeedsType());
                                System.out.println("Number of Units Required Each: " + p.getassistanceQty());
                            }
                            
                            // Display baggage info if checked in
                            if (checkinbaggage.equalsIgnoreCase("yes")) {
                                baggage.displayBaggageInfo(); // show bags and weight
                                System.out.println("Baggage Screening Status: " + (baggage.baggageScreened ? "Screened" : "Not Screened")); // <-- NEW
                            } else {
                                System.out.println("No Baggage Checked In.");
                                System.out.println("Baggage Screening Status: Not Applicable");
                            }
                            
                            baggage.baggageScreened = false;

                            
                            System.out.println("=====================================");
                            
                            // Generate boarding pass
                            boardingPass = new BoardingPass(
                                p,
                                flightDetails.getFlightNumber(),
                                flightDetails.getOrigin(),
                                flightDetails.getDestination(),
                                flightDetails.getDepartureTime()
                            );
                            
                            // Display boarding pass options
                            processBoardingPass(scanner, boardingPass, p.getPhoneNum());
                            break;

                        case 2:
                            // Collect basic group information first
                            g = checkin.collectBasicGroupInfo();
                            
                            // Generate and display flight details after basic info is collected
                            flightDetails = new FlightDetails();
                            flightDetails.displayFlightInfo();
                            
                            // Ask passenger if this is their flight
                            String proceedWithGroupFlight = "";
                            while (true) {
                                System.out.print("\nIs the Flight Information displayed correct? (Yes/No): ");
                                proceedWithGroupFlight = scanner.nextLine().trim();

                                if (proceedWithGroupFlight.equalsIgnoreCase("yes") || proceedWithGroupFlight.equalsIgnoreCase("no")) {
                                    break;
                                } else {
                                    System.out.println("Invalid input. Please type 'Yes' or 'No'.");
                                }
                            }
                            
                            if (!proceedWithGroupFlight.equalsIgnoreCase("yes")) {
                                System.out.println("Please go to counter 10, Servia will assist you. Returning to main menu.");
                                scanner.close();
                                return;
                            }
                            
                            // Continue with full group info collection
                            g = checkin.completeGroupInfo(g);
                            
                            // Check if group wants to check in baggage
                            String groupBag = "";
                            while (true) {
                                System.out.print("Would you like to check in baggage? (Yes/No): ");
                                groupBag = scanner.nextLine().trim();

                                if (groupBag.equalsIgnoreCase("yes") || groupBag.equalsIgnoreCase("no")) {
                                    break;
                                } else {
                                    System.out.println("Invalid input. Please enter 'Yes' or 'No'.");
                                }
                            }

                            if (groupBag.equalsIgnoreCase("yes")) {
                                baggage.collectGroupBaggageInfo(scanner); // still collect baggage info
                            }

                            // Check-in success message
                            System.out.println("=========CHECK IN SUCCESSFUL==========");
                            System.out.println("Group Information:");
                            System.out.println("Representative Name: " + g.getRepName());
                            System.out.println("Representative ID: " + g.getRepID());
                            System.out.println("Representative Passport: " + g.getRepPassportNum());
                            System.out.println("Representative Phone Number: " + g.getRepPhoneNum());
                            System.out.println("Number of Pax: " + g.getPax());
                            System.out.println("Special Assistance Needed?: " + (g.getGSpecialNeeds() ? "Yes" : "No"));

                            if (g.getGSpecialNeeds()) {
                                System.out.println("Special Accommodations: " + g.getGSpecialNeedsType());
                                System.out.println("Number of Units Required Each: " + g.getGassistanceQty());
                            }

                            if (groupBag.equalsIgnoreCase("yes")) {
                                baggage.displayBaggageInfo(); // show bags and weight
                                System.out.println("Baggage Screening Status: " + (baggage.baggageScreened ? "Screened" : "Not Screened")); // <-- NEW
                            } else {
                                System.out.println("No Baggage Checked In.");
                                System.out.println("Baggage Screening Status: Not Applicable");
                            }
                            System.out.println("=====================================");

                            // Reset the screening status for next user
                            baggage.baggageScreened = false; 
                            
                            // Generate group boarding pass
                            boardingPass = new BoardingPass(
                                g,
                                flightDetails.getFlightNumber(),
                                flightDetails.getOrigin(),
                                flightDetails.getDestination(),
                                flightDetails.getDepartureTime(),
                                true // indicate this is a group booking
                            );
                            
                            // Display boarding pass options
                            processBoardingPass(scanner, boardingPass, g.getRepPhoneNum());
                            break;

                        default:
                            System.out.println("Invalid option. Please enter 1 or 2.");
                    }
                    break;

                case 3:
                    baggage.screenBaggage();
                    break;
                 
                case 4:
                    System.out.println("Thank you for using AeroCheck, have a safe flight!");
                    break;

                default:
                    System.out.println("Invalid option. Please enter a number between 1 and 3.");
            }

        } while (option != 4);

        scanner.close();
    }
    
    private static void processBoardingPass(Scanner scanner, BoardingPass boardingPass, String phoneNum) {
        boolean continueProcessing = true;
        
        while (continueProcessing) {
            System.out.println("\n--- Boarding Pass Options ---");
            System.out.println("1. View Boarding Pass");
            System.out.println("2. Send to Mobile");
            System.out.println("3. Print Boarding Pass");
            System.out.print("Option: ");
            
            int boardingPassOption = 0;
            if (scanner.hasNextInt()) {
                boardingPassOption = scanner.nextInt();
                scanner.nextLine(); // consume newline
            } else {
                System.out.println("Invalid input. Defaulting to View Boarding Pass.");
                boardingPassOption = 1;
                scanner.next(); // Clear invalid input
            }
            
            switch (boardingPassOption) {
                case 1:
                    boardingPass.displayBoardingPass();
                    continue;
                    
                case 2:
                    boardingPass.sendBoardingPass(phoneNum);
                    
                    // Ask if they want to print as well
                    System.out.print("Would you like to print the boarding pass as well? (Yes/No): ");
                    String printAlso = scanner.nextLine();
                    
                    if (printAlso.equalsIgnoreCase("yes")) {
                        System.out.println("\nPrinting boarding pass...");
                        boardingPass.displayBoardingPass();
                        System.out.println("\nBoarding pass has been printed successfully.");
                    }
                    
                    showRealTimeUpdate(scanner, phoneNum);
                    continueProcessing = false;
                    break;
                    
                case 3:
                    System.out.println("\nPrinting boarding pass...");
                    boardingPass.displayBoardingPass();
                    System.out.println("\nBoarding pass has been printed successfully.");
                    
                    showRealTimeUpdate(scanner, phoneNum);
                    continueProcessing = false;
                    break;
                    
                default:
                    System.out.println("Invalid option. Displaying boarding pass.");
                    boardingPass.displayBoardingPass();
            }
        }
    }
    
    private static void showRealTimeUpdate(Scanner scanner, String phoneNum) {
        // Ask if they want to enable notifications
        System.out.print("\nEnable push notifications for this flight? (Yes/No): ");
        String enableNotifications = scanner.nextLine();
        
        if (enableNotifications.equalsIgnoreCase("yes")) {
            // Only show notification details if user opts in
            System.out.println("\n--- Real-time Updates ---");
            System.out.println("✓ You will receive notifications about:");
            System.out.println("  • Flight status changes");
            System.out.println("  • Gate changes");
            System.out.println("  • Boarding announcements");
            System.out.println("  • Delays or cancellations");
            System.out.println("\nPush notifications enabled. You will receive updates at " + phoneNum);
        } else {
            System.out.println("Push notifications not enabled. You can enable them later in the app settings.");
        }
        
        // Thank user and exit
        System.out.println("\nThank you for using AeroCheck. Have a safe flight!");
        scanner.close();
        System.exit(0); // Exit the application
    }
}
