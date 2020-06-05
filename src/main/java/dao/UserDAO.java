package dao;

import accounts.UserProfile;
import executor.Executor;
import java.sql.Connection;
import java.sql.SQLException;

public class UserDAO {
    private Executor executor;

    public UserDAO(Connection connection) {
        this.executor = new Executor(connection);
    }

    public void createTable() throws SQLException {
        executor.execUpdate("CREATE TABLE IF NOT EXISTS users(login_id SERIAL PRIMARY KEY, login VARCHAR(256), email VARCHAR(256), password VARCHAR(256))");
    }

    public void insertUser(String login, String email, String password) throws SQLException {
        executor.execUpdate("INSERT INTO users(login, email, password) VALUES ('"+login+"', '"+email+"', '"+password +"') ");
    }
    public void deleteUser(String login) throws SQLException {
        executor.execUpdate("DELETE FROM users WHERE login='" + login+"'");
    }

    public UserProfile getUserProfile(String login){
        try {
        return executor.execQuery("SELECT * FROM users WHERE login='" + login+"'", result -> {
            result.next();
            return new UserProfile(result.getString("login"), result.getString("password"), result.getString("email"));
        });}
        catch (SQLException e){
            return null;
        }
    }

    public long getUserId(String login) throws SQLException {
        return executor.execQuery("SELECT * FROM USERS WHERE login='" + login + "'", result -> {
            result.next();
            return result.getLong(1);
        });
    }


}
