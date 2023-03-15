import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AlertPopup implements Screen {

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void addComponents(JFrame frame) {
        frame.add(message);
        frame.add(okay);
    }

    private int width;
    private int height;
    private JLabel message;
    private JButton okay;

    public AlertPopup(int width, int height, String message) {
        this.width=width;
        this.height=height;
        this.message = new JLabel("<html><span style='font-size:10px;'>"+message+"</span></html>");
        this.message.setBounds(0, 0, width, height);
        this.message.setVerticalAlignment(SwingConstants.TOP);
        this.message.setHorizontalAlignment(SwingConstants.CENTER);
        this.okay = new JButton("Okay");
        this.okay.setBounds(width/4, height/2, width/2, height/4);
        okay.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                Main.hidePopup();
            }
        });
    }
    
}
