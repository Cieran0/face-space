import java.util.Set;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;

public class FriendsPanel extends JPanel {

    private boolean isCurrentUser;
    public Color foregroundColour;
    public Color backgroundColour;

    void reload(Set<Long> friendIDs) {
        this.removeAll();

        Button searchButton = new Button("Search").
        bounds(50,20,100,50).
        actionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent arg0) {
                    Main.setPopupScreen(new SearchPage());
                }
            }
        );

        this.add(searchButton);

        JLabel yourFriendsLabel = new JLabel((isCurrentUser) ? "Your Friends: " : "Their Friends: ");
        yourFriendsLabel.setBounds(50, 100 , 200, 50);
        yourFriendsLabel.setForeground(foregroundColour);
        this.add(yourFriendsLabel);
        int i = 1;
        for (Long friendID : friendIDs) {
            if(i > 3){
                break;
            }
            User currentFriend = Main.users.searchTree(friendID);
            Button viewProfile = new Button("View Profile")
            .bounds(50, (i*50)+125, 200, 25)
            .actionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent arg0) {
                        Main.setMainScreen(new HomePage(Main.users.searchTree(friendID)));
                    }
                }
            );

            if(!currentFriend.equals(Main.currentUser) && !Main.currentUser.isFriendsWith(friendID)){
                viewProfile.setBounds(50,(i*50)+125,125,25);
                Button addFriendButton = new Button("Add")
                .bounds(175,(i*50)+125,75,25)
                .actionListener(new ActionListener() 
                    {
                        public void actionPerformed(ActionEvent arg0){
                            Main.currentUser.addFriend(friendID);
                            reload(friendIDs);
                        }
                    }
                );
                this.add(addFriendButton);
            }

                User friend = Main.users.searchTree(friendID);
                JLabel friendName = new JLabel(friend.getFullName());
                friendName.setBounds(50, (i*50)+100, 200, 25);
                this.add(friendName);
                this.add(viewProfile);
                friendName.setForeground(foregroundColour);
                i++;
        }
        
        Button friendsListButton = new Button("All Friends")
        .bounds(50,325,200,50)
        .actionListener(new ActionListener() 
            {
                public void actionPerformed(ActionEvent arg0){
                    Main.setPopupScreen(new PeopleListPage(isCurrentUser,friendIDs));
                }
            }
        );
        
        if (isCurrentUser){
            i=1;
            JLabel reccomendedFriendsLabel = new JLabel("Recommended friends: ");
            reccomendedFriendsLabel.setBounds(50, 400 , 300, 50);
            reccomendedFriendsLabel.setForeground(foregroundColour);
            for (long friendID : Main.currentUser.recommendFriends()) {
                User currentFriend = Main.users.searchTree(friendID);
                Button viewProfile = new Button("View Profile")
                .actionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent arg0) {
                        Main.setMainScreen(new HomePage(currentFriend));
                    }
                }
                );

                viewProfile.setBounds(50,(i*50)+425,125,25);
                Button addFriendButton = new Button("Add")
                .bounds(175,(i*50)+425,75,25)
                .actionListener(new ActionListener() 
                    {
                        public void actionPerformed(ActionEvent arg0){
                            Main.currentUser.addFriend(friendID);
                            reload(friendIDs);
                        }
                    }
                );
                this.add(addFriendButton);

                User friend = Main.users.searchTree(friendID);
                JLabel friendName = new JLabel(friend.getFullName());
                friendName.setBounds(50, (i*50)+400, 200, 25);
                this.add(friendName);
                this.add(viewProfile);
                friendName.setForeground(foregroundColour);
                i++;
            }
            this.add(reccomendedFriendsLabel);
        }
        this.add(friendsListButton);
        this.revalidate();
        this.repaint();
    }

    FriendsPanel(boolean isCurrentUser, Set<Long> friendIDs, Color foregroundColour, Color backgroundColour){
        super(null);
        this.isCurrentUser=isCurrentUser;
        this.foregroundColour=foregroundColour;
        this.backgroundColour=backgroundColour;
        this.setBackground(backgroundColour);
        reload(friendIDs);
    }
}
