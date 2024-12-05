INSERT INTO roles (name) 
SELECT 'USER' 
WHERE NOT EXISTS (SELECT 1 FROM roles WHERE name = 'USER');

INSERT INTO roles (name) 
SELECT 'ADMIN' 
WHERE NOT EXISTS (SELECT 1 FROM roles WHERE name = 'ADMIN');


-- Insertar usuario
INSERT INTO users (username, lastname, firstname, email, country, password, role_id) 
VALUES ('testuser', 'Doe', 'John', 'testuser@example.com', 'Argentina', 'testpassword', 1);

INSERT INTO airports (name, city, country) 
VALUES 
('Heathrow', 'London', 'UK'),
('Charles de Gaulle', 'Paris', 'France'),
('Schiphol', 'Amsterdam', 'Netherlands'),
('Los Angeles International', 'Los Angeles', 'USA'),
('Dubai International', 'Dubai', 'UAE'),
('Tokyo Narita', 'Tokyo', 'Japan'),
('Kuala Lumpur International', 'Kuala Lumpur', 'Malaysia'),
('Hong Kong International', 'Hong Kong', 'China'),
('Frankfurt Airport', 'Frankfurt', 'Germany'),
('Sydney Kingsford Smith', 'Sydney', 'Australia'),
('Changi Airport', 'Singapore', 'Singapore'),
('Madrid Barajas', 'Madrid', 'Spain'),
('Rome Fiumicino', 'Rome', 'Italy'),
('Beijing Capital', 'Beijing', 'China'),
('Moscow Sheremetyevo', 'Moscow', 'Russia');



-- Insertar vuelos
INSERT INTO flights (departure_time, arrival_time, origin_id, destination_id, price, available_seats) 
VALUES
('2024-12-26 06:00:00', '2024-12-26 09:00:00', 
    (SELECT id FROM airports WHERE name = 'Heathrow' LIMIT 1), 
    (SELECT id FROM airports WHERE name = 'Charles de Gaulle' LIMIT 1), 150.00, 200),
('2024-12-26 13:00:00', '2024-12-26 16:30:00', 
    (SELECT id FROM airports WHERE name = 'Heathrow' LIMIT 1), 
    (SELECT id FROM airports WHERE name = 'Charles de Gaulle' LIMIT 1), 150.00, 200),
('2024-12-26 15:00:00', '2024-12-26 18:30:00', 
    (SELECT id FROM airports WHERE name = 'Heathrow' LIMIT 1), 
    (SELECT id FROM airports WHERE name = 'Charles de Gaulle' LIMIT 1), 150.00, 200),
('2024-12-26 07:30:00', '2024-12-26 10:30:00', 
    (SELECT id FROM airports WHERE name = 'Los Angeles International' LIMIT 1), 
    (SELECT id FROM airports WHERE name = 'Changi Airport' LIMIT 1), 350.00, 150),
('2024-12-26 08:00:00', '2024-12-26 14:00:00', 
    (SELECT id FROM airports WHERE name = 'Tokyo Narita' LIMIT 1), 
    (SELECT id FROM airports WHERE name = 'Los Angeles International' LIMIT 1), 500.00, 250),
('2024-12-27 11:00:00', '2024-12-27 13:00:00', 
    (SELECT id FROM airports WHERE name = 'Schiphol' LIMIT 1), 
    (SELECT id FROM airports WHERE name = 'Frankfurt Airport' LIMIT 1), 200.00, 180),
('2024-12-27 10:15:00', '2024-12-27 12:45:00', 
    (SELECT id FROM airports WHERE name = 'Dubai International' LIMIT 1), 
    (SELECT id FROM airports WHERE name = 'Hong Kong International' LIMIT 1), 350.00, 230),
('2024-12-28 09:30:00', '2024-12-28 12:00:00', 
    (SELECT id FROM airports WHERE name = 'Kuala Lumpur International' LIMIT 1), 
    (SELECT id FROM airports WHERE name = 'Changi Airport' LIMIT 1), 120.00, 200),
('2024-12-28 12:30:00', '2024-12-28 15:30:00', 
    (SELECT id FROM airports WHERE name = 'Moscow Sheremetyevo' LIMIT 1), 
    (SELECT id FROM airports WHERE name = 'Beijing Capital' LIMIT 1), 220.00, 210),
('2024-12-29 07:00:00', '2024-12-29 10:00:00', 
    (SELECT id FROM airports WHERE name = 'Sydney Kingsford Smith' LIMIT 1), 
    (SELECT id FROM airports WHERE name = 'Rome Fiumicino' LIMIT 1), 250.00, 180),
