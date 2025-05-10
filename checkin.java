package aerocheck;

import java.util.*;

public class checkin {
    
    Scanner scanner = new Scanner(System.in);
    
    // Constructor
    public checkin() {
        
    }
    
    // Method to collect passenger info and return a Passenger object
    // (Object types): The method returns a reference to an object (an instance of a class)
    public passenger collectPassengerInfo() {
        System.out.println("-----------------Passenger Check In-----------------");
        
        String pName = "";
        // Validatation (only alphabetic characters allowed)
        while (true) {
            System.out.print("Enter Passenger Name: ");
            pName = scanner.nextLine();
            System.out.println("----------------------------------------------------");
            
            if (pName.matches("[a-zA-Z]+")) {
                break;
            }
            else {
                System.out.println("Please enter a name with alphabetic characters only!");
                System.out.println("----------------------------------------------------");
            }
        }
        
        // Validation (4 uppercase letters followed by 4 digits)
        String pID = "";
        while(true) {
            System.out.println("Format: ABCD1234");
            System.out.print("Enter Passenger ID: ");
            pID = scanner.nextLine();
            System.out.println("----------------------------------------------------");
            
            if (pID.matches("^[A-Z]{4}[0-9]{4}$")) {  
                break;
            }
            else {
                System.out.println("Please enter in the correct format!");
                System.out.println("----------------------------------------------------");
            }
        }
        
        // Validation (1 uppercase letter followed by 8 digits)
        String passportNum = "";
        while(true) {
            System.out.println("Format: A12345678");
            System.out.print("Enter Passport Number: ");
            passportNum = scanner.nextLine();
            System.out.println("----------------------------------------------------");
            
            if (passportNum.matches("[A-Z]{1}[0-9]{8}")) { 
                break;
            }
            else {
                System.out.println("Please enter in the correct format!");
                System.out.println("----------------------------------------------------");
            }
        }
        
        String phoneNum = "";
        while(true) {
            System.out.println("Format (No spacing needed): 01X XXX XXXX");
            System.out.print("Enter Phone Number: ");
            phoneNum = scanner.nextLine();
            
            if (phoneNum.matches("^01[0-9]{8}$")) {
                break;
            }
            else {
                System.out.println("Please enter a valid phone number!");
                System.out.println("----------------------------------------------------");
            }
        }
                

        boolean specialNeeds = false;
        ArrayList<String> specialNeedsTypes = new ArrayList<>();
        int assistanceQty = 0;

        System.out.print("Special Needs Assistance? (Yes/No): ");
        String response = scanner.nextLine();

        if (response.equalsIgnoreCase("yes")) {
            specialNeeds = true;

            while (true) {
                System.out.println("Select assistance tools (separate choices by commas, e.g., A,C):");
                System.out.println("A. Wheelchair");
                System.out.println("B. Crutch");
                System.out.println("C. Tongkat");
                System.out.print("Options: ");
                String choices = scanner.nextLine().toUpperCase();

                String[] selectedOptions = choices.split(",");
                specialNeedsTypes.clear();

                boolean allValid = true;
                for (String choice : selectedOptions) {
                    switch (choice.trim()) {
                        case "A":
                            specialNeedsTypes.add("Wheelchair");
                            break;
                        case "B":
                            specialNeedsTypes.add("Crutch");
                            break;
                        case "C":
                            specialNeedsTypes.add("Tongkat");
                            break;
                        default:
                            System.out.println("Invalid selection: " + choice);
                            allValid = false;
                    }
                }

                if (allValid && !specialNeedsTypes.isEmpty()) {
                    break;
                } else {
                    System.out.println("Please enter valid options only.");
                }
            }

            while (true) {
                System.out.print("How many units are required each? (Max 3): ");
                if (scanner.hasNextInt()) {
                    assistanceQty = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    if (assistanceQty >= 1 && assistanceQty <= 3) {
                        break;
                    } else {
                        System.out.println("Sorry! Quantity must be between 1 and 3.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.next(); // clear invalid input
                }
            }
        }


        
        // Create a new Passenger object
        passenger p = new passenger(pName, pID, passportNum, phoneNum, specialNeeds, specialNeedsTypes, assistanceQty);
        
        return p;
    }
    
    public passenger collectGroupInfo() {
        System.out.println("-----------------Group Check In-----------------");
        
        String repName = "";
        // Validatation (only alphabetic characters allowed)
        while (true) {
            System.out.print("Enter Representative Name: ");
            repName = scanner.nextLine();
            System.out.println("----------------------------------------------------");
            
            if (repName.matches("[a-zA-Z]+")) {
                break;
            }
            else {
                System.out.println("Please enter a name with alphabetic characters only!");
                System.out.println("----------------------------------------------------");
            }
        }
        
        // Validation (4 uppercase letters followed by 4 digits)
        String repID = "";
        while(true) {
            System.out.println("Format: ABCD1234");
            System.out.print("Enter Representative Passenger ID: ");
            repID = scanner.nextLine();
            System.out.println("----------------------------------------------------");
            
            if (repID.matches("^[A-Z]{4}[0-9]{4}$")) {  
                break;
            }
            else {
                System.out.println("Please enter in the correct format!");
                System.out.println("----------------------------------------------------");
            }
        }
        
        // Validation (1 uppercase letter followed by 8 digits)
        String repPassportNum = "";
        while(true) {
            System.out.println("Format: A12345678");
            System.out.print("Enter Representative Passport Number: ");
            repPassportNum = scanner.nextLine();
            System.out.println("----------------------------------------------------");
            
            if (repPassportNum.matches("[A-Z]{1}[0-9]{8}")) { 
                break;
            }
            else {
                System.out.println("Please enter in the correct format!");
                System.out.println("----------------------------------------------------");
            }
        }
        
        String repPhoneNum = "";
        while(true) {
            System.out.println("Format (No spacing needed): 01X XXX XXX");
            System.out.print("Enter Representative Phone Number: ");
            repPhoneNum = scanner.nextLine();
            
            if (repPhoneNum.matches("^01[0-9]{8}$")) {
                break;
            }
            else {
                System.out.println("Please enter a valid phone number!");
                System.out.println("----------------------------------------------------");
            }
        }
        
        String pax = "";
        while (true) {
            System.out.print("Enter number of pax (1-9): ");
            pax = scanner.nextLine();

            if (pax.matches("^[1-9]$")) {
                break;
            } else {
                System.out.println("Invalid input! Please enter a number between 1 and 9.");
                System.out.println("----------------------------------------------------");
            }
        }
        
        
        boolean specialNeeds = false;
        ArrayList<String> specialNeedsTypes = new ArrayList<>();
        int assistanceQty = 0;

        System.out.print("Special Needs Assistance? (Yes/No): ");
        String response = scanner.nextLine();
        if (response.equalsIgnoreCase("yes")) {
            specialNeeds = true;

            while (true) {
                System.out.println("Select assistance tools (separate choices by commas, e.g., A,C):");
                System.out.println("A. Wheelchair");
                System.out.println("B. Crutch");
                System.out.println("C. Tongkat");
                System.out.print("Options (A/B/C): ");
                String choices = scanner.nextLine().toUpperCase();

                String[] selectedOptions = choices.split(",");
                specialNeedsTypes.clear(); // Clear previous entries if any

                boolean allValid = true;
                for (String choice : selectedOptions) {
                    switch (choice.trim()) {
                        case "A":
                            specialNeedsTypes.add("Wheelchair");
                            break;
                        case "B":
                            specialNeedsTypes.add("Crutch");
                            break;
                        case "C":
                            specialNeedsTypes.add("Tongkat");
                            break;
                        default:
                            System.out.println("Invalid selection: " + choice);
                            allValid = false;
                    }
                }

                if (allValid && !specialNeedsTypes.isEmpty()) {
                    break;
                } else {
                    System.out.println("Please enter valid options only.");
                }
            }

            while (true) {
                System.out.print("How many units are required each? (Max 3): ");
                if (scanner.hasNextInt()) {
                    assistanceQty = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    if (assistanceQty >= 1 && assistanceQty <= 3) {
                        break;
                    } else {
                        System.out.println("Sorry! Quantity must be between 1 and 3.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.next(); // clear invalid input
                }
            }
        }
        passenger g = new passenger(repName, repID, repPassportNum, repPhoneNum, pax, specialNeeds, specialNeedsTypes, assistanceQty);
        
        return g;
    }
}
