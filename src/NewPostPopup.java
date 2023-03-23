import javax.swing.JFrame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import static javax.swing.JOptionPane.showMessageDialog;


public class NewPostPopup implements Screen {

    private static final Integer WIDTH = 680;
    private static final Integer HEIGHT = 450;

    @Override
    public int getWidth() {
        return 680;
    }

    @Override
    public int getHeight() {
        return 450;
    }

    @Override
    public void addComponents(JFrame frame) {
        frame.add(content);
        frame.add(contentLabel);
        frame.add(titleField);
        frame.add(titleLabel);
        frame.add(postButton);
    }

    JTextArea content;
    Label contentLabel;
    JTextField titleField;
    Label titleLabel;
    Button postButton;

    public NewPostPopup() {
        Border blackBorder = BorderFactory.createLineBorder(Color.BLACK);
        this.titleField = new JTextField("");
        this.content = new JTextArea("");

        this.titleLabel = new Label("Title:").big()
        .bounds(25, 25, WIDTH-50, 25);

        this.contentLabel = new Label("Content:").big()
        .bounds(25, 125, WIDTH-50, 25);
        
        this.titleField.setBounds(25, 50, WIDTH-50, 25);
        this.content.setBounds(25,150,WIDTH-50,HEIGHT-250);
        
        this.titleField.setBorder(blackBorder);
        this.content.setBorder(blackBorder);
        
        this.postButton = new Button("Post")
        .bounds(WIDTH-200,HEIGHT-75,150,50)
        .actionListener(new ActionListener() 
            {
                public void actionPerformed(ActionEvent arg0) {
                    String titleString = titleField.getText();
                    String contentString = content.getText();
                    if(titleString.isEmpty() || contentString.isEmpty()) {
                        showMessageDialog(null,"Title or content is empty!");
                        return;
                    }
                    Main.allPosts.add(new Post(Main.currentUser.getId(), titleString, contentString));
                    if(Main.mainScreen instanceof HomePage) {
                        HomePage homePage = (HomePage)Main.mainScreen;
                        homePage.reloadPosts();
                    }
                    Main.hidePopup();
                } 
            }
        );
    }
}
