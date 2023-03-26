import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserTree {

    private class Node {
        Long id;
        User user;
        Node left;
        Node right;

        Node(User user) {
            this.user = user;
            this.id= user.getId();
            this.left = null;
            this.right = null;
        }
    }

    Node root;
    Set<Long> idSet = new HashSet<Long>();

    
    /** 
     * Add a user to the tree.
     * @param user the user to add.
     */
    public void insertUser(User user){

        if(user == null){
            return;
        }

        if(root == null){
            root = new Node(user);
            idSet.add(user.getId());
            return;
        }

        Node head = root;
        while (true){
        if (user.getId() < head.id) {
            if (head.left == null){
                head.left = new Node(user);
                idSet.add(user.getId());
                break;
            }
            else{
                head = head.left;
            }
        }
        else {
            if (head.right == null) {
                head.right = new Node(user);
                idSet.add(user.getId());
                break;
            } else {
                head = head.right;
            }
        }
        }

    }

    
    /** 
     * Find the user in a tree from the given id.
     * @param id
     * @return User
     */
    public User searchTree(long id){
        if(root==null) return null;
        Node head = root;

        while(head.id != id){
            if (head != null){
                if (head.id > id){
                    head = head.left;
                }
                else {
                    head = head.right;
                }

                if (head == null) {
                    return null;
                }
            }
        }

        return head.user;
    }

    /** 
     * Prints the tree
     */
    public void print() {
        print(this.root);
    }

    
    /** 
     * Prints the tree
     * @param root the starting point
     */
    private void print(Node root){
        if(root == null) {
            return;
        }
        print(root.left);
        System.out.println("ID: " + root.user.getId() + ". " + "Username: " + root.user.getUsername() + " Name: " +root.user.getFullName());
        print(root.right);
    }

    
    /** 
     * Gets the users in the tree as a list of users.
     * @return Every user in the tree in a list. 
     */
    public List<User> asList(){

        List<User> newList = new ArrayList<User>();

        for (Long id:idSet){
            newList.add(searchTree(id));
        }

        return newList;
    }
}