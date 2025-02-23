package fx;

import dao.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import models.*;
import javafx.beans.property.SimpleObjectProperty;

import java.util.ArrayList;
import java.util.List;

public class ExcelViewFX {
    private TableView<List<Object>> table;
    private ClientDAO clientDAO;
    private TravelAgentDAO travelAgentDAO;
    private ReservationDAO reservationDAO;
    private FlightDAO flightDAO;
    private AirlineDAO airlineDAO;

    public ExcelViewFX() {
        clientDAO = new ClientDAO();
        travelAgentDAO = new TravelAgentDAO();
        reservationDAO = new ReservationDAO();
        flightDAO = new FlightDAO();
        airlineDAO = new AirlineDAO();
    }

    public BorderPane getLayout() {
        BorderPane root = new BorderPane();

        //------ Table to display all Excel data ------//
        table = new TableView<>();
        refreshData();    // Load data at startup
        table.getColumns().addAll(createTableColumns());

        root.setCenter(table);
        return root;
    }

    public void refreshData() {
        ObservableList<List<Object>> data = getAllData();
        if (table != null) {
            table.setItems(data); 
            table.refresh();    // Refresh display
        }
    }

    private ObservableList<List<Object>> getAllData() {
        List<List<Object>> data = new ArrayList<>();

        try {
            List<Client> clients = clientDAO.findAll();
            List<Reservation> reservations = reservationDAO.findAll();
            List<Flight> flights = flightDAO.findAll();
            List<Airline> airlines = airlineDAO.findAll();
            List<TravelAgent> travelAgents = travelAgentDAO.findAll();

            for (Reservation reservation : reservations) {
                Client client = clients.stream().filter(c -> c.getClientId() == reservation.getClientId()).findFirst().orElse(null);
                TravelAgent travelAgent = travelAgents.stream().filter(c -> c.getTravelAgentId() == reservation.getTravelAgentId()).findFirst().orElse(null);
                Flight flight = flights.stream().filter(v -> v.getFlightId() == reservation.getFlightId()).findFirst().orElse(null);
                Airline airline = (flight != null) ? airlines.stream().filter(comp -> comp.getAirlineId() == flight.getAirlineId()).findFirst().orElse(null) : null;

                List<Object> row = new ArrayList<>();
                if (client != null) {
                    row.add(client.getClientId());
                    row.add(client.getLastName());
                    row.add(client.getFirstName());
                    row.add(client.getAddress());
                    row.add(client.getEmail());
                    row.add(client.getPhoneNumber());
                }
                if (reservation != null) {
                    row.add(reservation.getReservationId());
                    row.add(reservation.getReservationDate());
                    row.add(reservation.getFlightClass());
                    row.add(reservation.getPaymentType());
                    row.add(reservation.getStatus());
                    row.add(reservation.getBookerLastName());
                    row.add(reservation.getBookerFirstName());
                    row.add(reservation.getBookerPhoneNumber());
                }
                if (flight != null) {
                    row.add(flight.getFlightId());
                    row.add(flight.getDepartureLocation());
                    row.add(flight.getArrivalLocation());
                    row.add(flight.getDepartureDate());
                    row.add(flight.getDepartureTime());
                    row.add(flight.getArrivalDate());
                    row.add(flight.getArrivalTime());
                }
                if (airline != null) {
                    row.add(airline.getAirlineId());
                    row.add(airline.getName());
                    row.add(airline.getCountryOfOrigin());
                }
                if (travelAgent != null) {
                    row.add(travelAgent.getTravelAgentId());
                    row.add(travelAgent.getLastName());
                    row.add(travelAgent.getFirstName());
                    row.add(travelAgent.getEmail());
                    row.add(travelAgent.getPhoneNumber());
                }

                data.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return FXCollections.observableArrayList(data);
    }

    private List<TableColumn<List<Object>, ?>> createTableColumns() {
        List<String> headers = List.of(
            "Client ID", "Client Last Name", "Client First Name", "Client Address", "Client Email", "Client Phone",
            "Reservation ID", "Reservation Date", "Class", "Payment Type", "Status", "Booker Last Name", "Booker First Name", "Booker Phone",
            "Flight ID", "Departure Location", "Arrival Location", "Departure Date", "Departure Time", "Arrival Date", "Arrival Time",
            "Airline ID", "Airline Name", "Country of Origin",
            "Travel Agent ID", "Travel Agent Last Name", "Travel Agent First Name", "Travel Agent Email", "Travel Agent Phone"
        );

        List<TableColumn<List<Object>, ?>> columns = new ArrayList<>();
        for (int i = 0; i < headers.size(); i++) {
            final int index = i;
            TableColumn<List<Object>, Object> column = new TableColumn<>(headers.get(i));
            column.setCellValueFactory(data -> {
                if (data.getValue().size() > index) {
                    return new SimpleObjectProperty<>(data.getValue().get(index));
                } else {
                    return new SimpleObjectProperty<>(null);
                }
            });
            columns.add(column);
        }

        return columns;
    }
}