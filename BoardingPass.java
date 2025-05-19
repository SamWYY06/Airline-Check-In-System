package aerocheck;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class BoardingPass {
    private String passengerName;
    private String passengerId;
    private String flightNumber;
    private String origin;
    private String destination;
    private LocalDateTime departureTime;
    private String gate;
    private String seat;
    private String boardingGroup;
    private String boardingTime;
    private String barcode;
    private boolean isGroup;
    private int groupSize;
    private boolean specialAssistance;
    private String specialAssistanceType;
    
    // Constructor for individual passenger
    public BoardingPass(passenger p, String flightNumber, String origin, String destination, LocalDateTime departureTime) {
        this.passengerName = p.getpName();
        this.passengerId = p.getpID();
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.gate = generateRandomGate();
        this.seat = generateRandomSeat();
        this.boardingGroup = generateBoardingGroup(p.getSpecialNeeds());
        this.boardingTime = calculateBoardingTime(departureTime);
        this.barcode = generateBarcode();
        this.isGroup = false;
        this.specialAssistance = p.getSpecialNeeds();
        this.specialAssistanceType = p.getSpecialNeedsType();
    }
    
    // Constructor for group with a different signature to avoid ambiguity
    public BoardingPass(passenger g, String flightNumber, String origin, String destination, 
                        LocalDateTime departureTime, boolean isGroupBooking) {
        this.passengerName = g.getRepName() + " & Group";
        this.passengerId = g.getRepID();
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.gate = generateRandomGate();
        this.seat = "Multiple"; // Groups will have multiple seats
        this.boardingGroup = generateBoardingGroup(g.getGSpecialNeeds());
        this.boardingTime = calculateBoardingTime(departureTime);
        this.barcode = generateBarcode();
        this.isGroup = true;
        this.groupSize = Integer.parseInt(g.getPax());
        this.specialAssistance = g.getGSpecialNeeds();
        this.specialAssistanceType = g.getGSpecialNeedsType();
    }
    
    private String generateRandomGate() {
        char terminal = (char) ('A' + new Random().nextInt(5)); // Terminals A through E
        int gateNumber = new Random().nextInt(30) + 1; // Gates 1-30
        return terminal + String.valueOf(gateNumber);
    }
    
    private String generateRandomSeat() {
        String[] rows = {"A", "B", "C", "D", "E", "F"};
        int seatNumber = new Random().nextInt(30) + 1; // Seats 1-30
        String row = rows[new Random().nextInt(rows.length)];
        return seatNumber + row;
    }
    
    private String generateBoardingGroup(boolean hasSpecialNeeds) {
        // Passengers with special needs get priority boarding
        if (hasSpecialNeeds) {
            return "Priority";
        } else {
            // Regular boarding groups 1-5
            return String.valueOf(new Random().nextInt(5) + 1);
        }
    }
    
    private String calculateBoardingTime(LocalDateTime departureTime) {
        // Boarding starts 45 minutes before departure
        LocalDateTime boardingTime = departureTime.minusMinutes(45);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return boardingTime.format(formatter);
    }
    
    private String generateBarcode() {
        // Generate a random alphanumeric barcode
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        
        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(chars.length());
            sb.append(chars.charAt(index));
        }
        
        return sb.toString();
    }
    
    // Method to display boarding pass in console
    public void displayBoardingPass() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        
        System.out.println("\n==================================================");
        System.out.println("             AEROCHECK BOARDING PASS              ");
        System.out.println("==================================================");
        System.out.println("FLIGHT: " + flightNumber + "                DATE: " + departureTime.format(dateFormatter));
        System.out.println("FROM: " + origin + "          TO: " + destination);
        System.out.println("--------------------------------------------------");
        System.out.println("PASSENGER: " + passengerName);
        
        if (isGroup) {
            System.out.println("GROUP SIZE: " + groupSize);
            System.out.println("SEAT(s): Assigned at Gate");
        } else {
            System.out.println("SEAT: " + seat);
        }
        
        System.out.println("GATE: " + gate);
        System.out.println("BOARDING GROUP: " + boardingGroup);
        System.out.println("BOARDING TIME: " + boardingTime);
        
        if (specialAssistance) {
            System.out.println("SPECIAL ASSISTANCE: " + specialAssistanceType);
        }
        
        System.out.println("--------------------------------------------------");
        System.out.println("BARCODE: " + barcode);
        System.out.println("==================================================");
    }
    
    // Method to send boarding pass to email/SMS (simulated)
    public void sendBoardingPass(String contactInfo) {
        System.out.println("\nBoarding pass has been sent to: " + contactInfo);
        System.out.println("You can access your boarding pass on your mobile device or print it as needed.");
    }
}
