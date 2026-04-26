INSERT INTO roles(name) VALUES
('admin'),
('user');

INSERT INTO rules(name) VALUES
('read'),
('write');

INSERT INTO roles_rules(role_id, rule_id) VALUES
(1, 1),
(1, 2),
(2, 1);

INSERT INTO users(name, role_id) VALUES
('Alex', 1),
('Maria', 2);

INSERT INTO states(name) VALUES
('new'),
('done');

INSERT INTO categories(name) VALUES
('bug'),
('task');

INSERT INTO items(name, user_id, category_id, state_id) VALUES
('Fix login bug', 1, 1, 1),
('Write docs', 2, 2, 2);

INSERT INTO comments(text, item_id) VALUES
('Need to reproduce', 1),
('Done and tested', 2);

INSERT INTO attachs(file_name, item_id) VALUES
('error.png', 1),
('result.pdf', 2);