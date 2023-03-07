package Gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class MainMenu implements Screen {

    JButton login;
    JButton register;
    JLabel face;

    @Override
    public void clear() {
        Gui.f.remove(login);
        Gui.f.remove(register);
        Gui.f.remove(face);
        Gui.f.revalidate();
        Gui.f.repaint();
    }

    @Override
    public void addComponents() {
        Gui.f.add(login);
        Gui.f.add(register);
        Gui.f.add(face);
    }

    public MainMenu() {
        login = new JButton("Login");
        register = new JButton("Register");
        face = new JLabel("<html><span style='font-size:75px;'>FaceSpace</span></html>",SwingConstants.CENTER);
        login.setBounds(100, 600, 400, 50);
        register.setBounds(780, 600, 400, 50);
        face.setBounds(1280/2 - 1000/2, 300, 1000, 100);
        login.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Gui.popupScreen = new LoginPage();
                Gui.popupScreen.addComponents();
                Gui.popup.setVisible(true);
            }
        }
        );
        register.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Gui.popupScreen = new RegisterPage();
                Gui.popupScreen.addComponents();
                Gui.popup.setVisible(true);
            }
        }
        );
    }
    
}
