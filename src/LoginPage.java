

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class LoginPage implements Screen {

    public final Integer WIDTH = 300;
    public final Integer HEIGHT = 400;

    Button loginButton;
    JTextField usernameField;
    JTextField passwordField;
    JLabel usernameLabel;
    JLabel passwordLabel;

    public void addComponents(JFrame frame) {
        frame.setLocationRelativeTo(null);
        frame.add(loginButton);
        frame.add(usernameField);
        frame.add(passwordField);
        frame.add(usernameLabel);
        frame.add(passwordLabel);
    }
    
    public LoginPage() {
        loginButton = new Button("Login")
        .actionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent arg0) {
                    Main.login(usernameField.getText(),passwordField.getText());
                }
            }
        );
        usernameLabel = new JLabel("<html><span style='font-size:16px;'>Username:</span></html>");
        passwordLabel = new JLabel("<html><span style='font-size:16px;'>Password:</span></html>");
        usernameField = new JTextField("");
        passwordField = new JTextField("");
        usernameLabel.setBounds(50, 0, 200, 50);
        usernameField.setBounds(50, 50, 200, 50);
        passwordLabel.setBounds(50, 100, 200, 50);
        passwordField.setBounds(50, 150, 200, 50);
        loginButton.setBounds(50, 225, 200, 50);
    }

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }
}
