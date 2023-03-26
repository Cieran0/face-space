import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.border.Border;
import java.awt.Color;

public class TextField extends JTextField {

    public static final Border LIGHT_BORDER = BorderFactory.createLineBorder(Theme.SECONDARY_FG);
    public static final Border DARK_BORDER = BorderFactory.createLineBorder(Theme.PRIMARY_FG);

    public TextField(String text) {
        super(text);
        this.setBorder(LIGHT_BORDER);
        bgColour(Theme.ACCENT_BG);
        fgColour(Theme.SECONDARY_FG);
        this.setCaretColor(Theme.SECONDARY_FG);
    }

    
    /** 
     * Wraps JTextField.setBorder to return self rather than void.
     * @param border New border.
     * @return self
     */
    public TextField border(Border border) {
        this.setBorder(border);
        return this;
    }

    
    /** 
     * Wraps JTextField.setBounds to return self rather than void.
     * @param x New x position.
     * @param y New y position.
     * @param width New width.
     * @param height New height.
     * @return self
     */
    public TextField bounds(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
        return this;
    }

    
    /** 
     * @param bg new background colour.
     * @return self
     */
    public TextField bgColour(Color bg) {
        this.setBackground(bg);
        return this;
    }

    
    /** 
     * @param fg new foreground colour.
     * @return self
     */
    public TextField fgColour(Color fg) {
        this.setForeground(fg);
        return this;
    }

}
