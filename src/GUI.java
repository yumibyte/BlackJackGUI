import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.Math;

public class GUI implements ActionListener {
    public static JFrame frame;
    public static HandView handView = new HandView();
    public static JButton playButton;
    public static JPanel mainPanel;

    public static void main(String[] args) {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 650);

//        mainPanel = new JPanel();

        PlayView playView = new PlayView();     // instantiate main view

//        handView.createCard("1", 'l');
//        handView.createCard("2", 'l');
//        handView.createCard("1", 'r');
//        handView.createCard("2", 'r');




        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == playButton) {
//            String bet = JOptionPane.showInputDialog(playPanel, "What is your bet?", null);
//        }
    }
}
