package accounts;


import servlets.DBException;
import servlets.DBService;

import java.util.HashMap;
import java.util.Map;

public class AccountService {
   // private final Map<String, UserProfile> loginToProfile;
    private final Map<String, UserProfile> sessionIdToProfile;

    private final DBService dbService;

    public AccountService(){
       // loginToProfile = new HashMap<>();
        sessionIdToProfile = new HashMap<>();

        this.dbService = new DBService();
        dbService.printConnectInfo();

    }

    public void addNewUser(UserProfile userProfile)  {
        //loginToProfile.put(userProfile.getLogin(), userProfile);
        try {
            this.dbService.addUser(userProfile.getLogin(), userProfile.getEmail(), userProfile.getPass());
        }catch (DBException e){
            e.printStackTrace();
        }

    }
     public UserProfile getUserByLogin(String login){
        //return loginToProfile.get(login);
         try {
              return this.dbService.getUser(login);
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
         try {
             this.dbService.deleteUaser(login);
         }catch (DBException e){
             e.printStackTrace();
         }
     }
     public void addSession(String sessionId, UserProfile userProfile){
        sessionIdToProfile.put(sessionId, userProfile);
     }

     public void deleteSession(String sessionId){
        sessionIdToProfile.remove(sessionId);
     }


}
