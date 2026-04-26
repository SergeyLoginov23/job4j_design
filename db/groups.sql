CREATE TABLE devices
(
    id    SERIAL PRIMARY KEY,
    name  VARCHAR(255),
    price FLOAT
);

CREATE TABLE people
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE devices_people
(
    id        SERIAL PRIMARY KEY,
    device_id INT REFERENCES devices (id),
    people_id INT REFERENCES people (id)
);


INSERT INTO devices(name, price) VALUES
('iPhone 15', 1200),
('MacBook Pro', 3000),
('Gaming PC', 2500),
('Server Dell', 8000),
('Workstation HP', 6000);


INSERT INTO people(name) VALUES
('Alex'),
('Maria'),
('John');

INSERT INTO devices_people(device_id, people_id) VALUES
(1, 1),
(2, 1),
(3, 2),
(4, 2),
(5, 3),
(2, 3);

SELECT AVG(price) AS avg_price
FROM devices;

SELECT p.name, AVG(d.price) AS avg_price
FROM people p
JOIN devices_people dp ON p.id = dp.people_id
JOIN devices d ON dp.device_id = d.id
GROUP BY p.name;

SELECT p.name, AVG(d.price) AS avg_price
FROM people p
JOIN devices_people dp ON p.id = dp.people_id
JOIN devices d ON dp.device_id = d.id
GROUP BY p.name
HAVING AVG(d.price) > 5000;