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

    // Constructor for Solo Check-in
    public passenger(String pName, String pID, String passportNum, String phoneNum, boolean specialNeeds, ArrayList<String> specialNeedsTypes, int assistanceQty) {
        this.pName = pName;
        this.pID = pID;
        this.passportNum = passportNum;
        this.phoneNum = phoneNum;
        this.specialNeeds = specialNeeds;
        this.specialNeedsTypes = specialNeedsTypes;
        this.assistanceQty = assistanceQty;
    }

    // Constructor for Group Check-in
    public passenger(String repName, String repID, String repPassportNum, String repPhoneNum, String pax, boolean specialNeeds, ArrayList<String> specialNeedsTypes, int assistanceQty) {
        this.repName = repName;
        this.repID = repID;
        this.repPassportNum = repPassportNum;
        this.repPhoneNum = repPhoneNum;
        this.pax = pax;
        this.specialNeeds = specialNeeds;
        this.specialNeedsTypes = specialNeedsTypes;
        this.assistanceQty = assistanceQty;
    }

    // Solo check in setters
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

    public void setSpecialNeedsTypes(ArrayList<String> specialNeedsTypes) {
        this.specialNeedsTypes = specialNeedsTypes;
    }

    public void setAssistanceQty(int assistanceQty) {
        this.assistanceQty = assistanceQty;
    }

    // Group check in setters
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

    // Solo check in getters
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

    public ArrayList<String> getSpecialNeedsTypes() {
        return specialNeedsTypes;
    }

    public int getassistanceQty() {
        return assistanceQty;
    }

    // Group check in getters
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

    public ArrayList<String> getGSpecialNeedsTypes() {
        return specialNeedsTypes;
    }

    public int getGassistanceQty() {
        return assistanceQty;
    }
}
