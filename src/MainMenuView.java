import javax.swing.*;
import java.awt.*;

public class MainMenuView {

    static JPanel menuPanel;
    static JLabel blackJackLabel;
    static JButton singlePlayerButton;
    static JButton saveStatsButton;

    MainMenuView() {
        menuPanel = new JPanel();
        blackJackLabel = new JLabel("BlackJack");
        blackJackLabel.setBounds(350, 50, 300, 50);
        blackJackLabel.setFont(new Font("Helvetica",Font.PLAIN, 40));
        menuPanel.add(blackJackLabel);

        menuPanel.setLayout(null);

        // 3 decks --> 1 for dealer, 2 for ea. menuer...

        singlePlayerButton = new JButton("Single menu");
        singlePlayerButton.setBounds(300, 275, 300, 50);
        singlePlayerButton.addActionListener(new GUI());
        menuPanel.add(singlePlayerButton);

        saveStatsButton = new JButton("Save stats");
        saveStatsButton.setBounds(300, 350, 300, 50);
        saveStatsButton.addActionListener(new GUI());
        menuPanel.add(saveStatsButton);
    }

    JPanel retrievePanel() {
        return menuPanel;
    }
}
