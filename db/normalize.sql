CREATE TABLE clients (
    id SERIAL PRIMARY KEY,
    full_name TEXT NOT NULL,
    address TEXT NOT NULL,
    gender TEXT NOT NULL
);

CREATE TABLE movies (
    id SERIAL PRIMARY KEY,
    title TEXT NOT NULL
);

CREATE TABLE orders (
    id SERIAL PRIMARY KEY,
    client_id INT NOT NULL REFERENCES clients(id),
    movie_id INT NOT NULL REFERENCES movies(id)
);


INSERT INTO clients (full_name, address, gender) VALUES
('Ольга Егорова', '1-й Казанский переулок, д. 14', 'женский'),
('Иванов Сергей', 'ул. Центральная, д. 40, кв. 74', 'мужской'),
('Иванов Сергей', 'ул. Ленина, д. 7, кв. 130', 'мужской');

INSERT INTO movies (title) VALUES
('Пираты Карибского моря'),
('Матрица: Революция'),
('Человек, который изменил всё'),
('Интерстеллар');

INSERT INTO orders (client_id, movie_id) VALUES
(1, 1),
(1, 2);
INSERT INTO orders (client_id, movie_id) VALUES
(2, 3),
(2, 4);
INSERT INTO orders (client_id, movie_id) VALUES
(3, 2);