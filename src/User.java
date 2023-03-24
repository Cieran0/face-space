import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private Set<Long> friendIDs;

    public User() {

    }

    public User(String fullName, String username, Long passwordHash, String workPlace, String homeTown) {
        this.username = username;
        this.id = Hash.hash(username);
        this.passwordHash = passwordHash;
        this.fullName = fullName;
        this.homeTown = homeTown;
        this.workPlace = workPlace;
        this.friendIDs = new HashSet<Long>();
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

    public Set<Long> getFriends() {
        return friendIDs;
    }

    public void addFriend(long id) {
        if(this.id == id) return;
        if(this.friendIDs.contains(id)) return;
        this.friendIDs.add(id);
        User friend = Main.users.searchTree(id);
        if(friend != null)
            friend.addFriend(this.id);
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof User)) return false;
        User otherUser = (User)obj;
        return otherUser.username == this.username;
    }

    public Set<Long> recommendFriends(){
        Set<Long> recommendedFriends = new HashSet<Long>();
        List<User> listOfUsers = Main.users.asList();
        for (User user : listOfUsers) {
            if (user.equals(this) || this.isFriendsWith(user)) continue;
            if (this.hasSameWorkPlaceAs(user) || this.hasSameHomeTownAs(user) || this.hasMutalFriendWith(user)) {
                recommendedFriends.add(user.id);
            }
        }
        return recommendedFriends;
    }
    
    public boolean isCurrentUser() {
        return this.equals(Main.currentUser);
    }

    public boolean hasSameHomeTownAs(User otherUser) {
        String thisHomeTown = this.homeTown.toLowerCase();
        String otherHomeTown = otherUser.homeTown.toLowerCase();
        return thisHomeTown.equals(otherHomeTown) && !thisHomeTown.equals("hidden") && !otherHomeTown.equals("hidden");
    }

    public boolean hasSameWorkPlaceAs(User otherUser) {
        String thisWorkPlace = this.workPlace.toLowerCase();
        String otherWorkPlace = otherUser.workPlace.toLowerCase();
        return thisWorkPlace.equals(otherWorkPlace) && !thisWorkPlace.equals("hidden") && !otherWorkPlace.equals("hidden");
    }

    public boolean isFriendsWith(User otherUser) {
        return isFriendsWith(otherUser.id);
    }

    public boolean isFriendsWith(long id) {
        return this.friendIDs.contains(id);
    }

    public boolean hasMutalFriendWith(User otherUser) {
        return hasMutalFriendWith(otherUser.id);
    }

    public boolean hasMutalFriendWith(long id) {
        for (Long friendID : this.friendIDs){
            User userFriend = Main.users.searchTree(friendID);

            if (userFriend.isFriendsWith(id)){
                return true;
            }
        }
        return false;
    }
    
    public void removeFriend(long id) {
        this.friendIDs.remove(id);
        Main.users.searchTree(id).friendIDs.remove(this.id);
    }}
