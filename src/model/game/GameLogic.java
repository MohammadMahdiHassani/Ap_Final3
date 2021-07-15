package model.game;

import DataBase.DataHandler;
import javafx.geometry.Point2D;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import model.GameElement;
import model.cards.Card;
import model.cards.CardFactory;
import model.cards.buildings.Building;
import model.cards.spells.Spell;
import model.cards.troops.Archer;
import model.cards.troops.BabyDragon;
import model.cards.troops.Giant;
import model.cards.troops.Troop;
import model.towers.Tower;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class GameLogic {
    private int speedCounter ; 
    private ArenaModel model;
    private GameData data;
    private Point2D currPoint;
    private Card currCard;
    private boolean playerMoved;
    private ProgressBar elixirProgress;
    private ListView<Card> listArmy;
    private Card nextCard;
    private ArrayList<Card> savedCard;
    private ImageView nextCardImage;


    public GameLogic() {
        playerMoved = false;
        speedCounter = 0 ; 
    }

    public void preprocessLogic() {
        model = ArenaModel.arenaModel;
        data = ArenaModel.arenaModel.gameData;
    }

    public void executeLogic() {
        model.vectorMap = new HashMap<>();
        checkForPlayerMove();
        updateCards();
        updateBoard();
        if (playerMoved) {
            executeBot();
            playerMoved = false;
        }
    }


    private void checkForPlayerMove() {
        if (currPoint != null && currCard != null) {
            if (point2cellValue(currPoint) == null) {
                addToBoard(currCard , currPoint);
                playerMoved = true;
                currCard = null;
                currPoint = null;
            }
        }
    }


    private GameElement point2cellValue(Point2D point) {
        return model.cellValues[(int) point.getY()][(int) point.getX()];
    }

    private void executeBot() {
        switch (data.botlevel) {

            case RANDOME:

                Card card = getRandomCard() ;

                Point2D randomPoint = getRandomPoint();
                while(isOccupied(randomPoint))
                    randomPoint = getRandomPoint();

                addToBoard(card , randomPoint);
                break;

            case MEDIUM:

            case HARD:
        }
    }

    private Card getRandomCard() {
        ArrayList<GameElement> elements = data.botDeck;
        Random rnd = new Random();
        int x = rnd.nextInt(elements.size());
        while(elements.get(x) instanceof Tower) {
            x = rnd.nextInt(elements.size());
        }
        return (Card) elements.get(x);
    }

    private Point2D getRandomPoint() {
        Random rnd = new Random() ;
        int x = rnd.nextInt(18);
        while(x < 2){
            x = rnd.nextInt(18);
        }
        int y = rnd.nextInt(10);
        while(y < 2){
            y = rnd.nextInt(10);
        }

        return new Point2D(x , y) ;
    }


    private void updateCards() {

        speedCounter++ ;
        for (GameElement m : data.boardElements) {
            if (m instanceof Card) {
                
                if(m instanceof Troop) {
                    troopLogic((Troop) m) ;
                    }

                else if(m instanceof Spell){
                    switch (m.getValue()){
                        case RAGE:
                        case FIREBALL:
                    }
                }
                else if(m instanceof Building){
                    switch (m.getValue()){
                        case CANNON:
                        case INFERNO:
                    }
                }
                
            } else {

            }

        }

    }

    private void troopLogic(Troop m) {
        if(m instanceof Archer) {
            GameElement target = findCardInRang(m);
            if (target != null)
                shootTarget(m, target);
            else {
                if (moveToBridge(m))
                    moveToTower(m);
            }
        }
        else if(m instanceof Giant) {
            GameElement target = findCardInRang(m);
            if (target != null)
                shootTarget(m, target);
            else
                if (moveToBridge(m))
                    moveToTower(m);

        }
        else{
            if (moveToBridge(m))
                moveToTower(m);

        }

    }

    private void shootTarget(Troop m, GameElement target) {
        if(m.isAllowedToHit()){
            boolean flag = false ;
            if(target instanceof Tower){
                ((Tower) target).decreaseHitPoint(m.getDamage());
                flag = true;
            }
            else if( target instanceof Troop) {
                ((Troop) target).decreaseHitPoint(m.getDamage());
                flag = true;
            }

            else if( target instanceof Building){
                ((Building) target).decreaseHitPoint(m.getDamage());
                flag = true ;
            }
            if(flag)
               addToVectorMap(m , target);

        }
    }


    private boolean moveToBridge(Troop card) {
        if (isBotElement(card) && card.getPoint().getY() >= 10)
            return true;
        else if (isPlayerElement(card) && card.getPoint().getY() <= 10)
            return true;

        else {

            Point2D point = card.getPoint();
            if (point.distance(data.leftBridge) >= point.distance(data.rightBridge)) {
                Point2D vector = data.rightBridge.subtract(card.getPoint());
                if (Math.abs(vector.getX()) <= Math.abs(vector.getY()))
                    moveCard(card, data.rightBridge);
                else
                    moveCard(card, card.getPoint().add(1.0, 0));
            } else {
                Point2D vector = data.leftBridge.subtract(card.getPoint());
                if (Math.abs(vector.getX()) <= Math.abs(vector.getY()))
                    moveCard(card, data.leftBridge);
                else
                    moveCard(card, card.getPoint().add(-1, 0));
            }
            return false;
        }
    }

    private void moveCard(Troop movingCard, Point2D point) {
        if(!canMove(movingCard))
            return ;
        Point2D cardPoint = movingCard.getPoint();
        int a1 = (int) (point.getX() - cardPoint.getX());
        int a2 = (int) (point.getY() - cardPoint.getY());

        int a3 = (a1 == 0) ? 0 : Math.abs(a1) / a1;
        int a4 = (a2 == 0) ? 0 : Math.abs(a2) / a2;

        Point2D targetPoint = new Point2D(cardPoint.getX() + a3, cardPoint.getY() + a4);

        if (!isOccupied(targetPoint))
            movingCard.setPoint(targetPoint);
        else {
            if (isInNeighbourhood(cardPoint, point))
                return;
            if (a3 != 0) {
                targetPoint = targetPoint.add(0, -a4);
                if (!isOccupied(targetPoint))
                    movingCard.setPoint(targetPoint);
                else {
                    if (a4 != 0) {
                        targetPoint = targetPoint.add(-a3, a4);
                        if (!isOccupied(targetPoint))
                            movingCard.setPoint(targetPoint);
                    } else {

                    }
                }

            } else {
                if (!isOccupied(targetPoint.add(-1, 0)))
                    movingCard.setPoint(targetPoint.add(-1, 0));
                else if (!isOccupied(targetPoint.add(1, 0)))
                    movingCard.setPoint(targetPoint.add(1, 0));
            }

        }
    }

    private boolean canMove(Troop movingCard) {

        switch(movingCard.getSpeed()){
            case MEDIUM:
                return speedCounter % 3 == 0;
            case FAST:
                return speedCounter % 2 == 0;
            case SLOW:
                return speedCounter % 5 == 0;
        }
        return false ;
    }

    private void updateBoard() {
        model.cellValues = new GameElement[model.rowCount][model.columnCount];
        ArrayList<GameElement> deadPlayers = new ArrayList<>() ;
        for (GameElement ele : data.boardElements) {
            if (ele.isDead())
                deadPlayers.add(ele) ;
            model.cellValues[(int) ele.getPoint().getY()][(int) ele.getPoint().getX()] = ele;
        }
        for(GameElement i : deadPlayers)
            deletFromBoard(i);
    }

    private boolean isBotElement(GameElement card) {
        return data.botDeck.contains(card);
    }

    private boolean isPlayerElement(GameElement card) {
        return data.playerDeck.contains(card);
    }

    private boolean isOccupied(Point2D point) {
        for (GameElement i : data.boardElements) {
            if (point.equals(i.getPoint()))
                return true;
        }
        return false;
    }

    private GameElement findCardInRang(GameElement card){
        int minimumRang = 100; 
        GameElement result = null; 
        for(GameElement i : data.boardElements){
            if(!isOpposing(i , card))
                continue ; 
                if(i.getPoint().distance(card.getPoint()) < card.getRange() && isTargetApproved(i , card)) {
                    if(card.getRange() < minimumRang)
                    {minimumRang = card.getRange(); 
                    result = i;
                    if(card instanceof Giant)
                        System.out.println("Giant target selected -> " + i.getPoint() + i.getValue());}
                }
        }
        return result ;
    }

    private boolean isTargetApproved(GameElement target, GameElement attacker) {
        if(attacker instanceof Tower)
            return true ;
        else if(attacker instanceof Troop){
            switch (((Troop) attacker).getTarget())
            {
                case ANY:
                    return true ;
                case GROUND:
                    return ! (target instanceof BabyDragon) ;
                case BUILDING:
                    return (target instanceof Building || target instanceof Tower);
            }
        }
        else if(attacker instanceof Building){
            switch(((Building) attacker).getTarget()){
                case GROUND :
                    return ! (target instanceof BabyDragon) ;
                case ANY:
                    return true ;
            }
        }
        return false ;
    }

    private boolean moveToTower(Troop card) {
        double closestDistance = 100;
        Point2D towerPoint = new Point2D(0, 0);
        for (GameElement i : data.boardElements) {
            if (!(i instanceof Tower))
                continue;
            if (i.getPoint().distance(card.getPoint()) < closestDistance) {
                if (isPlayerElement(card) && isBotElement(i) || isPlayerElement(i) && isBotElement(card)) {
                    closestDistance = i.getPoint().distance(card.getPoint());
                    towerPoint = i.getPoint();
                }
            }
        }
        Point2D point = card.getPoint();

        if (isInNeighbourhood(point, towerPoint))
            return true;

        moveCard(card, towerPoint);
        return false;
    }

    private boolean isInNeighbourhood(Point2D point_1, Point2D point_2) {
        return (Math.abs(point_1.getX() - point_2.getX()) <= 1 && Math.abs(point_1.getY() - point_2.getY()) <= 1);
    }

    public void setCurrPoint(Point2D currPoint) {
//        if(!(currPoint.getY()>=11 && currPoint.getY()<=17))
//            return ;
        if (currCard == null) {
            System.out.println("can't set current point -> current card is null");
            //return;
        }
        else {

            this.currPoint = currPoint;

            //listArmy.getSelectionModel().select(null);

        }


    }

    public void setCurrCard(Card currCard) {
        this.currCard = currCard;

    }
    private boolean isOpposing(GameElement element_1 , GameElement element_2) {
        if(isPlayerElement(element_1) && isBotElement(element_2))
            return true ; 
        else return isBotElement(element_1) && isPlayerElement(element_2); 
    }

    private void addToBoard(GameElement card , Point2D point) {
        if (data.boardElements.contains(card)) {
            Card newCard = CardFactory.makeCard(card.getValue(), DataHandler.getLevel());
            newCard.setPoint(point);
            data.boardElements.add(newCard);
            if(isPlayerElement(card))
                data.playerDeck.add(newCard);
            else
                data.botDeck.add(newCard);
        } else {
            data.boardElements.add(card);
            card.setPoint(point);
        }
    }
    private void deletFromBoard(GameElement card){
        if(isPlayerElement(card))
            data.playerDeck.remove(card);
        else
            data.botDeck.remove(card);

        data.boardElements.remove(card);
    }
    private void addToVectorMap(GameElement element_1 , GameElement element_2){
        ArrayList<GameElement> arr = new ArrayList<>();
        arr.add(element_2);
        addToVectorMap(element_1 , arr);
    }
    private void addToVectorMap(GameElement element , ArrayList<GameElement> elementsArr){
        ArrayList<Point2D> pointsArr = new ArrayList<>() ;
        for(GameElement ele : elementsArr)
            pointsArr.add(ele.getPoint());

        model.vectorMap.put(element , pointsArr);
    }

}