('2024-12-29 15:00:00', '2024-12-29 20:00:00', 
    (SELECT id FROM airports WHERE name = 'Changi Airport' LIMIT 1), 
    (SELECT id FROM airports WHERE name = 'Madrid Barajas' LIMIT 1), 275.00, 220),
('2024-12-30 13:30:00', '2024-12-30 17:00:00', 
    (SELECT id FROM airports WHERE name = 'Rome Fiumicino' LIMIT 1), 
    (SELECT id FROM airports WHERE name = 'Beijing Capital' LIMIT 1), 350.00, 200),
('2024-12-30 08:00:00', '2024-12-30 12:00:00', 
    (SELECT id FROM airports WHERE name = 'Changi Airport' LIMIT 1), 
    (SELECT id FROM airports WHERE name = 'Los Angeles International' LIMIT 1), 220.00, 180),
('2024-12-30 22:00:00', '2024-12-31 02:00:00', 
    (SELECT id FROM airports WHERE name = 'Dubai International' LIMIT 1), 
    (SELECT id FROM airports WHERE name = 'Hong Kong International' LIMIT 1), 400.00, 190),
('2024-12-31 05:00:00', '2024-12-31 07:00:00', 
    (SELECT id FROM airports WHERE name = 'Charles de Gaulle' LIMIT 1), 
    (SELECT id FROM airports WHERE name = 'Heathrow' LIMIT 1), 150.00, 250),
('2024-12-31 16:30:00', '2024-12-31 19:00:00', 
    (SELECT id FROM airports WHERE name = 'Moscow Sheremetyevo' LIMIT 1), 
    (SELECT id FROM airports WHERE name = 'Schiphol' LIMIT 1), 180.00, 220),
('2024-12-31 18:45:00', '2024-12-31 21:15:00', 
    (SELECT id FROM airports WHERE name = 'Sydney Kingsford Smith' LIMIT 1), 
    (SELECT id FROM airports WHERE name = 'Tokyo Narita' LIMIT 1), 350.00, 200);



INSERT INTO seats (flight_id, seat_number, seat_class, is_available) 
VALUES
-- Para el vuelo 1 (ID: 1)
(1, '1A', 'Economy', true), (1, '1B', 'Economy', true), (1, '1C', 'Economy', true),
(1, '2A', 'Economy', true), (1, '2B', 'Economy', true), (1, '2C', 'Economy', true),
(1, '3A', 'Economy', true), (1, '3B', 'Economy', true), (1, '3C', 'Economy', true),
(1, '4A', 'Economy', true), (1, '4B', 'Economy', true), (1, '4C', 'Economy', true),
(1, '5A', 'Economy', true), (1, '5B', 'Economy', true), (1, '5C', 'Economy', true),
(1, '6A', 'Economy', true), (1, '6B', 'Economy', true), (1, '6C', 'Economy', true),
(1, '7A', 'Economy', true), (1, '7B', 'Economy', true), (1, '7C', 'Economy', true),

-- Para el vuelo 2 (ID: 2)
(2, '1A', 'Economy', true), (2, '1B', 'Economy', true), (2, '1C', 'Economy', true),
(2, '2A', 'Economy', true), (2, '2B', 'Economy', true), (2, '2C', 'Economy', true),
(2, '3A', 'Economy', true), (2, '3B', 'Economy', true), (2, '3C', 'Economy', true),
(2, '4A', 'Economy', true), (2, '4B', 'Economy', true), (2, '4C', 'Economy', true),
(2, '5A', 'Economy', true), (2, '5B', 'Economy', true), (2, '5C', 'Economy', true),
(2, '6A', 'Economy', true), (2, '6B', 'Economy', true), (2, '6C', 'Economy', true),
(2, '7A', 'Economy', true), (2, '7B', 'Economy', true), (2, '7C', 'Economy', true),


(3, '1A', 'Economy', true), (3, '1B', 'Economy', true), (3, '1C', 'Economy', true),
(3, '2A', 'Economy', true), (3, '2B', 'Economy', true), (3, '2C', 'Economy', true),
(3, '3A', 'Economy', true), (3, '3B', 'Economy', true), (3, '3C', 'Economy', true),
(3, '4A', 'Economy', true), (3, '4B', 'Economy', true), (3, '4C', 'Economy', true),
(3, '5A', 'Economy', true), (3, '5B', 'Economy', true), (3, '5C', 'Economy', true),
(3, '6A', 'Economy', true), (3, '6B', 'Economy', true), (3, '6C', 'Economy', true),
(3, '7A', 'Economy', true), (3, '7B', 'Economy', true), (3, '7C', 'Economy', true),


