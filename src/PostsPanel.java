import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.Stack;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class PostsPanel extends JPanel {

    public JPanel posts;
    public JScrollBar scrollBar;
    private static Border border = BorderFactory.createLineBorder(Theme.SECONDARY_FG);

    public void reloadPosts(User profile, int scrollValue, int selectedId) {
        posts.removeAll();
        posts.add(scrollBar);
        final Integer POST_HEIGHT = 350;
        Stack<Post> filteredPosts = new Stack<Post>();
        int trueSelectedId = (profile.isCurrentUser()? selectedId : selectedId+1);
        for (Post post : Main.allPosts) {
            boolean shouldBreak = false;
            switch(trueSelectedId) {
                case 0:
                    if(post.getLikedBy().contains(Main.currentUser.getId())) {
                        filteredPosts.add(post);
                        shouldBreak=true;
                    }
                    if(trueSelectedId != 0 || shouldBreak) 
                        break;
                case 1:
                    if(post.getPosterId() == profile.getId()) {
                        filteredPosts.add(post);
                        shouldBreak=true;
                    }
                    if(trueSelectedId != 0 || shouldBreak) 
                        break;
                case 2:
                    for (Long friendId : profile.getFriends()) {
                        if(post.getPosterId() == friendId) {
                            filteredPosts.add(post);
                            shouldBreak = true;
                        } 
                    }

                    if(trueSelectedId != 0 || shouldBreak) 
                        break;
                case 3:
                    if(post.mentions(profile)) {
                        filteredPosts.add(post);
                        shouldBreak=true;
                    }
                    if(trueSelectedId != 0 || shouldBreak) 
                        break; 
                case 4:
                    for (Long friendId : profile.getFriends()) {
                        if(post.mentions(Main.users.searchTree(friendId))) {
                            filteredPosts.add(post);
                            shouldBreak=true;
                        } 
                    }
                    if(trueSelectedId != 0 || shouldBreak) 
                        break;
                default:
                    break;
            }
        }
        int postsCount = filteredPosts.size();
        int totalHeight = postsCount*POST_HEIGHT + 50;
        int scrollInterval = (totalHeight - (Main.MAIN_WINDOW_HEIGHT));
        int yOffset = (scrollInterval > 0)? (scrollInterval*scrollValue)/100 : 0;

        int i = 0;
        
        while(!filteredPosts.isEmpty()) {
            Post post = filteredPosts.pop();
            JTextArea content = new JTextArea(post.getContent());

            Label title = new Label(post.getTitle(),SwingConstants.CENTER)
            .big()
            .bgColour(Theme.ACCENT_BG)
            .bright()
            .bounds(25, 10 + i*POST_HEIGHT - yOffset, 630, 30)
            .border(border);

            Label postedBy = new Label("Posted by: " + Main.users.searchTree(post.getPosterId()).getFullName())
            .bgColour(Theme.ACCENT_BG)
            .bright()
            .bounds(25, 310 + i*POST_HEIGHT - yOffset, 310, 30)
            .border(border);

            Label likes = new Label("Likes: " + post.getLikeCount())
            .bgColour(Theme.ACCENT_BG)
            .bright()
            .bounds(345, 310 + i*POST_HEIGHT - yOffset, 155, 30)
            .border(border);
            
            content.setBounds(25, 50 + i*POST_HEIGHT - yOffset, 630, 250);
            content.setBorder(border);
            content.setEditable(false);
            content.setBackground(Theme.ACCENT_BG);
            content.setForeground(Theme.SECONDARY_FG);
            content.setFont(content.getFont().deriveFont(16f));
            content.setLineWrap(true);

            Button likeButton = new Button((post.isLikedBy(Main.currentUser.getId()))? "Unlike" : "Like")
            .bounds(505, 310 + i*POST_HEIGHT - yOffset, 150, 30)
            .actionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent arg0){
                        post.toggleLike(Main.currentUser.getId());
                        reloadPosts(profile,scrollValue,selectedId);
                    }
                }
            );

            if(post.getLikeCount() > 0) {
                likeButton.setWidth(75);
                Button showLikes = new Button("Show")
                .bounds(likeButton.getBounds())
                .setX(580)
                .actionListener(new ActionListener() 
                {
                    public void actionPerformed(ActionEvent arg0){
                        Main.setPopupScreen(new PeopleListPage(true, post.getLikedBy()));
                    }
                }
                );
                posts.add(showLikes);
            }

            posts.add(title);
            posts.add(content);
            posts.add(postedBy);
            posts.add(likes);
            posts.add(likeButton);
            i++;
        }
        posts.revalidate();
        posts.repaint();
    }

    public void reload(User profile, int selectedId) {
        this.removeAll();
        scrollBar = new JScrollBar(SwingConstants.VERTICAL,0,10,0,110);
        posts = new JPanel(null);
        posts.setBounds(0,50,680,Main.MAIN_WINDOW_HEIGHT-50);
        scrollBar.setBounds(660, 0, 20, Main.MAIN_WINDOW_HEIGHT-50);
        scrollBar.setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbDarkShadowColor = Theme.ACCENT_BG.darker();
                this.thumbHighlightColor = Theme.ACCENT_BG.brighter();
                this.trackColor = Theme.PRIMARY_BG.darker();
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
        posts.add(scrollBar);
        posts.setBackground(Theme.PRIMARY_BG);

        String[] choices = profile.isCurrentUser()? 
            new String[]{
                "Feed","Your posts", "Your friends' posts", "Mentions you", "Mentions your friend" 
            } : 
            new String[]{
                "Their posts", "Their friends' posts", "Mentions them", "Mentions their friend"
            }
        ;
        JComboBox<String> postsFilter = new JComboBox<String>(choices);
        postsFilter.setSelectedIndex(selectedId);
        postsFilter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                @SuppressWarnings("unchecked")
                JComboBox<String> comboBox = (JComboBox<String>) event.getSource();
                Object selected = comboBox.getSelectedItem();
                for (int i = 0; i < choices.length; i++) {
                    if(selected.equals(choices[i]))
                    {
                        reload(profile, i);
                    }
                }
            }
        });
        postsFilter.setBounds(40, 15, 600, 25);
        postsFilter.setUI(new BasicComboBoxUI(){
            @Override
            public void configureArrowButton() {
                super.configureArrowButton();
                this.arrowButton.setBackground(Theme.SECONDARY_BG);
                this.arrowButton.setBorder(border);
                this.arrowButton.setForeground(Theme.SECONDARY_FG);
                this.listBox.setBackground(Theme.SECONDARY_BG);
                this.listBox.setForeground(Theme.SECONDARY_FG);
                this.comboBox.setBackground(Theme.SECONDARY_BG);
                this.comboBox.setForeground(Theme.SECONDARY_FG);
                this.comboBox.setBorder(border);
            }
        });

        scrollBar.addAdjustmentListener(new AdjustmentListener(){

            @Override
            public void adjustmentValueChanged(AdjustmentEvent arg0) {
                JScrollBar scroll = (JScrollBar)arg0.getSource();
                reloadPosts(profile,scroll.getValue(), selectedId);
            }

        });

        this.addMouseWheelListener(new MouseWheelListener(){

            @Override
            public void mouseWheelMoved(MouseWheelEvent arg0) {
                scrollBar.setValue(scrollBar.getValue() + arg0.getUnitsToScroll()/3);
            }
        });

        this.add(postsFilter);
        this.add(posts);
        this.reloadPosts(profile, 0, selectedId);
        this.revalidate();
        this.repaint();
    }

    public PostsPanel(User profile) {
        super(null);
        this.setBackground(Theme.ACCENT_BG);
        reload(profile,0);
    }

}
