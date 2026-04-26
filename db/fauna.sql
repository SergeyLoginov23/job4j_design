CREATE TABLE fauna
(
    id             SERIAL PRIMARY KEY,
    name           TEXT,
    avg_age        INT,
    discovery_date DATE
);

INSERT INTO fauna(name, avg_age, discovery_date) VALUES
('goldfish', 15, '1800-01-01'),         
('catfish', 12000, '1900-05-10'),       
('blue whale', 20000, '1920-03-15'),     
('elephant', 50000, '1955-06-20'),       
('dodo bird', 300, NULL),                
('jellyfish', 18000, '1930-09-09');      

SELECT * FROM fauna
WHERE name LIKE '%fish%';

SELECT * FROM fauna
WHERE avg_age BETWEEN 10000 AND 21000;

SELECT * FROM fauna
WHERE discovery_date IS NULL;

SELECT * FROM fauna
WHERE discovery_date < '1950-01-01';