(4, '1A', 'Economy', true), (4, '1B', 'Economy', true), (4, '1C', 'Economy', true),
(4, '2A', 'Economy', true), (4, '2B', 'Economy', true), (4, '2C', 'Economy', true),
(4, '3A', 'Economy', true), (4, '3B', 'Economy', true), (4, '3C', 'Economy', true),
(4, '4A', 'Economy', true), (4, '4B', 'Economy', true), (4, '4C', 'Economy', true),
(4, '5A', 'Economy', true), (4, '5B', 'Economy', true), (4, '5C', 'Economy', true),
(4, '6A', 'Economy', true), (4, '6B', 'Economy', true), (4, '6C', 'Economy', true),
(4, '7A', 'Economy', true), (4, '7B', 'Economy', true), (4, '7C', 'Economy', true),


(5, '1A', 'Economy', true), (5, '1B', 'Economy', true), (5, '1C', 'Economy', true),
(5, '2A', 'Economy', true), (5, '2B', 'Economy', true), (5, '2C', 'Economy', true),
(5, '3A', 'Economy', true), (5, '3B', 'Economy', true), (5, '3C', 'Economy', true),
(5, '4A', 'Economy', true), (5, '4B', 'Economy', true), (5, '4C', 'Economy', true),
(5, '5A', 'Economy', true), (5, '5B', 'Economy', true), (5, '5C', 'Economy', true),
(5, '6A', 'Economy', true), (5, '6B', 'Economy', true), (5, '6C', 'Economy', true),
(5, '7A', 'Economy', true), (5, '7B', 'Economy', true), (5, '7C', 'Economy', true),


(6, '1A', 'Economy', true), (6, '1B', 'Economy', true), (6, '1C', 'Economy', true),
(6, '2A', 'Economy', true), (6, '2B', 'Economy', true), (6, '2C', 'Economy', true),
(6, '3A', 'Economy', true), (6, '3B', 'Economy', true), (6, '3C', 'Economy', true),
(6, '4A', 'Economy', true), (6, '4B', 'Economy', true), (6, '4C', 'Economy', true),
(6, '5A', 'Economy', true), (6, '5B', 'Economy', true), (6, '5C', 'Economy', true),
(6, '6A', 'Economy', true), (6, '6B', 'Economy', true), (6, '6C', 'Economy', true),
(6, '7A', 'Economy', true), (6, '7B', 'Economy', true), (6, '7C', 'Economy', true),


(7, '1A', 'Economy', true), (7, '1B', 'Economy', true), (7, '1C', 'Economy', true),
(7, '2A', 'Economy', true), (7, '2B', 'Economy', true), (7, '2C', 'Economy', true),
(7, '3A', 'Economy', true), (7, '3B', 'Economy', true), (7, '3C', 'Economy', true),
(7, '4A', 'Economy', true), (7, '4B', 'Economy', true), (7, '4C', 'Economy', true),
(7, '5A', 'Economy', true), (7, '5B', 'Economy', true), (7, '5C', 'Economy', true),
(7, '6A', 'Economy', true), (7, '6B', 'Economy', true), (7, '6C', 'Economy', true),
(7, '7A', 'Economy', true), (7, '7B', 'Economy', true), (7, '7C', 'Economy', true),


(8, '1A', 'Economy', true), (8, '1B', 'Economy', true), (8, '1C', 'Economy', true),
(8, '2A', 'Economy', true), (8, '2B', 'Economy', true), (8, '2C', 'Economy', true),
(8, '3A', 'Economy', true), (8, '3B', 'Economy', true), (8, '3C', 'Economy', true),
(8, '4A', 'Economy', true), (8, '4B', 'Economy', true), (8, '4C', 'Economy', true),
(8, '5A', 'Economy', true), (8, '5B', 'Economy', true), (8, '5C', 'Economy', true),
(8, '6A', 'Economy', true), (8, '6B', 'Economy', true), (8, '6C', 'Economy', true),
(8, '7A', 'Economy', true), (8, '7B', 'Economy', true), (8, '7C', 'Economy', true),


(9, '1A', 'Economy', true), (9, '1B', 'Economy', true), (9, '1C', 'Economy', true),
(9, '2A', 'Economy', true), (9, '2B', 'Economy', true), (9, '2C', 'Economy', true),
(9, '3A', 'Economy', true), (9, '3B', 'Economy', true), (9, '3C', 'Economy', true),
(9, '4A', 'Economy', true), (9, '4B', 'Economy', true), (9, '4C', 'Economy', true),
(9, '5A', 'Economy', true), (9, '5B', 'Economy', true), (9, '5C', 'Economy', true),
(9, '6A', 'Economy', true), (9, '6B', 'Economy', true), (9, '6C', 'Economy', true),
(9, '7A', 'Economy', true), (9, '7B', 'Economy', true), (9, '7C', 'Economy', true),


