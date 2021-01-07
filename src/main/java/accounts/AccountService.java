package accounts;



import dataSets.UsersDataSet;
import servlets.db.DBException;
import servlets.db.DBService;
import servlets.db.DBServicelmpl;

import java.util.HashMap;
import java.util.Map;

public class AccountService {
    //private final Map<String, UserProfile> loginToProfile;
    private final Map<String, UserProfile> sessionIdToProfile;
    private final DBService dbService;
//...
    public AccountService(){
      //  loginToProfile = new HashMap<>();
        sessionIdToProfile = new HashMap<>();
        dbService = new DBServicelmpl();
        dbService.printConnectInfo();
    }

    public void addNewUser(UserProfile userProfile)  {
       // loginToProfile.put(userProfile.getLogin(), userProfile);
        try {
            dbService.addUser(userProfile.getLogin(), userProfile.getPass(), userProfile.getEmail());
        }catch (DBException e){
            e.printStackTrace();
        }


    }
     public UserProfile getUserByLogin(String login){
        //return loginToProfile.get(login);
        try {
            UsersDataSet usersDataSet = dbService.getUser(login);
            return new UserProfile(usersDataSet.getLogin(), usersDataSet.getPassword(), usersDataSet.getEmail());
        }catch (DBException e){
            e.printStackTrace();
        }
            return null;
     }
     public UserProfile getUserBySessionId(String sessionId){
        return  sessionIdToProfile.get(sessionId);
     }

     public void  deleteUser(String login){
        //loginToProfile.remove(login);

     }
     public void addSession(String sessionId, UserProfile userProfile){
        sessionIdToProfile.put(sessionId, userProfile);
     }

     public void deleteSession(String sessionId){
        sessionIdToProfile.remove(sessionId);
     }


}
