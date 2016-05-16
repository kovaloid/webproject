package com.epam.project.database.connection_pool;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.*;

public class ConnectionPoolTest {
    private ConnectionPool pool;
    private Connection connection;
    private Statement statement;

    @Before
    public void setUp() throws Exception {
        pool = ConnectionPool.getInstance();
        connection = pool.takeConnection();
        statement = connection.createStatement();
    }

    @After
    public void tearDown() throws Exception {
        pool.closeConnection(connection, statement);
    }

    @Test
    public void selectQuery() throws Exception {
        ResultSet rs = statement.executeQuery("SELECT * FROM AUTOBASE.USERS");
        assertEquals(rs.getMetaData().getColumnCount(), 4);
    }

    @Test
    public void insertQuery() throws Exception {
        createTestTableForInsert();
        int rows = statement.executeUpdate("INSERT INTO AUTOBASE.INSERT_TEST(ID, TEMP) VALUES('1', 'test')");
        assertEquals(rows, 1);
        dropTestTableForInsert();
    }

    @Test
    public void updateQuery() throws Exception {
        createTestTableForUpdate();
        fillTestTableForUpdate();
        int rows = statement.executeUpdate("UPDATE AUTOBASE.UPDATE_TEST SET TEMP='test2' WHERE ID='1'");
        assertEquals(rows, 1);
        dropTestTableForUpdate();
    }

    @Test
    public void deleteQuery() throws Exception {
        createTestTableForDelete();
        fillTestTableForDelete();
        int rows = statement.executeUpdate("DELETE FROM AUTOBASE.DELETE_TEST WHERE ID='1'");
        assertEquals(rows, 1);
        dropTestTableForDelete();
    }

    private void createTestTableForInsert() throws SQLException {
        Connection connection = pool.takeConnection();
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE \"AUTOBASE\".\"INSERT_TEST\" (\"ID\" NUMBER, \"TEMP\" VARCHAR(20))");
        pool.closeConnection(connection, statement);
    }

    private void createTestTableForUpdate() throws SQLException {
        Connection connection = pool.takeConnection();
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE \"AUTOBASE\".\"UPDATE_TEST\" (\"ID\" NUMBER, \"TEMP\" VARCHAR(20))");
        pool.closeConnection(connection, statement);
    }

    private void createTestTableForDelete() throws SQLException {
        Connection connection = pool.takeConnection();
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE \"AUTOBASE\".\"DELETE_TEST\" (\"ID\" NUMBER, \"TEMP\" VARCHAR(20))");
        pool.closeConnection(connection, statement);
    }

    private void dropTestTableForInsert() throws SQLException {
        Connection connection = pool.takeConnection();
        Statement statement = connection.createStatement();
        statement.execute("DROP TABLE \"AUTOBASE\".\"INSERT_TEST\"");
        pool.closeConnection(connection, statement);
    }

    private void dropTestTableForUpdate() throws SQLException {
        Connection connection = pool.takeConnection();
        Statement statement = connection.createStatement();
        statement.execute("DROP TABLE \"AUTOBASE\".\"UPDATE_TEST\"");
        pool.closeConnection(connection, statement);
    }

    private void dropTestTableForDelete() throws SQLException {
        Connection connection = pool.takeConnection();
        Statement statement = connection.createStatement();
        statement.execute("DROP TABLE \"AUTOBASE\".\"DELETE_TEST\"");
        pool.closeConnection(connection, statement);
    }

    private void fillTestTableForUpdate() throws SQLException {
        Connection connection = pool.takeConnection();
        Statement statement = connection.createStatement();
        statement.execute("INSERT INTO AUTOBASE.UPDATE_TEST(ID, TEMP) VALUES('1', 'test')");
        pool.closeConnection(connection, statement);
    }

    private void fillTestTableForDelete() throws SQLException {
        Connection connection = pool.takeConnection();
        Statement statement = connection.createStatement();
        statement.execute("INSERT INTO AUTOBASE.DELETE_TEST(ID, TEMP) VALUES('1', 'test')");
        pool.closeConnection(connection, statement);
    }
}