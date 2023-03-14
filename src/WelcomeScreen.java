

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class WelcomeScreen implements Screen {

    JButton login;
    JButton register;
    JLabel face;

    @Override
    public void clear() {
        Main.f.remove(login);
        Main.f.remove(register);
        Main.f.remove(face);
        Main.f.revalidate();
        Main.f.repaint();
    }

    @Override
    public void addComponents() {
        Main.f.add(login);
        Main.f.add(register);
        Main.f.add(face);
    }

    public WelcomeScreen() {
        login = new JButton("Login");
        register = new JButton("Register");
        face = new JLabel("<html><span style='font-size:75px;'>FaceSpace</span></html>",SwingConstants.CENTER);
        login.setBounds(100, 600, 400, 50);
        register.setBounds(780, 600, 400, 50);
        face.setBounds(1280/2 - 1000/2, 300, 1000, 100);
        login.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Main.popupScreen = new LoginPage();
                Main.popupScreen.addComponents();
                Main.popup.setVisible(true);
            }
        }
        );
        register.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Main.popupScreen = new RegisterPage();
                Main.popupScreen.addComponents();
                Main.popup.setVisible(true);
            }
        }
        );
    }
    
}
