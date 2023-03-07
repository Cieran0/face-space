

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
        Gui.popup.remove(loginButton);
        Gui.popup.remove(usernameField);
        Gui.popup.remove(passwordField);
        Gui.popup.remove(usernameLabel);
        Gui.popup.remove(passwordLabel);
        Gui.popup.revalidate();
        Gui.popup.repaint();
    }

    public void addComponents() {
        Gui.popup.add(loginButton);
        Gui.popup.add(usernameField);
        Gui.popup.add(passwordField);
        Gui.popup.add(usernameLabel);
        Gui.popup.add(passwordLabel);
    }
    
    public LoginPage() {
        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                Gui.popup.setVisible(false);
                Gui.popupScreen.clear();
                System.out.println(usernameField.getText());
                System.out.println(passwordField.getText());
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
