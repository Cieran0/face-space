

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class WelcomePage implements Screen {

    JButton login;
    JButton register;
    JLabel face;

    public void addComponents(JFrame frame) {
        frame.add(login);
        frame.add(register);
        frame.add(face);
    }

    public WelcomePage() {
        login = new JButton("Login");
        register = new JButton("Register");
        face = new JLabel("<html><span style='font-size:75px;'>FaceSpace</span></html>",SwingConstants.CENTER);
        login.setBounds(100, 600, 400, 50);
        register.setBounds(780, 600, 400, 50);
        face.setBounds(1280/2 - 1000/2, 300, 1000, 100);
        login.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Main.setPopupScreen(new LoginPage());
            }
        }
        );
        register.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Main.setPopupScreen(new RegisterPage());
            }
        }
        );
    }

    @Override
    public int getWidth() {
        return Main.MAIN_WINDOW_WIDTH;
    }

    @Override
    public int getHeight() {
        return Main.MAIN_WINDOW_HEIGHT;
    }

}
