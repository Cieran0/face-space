import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class DetailsPanel extends JPanel {
    
    /** 
     * Reloads the details panel.
     * @param profile The User who's page is being viewed.
     */
    public void reload(User profile) {
        this.removeAll();

        Label fullName = new Label(profile.getFullName(),SwingConstants.CENTER)
        .big()
        .bright()
        .bounds(0, 0,300,50);

        Label username = new Label("@" + profile.getUsername(),SwingConstants.CENTER)
        .fontSize(14)
        .bright()
        .bounds(0, 20,300,50);

        Label workPlace = new Label("Workplace: " + profile.getWorkPlace())
        .bright()
        .bounds(0, 50,300,50);
        boolean showWorkPlace = !(profile.getWorkPlace().toLowerCase().equals("hidden"));

        Label HomeTown = new Label("Hometown: " + profile.getHomeTown())
        .bright()
        .bounds(0, 50 + ((showWorkPlace)? 25 : 0),300,50);
        boolean showHomeTown = !(profile.getHomeTown().toLowerCase().equals("hidden"));


        this.add(fullName);
        this.add(username);
        if(showWorkPlace)
            this.add(workPlace);
        if(showHomeTown)
            this.add(HomeTown);

        if(profile.equals(Main.currentUser)){
            Button detailsButton = new Button("Edit Details")
            .bounds(100,210,100,50)
            .actionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent arg0) {
                        Main.setPopupScreen(new EditDetailsPage());
                    }
                }
            );

            this.add(detailsButton);

            Button newPostButton = new Button("New Post")
            .bounds(50, Main.MAIN_WINDOW_HEIGHT-100, 100, 50)
            .actionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent arg0) {
                        Main.setPopupScreen(new NewPostPopup());
                    }
                }
            );

            this.add(newPostButton);
        }

        else if(!profile.equals(Main.currentUser)){
            Button toggleFriendButton = new Button(
                (Main.currentUser.isFriendsWith(profile)) ?
                "Remove Friend" : "Add Friend"
            )
            .bounds(100,210,100,50)
            .actionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent arg0){
                    if (Main.currentUser.isFriendsWith(profile)) 
                    {
                        Main.currentUser.removeFriend(profile.getId());
                    } else {
                        Main.currentUser.addFriend(profile.getId());
                    }
                    Main.setMainScreen(new HomePage(profile));
                }
            });
            
            Button homeButton = new Button("Home")
            .bounds(50, Main.MAIN_WINDOW_HEIGHT-100, 100, 50)
            .actionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent arg0) {
                        Main.setMainScreen(new HomePage(Main.currentUser));
                    }
                }
            );

            this.add(homeButton);
            this.add(toggleFriendButton);
        }

        Button logout = new Button("Logout")
        .bounds(150, Main.MAIN_WINDOW_HEIGHT-100, 100, 50)
        .actionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent arg0) {
                    Main.logout();
                }
            }
        );

        this.add(logout);
        this.revalidate();
        this.repaint();
    }

    /**
     * Creates the details panel.
     * @param profile The User who's page is being viewed.
     */
    public DetailsPanel(User profile) {
        super(null);
        this.setBackground(Theme.SECONDARY_BG);
        reload(profile);
    }

}
