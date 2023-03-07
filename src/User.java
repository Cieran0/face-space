

import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String workPlace;
    private String homeTown;
    private Integer password;
    private List<Integer> friendIDs;

    public User() {

    }

    public User(String name) {
        this.username = name;
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

    public List<Integer> getFriends() {
        return friendIDs;
    }

    
}
