import java.util.List;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;

public class FriendsPanel extends JPanel {

    private boolean isCurrentUser;
    public Color foregroundColour;
    public Color backgroundColour;

    void reload(List<Integer> friendIDs) {
        this.removeAll();
        JLabel yourFriendsLabel = new JLabel((isCurrentUser)? "Your Friends: " : "Their Friends: ");
        yourFriendsLabel.setBounds(50, 0, 200, 50);
        yourFriendsLabel.setForeground(foregroundColour);
        this.add(yourFriendsLabel);
        int i = 1;
        for (Integer friendID : friendIDs) {
            JButton viewProfile = new JButton("View Profile");
            viewProfile.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    Main.setMainScreen(new HomePage(Main.users.get(friendID)));
                }
            }
            );

            User friend = Main.users.get(friendID);
            JLabel friendName = new JLabel(friend.getFullName());
            friendName.setBounds(50, i*50, 200, 25);
            viewProfile.setBounds(50, (i*50)+25, 200, 25);
            this.add(friendName);
            this.add(viewProfile);
            friendName.setForeground(foregroundColour);
            i++;
        }
        this.revalidate();
        this.repaint();
    }

    FriendsPanel(boolean isCurrentUser, List<Integer> friendIDs, Color foregroundColour, Color backgroundColour){
        super(null);
        this.isCurrentUser=isCurrentUser;
        this.foregroundColour=foregroundColour;
        this.backgroundColour=backgroundColour;
        this.setBackground(backgroundColour);
        reload(friendIDs);
    }
}
