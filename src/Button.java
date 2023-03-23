import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Color;

public class Button extends JButton {
    
    public Button(String text){
        super(text);
    }

    public Button bounds(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
        return this;
    }

    public Button actionListener(ActionListener al) {
        this.addActionListener(al);
        return this;
    }

    public Button bgColour(Color bg) {
        this.setBackground(bg);
        return this;
    }

    public Button fgColour(Color fg) {
        this.setForeground(fg);
        return this;
    }
}
