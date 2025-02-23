package fx;

import models.TravelAgent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import dao.TravelAgentDAO;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Arrays;
import java.util.List;

public class TravelAgentFX {
    private TableView<TravelAgent> table;
    private TravelAgentDAO travelAgentDAO;

    public TravelAgentFX() {
        travelAgentDAO = new TravelAgentDAO();
    }

    public BorderPane getLayout() {
        BorderPane root = new BorderPane();

        //------ Table to display travel agents ------//
        table = new TableView<>();
        table.setItems(getTravelAgents());
        table.getColumns().addAll(createTableColumns());

        root.setCenter(table);

        return root;
    }

    private ObservableList<TravelAgent> getTravelAgents() {
        try {
            return FXCollections.observableArrayList(travelAgentDAO.findAll());
        } catch (Exception e) {
            e.printStackTrace();
            return FXCollections.observableArrayList();
        }
    }

    private List<TableColumn<TravelAgent, ?>> createTableColumns() {
        TableColumn<TravelAgent, Integer> idCol = new TableColumn<>("Travel Agent ID");
        idCol.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getTravelAgentId()));

        TableColumn<TravelAgent, String> lastNameCol = new TableColumn<>("Last Name");
        lastNameCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getLastName()));

        TableColumn<TravelAgent, String> firstNameCol = new TableColumn<>("First Name");
        firstNameCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFirstName()));

        TableColumn<TravelAgent, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEmail()));

        TableColumn<TravelAgent, String> phoneNumberCol = new TableColumn<>("Phone Number");
        phoneNumberCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPhoneNumber()));

        return Arrays.asList(idCol, lastNameCol, firstNameCol, emailCol, phoneNumberCol);
    }
}
