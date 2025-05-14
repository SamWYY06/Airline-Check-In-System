package aerocheck;

import java.util.ArrayList;

public class passenger {

    // Solo Check-in
    private String pName;
    private String pID;
    private String passportNum;
    private String phoneNum;
    
    // Group Check-in
    private String repName;
    private String repID;
    private String repPassportNum;
    private String repPhoneNum;
    private String pax;
    
    // Shared
    private boolean specialNeeds;
    private ArrayList<String> specialNeedsTypes;
    private int assistanceQty;
    
    // Legacy support for old code
    private String specialNeedsType;

    // Constructor for partial Solo Check-in (basic info)
    public passenger(String pName, String pID, String phoneNum) {
        this.pName = pName;
        this.pID = pID;
        this.phoneNum = phoneNum;
        this.specialNeeds = false;
        this.specialNeedsTypes = new ArrayList<>();
        this.specialNeedsType = "None";
        this.assistanceQty = 0;
    }

    // Constructor for partial Group Check-in (basic info)
    public passenger(String repName, String repID, String repPhoneNum, String pax) {
        this.repName = repName;
        this.repID = repID;
        this.repPhoneNum = repPhoneNum;
        this.pax = pax;
        this.specialNeeds = false;
        this.specialNeedsTypes = new ArrayList<>();
        this.specialNeedsType = "None";
        this.assistanceQty = 0;
    }

    // Constructor for Solo Check-in with ArrayList
    public passenger(String pName, String pID, String passportNum, String phoneNum, 
                     boolean specialNeeds, ArrayList<String> specialNeedsTypes, int assistanceQty) {
        this.pName = pName;
        this.pID = pID;
        this.passportNum = passportNum;
        this.phoneNum = phoneNum;
        this.specialNeeds = specialNeeds;
        this.specialNeedsTypes = specialNeedsTypes;
        this.specialNeedsType = specialNeedsTypes != null && !specialNeedsTypes.isEmpty() ? 
                               String.join(", ", specialNeedsTypes) : "None";
        this.assistanceQty = assistanceQty;
    }
    
    // Legacy constructor for Solo Check-in with String
    public passenger(String pName, String pID, String passportNum, String phoneNum, 
                     boolean specialNeeds, String specialNeedsType, int assistanceQty) {
        this.pName = pName;
        this.pID = pID;
        this.passportNum = passportNum;
        this.phoneNum = phoneNum;
        this.specialNeeds = specialNeeds;
        this.specialNeedsType = specialNeedsType;
        this.specialNeedsTypes = new ArrayList<>();
        if (specialNeeds && specialNeedsType != null && !specialNeedsType.equals("None")) {
            this.specialNeedsTypes.add(specialNeedsType);
        }
        this.assistanceQty = assistanceQty;
    }

    // Constructor for Group Check-in with ArrayList
    public passenger(String repName, String repID, String repPassportNum, String repPhoneNum, 
                     String pax, boolean specialNeeds, ArrayList<String> specialNeedsTypes, int assistanceQty) {
        this.repName = repName;
        this.repID = repID;
        this.repPassportNum = repPassportNum;
        this.repPhoneNum = repPhoneNum;
        this.pax = pax;
        this.specialNeeds = specialNeeds;
        this.specialNeedsTypes = specialNeedsTypes;
        this.specialNeedsType = specialNeedsTypes != null && !specialNeedsTypes.isEmpty() ? 
                              String.join(", ", specialNeedsTypes) : "None";
        this.assistanceQty = assistanceQty;
    }
    
    // Legacy constructor for Group Check-in with String
    public passenger(String repName, String repID, String repPassportNum, String repPhoneNum, 
                     String pax, boolean specialNeeds, String specialNeedsType, int assistanceQty) {
        this.repName = repName;
        this.repID = repID;
        this.repPassportNum = repPassportNum;
        this.repPhoneNum = repPhoneNum;
        this.pax = pax;
        this.specialNeeds = specialNeeds;
        this.specialNeedsType = specialNeedsType;
        this.specialNeedsTypes = new ArrayList<>();
        if (specialNeeds && specialNeedsType != null && !specialNeedsType.equals("None")) {
            this.specialNeedsTypes.add(specialNeedsType);
        }
        this.assistanceQty = assistanceQty;
    }

