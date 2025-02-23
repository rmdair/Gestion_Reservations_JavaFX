package fx;

import models.Flight;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import dao.FlightDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.Arrays;
import java.util.List;

public class FlightFX {
    private TableView<Flight> table;
    private FlightDAO flightDAO;

    public FlightFX() {
        flightDAO = new FlightDAO();
    }

    public BorderPane getLayout() {
        BorderPane root = new BorderPane();

        //------ Table to display flights ------//
        table = new TableView<>();
        table.setItems(getFlights());
        table.getColumns().addAll(createTableColumns());

        root.setCenter(table);

        return root;
    }

    private ObservableList<Flight> getFlights() {
        try {
            return FXCollections.observableArrayList(flightDAO.findAll());
        } catch (Exception e) {
            e.printStackTrace();
            return FXCollections.observableArrayList();
        }
    }

    private List<TableColumn<Flight, ?>> createTableColumns() {
        TableColumn<Flight, Integer> idCol = new TableColumn<>("Flight ID");
        idCol.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getFlightId()));

        TableColumn<Flight, Integer> airlineCol = new TableColumn<>("Airline ID");
        airlineCol.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getAirlineId()));

        TableColumn<Flight, String> departureCol = new TableColumn<>("Departure Location");
        departureCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDepartureLocation()));

        TableColumn<Flight, String> arrivalCol = new TableColumn<>("Arrival Location");
        arrivalCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getArrivalLocation()));

        TableColumn<Flight, String> departureDateCol = new TableColumn<>("Departure Date");
        departureDateCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDepartureDate().toString()));

        TableColumn<Flight, String> departureTimeCol = new TableColumn<>("Departure Time");
        departureTimeCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDepartureTime().toString()));

        TableColumn<Flight, String> arrivalDateCol = new TableColumn<>("Arrival Date");
        arrivalDateCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getArrivalDate().toString()));

        TableColumn<Flight, String> arrivalTimeCol = new TableColumn<>("Arrival Time");
        arrivalTimeCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getArrivalTime().toString()));

        return Arrays.asList(
            idCol, airlineCol, departureCol, arrivalCol,
            departureDateCol, departureTimeCol, arrivalDateCol, arrivalTimeCol
        );
    }
}