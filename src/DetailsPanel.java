import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class DetailsPanel extends JPanel {
    
    public void reload(User profile) {
        this.removeAll();

        Label[] details = new Label[4];
        details[0] = new Label(profile.getFullName());
        details[1] = new Label("@" + profile.getUsername());
        details[2] = new Label("Workplace: " + profile.getWorkPlace());
        details[3] = new Label("Hometown: " + profile.getHomeTown());

        for (int i = 0; i < details.length; i++) {
            details[i].bright();
            details[i].setBounds(0, i*50, 200, 20);
            this.add(details[i]);
        }
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

        else if(!profile.equals(Main.currentUser) && !Main.currentUser.isFriendsWith(profile.getId())){
            Button addFriendButton = new Button("Add Friend")
            .bounds(100,210,100,50)
            .actionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent arg0){
                    Main.currentUser.addFriend(profile.getId());
                    Main.setMainScreen(new HomePage(profile));
                }
            });
            
            this.add(addFriendButton);
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

    public DetailsPanel(User profile) {
        super(null);
        this.setBackground(Theme.SECONDARY_BG);
        reload(profile);
    }

}
