import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class LoginPage implements Screen {

    public final Integer WIDTH = 300;
    public final Integer HEIGHT = 400;

    Button loginButton;
    TextField usernameField;
    TextField passwordField;
    Label usernameLabel;
    Label passwordLabel;

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
        usernameLabel = new Label("Username:").big()
        .bounds(50, 0, 200, 50);

        passwordLabel = new Label("Password:").big()
        .bounds(50, 100, 200, 50);

        usernameField = new TextField("")
        .bounds(50, 50, 200, 50);

        passwordField = new TextField("")
        .bounds(50, 150, 200, 50);

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
