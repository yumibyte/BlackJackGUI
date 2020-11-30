import javax.swing.*;

// this class should not be touched directly it's only used to initialize cards by HandView
public class CardView {

    int positionX;
    int positiony;
    String imageName;
    JLabel imageLabel;

    CardView(int positionX, int positiony, String imageName) {
        this.positionX = positionX;
        this.positiony = positiony;
        imageLabel = new JLabel(imageName);
        imageLabel.setBounds(positionX, positiony, 50, 100);

        GUI.playPanel.add(imageLabel);
    }
}
