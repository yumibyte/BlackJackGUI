import java.util.ArrayList;

public class HandView {

    // handViewList contains the number of cards for this one user. Main contains all user's hands
    public static ArrayList<ArrayList<CardView>> usersHandsList = new ArrayList<ArrayList<CardView>>(5);

    // only create a card that will be based on a set of coordinates, a name, and the position of the card before it
    ArrayList<CardView> createHand(ArrayList<HandView> handView, String cardName, char side) {
        ArrayList<CardView> cardList = new ArrayList<CardView>();

        int positionX = 0;      // increment through positions provided, 2 each
        int positiony = 0;

        switch(side) {
            case 'l':       // left
                positionX = 20;
                positiony = 20;
                break;
            case 'r':       // right
                positionX = 20;
                positiony = 40;
                break;
            case 'm':        // middle or dealer
                positionX = 20;
                positiony = 60;
                break;
        }
        CardView newCard = new CardView(positionX, positiony, cardName);
        cardList.add(newCard);

        return cardList;
    }
}
