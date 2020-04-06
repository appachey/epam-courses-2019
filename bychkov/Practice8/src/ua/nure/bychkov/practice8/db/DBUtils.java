package ua.nure.bychkov.practice8.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;
import java.util.Properties;

public class DBUtils {

    private static final String PROPERTIES = "app.properties";

    private DBUtils() {}

    public static void close(Connection con) {
        if (con != null) {
            try {
                con.close();
            }catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            }catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            }catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void rollback(Connection con) {
        if (con != null) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private static String connectURL(String propFileName) {
        String url = null;
        try {
            FileInputStream fis = new FileInputStream(propFileName);
            Properties prop = new Properties();
            prop.load(fis);
            url = prop.getProperty("connection.url");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return url;
    }

    public static Connection getConnection() throws SQLException {
        Connection con = DriverManager.getConnection(DBUtils.connectURL(PROPERTIES));
        con.setAutoCommit(false);
        con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        return con;
    }
}
