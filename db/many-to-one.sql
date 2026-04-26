CREATE TABLE country(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE people(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    country_id INT REFERENCES country(id)
);