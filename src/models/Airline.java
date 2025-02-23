package models;

public class Airline {
    private int airlineId;
    private String name;
    private String countryOfOrigin;

    //------ Empty constructor ------//
    public Airline() {}

    //------ Constructor with all parameters ------//
    public Airline(int airlineId, String name, String countryOfOrigin) {
        this.airlineId = airlineId;
        this.name = name;
        this.countryOfOrigin = countryOfOrigin;
    }

    //------ Getters and Setters ------//
    public int getAirlineId() {
        return airlineId;
    }

    public void setAirlineId(int airlineId) {
        this.airlineId = airlineId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    @Override
    public String toString() {
        return "Airline{" +
                "airlineId=" + airlineId +
                ", name='" + name + '\'' +
                ", countryOfOrigin='" + countryOfOrigin + '\'' +
                '}';
    }
}
