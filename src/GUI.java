import javax.swing.*;
import java.util.ArrayList;

public class GUI {
    public static JFrame frame;
    public static JPanel playPanel;
    public static ArrayList<HandView> handViewLists = new ArrayList<HandView>();
    public static int position = 0;
    public static void main(String[] args) {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 650);

        playPanel = new JPanel();
        frame.add(playPanel);

        for (int i = 0; i < 3; i ++) {
            HandView handView = new HandView();
//            position += 100;
//            handView.cardImage.setBounds(position, 10, 100, 50);
            playPanel.add(handView.cardImage);

        }

        frame.setVisible(true);
    }
}
