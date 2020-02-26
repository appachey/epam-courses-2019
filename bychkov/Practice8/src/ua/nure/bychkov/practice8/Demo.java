package ua.nure.bychkov.practice8;

import ua.nure.bychkov.practice8.db.DBManager;
import ua.nure.bychkov.practice8.db.MyException;
import ua.nure.bychkov.practice8.db.Team;
import ua.nure.bychkov.practice8.db.User;

import java.util.List;

public class Demo {
    private static void printList(List<?> list) {
        System.out.println(list);
    }

    public static void main(String[] args) throws MyException {
        // users  ==> [ivanov]
        // teams  ==> [teamA]

        DBManager dbManager = DBManager.getInstance();

        // Part 1
        dbManager.insertUser(User.createUser("petrov"));
        dbManager.insertUser(User.createUser("obama"));
        printList(dbManager.findAllUsers());
        // users  ==> [ivanov, petrov, obama]
        System.out.println("===========================");

        // Part 2
        dbManager.insertTeam(Team.createTeam("teamB"));
        dbManager.insertTeam(Team.createTeam("teamC"));

        printList(dbManager.findAllTeams());
        // teams ==> [teamA, teamB, teamC]

    }
}
