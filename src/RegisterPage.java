import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class RegisterPage implements Screen {

    public final Integer WIDTH = 300;
    public final Integer HEIGHT = 550;

    Button registerButton;
    TextField fullNameField;
    TextField usernameField;
    TextField passwordField;
    TextField confirmPasswordField;
    Label fullNameLabel;
    Label usernameLabel;
    Label passwordLabel;
    Label confirmPasswordLabel;

    public void addComponents(JFrame frame) {
        frame.setLocationRelativeTo(null);
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
        registerButton = new Button("Register")
        .bounds(50, 425, 200, 50)
        .actionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent arg0) {
                    String fullName = fullNameField.getText();
                    String username = usernameField.getText();
                    String password = passwordField.getText();
                    String confirmPassword = confirmPasswordField.getText();
                    String errorMessage = null;
                    long id = Hash.hash(username);

                    if(fullName.isBlank()) {
                        errorMessage = "Full Name field is empty";
                    } else if (username.isBlank()) {
                        errorMessage = "Username field is empty";
                    } else if (Main.users.searchTree(id) != null) {
                        errorMessage = "A user with that username already exists";
                    } else if (password.isBlank()) {
                        errorMessage = "Password field is empty";
                    } else if (confirmPassword.isBlank()) {
                        errorMessage = "Confirm Password field is empty";
                    } else if (!confirmPassword.equals(password)) {
                        errorMessage = "Confirm Password doesn't match Password";
                    } 

                    if(errorMessage != null) {
                        Main.showErrorMessage(errorMessage);
                        return;
                    }

                    Main.users.insertUser(new User(fullNameField.getText(),usernameField.getText(),Hash.hash(passwordField.getText()),"Hidden","Hidden"));
                    Main.login(usernameField.getText(), passwordField.getText());
                }
            }
        );

        fullNameLabel = new Label("Full Name:").big()
        .bounds(50, 0, 200, 50);

        usernameLabel = new Label("Username:").big()
        .bounds(50, 100, 200, 50);

        passwordLabel = new Label("Password:").big()
        .bounds(50, 200, 200, 50);

        confirmPasswordLabel = new Label("Confirm Password:").big()
        .bounds(50, 300, 200, 50);
        
        fullNameField = new TextField("")
        .bounds(50, 50, 200, 50);
        
        usernameField = new TextField("")
        .bounds(50, 150, 200, 50);
        
        passwordField = new TextField("")
        .bounds(50, 250, 200, 50);
        
        confirmPasswordField = new TextField("")
        .bounds(50, 350, 200, 50);
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
