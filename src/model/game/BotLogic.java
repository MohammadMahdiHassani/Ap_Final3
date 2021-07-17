package model.game;

import javafx.geometry.Point2D;
import model.cards.Card;

public class BotLogic {

    private GameLogic mainLogic;
    private GameData data;

    public BotLogic(GameLogic mainlogic) {
        this.mainLogic = mainlogic;
        this.data = mainlogic.data;
    }

    public void executeBot() {
        switch (data.botlevel) {

            case RANDOME:
                randomBotLogic();
                break;

            case MEDIUM:
            case HARD:

        }

    }

    private void randomBotLogic(){
        if (!mainLogic.playerMoved)
            return;
        mainLogic.playerMoved = false;

        Card card = mainLogic.getRandomCard();

        Point2D randomPoint = mainLogic.getRandomPoint();
        while (mainLogic.isOccupied(randomPoint))
            randomPoint = mainLogic.getRandomPoint();
        data.botDeck.add(mainLogic.addToBoard(card, randomPoint));
        mainLogic.checkForSpawnedPlayers(card);

        mainLogic.sound(card);

    }

    private void smartBotLogic(){


    }

}
