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

    public Label bright() {
        this.setForeground(Theme.SECONDARY_FG);
        return this;
    }

    public Label big() {
        return fontSize(16);
    }

    public Label bigger() {
        return fontSize(32);
    }

    public Label biggest() {
        return fontSize(75);
    }

    public Label fontSize(int fontSize) {
        setText("<html><span style='font-size:"+fontSize+"px;'>"+getText()+"</span></html>");
        return this;
    }

    public Label bounds(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
        return this;
    }

    public Label bgColour(Color bg) {
        this.setOpaque(true);
        this.setBackground(bg);
        return this;
    }

    public Label fgColour(Color fg) {
        this.setForeground(fg);
        return this;
    }

    public Label border(Border border) {
        this.setBorder(border);
        return this;
    }

}
