

import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String fullName;
    private String workPlace;
    private String homeTown;
    private String password;
    private List<Integer> friendIDs;

    public User() {

    }

    public User(String username, String fullName, String password) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.homeTown = "Hidden";
        this.workPlace = "Hidden";
        this.friendIDs = new ArrayList<Integer>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    public String getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String newPassword){
        this.password = newPassword;
    }

    public List<Integer> getFriends() {
        return friendIDs;
    }

    public void addFriend(Integer id) {
        this.friendIDs.add(id);
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof User)) return false;
        User otherUser = (User)obj;
        return otherUser.username == this.username;
    }
    
}
