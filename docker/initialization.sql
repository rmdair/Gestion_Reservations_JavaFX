CREATE TABLE Client (
    ClientID INT AUTO_INCREMENT PRIMARY KEY,
    LastName VARCHAR(100) NOT NULL,
    FirstName VARCHAR(100) NOT NULL,
    Address VARCHAR(255),
    Email VARCHAR(100),
    PhoneNumber VARCHAR(15)
);


CREATE TABLE TravelAgent (
    TravelAgentID INT AUTO_INCREMENT PRIMARY KEY,
    LastName VARCHAR(100) NOT NULL,
    FirstName VARCHAR(100) NOT NULL,
    Email VARCHAR(100),
    PhoneNumber VARCHAR(15)
);


CREATE TABLE Airline (
    AirlineID INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(100) NOT NULL,
    CountryOfOrigin VARCHAR(100) NOT NULL
);


CREATE TABLE Flight (
    FlightID INT AUTO_INCREMENT PRIMARY KEY,
    AirlineID INT NOT NULL,
    DepartureLocation VARCHAR(100) NOT NULL,
    ArrivalLocation VARCHAR(100) NOT NULL,
    DepartureDate DATE NOT NULL,
    DepartureTime TIME NOT NULL,
    ArrivalDate DATE NOT NULL,
    ArrivalTime TIME NOT NULL,
    FOREIGN KEY (AirlineID) REFERENCES Airline(AirlineID)
);


CREATE TABLE Reservation (
    ReservationID INT AUTO_INCREMENT PRIMARY KEY,
    ClientID INT NOT NULL,
    TravelAgentID INT NOT NULL,
    FlightID INT NOT NULL,
    ReservationDate DATE NOT NULL,
    Class ENUM('economy', 'first class', 'business') NOT NULL,
    PaymentType ENUM('credit_card', 'bank_transfer', 'paypal', 'cash') NOT NULL, 
    Status ENUM('confirmed', 'canceled') NOT NULL,
    BookerLastName VARCHAR(100),
    BookerFirstName VARCHAR(100),
    BookerPhoneNumber VARCHAR(15),
    FOREIGN KEY (ClientID) REFERENCES Client(ClientID),
    FOREIGN KEY (TravelAgentID) REFERENCES TravelAgent(TravelAgentID),
    FOREIGN KEY (FlightID) REFERENCES Flight(FlightID)
);



INSERT INTO Client (LastName, FirstName, Address, Email, PhoneNumber)
VALUES
('SMITH', 'James', '123 Oxford Street, London, UK', 'james.smith@example.co.uk', '447911123456'),
('BROWN', 'Olivia', '45 High Street, Manchester, UK', 'olivia.brown@example.co.uk', '447922234567'),
('WILSON', 'Ethan', '78 Baker Street, Birmingham, UK', 'ethan.wilson@example.co.uk', '447933345678'),
('JOHNSON', 'Emily', '90 Royal Road, Liverpool, UK', 'emily.johnson@example.co.uk', '447944456789'),
('TAYLOR', 'George', '22 Princes Street, Bristol, UK', 'george.taylor@example.co.uk', '447955567890'),
('ANDERSON', 'Sophia', '8 Park Lane, Newcastle, UK', 'sophia.anderson@example.co.uk', '447966678901'),
('THOMAS', 'Jacob', '11 Piccadilly, Leeds, UK', 'jacob.thomas@example.co.uk', '447977789012'),
('ROBERTS', 'Charlotte', '33 Regent Street, Sheffield, UK', 'charlotte.roberts@example.co.uk', '447988890123'),
('HARRIS', 'Daniel', '15 Victoria Road, Nottingham, UK', 'daniel.harris@example.co.uk', '447999901234'),
('CLARK', 'Isabella', '50 Tower Bridge Road, London, UK', 'isabella.clark@example.co.uk', '447910012345'),
('WALKER', 'Mason', '21 Famous Street, Edinburgh, UK', 'mason.walker@example.co.uk', '447911123456'),
('LEE', 'Hannah', '17 George Square, Glasgow, UK', 'hannah.lee@example.co.uk', '447912234567'),
('EVANS', 'Matthew', '32 Albert Dock, Liverpool, UK', 'matthew.evans@example.co.uk', '447913345678');


INSERT INTO TravelAgent (LastName, FirstName, Email, PhoneNumber)
VALUES
('DAVIES', 'Oliver', 'oliver.davies@travelagency.co.uk', '447914456789'),
('WILLIAMS', 'Emma', 'emma.williams@travelagency.co.uk', '447915567890'),
('JONES', 'Benjamin', 'benjamin.jones@travelagency.co.uk', '447916678901'),
('WHITE', 'Mia', 'mia.white@travelagency.co.uk', '447917789012'),
('HALL', 'Samuel', 'samuel.hall@travelagency.co.uk', '447918890123');


