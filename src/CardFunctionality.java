//

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class CardFunctionality {
    ArrayList<String> mainCards = new ArrayList<String>();        // using an arrayList so values can be added/removed w/ ease. Also since listOfFiles uses a list

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

        for (int i = 0; i < PlayView.usersHandsList[userNum].length; i ++) {
            if (PlayView.usersHandsList[userNum][i] == null) {
                String newCardName = mainCards.get(int_random);

                createCard(newCardName, PlayView.currentSide);
                break;
            } else if (i == 4 && PlayView.usersHandsList[userNum][4] != null) {
                // apply functionality for winning because the user has filled the entire deck already and has won
            }
        }
    }

    void stand() {
        if (PlayView.currentSide != 2) {     // if we're on the dealer's cards and they finish by standing, finalize the results
            PlayView.currentSide += 1;
        } else {
            determineWinner();
        }
    }

    void determineWinner() {

        int[] winnersScores;
        if (PlayView.numberOfPlayers == 2) {
            winnersScores = new int[] {PlayView.totalL, PlayView.totalM};
        } else {
            winnersScores = new int[] {PlayView.totalL, PlayView.totalR, PlayView.totalM};
        }
        for (int i = 0; i < PlayView.numberOfPlayers; i ++) {
            if (PlayView.hasLost[i] == true) {
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


        CardView[] cardList = PlayView.usersHandsList[sideInt];

        // count number of cards in hand
        int cardsInHandLength = 0;
        for (int card = 0; card < 4; card ++) {
            if (cardList[card] != null) cardsInHandLength++;
        }

        CardView newCard = new CardView(positionX, positiony, cardName);
        cardList[cardsInHandLength + 1] = newCard;      // make next card the new card

        PlayView.usersHandsList[sideInt] = cardList;      // set all hands at the position with the modified list

        switch (side) {
            case 0:
                PlayView.totalL += newCard.value;

                if (PlayView.totalL > 21) {
                    // convert to 1 for ace if the person "busts"
                    if (PlayView.hasAceL) {
                        PlayView.totalR -= 10;       // ace was worth 11 now is worth 1
                        PlayView.scoreLabelL.setText("Score: " + PlayView.totalL);
                    } else {
                        PlayView.scoreLabelL.setText("BUST!");
                        PlayView.hasLost[0] = true;
                        stand();
                    }
                } else {
                    PlayView.scoreLabelL.setText("Score: " + PlayView.totalL);
                }
                break;
            case 1:
                PlayView.totalR += newCard.value;

                if (PlayView.totalR > 21) {
                    if (PlayView.hasAceR) {
                        PlayView.totalR -= 10;       // ace was worth 11 now is worth 1
                        PlayView.scoreLabelR.setText("Score: " + PlayView.totalR);
                    } else {
                        PlayView.scoreLabelR.setText("BUST!");
                        PlayView.numberOfPlayers -= 1;
                        PlayView.hasLost[1] = true;
                        stand();
                    }
                } else {
                    PlayView.scoreLabelR.setText("Score: " + PlayView.totalR);
                }
                break;
            case 2:
                PlayView.totalM += newCard.value;

                if (PlayView.totalM > 21) {
                    if (PlayView.hasAceM) {
                        PlayView.totalM -= 10;       // ace was worth 11 now is worth 1
                        PlayView.scoreLabelM.setText("Score: " + PlayView.totalM);
                    } else {
                        PlayView.scoreLabelM.setText("BUST!");
                        PlayView.hasLost[2] = true;
                        stand();
                    }
                } else {
                    PlayView.scoreLabelM.setText("Score: " + PlayView.totalM);
                }
                break;
        }

    }
}
