import javax.swing.*;

public class HandView {

    int positionX;
    int positiony;
    String imageName;
    JLabel imageLabel;
    HandView(double positionX, double positiony, String imageName) {

        // cast to int since double was required for trig
        int positionXLabel = (int) positionX;
        int positionYLabel = (int) positiony;

        this.positionX = positionXLabel;
        this.positiony = positionYLabel;
        imageLabel = new JLabel(imageName);
        imageLabel.setBounds(positionXLabel, positionYLabel, 50, 100);
    }
}
