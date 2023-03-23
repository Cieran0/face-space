import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DetailsPanel extends JPanel {

    public Color foregroundColour;
    public Color backgroundColour;
    
    public void reload(User profile) {
        this.removeAll();

        JLabel[] details = new JLabel[4];
        details[0] = new JLabel(profile.getFullName());
        details[1] = new JLabel("@" + profile.getUsername());
        details[2] = new JLabel("Workplace: " + profile.getWorkPlace());
        details[3] = new JLabel("Hometown: " + profile.getHomeTown());

        for (int i = 0; i < details.length; i++) {
            details[i].setForeground(foregroundColour);
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

    public DetailsPanel(User profile, Color foregroundColour, Color backgroundColour) {
        super(null);
        this.foregroundColour=foregroundColour;
        this.backgroundColour=backgroundColour;
        this.setBackground(backgroundColour);
        reload(profile);
    }

}
