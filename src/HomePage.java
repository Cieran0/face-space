import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

public class HomePage implements Screen{

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

    @Override
    public int getWidth() {
        return Main.MAIN_WINDOW_WIDTH;
    }

    @Override
    public int getHeight() {
        return Main.MAIN_WINDOW_HEIGHT;
    }

    @Override
    public void addComponents(JFrame frame) {
        frame.add(friendsPanel);
        frame.add(detailsPanel);
    }

}
