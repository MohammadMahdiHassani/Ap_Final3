package model.game;

import model.cards.Card;
import model.cards.CardFactory;
import model.cards.CellValue;
import model.cards.levelEnums.Level;

import java.util.ArrayList;

public class GameData {
     ArrayList<Card> playerDeck ;
     ArrayList<Card> botDeck ;
     ArrayList<Card> playedCards ;

    public GameData() {
        initBotDeck();
        initPlayerDeck();
        this.playedCards = new ArrayList<>();

    }

    private void initBotDeck(){
        botDeck = new ArrayList<>() ;

    }
    private void initPlayerDeck(){
        playerDeck = new ArrayList<>() ;
        playerDeck.add(CardFactory.makeCard(CellValue.GIANT , Level.LEVEL_1) );
        playerDeck.add(CardFactory.makeCard(CellValue.BARBERIAN , Level.LEVEL_1) );
        playerDeck.add(CardFactory.makeCard(CellValue.ARCHER , Level.LEVEL_1) );
        playerDeck.add(CardFactory.makeCard(CellValue.CANNON , Level.LEVEL_1) );

    }
}
