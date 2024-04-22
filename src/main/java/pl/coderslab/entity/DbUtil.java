package pl.coderslab.entity;

import java.sql.*;

public class DbUtil {

        private static final String DB_URL = "jdbc:mysql://localhost:3306";
        private static final String DB_USER = "root";

        private static final String DB_PASS = "Wiosna2023!?";

        public static Connection connect() throws SQLException {
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        }
    }
