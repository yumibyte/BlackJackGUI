//
//import javax.swing.*;
//import java.io.File;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Random;
//
//public class CardFunctionality extends PlayView {
//
//    public static ArrayList<String> mainCards = new ArrayList<String>();        // using an arrayList so values can be added/removed w/ ease. Also since listOfFiles uses a list
//    // used for setting position, needs to be incremented ea. time
//    int positionXl = 462;      // increment through positions provided, 2 each
//    int positionyl = 455;      // set in exact middle
//
//    int positionXr = 730;
//    int positionyr = 420;
//
//    int positionXm = 462;       // set at top middle
//    int positionym = 50;
//
//    public boolean hasAceL = false;
//    public boolean hasAceR = false;
//    public boolean hasAceM = false;
//
//    CardFunctionality() {
//    }
//
//    static void retrieveNewDeck() {
//        File folder = new File("Card/");
//        File[] listOfFiles = folder.listFiles();
//
//        for(int i = 0; i < 54; i ++) {
//            assert listOfFiles != null;
//            mainCards.add(listOfFiles[i].getName());
//        }
//
//        Collections.shuffle(mainCards);     // shuffle list
//        // setup based on names in Card/
//    }
//
//    void hit() {
//        int userNum = 0;        // replace with global variable that will change each time
//        Random rand = new Random(); //instance of random class
//        int upperBound = 51;        // arrays start at 0
//        //generate random values from 0-51
//        int int_random = rand.nextInt(upperBound);
//        // left = 0, mid = 1, right = 2
//
//        // get usersHandsList
//
//        for (int i = 0; i < usersHandsList[userNum].length; i ++) {
//            if (usersHandsList[userNum][i] == null) {
//                String newCardName = mainCards.get(int_random);
//
//                createCard(newCardName, currentSide);
//                break;
//            } else if (i == 4 && usersHandsList[userNum][4] != null) {
//                // apply functionality for winning because the user has filled the entire deck already and has won
//            }
//        }
//    }
//
//    void setupCards() {
//
//        for (int i = 0; i < numberOfPlayers; i ++) {       // for the number of players, set up 2 cards each.
//            hit();
//            GUI.playView.playViewPanel.revalidate();
//            GUI.playView.playViewPanel.repaint();
//            hit();
//            GUI.playView.playViewPanel.revalidate();
//            GUI.playView.playViewPanel.repaint();
//            stand();
//        }
//    }
//
//    void stand() {
//        if (currentSide != 2) {     // if we're on the dealer's cards and they finish by standing, finalize the results
//            currentSide += 1;
//            if (numberOfPlayers == 2 && currentSide == 1) {       // if there's only two players, then skip the third side
//                currentSide = 2;
//            }
//        } else {
//            if (hasFinishedSettingUp) {
//                determineWinner();
//            } else {
//                currentSide = 0;
//            }
//        }
//    }
//
//    void determineWinner() {
//
//        // flip over dealers card that was hidden
//        CardView dealersHand = usersHandsList[2][0];
//        dealersHand.cardDisplay.setIcon(new ImageIcon("Card/" + dealersHand.cardName));
//
//
//        int[] winnersScores;
//        if (numberOfPlayers == 2) {
//            winnersScores = new int[] {totalL, totalM};
//        } else {
//            winnersScores = new int[] {totalL, totalR, totalM};
//        }
//        for (int i = 0; i < numberOfPlayers; i ++) {
//            if (hasLost[i] == true) {
//                for (int j = i; j < winnersScores.length - 1; j++) {
//                    winnersScores[j] = winnersScores[j + 1];
//                }
//            }
//        }
//
//        // use an arrayList so collections can be used to sort the winners
//        ArrayList<Integer> sortedWinnersScores = new ArrayList<Integer>();
//        for (int player = 0; player < numberOfPlayers; player ++) {
//            sortedWinnersScores.add(winnersScores[player]);
//        }
//
//        Collections.sort(sortedWinnersScores);
//        Collections.reverse(sortedWinnersScores);
//
//        int finalWinner = -1;
//
//        // find which player corresponds to the highest number
//        for (int player = 0; player < sortedWinnersScores.size(); player ++) {
//            if (winnersScores[player] == sortedWinnersScores.get(0)) {
//                finalWinner = player;
//            }
//        }
//        System.out.println(finalWinner);
//
//        // performed in because that is where all the vars all
////        resetGame();
//    }
//
//    void createCard(String cardName, int side) {
//
//        int sideInt;
//        int positionX;
//        int positiony;
//
//        switch(side) {
//            // 1000 x 760 is the frame size
//            case 0:       // left
//                positionX = positionXl;
//                positiony = positionyl;
//
//                positionXl += 15;
//                sideInt = 0;
//                break;
//            case 1:       // right
//                positionX = positionXr;
//                positiony = positionyr;
//
//                positionXr += 15;
//                sideInt = 1;
//                break;
//            case 2:        // middle or dealer
//                positionX = positionXm;
//                positiony = positionym;
//
//                positionXm += 15;
//                sideInt = 2;
//                break;
//            default:
//                throw new IllegalStateException("Unexpected value: " + side);
//        }
//
//
//        CardView[] cardList = usersHandsList[sideInt];
//
//        // count number of cards in hand
//        int cardsInHandLength = 0;
//        for (int card = 0; card < 4; card ++) {
//            if (cardList[card] != null) cardsInHandLength++;
//        }
//
//        CardView newCard = new CardView(positionX, positiony, cardName);
//        cardList[cardsInHandLength] = newCard;      // make next card the new card
//
//        usersHandsList[sideInt] = cardList;      // set all hands at the position with the modified list
//
//        switch (side) {
//            case 0:
//                totalL += newCard.value;
//
//                if (totalL > 21) {
//                    // convert to 1 for ace if the person "busts"
//                    if (hasAceL) {
//                        totalR -= 10;       // ace was worth 11 now is worth 1
//                        GUI.playView.scoreLabelL.setText("Score: " + totalL);
//                    } else {
//                        GUI.playView.scoreLabelL.setText("BUST!");
//                        hasLost[0] = true;
//                        stand();
//                    }
//                } else {
//                    GUI.playView.scoreLabelL.setText("Score: " + totalL);
//                }
//                break;
//            case 1:
//                totalR += newCard.value;
//
//                if (totalR > 21) {
//                    if (hasAceR) {
//                        totalR -= 10;       // ace was worth 11 now is worth 1
//                        GUI.playView.scoreLabelR.setText("Score: " + totalR);
//                    } else {
//                        GUI.playView.scoreLabelR.setText("BUST!");
//                        numberOfPlayers -= 1;
//                        hasLost[1] = true;
//                        stand();
//                    }
//                } else {
//                    GUI.playView.scoreLabelR.setText("Score: " + totalR);
//                }
//                break;
//            case 2:
//                totalM += newCard.value;
//
//                if (totalM > 21) {
//                    if (hasAceM) {
//                        totalM -= 10;       // ace was worth 11 now is worth 1
//                        GUI.playView.scoreLabelM.setText("Score: " + totalM);
//                    } else {
//                        GUI.playView.scoreLabelM.setText("BUST!");
//                        hasLost[2] = true;
//                        stand();
//                    }
//                } else {
//                    GUI.playView.scoreLabelM.setText("Score: " + totalM);
//                }
//                break;
//        }
//    }
//}
