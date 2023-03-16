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

    public void insertUser(User user){

        if(user == null){
            return;
        }

        if(root == null){
            root = new Node(user);
            return;
        }

        Node head = root;
        while (true){
        if (user.getId() < head.id) {
            if (head.left == null){
                head.left = new Node(user);
                break;
            }
            else{
                head = head.left;
            }
        }
        else {
            if (head.right == null) {
                head.right = new Node(user);
                break;
            } else {
                head = head.right;
            }
        }
        }
    }

    public User searchTree(long id){
        Node head = root;

        while(head.id != id){
            System.out.println(head.id);
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
        System.out.println("ID: " + root.user.getId() + ". " + "Username: " + root.user.getUsername() + " Name: " +root.user.getFullName());
        print(root.right);
    }
}