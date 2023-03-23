

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class WelcomePage implements Screen {

    Button login;
    Button register;
    JLabel face;

    public void addComponents(JFrame frame) {
        frame.add(login);
        frame.add(register);
        frame.add(face);
    }

    public WelcomePage() {

        face = new JLabel("<html><span style='font-size:75px;'>FaceSpace</span></html>",SwingConstants.CENTER);
        face.setBounds(1280/2 - 1000/2, 300, 1000, 100);

        login = new Button("Login")
        .bounds(100, 600, 400, 50)
        .actionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent arg0) {
                    Main.setPopupScreen(new LoginPage());
                }
            }
        );

        register = new Button("Register")
        .bounds(780, 600, 400, 50)
        .actionListener(new ActionListener()
            {
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
