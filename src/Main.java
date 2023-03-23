import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import javax.swing.*;
import java.util.Queue;
import java.util.Scanner;
import java.util.regex.Pattern;

import static javax.swing.JOptionPane.showMessageDialog;

public class Main {
    
    public static final Integer MAIN_WINDOW_WIDTH = 1280;
    public static final Integer MAIN_WINDOW_HEIGHT = 720;
    public static JFrame mainWindow = new JFrame("DEBUG");//creating instance of JFrame  
    public static Screen mainScreen = new WelcomePage();
    public static JFrame popupWindow = new JFrame("DEBUG");
    public static Screen popupScreen;
    public static UserTree users = new UserTree();
    public static User currentUser = null;
    public static Queue<Post> allPosts = new LinkedList<Post>();

    public static WindowListener wl = new WindowListener() {
        public void windowClosing(WindowEvent arg0) {
            writeToFile(users);
            writePostsToFile(allPosts);
            System.exit(0);
        }

        public void windowActivated(WindowEvent arg0) {}
        public void windowClosed(WindowEvent arg0) {}
        public void windowDeactivated(WindowEvent arg0) {}
        public void windowDeiconified(WindowEvent arg0) {}
        public void windowIconified(WindowEvent arg0) {}
        public void windowOpened(WindowEvent arg0) {}
    };

    public static void main(String[] args) {
        readInPosts();
        readFile();
//        for (User user : users.asList()) {
//            for (User friend : users.asList()) {
//                user.addFriend(friend.getId());
//            }
//        }
        setMainScreen(mainScreen); 
        mainWindow.setSize(MAIN_WINDOW_WIDTH,MAIN_WINDOW_HEIGHT); 
        mainWindow.setLayout(null);//using no layout managers  
        popupWindow.setLayout(null);//using no layout managers  
        mainWindow.setVisible(true);//making the frame visible  
        mainWindow.addWindowListener(wl);

    }

    public static void setPopupScreen(Screen s) {
        clearFrame(popupWindow);
        popupWindow.setBounds(0, 0, s.getWidth(), s.getHeight());
        popupWindow.revalidate();
        popupWindow.repaint();
        popupScreen = s;
        popupScreen.addComponents(popupWindow);
        popupWindow.revalidate();
        popupWindow.repaint();
        popupWindow.setVisible(true);
    }
    
    public static void hidePopup() {
        popupWindow.setVisible(false);
    }

    public static void clearFrame(JFrame frame) {
        frame.getContentPane().removeAll();
    }

    public static void setMainScreen(Screen s) {
        clearFrame(mainWindow);
        mainWindow.revalidate();
        mainWindow.repaint();
        mainScreen = s;
        mainScreen.addComponents(mainWindow);
        mainWindow.revalidate();
        mainWindow.repaint();
    }

    public static void login(String username, String password) {
        hidePopup();
        boolean success = false;
        long passwordHash = Hash.hash(password);
        for (User user : users.asList()) {
            if(user.getUsername().equals(username) && user.getPasswordHash() == passwordHash) {
                success = true;
                currentUser = user;
                break;
            }
        }
        if(!success) {
            hidePopup();            
            showMessageDialog(null, "Invalid Username/Password!");
            return;
        }
        setMainScreen(new HomePage(currentUser));
    }

    public static void logout() {
        setMainScreen(new WelcomePage());
        currentUser = null;
    }

    public static Long getUserID(User user) {
        return user.getId();
    }

    public static void writeToFile(UserTree users){
        int noFriends = 0;
        try{
            FileWriter writer = new FileWriter("accounts.txt");
            for (User user : users.asList()) {
                noFriends = user.getFriends().size();
                writer.write(user.getFullName() + "\n"+user.getUsername()+"\n" + user.getPasswordHash().toString() + "\n" + user.getWorkPlace() + "\n"+user.getHomeTown() +"\n" + noFriends + "\n");
                //Long[] friends = user.getFriends().toArray(Long[]::new);
                Long[] friends = user.getFriends().toArray(new Long[user.getFriends().size()]);
                for(int i = 0; i< noFriends; i++){
                    writer.write(friends[i] + "\n");
                }
            }
            writer.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void readFile(){
        int noFriends = 0;
        try{
            File f = new File("accounts.txt");
            Scanner scRead = new Scanner(f);
            while(scRead.hasNextLine()){
                User newUser = new User(scRead.nextLine(),scRead.nextLine(),Long.parseLong(scRead.nextLine()),scRead.nextLine(),scRead.nextLine());
                Main.users.insertUser(newUser);
                noFriends = scRead.nextInt();
                scRead.nextLine();
                for(int i = 0; i<noFriends; i++){
                    newUser.addFriend(scRead.nextLong());
                    scRead.nextLine();
                }
            }
            scRead.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private static void writePostsToFile(Queue<Post> allPosts) {
        try{
            FileWriter writer = new FileWriter("posts.txt");
            Integer numberOfPosts = allPosts.size();
            writer.write(numberOfPosts.toString() + '\n');
            for (Post post : allPosts) {
                writer.write(post.toString());
            }
            writer.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private static void readInPosts() {
        Scanner scan;
        //TODO: make likes be read in and wrote out!
        try {
            scan = new Scanner(new File("posts.txt"));
            int count = scan.nextInt();
            scan.useDelimiter("\3");
            scan.nextLine();
            while (count >= 1) {
                Long posterId = Long.parseLong(scan.nextLine());
                String title = scan.nextLine();
                String content = scan.next();
                allPosts.add(new Post(posterId, title, content));
                count--;
            }
            scan.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

