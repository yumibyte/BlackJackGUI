import javax.swing.*;

// this class should not be touched directly it's only used to initialize cards by HandView
public class CardView extends CardFunctionality {

    int positionX;
    int positiony;
    int value;
    String cardName;
    JLabel cardDisplay;

    CardView(int positionX, int positiony, String imageName) {
        // can be left without inheritance because this is an association

        // locate position
        this.positionX = positionX;
        this.positiony = positiony;

        cardName = imageName;       // set this so the dealer's first card can be recalled later
        // associate value
        String[] cardStringContents = imageName.substring(0, imageName.length() - 4).split("_");

        for (int i = 0; i < cardStringContents.length; i ++) {
            System.out.println(cardStringContents[i]);
        }
        switch (cardStringContents[1]) {

            case "ace":
                value = 11;

                // nested case to check if there's an ace
                // in CardFunctionality, if the value is > 21 it will check if this is true and then set their ace value to 1
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

            case "jack": case "king": case "queen": case "joker":
                value = 10;
                break;

            default:        // every other suit
                value = Integer.parseInt(cardStringContents[1]);
                break;
        }

        ImageIcon newCard;
        // create image
        if (currentSide == 2 && usersHandsList[2][0] == null) {        // if it's the dealer's first card
            newCard = new ImageIcon("ExtraCards/" + "cardBack.png");
        } else {
            newCard = new ImageIcon("Card/" + imageName);

        }
        cardDisplay = new JLabel("");
        cardDisplay.setIcon(newCard);
        cardDisplay.setBounds(positionX, positiony, 72, 96);
        GUI.playView.background.add(cardDisplay);

    }
}
