package model.game;

import javafx.geometry.Point2D;
import model.GameElement;
import model.cards.Card;
import model.cards.levelEnums.Botlevel;
import model.towers.Tower;

public class GameLogic {
    private ArenaModel model ;
    private GameData data ;
    private Botlevel botlevel ;
    private Point2D currPoint ;
    private Card currCard ;
    private boolean playerMoved ;

    public GameLogic() {
        playerMoved = false ;
        botlevel = Botlevel.RANDOME ;
    }
    public void preprocessLogic(){
        model = ArenaModel.arenaModel;
        data = ArenaModel.arenaModel.gameData;
    }
    public void executeLogic(){

        checkForPlayerMove();
        updateCards();
        updateBoard();
        if(playerMoved){
            executeBot();
            playerMoved = false ;
        }
    }


    private void checkForPlayerMove(){
        if(currPoint != null && currCard != null){
            if(point2cellValue(currPoint) == null){
                currCard.setPoint(currPoint);
                data.boardElements.add(currCard);
                playerMoved = true ;
                currCard = null ;
                currPoint = null ;
            }
        }
    }


    private GameElement point2cellValue(Point2D point){
        return model.cellValues[(int)point.getY()][(int)point.getX()];
    }

    private void executeBot(){
        switch(botlevel){

            case RANDOME:

            case MEDIUM:

            case HARD:
        }
    }


    private void updateCards(){


        for(GameElement m : data.boardElements){
            if(m instanceof Card){

                switch(m.getValue()){
                    case GIANT:
                        giantLogic((Card) m);
                        break;
                    case ARCHER:
                        archerLogic((Card) m);
                        break;
                    case BARBERIAN:

                    case MINI_PEKA:
                    case WIZARD:
                    case VALKYRIE:
                    case CANNON:
                    case INFERNO:
                    case FIREBALL:
                    case RAGE:
                    case ARROWS:
                }

            }
            else
            {

            }

        }

    }
    private boolean moveToBridge(Card card) {
        if (isBotElement(card) && card.getPoint().getY() >= 10)
            return true;
        else if (isPlayerElement(card) && card.getPoint().getY() <= 10)
            return true;

        else {

            Point2D point = card.getPoint();
            if (point.distance(data.leftBridge) >= point.distance(data.rightBridge)) {
                Point2D vector = data.rightBridge.subtract(card.getPoint());
                if(Math.abs(vector.getX()) <= Math.abs(vector.getY()))
                    moveCard(card, data.rightBridge);
                else
                    moveCard(card , card.getPoint().add(1.0, 0));
            }
                else {
                Point2D vector = data.leftBridge.subtract(card.getPoint());
                if(Math.abs(vector.getX()) <= Math.abs(vector.getY()))
                    moveCard(card, data.leftBridge);
                else
                    moveCard(card , card.getPoint().add(-1 , 0));
            }
            return false;
        }
    }
    private void moveCard(Card movingCard , Point2D point){
        Point2D cardPoint = movingCard.getPoint() ;
        int a1 =(int)(point.getX() - cardPoint.getX()) ;
        int a2 =(int) (point.getY() - cardPoint.getY()) ;

        int a3 = (a1 == 0) ? 0 : Math.abs(a1)/a1 ;
        int a4 = (a2 == 0) ? 0 : Math.abs(a2)/a2 ;

        Point2D targetPoint = new Point2D(cardPoint.getX() + a3, cardPoint.getY() + a4) ;

        if(!isOccupied(targetPoint))
            movingCard.setPoint(targetPoint);
        else{
            if(a3 != 0) {
                if (!isOccupied(targetPoint.add(0, -a4)))
                    movingCard.setPoint(targetPoint.add(0, -a4));
                else
                {
                    if(a4 != 0){
                    if(!isOccupied(targetPoint.add(-a3,0) ))
                        movingCard.setPoint(targetPoint.add(-a3,0) );
                    }
                    else{

                    }
                }

            }
            else{
                if(!isOccupied(targetPoint.add(-1 , 0)))
                    movingCard.setPoint(targetPoint.add(-1 , 0));
                else if(!isOccupied(targetPoint.add(1 , 0)))
                    movingCard.setPoint(targetPoint.add(1 , 0));
            }

        }
    }
    private void updateBoard() {
        model.cellValues = new GameElement[model.rowCount][model.columnCount];
        for (GameElement i : data.boardElements) {
            model.cellValues[(int) i.getPoint().getY()][(int) i.getPoint().getX()] = i;
        }

    }
    private boolean isBotElement(GameElement card){
        return data.botDeck.contains(card);
    }
    private boolean isPlayerElement(GameElement card){
        return data.playerDeck.contains(card) ;
    }
    private void giantLogic(Card card){
        if(moveToBridge(card))
         moveToTower(card);
    }
    private void archerLogic(Card card){

        if(moveToBridge(card))
          moveToTower(card);

    }
    private boolean isOccupied(Point2D point){
        for(GameElement i : data.boardElements){
            if(point.equals(i.getPoint()))
                return true ;
        }
        return false ;
    }
    private void moveToTower(Card card){
        double closestDistance = 100 ;
        Point2D towerPoint = new Point2D(0,0);
        for(GameElement i : data.boardElements){
            if(!(i instanceof Tower))
                continue ;
            if(i.getPoint().distance(card.getPoint()) < closestDistance){
                if(isPlayerElement(card) && isBotElement(i) || isPlayerElement(i) && isBotElement(card)) {
                    closestDistance = i.getPoint().distance(card.getPoint()) ;
                    towerPoint = i.getPoint();
                }
            }
            moveCard(card , towerPoint);

        }
    }



    public void setBotlevel(Botlevel botlevel) {
        this.botlevel = botlevel;
    }
    public void setCurrPoint(Point2D currPoint) {
        if(!(currPoint.getY()>=11 && currPoint.getY()<=17))
            return ;
        else if(currCard == null)
            return ;
        else
            this.currPoint = currPoint;
    }
    public void setCurrCard(Card currCard) {
        if(!data.boardElements.contains(currCard))
            this.currCard = currCard;
    }





}
