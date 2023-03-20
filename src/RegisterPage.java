import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class RegisterPage implements Screen {

    public final Integer WIDTH = 300;
    public final Integer HEIGHT = 550;

    JButton registerButton;
    JTextField fullNameField;
    JTextField usernameField;
    JTextField passwordField;
    JTextField confirmPasswordField;
    JLabel fullNameLabel;
    JLabel usernameLabel;
    JLabel passwordLabel;
    JLabel confirmPasswordLabel;

    public void addComponents(JFrame frame) {
        frame.add(registerButton);
        frame.add(fullNameLabel);
        frame.add(fullNameField);
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
                Main.users.insertUser(new User(fullNameField.getText(),usernameField.getText(),Hash.hash(passwordField.getText()),"Hidden","Hidden"));
                Main.login(usernameField.getText(), passwordField.getText());
            }
        });
        fullNameLabel = new JLabel("<html><span style='font-size:16px;'>Full Name:</span></html>");
        usernameLabel = new JLabel("<html><span style='font-size:16px;'>Username:</span></html>");
        passwordLabel = new JLabel("<html><span style='font-size:16px;'>Password:</span></html>");
        confirmPasswordLabel = new JLabel("<html><span style='font-size:16px;'>Confirm Password:</span></html>");

        fullNameField = new JTextField("");
        usernameField = new JTextField("");
        passwordField = new JTextField("");
        confirmPasswordField = new JTextField("");

        fullNameLabel.setBounds(50, 0, 200, 50);
        fullNameField.setBounds(50, 50, 200, 50);
        usernameLabel.setBounds(50, 100, 200, 50);
        usernameField.setBounds(50, 150, 200, 50);
        passwordLabel.setBounds(50, 200, 200, 50);
        passwordField.setBounds(50, 250, 200, 50);
        confirmPasswordLabel.setBounds(50, 300, 200, 50);
        confirmPasswordField.setBounds(50, 350, 200, 50);
        registerButton.setBounds(50, 425, 200, 50);
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
