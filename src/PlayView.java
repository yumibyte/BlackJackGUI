import javax.swing.*;
import java.awt.*;

public class PlayView {

    JPanel playViewPanel;
    JLabel background;
    JButton hitButton;
    JButton standButton;

    PlayView() {
        playViewPanel = new JPanel();
        background = new JLabel();

        ImageIcon blackJackBackground = new ImageIcon("BlackJackImage.jpg");
        background.setIcon(blackJackBackground);
        background.setBounds(200, 400, 1000, 600);
//        background.setLayout(new FlowLayout(FlowLayout.CENTER));
        playViewPanel.add(background);

        hitButton = new JButton("Hit!");
        hitButton.setBounds(100, 50, 175, 50);
        background.add(hitButton);

        standButton = new JButton("Stand");
        standButton.setBounds(700, 50, 175, 50);
        background.add(standButton);

    }

    JPanel retrievePanel() {
        return playViewPanel;
    }
}
