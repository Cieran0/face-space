import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import javax.swing.*;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class Main {
    
    public static final Integer MAIN_WINDOW_WIDTH = 1280;
    public static final Integer MAIN_WINDOW_HEIGHT = 720;
    public static JFrame mainWindow = new JFrame("DEBUG");
    public static Screen mainScreen = new WelcomePage();
    public static JFrame popupWindow = new JFrame("DEBUG");
    public static Screen popupScreen;
    public static UserTree users = new UserTree();
    public static User currentUser = null;
    public static Queue<Post> allPosts = new LinkedList<Post>();

    public static WindowListener wl = new WindowListener() {
        public void windowClosing(WindowEvent arg0) {
            writeToFile(users,allPosts);
            System.exit(0);
        }

        public void windowActivated(WindowEvent arg0) {}
        public void windowClosed(WindowEvent arg0) {}
        public void windowDeactivated(WindowEvent arg0) {}
        public void windowDeiconified(WindowEvent arg0) {}
        public void windowIconified(WindowEvent arg0) {}
        public void windowOpened(WindowEvent arg0) {}
    };

    
    /** 
     * Shows a popup error message.
     * @param text Text of the error message.
     */
    public static void showErrorMessage(String text) {
        Label textLabel = new Label(text).fgColour(Theme.PRIMARY_FG);
        JOptionPane.showMessageDialog(null,textLabel);
    }

    /*
     * Sets up the application.
     */
    public static void main(String[] args) {
        UIManager.put("OptionPane.background", Theme.PRIMARY_BG);
        UIManager.put("Panel.background", Theme.PRIMARY_BG);

        readFile();

        mainWindow.getContentPane().setBackground(Theme.PRIMARY_BG);
        popupWindow.getContentPane().setBackground(Theme.PRIMARY_BG);
        setMainScreen(mainScreen); 
        mainWindow.setSize(MAIN_WINDOW_WIDTH,MAIN_WINDOW_HEIGHT); 
        mainWindow.setLayout(null);//using no layout managers  
        popupWindow.setLayout(null);//using no layout managers  
        mainWindow.setVisible(true);//making the frame visible  
        mainWindow.addWindowListener(wl);
    }

    
    /** 
     * Sets the popup window to a screen.
     * @param s Screen to set the popup window to.
     */
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
    
    /**
     * Hides the popup window.
     */
    public static void hidePopup() {
        popupWindow.setVisible(false);
    }
    
    /** 
     * Clears a JFrame.
     * @param frame JFrame to clear.
     */
    public static void clearFrame(JFrame frame) {
        frame.getContentPane().removeAll();
    }

    
    /** 
     * Sets the main window to a screen.
     * @param s Screen to set the main window to.
     */
    public static void setMainScreen(Screen s) {
        clearFrame(mainWindow);
        mainWindow.revalidate();
        mainWindow.repaint();
        mainScreen = s;
        mainScreen.addComponents(mainWindow);
        mainWindow.revalidate();
        mainWindow.repaint();
    }

    
    /** 
     * @param username
     * @param password
     */
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
            showErrorMessage("Invalid Username/Password!");
            return;
        }
        setMainScreen(new HomePage(currentUser));
    }

    public static void logout() {
        setMainScreen(new WelcomePage());
        currentUser = null;
    }

    
    /** 
     * Saves data to a file.
     * @param users Tree of users to save.
     * @param posts Queue of posts to save.
     */
    public static void writeToFile(UserTree users, Queue<Post> posts){
        List<User> userList = users.asList(); 
        int numberOfUsers = userList.size();
        try{
            FileWriter writer = new FileWriter("database.txt");
            writer.write(numberOfUsers + "\n");
            for (User user : userList) {
                writer.write(user.toString());
                Long[] friends = user.getFriends().toArray(new Long[user.getFriends().size()]);
                for(int i = 0; i< friends.length; i++){
                    writer.write(friends[i] + "\n");
                }
            }
            Integer numberOfPosts = allPosts.size();
            writer.write(numberOfPosts.toString() + '\n');
            for (Post post : allPosts) {
                writer.write(post.toString());
                writer.write("\n");
            }
            writer.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /** 
     * Reads user and post data from a file.
     */
    public static void readFile(){
        int noFriends = 0;
        try{
            File f = new File("database.txt");
            Scanner scRead = new Scanner(f);
            int numberOfUsers = Integer.parseInt(scRead.nextLine());
            for (int i = 0; i < numberOfUsers; i++) {

                String fullName = scRead.nextLine();
                String username = scRead.nextLine();
                Long passwordHash = Long.parseLong(scRead.nextLine());
                String workPlace = scRead.nextLine();
                String homeTown = scRead.nextLine();

                User newUser = new User(fullName, username, passwordHash, workPlace, homeTown);
                Main.users.insertUser(newUser);

                noFriends = Integer.parseInt(scRead.nextLine());
                for(int j = 0; j<noFriends; j++){
                    newUser.addFriend(Long.parseLong(scRead.nextLine()));
                }
            }

            scRead.useDelimiter("\3");
            int count = Integer.parseInt(scRead.nextLine());
            while (count >= 1) {
                Long posterId = Long.parseLong(scRead.nextLine());
                Integer likeCount = Integer.parseInt(scRead.nextLine());
                Set<Long> likedBy = new HashSet<Long>();
                for (int i = 0; i < likeCount; i++) {
                    likedBy.add(Long.parseLong(scRead.nextLine()));
                }
                String title = scRead.nextLine();
                String content = scRead.next();
                allPosts.add(new Post(posterId, likedBy, title, content));
                scRead.nextLine();
                count--;
            }
            scRead.close();
        }
            catch(Exception e){
            e.printStackTrace();
        }
    }
}

