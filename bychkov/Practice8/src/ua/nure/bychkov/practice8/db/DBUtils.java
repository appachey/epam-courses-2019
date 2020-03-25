package ua.nure.bychkov.practice8.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtils {

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
}
