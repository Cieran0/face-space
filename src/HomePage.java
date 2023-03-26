import javax.swing.JFrame;

public class HomePage implements Screen{

    private boolean isCurrentUser;
    private User profile;
    private FriendsPanel friendsPanel;
    private DetailsPanel detailsPanel;
    private PostsPanel postsPanel;
    
    /*
     * Creates a page for the user we are viewing.
     */
    public HomePage(User profile) {
        this.isCurrentUser = profile.equals(Main.currentUser);
        this.profile = profile;

        this.friendsPanel = new FriendsPanel(isCurrentUser, profile.getFriends());
        this.friendsPanel.setBounds(Main.MAIN_WINDOW_WIDTH-300, 0, 300, Main.MAIN_WINDOW_HEIGHT);

        this.detailsPanel = new DetailsPanel(profile);
        this.detailsPanel.setBounds(0, 0, 300, Main.MAIN_WINDOW_HEIGHT);

        this.postsPanel = new PostsPanel(profile);
        this.postsPanel.setBounds(300,0,680,Main.MAIN_WINDOW_HEIGHT);
    }

    
    /** 
     * @return The Page's Width.
     */
    @Override
    public int getWidth() {
        return Main.MAIN_WINDOW_WIDTH;
    }

    
    /** 
     * @return The Page's Height.
     */
    @Override
    public int getHeight() {
        return Main.MAIN_WINDOW_HEIGHT;
    }

    
    /** 
     * Adds the components of the page to the JFrame.
     * @param frame The JFrame the components are being added to.
     */
    @Override
    public void addComponents(JFrame frame) {
        frame.add(friendsPanel);
        frame.add(detailsPanel);
        frame.add(postsPanel);
    }

    /**
     * Reloads the posts panel
     */
    public void reloadPosts() {
        this.postsPanel = new PostsPanel(profile);
        this.postsPanel.setBounds(300,0,680,Main.MAIN_WINDOW_HEIGHT);
        this.postsPanel.reloadPosts(profile, 0,0);
        Main.setMainScreen(this);
    }

    /**
     * Reloads the friends panel.
     */
    public void reloadFriends() {
        this.friendsPanel = new FriendsPanel(isCurrentUser, profile.getFriends());
        this.friendsPanel.setBounds(Main.MAIN_WINDOW_WIDTH-300, 0, 300, Main.MAIN_WINDOW_HEIGHT);
        Main.setMainScreen(this);
    }

}
