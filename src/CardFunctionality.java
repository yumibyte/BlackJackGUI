//

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CardFunctionality {
    ArrayList<String> mainCards = new ArrayList<String>();        // using an arrayList so values can be added/removed w/ ease. Also since listOfFiles uses a list

    // handViewList contains the number of cards for this one user. Main contains all user's hands
    public static CardView[][] usersHandsList = new CardView[3][5];
    public static int currentSide = 0;        // start on the left      // needs to be accessed in CardView

    // total values for each hand
    public static int totalL = 0;
    public static int totalR = 0;
    public static int totalM = 0;

    public static boolean[] hasLost = {false, false, false};


    // used for setting position, needs to be incremented ea. time
    int positionXl = 462;      // increment through positions provided, 2 each
    int positionyl = 455;      // set in exact middle

    int positionXr = 730;
    int positionyr = 420;

    int positionXm = 462;       // set at top middle
    int positionym = 50;



    void retrieveNewDeck() {
        File folder = new File("Card/");
        File[] listOfFiles = folder.listFiles();

        for(int i = 0; i < 54; i ++) {
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
        // left = 0, mid = 1, right = 2

        for (int i = 0; i < usersHandsList[userNum].length; i ++) {
            if (usersHandsList[userNum][i] == null) {
                String newCardName = mainCards.get(int_random);

                createCard(newCardName, currentSide);
                break;
            } else if (i == 4 && usersHandsList[userNum][4] != null) {
                // apply functionality for winning because the user has filled the entire deck already and has won
            }
        }
    }

    void stand() {
        if (currentSide != 2) {     // if we're on the dealer's cards and they finish by standing, finalize the results
            currentSide += 1;
        } else {
            determineWinner();
        }
    }

    void determineWinner() {


        int[] winnersScores;
        if (PlayView.numberOfPlayers == 2) {
            winnersScores = new int[] {totalL, totalM};
        } else {
            winnersScores = new int[] {totalL, totalR, totalM};
        }
        for (int i = 0; i < PlayView.numberOfPlayers; i ++) {
            if (hasLost[i] == true) {
                for (int j = i; j < winnersScores.length - 1; j++) {
                    winnersScores[j] = winnersScores[j + 1];
                }
            }
        }

        // use an arrayList so collections can be used to sort the winners
        ArrayList<Integer> sortedWinnersScores = new ArrayList<Integer>();
        for (int player = 0; player < PlayView.numberOfPlayers; player ++) {
            sortedWinnersScores.add(winnersScores[player]);
        }

        Collections.sort(sortedWinnersScores);
        Collections.reverse(sortedWinnersScores);

        int finalWinner = -1;

        // find which player corresponds to the highest number
        for (int player = 0; player < sortedWinnersScores.size(); player ++) {
            if (winnersScores[player] == sortedWinnersScores.get(0)) {
                finalWinner = player;
            }
        }
        System.out.println(finalWinner);

        resetGame();
    }

    void resetGame() {
        //
    }
    void createCard(String cardName, int side) {

        int sideInt;
        int positionX;
        int positiony;

        switch(side) {
            // 1000 x 760 is the frame size
            case 0:       // left
                positionX = positionXl;
                positiony = positionyl;

                positionXl += 15;
                sideInt = 0;
                break;
            case 1:       // right
                positionX = positionXr;
                positiony = positionyr;

                positionXr += 15;
                sideInt = 1;
                break;
            case 2:        // middle or dealer
                positionX = positionXm;
                positiony = positionym;

                positionXm += 15;
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

        switch (side) {
            case 0:
                totalL += newCard.value;

                if (totalL > 21) {
                    // convert to 1 for ace if the person "busts"
                    if (CardView.hasAceL) {
                        totalR -= 10;       // ace was worth 11 now is worth 1
                        PlayView.scoreLabelL.setText("Score: " + totalL);
                    } else {
                        PlayView.scoreLabelL.setText("BUST!");
                        hasLost[0] = true;
                        stand();
                    }
                } else {
                    PlayView.scoreLabelL.setText("Score: " + totalL);
                }
                break;
            case 1:
                totalR += newCard.value;

                if (totalR > 21) {
                    if (CardView.hasAceR) {
                        totalR -= 10;       // ace was worth 11 now is worth 1
                        PlayView.scoreLabelR.setText("Score: " + totalR);
                    } else {
                        PlayView.scoreLabelR.setText("BUST!");
                        PlayView.numberOfPlayers -= 1;
                        hasLost[1] = true;
                        stand();
                    }
                } else {
                    PlayView.scoreLabelR.setText("Score: " + totalR);
                }
                break;
            case 2:
                totalM += newCard.value;

                if (totalM > 21) {
                    if (CardView.hasAceM) {
                        totalM -= 10;       // ace was worth 11 now is worth 1
                        PlayView.scoreLabelM.setText("Score: " + totalM);
                    } else {
                        PlayView.scoreLabelM.setText("BUST!");
                        hasLost[2] = true;
                        stand();
                    }
                } else {
                    PlayView.scoreLabelM.setText("Score: " + totalM);
                }
                break;
        }

//        // convert ace to 1 if the person had "busted"
//        if (totalL > 21 && CardView.hasAceL) {
//            totalL -= 10;       // ace was worth 11 now is worth 1
//            PlayView.scoreLabelL.setText("Score: " + totalL);
//        } else if (totalR > 21 && CardView.hasAceR) {
//            totalR -= 10;       // ace was worth 11 now is worth 1
//            PlayView.scoreLabelR.setText("Score: " + totalR);
//        } else if (totalM > 21 && CardView.hasAceM) {
//            totalM -= 10;       // ace was worth 11 now is worth 1
//            PlayView.scoreLabelM.setText("Score: " + totalM);
//        }
    }
}
