BEGIN TRANSACTION;
SELECT * FROM products;
SAVEPOINT first_savepoint;

DELETE FROM products WHERE price = 144;
UPDATE products SET price = 75 WHERE name = 'product_2';
SELECT * FROM products;

SAVEPOINT second_savepoint;

INSERT INTO products (name, producer, count, price)
VALUES ('product_3', 'producer_3', 8, 115);
SELECT * FROM products;

ROLLBACK TO SAVEPOINT second_savepoint;
SELECT * FROM products;
ROLLBACK TO SAVEPOINT first_savepoint;
SELECT * FROM products;
COMMIT;