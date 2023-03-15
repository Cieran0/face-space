import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class RegisterPage implements Screen {

    public final Integer WIDTH = 300;
    public final Integer HEIGHT = 400;

    JButton registerButton;
    JTextField usernameField;
    JTextField passwordField;
    JTextField confirmPasswordField;
    JLabel usernameLabel;
    JLabel passwordLabel;
    JLabel confirmPasswordLabel;

    public void addComponents(JFrame frame) {
        frame.add(registerButton);
        frame.add(usernameField);
        frame.add(passwordField);
        frame.add(usernameLabel);
        frame.add(passwordLabel);
        frame.add(confirmPasswordField);
        frame.add(confirmPasswordLabel);
    }
    
    public RegisterPage() {
        registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                Main.users.add(new User(usernameField.getText(),usernameField.getText(),Hash.hash(passwordField.getText())));
                Main.login(usernameField.getText(), passwordField.getText());
            }
        });
        usernameLabel = new JLabel("<html><span style='font-size:16px;'>Username:</span></html>");
        passwordLabel = new JLabel("<html><span style='font-size:16px;'>Password:</span></html>");
        confirmPasswordLabel = new JLabel("<html><span style='font-size:16px;'>Confirm Password:</span></html>");

        usernameField = new JTextField("");
        passwordField = new JTextField("");
        confirmPasswordField = new JTextField("");

        usernameLabel.setBounds(50, 0, 200, 50);
        usernameField.setBounds(50, 50, 200, 50);
        passwordLabel.setBounds(50, 100, 200, 50);
        passwordField.setBounds(50, 150, 200, 50);
        confirmPasswordLabel.setBounds(50, 200, 200, 50);
        confirmPasswordField.setBounds(50, 250, 200, 50);
        registerButton.setBounds(50, 325, 200, 50);
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