(10, '1A', 'Economy', true), (10, '1B', 'Economy', true), (10, '1C', 'Economy', true),
(10, '2A', 'Economy', true), (10, '2B', 'Economy', true), (10, '2C', 'Economy', true),
(10, '3A', 'Economy', true), (10, '3B', 'Economy', true), (10, '3C', 'Economy', true),
(10, '4A', 'Economy', true), (10, '4B', 'Economy', true), (10, '4C', 'Economy', true),
(10, '5A', 'Economy', true), (10, '5B', 'Economy', true), (10, '5C', 'Economy', true),
(10, '6A', 'Economy', true), (10, '6B', 'Economy', true), (10, '6C', 'Economy', true),
(10, '7A', 'Economy', true), (10, '7B', 'Economy', true), (10, '7C', 'Economy', true),

(11, '1A', 'Economy', true), (10, '1B', 'Economy', true), (10, '1C', 'Economy', true),
(11, '2A', 'Economy', true), (10, '2B', 'Economy', true), (10, '2C', 'Economy', true),
(11, '3A', 'Economy', true), (10, '3B', 'Economy', true), (10, '3C', 'Economy', true),
(11, '4A', 'Economy', true), (10, '4B', 'Economy', true), (10, '4C', 'Economy', true),
(11, '5A', 'Economy', true), (10, '5B', 'Economy', true), (10, '5C', 'Economy', true),
(11, '6A', 'Economy', true), (10, '6B', 'Economy', true), (10, '6C', 'Economy', true),
(11, '7A', 'Economy', true), (10, '7B', 'Economy', true), (10, '7C', 'Economy', true),

(12, '1A', 'Economy', true), (10, '1B', 'Economy', true), (10, '1C', 'Economy', true),
(12, '2A', 'Economy', true), (10, '2B', 'Economy', true), (10, '2C', 'Economy', true),
(12, '3A', 'Economy', true), (10, '3B', 'Economy', true), (10, '3C', 'Economy', true),
(12, '4A', 'Economy', true), (10, '4B', 'Economy', true), (10, '4C', 'Economy', true),
(12, '5A', 'Economy', true), (10, '5B', 'Economy', true), (10, '5C', 'Economy', true),
(12, '6A', 'Economy', true), (10, '6B', 'Economy', true), (10, '6C', 'Economy', true),
(12, '7A', 'Economy', true), (10, '7B', 'Economy', true), (10, '7C', 'Economy', true),

(13, '1A', 'Economy', true), (10, '1B', 'Economy', true), (10, '1C', 'Economy', true),
(13, '2A', 'Economy', true), (10, '2B', 'Economy', true), (10, '2C', 'Economy', true),
(13, '3A', 'Economy', true), (10, '3B', 'Economy', true), (10, '3C', 'Economy', true),
(13, '4A', 'Economy', true), (10, '4B', 'Economy', true), (10, '4C', 'Economy', true),
(13, '5A', 'Economy', true), (10, '5B', 'Economy', true), (10, '5C', 'Economy', true),
(13, '6A', 'Economy', true), (10, '6B', 'Economy', true), (10, '6C', 'Economy', true),
(13, '7A', 'Economy', true), (10, '7B', 'Economy', true), (10, '7C', 'Economy', true),

(14, '1A', 'Economy', true), (10, '1B', 'Economy', true), (10, '1C', 'Economy', true),
(14, '2A', 'Economy', true), (10, '2B', 'Economy', true), (10, '2C', 'Economy', true),
(14, '3A', 'Economy', true), (10, '3B', 'Economy', true), (10, '3C', 'Economy', true),
(14, '4A', 'Economy', true), (10, '4B', 'Economy', true), (10, '4C', 'Economy', true),
(14, '5A', 'Economy', true), (10, '5B', 'Economy', true), (10, '5C', 'Economy', true),
(14, '6A', 'Economy', true), (10, '6B', 'Economy', true), (10, '6C', 'Economy', true),
(14, '7A', 'Economy', true), (10, '7B', 'Economy', true), (10, '7C', 'Economy', true),

(15, '1A', 'Economy', true), (10, '1B', 'Economy', true), (10, '1C', 'Economy', true),
(15, '2A', 'Economy', true), (10, '2B', 'Economy', true), (10, '2C', 'Economy', true),
(15, '3A', 'Economy', true), (10, '3B', 'Economy', true), (10, '3C', 'Economy', true),
(15, '4A', 'Economy', true), (10, '4B', 'Economy', true), (10, '4C', 'Economy', true),
(15, '5A', 'Economy', true), (10, '5B', 'Economy', true), (10, '5C', 'Economy', true),
(15, '6A', 'Economy', true), (10, '6B', 'Economy', true), (10, '6C', 'Economy', true),
(15, '7A', 'Economy', true), (10, '7B', 'Economy', true), (10, '7C', 'Economy', true);


