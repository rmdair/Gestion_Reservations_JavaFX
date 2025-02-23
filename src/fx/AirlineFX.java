package fx;

import models.Airline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import dao.AirlineDAO;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Arrays;
import java.util.List;

public class AirlineFX {
    private TableView<Airline> table;
    private AirlineDAO airlineDAO;

    public AirlineFX() {
        airlineDAO = new AirlineDAO();
    }

    public BorderPane getLayout() {
        BorderPane root = new BorderPane();

        //------ Table to display airlines ------//
        table = new TableView<>();
        table.setItems(getAirlines());
        table.getColumns().addAll(createTableColumns());

        root.setCenter(table);

        return root;
    }

    private ObservableList<Airline> getAirlines() {
        try {
            return FXCollections.observableArrayList(airlineDAO.findAll());
        } catch (Exception e) {
            e.printStackTrace();
            return FXCollections.observableArrayList();
        }
    }

    private List<TableColumn<Airline, ?>> createTableColumns() {
        TableColumn<Airline, Integer> idCol = new TableColumn<>("Airline ID");
        idCol.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getAirlineId()));

        TableColumn<Airline, String> nameCol = new TableColumn<>("Airline Name");
        nameCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));

        TableColumn<Airline, String> countryCol = new TableColumn<>("Country of Origin");
        countryCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCountryOfOrigin()));

        return Arrays.asList(idCol, nameCol, countryCol);
    }
}
