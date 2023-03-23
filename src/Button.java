import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class Button extends JButton {
    
    public Button(String text){
        super(text);
        setContentAreaFilled(false);
        setFocusPainted(false); // used for demonstration
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

    @Override
    protected void paintComponent(Graphics g) {
        final Graphics2D g2 = (Graphics2D) g.create();
        g2.setPaint(new GradientPaint(
            new Point(0, 0), 
            Theme.BUTTON_BG_1, 
            new Point(0, getHeight()), 
            Theme.BUTTON_BG_2,false));
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.dispose();
        super.paintComponent(g);
    }
}
