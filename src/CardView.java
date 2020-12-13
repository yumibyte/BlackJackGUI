import javax.swing.*;

// this class should not be touched directly it's only used to initialize cards by HandView
public class CardView {

    int positionX;
    int positiony;

    JLabel cardDisplay;
//    PlayView playView = new PlayView();


    CardView(int positionX, int positiony, String imageName) {
        this.positionX = positionX;
        this.positiony = positiony;
        ImageIcon newCard = new ImageIcon("Card/" + imageName);
        cardDisplay = new JLabel("");
        cardDisplay.setIcon(newCard);
        cardDisplay.setBounds(positionX, positiony, 72, 96);
        GUI.playView.background.add(cardDisplay);

    }
}
