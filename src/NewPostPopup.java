import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class NewPostPopup implements Screen {

    private static final Integer WIDTH = 680;
    private static final Integer HEIGHT = 450;

    
    /** 
     * @return The Page's Width.
     */
    @Override
    public int getWidth() {
        return 680;
    }

    
    /** 
     * @return The Page's Height.
     */
    @Override
    public int getHeight() {
        return 450;
    }

    
    /** 
     * Adds the components of the page to the JFrame.
     * @param frame The JFrame the components are being added to.
     */
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
    TextField titleField;
    Label titleLabel;
    Button postButton;

    public NewPostPopup() {
        Border border = BorderFactory.createLineBorder(Theme.SECONDARY_FG);

        this.titleField = new TextField("")
        .bounds(25, 50, WIDTH-50, 25);

        this.titleLabel = new Label("Title:").big()
        .bounds(25, 25, WIDTH-50, 25);

        this.contentLabel = new Label("Content:").big()
        .bounds(25, 125, WIDTH-50, 25);
        
        this.content = new JTextArea("");
        this.content.setBounds(25,150,WIDTH-50,HEIGHT-250);
        this.content.setBackground(Theme.ACCENT_BG);
        this.content.setForeground(Theme.SECONDARY_FG);
        this.content.setFont(content.getFont().deriveFont(16f));
        this.content.setCaretColor(Theme.SECONDARY_FG);
        this.content.setBorder(border);
        this.content.setLineWrap(true);
        
        this.postButton = new Button("Post")
        .bounds(WIDTH-200,HEIGHT-75,150,50)
        .actionListener(new ActionListener() 
            {
                public void actionPerformed(ActionEvent arg0) {
                    String titleString = titleField.getText();
                    String contentString = content.getText();
                    if(titleString.isEmpty() || contentString.isEmpty()) {
                        Main.showErrorMessage("Title or content is empty!");
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
