INSERT INTO roles (name) 
SELECT 'USER' 
WHERE NOT EXISTS (SELECT 1 FROM roles WHERE name = 'USER');

INSERT INTO roles (name) 
SELECT 'ADMIN' 
WHERE NOT EXISTS (SELECT 1 FROM roles WHERE name = 'ADMIN');


-- Insertar usuario
INSERT INTO users (username, lastname, firstname, email, country, password, role_id) 
VALUES ('testuser', 'Doe', 'John', 'testuser@example.com', 'Argentina', 'testpassword', 1);

-- Insertar aeropuertos
INSERT INTO airports (name, city, country) 
VALUES ('El Prat', 'Barcelona', 'Spain'), 
       ('JFK', 'New York', 'USA');

-- Insertar vuelos
INSERT INTO flights (departure_time, arrival_time, origin_id, destination_id, price, available_seats) 
VALUES ('2024-12-25 12:00:00', '2024-12-25 15:00:00', 
        (SELECT id FROM airports WHERE name = 'El Prat' LIMIT 1), 
        (SELECT id FROM airports WHERE name = 'JFK' LIMIT 1), 300.00, 200);

-- Insertar asientos
INSERT INTO seats (flight_id, seat_number, seat_class, is_available) 
VALUES (1, '12A', 'Economy', true), 
       (1, '12B', 'Economy', true),
       (1, '12C', 'Economy', true);


-- Verifica que todo se haya insertado correctamente
SELECT * FROM users;
SELECT * FROM airports;
SELECT * FROM flights;
SELECT * FROM seats;