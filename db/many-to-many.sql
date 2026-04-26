CREATE TABLE users(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE roles(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE users_roles(
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(id),
    role_id INT REFERENCES roles(id)
);