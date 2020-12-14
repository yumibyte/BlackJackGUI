import javax.swing.*;
import java.awt.*;

public class PlayView {

    JPanel playViewPanel;
    JLabel background;
    JButton hitButton;
    JButton standButton;
    JLabel betLabel;

    // score labels w/ total points
    JLabel scoreL;
    JLabel scoreR;
    JLabel scoreM;      // dealer
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

        scoreL = new JLabel("Score: 0");
        scoreL.setFont(new Font("Helvetica", Font.PLAIN, 20));
        scoreL.setBounds(462, 410, 100, 50);
        background.add(scoreL);

        scoreM = new JLabel("Score: 0");
        scoreM.setFont(new Font("Helvetica", Font.PLAIN, 20));
        scoreM.setBounds(710, 370, 100, 50);
        background.add(scoreM);

        scoreR = new JLabel("Score: 0");
        scoreR.setFont(new Font("Helvetica", Font.PLAIN, 20));
        scoreR.setBounds(462, 150, 100, 50);
        background.add(scoreR);


    }

    JPanel retrievePanel() {
        return playViewPanel;
    }
}
