package servlets;

import accounts.UserProfile;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import dao.UserDAO;
import org.h2.jdbcx.JdbcDataSource;

public class DBService {
    private final Connection connection;

    public DBService(){
       // this.connection = getPSQLConnection();
        this.connection = getH2Connection();
        UserDAO dao = new UserDAO(this.connection);
        try {
            dao.createTable();
            if(getUser("admin") == null) {
                dao.insertUser("admin", "admin@admin.com", "admin");
            }
        }catch (SQLException | DBException e){
            e.printStackTrace();
        }

    }

    public void printConnectInfo() {
        try {
            System.out.println("DB name: " + connection.getMetaData().getDatabaseProductName());
            System.out.println("DB version: " + connection.getMetaData().getDatabaseProductVersion());
            System.out.println("Driver: " + connection.getMetaData().getDriverName());
            System.out.println("Autocommit: " + connection.getAutoCommit());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public long addUser(String login, String email, String password) throws DBException {
        try {
            connection.setAutoCommit(false);
            UserDAO dao = new UserDAO(connection);
            if(getUser(login)==null) {
                dao.insertUser(login, email, password);
                connection.commit();
                return dao.getUserId(login);
            }
            else {
                return 0;
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignore) {
            }
            throw new DBException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ignore) {
            }
        }
    }

    public void deleteUaser(String login) throws DBException{
        try{
            new UserDAO(connection).deleteUser(login);
        }catch (SQLException e){
            throw new DBException(e);
        }
    }

    public UserProfile getUser(String login) throws DBException {
        return (new UserDAO(connection).getUserProfile(login));
    }

    @SuppressWarnings("UnusedDeclaration")
    public static Connection getPSQLConnection(){
        try {
            Class.forName("org.postgresql.Driver");

            String DB_URL = "jdbc:postgresql://127.0.0.1:5432/javatest";
            String USER = "kirill";
            String PASS = "4865";

            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return null;

    }
    public static Connection getH2Connection() {
        try {
            String url = "jdbc:h2:./h2db";
            String name = "tully";
            String pass = "tully";

            JdbcDataSource ds = new JdbcDataSource();
            ds.setURL(url);
            ds.setUser(name);
            ds.setPassword(pass);

            Connection connection = DriverManager.getConnection(url, name, pass);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
