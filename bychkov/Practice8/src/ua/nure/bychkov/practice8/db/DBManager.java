package ua.nure.bychkov.practice8.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private static DBManager instance;
    private static final String URL = "jdbc:mysql://localhost:3306/bychkov_epam?user=appachey&password=6754";
    private static final String FIND_ALL_USERS = "SELECT * FROM users";
    private static final String FIND_ALL_TEAMS = "SELECT * FROM teams";
    private static final String INSERT_USER = "INSERT INTO users VALUES(DEFAULT, ?)";
    private static final String INSERT_TEAM = "INSERT INTO teams VALUES(DEFAULT, ?)";


    private DBManager () {}

    public static synchronized DBManager getInstance() {
        if (instance == null) {
            return new DBManager();
        }
        return instance;
    }

    public boolean insertUser (User user) throws MyException{
        boolean result = false;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = DriverManager.getConnection(URL);
            pstmt = con.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS);
            int i = 1;
            pstmt.setString(i++, user.getLogin());
            if (pstmt.executeUpdate() > 0) {
                rs = pstmt.getGeneratedKeys();
                while (rs.next()) {
                    int userID = rs.getInt(1);
                    user.setId(userID);
                    result = true;
                }
            }
        } catch (SQLException ex) {
            throw new MyException("User creation failed!", ex);
        } finally {
            close(con);
        }
        return result;
    }

    public boolean insertTeam (Team team) throws MyException{
        boolean result = false;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = DriverManager.getConnection(URL);
            pstmt = con.prepareStatement(INSERT_TEAM, Statement.RETURN_GENERATED_KEYS);
            int i = 1;
            pstmt.setString(i++, team.getName());
            if (pstmt.executeUpdate() > 0) {
                rs = pstmt.getGeneratedKeys();
                while (rs.next()) {
                    int teamID = rs.getInt(1);
                    team.setId(teamID);
                    result = true;
                }
            }
        } catch (SQLException ex) {
            throw new MyException("Team creation failed!", ex);
        } finally {
            close(con);
        }
        return result;
    }

    public List<User> findAllUsers() throws MyException{
        List<User> users = new ArrayList<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try{
            con = DriverManager.getConnection(URL);
            stmt = con.createStatement();
            rs = stmt.executeQuery(FIND_ALL_USERS);
            while (rs.next()) {
                users.add(User.createUser(rs.getString("login")));
            }
        } catch (SQLException ex) {
            throw new MyException("No users found", ex);
        } finally {
            close(con);
        }

        return users;
    }

    public List<Team> findAllTeams() throws MyException{
        List<Team> teams = new ArrayList<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try{
            con = DriverManager.getConnection(URL);
            stmt = con.createStatement();
            rs = stmt.executeQuery(FIND_ALL_TEAMS);
            while (rs.next()) {
                teams.add(Team.createTeam(rs.getString("Name")));
            }
        } catch (SQLException ex) {
            throw new MyException("No teams found", ex);
        } finally {
            close(con);
        }

        return teams;
    }

    private static void close (Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
