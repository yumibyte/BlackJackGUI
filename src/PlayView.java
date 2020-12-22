import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class PlayView {

    public static class CardFunctionality {
        public CardView[][] usersHandsList;
        // number of players, used when checking winners in CardFunctionality
        private int numberOfPlayers;
        public int currentSide;        // start on the left
        private boolean hasFinishedSettingUp;

        private int finalWinner;
        // total values for each hand
        private int[] scores;

        private boolean[] hasLost;
        private ArrayList<String> mainCards;        // using an arrayList so values can be added/removed w/ ease. Also since listOfFiles uses a list
        // used for setting position, needs to be incremented ea. time

        private int positionXl;      // increment through positions provided, 2 each
        private int positionyl;      // set in exact middle

        private int positionXr;
        private int positionyr;

        private int positionXm;       // set at top middle
        private int positionym;

        public static boolean[] hasAces;
        public boolean hasFinishedDealersTurn;
        public boolean hasFinishedPlayersTurn;

        CardFunctionality() {

            // setup all variables
            hasFinishedDealersTurn = false;
            usersHandsList = new CardView[3][5];
            numberOfPlayers = 0;
            currentSide = 0;
            hasFinishedSettingUp = false;

            hasLost = new boolean[] {false, false, false};
            mainCards = new ArrayList<String>();        // using an arrayList so values can be added/removed w/ ease. Also since listOfFiles uses a list
            // used for setting position, needs to be incremented ea. time

            positionXl = 462;      // increment through positions provided, 2 each
            positionyl = 455;      // set in exact middle

            positionXr = 730;
            positionyr = 420;

            positionXm = 462;       // set at top middle
            positionym = 50;

            hasAces = new boolean[] {false, false, false};
            scores = new int[] {0, 0, 0};
            // setup main deck
            retrieveNewDeck();


        }

        // setters
        public void setNumberOfPlayers(int i) {
            numberOfPlayers = i;
        }

//        public void setHasAces(int index, boolean value) {
//            hasAces[index] = value;
//        }

        public void setHasFinishedSettingUp(boolean j) {
            hasFinishedSettingUp = j;
        }
        // getters
        public int getFinalWinner() {
            return this.finalWinner;
        }

        public void retrieveNewDeck() {
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
//            int userNum = 0;        // replace with global variable that will change each time
            Random rand = new Random(); //instance of random class
            int upperBound = 51;        // arrays start at 0
            //generate random values from 0-51
            int int_random = rand.nextInt(upperBound);
            // left = 0, mid = 1, right = 2

            // get usersHandsList

            for (int i = 0; i < usersHandsList[currentSide].length; i ++) {
                if (usersHandsList[currentSide][i] == null) {
                    String newCardName = mainCards.get(int_random);

                    createCard(newCardName, currentSide);
                    break;
                } else if (i == 4 && usersHandsList[currentSide][4] != null) {
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
                if (numberOfPlayers == 2 && currentSide == 1) {       // if there's only two players, then skip the second side
                    currentSide = 2;
                }
            } else {
                if (hasFinishedSettingUp && hasFinishedDealersTurn) {      // if set up and dealer has taken turn
                    determineWinner();
                } else if (hasFinishedPlayersTurn == false) {
                    currentSide = 0;
                } else if (hasFinishedDealersTurn == false) {
                    hit();
                    GUI.playView.playViewPanel.revalidate();
                    GUI.playView.playViewPanel.repaint();
                    hasFinishedDealersTurn = true;
                    stand();
                }
            }

        }

        public void determineWinner() {

            // flip over dealers card that was hidden
            CardView dealersHand = usersHandsList[2][0];
            scores[2] += dealersHand.value;
            GUI.playView.scoreLabelM.setText("Score: " + scores[2]);
            dealersHand.cardDisplay.setIcon(new ImageIcon("Card/" + dealersHand.cardName));

            // edit values based on aces
            for (int i = 0; i < 3; i ++) {
                if (scores[i] <= 11) {
                    if (hasAces[i]) {
                        scores[i] += 10;
                    }
                }
            }
            GUI.playView.scoreLabelL.setText("Score: " + scores[0]);
            GUI.playView.scoreLabelR.setText("Score: " + scores[1]);
            GUI.playView.scoreLabelM.setText("Score: " + scores[2]);

            // check if their new value means they lost
            for (int i = 0; i < 3; i ++) {
                if (scores[i] > 21) {
                    hasLost[i] = true;
                }
            }

            // check if they've busted
            for (int j = 0; j < 3; j ++) {
                if (j == 0 && hasLost[j]) {
                    GUI.playView.scoreLabelL.setText("BUST!");
                }
                else if (j == 1 && hasLost[j]) {
                    GUI.playView.scoreLabelR.setText("BUST!");
                }
                else if (j == 2 && hasLost[j]) {
                    GUI.playView.scoreLabelM.setText("BUST!");
                }
            }

            // sort winnersScores
            int[] winnersScores;
            if (numberOfPlayers == 2) {
                winnersScores = new int[] {scores[0], scores[2]};       // you and dealer
                hasLost = new boolean[] {hasLost[0], hasLost[2]};
            } else {
                winnersScores = new int[] {scores[0], scores[1], scores[2]};        // 2 cpus and dealer
                // has lost is still all 3
            }
            for (int i = 0; i < numberOfPlayers; i ++) {
                if (hasLost[i] == true) {
                    winnersScores[i] = 0;       // place in last
                }
            }

            // use an arrayList so collections can be used to sort the winners
            ArrayList<Integer> sortedWinnersScores = new ArrayList<Integer>();
            for (int player = 0; player < numberOfPlayers; player ++) {
                sortedWinnersScores.add(winnersScores[player]);
            }

            Collections.sort(sortedWinnersScores);
            Collections.reverse(sortedWinnersScores);

            // find which player corresponds to the highest number
            for (int player = 0; player < sortedWinnersScores.size(); player ++) {
                if (winnersScores[player] == sortedWinnersScores.get(0)) {
                    finalWinner = player;
                }
            }




            if (numberOfPlayers == 2) {     // if there's two people and the 2nd person wins, the dealer won
                if (winnersScores[0] == winnersScores[1]) {
                    finalWinner = -1;
                }
                else if (finalWinner == 1) {
                    finalWinner += 1;
                }
            }

            // performed in because that is where all the vars all
            GUI.resetGame();
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

            CardView newCard = new CardView(positionX, positiony, cardName, currentSide, usersHandsList, hasAces);
            cardList[cardsInHandLength] = newCard;      // make next card the new card

            usersHandsList[sideInt] = cardList;      // set all hands at the position with the modified list
            switch (side) {
                case 0:
                    scores[0] += newCard.value;
                    GUI.playView.scoreLabelL.setText("Score: " + scores[0]);
                    break;
                case 1:
                    scores[1] += newCard.value;
                    GUI.playView.scoreLabelR.setText("Score: " + scores[1]);
                    break;
                case 2:
                    if (usersHandsList[2][1] != null) {     // keep the first card's value hidden
                        scores[2] += newCard.value;
                    }
                    GUI.playView.scoreLabelM.setText("Score: " + scores[2]);
                    break;
            }
        }
    }

    public static class CardView extends CardFunctionality {

        // not because it'll be instantiated multiple times
        int positionX;
        int positiony;
        int value;
        String cardName;
        JLabel cardDisplay;

        CardView(int positionX, int positiony, String imageName, int currentSideInput, CardView[][] usersHandsListInput, boolean[] hasAcesInput) {
            // can be left without inheritance because this is an association

            // locate position
            this.usersHandsList = usersHandsListInput;
            this.currentSide = currentSideInput;
            this.positionX = positionX;
            this.positiony = positiony;
            this.hasAces = hasAcesInput;
            cardName = imageName;       // set this so the dealer's first card can be recalled later
            // associate value
            String[] cardStringContents = imageName.substring(0, imageName.length() - 4).split("_");

            for (int i = 0; i < cardStringContents.length; i ++) {
                System.out.println(cardStringContents[i]);
            }
            switch (cardStringContents[1]) {

                case "ace":
                    value = 1;

                    // nested case to check if there's an ace
                    // in CardFunctionality, if the value is > 21 it will check if this is true and then set their ace value to 1
                    for (int i = 0; i < 3; i ++) {
                        if (currentSide == i) {
                            hasAces[i] = true;
                        }
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
        // use public so they are set in CardFunctionality
        public JLabel scoreLabelL;
        public JLabel scoreLabelR;
        public JLabel scoreLabelM;      // dealer
        public double inputBet;
        public double rightBet = 300;
        public double dealerBet = 100;

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

        public JPanel getPlayViewPanel() {
            return playViewPanel;
        }
    }

}
