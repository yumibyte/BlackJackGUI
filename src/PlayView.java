import javax.swing.*;
import java.awt.*;

public class PlayView {

    JPanel playViewPanel;
    JLabel background;
    JButton hitButton;
    JButton standButton;
    JLabel betLabel;

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

    }

    JPanel retrievePanel() {
        return playViewPanel;
    }
}
