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

    public static void gameSetup() {
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

    public static void resetGame() {        // static method because it's called from static classes

        int finalWinner = playView.getFinalWinner();
        String message = "";
        if (finalWinner == 0) {     // player won
            double amountWon = playView.inputBet + 100;
            message = "You won! I suppose here's your " + amountWon + " dollars...";
        } else if (finalWinner == 1) {      // right person won
            message = "Close one... sad a robot's better than you. Right person +" + 400 + " dollars";
        } else if (finalWinner == 2) {        // dealer won
            message = "The ole' expert won again. Dealer +" + 200 + " dollars";
        } else {        // -1, tie
            message = "Tie! You're all bad at blackjack. Here's your $" + playView.inputBet + " back";
        }
        String[] options = {"Play Again", "Exit to main menu"};
        String nextAction = (String)JOptionPane.showInputDialog(null, message,
                "Results", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (nextAction.equals("Play Again")) {
//            frame.revalidate();
//            frame.repaint();
            playView = new PlayView.PlayViewGUI();
            gameSetup();
            playView.playViewPanel.revalidate();
            playView.playViewPanel.repaint();
        } else {
            mainPanel = mainMenuView.retrievePanel();     // instantiate main view
            frame.setContentPane(mainPanel);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mainMenuView.singlePlayerButton) {
            gameSetup();
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
