

import java.util.ArrayList;
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
        Main.users.searchTree(id).addFriend(this.id);
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
            if (user.workPlace.equals(this.workPlace)){
                recommendedFriends.add(user.id);
            }
            else if (user.homeTown.equals(this.homeTown)) {
                recommendedFriends.add(user.id);
            }
            else {
                for (Long friendID : this.friendIDs){
                    User userFriend = Main.users.searchTree(friendID);

                    if (userFriend.getFriends().contains(user.id)){
                        recommendedFriends.add(user.id);
                    }
                }
            }
        }
        return recommendedFriends;
    }
    
    public boolean isCurrentUser() {
        return this.equals(Main.currentUser);
    }

}
