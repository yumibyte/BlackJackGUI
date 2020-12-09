import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {
    public static JFrame frame;
    public static HandView handView = new HandView();
    public static JPanel mainPanel;
    public static MainMenuView mainMenuView = new MainMenuView();
    public static PlayView playView = new PlayView();
    public static CardFunctionality cardFunctionality = new CardFunctionality();

    public static double inputBet;



    public static void main(String[] args) {

        cardFunctionality.retrieveNewDeck();
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 760);       // based on image of BlackJackImage.jpg (background)

//        mainPanel = new JPanel();

        mainPanel = mainMenuView.retrievePanel();     // instantiate main view
        frame.setContentPane(mainPanel);
//        handView.createCard("1", 'l');
//        handView.createCard("2", 'l');
//        handView.createCard("1", 'r');
//        handView.createCard("2", 'r');


        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mainMenuView.singlePlayerButton) {

            mainPanel = playView.retrievePanel();
            frame.setContentPane(mainPanel);
            mainPanel.revalidate();

            // take users bet immediately

            String bet = JOptionPane.showInputDialog(mainPanel, "What is your bet in dollars? (Ex.: 50)?", "Don't be a cheapskate!", JOptionPane.QUESTION_MESSAGE);
            inputBet = Integer.parseInt(bet);
            playView.betLabel.setText("My Bet: " + inputBet);
        }
    }
}
