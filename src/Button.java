import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class Button extends JButton {
    
    private static final Border NORMAL_BORDER = BorderFactory.createLineBorder(Theme.BUTTON_TEXT);
    private static final Border SELECTED_BORDER = BorderFactory.createLineBorder(Theme.BUTTON_SELECTED);

    public Button(String text){
        super(text);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setRolloverEnabled(false);
        border(NORMAL_BORDER);
        addMouseListener(new MouseListener()
            {
                public void mouseClicked(MouseEvent arg0) {}
                public void mousePressed(MouseEvent arg0) {}
                public void mouseReleased(MouseEvent arg0) {}
                public void mouseEntered(MouseEvent arg0) {
                    border(SELECTED_BORDER);
                    fgColour(Theme.BUTTON_SELECTED);
                }
                public void mouseExited(MouseEvent arg0) {
                    border(NORMAL_BORDER);
                    fgColour(Theme.BUTTON_TEXT);
                }
            }
        );
        fgColour(Theme.BUTTON_TEXT);
    }
    
    public Button border(Border border) {
        this.setBorder(border);
        return this;
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
