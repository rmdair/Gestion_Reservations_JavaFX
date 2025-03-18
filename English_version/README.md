<div align="center">
  <h1> Travel Agency Reservation Management</h1>
</div>

## Overview

This JavaFX application is designed as **a dedicated workspace for travel agency staff**.  
It allows them to easily manage client reservations while accessing pre-filled flight and airline information, which is updated by the administration.  

The application is based on a **MySQL database** and offers different levels of access:  
- **Travel agents** manage clients and their reservations only.  
- **The administration** controls flights, airlines, and agency personnel.  

## Conceptual Data Model (CDM)

Here is the structure of the project's main entities:

![CDM](Conceptual_Data_Model.png)

Reservations are the central element of the model, linked to clients, travel agents, and flights via foreign keys.  
Each reservation is associated with a client who books it, a travel agent who manages it, and a corresponding flight.

## Features

Travel agents can:  
- Add, modify, or cancel a client's reservation.  
- View available flights and operating airlines.  
- Access a global view of reservations in Excel format.  
- Manage clients and track their travel history.  

---

## 📂 Project Structure

```plaintext
docker/                 → MySQL container configuration
├── docker-compose.yml  → Database deployment
├── initialization.sql  → Fictitious SQL initialization script for testing

src/                    → Project source code
├── config/             → MySQL connection management
│   ├── DatabaseConnection.java
│   ├── test.java
├── models/             → Entity definitions
│   ├── Client.java
│   ├── TravelAgent.java
│   ├── Airline.java
│   ├── Flight.java
│   ├── Reservation.java
├── dao/                → Data access (CRUD operations)
│   ├── ClientDAO.java
│   ├── TravelAgentDAO.java
│   ├── AirlineDAO.java
│   ├── FlightDAO.java
│   ├── ReservationDAO.java
├── fx/                 → JavaFX graphical interface
│   ├── ClientFX.java
│   ├── TravelAgentFX.java
│   ├── AirlineFX.java
│   ├── FlightFX.java
│   ├── ReservationFX.java
│   ├── ExcelViewFX.java
│   ├── Main.java       → Main application class

executable.jar          → Executable file to launch the application
```

---

## Docker Commands

**Start the MySQL database**

```bash
docker-compose up -d
```

**Access MySQL inside the container** 
```bash
docker exec -it project_travel_agency mysql -u project_travel_agency -p      
```
or
```bash
docker exec -it project_travel_agency mysql -u root -p
```

**Stop and remove the container**  
```bash
docker-compose down
```

---


## 🖥️ Launching the JavaFX Interface

### Running from Eclipse  
**Eclipse IDE** is a powerful and widely used integrated development environment for Java programming.
The graphical interface can be launched directly from Eclipse, which is practical if one wants to develop the application's code:  

1. **Create a new Java project in Eclipse**
    
2. **Add the existing `src/` directory to the project**  
   - Right-click on the newly created project in **Project Explorer**  
   - Select **Properties → Java Build Path → Source**  
   - Click **Add Folder...**, select the existing [`src/`](../src/) directory, and click **Apply and Close**  

3. **Add the required libraries**:  
   - Download **JavaFX SDK** from [the official website](https://openjfx.io/)  
   - Download the **MySQL connector** from [the official website](https://www.mysql.com/products/connector/)  
   - Add these libraries to the project via **Build Path → Configure Build Path → Add External JARs**  

4. **Select `Main.java` in the `fx` package**  

5. **Click "Run"** to launch the application  

### Running from the Terminal  
The application can also be executed outside the IDE using the following command:  
```bash
java -p "javafx-sdk-23.0.1/lib" --add-modules javafx.controls,javafx.base,javafx.fxml,javafx.graphics,javafx.media,javafx.web --add-opens=javafx.graphics/javafx.scene=ALL-UNNAMED --add-exports javafx.base/com.sun.javafx.event=ALL-UNNAMED -jar executable.jar
```

---

## 📖 Documentation
One detailed report about the project is available:

**French Version** : [Lire le rapport en français](../rapport_gestion_reservations.pdf)  
**English Version** : [Read the report in English](reservations_management_report.pdf)  

---

## 📸 Application Overview

### Clients Tab
![Accueil](../pictures/update_client.png)

### Reservations Tab  
![Réservations](../pictures/update_reservation.png)

### Flights Tab 
![Vols](../pictures/flights.png)

### Airlines Tab  
![Compagnies](../pictures/airlines.png)

### Travel Agents Tab  
![Conseillers](../pictures/travel_agents.png)

### Excel Overview  
![Excel](../pictures/excel_window.png)

---


**Clone and Run the Project**  
```bash
git clone https://github.com/rmdair/Gestion_Reservations_JavaFX.git
```
```bash
cd Gestion_Reservations_JavaFX
```
```bash
java -p "javafx-sdk-23.0.1/lib" --add-modules javafx.controls,javafx.base,javafx.fxml,javafx.graphics,javafx.media,javafx.web --add-opens=javafx.graphics/javafx.scene=ALL-UNNAMED --add-exports javafx.base/com.sun.javafx.event=ALL-UNNAMED -jar executable.jar
```
