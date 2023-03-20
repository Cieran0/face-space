import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class SearchPage implements Screen{
    public final Integer WIDTH = 400;
    public final Integer HEIGHT = 300;

    JLabel searchLabel;
    JTextField searchField;
    JButton searchButton;

    public void addComponents(JFrame frame){
        frame.setLocationRelativeTo(null);
        frame.add(searchLabel);
        frame.add(searchField);
    }

    public SearchPage(){
        searchButton = new JButton("Search");

        searchLabel = new JLabel("<html><span style='font-size:32px;'>Search</span></html>");
        searchLabel.setBounds(50,0,200,50);

        searchField = new JTextField();
        searchField.setBounds(50,50,200,50);
    }

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }
}
