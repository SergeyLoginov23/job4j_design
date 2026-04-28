CREATE TABLE animals (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50),
    species VARCHAR(50),
    age INTEGER,
    weight INTEGER
);

INSERT INTO animals(name, species, age, weight) VALUES
('Leo', 'Lion', 5, 190),
('Molly', 'Cat', 3, 4),
('Buddy', 'Dog', 7, 25),
('Charlie', 'Parrot', 2, 1),
('Max', 'Elephant', 10, 5000);

begin transaction isolation level serializable;

begin transaction isolation level serializable;

SELECT SUM(weight) FROM animals;
UPDATE animals SET weight = 10 WHERE name = 'Charlie';

SELECT SUM(weight) FROM animals;
UPDATE animals SET weight = 10 WHERE name = 'Charlie';
