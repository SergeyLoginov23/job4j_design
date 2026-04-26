CREATE TABLE roles(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE rules(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE roles_rules(
    id SERIAL PRIMARY KEY,
    role_id INT REFERENCES roles(id),
    rule_id INT REFERENCES rules(id),
    UNIQUE(role_id, rule_id)
);

CREATE TABLE users(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    role_id INT REFERENCES roles(id)
);

CREATE TABLE states(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE categories(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE items(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    user_id INT REFERENCES users(id),
    category_id INT REFERENCES categories(id),
    state_id INT REFERENCES states(id)
);

CREATE TABLE comments(
    id SERIAL PRIMARY KEY,
    text TEXT,
    item_id INT REFERENCES items(id)
);

CREATE TABLE attachs(
    id SERIAL PRIMARY KEY,
    file_name VARCHAR(255),
    item_id INT REFERENCES items(id)
);