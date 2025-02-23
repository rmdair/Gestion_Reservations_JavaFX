package fx;

import models.Reservation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import dao.ReservationDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.Arrays;
import java.util.List;

public class ReservationFX {
    private TableView<Reservation> table;
    private ReservationDAO reservationDAO;
    private ExcelViewFX excelFX; 

    public ReservationFX(ExcelViewFX excelFX) { // Inject ExcelViewFX into the constructor
        reservationDAO = new ReservationDAO();
        this.excelFX = excelFX;
    }

    public BorderPane getLayout() {
        BorderPane root = new BorderPane();

        //------ Table to display reservations ------//
        table = new TableView<>();
        table.setItems(getReservations());
        table.getColumns().addAll(createTableColumns());

        //------ CRUD Buttons ------//
        HBox buttons = createButtons();

        root.setCenter(table);
        root.setBottom(buttons);

        return root;
    }

    private ObservableList<Reservation> getReservations() {
        try {
            return FXCollections.observableArrayList(reservationDAO.findAll());
        } catch (Exception e) {
            e.printStackTrace();
            return FXCollections.observableArrayList();
        }
    }

    private List<TableColumn<Reservation, ?>> createTableColumns() {
        TableColumn<Reservation, Integer> idCol = new TableColumn<>("Reservation ID");
        idCol.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getReservationId()));

        TableColumn<Reservation, Integer> clientCol = new TableColumn<>("Client ID");
        clientCol.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getClientId()));

        TableColumn<Reservation, Integer> travelAgentCol = new TableColumn<>("Travel Agent ID");
        travelAgentCol.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getTravelAgentId()));

        TableColumn<Reservation, Integer> flightCol = new TableColumn<>("Flight ID");
        flightCol.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getFlightId()));

        TableColumn<Reservation, String> reservationDateCol = new TableColumn<>("Reservation Date");
        reservationDateCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getReservationDate().toString()));

        TableColumn<Reservation, String> flightClassCol = new TableColumn<>("Class");
        flightClassCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFlightClass()));

        TableColumn<Reservation, String> paymentTypeCol = new TableColumn<>("Payment");
        paymentTypeCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPaymentType()));

        TableColumn<Reservation, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getStatus()));

        TableColumn<Reservation, String> bookerLastNameCol = new TableColumn<>("Booker Last Name");
        bookerLastNameCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getBookerLastName()));

        TableColumn<Reservation, String> bookerFirstNameCol = new TableColumn<>("Booker First Name");
        bookerFirstNameCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getBookerFirstName()));

        TableColumn<Reservation, String> bookerPhoneNumberCol = new TableColumn<>("Booker Phone Number");
        bookerPhoneNumberCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getBookerPhoneNumber()));

        return Arrays.asList(
                idCol, clientCol, travelAgentCol, flightCol, reservationDateCol, flightClassCol,
                paymentTypeCol, statusCol, bookerLastNameCol, bookerFirstNameCol, bookerPhoneNumberCol
            );
    }

    private HBox createButtons() {
        Button addButton = new Button("Add");
        Button updateButton = new Button("Update");
        Button deleteButton = new Button("Delete");

        addButton.setOnAction(e -> addReservation());
        updateButton.setOnAction(e -> updateReservation());
        deleteButton.setOnAction(e -> deleteReservation());

        HBox buttons = new HBox(10, addButton, updateButton, deleteButton);
        buttons.setPadding(new Insets(10));
        return buttons;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    //---------- Add Reservation Button Functionality ----------//
    private void addReservation() {
        Dialog<Reservation> dialog = new Dialog<>();
        dialog.setTitle("Add Reservation");

        //------ Input Fields ------//
        TextField clientField = new TextField();
        clientField.setPromptText("Client ID");

        TextField advisorField = new TextField();
        advisorField.setPromptText("Advisor ID");

        TextField flightField = new TextField();
        flightField.setPromptText("Flight ID");

        DatePicker reservationDatePicker = new DatePicker();

        //------ ComboBox for Class ------//
        ComboBox<String> flightClassComboBox = new ComboBox<>();
        flightClassComboBox.getItems().addAll("economy", "first class", "business");
        flightClassComboBox.setPromptText("Select a class");

        //------ ComboBox for Status ------//
        ComboBox<String> statusComboBox = new ComboBox<>();
        statusComboBox.getItems().addAll("confirmed", "canceled");
        statusComboBox.setPromptText("Select a status");

        //------ ComboBox for Payment Type ------//
        ComboBox<String> paymentTypeComboBox = new ComboBox<>();
        paymentTypeComboBox.getItems().addAll("credit_card", "bank_transfer", "paypal", "cash");
        paymentTypeComboBox.setPromptText("Select a payment method");

        TextField bookerLastNameField = new TextField();
        bookerLastNameField.setPromptText("Booker Last Name");

        TextField bookerFirstNameField = new TextField();
        bookerFirstNameField.setPromptText("Booker First Name");

        TextField bookerPhoneNumberField = new TextField();
        bookerPhoneNumberField.setPromptText("Booker Phone Number");

        VBox fields = new VBox(10,
            new Label("Client ID"), clientField,
            new Label("Advisor ID"), advisorField,
            new Label("Flight ID"), flightField,
            new Label("Reservation Date"), reservationDatePicker,
            new Label("Class"), flightClassComboBox,
            new Label("Status"), statusComboBox,
            new Label("Payment"), paymentTypeComboBox,
            new Label("Booker Last Name"), bookerLastNameField,
            new Label("Booker First Name"), bookerFirstNameField,
            new Label("Booker Phone Number"), bookerPhoneNumberField
        );
        fields.setPadding(new Insets(10));
        dialog.getDialogPane().setContent(fields);
        
        
        ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        Button saveButton = (Button) dialog.getDialogPane().lookupButton(saveButtonType);
        saveButton.addEventFilter(javafx.event.ActionEvent.ACTION, event -> {
            String bookerPhoneNumber = bookerPhoneNumberField.getText();

            if (bookerPhoneNumber.length() < 10 || bookerPhoneNumber.length() > 12) {
                showAlert("Error", "The phone number must be between 10 and 12 characters.");
                event.consume(); // Prevent dialog from closing
            }
        });

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                //------ Validation for Class and Status ------//
                if (flightClassComboBox.getValue() == null || statusComboBox.getValue() == null) {
                    showAlert("Error", "Please select values for Class and Status.");
                    return null;
                }

                try {
                    //------ Retrieve the last ID ------//
                    int newId = reservationDAO.getLastReservationId() + 1;

                    //------ Create reservation with new ID ------//
                    return new Reservation(
                        newId,
                        Integer.parseInt(clientField.getText()),
                        Integer.parseInt(advisorField.getText()),
                        Integer.parseInt(flightField.getText()),
                        reservationDatePicker.getValue(),
                        flightClassComboBox.getValue(),
                        paymentTypeComboBox.getValue(),
                        statusComboBox.getValue(),
                        bookerLastNameField.getText(),
                        bookerFirstNameField.getText(),
                        bookerPhoneNumberField.getText()
                    );
                } catch (Exception e) {
                    showAlert("Error", "An error occurred while generating the ID. Please check the entered fields.");
                    e.printStackTrace();
                }
            }
            return null;
        });

        dialog.showAndWait().ifPresent(reservation -> {
            try {
                reservationDAO.create(reservation);
                table.getItems().add(reservation);
                showAlert("Success", "Reservation successfully added!");
                excelFX.refreshData(); // Update Excel instantly
            } catch (Exception e) {
                showAlert("Error", "Error adding reservation. Please check if the entered IDs exist.");
                e.printStackTrace();
            }
        });           
    }


  //---------- Update Reservation Button Functionality ----------//
    private void updateReservation() {
        Reservation selectedReservation = table.getSelectionModel().getSelectedItem();
        if (selectedReservation == null) {
            showAlert("Information", "Please select a reservation to update...");
            return;
        }

        Dialog<Reservation> dialog = new Dialog<>();
        dialog.setTitle("Update Reservation");

        //------ Pre-filled input fields ------//
        TextField clientField = new TextField(String.valueOf(selectedReservation.getClientId()));
        TextField travelAgentField = new TextField(String.valueOf(selectedReservation.getTravelAgentId()));
        TextField flightField = new TextField(String.valueOf(selectedReservation.getFlightId()));

        DatePicker reservationDatePicker = new DatePicker(selectedReservation.getReservationDate());

        ComboBox<String> flightClassComboBox = new ComboBox<>();
        flightClassComboBox.getItems().addAll("economy", "first class", "business");
        flightClassComboBox.setValue(selectedReservation.getFlightClass());

        ComboBox<String> paymentTypeComboBox = new ComboBox<>();
        paymentTypeComboBox.getItems().addAll("credit_card", "bank_transfer", "paypal", "cash");
        paymentTypeComboBox.setValue(selectedReservation.getPaymentType());

        ComboBox<String> statusComboBox = new ComboBox<>();
        statusComboBox.getItems().addAll("confirmed", "canceled");
        statusComboBox.setValue(selectedReservation.getStatus());

        TextField bookerLastNameField = new TextField(selectedReservation.getBookerLastName());
        TextField bookerFirstNameField = new TextField(selectedReservation.getBookerFirstName());
        TextField bookerPhoneNumberField = new TextField(selectedReservation.getBookerPhoneNumber());

        VBox fields = new VBox(10,
            new Label("Client ID"), clientField,
            new Label("Travel Agent ID"), travelAgentField,
            new Label("Flight ID"), flightField,
            new Label("Reservation Date"), reservationDatePicker,
            new Label("Class"), flightClassComboBox,
            new Label("Payment Type"), paymentTypeComboBox,
            new Label("Status"), statusComboBox,
            new Label("Booker Last Name"), bookerLastNameField,
            new Label("Booker First Name"), bookerFirstNameField,
            new Label("Booker Phone Number"), bookerPhoneNumberField
        );
        fields.setPadding(new Insets(10));
        dialog.getDialogPane().setContent(fields);

        ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        Button saveButton = (Button) dialog.getDialogPane().lookupButton(saveButtonType);
        saveButton.addEventFilter(javafx.event.ActionEvent.ACTION, event -> {
            //------ Validate booker phone number ------//
            String bookerPhoneNumber = bookerPhoneNumberField.getText();
            if (bookerPhoneNumber.length() < 10 || bookerPhoneNumber.length() > 12) {
                showAlert("Error", "The phone number must be between 10 and 12 characters.");                
                event.consume(); 
            }
        });

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                selectedReservation.setClientId(Integer.parseInt(clientField.getText()));
                selectedReservation.setTravelAgentId(Integer.parseInt(travelAgentField.getText()));
                selectedReservation.setFlightId(Integer.parseInt(flightField.getText()));
                selectedReservation.setReservationDate(reservationDatePicker.getValue());
                selectedReservation.setFlightClass(flightClassComboBox.getValue());
                selectedReservation.setPaymentType(paymentTypeComboBox.getValue());
                selectedReservation.setStatus(statusComboBox.getValue());
                selectedReservation.setBookerLastName(bookerLastNameField.getText());
                selectedReservation.setBookerFirstName(bookerFirstNameField.getText());
                selectedReservation.setBookerPhoneNumber(bookerPhoneNumberField.getText());
                return selectedReservation;
            }
            return null;
        });

        dialog.showAndWait().ifPresent(reservation -> {
            try {
                reservationDAO.update(reservation);
                table.refresh();
                showAlert("Success", "Reservation successfully updated!");
                System.out.println("Reservation successfully updated!");
                excelFX.refreshData(); // Update Excel
            } catch (Exception e) {
                e.printStackTrace();
                showAlert("Error", "An error occurred while updating the reservation.");
            }
        });
    }


  //---------- Delete Reservation Button Functionality ----------//
    private void deleteReservation() {
        Reservation selectedReservation = table.getSelectionModel().getSelectedItem();
        if (selectedReservation == null) {
            showAlert("Information", "Please select a reservation to delete...");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Confirmation");
        alert.setContentText("Are you sure you want to delete this reservation?");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    reservationDAO.deleteByReservationId(selectedReservation.getReservationId());
                    table.getItems().remove(selectedReservation);
                    showAlert("Confirmation", "Reservation successfully deleted.");
                    System.out.println("Reservation successfully deleted.");
                    excelFX.refreshData(); // Update Excel
                } catch (Exception e) {
                    e.printStackTrace();
                    showAlert("Error", "An error occurred while deleting the reservation. Make sure there are no dependencies before deletion.");
                }
            }
        });
    }
}