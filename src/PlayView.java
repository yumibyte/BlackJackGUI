import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PlayView {

    JPanel playViewPanel;
    public static JLabel background;        // needs to be reset after so it's public
    JButton hitButton;
    JButton standButton;
    public static JLabel betLabel;      // needs to be reset

    // score labels w/ total points
    // use public static so they are set in CardFunctionality
    public static JLabel scoreLabelL;
    public static JLabel scoreLabelR;
    public static JLabel scoreLabelM;      // dealer

    // number of players, used when checking winners in CardFunctionality
    public static int numberOfPlayers = 0;

    // total values for each hand
    public static int totalL = 0;
    public static int totalR = 0;
    public static int totalM = 0;

    public static boolean[] hasLost = {false, false, false};

    public static CardView[][] usersHandsList = new CardView[3][5];
    public static int currentSide = 0;        // start on the left      // needs to be accessed in CardView

    public static boolean hasAceL = false;
    public static boolean hasAceR = false;
    public static boolean hasAceM = false;

    public static double inputBet;


    PlayView() {
        playViewPanel = new JPanel();
        background = new JLabel();

        ImageIcon blackJackBackground = new ImageIcon("BlackJackImage.jpg");
        background.setIcon(blackJackBackground);
        background.setBounds(200, 400, 1000, 600);
        playViewPanel.add(background);

        hitButton = new JButton("Hit!");
        hitButton.setBounds(100, 50, 175, 50);
        hitButton.addActionListener(new GUI());
        background.add(hitButton);

        standButton = new JButton("Stand");
        standButton.setBounds(700, 50, 175, 50);
        standButton.addActionListener(new GUI());
        background.add(standButton);

        betLabel = new JLabel("My Bet: ");
        betLabel.setBounds(20, 650, 500, 25);
        betLabel.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 20));
        background.add(betLabel);

        scoreLabelL = new JLabel("Score: 0");
        scoreLabelL.setFont(new Font("Helvetica", Font.PLAIN, 20));
        scoreLabelL.setBounds(462, 410, 100, 50);
        background.add(scoreLabelL);

        scoreLabelR = new JLabel("Score: 0");
        scoreLabelR.setFont(new Font("Helvetica", Font.PLAIN, 20));
        scoreLabelR.setBounds(710, 370, 100, 50);
        background.add(scoreLabelR);

        scoreLabelM = new JLabel("Score: 0");
        scoreLabelM.setFont(new Font("Helvetica", Font.PLAIN, 20));
        scoreLabelM.setBounds(462, 150, 100, 50);
        background.add(scoreLabelM);

    }

    JPanel retrievePanel() {
        return playViewPanel;
    }

    static void resetGame() {
//        scoreLabelL.setText("Score: 0");
//        scoreLabelR.setText("Score: 0");
//        scoreLabelM.setText("Score: 0");
//
//        betLabel.setText("My bet: 0");
//        numberOfPlayers = 0;
//
//        hasLost = new boolean[]{false, false, false};
// 
//        usersHandsList = new CardView[3][5];
//        currentSide = 0;        // start on the left      // needs to be accessed in CardView
//
//        hasAceL = false;
//        hasAceR = false;
//        hasAceM = false;
//
//        inputBet = 0;
//        background.repaint();


        // used for setting position, needs to be incremented ea. time
        GUI.cardFunctionality = new CardFunctionality();
        GUI.playView = new PlayView();

    }
}
