//

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CardFunctionality {
    ArrayList<String> mainCards = new ArrayList<String>();        // using an arrayList so values can be added/removed w/ ease. Also since listOfFiles uses a list

    // handViewList contains the number of cards for this one user. Main contains all user's hands
    public static CardView[][] usersHandsList = new CardView[3][5];

    void retrieveNewDeck() {
        File folder = new File("Card/");
        File[] listOfFiles = folder.listFiles();

//        for (int h = 0; h < 55; h ++) {
//            mainCards.add(null);
//        }
        for(int i = 0; i < 55; i ++) {
            assert listOfFiles != null;
            mainCards.add(listOfFiles[i].getName());
        }


        Collections.shuffle(mainCards);     // shuffle list

        // setup based on names in Card/
    }

    void hit() {
        int userNum = 0;        // replace with global variable that will change each time
        Random rand = new Random(); //instance of random class
        int upperBound = 53;        // arrays start at 0
        //generate random values from 0-53
        int int_random = rand.nextInt(upperBound);
        // left = 1, mid = 2, right = 3

        for (int i = 0; i < usersHandsList[userNum].length; i ++) {
            if (usersHandsList[userNum][i] == null) {
                String newCardName = mainCards.get(int_random);

                createCard(newCardName, 'l');
                break;
            } else if (i == 4 && usersHandsList[userNum][4] != null) {
                // apply functionality for winning because the user has filled the entire deck already and has won

            }
        }
    }

    void stand() {

    }

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
        for (int card = 0; card < 4; card ++) {
            if (cardList[card] != null) cardsInHandLength++;
        }

        CardView newCard = new CardView(positionX, positiony, cardName);
        cardList[cardsInHandLength + 1] = newCard;      // make next card the new card

        usersHandsList[sideInt] = cardList;      // set all hands at the position with the modified list
    }
}