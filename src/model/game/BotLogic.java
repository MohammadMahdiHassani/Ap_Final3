package model.game;

import javafx.geometry.Point2D;
import model.GameElement;
import model.cards.Card;
import model.cards.CardFactory;
import model.cards.CellValue;
import model.cards.buildings.Building;
import model.cards.spells.Rage;
import model.cards.spells.Spell;
import model.cards.troops.Giant;
import model.towers.ArcherTower;
import model.towers.KingTower;
import model.towers.Tower;

import java.util.ArrayList;

import static model.cards.CellValue.*;

public class BotLogic {

    private final GameLogic mainLogic;
    private final GameData data;
    private KingTower kingTower ;
    private ArcherTower archerTower_left ;
    private ArcherTower archerTower_right ;
    private final int kingRadarRadius ;
    private final int archerRaderRadius ;
    private int criticalHitPoint ;
    private int lT = 0 ;
    private int rT = 0 ;
    private CellValue[] ArcherTowerDefenseDeck ;

    public BotLogic(GameLogic mainlogic) {
        this.mainLogic = mainlogic;
        this.data = mainlogic.data;
        criticalHitPoint = 500 ;
        kingRadarRadius = 5 ;
        archerRaderRadius = 7 ;
        getTowers();
        initDecks() ;
    }

    private void initDecks() {
        ArcherTowerDefenseDeck = new CellValue[]{WIZARD, CANNON, BABY_DRAGON, RAGE, FIREBALL};
    }

    private void getTowers() {
        for(GameElement i : data.botDeck)
        {
            if(i instanceof Tower) {
                if (i instanceof KingTower)
                    kingTower = (KingTower) i;
                else if (i instanceof ArcherTower)
                {
                    if(i.getPoint().getX() < 10)
                        archerTower_left = (ArcherTower) i;
                    else
                        archerTower_right = (ArcherTower) i ;
                }
            }
        }
    }

