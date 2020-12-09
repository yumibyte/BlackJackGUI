import java.util.ArrayList;
public class HandView {

    // handViewList contains the number of cards for this one user. Main contains all user's hands
    public static CardView[][] usersHandsList = new CardView[3][5];


    // only create a card that will be based on a set of coordinates, a name, and the position of the card before it
    void createCard(String cardName, char side) {

        int positionX = 0;      // increment through positions provided, 2 each
        int positiony = 0;
        int sideInt;

        switch(side) {
            case 'l':       // left
                positionX = 20;
                positiony = 20;
                sideInt = 0;
                break;
            case 'r':       // right
                positionX = 20;
                positiony = 40;
                sideInt = 1;
                break;
            case 'm':        // middle or dealer
                positionX = 20;
                positiony = 60;
                sideInt = 2;

                break;
            default:
                throw new IllegalStateException("Unexpected value: " + side);
        }

        CardView[] cardList = usersHandsList[sideInt];

        // count number of cards in hand
        int cardsInHandLength = 0;
        for (int card = 0; card < 5; card ++) {
            if (cardList[card] != null) cardsInHandLength++;
        }

        CardView newCard = new CardView(positionX, positiony, cardName);
        cardList[cardsInHandLength + 1] = newCard;      // make next card the new card

        usersHandsList[sideInt] = cardList;      // set all hands at the position with the modified list
    }
}