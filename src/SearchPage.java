import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JTextField;

public class SearchPage implements Screen{
    public final Integer WIDTH = 300;
    public final Integer HEIGHT = 310;

    Label searchLabel;
    JTextField searchField;
    Button searchButton;

    public void addComponents(JFrame frame){
        frame.setLocationRelativeTo(null);
        frame.add(searchLabel);
        frame.add(searchField);
        frame.add(searchButton);
    }

    public SearchPage(){
        searchButton = new Button("Search")
        .bounds(50,100,200,50)
        .actionListener(new ActionListener() 
            {
                public void actionPerformed(ActionEvent arg0){
                    User searchedUser;

                    searchedUser = Main.users.searchTree(Hash.hash(searchField.getText()));
                    if(searchedUser == null) {
                        showMessageDialog(null, "User not found");
                        return;
                    }

                    for(User user : Main.users.asList()){
                        if(searchedUser.getFullName().equals(user.getFullName())){
                            Main.hidePopup();
                            Main.setMainScreen(new HomePage(searchedUser));
                        }
                        else if(searchedUser.getUsername().equals(user.getUsername())){
                            Main.hidePopup();
                            Main.setMainScreen(new HomePage(searchedUser));
                        }
                    }
                }
            }
        );

        searchLabel = new Label("Search")
        .bigger()
        .bounds(50,0,200,50);

        searchField = new JTextField();
        searchField.setBounds(50,50,200,50);
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
