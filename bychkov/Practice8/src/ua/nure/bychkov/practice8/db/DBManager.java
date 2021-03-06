package ua.nure.bychkov.practice8.db;

import ua.nure.bychkov.practice8.db.entity.Team;
import ua.nure.bychkov.practice8.db.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DBManager {
    private static DBManager instance;

    private static final String FIND_ALL_USERS = "SELECT * FROM users";
    private static final String FIND_ALL_TEAMS = "SELECT * FROM teams";
    private static final String FIND_ALL_USER_TEAMS = "SELECT * FROM users_teams WHERE user_id = ?";
    private static final String INSERT_USER = "INSERT INTO users VALUES(DEFAULT, ?)";
    private static final String INSERT_TEAM = "INSERT INTO teams VALUES(DEFAULT, ?)";
    private static final String FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE login = ?";
    private static final String FIND_TEAM_BY_NAME = "SELECT * FROM teams WHERE name = ?";
    private static final String SET_TEAM_FOR_USER = "INSERT INTO users_teams VALUES(?, ?)";
    private static final String FIND_TEAM_BY_ID = "SELECT * FROM teams WHERE id = ?";
    private static final String DELETE_TEAM = "DELETE FROM teams WHERE name = ?";
    private static final String UPDATE_TEAM = "UPDATE teams SET name = ? WHERE id = ?";


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
            con = DBUtils.getConnection();
            pstmt = con.prepareStatement(FIND_USER_BY_LOGIN);
            pstmt.setString(1, login);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                user = extracrUser(rs);
            }
            con.commit();
        }catch (SQLException ex) {
            DBUtils.rollback(con);
            throw new MyException("Cant`t find user with login: " + login, ex);
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
            con = DBUtils.getConnection();
            pstmt = con.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS);
            int k = 1;
            pstmt.setString(k++, user.getLogin());
            if (pstmt.executeUpdate() > 0) {
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    int userID = rs.getInt(1);
                    user.setId(userID);
                    result = true;
                }
            }
            con.commit();
        } catch (SQLException ex) {
            DBUtils.rollback(con);
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
            con = DBUtils.getConnection();
            pstmt = con.prepareStatement(FIND_TEAM_BY_NAME);
            pstmt.setString(1, teamName);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                team = extracrTeam(rs);
            }
            con.commit();
        }catch (SQLException ex) {
            DBUtils.rollback(con);
            throw new MyException("Can`t team with name: " + teamName, ex);
        }finally {
            DBUtils.close(rs);
            DBUtils.close(pstmt);
            DBUtils.close(con);
        }
        return team;
    }

    private static Team getTeam(Connection con, int teamID) throws MyException {
        Team team = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = con.prepareStatement(FIND_TEAM_BY_ID);
            pstmt.setInt(1, teamID);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                team = extracrTeam(rs);
            }
            con.commit();
        }catch (SQLException ex) {
            DBUtils.rollback(con);
            throw new MyException("Can`t find team with id: " + teamID, ex);
        }finally {
            DBUtils.close(rs);
            DBUtils.close(pstmt);
        }
        return team;
    }

    public boolean insertTeam (Team team) throws MyException{
        boolean result = false;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            pstmt = con.prepareStatement(INSERT_TEAM, Statement.RETURN_GENERATED_KEYS);
            int k = 1;
            pstmt.setString(k++, team.getName());
            if (pstmt.executeUpdate() > 0) {
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    int teamID = rs.getInt(1);
                    team.setId(teamID);
                    result = true;
                }
            }
            con.commit();
        } catch (SQLException ex) {
            DBUtils.rollback(con);
            throw new MyException("Adding team failed!", ex);
        } finally {
            DBUtils.close(rs);
            DBUtils.close(pstmt);
            DBUtils.close(con);
        }
        return result;
    }

    public boolean deleteTeam(Team team) throws MyException {
        boolean res = false;
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = DBUtils.getConnection();
            pstmt = con.prepareStatement(DELETE_TEAM);
            pstmt.setString(1, team.getName());
            res =  pstmt.executeUpdate() > 0;
            con.commit();
        } catch(SQLException ex) {
            DBUtils.rollback(con);
            throw new MyException("Team with name " + team.getName() + "not found!", ex);
        } finally {
            DBUtils.close(pstmt);
            DBUtils.close(con);
        }
        return res;
    }

    public boolean updateTeam(Team team) throws MyException {
        boolean res = false;
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = DBUtils.getConnection();
            pstmt = con.prepareStatement(UPDATE_TEAM);
            int k = 1;
            pstmt.setString(k++, team.getName());
            pstmt.setInt(k++, team.getId());
            res =  pstmt.executeUpdate() > 0;
            con.commit();
        } catch (SQLException ex) {
            DBUtils.rollback(con);
            throw new MyException("Team update failed!", ex);
        } finally {
            DBUtils.close(pstmt);
            DBUtils.close(con);
        }
        return res;
    }

    public List<Team> getUserTeams(User user) throws MyException {
        List<Team> teams = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            pstmt = con.prepareStatement(FIND_ALL_USER_TEAMS);
            pstmt.setInt(1, user.getId());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                teams.add(getTeam(con, rs.getInt("team_id")));
            }
            con.commit();
        } catch(SQLException ex) {
            DBUtils.rollback(con);
            throw new MyException("No teams found for user " + user.getLogin(), ex);
        } finally {
            DBUtils.close(rs);
            DBUtils.close(pstmt);
            DBUtils.close(con);
        }
        return teams;
    }

    public List<User> findAllUsers() throws MyException{
        List<User> users = new ArrayList<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try{
            con = DBUtils.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(FIND_ALL_USERS);
            while (rs.next()) {
                users.add(getUser(rs.getString("login")));
            }
            con.commit();
        } catch (SQLException ex) {
            DBUtils.rollback(con);
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
            con = DBUtils.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(FIND_ALL_TEAMS);
            while (rs.next()) {
                teams.add(getTeam(rs.getString("Name")));
            }
            con.commit();
        } catch (SQLException ex) {
            DBUtils.rollback(con);
            throw new MyException("No teams found", ex);
        } finally {
            DBUtils.close(rs);
            DBUtils.close(stmt);
            DBUtils.close(con);
        }
        return teams;
    }

    public void setTeamsForUser(User user, Team... teams) throws MyException {
        Connection con = null;
        try {
            con = DBUtils.getConnection();
            for (Team team : teams) {
                setTeamForUser(con, user, team);
            }
            con.commit();
        } catch (SQLException ex) {
            DBUtils.rollback(con);
            throw new MyException("Can`t set teams " + Arrays.toString(teams) + " for user " +
                    user.getLogin() + "!", ex);
        }finally {
            DBUtils.close(con);
        }
    }

    private static void setTeamForUser(Connection con, User user, Team team) throws SQLException {
        PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement(SET_TEAM_FOR_USER);
            int k = 1;
            pstmt.setInt(k++, user.getId());
            pstmt.setInt(k++, team.getId());
            pstmt.executeUpdate();
        } finally {
            DBUtils.close(pstmt);
        }
    }

    private static User extracrUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setLogin(rs.getString("login"));
        return user;
    }

    private static Team extracrTeam(ResultSet rs) throws SQLException {
        Team team = new Team();
        team.setId(rs.getInt("id"));
        team.setName(rs.getString("name"));
        return team;
    }
}