INSERT INTO Airline (AirlineID, Name, CountryOfOrigin)
VALUES
(1, 'Emirates', 'United Arab Emirates'),
(2, 'Qatar Airways', 'Qatar'),
(3, 'Swiss International AirLines', 'Switzerland'),
(4, 'Royal Air Maroc', 'Morocco'),
(5, 'Air France', 'France'),
(6, 'British Airways', 'United Kingdom'),
(7, 'Lufthansa', 'Germany'),
(8, 'Delta Airlines', 'United States'),
(9, 'Ryanair', 'Ireland'),
(10, 'Turkish Airlines', 'Turkey'),
(11, 'Cathay Pacific', 'Hong Kong'),
(12, 'All Nippon Airways', 'Japan'),
(13, 'Qantas', 'Australia'),
(14, 'Malaysia Airlines', 'Malaysia'),
(15, 'KLM', 'Netherlands'),
(16, 'Iberia', 'Spain'),
(17, 'Austrian Airlines', 'Austria'),
(18, 'IndiGo', 'India'),
(19, 'South African Airways', 'South Africa'),
(20, 'Singapore Airlines', 'Singapore');



INSERT INTO Flight (AirlineID, DepartureLocation, ArrivalLocation, DepartureDate, DepartureTime, ArrivalDate, ArrivalTime)
VALUES
(1, 'Dubai', 'Paris', '2025-07-20', '14:00:00', '2025-07-20', '18:00:00'),
(2, 'Doha', 'London', '2025-07-21', '15:30:00', '2025-07-21', '19:30:00'),
(3, 'Zurich', 'Charleroi', '2025-07-22', '11:00:00', '2025-07-22', '12:40:00'),
(4, 'Casablanca', 'Bern', '2025-07-25', '10:00:00', '2025-07-25', '12:55:00'),
(5, 'Paris', 'Tokyo', '2025-07-25', '22:30:00', '2025-07-26', '17:00:00'),
(6, 'London', 'Los Angeles', '2025-07-26', '21:00:00', '2025-07-27', '05:30:00'),
(7, 'Frankfurt', 'Tokyo', '2025-07-28', '13:00:00', '2025-07-29', '01:00:00'),
(8, 'Atlanta', 'Toronto', '2025-08-01', '09:00:00', '2025-08-01', '10:30:00'),
(9, 'Dublin', 'Brussels', '2025-08-02', '08:00:00', '2025-08-02', '09:45:00'),
(10, 'Istanbul', 'Rome', '2025-08-03', '12:00:00', '2025-08-03', '13:30:00'),
(11, 'Hong Kong', 'San Francisco', '2025-08-03', '16:00:00', '2025-08-04', '08:00:00'),
(12, 'Tokyo', 'Los Angeles', '2025-08-06', '19:00:00', '2025-08-07', '11:00:00'),
(13, 'Sydney', 'Auckland', '2025-09-01', '09:00:00', '2025-09-01', '10:30:00'),
(14, 'Kuala Lumpur', 'Bali', '2025-09-02', '11:00:00', '2025-09-02', '12:00:00'),
(15, 'Amsterdam', 'Bordeaux', '2025-09-03', '16:30:00', '2025-09-03', '18:30:00'),
(16, 'Madrid', 'Lisbon', '2025-09-04', '10:00:00', '2025-09-04', '11:00:00'),
(17, 'Vienna', 'Warsaw', '2025-10-05', '14:00:00', '2025-10-05', '15:30:00'),
(18, 'New Delhi', 'Mumbai', '2025-10-06', '09:00:00', '2025-10-06', '10:30:00'),
(19, 'Johannesburg', 'Cape Town', '2025-10-07', '11:00:00', '2025-10-07', '12:00:00'),
(20, 'Singapore', 'New York', '2025-10-22', '23:30:00', '2025-10-23', '17:00:00');


INSERT INTO Reservation (ClientID, TravelAgentID, FlightID, ReservationDate, Class, PaymentType, Status, BookerLastName, BookerFirstName, BookerPhoneNumber)
VALUES
(1, 4, 1, '2025-01-10', 'economy', 'credit_card', 'confirmed', 'SMITH', 'James', '447911123456'),
(2, 5, 3, '2025-01-11', 'business', 'bank_transfer', 'confirmed', 'BROWN', 'Olivia', '447922234567'),
(3, 1, 5, '2025-01-12', 'first class', 'paypal', 'canceled', 'WILSON', 'Ethan', '447933345678'),
(4, 3, 7, '2025-01-13', 'economy', 'cash', 'confirmed', 'JOHNSON', 'Emily', '447944456789'),
(5, 2, 9, '2025-01-14', 'business', 'credit_card', 'confirmed', 'TAYLOR', 'George', '447955567890');
