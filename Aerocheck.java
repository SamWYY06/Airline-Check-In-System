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

        int numberOfBags = 0;
        double totalWeight = 0.0;
        boolean hasOversized = false;
        baggage baggage = new baggage(numberOfBags, totalWeight);

        int option = 0;

        System.out.println("====================================");
        System.out.println("        Welcome to AeroCheck        ");
        System.out.println("====================================");

        while (true) {
            System.out.println("+----------------------------------+");
            System.out.println("| How would you like to check in:  |");
            System.out.println("+----------------------------------+");
            System.out.println("| 1. Counter check-in              |");
            System.out.println("| 2. Kiosk check-in                |");
            System.out.println("| 3. Screen Baggage                |");
            System.out.println("| 4. Exit                          |");
            System.out.println("+----------------------------------+");
            System.out.print("Option: ");

            if (scanner.hasNextInt()) {
                option = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("\n[!] Invalid input. Please enter a number between 1 and 4.\n");
                scanner.next();
                continue;
            }

            switch (option) {
                case 1:
                    System.out.println("\n>>> Please wait a moment for counter check-in...");
                    
                    for (int i = 5; i >= 1; i--) {
                        System.out.println(">>> Finding an available counter... " + i + " seconds remaining");
                        try {
                            Thread.sleep(1000); // Wait 1 second
                        } catch (InterruptedException e) {
                            System.out.println("[!] Timer interrupted. Please try again.");
                            Thread.currentThread().interrupt(); // Restore interrupt status
                            break;
                        }
                    }

                    // Generate random queue number and counter
                    Random rand = new Random();
                    int counterNumber = rand.nextInt(10) + 1;
                    int qNumber = rand.nextInt(30) + 1;         

                    // Generate random uppercase letter A–Z
                    char letter = (char) (rand.nextInt(26) + 'A');

                    // Display result
                    System.out.println("Your queue number is " + letter + String.format("%02d", qNumber));
                    System.out.println("Please head to COUNTER " + counterNumber + " to check in.");
                    System.out.println("Returning to main menu...\n");
                    break;

                case 2:
                    boolean inKioskMenu = true;
                    while (inKioskMenu) {
                        System.out.println("\n=========== KIOSK CHECK-IN MENU ===========");
                        System.out.println("| 1. Passenger Check-in                    |");
                        System.out.println("| 2. Group Check-in                        |");
                        System.out.println("| 3. Back to Main Menu                     |");
                        System.out.println("===========================================");
                        System.out.print("Option: ");

                        int checkinoption = 0;
                        if (scanner.hasNextInt()) {
                            checkinoption = scanner.nextInt();
                            scanner.nextLine();
                        } else {
                            System.out.println("\n[!] Invalid input. Please enter 1, 2 or 3.\n");
                            scanner.next();
                            continue;
                        }

                        switch (checkinoption) {
                            case 1:
                                p = checkin.collectBasicPassengerInfo();
                                flightDetails = new FlightDetails();
                                flightDetails.displayFlightInfo();

                                String proceedWithFlight = "";
                                while (true) {
                                    System.out.print("\nIs the Flight Information displayed correct? (Yes/No): ");
                                    proceedWithFlight = scanner.nextLine().trim();
                                    if (proceedWithFlight.equalsIgnoreCase("yes") || proceedWithFlight.equalsIgnoreCase("no")) 
                                        break;
                                    System.out.println("[!] Invalid input. Please type 'Yes' or 'No'.");
                                }

                                if (!proceedWithFlight.equalsIgnoreCase("yes")) {
                                    System.out.println("\n[!] Please go to counter 10, Servia will assist you.");
                                    System.out.println("Returning to main menu...\n");
                                    inKioskMenu = false;
                                    break;
                                }

                                p = checkin.completePassengerInfo(p);
                                String checkinbaggage = "";
                                while (true) {
                                    System.out.print("Would you like to check in baggage? (Yes/No): ");
                                    checkinbaggage = scanner.nextLine().trim();
                                    if (checkinbaggage.equalsIgnoreCase("yes") || checkinbaggage.equalsIgnoreCase("no")) 
                                        break;
                                    System.out.println("[!] Invalid input. Please enter 'Yes' or 'No'.");
                                }

                                if (checkinbaggage.equalsIgnoreCase("yes")) {
                                    baggage.collectSoloBaggageInfo(scanner);

                                    if (!baggage.baggageScreened) {
                                        System.out.println("\n[!] Your baggage has not been screened yet!");
                                        System.out.println("[!] Please proceed to baggage screening before continuing check-in.");
                                        System.out.println("Returning to main menu...\n");
                                        inKioskMenu = false;
                                        continue;
                                    }
                                }

                                System.out.println("=================================================================");
                                System.out.println("|                        CHECK-IN SUCCESSFUL                    |");
                                System.out.println("=================================================================");
                                System.out.printf ("| %-30s : %-28s |\n", "Name", p.getpName());
                                System.out.printf ("| %-30s : %-28s |\n", "ID", p.getpID());
                                System.out.printf ("| %-30s : %-28s |\n", "Passport", p.getPassportNum());
                                System.out.printf ("| %-30s : %-28s |\n", "Phone Number", p.getPhoneNum());
                                System.out.printf ("| %-30s : %-28s |\n", "Special Assistance Needed?", p.getSpecialNeeds() ? "Yes" : "No");

                                if (p.getSpecialNeeds()) {
                                    System.out.printf ("| %-30s : %-28s |\n", "Special Accommodations", p.getSpecialNeedsType());
                                    System.out.printf ("| %-30s : %-28d |\n", "Units Required Each", p.getassistanceQty());
                                }

                                if (checkinbaggage.equalsIgnoreCase("yes")) {
                                    System.out.println("+---------------------------------------------------------------+");
                                    baggage.displayBaggageInfo();
                                } else {
                                    System.out.printf ("| %-30s : %-28s |\n", "Baggage", "None checked in");
                                    System.out.printf ("| %-30s : %-28s |\n", "Baggage Screening Status", "Not Applicable");
                                    System.out.println("+---------------------------------------------------------------+");
                                }

                                baggage.baggageScreened = false;

                                boardingPass = new BoardingPass(
                                    p,
                                    flightDetails.getFlightNumber(),
                                    flightDetails.getOrigin(),
                                    flightDetails.getDestination(),
                                    flightDetails.getDepartureTime()
                                );

                                processBoardingPass(scanner, boardingPass, p.getPhoneNum());
                                inKioskMenu = false;
                                break;

                            case 2:
                                g = checkin.collectBasicGroupInfo();
                                flightDetails = new FlightDetails();
                                flightDetails.displayFlightInfo();

                                String proceedWithGroupFlight = "";
                                while (true) {
                                    System.out.print("\nIs the Flight Information displayed correct? (Yes/No): ");
                                    proceedWithGroupFlight = scanner.nextLine().trim();
                                    if (proceedWithGroupFlight.equalsIgnoreCase("yes") || proceedWithGroupFlight.equalsIgnoreCase("no")) 
                                        break;
                                    System.out.println("[!] Invalid input. Please type 'Yes' or 'No'.");
                                }

                                if (!proceedWithGroupFlight.equalsIgnoreCase("yes")) {
                                    System.out.println("\n[!] Please go to counter 10, Servia will assist you.");
                                    System.out.println("Returning to main menu...\n");
                                    inKioskMenu = false;
                                    break;
                                }

                                g = checkin.completeGroupInfo(g);
                                String groupBag = "";
                                while (true) {
                                    System.out.print("Would you like to check in baggage? (Yes/No): ");
                                    groupBag = scanner.nextLine().trim();
                                    if (groupBag.equalsIgnoreCase("yes") || groupBag.equalsIgnoreCase("no")) 
                                        break;
                                    System.out.println("[!] Invalid input. Please enter 'Yes' or 'No'.");
                                }

                                if (groupBag.equalsIgnoreCase("yes")) {
                                    baggage.collectGroupBaggageInfo(scanner);

                                    if (!baggage.baggageScreened) {
                                        System.out.println("\n[!] Your group's baggage has not been screened yet!");
                                        System.out.println("[!] Please proceed to baggage screening before continuing check-in.");
                                        System.out.println("Returning to main menu...\n");
                                        inKioskMenu = false;
                                        continue;
                                    }
                                }

                                System.out.println("=================================================================");
                                System.out.println("|                    GROUP CHECK-IN SUCCESSFUL                  |");
                                System.out.println("=================================================================");
                                System.out.printf("| %-30s : %-28s |\n", "Representative Name", g.getRepName());
                                System.out.printf("| %-30s : %-28s |\n", "ID", g.getRepID());
                                System.out.printf("| %-30s : %-28s |\n", "Passport", g.getRepPassportNum());
                                System.out.printf("| %-30s : %-28s |\n", "Phone Number", g.getRepPhoneNum());
                                System.out.printf("| %-30s : %-28s |\n", "Pax", g.getPax());
                                System.out.printf("| %-30s : %-28s |\n", "Special Assistance Needed?", g.getGSpecialNeeds() ? "Yes" : "No");
                                if (g.getGSpecialNeeds()) {
                                    System.out.printf("| %-30s : %-28s |\n", "Special Accommodations", g.getGSpecialNeedsType());
                                    System.out.printf("| %-30s : %-28d |\n", "Units Required", g.getGassistanceQty());
                                }

                                if (groupBag.equalsIgnoreCase("yes")) {
                                    System.out.println("+---------------------------------------------------------------+");
                                    baggage.displayBaggageInfo();
                                } else {
                                    System.out.printf("| %-30s : %-28s |\n", "Baggage", "None checked in");
                                    System.out.printf("| %-30s : %-28s |\n", "Baggage Screening Status", "Not Applicable");
                                    System.out.println("+---------------------------------------------------------------+");
                                }

                                baggage.baggageScreened = false;

                                boardingPass = new BoardingPass(
                                    g,
                                    flightDetails.getFlightNumber(),
                                    flightDetails.getOrigin(),
                                    flightDetails.getDestination(),
                                    flightDetails.getDepartureTime(),
                                    true
                                );

                                processBoardingPass(scanner, boardingPass, g.getRepPhoneNum());
                                inKioskMenu = false;
                                break;

                            case 3:
                                inKioskMenu = false;
                                break;

                            default:
                                System.out.println("[!] Invalid option. Please enter 1, 2 or 3.");
                        }
                    }
                    break;

                case 3:
                    baggage.screenBaggage();
                    break;

                case 4:
                    System.out.println("\nThank you for using AeroCheck, have a safe flight!");
                    scanner.close();
                    return;

                default:
                    System.out.println("[!] Invalid option. Please enter a number between 1 and 4.");
            }
        }
    }

    private static void processBoardingPass(Scanner scanner, BoardingPass boardingPass, String phoneNum) {
        while (true) {
            System.out.println("+-------------------------------------+");
            System.out.println("|     --- BOARDING PASS OPTIONS ---   |");
            System.out.println("+-------------------------------------+");
            System.out.println("| 0. Exit Program                     |");
            System.out.println("| 1. View Boarding Pass               |");
            System.out.println("| 2. Send to Mobile                   |");
            System.out.println("| 3. Print Boarding Pass              |");
            System.out.println("+-------------------------------------+");
            System.out.print("Option: ");

            int boardingPassOption = -1;
            if (scanner.hasNextInt()) {
                boardingPassOption = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("[!] Invalid input. Defaulting to View Boarding Pass.");
                boardingPassOption = 1;
                scanner.next();
            }

            switch (boardingPassOption) {
                case 0:
                    System.out.println("\nThank you for using AeroCheck, have a safe flight!");
                    scanner.close();
                    System.exit(0);
                    break;

                case 1:
                    boardingPass.displayBoardingPass();
                    break;

                case 2:
                    boardingPass.sendBoardingPass(phoneNum);
                    System.out.print("Would you like to print the boarding pass as well? (Yes/No): ");
                    String printAlso = scanner.nextLine();
                    if (printAlso.equalsIgnoreCase("yes")) {
                        System.out.println("\nPrinting boarding pass...");
                        boardingPass.displayBoardingPass();
                        System.out.println("\nBoarding pass has been printed successfully.");
                    }
                    showRealTimeUpdate(scanner, phoneNum);
                    break;

                case 3:
                    System.out.println("\nPrinting boarding pass...");
                    boardingPass.displayBoardingPass();
                    System.out.println("\nBoarding pass has been printed successfully.");
                    showRealTimeUpdate(scanner, phoneNum);
                    break;

                default:
                    System.out.println("[!] Invalid option. Displaying boarding pass.");
                    boardingPass.displayBoardingPass();
            }
        }
    }

    private static void showRealTimeUpdate(Scanner scanner, String phoneNum) {
        System.out.print("\nEnable push notifications for this flight? (Yes/No): ");
        String enableNotifications = scanner.nextLine();

        if (enableNotifications.equalsIgnoreCase("yes")) {
            System.out.println("+---------------------------------------------+");
            System.out.println("|         REAL-TIME UPDATES ENABLED           |");
            System.out.println("+---------------------------------------------+");
            System.out.println("| You will receive notifications about:       |");
            System.out.println("|   - Flight status changes                   |");
            System.out.println("|   - Gate changes                            |");
            System.out.println("|   - Boarding announcements                  |");
            System.out.println("|   - Delays or cancellations                 |");
            System.out.println("+---------------------------------------------+");
            System.out.println("| Push notifications enabled.                 |");
            System.out.println("| Updates will be sent to: " + phoneNum + "         |");
            System.out.println("+---------------------------------------------+");
        } else {
            System.out.println("+---------------------------------------------+");
            System.out.println("|       PUSH NOTIFICATIONS NOT ENABLED        |");
            System.out.println("+---------------------------------------------+");
            System.out.println("| You can enable them later in the            |");
            System.out.println("| AeroCheck app settings.                     |");
            System.out.println("+---------------------------------------------+");
        }

        System.out.println("\nReturning to main menu...\n");
    }
}
