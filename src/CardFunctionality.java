//

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class CardFunctionality {
    ArrayList<String> mainCards = new ArrayList<String>();        // using an arrayList so values can be added/removed w/ ease. Also since listOfFiles uses a list
    PlayView playView = GUI.playView;
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
        int upperBound = 51;        // arrays start at 0
        //generate random values from 0-51
        int int_random = rand.nextInt(upperBound);
        // left = 0, mid = 1, right = 2

        for (int i = 0; i < playView.usersHandsList[userNum].length; i ++) {
            if (playView.usersHandsList[userNum][i] == null) {
                String newCardName = mainCards.get(int_random);

                createCard(newCardName, playView.currentSide);
                break;
            } else if (i == 4 && playView.usersHandsList[userNum][4] != null) {
                // apply functionality for winning because the user has filled the entire deck already and has won
            }
        }
    }

    void setupCards() {

        for (int i = 0; i < playView.numberOfPlayers; i ++) {       // for the number of players, set up 2 cards each.
            hit();
            playView.playViewPanel.revalidate();
            playView.playViewPanel.repaint();
            hit();
            playView.playViewPanel.revalidate();
            playView.playViewPanel.repaint();
            stand();
        }
        playView.hasFinishedSettingUp = true;
    }

    void stand() {
        if (playView.currentSide != 2) {     // if we're on the dealer's cards and they finish by standing, finalize the results
            playView.currentSide += 1;
            if (playView.numberOfPlayers == 2 && playView.currentSide == 1) {       // if there's only two players, then skip the third side
                playView.currentSide = 2;
            }
        } else {
            if (playView.hasFinishedSettingUp) {
                determineWinner();
            } else {
                playView.currentSide = 0;
            }
        }
    }

    void determineWinner() {

        int[] winnersScores;
        if (playView.numberOfPlayers == 2) {
            winnersScores = new int[] {playView.totalL, playView.totalM};
        } else {
            winnersScores = new int[] {playView.totalL, playView.totalR, playView.totalM};
        }
        for (int i = 0; i < playView.numberOfPlayers; i ++) {
            if (playView.hasLost[i] == true) {
                for (int j = i; j < winnersScores.length - 1; j++) {
                    winnersScores[j] = winnersScores[j + 1];
                }
            }
        }

        // use an arrayList so collections can be used to sort the winners
        ArrayList<Integer> sortedWinnersScores = new ArrayList<Integer>();
        for (int player = 0; player < playView.numberOfPlayers; player ++) {
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

        // performed in playView because that is where all the vars all
        playView.resetGame();
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


        CardView[] cardList = playView.usersHandsList[sideInt];

        // count number of cards in hand
        int cardsInHandLength = 0;
        for (int card = 0; card < 4; card ++) {
            if (cardList[card] != null) cardsInHandLength++;
        }

        CardView newCard = new CardView(positionX, positiony, cardName);
        cardList[cardsInHandLength] = newCard;      // make next card the new card

        playView.usersHandsList[sideInt] = cardList;      // set all hands at the position with the modified list

        switch (side) {
            case 0:
                playView.totalL += newCard.value;

                if (playView.totalL > 21) {
                    // convert to 1 for ace if the person "busts"
                    if (playView.hasAceL) {
                        playView.totalR -= 10;       // ace was worth 11 now is worth 1
                        playView.scoreLabelL.setText("Score: " + playView.totalL);
                    } else {
                        playView.scoreLabelL.setText("BUST!");
                        playView.hasLost[0] = true;
                        stand();
                    }
                } else {
                    playView.scoreLabelL.setText("Score: " + playView.totalL);
                }
                break;
            case 1:
                playView.totalR += newCard.value;

                if (playView.totalR > 21) {
                    if (playView.hasAceR) {
                        playView.totalR -= 10;       // ace was worth 11 now is worth 1
                        playView.scoreLabelR.setText("Score: " + playView.totalR);
                    } else {
                        playView.scoreLabelR.setText("BUST!");
                        playView.numberOfPlayers -= 1;
                        playView.hasLost[1] = true;
                        stand();
                    }
                } else {
                    playView.scoreLabelR.setText("Score: " + playView.totalR);
                }
                break;
            case 2:
                playView.totalM += newCard.value;

                if (playView.totalM > 21) {
                    if (playView.hasAceM) {
                        playView.totalM -= 10;       // ace was worth 11 now is worth 1
                        playView.scoreLabelM.setText("Score: " + playView.totalM);
                    } else {
                        playView.scoreLabelM.setText("BUST!");
                        playView.hasLost[2] = true;
                        stand();
                    }
                } else {
                    playView.scoreLabelM.setText("Score: " + playView.totalM);
                }
                break;
        }
    }
}
