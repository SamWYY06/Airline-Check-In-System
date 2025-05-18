package aerocheck;

import java.time.LocalDateTime;
import java.util.*;

public class FlightDetails {
    private String flightNumber;
    private String origin;
    private String destination;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private String airlineName;
    private String aircraft;
    private String terminalId;
    private boolean isInternational;
    
    // List of possible destinations and corresponding flight prefixes
    private static final String[][] DESTINATIONS = {
        {"Kuala Lumpur", "KUL", "MH"}, 
        {"Singapore", "SIN", "SQ"}, 
        {"Bangkok", "BKK", "TG"}, 
        {"Tokyo", "NRT", "JL"}, 
        {"Seoul", "ICN", "KE"},
        {"Sydney", "SYD", "QF"},
        {"London", "LHR", "BA"},
        {"Dubai", "DXB", "EK"},
        {"New York", "JFK", "AA"},
        {"Hong Kong", "HKG", "CX"}
    };
    
    private static final String[] AIRCRAFT_TYPES = {
        "Boeing 737-800",
        "Airbus A320",
        "Boeing 777-300ER",
        "Airbus A350-900",
        "Boeing 787-9 Dreamliner"
    };
    
    // Constructor
    public FlightDetails() {
        generateRandomFlight();
    }
    
    // Generate a random flight
    private void generateRandomFlight() {
        Random random = new Random();
        
        // Pick random origin and destination
        int originIndex = random.nextInt(DESTINATIONS.length);
        int destIndex;
        do {
            destIndex = random.nextInt(DESTINATIONS.length);
        } while (destIndex == originIndex);
        
        origin = DESTINATIONS[originIndex][0] + " (" + DESTINATIONS[originIndex][1] + ")";
        destination = DESTINATIONS[destIndex][0] + " (" + DESTINATIONS[destIndex][1] + ")";
        
        // Generate flight number
        String airlineCode = DESTINATIONS[random.nextInt(DESTINATIONS.length)][2];
        flightNumber = airlineCode + (1000 + random.nextInt(9000));
        
        // Set airline name based on code
        airlineName = getAirlineName(airlineCode);
        
        // Set aircraft type
        aircraft = AIRCRAFT_TYPES[random.nextInt(AIRCRAFT_TYPES.length)];
        
        // Generate departure time (within next 12 hours)
        int hoursFromNow = random.nextInt(12) + 1;
        departureTime = LocalDateTime.now().plusHours(hoursFromNow);
        
        // Generate arrival time (flight duration between 1-15 hours)
        int flightDuration = random.nextInt(14) + 1;
        arrivalTime = departureTime.plusHours(flightDuration);
        
        // Set terminal
        char terminal = (char) ('A' + random.nextInt(5)); // Terminals A to E
        terminalId = String.valueOf(terminal);
        
        // Set whether international
        isInternational = !DESTINATIONS[originIndex][1].equals("KUL") && !DESTINATIONS[destIndex][1].equals("KUL");
    }
    
    private String getAirlineName(String code) {
        switch (code) {
            case "MH": return "Malaysia Airlines";
            case "SQ": return "Singapore Airlines";
            case "TG": return "Thai Airways";
            case "JL": return "Japan Airlines";
            case "KE": return "Korean Air";
            case "QF": return "Qantas";
            case "BA": return "British Airways";
            case "EK": return "Emirates";
            case "AA": return "American Airlines";
            case "CX": return "Cathay Pacific";
            default: return "Unknown Airline";
        }
    }
    
    // Getters
    public String getFlightNumber() {
        return flightNumber;
    }
    
    public String getOrigin() {
        return origin;
    }
    
    public String getDestination() {
        return destination;
    }
    
    public LocalDateTime getDepartureTime() {
        return departureTime;
    }
    
    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }
    
    public String getAirlineName() {
        return airlineName;
    }
    
    public String getAircraft() {
        return aircraft;
    }
    
    public String getTerminalId() {
        return terminalId;
    }
    
    public boolean isInternational() {
        return isInternational;
    }
    
    // Display flight information
    public void displayFlightInfo() {
        System.out.println("\n+--------------------------------------------------+");
        System.out.println("|               FLIGHT INFORMATION                 |");
        System.out.println("+--------------------------------------------------+");
        System.out.printf ("| Flight Number : %-32s |\n", flightNumber + " (" + airlineName + ")");
        System.out.printf ("| From          : %-32s |\n", origin);
        System.out.printf ("| To            : %-32s |\n", destination);
        System.out.printf ("| Departure     : %-32s |\n", 
            departureTime.toLocalDate() + " at " + 
            departureTime.toLocalTime().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm")));
        System.out.printf ("| Arrival       : %-32s |\n", 
            arrivalTime.toLocalDate() + " at " + 
            arrivalTime.toLocalTime().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm")));
        System.out.printf ("| Aircraft      : %-32s |\n", aircraft);
        System.out.printf ("| Terminal      : %-32s |\n", terminalId);
        System.out.printf ("| Flight Type   : %-32s |\n", (isInternational ? "International" : "Domestic"));
        System.out.println("+--------------------------------------------------+");
    }

}
