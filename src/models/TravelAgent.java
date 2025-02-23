package models;

public class TravelAgent {
    private int travelAgentId;
    private String lastName;
    private String firstName;
    private String email;
    private String phoneNumber;

    //------ Empty constructor ------//
    public TravelAgent() {}

    //------ Constructor with all parameters ------//
    public TravelAgent(int travelAgentId, String lastName, String firstName, String email, String phoneNumber) {
        this.travelAgentId = travelAgentId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    //------ Getters and Setters ------//
    public int getTravelAgentId() {
        return travelAgentId;
    }

    public void setTravelAgentId(int travelAgentId) {
        this.travelAgentId = travelAgentId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "TravelAgent{" +
                "travelAgentId=" + travelAgentId +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}

