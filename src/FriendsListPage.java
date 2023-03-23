import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

public class FriendsListPage implements Screen {
    public final Integer WIDTH = 400;
    public final Integer HEIGHT = 400;

    JLabel filterLabel;
    JLabel sortLabel;
    JLabel friendsLabel;
    JComboBox<String>  filterComboBox;
    JComboBox<String> sortComboBox;
    String[] filterOptions={"All","Home Town","Work Place","Mutual Friends"};
    String[] sortOptions = {"Ascending","Descending"};
    JScrollBar scrollBar;

    public void addComponents(JFrame frame){
        frame.add(filterLabel);
        frame.add(filterComboBox);
        frame.add(sortLabel);
        frame.add(sortComboBox);
        frame.add(scrollBar);
    }

    public void reloadFriendsList(int scrollValue, int selectedFiterID, int selectedSortID){

    }

    public void reload(int selectedFiterID, int selectedSortID){
        scrollBar = new JScrollBar(SwingConstants.VERTICAL,0,10,0,110);
        scrollBar.setBounds(360, 0, 20, 350);
        filterLabel = new JLabel("<html><span style='font-size:16px;'>Filter:</span></html>");
        filterLabel.setBounds(50,0,100,30);

        sortLabel = new JLabel("<html><span style='font-size:16px;'>Sort:</span></html>");
        sortLabel.setBounds(210,0,100,30);

        filterComboBox = new JComboBox<String>(filterOptions);
        filterComboBox.setSelectedIndex(selectedFiterID);
        filterComboBox.setBounds(25,40,150,25);
        filterComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
                JComboBox cb = (JComboBox) event.getSource();
                Object selected = cb.getSelectedItem();
                for(int i = 0; i < filterOptions.length; i++){
                    if(selected.equals(filterOptions[i])){
                        reload(selectedFiterID, selectedSortID);
                    }
                }
            }
        });

        sortComboBox = new JComboBox<String>(sortOptions);
        sortComboBox.setSelectedIndex(selectedSortID);
        sortComboBox.setBounds(185,40,150,25);
        sortComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
                JComboBox cb = (JComboBox) event.getSource();
                Object selected = cb.getSelectedItem();
                for(int i = 0; i < sortOptions.length;i++){
                    if(selected.equals(sortOptions[i])){
                        reload(selectedFiterID, selectedSortID);
                    }
                }
            }
        });

        scrollBar.addAdjustmentListener(new AdjustmentListener(){
            @Override
            public void adjustmentValueChanged(AdjustmentEvent arg0){
                JScrollBar scroll = (JScrollBar)arg0.getSource();
                reloadFriendsList(scroll.getValue(), selectedFiterID, selectedSortID);
            }
        });
    }

    public FriendsListPage(){
        reload(0,0);
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
