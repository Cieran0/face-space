import java.util.List;

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
            User friend = Main.users.get(friendID);
            JLabel friendName = new JLabel(friend.getFullName());
            friendName.setBounds(50, i*50, 200, 50);
            this.add(friendName);
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
