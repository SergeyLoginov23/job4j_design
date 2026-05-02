package ru.job4j.spammer;

import ru.job4j.jdbc.TableEditor;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDB {

    private Properties config;
    private String dump;

    public ImportDB(Properties config, String dump) {
        this.config = config;
        this.dump = dump;
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(dump))) {
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                String trimmed = line.trim();
                if (trimmed.isEmpty()) {
                    continue;
                }
                String[] words = trimmed.split(";", 2);
                if (words.length == 2) {
                    String name = words[0].trim();
                    String email = words[1].trim();
                    if (name.isEmpty() || email.isEmpty()) {
                        throw new IllegalArgumentException("Строка не содержит имени, либо адреса электронной почты");
                    }
                    User user = new User(name, email);
                    users.add(user);
                } else {
                    throw new IllegalArgumentException("В строке недостаточно данных");
                }
            }
        }
        return users;
    }

    public void createUsersTable(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sql = """
            CREATE TABLE IF NOT EXISTS users (
                id SERIAL PRIMARY KEY,
                name TEXT,
                email TEXT
            );
            """;
            statement.execute(sql);
        }
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(config.getProperty("jdbc.driver"));
        try (Connection connection = DriverManager.getConnection(
                config.getProperty("jdbc.url"),
                config.getProperty("jdbc.username"),
                config.getProperty("jdbc.password")
        )) {
            createUsersTable(connection);
            String sql = "INSERT INTO users(name, email) VALUES (?, ?)";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                for (User user : users) {
                    ps.setString(1, user.name);
                    ps.setString(2, user.email);
                    ps.execute();
                }
            }
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream input = ImportDB.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(input);
        }
        ImportDB dataBase = new ImportDB(config, "./data/dump.txt");
        dataBase.save(dataBase.load());
    }
}