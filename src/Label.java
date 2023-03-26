import javax.swing.JLabel;
import javax.swing.border.Border;

import java.awt.Color;

public class Label extends JLabel {
    public Label(String text) {
        super(text);
        this.setForeground(Theme.PRIMARY_FG);
    }

    public Label(String text, int horizontalAlignment) {
        super(text,horizontalAlignment);
        this.setForeground(Theme.PRIMARY_FG);
    }

    
    /** 
     * Makes the label a brighter colour.
     * @return self
     */
    public Label bright() {
        this.setForeground(Theme.SECONDARY_FG);
        return this;
    }

    
    /** 
     * Makes the label's font size 16px.
     * @return self
     */
    public Label big() {
        return fontSize(16);
    }

    
    /** 
     * Makes the label's font size 32px.
     * @return self
     */
    public Label bigger() {
        return fontSize(32);
    }

    
    /** 
     * Makes the label's font size 75px.
     * @return self
     */
    public Label biggest() {
        return fontSize(75);
    }

    
    /** 
     * @param fontSize
     * @return self
     */
    public Label fontSize(int fontSize) {
        setText("<html><span style='font-size:"+fontSize+"px;'>"+getText()+"</span></html>");
        return this;
    }

    
    /** 
     * Wraps JLabel.setBounds to return self rather than void.
     * @param x the new x position.
     * @param y the new y position.
     * @param width the new width.
     * @param height the new height.
     * @return self
     */
    public Label bounds(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
        return this;
    }

    
    /** 
     * Sets the background colour of the label.
     * @param bg background colour
     * @return self
     */
    public Label bgColour(Color bg) {
        this.setOpaque(true);
        this.setBackground(bg);
        return this;
    }

    
    /** 
     * Sets the foreground colour of the label.
     * @param fg foreground colour
     * @return self
     */
    public Label fgColour(Color fg) {
        this.setForeground(fg);
        return this;
    }

    
    /** Wraps JLabel.setBorder to return self rather than void.
     * @param border the new border.
     * @return self
     */
    public Label border(Border border) {
        this.setBorder(border);
        return this;
    }

}
