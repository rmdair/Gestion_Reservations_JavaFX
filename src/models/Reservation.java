package models;

import java.time.LocalDate;

public class Reservation {
    private int reservationId;
    private int clientId;
    private int travelAgentId;
    private int flightId;
    private LocalDate reservationDate;
    private String flightClass;
    private String paymentType;
    private String status;
    private String bookerLastName;
    private String bookerFirstName;
    private String bookerPhoneNumber;

    //------ Empty constructor ------//
    public Reservation() {}

    //------ Constructor with all parameters ------//
    public Reservation(int reservationId, int clientId, int travelAgentId, int flightId, LocalDate reservationDate,
                       String flightClass, String paymentType, String status,
                       String bookerLastName, String bookerFirstName, String bookerPhoneNumber) {
        this.reservationId = reservationId;
        this.clientId = clientId;
        this.travelAgentId = travelAgentId;
        this.flightId = flightId;
        this.reservationDate = reservationDate;
        this.flightClass = flightClass;
        this.paymentType = paymentType;
        this.status = status;
        this.bookerLastName = bookerLastName;
        this.bookerFirstName = bookerFirstName;
        this.bookerPhoneNumber = bookerPhoneNumber;
    }
    
    //------ Getters and Setters ------//
    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getTravelAgentId() {
        return travelAgentId;
    }

    public void setTravelAgentId(int travelAgentId) {
        this.travelAgentId = travelAgentId;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getFlightClass() {
        return flightClass;
    }

    public void setFlightClass(String flightClass) {
        this.flightClass = flightClass;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBookerLastName() {
        return bookerLastName;
    }

    public void setBookerLastName(String bookerLastName) {
        this.bookerLastName = bookerLastName;
    }

    public String getBookerFirstName() {
        return bookerFirstName;
    }

    public void setBookerFirstName(String bookerFirstName) {
        this.bookerFirstName = bookerFirstName;
    }

    public String getBookerPhoneNumber() {
        return bookerPhoneNumber;
    }

    public void setBookerPhoneNumber(String bookerPhoneNumber) {
        this.bookerPhoneNumber = bookerPhoneNumber;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId=" + reservationId +
                ", clientId=" + clientId +
                ", travelAgentId=" + travelAgentId +
                ", flightId=" + flightId +
                ", reservationDate=" + reservationDate +
                ", flightClass='" + flightClass + '\'' +
                ", paymentType='" + paymentType + '\'' +
                ", status='" + status + '\'' +
                ", bookerLastName='" + bookerLastName + '\'' +
                ", bookerFirstName='" + bookerFirstName + '\'' +
                ", bookerPhoneNumber='" + bookerPhoneNumber + '\'' +
                '}';
    }
}

