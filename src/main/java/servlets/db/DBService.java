package servlets.db;

import dataSets.UsersDataSet;

public interface DBService {
    public UsersDataSet getUser(String login) throws DBException ;

    public long getUserId(String login) throws DBException;

    public long addUser(String login, String password, String email) throws DBException;

    public void printConnectInfo();
}
