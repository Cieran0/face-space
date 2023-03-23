import javax.swing.JFrame;

public class HomePage implements Screen{

    private boolean isCurrentUser;
    private User profile;
    private FriendsPanel friendsPanel;
    private DetailsPanel detailsPanel;
    private PostsPanel postsPanel;
    
    public HomePage(User profile) {
        this.isCurrentUser = profile.equals(Main.currentUser);
        this.profile = profile;

        this.friendsPanel = new FriendsPanel(isCurrentUser, profile.getFriends(),Theme.SECONDARY_FG,Theme.SECONDARY_BG);
        this.friendsPanel.setBounds(Main.MAIN_WINDOW_WIDTH-300, 0, 300, Main.MAIN_WINDOW_HEIGHT);

        this.detailsPanel = new DetailsPanel(profile,Theme.SECONDARY_FG,Theme.SECONDARY_BG);
        this.detailsPanel.setBounds(0, 0, 300, Main.MAIN_WINDOW_HEIGHT);

        this.postsPanel = new PostsPanel(profile, Theme.SECONDARY_FG,Theme.PRIMARY_BG);
        this.postsPanel.setBounds(300,0,680,Main.MAIN_WINDOW_HEIGHT);
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
        frame.setLocationRelativeTo(null);
        frame.add(friendsPanel);
        frame.add(detailsPanel);
        frame.add(postsPanel);
    }

    public void reloadPosts() {
        this.postsPanel = new PostsPanel(profile, Theme.SECONDARY_FG,Theme.PRIMARY_BG);
        this.postsPanel.setBounds(300,0,680,Main.MAIN_WINDOW_HEIGHT);
        this.postsPanel.reloadPosts(profile, 0,0);
        Main.setMainScreen(this);
    }

}
