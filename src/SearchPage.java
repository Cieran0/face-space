import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.SwingConstants;

public class SearchPage implements Screen{
    public final Integer WIDTH = 300;
    public final Integer HEIGHT = 200;

    Label searchLabel;
    TextField searchField;
    Button searchButton;

    public void addComponents(JFrame frame){
        frame.setLocationRelativeTo(null);
        frame.add(searchLabel);
        frame.add(searchField);
        frame.add(searchButton);
    }

    public SearchPage(){
        searchButton = new Button("Search")
        .bounds(50,115,200,50)
        .actionListener(new ActionListener() 
            {
                public void actionPerformed(ActionEvent arg0){
                    Set<Long> foundUsers = new HashSet<Long>();
                    String searchTerm = searchField.getText().toLowerCase();

                    for (User user : Main.users.asList()) {
                        String name = user.getFullName().toLowerCase();
                        String username = user.getUsername().toLowerCase();
                        if(name.contains(searchTerm) || name.equals(searchTerm) || username.contains(searchTerm) || username.equals(searchTerm)) {
                            foundUsers.add(user.getId());
                        }
                    }
                    if(foundUsers.size() == 0) {
                        Main.hidePopup();
                        Main.showErrorMessage("No users found");
                        return;
                    }
                    Main.setPopupScreen(new PeopleListPage(true, foundUsers));
                }
            }
        );

        searchLabel = new Label("Search", SwingConstants.CENTER)
        .bigger()
        .bounds(0,9,WIDTH,32);

        searchField = new TextField("")
        .bounds(50,50,200,50);
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
