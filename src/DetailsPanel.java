import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class DetailsPanel extends JPanel {

    public Color foregroundColour;
    public Color backgroundColour;
    
    public void reload(User profile) {
        this.removeAll();

        JLabel[] details = new JLabel[4];
        details[0] = new JLabel(profile.getFullName());
        details[1] = new JLabel("Username: " + profile.getUsername());
        details[2] = new JLabel("Workplace: " + profile.getWorkPlace());
        details[3] = new JLabel("Hometown: " + profile.getHomeTown());

        for (int i = 0; i < details.length; i++) {
            details[i].setForeground(foregroundColour);
            details[i].setBounds(0, i*50, 200, 20);
            this.add(details[i]);
        }
        this.revalidate();
        this.repaint();
    }

    public DetailsPanel(User profile, Color foregroundColour, Color backgroundColour) {
        super(null);
        this.foregroundColour=foregroundColour;
        this.backgroundColour=backgroundColour;
        this.setBackground(backgroundColour);
        reload(profile);
    }

}
