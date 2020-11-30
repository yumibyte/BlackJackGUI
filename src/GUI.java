import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.Math;

public class GUI implements ActionListener {
    public static JFrame frame;
    public static JPanel panel;
    public static HandView handView = new HandView();
    public static JButton playButton;


    public static void main(String[] args) {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 650);

        panel = new JPanel();
        panel.setLayout(null);
        frame.add(panel);

        JLabel blackJackLabel = new JLabel("BlackJack");
        blackJackLabel.setBounds(350, 50, 300, 50);
        blackJackLabel.setFont(new Font("Helvetica", Font.PLAIN, 40));
        panel.add(blackJackLabel);

        // 3 decks --> 1 for dealer, 2 for ea. player...

        playButton = new JButton("Single Player");
        playButton.setBounds(300, 275, 300, 50);
        playButton.addActionListener(new GUI());
        panel.add(playButton);



//        handView.createCard("1", 'l');
//        handView.createCard("2", 'l');
//        handView.createCard("1", 'r');
//        handView.createCard("2", 'r');




        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playButton) {
            String bet = JOptionPane.showInputDialog(panel, "What is your bet?", null);
        }
    }
}
