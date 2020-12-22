import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {
    public static JFrame frame;
    public static JPanel mainPanel;
    public static MainMenuView mainMenuView = new MainMenuView();
    public static PlayView.PlayViewGUI playView = new PlayView.PlayViewGUI();
//    public static CardFunctionality cardFunctionality = new CardFunctionality();

    public static void main(String[] args) {

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 760);       // based on image of BlackJackImage.jpg (background)

        mainPanel = mainMenuView.retrievePanel();     // instantiate main view
        frame.setContentPane(mainPanel);

        frame.setVisible(true);
    }

    public static void resetGame() {        // static method because it's called from static classes
        mainMenuView = new MainMenuView();
        playView = null;
        playView = new PlayView.PlayViewGUI();

        GUI.playView.playViewPanel.revalidate();
        GUI.playView.playViewPanel.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mainMenuView.singlePlayerButton) {

            // set number of players to 2
            playView.setNumberOfPlayers(2);

            // swap panels
            mainPanel = playView.getPlayViewPanel();
            frame.setContentPane(mainPanel);
            mainPanel.revalidate();

            // take users bet immediately

            String bet = JOptionPane.showInputDialog(mainPanel, "What is your bet in dollars? (Ex.: 50)?", "Don't be a cheapskate!", JOptionPane.QUESTION_MESSAGE);
            playView.inputBet = Integer.parseInt(bet);
            playView.betLabel.setText("My Bet: " + playView.inputBet);

            playView.setupCards(); // from cardFunctionality
            playView.setHasFinishedSettingUp(true);


        }
        else if (e.getSource() == playView.hitButton) {
            playView.hit();      // from cardFunctionality

            playView.playViewPanel.revalidate();
            playView.playViewPanel.repaint();
        }
        else if (e.getSource() == playView.standButton) {
            playView.stand();
        }
    }
}
