
import java.awt.Component;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.*;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

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
        readFile();
        for (User user : users.asList()) {
            for (User friend : users.asList()) {
                user.addFriend(friend.getId());
            }
        }
        for (int i = 0; i < 10; i++) {
            allPosts.add(new Post(Hash.hash("cieran0"), "test", "tets"));
        }
        allPosts.add(new Post(Hash.hash("benh99"), "Tetsing", "Hey @cieran0 this is a test!"));
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
            setPopupScreen(new AlertPopup(250, 100, "Invalid username/password"));            
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
        try{
            FileWriter writer = new FileWriter("accounts.txt");
            for (User user : users.asList()) {
                writer.write(user.getFullName() + "\n"+user.getUsername()+"\n" + user.getPasswordHash().toString() + "\n" + user.getWorkPlace() + "\n"+user.getHomeTown() +"\n" );
            }
            writer.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void readFile(){
        try{
            File f = new File("accounts.txt");
            Scanner scRead = new Scanner(f);
            while(scRead.hasNextLine()){
                Main.users.insertUser(new User(scRead.nextLine(),scRead.nextLine(),Long.parseLong(scRead.nextLine()),scRead.nextLine(),scRead.nextLine()));
            }
            scRead.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

