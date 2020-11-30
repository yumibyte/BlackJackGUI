import javax.swing.*;
import java.util.ArrayList;
import java.lang.Math;

public class GUI {
    public static JFrame frame;
    public static JPanel playPanel;


    // handViewList contains the number of cards for this one user. Main contains all user's hands
    public static ArrayList<ArrayList<HandView>> usersHandsList = new ArrayList<ArrayList<HandView>>(3);

    ArrayList<HandView> createHand(int[] positions, String cardName) {
        ArrayList<HandView> cardList = new ArrayList<HandView>();

        int positionCount = 0;      // increment through positions provided, 2 each
        for(int card = 0; card < 3; card++) {
            HandView newCard = new HandView(positions[positionCount], positions[positionCount + 1], cardName);
            cardList.add(newCard);
        }

        return cardList;
    }

    public static void main(String[] args) {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 650);

        playPanel = new JPanel();
        playPanel.setLayout(null);
        frame.add(playPanel);

        // create 3 labels next to each other 3 separate times
        double positionX = 80;
        double positiony = 350;
        double radius = Math.toRadians(1000);
        double angleVariable = 45;
        double direction = 0;

        // 3 decks --> 1 for dealer, 2 for ea. player...

        HandView card1L = new HandView(150, 330, "1");
        HandView card2L = new HandView(170, 343, "2");
        HandView card3L = new HandView(190, 363, "3");
        cardList.add(card1L);
        cardList.add(card2L);
        cardList.add(card3L);
        playPanel.add()



//        for (int i = 0; i < 3; i ++) {
//            for (int j = 0; j < 3; j ++) {
//                // modify position on a semicircle
//                direction = 25 * Math.sin(angleVariable);
//                positionX = positionX + (radius * Math.cos(angleVariable));
//                positiony = positiony + (radius * Math.sin(angleVariable));
////                HandView handView = new HandView(positionX, positiony, "Hi");
////                cardList.add(handView);
////                playPanel.add(handView.imageLabel);
////                System.out.println(positionX);
//                System.out.println(positiony);
//            }
//            radius = radius * -1;
//            angleVariable = angleVariable * -4;
//            positionX += 600;
//            if (i == 1) {
//                positionX -= 900;       // undo adjustments to x put place iin middle
//                positiony += 200;
//            }
//
//            usersHandsList.add(cardList);
//
//        }


        frame.setVisible(true);
    }
}
