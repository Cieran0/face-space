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

    public void print() {
        print(this.root);
    }

    private void print(Node root){
        if(root == null) {
            return;
        }
        print(root.left);
        //System.out.println("ID: " + root.user.getId() + ". " + "Username: " + root.user.getUsername() + " Name: " +root.user.getFullName());
        print(root.right);
    }

    public List<User> asList(){

        List<User> newList = new ArrayList<User>();

        for (Long id:idSet){
            newList.add(searchTree(id));
        }

        return newList;
    }
}