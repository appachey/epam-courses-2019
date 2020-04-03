package ua.nure.bychkov.practice8;

import ua.nure.bychkov.practice8.db.DBManager;
import ua.nure.bychkov.practice8.db.MyException;
import ua.nure.bychkov.practice8.db.entity.Team;
import ua.nure.bychkov.practice8.db.entity.User;

import java.util.List;

public class Demo {

    private static void printList(List<?> list) {
        System.out.println(list);
    }

    public static void main(String[] args) throws MyException {
        // users  ==> [ivanov]
        // teams ==> [teamA]

        DBManager dbManager = DBManager.getInstance();

        // Part 1
        dbManager.insertUser(User.createUser("petrov"));
        dbManager.insertUser(User.createUser("obama"));
        printList(dbManager.findAllUsers());
        // users  ==> [ivanov, petrov, obama]

        System.out.println("===========================");

        // Part 2
        dbManager.insertTeam(Team.createTeam("teamX"));
        dbManager.insertTeam(Team.createTeam("teamY"));

        printList(dbManager.findAllTeams());
        // teams ==> [teamA, teamB, teamC]

        System.out.println("===========================");

        // Part 3
        User userPetrov = dbManager.getUser("petrov");
        User userIvanov = dbManager.getUser("ivanov");
        User userObama = dbManager.getUser("obama");

        Team teamA = dbManager.getTeam("teamZ");
        Team teamB = dbManager.getTeam("teamX");
        Team teamC = dbManager.getTeam("teamY");

        // method setTeamsForUser must implement transaction!
        dbManager.setTeamsForUser(userIvanov, teamA);
        dbManager.setTeamsForUser(userPetrov, teamA, teamB);
        dbManager.setTeamsForUser(userObama, teamA, teamB, teamC);

        for (User user : dbManager.findAllUsers()) {
            printList(dbManager.getUserTeams(user));
            System.out.println("~~~~~");
        }
        // teamA
        // teamA teamB
        // teamA teamB teamC

        System.out.println("===========================");

        // Part 4

        // on delete cascade!
        dbManager.deleteTeam(teamA);

        // Part 5
        teamC.setName("teamA");
        dbManager.updateTeam(teamC);
        printList(dbManager.findAllTeams());
        // teams ==> [teamB, teamX]

    }
}