import javax.swing.*;

public class PlayView {

    JPanel playViewPanel;
    JLabel background;

    PlayView() {
        playViewPanel = new JPanel();
        background = new JLabel();

        ImageIcon blackJackBackground = new ImageIcon("BlackJackImage.jpg");
        background.setIcon(blackJackBackground);
        background.setBounds(200, 400, 1000, 600);



        playViewPanel.add(background);
    }

    JPanel retrievePanel() {
        return playViewPanel;
    }
}
