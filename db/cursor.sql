BEGIN;

DECLARE product_cursor SCROLL CURSOR FOR
SELECT * FROM products ORDER BY id;

FETCH LAST FROM product_cursor;

FETCH ABSOLUTE 15 FROM product_cursor;

FETCH ABSOLUTE 7 FROM product_cursor;

FETCH ABSOLUTE 2 FROM product_cursor;

FETCH PRIOR FROM product_cursor; 

CLOSE product_cursor;

COMMIT;