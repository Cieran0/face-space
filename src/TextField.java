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

    public TextField border(Border border) {
        this.setBorder(border);
        return this;
    }

    public TextField bounds(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
        return this;
    }

    public TextField bgColour(Color bg) {
        this.setBackground(bg);
        return this;
    }

    public TextField fgColour(Color fg) {
        this.setForeground(fg);
        return this;
    }

}
