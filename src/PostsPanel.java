import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class PostsPanel extends JPanel {

    public Color foregroundColour;
    public Color backgroundColour;
    public JPanel posts;
    public JScrollBar scrollBar;

    public void reloadPosts(User profile, int scrollValue, int selectedId) {
        posts.removeAll();
        posts.add(scrollBar);
        final Integer POST_HEIGHT = 350;
        Stack<Post> filteredPosts = new Stack<Post>();
        if(!profile.isCurrentUser()) {
            selectedId++;
        }
        for (Post post : Main.allPosts) {
            if(selectedId == 1 || selectedId == 0) {
                if(post.getPosterId() == profile.getId()) {
                    filteredPosts.add(post);
                } 
            } else if(selectedId == 2 || selectedId == 0) {
                for (Long friendId : profile.getFriends()) {
                    if(post.getPosterId() == friendId) {
                        filteredPosts.add(post);
                    } 
                }
            } else if (selectedId == 3 || selectedId == 0) {
                if(post.mentions(profile)) {
                    filteredPosts.add(post);
                } 
            } else if (selectedId == 4 || selectedId == 0) {
                for (Long friendId : profile.getFriends()) {
                    if(post.mentions(Main.users.searchTree(friendId))) {
                        filteredPosts.add(post);
                    } 
                }
            }
        }
        int postsCount = filteredPosts.size();
        int totalHeight = postsCount*POST_HEIGHT + 50;
        int scrollInterval = (totalHeight - (Main.MAIN_WINDOW_HEIGHT));
        int yOffset = (scrollInterval > 0)? (scrollInterval*scrollValue)/100 : 0;

        int i = 0;
        Border blackBorder = BorderFactory.createLineBorder(Color.BLACK);
        while(!filteredPosts.isEmpty()) {
            Post post = filteredPosts.pop();
            JLabel title = new JLabel("<html><span style='font-size:16px;'>"+post.getTitle()+"</span></html>",SwingConstants.CENTER);
            JTextArea content = new JTextArea(post.getContent());
            JLabel postedBy = new JLabel("Posted by: " + Main.users.searchTree(post.getPosterId()).getFullName());
            title.setBounds(25, 10 + i*POST_HEIGHT - yOffset, 630, 30);
            title.setBorder(blackBorder);
            content.setBounds(25, 50 + i*POST_HEIGHT - yOffset, 630, 250);
            content.setBorder(blackBorder);
            content.setEditable(false);
            postedBy.setBounds(25, 310 + i*POST_HEIGHT - yOffset, 630, 30);
            postedBy.setBorder(blackBorder);
            posts.add(title);
            posts.add(content);
            posts.add(postedBy);
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
        posts.add(scrollBar);

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
                JComboBox comboBox = (JComboBox) event.getSource();
                Object selected = comboBox.getSelectedItem();
                for (int i = 0; i < choices.length; i++) {
                    if(selected.equals(choices[i]))
                    {
                        reload(profile, i);
                    }
                }
            }
        });

        postsFilter.setBounds(40, 20, 600, 25);

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

    public PostsPanel(User profile, Color foregroundColour, Color backgroundColour) {
        super(null);
        this.foregroundColour=foregroundColour;
        this.backgroundColour=backgroundColour;
        this.setBackground(backgroundColour);
        reload(profile,0);
    }

}
