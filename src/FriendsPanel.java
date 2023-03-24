import java.util.Set;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import java.awt.Color;

public class FriendsPanel extends JPanel {

    private boolean isCurrentUser;

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

        Label yourFriendsLabel = new Label((isCurrentUser) ? "Your Friends: " : "Their Friends: ")
        .bright()
        .bounds(50, 100 , 200, 50);

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
                Label friendName = new Label(friend.getFullName())
                .bounds(50, (i*50)+100, 200, 25)
                .bright();

                this.add(friendName);
                this.add(viewProfile);
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
            Label reccomendedFriendsLabel = new Label("Recommended friends: ")
            .bounds(50, 400 , 300, 50)
            .bright();

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
                Label friendName = new Label(friend.getFullName())
                .bounds(50, (i*50)+400, 200, 25).bright();
                this.add(friendName);
                this.add(viewProfile);
                i++;
            }
            this.add(reccomendedFriendsLabel);
        }
        this.add(friendsListButton);
        this.revalidate();
        this.repaint();
    }

    FriendsPanel(boolean isCurrentUser, Set<Long> friendIDs){
        super(null);
        this.isCurrentUser=isCurrentUser;
        this.setBackground(Theme.SECONDARY_BG);
        reload(friendIDs);
    }
}
