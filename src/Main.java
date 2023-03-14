
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import javax.swing.*;
import java.util.List;

public class Main {
    
    public static final Integer MAIN_WINDOW_WIDTH = 1280;
    public static final Integer MAIN_WINDOW_HEIGHT = 720;
    public static final Integer POPUP_WINDOW_WIDTH = 300;
    public static final Integer POPUP_WINDOW_HEIGHT = 400;
    public static Screen menu = new MainMenu();
    public static JFrame f = new JFrame("DEBUG");//creating instance of JFrame  
    public static JFrame popup = new JFrame("DEBUG");
    public static Screen popupScreen;
    public static List<User> users = new ArrayList<User>(); 
    public static User currentUser = null;

    public static WindowListener wl = new WindowListener() {
        public void windowClosing(WindowEvent arg0) {
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
        users.add(new User("cieran0","Cieran O'Neill", "password?"));
        users.add(new User("benh99","Ben H", "penis"));
        users.add(new User("micha","Baldie McBaldface", "help"));
        currentUser=users.get(0);
        for (int i = 0; i < users.size(); i++) {
            currentUser.addFriend(i);
        }
        menu.addComponents();
        popup.setSize(POPUP_WINDOW_WIDTH,POPUP_WINDOW_HEIGHT);//300 width and 500 height  
        popup.setLayout(null);//using no layout managers  
        f.setSize(MAIN_WINDOW_WIDTH,MAIN_WINDOW_HEIGHT);//400 width and 500 height  
        f.setLayout(null);//using no layout managers  
        f.setVisible(true);//making the frame visible  
        f.addWindowListener(wl);

    }

    public static void login(String username, String password) {
        if(!(currentUser.getUsername().equals(username) && currentUser.getPassword().equals(password)))
            return;
        menu.clear();
        menu = new HomePage(currentUser);
        menu.addComponents();
    }
}

