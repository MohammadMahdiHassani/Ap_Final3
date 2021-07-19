package model.game;

import javafx.geometry.Point2D;
import model.GameElement;
import model.cards.Card;
import model.cards.CardFactory;
import model.cards.CellValue;
import model.cards.buildings.Building;
import model.cards.buildings.Inferno;
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
    ArcherTower archerTower_left ;
    ArcherTower archerTower_right ;
    private int kingRadarRadius ;
    private final int archerRaderRadius ;
    private int criticalHitPoint ;
    private int lT = 0 ;
    private int rT = 0 ;
    private int mT = 0 ;
    private int attckFlag = 0 ;
    private CellValue[] ArcherTowerDefenseDeck ;
    private CellValue[] KingTowerDefense ;
    private CellValue[] AttackingValues ;
    private Point2D attckingPoint = null;
    private boolean criticalCondition ;
    private ArrayList<Point2D> criticalPoints ;

    public BotLogic(GameLogic mainlogic) {
        this.mainLogic = mainlogic;
        this.data = mainlogic.data;
        criticalHitPoint = 500 ;
        kingRadarRadius = 7 ;
        archerRaderRadius = 7 ;
        criticalCondition = false ;
        getTowers();
        initDecks() ;
    }

    private void initDecks() {
        ArcherTowerDefenseDeck = new CellValue[]{WIZARD, CANNON, BABY_DRAGON, RAGE, FIREBALL};
        KingTowerDefense = new CellValue[]{WIZARD, BABY_DRAGON, RAGE};
        AttackingValues = new CellValue[]{GIANT , RAGE , BABY_DRAGON, WIZARD};
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
        if(mainLogic.speedCounter % 7 == 0 )
            if(!isdefending())
                attack() ;
    }

    private void attack() {

            if(attckingPoint == null)
                attckingPoint = getAttackingPoint();

            if(attckingPoint != null)
            {
                makeAttackingCard(AttackingValues[attckFlag], attckingPoint) ;
                if(attckFlag == 3) {
                    attckFlag = 0;
                    attckingPoint = getAttackingPoint() ;
                }
                else
                    attckFlag++ ;
            }
    }

    private void makeAttackingCard(CellValue attackingValue, Point2D point) {
        GameElement attacker = CardFactory.makeCard(attackingValue , data.gameLevel) ;
        attacker.setPoint(point) ;
        data.botDeck.add(attacker) ;
        data.boardElements.add(attacker) ;
    }

    private Point2D getAttackingPoint() {
        ArrayList<Tower> towers = getPlayerArcherTowers();
        if(towers.size() == 2)
        {
            int leftCounter = 0 ;
            int rightCounter = 0 ;
            for(GameElement i : data.playerDeck){
                if(i.getPoint().getX() < 9)
                    leftCounter++ ;
                else
                    rightCounter++ ;
            }

            if(rightCounter > leftCounter)
                return mainLogic.findEmptyCell(new Point2D(3 , 8));
            else
                return mainLogic.findEmptyCell(new Point2D(15 , 8));
        }

        else if(towers.size() == 1){
            if(towers.get(0).getPoint().getX() < 9)
                return mainLogic.findEmptyCell(new Point2D(15 , 12));
            else
                return mainLogic.findEmptyCell(new Point2D(3 , 12)) ;
        }

        return null ;

    }
    private ArrayList<Tower> getPlayerArcherTowers(){
        ArrayList<Tower> towers = new ArrayList<>() ;
        for(GameElement i : data.playerDeck){
            if(i instanceof ArcherTower)
                towers.add((Tower) i) ;
        }
        return towers ;
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

        if(KingRadar() <= 1) {
            //King is protected
            setFlagsToZero(kingTower);
            return true;
        }

        else {
            if(kingTower.getHitPoint() < 1000)
                initCriticalProtocols() ;

            if(!deployInfernos())
            {

                if(!makeBotCard(KingTowerDefense[getFlags(kingTower)] , kingTower)){
                    incrementFlags(kingTower);
                    makeBotCard(KingTowerDefense[getFlags(kingTower)] , kingTower);
                }
                incrementFlags(kingTower);

            }

            return false ;
        }
    }

    private void initCriticalProtocols() {
        if(!criticalCondition) {
            criticalCondition = true;
            kingRadarRadius = 10;
            criticalPoints = new ArrayList<>();
            criticalPoints.add(new Point2D(kingTower.getPoint().getX(), kingTower.getPoint().getY() + 3));
            criticalPoints.add(new Point2D(kingTower.getPoint().getX() - 2, kingTower.getPoint().getY() + 2));
            criticalPoints.add(new Point2D(kingTower.getPoint().getX() + 2, kingTower.getPoint().getY() + 2));
        }
    }

    private boolean deployInfernos(){

        if(kingTower.getHitPoint() < 700){
            boolean flag = false ;
            for (Point2D m : criticalPoints) {
                if (mainLogic.isOccupied(m)){
                    if(!(mainLogic.point2Element(m) instanceof Inferno))
                        flag = true ;
                    continue;}
                GameElement inferno = CardFactory.makeCard(INFERNO, data.gameLevel);
                inferno.setPoint(m);
                data.boardElements.add(inferno);
                data.botDeck.add(inferno);
                return true ;
            }
            return !flag ;
        }
        return false ;
    }

    private boolean protectTowers(){
            return defendArcherTower(archerTower_left) && defendArcherTower(archerTower_right);
    }

    private boolean defendArcherTower(ArcherTower archerTower) {
        if(archerTower.isDead() || ArcherRadar(archerTower)<=1) {
            setFlagsToZero(archerTower) ;
            return true;
        }
        else{

            if(!makeBotCard(ArcherTowerDefenseDeck[getFlags(archerTower)] , archerTower)){
                incrementFlags(archerTower);
                makeBotCard(ArcherTowerDefenseDeck[getFlags(archerTower)] , archerTower);
            }
            incrementFlags(archerTower);
        }
        return false ;
    }

    private int getFlags(Tower tower) {
        if(tower == archerTower_left )
            return lT ;
        else if( tower == archerTower_right)
            return rT ;
        return mT ;
    }

    private void setFlagsToZero(Tower tower) {

        if(tower == archerTower_left)
            lT = 0 ;
        else if(tower == archerTower_right)
            rT = 0 ;
        else if(tower == kingTower)
            mT = 0 ;
    }

    private void incrementFlags(Tower tower){
        if(tower == archerTower_left)
            lT++ ;
        else if(tower == archerTower_right)
            rT++ ;
        else if(tower == kingTower)
            mT++ ;
        if(lT > 4)
            lT = 0 ;
        if(rT > 4)
            rT = 0 ;
        if(mT >= 3)
            mT = 0 ;


        attckFlag = 0 ;
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



            defendingElement.setPoint(point);
            data.boardElements.add(defendingElement);
            data.botDeck.add(defendingElement);
            return true ;
        }
        return false ;
    }

    private boolean isdefending() {
            return !(protectTheKing() && protectTowers());
    }

    private Point2D getDefendingPoint(Tower tower){
        if(tower == kingTower) {
            Point2D kingPoint = tower.getPoint();
            if (ArcherRadar(archerTower_left) > ArcherRadar(archerTower_right)) {
                Point2D point;
                for (int i = (int) kingPoint.getX() - 1; i >= kingPoint.getX() - 3; i--) {
                    for (int j = (int) kingPoint.getY() ; j <= kingPoint.getY() + 3; j++) {
                        if (!mainLogic.isOccupied(point = new Point2D(i, j)))
                            return point;
                    }
                }
            }
            else {
                Point2D point;
                for (int i = (int) kingPoint.getX() + 1; i <= kingPoint.getX() + 3; i++) {
                    for (int j = (int) kingPoint.getY() ; j <= kingPoint.getY() + 3; j++) {
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
                        for (int j = (int) archerTower_left.getPoint().getY(); j <= archerTower_left.getPoint().getY() + 3; j++) {
                            if (!mainLogic.isOccupied(point = new Point2D(i, j)))
                                return point;
                        }
                    }
                }
                else{
                    Point2D point;
                    for (int i = (int) archerTower_right.getPoint().getX() - 2 ; i <= archerTower_right.getPoint().getX() ; i++) {
                        for (int j = (int) archerTower_right.getPoint().getY() ; j <= archerTower_right.getPoint().getY() + 3 ; j++) {
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