    // ========== Solo Check-in Setters ==========
    public void setpName(String pName) {
        this.pName = pName;
    }

    public void setpID(String pID) {
        this.pID = pID;
    }

    public void setPassportNum(String passportNum) {
        this.passportNum = passportNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setSpecialNeeds(boolean specialNeeds) {
        this.specialNeeds = specialNeeds;
    }

    public void setSpecialNeedsType(String specialNeedsType) {
        this.specialNeedsType = specialNeedsType;
        // Update ArrayList for consistency
        this.specialNeedsTypes.clear();
        if (specialNeeds && specialNeedsType != null && !specialNeedsType.equals("None")) {
            this.specialNeedsTypes.add(specialNeedsType);
        }
    }
    
    public void setSpecialNeedsTypes(ArrayList<String> specialNeedsTypes) {
        this.specialNeedsTypes = specialNeedsTypes;
        // Update String for backward compatibility
        this.specialNeedsType = specialNeedsTypes != null && !specialNeedsTypes.isEmpty() ? 
                              String.join(", ", specialNeedsTypes) : "None";
    }

    public void setAssistanceQty(int assistanceQty) {
        this.assistanceQty = assistanceQty;
    }

    // ========== Group Check-in Setters ==========
    public void setRepName(String repName) {
        this.repName = repName;
    }

    public void setRepID(String repID) {
        this.repID = repID;
    }

    public void setRepPassportNum(String repPassportNum) {
        this.repPassportNum = repPassportNum;
    }

    public void setRepPhoneNum(String repPhoneNum) {
        this.repPhoneNum = repPhoneNum;
    }

    public void setPax(String pax) {
        this.pax = pax;
    }
    
    public void setGSpecialNeeds(boolean specialNeeds) {
        this.specialNeeds = specialNeeds;
    }

    public void setGSpecialNeedsType(String specialNeedsType) {
        this.specialNeedsType = specialNeedsType;
        // Update ArrayList for consistency
        this.specialNeedsTypes.clear();
        if (specialNeeds && specialNeedsType != null && !specialNeedsType.equals("None")) {
            this.specialNeedsTypes.add(specialNeedsType);
        }
    }
    
    public void setGSpecialNeedsTypes(ArrayList<String> specialNeedsTypes) {
        this.specialNeedsTypes = specialNeedsTypes;
        // Update String for backward compatibility
        this.specialNeedsType = specialNeedsTypes != null && !specialNeedsTypes.isEmpty() ? 
                              String.join(", ", specialNeedsTypes) : "None";
    }

    public void setGAssistanceQty(int assistanceQty) {
        this.assistanceQty = assistanceQty;
    }

    // ========== Solo Check-in Getters ==========
    public String getpName() {
        return pName;
    }

    public String getpID() {
        return pID;
    }

    public String getPassportNum() {
        return passportNum;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public boolean getSpecialNeeds() {
        return specialNeeds;
    }

    public String getSpecialNeedsType() {
        return specialNeedsType;
    }
    
    public ArrayList<String> getSpecialNeedsTypes() {
        return specialNeedsTypes;
    }

    public int getassistanceQty() {
        return assistanceQty;
    }

    // ========== Group Check-in Getters ==========
    public String getRepName() {
        return repName;
    }

    public String getRepID() {
        return repID;
    }

    public String getRepPassportNum() {
        return repPassportNum;
    }

    public String getRepPhoneNum() {
        return repPhoneNum;
    }

    public String getPax() {
        return pax;
    }
    
    public boolean getGSpecialNeeds() {
        return specialNeeds;
    }

    public String getGSpecialNeedsType() {
        return specialNeedsType;
    }
    
    public ArrayList<String> getGSpecialNeedsTypes() {
        return specialNeedsTypes;
    }

    public int getGassistanceQty() {
        return assistanceQty;
    }
}
