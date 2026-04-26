CREATE TABLE users(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE profiles(
    id SERIAL PRIMARY KEY,
    bio VARCHAR(255),
    user_id INT REFERENCES users(id) UNIQUE
);