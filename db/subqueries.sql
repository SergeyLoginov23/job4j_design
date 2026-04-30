CREATE TABLE customers
(
    id         SERIAL PRIMARY KEY,
    first_name TEXT,
    last_name  TEXT,
    age        INT,
    country    TEXT
);

INSERT INTO customers (first_name, last_name, age, country) VALUES
('Ivan', 'Petrov', 25, 'Russia'),
('Anna', 'Smirnova', 31, 'Germany'),
('John', 'Smith', 40, 'USA'),
('Maria', 'Ivanova', 28, 'Russia'),
('Lucas', 'Müller', 35, 'Germany'),
('Emma', 'Brown', 22, 'UK');

SELECT * FROM customers 
WHERE age = (SELECT MIN(age) FROM customers);


CREATE TABLE orders
(
    id          SERIAL PRIMARY KEY,
    amount      INT,
    customer_id INT REFERENCES customers (id)
);

INSERT INTO orders (amount, customer_id) VALUES
(100, 1),
(250, 1),
(300, 2),
(150, 3),
(500, 5);

SELECT *
FROM customers c
WHERE c.id NOT IN (
    SELECT customer_id
    FROM orders
);