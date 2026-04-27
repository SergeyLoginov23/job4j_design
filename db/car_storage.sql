CREATE TABLE car_bodies(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE car_engines(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE car_transmissions(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE cars(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    body_id INT REFERENCES car_bodies(id),
    engine_id INT REFERENCES car_engines(id),
    transmission_id INT REFERENCES car_transmissions(id)
);

INSERT INTO car_bodies(name) VALUES
('Седан'),
('Хэтчбек'),
('SUV');
('Фургон');

INSERT INTO car_engines(name) VALUES
('Бензиновый'),
('Дизельный'),
('Электрический');
('Гибрид');

INSERT INTO car_transmissions(name) VALUES
('Механика'),
('Автомат'),
('Вариатор');
('Робот');

INSERT INTO cars(name, body_id, engine_id, transmission_id) VALUES
('Toyota Camry', 1, 1, 2),
('Volkswagen Golf', 2, null, 1),
('Tesla Model 3', 1, 3, 2),
('BMW X5', 3, 1, 2);

SELECT c.id, c.name, cb.name AS body_name, ce.name AS engine_name, ct.name AS transmission_name
FROM cars c
LEFT JOIN car_bodies cb ON cb.id = c.body_id
LEFT JOIN car_engines ce ON ce.id = c.engine_id
LEFT JOIN car_transmissions ct ON ct.id = c.transmission_id;

SELECT cb.id, cb.name
FROM car_bodies cb
LEFT JOIN cars c ON cb.id = c.body_id
WHERE c.id IS NULL;

SELECT ce.id, ce.name
FROM car_engines ce
LEFT JOIN cars c ON ce.id = c.engine_id
WHERE c.id IS NULL;

SELECT ct.id, ct.name
FROM car_transmissions ct
LEFT JOIN cars c ON ct.id = c.transmission_id
WHERE c.id IS NULL;
