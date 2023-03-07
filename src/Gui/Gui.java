
package Gui;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;  
import java.util.List;

public class Gui {
    
    public static Screen menu = new MainMenu();
    public static JFrame f = new JFrame("DEBUG");//creating instance of JFrame  
    public static JFrame popup = new JFrame("DEBUG");
    public static Screen popupScreen;
    public static List<User> users = new ArrayList<User>(); 

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

        menu.addComponents();
        popup.setSize(300,400);//300 width and 500 height  
        popup.setLayout(null);//using no layout managers  
        f.setSize(1280,720);//400 width and 500 height  
        f.setLayout(null);//using no layout managers  
        f.setVisible(true);//making the frame visible  
        f.addWindowListener(wl);

    }
}

