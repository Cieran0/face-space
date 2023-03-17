

import java.util.ArrayList;
import java.util.List;

public class User {

    private Long id;

    public Long getId() {
        return id;
    }

    private String username;
    private String fullName;
    private String workPlace;
    private String homeTown;
    private Long passwordHash;
    private List<Long> friendIDs;

    public User() {

    }

    public User(String fullName, String username, Long passwordHash) {
        this.username = username;
        this.id = Hash.hash(username);
        this.passwordHash = passwordHash;
        this.fullName = fullName;
        this.homeTown = "Hidden";
        this.workPlace = "Hidden";
        this.friendIDs = new ArrayList<Long>();
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

    public List<Long> getFriends() {
        return friendIDs;
    }

    public void addFriend(long id) {
        if(this.id == id) return;
        if(this.friendIDs.contains(id)) return;
        this.friendIDs.add(id);
        Main.users.searchTree(id).addFriend(this.id);
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof User)) return false;
        User otherUser = (User)obj;
        return otherUser.username == this.username;
    }
    
}