    public void executeBot() {
        switch (data.botlevel) {

            case RANDOME:
                randomBotLogic();
                break;
            case MEDIUM:
                break;
            case HARD:
                smartBotLogic();

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
        if(mainLogic.speedCounter % 5 == 0 )
            isdefending();
    }

    private int KingRadar() {
        int counter = 0 ;
        for(GameElement element : data.boardElements){
            if(element.getPoint().distance(kingTower.getPoint()) < kingRadarRadius && !mainLogic.isBotElement(element))
                counter++ ;
        }

        return counter ;
    }
    private int ArcherRadar(ArcherTower archerTower){

        int counter = 0 ;
        for(GameElement element : data.boardElements){
            if(element.getPoint().distance(archerTower.getPoint()) < archerRaderRadius && !mainLogic.isBotElement(element))
                counter++ ;
        }

        return counter ;
    }
    private ArrayList<GameElement> getKingAttackers (){
        ArrayList<GameElement> tempArr = new ArrayList<>() ;
        for(GameElement i : data.boardElements){
            if(i.getPoint().distance(kingTower.getPoint()) < kingRadarRadius && !mainLogic.isBotElement(i))
                tempArr.add(i) ;
        }
        return tempArr ;
    }
    private ArrayList<GameElement> getArcherTowerAttackers (ArcherTower tower){
        ArrayList<GameElement> tempArr = new ArrayList<>() ;
        for(GameElement i : data.boardElements){
            if(i.getPoint().distance(tower.getPoint()) < archerRaderRadius && !mainLogic.isBotElement(i))
                tempArr.add(i) ;
        }
        return tempArr ;
    }
    private ArrayList<GameElement> getKingDefender (){
        ArrayList<GameElement> tempArr = new ArrayList<>() ;
        for(GameElement i : data.boardElements){
            if(i.getPoint().distance(kingTower.getPoint()) < kingRadarRadius && mainLogic.isBotElement(i))
                tempArr.add(i) ;
        }
        return tempArr ;
    }
    private ArrayList<GameElement> getArcherTowerDefender(ArcherTower tower){
        ArrayList<GameElement> tempArr = new ArrayList<>() ;
        for(GameElement i : data.boardElements){
            if(i.getPoint().distance(tower.getPoint()) < archerRaderRadius && mainLogic.isBotElement(i))
                tempArr.add(i) ;
        }
        return tempArr ;
    }


    private boolean protectTheKing(){

        if(KingRadar() <= 1)
           //King is protected
            return true;
        else {



        }




        return true ;
    }
    private boolean protectTowers(){

        boolean a , b = false ;
        if(!archerTower_left.isDead())
            a = defendArcherTower(archerTower_left);
        if(!archerTower_right.isDead())
            b = defendArcherTower(archerTower_right);

        return false;

    }

    private boolean defendArcherTower(ArcherTower archerTower) {
        if(archerTower.isDead() || ArcherRadar(archerTower)>1) {
            setFlagsToZero(archerTower) ;
            return false;
        }
        else{

            if(!makeBotCard(ArcherTowerDefenseDeck[getFlags(archerTower)] , archerTower)){
                incrementFlags(archerTower);
                makeBotCard(ArcherTowerDefenseDeck[getFlags(archerTower)] , archerTower);
            }
            incrementFlags(archerTower);
        }




        return true ;
    }

    private int getFlags(ArcherTower archerTower) {
        if(archerTower == archerTower_left )
            return lT ;
        return rT ;
    }

    private void setFlagsToZero(ArcherTower archerTower) {

        if(archerTower == archerTower_left)
            lT = 0 ;
        else
            rT = 0 ;
    }

    private void incrementFlags(ArcherTower archerTower){
        if(archerTower == archerTower_left)
            lT++ ;
        else
            rT++ ;

        if(lT > 4)
            lT = 0 ;
        if(rT > 4)
            rT = 0 ;
    }

    private boolean makeBotCard(CellValue cellValue , Tower tower){

        GameElement defendingElement = CardFactory.makeCard(cellValue , data.gameLevel) ;

        Point2D point = getDefendingPoint(tower) ;
        if(point != null) {
            ArrayList<GameElement> friendElements ;
            ArrayList<GameElement> enemyElements ;
            if(tower instanceof KingTower){
                enemyElements = getKingAttackers() ;
                friendElements = getKingDefender() ;
            }
            else {
                 enemyElements = getArcherTowerAttackers((ArcherTower) tower);
                 friendElements = getArcherTowerDefender((ArcherTower) tower);
            }
            if(defendingElement instanceof Spell)
            {
                if(defendingElement instanceof Rage){
                    boolean flag = false ;
                    for(GameElement i : friendElements){
                        if(i.getPoint().distance(point) > defendingElement.getRange())
                            flag = true ;
                    }
                    if(flag)
                        return false;

                }
                else{
                    boolean flag = false ;
                    for(GameElement i : enemyElements){
                        if(i.getPoint().distance(point) > defendingElement.getRange())
                            flag = true ;
                    }
                    if(flag)
                        return false;
                }
            }
            else if(defendingElement instanceof Building){
                boolean flag = false ;
                for(GameElement i : enemyElements){
                    if(i.getPoint().distance(point) > defendingElement.getRange())
                        flag = true ;
                }
                if(flag)
                    return false;
            }



            defendingElement.setPoint(getDefendingPoint(archerTower_left));
            data.boardElements.add(defendingElement);
            data.botDeck.add(defendingElement);
            return true ;
        }
        return false ;
    }

    private boolean isdefending() {
            return protectTheKing() || protectTowers();
    }

    private Point2D getDefendingPoint(Tower tower){
        if(tower instanceof KingTower) {
            Point2D kingPoint = tower.getPoint();
            if (ArcherRadar(archerTower_left) > ArcherRadar(archerTower_right)) {
                Point2D point;
                for (int i = (int) kingPoint.getX() - 1; i >= kingPoint.getX() - 3; i--) {
                    for (int j = (int) kingPoint.getY() + 3; j >= kingPoint.getY(); j--) {
                        if (!mainLogic.isOccupied(point = new Point2D(i, j)))
                            return point;
                    }
                }
            }
            else {
                Point2D point;
                for (int i = (int) kingPoint.getX() + 1; i <= kingPoint.getX() + 3; i++) {
                    for (int j = (int) kingPoint.getY() + 3; j >= kingPoint.getY(); j--) {
                        if (!mainLogic.isOccupied(point = new Point2D(i, j)))
                            return point;
                    }
                }
            }
        }
            else{
                if(tower == archerTower_left){
                    Point2D point;
                    for (int i = (int) archerTower_left.getPoint().getX() + 2; i >= archerTower_left.getPoint().getX() ; i--) {
                        for (int j = (int) archerTower_left.getPoint().getY() + 3; j >= archerTower_left.getPoint().getY(); j--) {
                            if (!mainLogic.isOccupied(point = new Point2D(i, j)))
                                return point;
                        }
                    }
                }
                else{
                    Point2D point;
                    for (int i = (int) archerTower_right.getPoint().getX() - 2 ; i <= archerTower_right.getPoint().getX() ; i++) {
                        for (int j = (int) archerTower_right.getPoint().getY() + 3 ; j >= archerTower_right.getPoint().getY(); j--) {
                            if (!mainLogic.isOccupied(point = new Point2D(i, j)))
                                return point;
                        }
                    }
                }
        }
            return null ;
    }

    private boolean containsElement(ArrayList<GameElement> enemyElements , CellValue value){
        for(GameElement i : enemyElements){

            switch(value){
                case GIANT:
                    if(i instanceof Giant)
                        return true ;
                    break;
                case BABY_DRAGON:
                    if(i instanceof Giant)
                        return true ;
                    break;
            }
        }
        return false ;
    }





}

