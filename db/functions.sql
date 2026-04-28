CREATE TABLE products
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(50),
    producer VARCHAR(50),
    count    INTEGER DEFAULT 0,
    price    INTEGER
);


INSERT INTO products (name, producer, count, price)
VALUES ('product_1', 'producer_1', 0, 50);

CREATE
OR REPLACE PROCEDURE delete_empty_products()
LANGUAGE 'plpgsql'
AS 
$$
    BEGIN
       	DELETE FROM products p
		   where p.count = 0;
    END
$$;

CALL delete_empty_products();

INSERT INTO products (name, producer, count, price)
VALUES ('product_1', 'producer_1', 4, 50);

CREATE
OR REPLACE FUNCTION delete_product_by_id(p_id integer)
RETURNS void
LANGUAGE 'plpgsql'
AS
$$
	BEGIN
       	DELETE FROM products p
		   where p.id = p_id;
    END
$$;

SELECT delete_product_by_id(2);
