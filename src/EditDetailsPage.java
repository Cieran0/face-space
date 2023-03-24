import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class EditDetailsPage implements Screen {
    public final Integer WIDTH = 300;
    public final Integer HEIGHT = 550;

    Label fullNameLabel;
    Label workPlaceLabel;
    Label homeTownLabel;
    Label passwordLabel;
    TextField fullNameField;
    TextField workPlaceField;
    TextField homeTownField;
    TextField passwordField;
    Button saveDetailsButton;

    public void addComponents(JFrame frame){
        frame.setLocationRelativeTo(null);
        frame.add(fullNameLabel);
        frame.add(workPlaceLabel);
        frame.add(homeTownLabel);
        frame.add(passwordLabel);
        frame.add(saveDetailsButton);
        frame.add(fullNameField);
        frame.add(workPlaceField);
        frame.add(homeTownField);
        frame.add(passwordField);
    }

    public EditDetailsPage(){
        saveDetailsButton = new Button("Save Details")
        .bounds(50, 400, 200, 50)
        .actionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent arg0) {
                    Main.currentUser.setFullName(fullNameField.getText());
                    Main.currentUser.setHomeTown(homeTownField.getText());
                    Main.currentUser.setWorkPlace(workPlaceField.getText());

                    if(passwordField.getText().length() != 0){
                        Main.currentUser.setPasswordHash(Hash.hash(passwordField.getText()));
                    }
                    for(User user : Main.users.asList()){
                        if(user.getUsername().equals(Main.currentUser.getUsername())){
                            user = Main.currentUser;
                        }
                    }
                    Main.hidePopup();
                    Main.setMainScreen(new HomePage(Main.currentUser));
                }
            }
        );

        fullNameLabel = new Label("Full Name:").big()
        .bounds(50, 0, 200, 50);

        homeTownLabel = new Label("Home Town:").big()
        .bounds(50, 100, 200, 50);

        workPlaceLabel = new Label("Work Place:").big()
        .bounds(50, 200, 200, 50);

        passwordLabel = new Label("Password:").big()
        .bounds(50, 300, 200, 50);

        fullNameField = new TextField(Main.currentUser.getFullName())
        .bounds(50, 50, 200, 50);

        homeTownField = new TextField(Main.currentUser.getHomeTown())
        .bounds(50, 150, 200, 50);

        workPlaceField = new TextField(Main.currentUser.getWorkPlace())
        .bounds(50, 250, 200, 50);
        
        passwordField = new TextField("")
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
