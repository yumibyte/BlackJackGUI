//

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class CardFunctionality {
    ArrayList<String> mainCards = new ArrayList<String>();        // using an arrayList so values can be added/removed w/ ease

    void retrieveNewDeck() {
        File folder = new File("Card/");
        File[] listOfFiles = folder.listFiles();

        for(int i = 0; i < 55; i ++) {
            mainCards.add(listOfFiles[i].getName());
        }
        Collections.shuffle(mainCards);     // shuffle list

        // setup based on names in Card/

        

    }

}
