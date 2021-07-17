package model.game;

import DataBase.DataHandler;
import controller.LoginController;
import controller.MenuController;
import javafx.geometry.Point2D;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import model.GameElement;
import model.cards.Card;
import model.cards.CardFactory;
import model.cards.buildings.Building;
import model.cards.levelEnums.Level;
import model.cards.spells.Arrows;
import model.cards.spells.FireBall;
import model.cards.spells.Rage;
import model.cards.spells.Spell;
import model.cards.troops.*;
import model.towers.ArcherTower;
import model.towers.KingTower;
import model.towers.Tower;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Random;

public class GameLogic {
    private int speedCounter;
    private final ArenaModel model;
    public GameData data;
    private Point2D currPoint;
    private Card currCard;
    boolean playerMoved;
    private BotLogic botLogic ;

    public GameLogic(ArenaModel model) {
        this.model = model ;
        this.data = model.getGameData() ;
        playerMoved = false;
        speedCounter = 0;
        botLogic = new BotLogic(this);
    }


    public GameData getData() {
        return data;
    }

    public void executeLogic() {
        model.vectorMap = new HashMap<>();
        checkForPlayerMove();
        updateCards();
        updateBoard();
        if (!MenuController.isOnServer)
            executeBot();

        botLogic.executeBot();

    }

    private void checkForPlayerMove() {
        if (currPoint != null && currCard != null) {
            if (point2cellValue(currPoint) == null) {
                GameElement ele = addToBoard(currCard, currPoint);
                data.playerDeck.add(ele) ;
                playerMoved = true;
                currCard = null;
                currPoint = null;
                checkForSpawnedPlayers(ele);
            }
        }
    }

    private GameElement point2cellValue(Point2D point) {
        return model.cellValues[(int) point.getY()][(int) point.getX()];
    }

    private void executeBot() {
        switch (data.botlevel) {

            case RANDOME:
                if(!playerMoved)
                    return ;
                playerMoved = false ;

                Card card = getRandomCard();

                Point2D randomPoint = getRandomPoint();
                while (isOccupied(randomPoint))
                    randomPoint = getRandomPoint();
                data.botDeck.add(addToBoard(card, randomPoint));
                checkForSpawnedPlayers(card);

                sound(card);
                break;

            case MEDIUM:
            case HARD:

        }
    }

    Card getRandomCard() {
        ArrayList<GameElement> elements = data.botGenesis;
        Random rnd = new Random();
        int x = rnd.nextInt(elements.size());
        while (elements.get(x) instanceof Tower) {
            x = rnd.nextInt(elements.size());
        }
        return (Card) elements.get(x);
    }

    Point2D getRandomPoint() {
        Random rnd = new Random();
        int x = rnd.nextInt(18);
        while (x < 2) {
            x = rnd.nextInt(18);
        }
        int y = rnd.nextInt(10);
        while (y < 2) {
            y = rnd.nextInt(10);
        }

        return new Point2D(x, y);
    }

    private void updateCards() {

        speedCounter++;
        for (GameElement m : data.boardElements) {
            if (m instanceof Card) {

                if (m instanceof Troop) {
                    troopLogic((Troop) m);
                } else if (m instanceof Spell) {
                    spellLogic(m);
                } else if (m instanceof Building) {
                    buildingLogic((Building) m);
                }

            } else {

                towerLogic((Tower) m) ;

            }

        }

    }

