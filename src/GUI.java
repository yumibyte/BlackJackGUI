import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {
    public static JFrame frame;
    public static HandView handView = new HandView();
    public static JButton playButton;
    public static JPanel mainPanel;
    public static MainMenuView mainMenuView = new MainMenuView();
    public static PlayView playView = new PlayView();




    public static void main(String[] args) {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 650);

//        mainPanel = new JPanel();

        mainPanel = mainMenuView.retrievePanel();     // instantiate main view
        frame.add(mainPanel);
//        handView.createCard("1", 'l');
//        handView.createCard("2", 'l');
//        handView.createCard("1", 'r');
//        handView.createCard("2", 'r');


        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mainMenuView.menuButton) {
//            String bet = JOptionPane.showInputDialog(playPanel, "What is your bet?", null);
            mainPanel.revalidate();
            mainPanel.repaint();        // clears frame
            mainPanel = playView.retrievePanel();

            frame.add(mainPanel);

        }
    }
}
