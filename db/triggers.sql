CREATE TABLE products
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(50),
    producer VARCHAR(50),
    count    INTEGER DEFAULT 0,
    price    INTEGER
);

CREATE OR REPLACE FUNCTION tax()
    RETURNS trigger AS
$$
    BEGIN
        UPDATE products
        SET price = price + price * 0.2
        WHERE id IN (SELECT id FROM inserted);  
		RETURN NEW;
    END;
$$
LANGUAGE 'plpgsql';

CREATE OR REPLACE TRIGGER tax_trigger
    AFTER INSERT
    ON products
    REFERENCING NEW TABLE AS
                    inserted
    FOR EACH STATEMENT
    EXECUTE PROCEDURE tax();


CREATE OR REPLACE FUNCTION tax_before_ins()
RETURNS trigger AS
$$
BEGIN
    NEW.price := NEW.price + NEW.price * 0.2;
    RETURN NEW;
END;
$$
LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER tax_trigger_before_ins
    BEFORE INSERT
    ON products
    FOR EACH ROW
    EXECUTE PROCEDURE tax_before_ins();

CREATE TABLE history_of_price
(
    id    SERIAL PRIMARY KEY,
    name  VARCHAR(50),
    price INTEGER,
    date  TIMESTAMP
);

CREATE OR REPLACE FUNCTION save_history()
RETURNS trigger AS
$$
BEGIN
    INSERT INTO history_of_price (name, price,date)
	VALUES (NEW.name, NEW.price, current_timestamp);
    RETURN NEW;
END;
$$
LANGUAGE plpgsql;


CREATE OR REPLACE TRIGGER history_trigger
    AFTER INSERT
    ON products
    FOR EACH ROW
    EXECUTE PROCEDURE save_histiory();




