

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
        Gui.popup.remove(registerButton);
        Gui.popup.remove(usernameField);
        Gui.popup.remove(passwordField);
        Gui.popup.remove(usernameLabel);
        Gui.popup.remove(passwordLabel);
        Gui.popup.remove(confirmPasswordField);
        Gui.popup.remove(confirmPasswordLabel);
        Gui.popup.revalidate();
        Gui.popup.repaint();
    }

    public void addComponents() {
        Gui.popup.add(registerButton);
        Gui.popup.add(usernameField);
        Gui.popup.add(passwordField);
        Gui.popup.add(usernameLabel);
        Gui.popup.add(passwordLabel);
        Gui.popup.add(confirmPasswordField);
        Gui.popup.add(confirmPasswordLabel);
    }
    
    public RegisterPage() {
        registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                Gui.users.add(new User(usernameField.getText()));
                Gui.popup.setVisible(false);
                Gui.popupScreen.clear();
                for (User user : Gui.users) {
                    System.out.println(user.getUsername());
                }
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
