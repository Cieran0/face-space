import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class RegisterPage implements Screen {

    JButton registerButton;
    JTextField usernameField;
    JTextField passwordField;
    JTextField confirmPasswordField;
    JLabel usernameLabel;
    JLabel passwordLabel;
    JLabel confirmPasswordLabel;

    public void clear() {
        Main.popup.remove(registerButton);
        Main.popup.remove(usernameField);
        Main.popup.remove(passwordField);
        Main.popup.remove(usernameLabel);
        Main.popup.remove(passwordLabel);
        Main.popup.remove(confirmPasswordField);
        Main.popup.remove(confirmPasswordLabel);
        Main.popup.revalidate();
        Main.popup.repaint();
    }

    public void addComponents() {
        Main.popup.add(registerButton);
        Main.popup.add(usernameField);
        Main.popup.add(passwordField);
        Main.popup.add(usernameLabel);
        Main.popup.add(passwordLabel);
        Main.popup.add(confirmPasswordField);
        Main.popup.add(confirmPasswordLabel);
    }
    
    public RegisterPage() {
        registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                Main.users.add(new User(usernameField.getText(),usernameField.getText(),passwordField.getText()));
                Main.popup.setVisible(false);
                Main.popupScreen.clear();
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
}
