import javax.swing.*;

public class HandView {

    int positionX;
    int positiony;
    String imageName;
    JLabel imageLabel;
    HandView(int positionX, int positiony, String imageName) {
        this.positionX = positionX;
        this.positiony = positiony;
        imageLabel = new JLabel(imageName);
        imageLabel.setBounds(positionX, positiony, 50, 100);
    }
}
