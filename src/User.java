import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User {

    private Long id;

    
    /** 
     * @return Long
     */
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

    
    /** 
     * @return String
     */
    public String getUsername() {
        return username;
    }

    
    /** 
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    
    /** 
     * @return String
     */
    public String getFullName() {
        return fullName;
    }

    
    /** 
     * @param fullName
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    
    /** 
     * @return String
     */
    public String getWorkPlace() {
        return workPlace;
    }

    
    /** 
     * @param workPlace
     */
    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    
    /** 
     * @return String
     */
    public String getHomeTown() {
        return homeTown;
    }

    
    /** 
     * @param homeTown
     */
    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }

    
    /** 
     * @return Long
     */
    public Long getPasswordHash(){
        return passwordHash;
    }

    
    /** 
     * @param passwordHash
     */
    public void setPasswordHash(Long passwordHash){
        this.passwordHash = passwordHash;
    }

    
    /** 
     * @return Set<Long>
     */
    public Set<Long> getFriends() {
        return friendIDs;
    }

    
    /** 
     * Adds a friend to user's set of friends.
     * @param id id of user to add as a friend.
     */
    public void addFriend(long id) {
        if(this.id == id) return;
        if(this.friendIDs.contains(id)) return;
        this.friendIDs.add(id);
        User friend = Main.users.searchTree(id);
        if(friend != null)
            friend.addFriend(this.id);
    }

    
    /** 
     * Checks if an object is equal to this user.
     * @param obj Object to check.
     * @return if obj is equal to this user.
     */
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof User)) return false;
        User otherUser = (User)obj;
        return otherUser.username == this.username;
    }

    
    /** 
     * Gets a Set of the ids of the recommend friends.
     * @return Set<Long>
     */
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
    
    
    /** 
     * @return boolean
     */
    public boolean isCurrentUser() {
        return this.equals(Main.currentUser);
    }

    
    /** 
     * @param otherUser
     * @return boolean
     */
    public boolean hasSameHomeTownAs(User otherUser) {
        String thisHomeTown = this.homeTown.toLowerCase();
        String otherHomeTown = otherUser.homeTown.toLowerCase();
        return thisHomeTown.equals(otherHomeTown) && !thisHomeTown.equals("hidden") && !otherHomeTown.equals("hidden");
    }

    
    /** 
     * @param otherUser
     * @return boolean
     */
    public boolean hasSameWorkPlaceAs(User otherUser) {
        String thisWorkPlace = this.workPlace.toLowerCase();
        String otherWorkPlace = otherUser.workPlace.toLowerCase();
        return thisWorkPlace.equals(otherWorkPlace) && !thisWorkPlace.equals("hidden") && !otherWorkPlace.equals("hidden");
    }

    
    /** 
     * @param otherUser
     * @return boolean
     */
    public boolean isFriendsWith(User otherUser) {
        return isFriendsWith(otherUser.id);
    }

    
    /** 
     * @param id
     * @return boolean
     */
    public boolean isFriendsWith(long id) {
        return this.friendIDs.contains(id);
    }

    
    /** 
     * @param otherUser
     * @return boolean
     */
    public boolean hasMutalFriendWith(User otherUser) {
        return hasMutalFriendWith(otherUser.id);
    }

    
    /** 
     * @param id
     * @return boolean
     */
    public boolean hasMutalFriendWith(long id) {
        for (Long friendID : this.friendIDs){
            User userFriend = Main.users.searchTree(friendID);

            if (userFriend.isFriendsWith(id)){
                return true;
            }
        }
        return false;
    }
    
    
    /** 
     * Removes a friend from this user
     * @param id id of the friend to remove
     */
    public void removeFriend(long id) {
        this.friendIDs.remove(id);
        Main.users.searchTree(id).friendIDs.remove(this.id);
    }

    
    /** 
     * Sorts an array of users ascendingly.
     * @param toSort the array to sort.
     */
    public static void sortAscending(User[] toSort) {
        Arrays.sort(toSort, new Comparator<User>() {
            public int compare(User a, User b)
            {
                return a.fullName.compareTo(b.fullName);
            }
        }
        );
    }

    
   /** 
     * Sorts an array of users descendingly.
     * @param toSort the array to sort.
     */
    public static void sortDescending(User[] toSort) {
        Arrays.sort(toSort, new Comparator<User>() {
            public int compare(User a, User b)
            {
                return b.fullName.compareTo(a.fullName);
            }
        }
        ); 
    }  

    
    /** 
     * Gets the data of the user as a single string.
     * @return Data of the user as a single string.
     */
    @Override
    public String toString() {
        return fullName + "\n" + username + "\n" + passwordHash.toString() + "\n" + workPlace + "\n" + homeTown + "\n" + friendIDs.size() + "\n";
    }

}
