import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

public class Button extends JButton {
    
    private static final Border NORMAL_BORDER = BorderFactory.createLineBorder(Theme.BUTTON_TEXT);
    private static final Border SELECTED_BORDER = BorderFactory.createLineBorder(Theme.BUTTON_SELECTED);
    private boolean zeroWidth = false;

    /**
     * Creates a invisible button.
     * @param zeroWidth
     */
    public Button(boolean zeroWidth) {
        this.zeroWidth = zeroWidth;
    }
    
    /** 
     * Allows for invisible buttons, needed for scrollbars without buttons. 
     * @return Dimension
     */
    @Override
    public Dimension getPreferredSize() {
        if(zeroWidth)
            return new Dimension();
        return super.getPreferredSize();
    }

    /**
     * Creates a button with.
     * @param text Text displayed on the button.
     */
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
    
    
    /** 
     * Wraps JButton.setBorder to return self rather than void.
     * @param border New border.
     * @return self
     */
    public Button border(Border border) {
        this.setBorder(border);
        return this;
    }

    
    /** 
     * Wraps JButton.setBounds to return self rather than void.
     * @param x New x position.
     * @param y New y position.
     * @param width New width.
     * @param height New height.
     * @return self
     */
    public Button bounds(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
        return this;
    }

    
    /**
     * Wraps JButton.setBounds to return self rather than void.
     * @param rect new bounds
     * @return self
     */
    public Button bounds(Rectangle rect) {
        this.setBounds(rect);
        return this;
    }

    
    /** 
     * Wraps JButton.addActionListener to return self rather than void.
     * @param actionListener ActionListener to add 
     * @return self
     */
    public Button actionListener(ActionListener actionListener) {
        this.addActionListener(actionListener);
        return this;
    }

    
    /** 
     * @param bg new background colour
     * @return self
     */
    public Button bgColour(Color bg) {
        this.setBackground(bg);
        return this;
    }

    
    /** 
     * @param fg new foreground colour
     * @return self
     */
    public Button fgColour(Color fg) {
        this.setForeground(fg);
        return this;
    }

    
    /** 
     * Override the super.paintComponent so we can have a custom gradient on our buttons 
     * @param g unsure.
     */
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

    
    /** 
     * @param height New height
     * @return self
     */
    public Button setHeight(int height) {
        this.setBounds(getX(), getY(), getWidth(), height);
        return this;
    }

    
    /** 
     * @param width New width
     * @return self
     */
    public Button setWidth(int width) {
        this.setBounds(getX(), getY(), width, getHeight());
        return this;
    }

    
    /** 
     * @param x New x position
     * @return self
     */
    public Button setX(int x) {
        this.setBounds(x, getY(), getWidth(), getHeight());
        return this;
    }

    
    /** 
     * @param y New y position
     * @return self
     */
    public Button setY(int y) {
        this.setBounds(getX(), y, getWidth(), getHeight());
        return this;
    }
}
