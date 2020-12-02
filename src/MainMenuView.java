import javax.swing.*;
import java.awt.*;

public class MainMenuView {

    JPanel menuPanel;
    JLabel blackJackLabel;
    JButton menuButton;

    MainMenuView() {
        menuPanel = new JPanel();
        blackJackLabel = new JLabel("BlackJack");
        blackJackLabel.setBounds(350, 50, 300, 50);
        blackJackLabel.setFont(new Font("Helvetica",Font.PLAIN, 40));
        menuPanel.add(blackJackLabel);

        menuPanel.setLayout(null);

        // 3 decks --> 1 for dealer, 2 for ea. menuer...

        menuButton = new JButton("Single menu");
        menuButton.setBounds(300, 275, 300, 50);
        menuButton.addActionListener(new GUI());
        menuPanel.add(menuButton);
    }

    JPanel retrievePanel() {
        return menuPanel;
//        Thread thread = new Thread(); // run on new thread
//        thread.start();

    }

}
