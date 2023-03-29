import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.SwingConstants;

public class SearchPage implements Screen{
    public final Integer WIDTH = 300;
    public final Integer HEIGHT = 225;

    Label searchLabel;
    TextField searchField;
    Button searchButton;

    
    /** 
     * Adds the components of the page to the JFrame.
     * @param frame The JFrame the components are being added to.
     */
    public void addComponents(JFrame frame){
        frame.setLocationRelativeTo(null);
        frame.add(searchLabel);
        frame.add(searchField);
        frame.add(searchButton);
    }

    public SearchPage(){
        searchButton = new Button("Search")
        .bounds(50,125,200,50)
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
        .bounds(0,9,WIDTH,40);

        searchField = new TextField("")
        .bounds(50,60,200,50);
    }

    
    /** 
     * @return The Page's WIDTH.
     */
    @Override
    public int getWidth() {
        return WIDTH;
    }

    
    /** 
     * @return The Page's Height.
     */
    @Override
    public int getHeight() {
        return HEIGHT;
    }
}
