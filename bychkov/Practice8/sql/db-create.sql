DROP TABLE IF EXISTS users_teams;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS teams;

CREATE TABLE users (
        id INT PRIMARY KEY AUTO_INCREMENT,
        login VARCHAR(15) NOT NULL UNIQUE
);

CREATE TABLE teams (
        id INT PRIMARY KEY AUTO_INCREMENT,
        name VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE users_teams (
        user_id INT NOT NULL,
        team_id INT NOT NULL,
        PRIMARY KEY (user_id, team_id),
        FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
        FOREIGN KEY (team_id) REFERENCES teams (id) ON DELETE CASCADE
);

INSERT INTO users (login) VALUES ('ivanov');
INSERT INTO teams (name) VALUES ('teamA');