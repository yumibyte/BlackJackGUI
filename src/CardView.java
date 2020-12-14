import javax.swing.*;

// this class should not be touched directly it's only used to initialize cards by HandView
public class CardView {

    int positionX;
    int positiony;

    int value;

    boolean hasAceL = false;
    boolean hasAceR = false;
    boolean hasAceM = false;


    JLabel cardDisplay;
//    PlayView playView = new PlayView();


    CardView(int positionX, int positiony, String imageName) {

        // locate position
        this.positionX = positionX;
        this.positiony = positiony;

        // associate value
        String[] cardStringContents = imageName.substring(-4).split("_");
        
        switch (cardStringContents[1]) {
            case "ace":

                value = 1;
                int currentSide = CardFunctionality.currentSide;

                // nested case to check if there's an ace
                switch (currentSide) {
                    case 0:
                        hasAceL = true;
                        break;
                    case 1:
                        hasAceR = true;
                        break;
                    case 2:
                        hasAceM = true;
                        break;
                }
                break;
            case "jack": case "king": case "queen":
                value = 10;
                break;
        }


        // create image
        ImageIcon newCard = new ImageIcon("Card/" + imageName);
        cardDisplay = new JLabel("");
        cardDisplay.setIcon(newCard);
        cardDisplay.setBounds(positionX, positiony, 72, 96);
        GUI.playView.background.add(cardDisplay);


    }
}
