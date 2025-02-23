package fx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.util.Locale;


public class Main extends Application {
	
    public static void main(String[] args) {
        launch(args);
    }

    private ExcelViewFX excelFX; // For synchronization

    @Override
    public void start(Stage primaryStage) {
    	
    	Locale.setDefault(Locale.ENGLISH);
        
        excelFX = new ExcelViewFX();

        //------ Tab container ------//
        TabPane tabPane = new TabPane();

        //------ Tabs for each entity ------//
        Tab clientTab = createTab("Clients", new ClientFX(excelFX).getLayout());                // Pass excelFX for synchronization with Client
        Tab reservationTab = createTab("Reservations", new ReservationFX(excelFX).getLayout()); // Pass excelFX for synchronization with Reservation
        Tab flightTab = createTab("Flights", new FlightFX().getLayout());
        Tab airlineTab = createTab("Airlines", new AirlineFX().getLayout());
        Tab travelAgentTab = createTab("Travel Agents", new TravelAgentFX().getLayout());
        Tab excelTab = createTab("Excel File", excelFX.getLayout());

        //------ Adding tabs to the container ------//
        tabPane.getTabs().addAll(clientTab, reservationTab, flightTab, airlineTab, travelAgentTab, excelTab);

        //------ Layout setup ------//
        BorderPane root = new BorderPane();
        root.setCenter(tabPane);

        //------ Window configuration ------//
        Scene scene = new Scene(root, 1000, 700);
        primaryStage.setTitle("Travel Agent Interface for Booking a Flight for a Client");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Tab createTab(String title, BorderPane content) {
        final Tab tab = new Tab(title, content);
        tab.setClosable(false);
        return tab;
    }
}