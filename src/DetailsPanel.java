import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class DetailsPanel extends JPanel {
    
    /** 
     * Reloads the details panel.
     * @param profile The User who's page is being viewed.
     */
    public void reload(User profile) {
        this.removeAll();

        String[] themeOptions = {"Dark","Light","Red","Blue","Irn Bru"};

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

        Label theme = new Label("Theme: ",SwingConstants.CENTER)
        .bright()
        .bounds(0, 300, 300,100);

        JComboBox<String> themeSelect = new JComboBox<String>(themeOptions);
        themeSelect.setBounds(50,375,200,25);
        themeSelect.setSelectedIndex(Theme.themeID);
        themeSelect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                @SuppressWarnings("unchecked")
                JComboBox<String> cb  = (JComboBox<String>) e.getSource();
                Object selected = cb.getSelectedItem();
                for(int i =  0; i< themeOptions.length; i++){
                    if(selected.equals(themeOptions[i])){
                        Theme.changeTheme(i);
                        Main.setMainScreen(new HomePage(profile));
                    }
                }
            }
        });
        Border border = BorderFactory.createLineBorder(Theme.SECONDARY_FG);
        themeSelect.setUI(new BasicComboBoxUI(){
            @Override
            public void configureArrowButton() {
                super.configureArrowButton();
                this.arrowButton.setBackground(Theme.SECONDARY_BG);
                this.arrowButton.setForeground(Theme.SECONDARY_FG);
                this.arrowButton.setBorder(border);
                this.listBox.setBackground(Theme.SECONDARY_BG);
                this.listBox.setForeground(Theme.SECONDARY_FG);
                this.comboBox.setBackground(Theme.SECONDARY_BG);
                this.comboBox.setForeground(Theme.SECONDARY_FG);
                this.comboBox.setBorder(border);
            }
        });

        this.add(fullName);
        this.add(theme);
        this.add(themeSelect);
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
