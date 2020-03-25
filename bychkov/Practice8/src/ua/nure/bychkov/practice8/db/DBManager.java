package ua.nure.bychkov.practice8.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DBManager {
    private static DBManager instance;
    private static final String PROPERTIES = "app.properties";
    private static final String FIND_ALL_USERS = "SELECT * FROM users";
    private static final String FIND_ALL_TEAMS = "SELECT * FROM teams";
    private static final String INSERT_USER = "INSERT INTO users VALUES(DEFAULT, ?)";
    private static final String INSERT_TEAM = "INSERT INTO teams VALUES(DEFAULT, ?)";
    private static final String FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE login = ?";
    private static final String FIND_TEAM_BY_NAME = "SELECT * FROM teams WHERE name = ?";


    private DBManager () {}

    public static synchronized DBManager getInstance() {
        if (instance == null) {
            return new DBManager();
        }
        return instance;
    }

    public User getUser(String login) throws MyException {
        User user = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = DriverManager.getConnection(getDBCon(PROPERTIES));
            pstmt = con.prepareStatement(FIND_USER_BY_LOGIN);
            pstmt.setString(1, login);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                user = extracrUser(rs);
            }
        }catch (SQLException ex) {
            throw new MyException("User with login: " + login + " doesn`t exist", ex);
        } finally {
            DBUtils.close(rs);
            DBUtils.close(pstmt);
            DBUtils.close(con);
        }
        return user;
    }

    public boolean insertUser (User user) throws MyException{
        boolean result = false;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = DriverManager.getConnection(getDBCon(PROPERTIES));
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
            DBUtils.close(rs);
            DBUtils.close(pstmt);
            DBUtils.close(con);
        }
        return result;
    }

    public Team getTeam(String teamName) throws MyException {
        Team team = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = DriverManager.getConnection(getDBCon(PROPERTIES));
            pstmt = con.prepareStatement(FIND_TEAM_BY_NAME);
            pstmt.setString(1, teamName);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                team = extracrTeam(rs);
            }
        }catch (SQLException ex) {
            throw new MyException("User with login: " + teamName + " doesn`t exist", ex);
        }finally {
            DBUtils.close(rs);
            DBUtils.close(pstmt);
            DBUtils.close(con);
        }
        return team;
    }

    public boolean insertTeam (Team team) throws MyException{
        boolean result = false;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = DriverManager.getConnection(getDBCon(PROPERTIES));
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
            DBUtils.close(rs);
            DBUtils.close(pstmt);
            DBUtils.close(con);
        }
        return result;
    }

    public List<User> findAllUsers() throws MyException{
        List<User> users = new ArrayList<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try{
            con = DriverManager.getConnection(getDBCon(PROPERTIES));
            stmt = con.createStatement();
            rs = stmt.executeQuery(FIND_ALL_USERS);
            while (rs.next()) {
                users.add(User.createUser(rs.getString("login")));
            }
        } catch (SQLException ex) {
            throw new MyException("No users found", ex);
        } finally {
            DBUtils.close(rs);
            DBUtils.close(stmt);
            DBUtils.close(con);
        }

        return users;
    }

    public List<Team> findAllTeams() throws MyException{
        List<Team> teams = new ArrayList<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try{
            con = DriverManager.getConnection(getDBCon(PROPERTIES));
            stmt = con.createStatement();
            rs = stmt.executeQuery(FIND_ALL_TEAMS);
            while (rs.next()) {
                teams.add(Team.createTeam(rs.getString("Name")));
            }
        } catch (SQLException ex) {
            throw new MyException("No teams found", ex);
        } finally {
            DBUtils.close(rs);
            DBUtils.close(stmt);
            DBUtils.close(con);
        }

        return teams;
    }

    private static String getDBCon(String propFileName) {
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

    private User extracrUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setLogin(rs.getString("login"));
        return user;
    }

    private Team extracrTeam(ResultSet rs) throws SQLException {
        Team team = new Team();
        team.setId(rs.getInt("id"));
        team.setName(rs.getString("name"));
        return team;
    }
}
