package fx;

import models.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import dao.ClientDAO;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.layout.VBox;

import java.util.Arrays;
import java.util.List;

public class ClientFX {
    private TableView<Client> table;
    private ClientDAO clientDAO;
    private ExcelViewFX excelFX;      

    public ClientFX(ExcelViewFX excelFX) {      // Inject ExcelViewFX into the constructor
        clientDAO = new ClientDAO();
        this.excelFX = excelFX;
    }

    public BorderPane getLayout() {
        BorderPane root = new BorderPane();

        //------ Table to display clients ------//
        table = new TableView<>();
        table.setItems(getClients());
        table.getColumns().addAll(createTableColumns());

        //------ CRUD Buttons ------//
        HBox buttons = createButtons();

        root.setCenter(table);
        root.setBottom(buttons);

        return root;
    }

    private ObservableList<Client> getClients() {
        try {
            return FXCollections.observableArrayList(clientDAO.findAll());
        } catch (Exception e) {
            e.printStackTrace();
            return FXCollections.observableArrayList();
        }
    }

    private List<TableColumn<Client, ?>> createTableColumns() {
        TableColumn<Client, Integer> idCol = new TableColumn<>("Client ID");
        idCol.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getClientId()));

        TableColumn<Client, String> lastNameCol = new TableColumn<>("Last Name");
        lastNameCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getLastName()));

        TableColumn<Client, String> firstNameCol = new TableColumn<>("First Name");
        firstNameCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFirstName()));

        TableColumn<Client, String> addressCol = new TableColumn<>("Address");
        addressCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAddress()));

        TableColumn<Client, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEmail()));

        TableColumn<Client, String> phoneNumberCol = new TableColumn<>("Phone Number");
        phoneNumberCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPhoneNumber()));

        return Arrays.asList(idCol, lastNameCol, firstNameCol, addressCol, emailCol, phoneNumberCol);
    }

    private HBox createButtons() {
        Button addButton = new Button("Add");
        Button updateButton = new Button("Update");
        Button deleteButton = new Button("Delete");

        addButton.setOnAction(e -> addClient());
        updateButton.setOnAction(e -> updateClient());
        deleteButton.setOnAction(e -> deleteClient());

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

    
    
    //---------- Add Client Button Functionality ----------//
    private void addClient() {
        Dialog<Client> dialog = new Dialog<>();
        dialog.setTitle("Add Client");

        //------ Input Fields ------//
        TextField lastNameField = new TextField();
        lastNameField.setPromptText("Last Name");

        TextField firstNameField = new TextField();
        firstNameField.setPromptText("First Name");

        TextField addressField = new TextField();
        addressField.setPromptText("Address");

        TextField emailField = new TextField();
        emailField.setPromptText("Email");

        TextField phoneNumberField = new TextField();
        phoneNumberField.setPromptText("Phone Number");

        VBox fields = new VBox(10,
            new Label("Last Name"), lastNameField,
            new Label("First Name"), firstNameField,
            new Label("Address"), addressField,
            new Label("Email"), emailField,
            new Label("Phone Number"), phoneNumberField
        );
        fields.setPadding(new Insets(10));
        dialog.getDialogPane().setContent(fields);

        ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        //------ Phone Number Validation ------//
        final Button saveButton = (Button) dialog.getDialogPane().lookupButton(saveButtonType);
        saveButton.addEventFilter(javafx.event.ActionEvent.ACTION, event -> {
            String phoneNumber = phoneNumberField.getText();

            if (phoneNumber.length() < 10 || phoneNumber.length() > 12) {
                showAlert("Error", "The phone number must contain between 10 and 12 characters.");
                event.consume(); 
            }
        });

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                try {
                    int newId = clientDAO.getLastClientId() + 1;

                    return new Client(newId,
                        lastNameField.getText(),
                        firstNameField.getText(),
                        addressField.getText(),
                        emailField.getText(),
                        phoneNumberField.getText()
                    );
                } catch (Exception e) {
                    showAlert("Error", "An error occurred while generating the ID.");
                    e.printStackTrace();
                }
            }
            return null;
        });

        dialog.showAndWait().ifPresent(client -> {
            try {
                clientDAO.create(client);
                table.getItems().add(client);
                showAlert("Success", "Client successfully added!");
            } catch (Exception e) {
                showAlert("Error", "An error occurred while adding the client.");
                e.printStackTrace();
            }
        });
    }

    
    
    //---------- Update Client Button Functionality ----------//
    private void updateClient() {
        Client selectedClient = table.getSelectionModel().getSelectedItem();
        if (selectedClient == null) {
            showAlert("Information", "Please select a client to update...");
            return;
        }

        Dialog<Client> dialog = new Dialog<>();
        dialog.setTitle("Update Client");

        //------ Prefilled Input Fields ------//
        TextField lastNameField = new TextField(selectedClient.getLastName());
        TextField firstNameField = new TextField(selectedClient.getFirstName());
        TextField addressField = new TextField(selectedClient.getAddress());
        TextField emailField = new TextField(selectedClient.getEmail());
        TextField phoneNumberField = new TextField(selectedClient.getPhoneNumber());

        VBox fields = new VBox(10,
            new Label("Last Name"), lastNameField,
            new Label("First Name"), firstNameField,
            new Label("Address"), addressField,
            new Label("Email"), emailField,
            new Label("Phone Number"), phoneNumberField);
        fields.setPadding(new Insets(10));
        dialog.getDialogPane().setContent(fields);

        ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                selectedClient.setLastName(lastNameField.getText());
                selectedClient.setFirstName(firstNameField.getText());
                selectedClient.setAddress(addressField.getText());
                selectedClient.setEmail(emailField.getText());
                selectedClient.setPhoneNumber(phoneNumberField.getText());
                return selectedClient;
            }
            return null;
        });

        dialog.showAndWait().ifPresent(client -> {
            try {
                clientDAO.update(client);
                table.refresh();
                showAlert("Success", "Client successfully updated!");
                excelFX.refreshData();
            } catch (Exception e) {
                showAlert("Error", "Error updating client.");
                e.printStackTrace();
            }
        });
    }



   	//---------- Delete Client Button Functionality ----------//
	private void deleteClient() {
	  Client selectedClient = table.getSelectionModel().getSelectedItem();
	  
	  if (selectedClient == null) {
	      showAlert("Information", "Please select a client to delete...");
	      return;
	  }
	
	  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	  alert.setTitle("Delete Confirmation");
	  alert.setContentText("Are you sure you want to delete this client?");
	  
	  alert.showAndWait().ifPresent(response -> {
	      if (response == ButtonType.OK) {
	          try {
	              clientDAO.deleteByClientId(selectedClient.getClientId());
	              table.getItems().remove(selectedClient);
	              showAlert("Confirmation", "Client successfully deleted.");
	              System.out.println("Client successfully deleted.");
	          } catch (Exception e) {
	              e.printStackTrace();
	              showAlert("Error", "Please delete all reservations associated with this client before removing him from the table.");
	              System.out.println("Please delete all reservations associated with this client before removing him from the table.");
	          }
	      }
	  });
	}


}