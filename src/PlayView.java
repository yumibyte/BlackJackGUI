import javax.swing.*;

public class PlayView {

    JPanel playViewPanel;
    JLabel label;

    PlayView() {
        playViewPanel = new JPanel();
        label = new JLabel("hi");
//        Thread thread = new Thread(); // run on new thread
//        thread.start();
//        System.out.println(thread.getId());
        playViewPanel.add(label);
    }

    JPanel retrievePanel() {
        return playViewPanel;
    }
}
