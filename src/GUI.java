import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {
    public static JFrame frame;
    public static JPanel mainPanel;
    public static MainMenuView mainMenuView = new MainMenuView();
    public static PlayView playView = new PlayView();
    public static CardFunctionality cardFunctionality = new CardFunctionality();

    public static void main(String[] args) {

        cardFunctionality.retrieveNewDeck();

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 760);       // based on image of BlackJackImage.jpg (background)

        mainPanel = mainMenuView.retrievePanel();     // instantiate main view
        frame.setContentPane(mainPanel);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mainMenuView.singlePlayerButton) {

            // set number of players to 2
            PlayView.numberOfPlayers = 2;

            // swap panels
            mainPanel = playView.retrievePanel();
            frame.setContentPane(mainPanel);
            mainPanel.revalidate();

            // take users bet immediately

            String bet = JOptionPane.showInputDialog(mainPanel, "What is your bet in dollars? (Ex.: 50)?", "Don't be a cheapskate!", JOptionPane.QUESTION_MESSAGE);
            playView.inputBet = Integer.parseInt(bet);
            playView.betLabel.setText("My Bet: " + playView.inputBet);


        }
        else if (e.getSource() == playView.hitButton) {
            cardFunctionality.hit();
            playView.playViewPanel.revalidate();
            playView.playViewPanel.repaint();
        }
        else if (e.getSource() == playView.standButton) {
            cardFunctionality.stand();
        }
    }
}
