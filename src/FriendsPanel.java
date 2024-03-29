import java.util.Set;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class FriendsPanel extends JPanel {

    private boolean isCurrentUser;
    
    /** 
     * Reloads the friends panel.
     * @param friendIDs Set of the users friends.
     */
    void reload(Set<Long> friendIDs) {
        this.removeAll();

        Button searchButton = new Button("Search").
        bounds(50,20,200,50).
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
        .bounds(50, 60 , 200, 50);

        this.add(yourFriendsLabel);
        int i = 1;
        for (Long friendID : friendIDs) {
            if(i > 4){
                break;
            }
            User currentFriend = Main.users.searchTree(friendID);
            Button viewProfile = new Button("View Profile")
            .bounds(50, (i*50)+75, 200, 25)
            .actionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent arg0) {
                        Main.setMainScreen(new HomePage(Main.users.searchTree(friendID)));
                    }
                }
            );

            if(!currentFriend.equals(Main.currentUser) && !Main.currentUser.isFriendsWith(friendID)){
                viewProfile.setBounds(50,(i*50)+75,125,25);
                Button addFriendButton = new Button("Add")
                .bounds(175,(i*50)+75,75,25)
                .actionListener(new ActionListener() 
                    {
                        public void actionPerformed(ActionEvent arg0){
                            Main.currentUser.addFriend(friendID);
                            if(Main.mainScreen instanceof HomePage) {
                                ((HomePage)Main.mainScreen).reloadPosts();
                            }
                            reload(friendIDs);
                        }
                    }
                );
                this.add(addFriendButton);
            }

                User friend = Main.users.searchTree(friendID);
                Label friendName = new Label(friend.getFullName())
                .bounds(50, (i*50)+50, 200, 25)
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
            Label reccomendedFriendsLabel = new Label("Recommended Friends: ")
            .bounds(50, 400 , 300, 50)
            .bright();

            for (long friendID : Main.currentUser.recommendFriends()) {
                if(i > 5){
                    break;
                }
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
                            if(Main.mainScreen instanceof HomePage) {
                                ((HomePage)Main.mainScreen).reloadPosts();
                            }
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

    /**
     * Creates the friends panel
     * @param isCurrentUser does the page we are viewing belong to the current user?
     * @param friendIDs Set of the users friends.
     */
    FriendsPanel(boolean isCurrentUser, Set<Long> friendIDs){
        super(null);
        this.isCurrentUser=isCurrentUser;
        this.setBackground(Theme.SECONDARY_BG);
        reload(friendIDs);
    }
}
