package ru.job4j.jdbc;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor() throws Exception {
        Properties properties = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            properties.load(in);
        }
        this.properties = properties;
        initConnection();
    }

    private void executeAndPrint(String sql, String tableName) throws Exception {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
            System.out.println(getTableScheme(tableName));
        }
    }

    private void execute(String sql) throws Exception {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    private void initConnection() throws Exception {
        String url = properties.getProperty("url");
        String login = properties.getProperty("username");
        String password = properties.getProperty("password");
        connection = DriverManager.getConnection(url, login, password);
    }

    public void createTable(String tableName) throws Exception {
            String sql = String.format(
                    "CREATE TABLE IF NOT EXISTS %s (%s)", tableName,
                    "id SERIAL PRIMARY KEY"
            );
            executeAndPrint(sql, tableName);
    }

    public void dropTable(String tableName) throws Exception {
            String sql = String.format(
                    "Drop TABLE IF EXISTS %s;", tableName

            );
            execute(sql);
            System.out.println("Таблица " + tableName + " успешно удалена");
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        String sql = String.format(
                "ALTER TABLE IF EXISTS %s ADD COLUMN %s %s;", tableName, columnName, type
        );
        executeAndPrint(sql, tableName);
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        String sql = String.format(
                "ALTER TABLE IF EXISTS %s DROP COLUMN %s;", tableName, columnName
        );
        executeAndPrint(sql, tableName);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
        String sql = String.format(
                "ALTER TABLE IF EXISTS %s RENAME COLUMN %s TO %s ;", tableName, columnName, newColumnName
        );
        executeAndPrint(sql, tableName);
    }

    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        TableEditor tableEditor = new TableEditor();
        tableEditor.createTable("test");
        tableEditor.addColumn("test", "name", "varchar");
        tableEditor.addColumn("test", "lastName", "varchar");
        tableEditor.dropColumn("test", "lastName");
        tableEditor.renameColumn("test", "name", "fullname");
        tableEditor.dropTable("test");
        tableEditor.close();

    }
}