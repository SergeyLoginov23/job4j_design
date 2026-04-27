CREATE TABLE students
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(50)
);

INSERT INTO students (name)
VALUES ('Иван Иванов');
INSERT INTO students (name)
VALUES ('Петр Петров');


CREATE TABLE authors
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(50)
);

INSERT INTO authors (name)
VALUES ('Александр Пушкин');
INSERT INTO authors (name)
VALUES ('Николай Гоголь');

CREATE TABLE books
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(200),
    author_id INTEGER REFERENCES authors (id)
);

INSERT INTO books (name, author_id)
VALUES ('Евгений Онегин', 1);
INSERT INTO books (name, author_id)
VALUES ('Капитанская дочка', 1);
INSERT INTO books (name, author_id)
VALUES ('Дубровский', 1);
INSERT INTO books (name, author_id)
VALUES ('Мертвые души', 2);
INSERT INTO books (name, author_id)
VALUES ('Вий', 2);

CREATE TABLE orders
(
    id SERIAL PRIMARY KEY,
    active BOOLEAN DEFAULT true,
    book_id INTEGER REFERENCES books (id),
    student_id INTEGER REFERENCES students (id)
);

INSERT INTO orders (book_id, student_id)
VALUES (1, 1);
INSERT INTO orders (book_id, student_id)
VALUES (3, 1);
INSERT INTO orders (book_id, student_id)
VALUES (5, 2);
INSERT INTO orders (book_id, student_id)
VALUES (4, 1);
INSERT INTO orders (book_id, student_id)
VALUES (2, 2);


CREATE OR REPLACE VIEW student_book_info AS
SELECT 
    s.name AS student_name,
    b.name AS book_name,
    a.name AS author_name,
    
    (
        SELECT COUNT(*)
        FROM orders o2
        WHERE o2.student_id = s.id
    ) AS total_books_by_student,
    
    (
        SELECT COUNT(*)
        FROM orders o3
        WHERE o3.book_id = b.id
    ) AS book_popularity,
    
    o.active

FROM orders o
JOIN students s ON o.student_id = s.id
JOIN books b ON o.book_id = b.id
JOIN authors a ON b.author_id = a.id;

SELECT *
FROM student_book_info
ORDER BY student_name;
