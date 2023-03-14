

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class LoginPage implements Screen {

    JButton loginButton;
    JTextField usernameField;
    JTextField passwordField;
    JLabel usernameLabel;
    JLabel passwordLabel;

    public void clear() {
        Main.popup.remove(loginButton);
        Main.popup.remove(usernameField);
        Main.popup.remove(passwordField);
        Main.popup.remove(usernameLabel);
        Main.popup.remove(passwordLabel);
        Main.popup.revalidate();
        Main.popup.repaint();
    }

    public void addComponents() {
        Main.popup.add(loginButton);
        Main.popup.add(usernameField);
        Main.popup.add(passwordField);
        Main.popup.add(usernameLabel);
        Main.popup.add(passwordLabel);
    }
    
    public LoginPage() {
        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                Main.popup.setVisible(false);
                Main.popupScreen.clear();
                Main.login(usernameField.getText(),passwordField.getText());
            }
        });
        usernameLabel = new JLabel("<html><span style='font-size:16px;'>Username:</span></html>");
        passwordLabel = new JLabel("<html><span style='font-size:16px;'>Password:</span></html>");
        usernameField = new JTextField("");
        passwordField = new JTextField("");
        usernameLabel.setBounds(50, 0, 200, 50);
        usernameField.setBounds(50, 50, 200, 50);
        passwordLabel.setBounds(50, 100, 200, 50);
        passwordField.setBounds(50, 150, 200, 50);
        loginButton.setBounds(50, 325, 200, 50);
    }
}
