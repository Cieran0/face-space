import java.awt.Color;
import javax.swing.JPanel;

public class HomePage implements Screen{

    public void clear(){
        Main.f.remove(friendsPanel);
        Main.f.remove(detailsPanel);
        Main.popup.revalidate();
        Main.popup.repaint();
    }

    public void addComponents(){
        Main.f.add(friendsPanel);
        Main.f.add(detailsPanel);
    }

    private boolean isCurrentUser;
    private User profile;
    private JPanel friendsPanel;
    private JPanel detailsPanel;
    
    public HomePage(User profile) {
        this.isCurrentUser = profile.equals(Main.currentUser);
        this.profile = profile;

        this.friendsPanel = new FriendsPanel(isCurrentUser, profile.getFriends(),Color.WHITE,Color.DARK_GRAY);
        this.friendsPanel.setBounds(Main.MAIN_WINDOW_WIDTH-300, 0, 300, Main.MAIN_WINDOW_HEIGHT);

        this.detailsPanel = new DetailsPanel(profile,Color.WHITE,Color.DARK_GRAY);
        this.detailsPanel.setBounds(0, 0, 300, Main.MAIN_WINDOW_HEIGHT);
    }


}
