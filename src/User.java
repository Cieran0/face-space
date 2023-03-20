

import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String fullName;
    private String workPlace;
    private String homeTown;
    private Long passwordHash;
    private List<Integer> friendIDs;

    public User() {

    }

    public User(String fullName, String username, Long passwordHash, String workPlace, String homeTown) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.fullName = fullName;
        this.homeTown = homeTown;
        this.workPlace = workPlace;
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

    public Long getPasswordHash(){
        return passwordHash;
    }

    public void setPasswordHash(Long passwordHash){
        this.passwordHash = passwordHash;
    }

    public List<Integer> getFriends() {
        return friendIDs;
    }

    public void addFriend(Integer id) {
        Integer myId = Main.getUserID(this);
        if(myId == id) return;
        if(this.friendIDs.contains(id)) return;
        this.friendIDs.add(id);
        Main.users.get(id).addFriend(myId);
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof User)) return false;
        User otherUser = (User)obj;
        return otherUser.username == this.username;
    }
    
}
