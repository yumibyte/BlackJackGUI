import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class PlayView {

    public static class CardFunctionality {
        CardView[][] usersHandsList = new CardView[3][5];
        // number of players, used when checking winners in CardFunctionality
        public int numberOfPlayers = 0;
        public int currentSide = 0;        // start on the left
        public boolean hasFinishedSettingUp = false;

        // total values for each hand
        public int totalL = 0;
        public int totalR = 0;
        public int totalM = 0;
        public boolean[] hasLost = {false, false, false};
        public static ArrayList<String> mainCards = new ArrayList<String>();        // using an arrayList so values can be added/removed w/ ease. Also since listOfFiles uses a list
        // used for setting position, needs to be incremented ea. time

        int positionXl = 462;      // increment through positions provided, 2 each
        int positionyl = 455;      // set in exact middle

        int positionXr = 730;
        int positionyr = 420;

        int positionXm = 462;       // set at top middle
        int positionym = 50;

        public boolean hasAceL = false;
        public boolean hasAceR = false;
        public boolean hasAceM = false;
        CardFunctionality() {
            retrieveNewDeck();
        }

        public static void retrieveNewDeck() {
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

            // get usersHandsList


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

        void setupCards() {

            for (int i = 0; i < numberOfPlayers; i ++) {       // for the number of players, set up 2 cards each.
                hit();
                GUI.playView.playViewPanel.revalidate();
                GUI.playView.playViewPanel.repaint();
                hit();
                GUI.playView.playViewPanel.revalidate();
                GUI.playView.playViewPanel.repaint();
                stand();
            }
        }

        void stand() {
            if (currentSide != 2) {     // if we're on the dealer's cards and they finish by standing, finalize the results
                currentSide += 1;
                if (numberOfPlayers == 2 && currentSide == 1) {       // if there's only two players, then skip the third side
                    currentSide = 2;
                }
            } else {
                if (hasFinishedSettingUp) {
                    determineWinner();
                } else {
                    currentSide = 0;
                }
            }
        }

        void determineWinner() {

            // flip over dealers card that was hidden
            CardView dealersHand = usersHandsList[2][0];
            dealersHand.cardDisplay.setIcon(new ImageIcon("Card/" + dealersHand.cardName));


            int[] winnersScores;
            if (numberOfPlayers == 2) {
                winnersScores = new int[] {totalL, totalM};
            } else {
                winnersScores = new int[] {totalL, totalR, totalM};
            }
            for (int i = 0; i < numberOfPlayers; i ++) {
                if (hasLost[i] == true) {
                    for (int j = i; j < winnersScores.length - 1; j++) {
                        winnersScores[j] = winnersScores[j + 1];
                    }
                }
            }

            // use an arrayList so collections can be used to sort the winners
            ArrayList<Integer> sortedWinnersScores = new ArrayList<Integer>();
            for (int player = 0; player < numberOfPlayers; player ++) {
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

            // performed in because that is where all the vars all
//        resetGame();
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
            cardList[cardsInHandLength] = newCard;      // make next card the new card

            usersHandsList[sideInt] = cardList;      // set all hands at the position with the modified list

            switch (side) {
                case 0:
                    totalL += newCard.value;

                    if (totalL > 21) {
                        // convert to 1 for ace if the person "busts"
                        if (hasAceL) {
                            totalR -= 10;       // ace was worth 11 now is worth 1
                            GUI.playView.scoreLabelL.setText("Score: " + totalL);
                        } else {
                            GUI.playView.scoreLabelL.setText("BUST!");
                            hasLost[0] = true;
                            stand();
                        }
                    } else {
                        GUI.playView.scoreLabelL.setText("Score: " + totalL);
                    }
                    break;
                case 1:
                    totalR += newCard.value;

                    if (totalR > 21) {
                        if (hasAceR) {
                            totalR -= 10;       // ace was worth 11 now is worth 1
                            GUI.playView.scoreLabelR.setText("Score: " + totalR);
                        } else {
                            GUI.playView.scoreLabelR.setText("BUST!");
                            numberOfPlayers -= 1;
                            hasLost[1] = true;
                            stand();
                        }
                    } else {
                        GUI.playView.scoreLabelR.setText("Score: " + totalR);
                    }
                    break;
                case 2:
                    totalM += newCard.value;

                    if (totalM > 21) {
                        if (hasAceM) {
                            totalM -= 10;       // ace was worth 11 now is worth 1
                            GUI.playView.scoreLabelM.setText("Score: " + totalM);
                        } else {
                            GUI.playView.scoreLabelM.setText("BUST!");
                            hasLost[2] = true;
                            stand();
                        }
                    } else {
                        GUI.playView.scoreLabelM.setText("Score: " + totalM);
                    }
                    break;
            }
        }
    }

    public static class CardView extends CardFunctionality {

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

    public static class PlayViewGUI extends CardFunctionality {
        public JPanel playViewPanel;
        public JLabel background;        // needs to be reset after so it's public
        public JButton hitButton;
        public JButton standButton;
        public JLabel betLabel;      // needs to be reset

        // score labels w/ total points
        // use public static so they are set in CardFunctionality
        public JLabel scoreLabelL;
        public JLabel scoreLabelR;
        public JLabel scoreLabelM;      // dealer
        public double inputBet;

        PlayViewGUI() {
            playViewPanel = new JPanel();
            background = new JLabel();

            ImageIcon blackJackBackground = new ImageIcon("BlackJackImage.jpg");
            background.setIcon(blackJackBackground);
            background.setBounds(200, 400, 1000, 600);
            playViewPanel.add(background);

            hitButton = new JButton("Hit!");
            hitButton.setBounds(100, 50, 175, 50);
            hitButton.addActionListener(new GUI());
            background.add(hitButton);

            standButton = new JButton("Stand");
            standButton.setBounds(700, 50, 175, 50);
            standButton.addActionListener(new GUI());
            background.add(standButton);

            betLabel = new JLabel("My Bet: ");
            betLabel.setBounds(20, 650, 500, 25);
            betLabel.setFont(new Font("Helvetica", Font.ROMAN_BASELINE, 20));
            background.add(betLabel);

            scoreLabelL = new JLabel("Score: 0");
            scoreLabelL.setFont(new Font("Helvetica", Font.PLAIN, 20));
            scoreLabelL.setBounds(462, 410, 100, 50);
            background.add(scoreLabelL);

            scoreLabelR = new JLabel("Score: 0");
            scoreLabelR.setFont(new Font("Helvetica", Font.PLAIN, 20));
            scoreLabelR.setBounds(710, 370, 100, 50);
            background.add(scoreLabelR);

            scoreLabelM = new JLabel("Score: 0");
            scoreLabelM.setFont(new Font("Helvetica", Font.PLAIN, 20));
            scoreLabelM.setBounds(462, 150, 100, 50);
            background.add(scoreLabelM);
        }

//    static void resetGame() {
//        scoreLabelL.setText("Score: 0");
//        scoreLabelR.setText("Score: 0");
//        scoreLabelM.setText("Score: 0");
//
//        betLabel.setText("My bet: 0");
//        numberOfPlayers = 0;
//
//        hasLost = new boolean[]{false, false, false};
//
//        usersHandsList = new CardView[3][5];
//        currentSide = 0;        // start on the left      // needs to be accessed in CardView
//
//        hasAceL = false;
//        hasAceR = false;
//        hasAceM = false;
//
//        inputBet = 0;
//        background.repaint();


        // used for setting position, needs to be incremented ea. time
//        GUI.
//        GUI.playView = new PlayView();
//
//    }

        public JPanel getPlayViewPanel() {
            return playViewPanel;
        }
    }

}
