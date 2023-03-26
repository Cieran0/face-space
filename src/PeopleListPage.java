import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.Set;
import java.util.Stack;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class PeopleListPage implements Screen {
    public final Integer WIDTH = 400;
    public final Integer HEIGHT = 400;

    Label filterLabel;
    Label sortLabel;
    Label friendsLabel;
    JComboBox<String>  filterComboBox;
    JComboBox<String> sortComboBox;
    String[] filterOptions;
    String[] sortOptions = {"Ascending","Descending"};
    JScrollBar scrollBar;
    JPanel peoplePanel;
    Set<Long> people;
    boolean isCurrentUser;
    int selectedFiterID; 
    int selectedSortID;
    Border border = BorderFactory.createLineBorder(Theme.SECONDARY_FG);

    
    /** 
     * Adds the components of the page to the JFrame.
     * @param frame The JFrame the components are being added to.
     */
    public void addComponents(JFrame frame){
        frame.add(filterLabel);
        frame.add(filterComboBox);
        frame.add(sortLabel);
        frame.add(sortComboBox);
        frame.add(peoplePanel);
    }

    
    /** 
     * Reloads the page, scrolling the object based on the scroll wheel.
     * @param scrollValue The position of the scrolllwheel (0-100)
     */
    public void reloadPeopleList(int scrollValue){
        peoplePanel.removeAll();
        peoplePanel.add(scrollBar);
        scrollBar.setValue(scrollValue);
        final Integer PERSON_HEIGHT = 75;

        Stack<User> filteredPeople = new Stack<User>();
        for (Long personId : this.people) {
            User person = Main.users.searchTree(personId);
            if(selectedFiterID == 0) {
                filteredPeople.add(person);
                continue;
            } else if (selectedFiterID==1 && !person.hasSameHomeTownAs(Main.currentUser)) {
                continue;
            } else if (selectedFiterID==2 && !person.hasSameWorkPlaceAs(Main.currentUser)) {
                continue;
            } else if (selectedFiterID==3 && !person.isFriendsWith(Main.currentUser)) {
                continue;
            }
            filteredPeople.add(person);
        }

        int peopleCount = filteredPeople.size();
        int totalHeight = peopleCount*PERSON_HEIGHT + 50;
        int scrollInterval = (totalHeight - (peoplePanel.getBounds().height));
        int yOffset = (scrollInterval > 0)? (scrollInterval*scrollValue)/100 : 0;

        User[] sortedPeople = filteredPeople.toArray(new User[peopleCount]);
        if(selectedSortID == 0) {
            User.sortAscending(sortedPeople);
        } else {
            User.sortDescending(sortedPeople);
        }

        for (int i = 0; i < sortedPeople.length; i++) {
            User person = sortedPeople[i];
            Label name = new Label(person.getFullName())
            .bounds(25, 10 + i*PERSON_HEIGHT - yOffset, WIDTH-50, 20)
            .bright();

            Button viewProfileButton = new Button("View Profile")
            .bounds(25, 30 + i*PERSON_HEIGHT - yOffset, WIDTH-50, 30)
            .actionListener(new ActionListener() 
                {
                    public void actionPerformed(ActionEvent arg0) {
                        Main.hidePopup();
                        Main.setMainScreen(new HomePage(person));
                    }
                }
            );
            
            if(!(Main.currentUser.isFriendsWith(person.getId()) || person.isCurrentUser())) {
                viewProfileButton.setBounds(25, 30 + i*PERSON_HEIGHT - yOffset, (3*(WIDTH-50))/4, 30);
                Button addButton = new Button("Add")
                .bounds(25 + (3*(WIDTH-50))/4, 30 + i*PERSON_HEIGHT - yOffset, (WIDTH-50)/4, 30)
                .actionListener(new ActionListener() 
                    {
                        public void actionPerformed(ActionEvent arg0) {
                            Main.currentUser.addFriend(person.getId());
                            reloadPeopleList(scrollValue);
                            if(Main.mainScreen instanceof HomePage) {
                                ((HomePage)Main.mainScreen).reloadFriends();
                            }
                        }
                    }
                );
                peoplePanel.add(addButton);
            }
            peoplePanel.add(viewProfileButton);
            peoplePanel.add(name);
        }
        peoplePanel.revalidate();
        peoplePanel.repaint();
    }

    /**
     * Reloads the page.
     */
    public void reload(){
        peoplePanel = new JPanel(null);
        peoplePanel.setBounds(0, 75, WIDTH, HEIGHT-75);
        peoplePanel.setBackground(Theme.SECONDARY_BG);

        peoplePanel.addMouseWheelListener(new MouseWheelListener(){
            @Override
            public void mouseWheelMoved(MouseWheelEvent arg0) {
                scrollBar.setValue(scrollBar.getValue() + arg0.getUnitsToScroll()/3);
            }
        });

        scrollBar = new JScrollBar(SwingConstants.VERTICAL,0,10,0,110);
        scrollBar.setBounds(WIDTH-20, 0, 20, HEIGHT-75);
        scrollBar.setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbDarkShadowColor = Theme.ACCENT_BG.darker();
                this.thumbHighlightColor = Theme.ACCENT_BG.brighter();
                this.trackColor = Theme.SECONDARY_BG.brighter();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return new Button(true);
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                return new Button(true);
            }
        });

        reloadPeopleList(0);

        filterLabel = new Label("Filter:",SwingConstants.CENTER).big()
        .bounds(25,10,150,30);

        sortLabel = new Label("Sort:",SwingConstants.CENTER).big()
        .bounds(WIDTH-175,10,150,30);

        filterComboBox = new JComboBox<String>(filterOptions);
        filterComboBox.setSelectedIndex(selectedFiterID);
        filterComboBox.setBounds(25,40,150,25);
        filterComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
                @SuppressWarnings("unchecked")
                JComboBox<String> cb = (JComboBox<String>) event.getSource();
                Object selected = cb.getSelectedItem();
                for(int i = 0; i < filterOptions.length; i++){
                    if(selected.equals(filterOptions[i])){
                        selectedFiterID=i;
                        reloadPeopleList(0);
                    }
                }
            }
        });
        filterComboBox.setUI(new BasicComboBoxUI(){
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
        
        sortComboBox = new JComboBox<String>(sortOptions);
        sortComboBox.setSelectedIndex(selectedSortID);
        sortComboBox.setBounds(WIDTH-175,40,150,25);
        sortComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
                @SuppressWarnings("unchecked")
                JComboBox<String> cb = (JComboBox<String>) event.getSource();
                Object selected = cb.getSelectedItem();
                for(int i = 0; i < sortOptions.length;i++){
                    if(selected.equals(sortOptions[i])){
                        selectedSortID=i;
                        reloadPeopleList(0);
                    }
                }
            }
        });
        sortComboBox.setUI(new BasicComboBoxUI(){
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

        scrollBar.addAdjustmentListener(new AdjustmentListener(){
            @Override
            public void adjustmentValueChanged(AdjustmentEvent arg0){
                JScrollBar scroll = (JScrollBar)arg0.getSource();
                reloadPeopleList(scroll.getValue());
            }
        });
    }

    /**
     * Creates a page of people from a list of people.
     */
    public PeopleListPage(boolean isCurrentUser, Set<Long> people){
        this.people = people;
        this.isCurrentUser = isCurrentUser;
        this.filterOptions = isCurrentUser? 
        new String[] {
            "All","Home Town","Work Place"
        }: 
        new String[]{
            "All","Home Town","Work Place","Mutual Friends"
        };
        selectedFiterID = 0;
        selectedSortID = 0;
        reload();
    }

    
    /** 
     * @return The Page's Width.
     */
    @Override
    public int getWidth() {
        return WIDTH;
    }

    
    /** 
     * @return The Page's Height.
     */
    @Override
    public int getHeight() {
        return HEIGHT;
    }
}
