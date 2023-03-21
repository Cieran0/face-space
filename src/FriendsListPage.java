import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class FriendsListPage implements Screen {
    public final Integer WIDTH = 300;
    public final Integer HEIGHT = 310;

    JComboBox  filderComboBox;
    

    public void addComponents(JFrame frame){

    }

    public FriendsListPage(){

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
