package accounts;

public class UserProfile {
    private final String login;
    private final String password;
    private final String email;

    public UserProfile(String login, String password, String email){
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public UserProfile(String login){
        this.login = login;
        this.password = login;
        this.email = login+"@"+login+".com";
    }
    public String getLogin(){
        return this.login;
    }

    public String getPass(){
        return this.password;
    }

    public String getEmail(){
        return email;
    }



}
