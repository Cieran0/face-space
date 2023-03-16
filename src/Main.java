
import java.awt.Component;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;
import java.util.List;
import java.util.Scanner;

public class Main {
    
    public static final Integer MAIN_WINDOW_WIDTH = 1280;
    public static final Integer MAIN_WINDOW_HEIGHT = 720;
    public static JFrame mainWindow = new JFrame("DEBUG");//creating instance of JFrame  
    public static Screen mainScreen = new WelcomePage();
    public static JFrame popupWindow = new JFrame("DEBUG");
    public static Screen popupScreen;
    public static List<User> users = new ArrayList<User>(); 
    public static User currentUser = null;

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
        //users.add(new User("cieran0","Cieran O'Neill", Hash.hash("password?")));
        //users.add(new User("benh99","Ben H", Hash.hash("penis")));
        //users.add(new User("micha","Baldie McBaldface", Hash.hash("help")));
        readFile();
        for (int i = 0, j =0; i < users.size();) {
            users.get(j).addFriend(i);
            if(++j >= users.size()) {
                j=0;
                i++;
            }
        }
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
        for (User user : users) {
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

    public static Integer getUserID(User user) {
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).equals(user)) {
                return i;
            }
        }
        return -1;
    }

    public static void writeToFile(List<User> users){
        try{
            FileWriter writer = new FileWriter("accounts.txt");
            for (User user : users) {
                writer.write(user.getFullName() + "\n"+user.getUsername()+"\n" + user.getPasswordHash().toString() + "\n");
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
                Main.users.add(new User(scRead.nextLine(),scRead.nextLine(),Long.parseLong(scRead.nextLine())));
            }
            System.out.println(users.size());
            scRead.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

