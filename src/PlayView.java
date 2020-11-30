import javax.swing.*;
import java.awt.*;

public class PlayView {

    JPanel playPanel;
    JLabel blackJackLabel;
    JButton playButton;

    PlayView() {
        playPanel = new JPanel();
        blackJackLabel = new JLabel("BlackJack");
        blackJackLabel.setBounds(350, 50, 300, 50);
        blackJackLabel.setFont(new Font("Helvetica",Font.PLAIN, 40));
        playPanel.add(blackJackLabel);

        playPanel.setLayout(null);

        // 3 decks --> 1 for dealer, 2 for ea. player...

        playButton = new JButton("Single Player");
        playButton.setBounds(300, 275, 300, 50);
        playButton.addActionListener(new GUI());
        playPanel.add(playButton);

        GUI.frame.add(playPanel);
    }

}
