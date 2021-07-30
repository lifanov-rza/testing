package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnectionTests {

    @Test
    public void testDbConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?user=root&password=");

        } catch (SQLException ex) {
            System.out.println("SQL exception: " + ex.getMessage());
            System.out.println("SQL state: " + ex.getSQLState());
            System.out.println("Vendor Error" + ex.getErrorCode());
        }

    }

}