    public void checkForSpawnedPlayers(GameElement m) {
        if(m instanceof Troop && ((Troop) m).getCount() != 1 && !((Troop) m).isSpawned())
            troopSpawn((Troop) m);
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

    private void spellLogic(GameElement m) {
        if(m instanceof Rage) {
            if (!((Rage) m).isTimerStarted())
                ((Rage) m).startTimer();

            ArrayList<GameElement> targetList = new ArrayList<>() ;
            targetList.add(m) ;
            addToVectorMap(m , targetList);
        }
        ArrayList<GameElement> target = findCardInRang(m) ;
        if (target.size() != 0)
            shootTarget(m, target);

        if(!(m instanceof Rage))
            m.killCard();
    }

    private void towerLogic(Tower m) {

        if(m instanceof KingTower)
            if(!isKingHit(m) && !isArcherTowerDestroyed(m))
             return ;

        ArrayList<GameElement> target = findCardInRang(m);
        if (target.size() != 0)
            shootTarget(m, target);
    }

    private boolean isArcherTowerDestroyed(Tower m) {
        ArrayList<GameElement> searchArr ;
        if(isBotElement(m))
            searchArr = data.botDeck ;
        else
            searchArr = data.playerDeck ;

        int counter = 0 ;
        for(GameElement i : searchArr){
            if(i instanceof ArcherTower)
                counter++ ;
        }
        return counter != 2 ;

    }

    public void sound(GameElement card) {
        if (card.getValue().toString().equals("ARCHER")) {
            LoginController.sound.playMain("ARCHER_ATTACK");
        } else if (card.getValue().toString().equals("GIANT")) {
            LoginController.sound.playMain("GIANT_ATTACK");
        } else if (card.getValue().toString().equals("BARBERIAN")) {
            LoginController.sound.playMain("BARBARIAN_ATTACK");
        } else if (card.getValue().toString().equals("CANNON")) {
            LoginController.sound.playMain("CANNON_ATTACK");
        } else if (card.getValue().toString().equals("INFERNO")) {
            LoginController.sound.playMain("INFERNO_ATTACK");
        } else if (card.getValue().toString().equals("VALKYRIE")) {
            LoginController.sound.playMain("VALKYRIE_ATTACK");
        } else if (card.getValue().toString().equals("MINI_PEKA")) {
            LoginController.sound.playMain("MINIPEKKA_ATTACK");
        } else if (card.getValue().toString().equals("BABY_DRAGON")) {
            LoginController.sound.playMain("BABYDRAGON_ATTACK");
        } else if (card.getValue().toString().equals("WIZARD")) {
            LoginController.sound.playMain("WIZARD_ATTACK");
        } else if (card.getValue().toString().equals("KINGTOWER")) {
            LoginController.sound.playMain("KING_ATTACK");
        } else if (card.getValue().toString().equals("ARCHERTOWER")) {
            LoginController.sound.playMain("ARCHERTOWER_ATTACK");
        }
    }

    private boolean isKingHit(Tower m) {
        return m.getHitPoint() < ((KingTower) m).getLevelHitPoint();
    }

    private void buildingLogic(Building m) {
        if(!m.isTimerStarted())
            m.startTimer();
        switch (m.getValue()) {
            case CANNON:
                ArrayList<GameElement> target = findCardInRang(m);
                if (target.size() != 0)
                    shootTarget(m, target);
            case INFERNO:
        }
    }

    private void troopLogic(Troop m) {
        ArrayList<GameElement> target = findCardInRang(m);
        if (target.size() != 0)
            shootTarget(m, target);
        else if (moveToBridge(m))
            moveToTower(m);
    }

    private void troopSpawn(Troop troop){
        int count = troop.getCount() - 1;
        ArrayList<Troop> troops = new ArrayList<>() ;
        for(int i = 0 ; i < count ; i++)
            troops.add((Troop) CardFactory.makeCard(troop.getValue() , Level.LEVEL_1));

        for(Troop t : troops)
        {
            Point2D point ;
            if((point = findEmptyCell(troop.getPoint())) != null){
                if(isPlayerElement(troop))
                    data.playerDeck.add(t) ;
                else
                    data.botDeck.add(t) ;
                t.setPoint(point) ;
                t.isSpawned() ;
                data.boardElements.add(t) ;
            }

        }

    }

    protected Point2D findEmptyCell(Point2D point){

        for(int x = (int) (point.getX() - 1) ; x <= (int) point.getX() + 1 ; x ++){
            if(x < 0)
                continue ;
            for(int y = (int) (point.getY() - 1) ; y <= (int) point.getY() + 1 ; y ++){
                if(y < 0)
                    continue ;
                Point2D newPoint ;
                if(!isOccupied(newPoint = new Point2D(x , y)) && y != 10) {
                    return newPoint ;
                }
            }
        }
        return null ;
    }

    private void shootTarget(GameElement m, ArrayList<GameElement> targets) {
        if(m instanceof Spell){
            if(m instanceof Rage){
                ((Rage) m).executeRage(targets);
            }
            else if(m instanceof FireBall){

                ArrayList<GameElement> targetList = new ArrayList<>() ;
                for(GameElement target : targets) {
                    boolean flag = false ;
                    if (target instanceof Tower) {
                        ((Tower) target).decreaseHitPoint(((FireBall) m).getDamage());
                        flag = true;
                    } else if (target instanceof Troop) {
                        ((Troop) target).decreaseHitPoint(((FireBall) m).getDamage());
                        flag = true;
                    } else if (target instanceof Building) {
                        ((Building) target).decreaseHitPoint(((FireBall) m).getDamage());
                        flag = true;
                    }
                    if (flag)
                        targetList.add(target) ;
                }
                addToVectorMap(m , targetList);
            }
            else{
                ArrayList<GameElement> targetList = new ArrayList<>() ;
                for(GameElement target : targets) {
                    boolean flag = false ;
                    if (target instanceof Tower) {
                        ((Tower) target).decreaseHitPoint(((Arrows) m).getDamage());
                        flag = true;
                    } else if (target instanceof Troop) {
                        ((Troop) target).decreaseHitPoint(((Arrows) m).getDamage());
                        flag = true;
                    } else if (target instanceof Building) {
                        ((Building) target).decreaseHitPoint(((Arrows) m).getDamage());
                        flag = true;
                    }
                    if (flag)
                        targetList.add(target) ;
                }
                addToVectorMap(m , targetList);

            }
        }
        else if(m instanceof Building){
            if(!((Building) m).isAllowedToHit())
                return ;
            ArrayList<GameElement> targetList = new ArrayList<>() ;
            for(GameElement target : targets) {
                boolean flag = false ;
                if (target instanceof Tower) {
                    ((Tower) target).decreaseHitPoint(((Building) m).getDamage());
                    flag = true;
                } else if (target instanceof Troop) {
                    ((Troop) target).decreaseHitPoint(((Building) m).getDamage());
                    flag = true;
                } else if (target instanceof Building) {
                    ((Building) target).decreaseHitPoint(((Building) m).getDamage());
                    flag = true;
                }
                if (flag)
                    targetList.add(target) ;
            }
            sound(m);
            addToVectorMap(m, targetList);
        } else if (m instanceof Troop) {
            if (((Troop) m).isAllowedToHit()) {

                ArrayList<GameElement> targetList = new ArrayList<>();
                for (GameElement target : targets) {
                    boolean flag = false;
                    if (target instanceof Tower) {
                        ((Tower) target).decreaseHitPoint(((Troop) m).getDamage());
                        flag = true;
                    } else if (target instanceof Troop) {
                        ((Troop) target).decreaseHitPoint(((Troop) m).getDamage());
                        flag = true;
                    } else if (target instanceof Building) {
                        ((Building) target).decreaseHitPoint(((Troop) m).getDamage());
                        flag = true;
                    }
                    if (flag)
                        targetList.add(target);
                }
                sound(m);
                addToVectorMap(m, targetList);
            }
        }
        else if(m instanceof Tower){
            if(!((Tower) m).isAllowedToHit())
                return ;
            ArrayList<GameElement> targetList = new ArrayList<>() ;
            for(GameElement target : targets) {
                boolean flag = false ;
                if (target instanceof Tower) {
                    ((Tower) target).decreaseHitPoint(((Tower) m).getDamage());
                    flag = true;
                } else if (target instanceof Troop) {
                    ((Troop) target).decreaseHitPoint(((Tower) m).getDamage());
                    flag = true;
                } else if (target instanceof Building) {
                    ((Building) target).decreaseHitPoint(((Tower) m).getDamage());
                    flag = true;
                }
                if (flag)
                    targetList.add(target) ;
            }
            sound(m);
            addToVectorMap(m, targetList);
        }

    }

    private boolean moveToBridge(Troop card) {
        if (isBotElement(card) && card.getPoint().getY() >= 10) {
            System.out.println("connect to bridge");
            return true;
        }
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

    public boolean isBotElement(GameElement card) {
        return data.botDeck.contains(card);
    }

    public boolean isPlayerElement(GameElement card) {
        return data.playerDeck.contains(card);
    }

    public boolean isOccupied(Point2D point) {
        for (GameElement i : data.boardElements) {
            if (point.equals(i.getPoint()))
                return true;
        }
        return false;
    }

    private ArrayList<GameElement> findCardInRang(GameElement card){

        ArrayList<GameElement> result = new ArrayList<>() ;
        if(card instanceof Troop && ((Troop) card).isAreaSplash()) {

                for (GameElement i : data.boardElements) {
                    if (!isOpposing(i, card))
                        continue;

                    if (card.getRange() == 0) {
                        if (isInNeighbourhood(i.getPoint(), card.getPoint()) && isTargetApproved(i, card)) {
                            result.add(i);
                        }
                    } else {
                        if (i.getPoint().distance(card.getPoint()) < card.getRange() && isTargetApproved(i, card)) {
                            result.add(i);
                        }

                    }
                }
                if(card.getRange() != 0){
                    result = deployAreaSplashLogic(result , (Card) card);
                }
        }

        else if (card instanceof Spell) {

            for (GameElement i : data.boardElements) {
                if(card instanceof Rage)
                {
                    if (isOpposing(i, card))
                        continue;
                }
                else {
                    if (!isOpposing(i, card))
                        continue;
                }
                if (i.getPoint().distance(card.getPoint()) < card.getRange() && isTargetApproved(i, card)) {
                    result.add(i);
                }

            }
        }

        else {

                int minimumRang = 100;
                for (GameElement i : data.boardElements) {
                    if (!isOpposing(i, card))
                        continue;
                    if (card.getRange() != 0) {
                        if (i.getPoint().distance(card.getPoint()) < card.getRange() && isTargetApproved(i, card)) {
                            if (card.getRange() < minimumRang) {
                                minimumRang = card.getRange();
                                result.add(i);
                            }
                        }
                    } else {
                        if (isInNeighbourhood(i.getPoint(), card.getPoint()) && isTargetApproved(i, card)) {
                            if (card.getRange() < minimumRang) {
                                minimumRang = card.getRange();
                                result.add(i);
                            }
                        }
                    }
                }
            }
        return result ;
    }

    private ArrayList<GameElement> deployAreaSplashLogic(ArrayList<GameElement> result , Card card) {

        int right = 0 ;
        int left = 0 ;
        int up = 0 ;
        int down = 0 ;
        int x = (int) card.getPoint().getX() ;
        int y = (int) card.getPoint().getY() ;

        for(GameElement i : result){
            if(i.getPoint().getX() > x)
                right++ ;
            if(i.getPoint().getX() < x)
                left++;
            if(i.getPoint().getY() > y)
                down++ ;
            if(i.getPoint().getY() < y)
                up++ ;
        }

        int max = 0 ;
        if(right >= max)
            max = right ;
        if(left >= max)
            max = left ;
        if(up >= max)
            max = up ;
        if(down >= max)
            max = down ;

        ArrayList<GameElement> newArr = new ArrayList<>() ;

        if(max == down && max == up){
            if(isPlayerElement(card))
                up-- ;
            else
                down-- ;
        }

        for(GameElement i : result){
            if(max == down)
            {
                if(i.getPoint().getY() > y)
                    newArr.add(i) ;
            }
            else if(max == up)
            {
                if(i.getPoint().getY() < y)
                    newArr.add(i) ;

            }
            else if(max == right){
                 if(i.getPoint().getX() > x)
                     newArr.add(i) ;
            }
            else if(max == left){
                 if(i.getPoint().getX() < x)
                     newArr.add(i) ;
             }

        }
        return newArr;
    }

    private boolean isTargetApproved(GameElement target, GameElement attacker) {
        if(target instanceof Spell)
            return false ;
        else if(attacker instanceof Tower)
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
        else return attacker instanceof Spell;
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

    public boolean isInNeighbourhood(Point2D point_1, Point2D point_2) {
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

    public boolean isOpposing(GameElement element_1 , GameElement element_2) {
        if(isPlayerElement(element_1) && isBotElement(element_2))
            return true ;
        else return isBotElement(element_1) && isPlayerElement(element_2);
    }

    public Card addToBoard(GameElement card , Point2D point) {

            Card newCard = CardFactory.makeCard(card.getValue(), DataHandler.getLevel());
            newCard.setPoint(point);
            data.boardElements.add(newCard);

            return newCard ;

    }
    private void deletFromBoard(GameElement card){
        setScore(card);
        if(isPlayerElement(card))
            data.playerDeck.remove(card);
        else
            data.botDeck.remove(card);
        data.boardElements.remove(card);
    }
    private void setScore(GameElement element){
        if(element instanceof Tower){
            if(isPlayerElement(element))
            {
                if(element instanceof KingTower)
                    data.playerScore += 3;
                else
                    data.playerScore += 1;
            }
            else
            {
                if(element instanceof KingTower)
                    data.botScore += 3;
                else
                    data.botScore += 1;
            }
        }
    }
    private void addToVectorMap(GameElement element , ArrayList<GameElement> elementsArr){
        ArrayList<Point2D> pointsArr = new ArrayList<>() ;
        for(GameElement ele : elementsArr)
            pointsArr.add(ele.getPoint());

        model.vectorMap.put(element , pointsArr);
    }

    public boolean isKingDead(){
        int counter = 0 ;
        for(GameElement i : data.playerDeck)
            if(i instanceof KingTower)
                counter++ ;
        for(GameElement i : data.botDeck)
            if(i instanceof KingTower)
                counter++ ;

            return counter != 2 ;
    }
    public void endGameLogic(){
        boolean winner = false ;
        if(isKingDead()) {
            boolean flag = false;
            for (GameElement i : data.playerDeck)
                if (i instanceof KingTower)
                    flag = true;

            if (flag)winner = true ;

        }
        else
        {
            if(data.playerScore > data.botScore) winner = true ;
            else if(data.playerScore == data.botScore){
                int playerTowerHitPoint = 0 ;
                for(GameElement i : data.playerDeck)
                    if(i instanceof Tower)
                        playerTowerHitPoint += ((Tower) i).getHitPoint() ;

                    int botTowerHitPoint = 0 ;
                for(GameElement i : data.botDeck)
                    if(i instanceof Tower)
                        botTowerHitPoint += ((Tower) i).getHitPoint() ;

                if(playerTowerHitPoint >= botTowerHitPoint)
                winner = true ;
            }

        }

            setPlayerWon(winner);
            data.saveToHistory();
            data.saveData() ;
    }
    private void setPlayerWon(boolean isPlayerTheWinner){
        if (isPlayerTheWinner)
        {

            data.xpDealer(200); data.playerWon = true ;
        }
        else
        {
            data.xpDealer(70); data.playerWon = false ;
        }
    }
}
