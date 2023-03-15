import javax.swing.JFrame;

public interface Screen {
    public int getWidth();
    public int getHeight();
    public void addComponents(JFrame frame);
}
