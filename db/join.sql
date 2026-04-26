CREATE TABLE authors(
    id SERIAL PRIMARY KEY,
    name TEXT
);

CREATE TABLE books(
    id SERIAL PRIMARY KEY,
    title TEXT,
    author_id INT REFERENCES authors(id)
);

INSERT INTO authors(name) VALUES
('Stephen King'),
('J.K. Rowling');

INSERT INTO books(title, author_id) VALUES
('It', 1),
('The Shining', 1),
('Harry Potter', 2);

SELECT b.title, a.name
FROM books b
INNER JOIN authors a ON b.author_id = a.id;



SELECT a.name, COUNT(b.id) AS books_count
FROM authors a
INNER JOIN books b ON b.author_id = a.id
GROUP BY a.name;