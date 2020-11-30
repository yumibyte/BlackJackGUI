import javax.swing.*;

public class PlayView {

    JLabel label;

    PlayView() {
        label = new JLabel("hi");
        GUI.frame.add(label);
        Thread thread = new Thread(); // run on new thread
        thread.start();
        System.out.println(thread.getId());

    }
}
