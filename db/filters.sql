CREATE TABLE type(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE product(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    type_id INT REFERENCES type(id),
    expired_date DATE,
    price INT
);

INSERT INTO type(name) VALUES
('СЫР'),
('МОЛОКО'),
('МЯСО');

INSERT INTO product(name, type_id, expired_date, price) VALUES
('Сыр моцарелла', 1, '2024-01-01', 300),
('Сыр плавленный', 1, '2026-01-01', 450),
('Молоко 1л', 2, '2023-01-01', 120),
('Мороженое ванильное', 2, '2026-06-01', 200),
('Мороженое шоколадное', 2, '2026-06-01', 250),
('Стейк говяжий', 3, '2026-01-01', 900),
('Сыр дорблю', 1, '2026-01-01', 450);

SELECT p.*
FROM product p
JOIN type t ON p.type_id = t.id
WHERE t.name = 'СЫР';

SELECT *
FROM product
WHERE name ILIKE '%мороженое%';

SELECT *
FROM product
WHERE expired_date < CURRENT_DATE;

SELECT *
FROM product
WHERE price = (SELECT MAX(price) FROM product);

SELECT t.name, COUNT(p.id) AS count_products
FROM type t
LEFT JOIN product p ON p.type_id = t.id
GROUP BY t.name;

SELECT p.*
FROM product p
JOIN type t ON p.type_id = t.id
WHERE t.name IN ('СЫР', 'МОЛОКО');

SELECT t.name, COUNT(p.id) AS count_products
FROM type t
JOIN product p ON p.type_id = t.id
GROUP BY t.name
HAVING COUNT(p.id) < 10;

SELECT p.name AS product, t.name AS type
FROM product p
JOIN type t ON p.type_id = t.id;
