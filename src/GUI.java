import javax.swing.*;
import java.util.ArrayList;

public class GUI {
    public static JFrame frame;
    public static JPanel playPanel;

    // usersHandsList contains every single hand and the object associated with their cards
    public static ArrayList<HandView> usersHandsList = new ArrayList<HandView>();
    // handViewList contains the number of cards for this one user
    public static ArrayList<HandView> handViewList = new ArrayList<HandView>();
    public static void main(String[] args) {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 650);

        playPanel = new JPanel();
        playPanel.setLayout(null);
        frame.add(playPanel);

        // create 3 labels next to each other
        int positionX = 0;
        int positiony = 0;
        for (int i = 0; i < 3; i ++) {
            HandView handView = new HandView(positionX, positiony, "Hi");
            handViewList.add(handView);
            playPanel.add(handView.imageLabel);
            positionX += 20;
            positiony += 20;
        }

        frame.setVisible(true);
    }
}
