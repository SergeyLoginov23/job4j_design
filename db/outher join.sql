CREATE TABLE departments(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE employees(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    department_id INT REFERENCES departments(id)
);

INSERT INTO departments(name) VALUES
('HR'),
('IT'),
('Finance'),
('Marketing');

INSERT INTO employees(name, department_id) VALUES
('Alex', 1),
('Maria', 2),
('John', 2),
('Kate', 3);

SELECT d.name AS department, e.name AS employee
FROM departments d
LEFT JOIN employees e ON d.id = e.department_id;

SELECT d.name AS department, e.name AS employee
FROM departments d
RIGHT JOIN employees e ON d.id = e.department_id;

SELECT d.name AS department, e.name AS employee
FROM departments d
FULL JOIN employees e ON d.id = e.department_id;

SELECT d.name AS department, e.name AS employee
FROM departments d
CROSS JOIN employees e;

SELECT d.name
FROM departments d
LEFT JOIN employees e ON d.id = e.department_id
WHERE e.id IS NULL;

SELECT d.name AS department, e.name AS employee
FROM departments d
LEFT JOIN employees e ON d.id = e.department_id;

SELECT d.name AS department, e.name AS employee
FROM employees e
RIGHT JOIN departments d ON d.id = e.department_id;

CREATE TABLE teens(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    gender VARCHAR(10)
);

INSERT INTO teens(name, gender) VALUES
('Вася', 'M'),
('Петя', 'M'),
('Маша', 'F'),
('Катя', 'F');

SELECT t1.name AS boy, t2.name AS girl
FROM teens t1
CROSS JOIN teens t2
WHERE t1.gender = 'M'
  AND t2.gender = 'F';
