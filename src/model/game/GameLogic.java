package model.game;

import javafx.geometry.Point2D;
import model.GameElement;
import model.cards.Card;
import model.cards.levelEnums.Botlevel;

public class GameLogic {
    private ArenaModel model ;
    private GameData data ;
    private Botlevel botlevel ;
    private Point2D currPoint ;
    private Card currCard ;
    private boolean playerMoved ;

    public GameLogic() {
        this.model = ArenaModel.getModel();
        data = model.gameData ;
        playerMoved = false ;
    }
    public void executeLogic(){

        checkForPlayerMove();
        updateBoardCards();
        if(playerMoved){
            executeBot() ;
        }
    }
    private void checkForPlayerMove(){
        if(currPoint != null && currCard != null){
            if(point2cellValue(currPoint) == null){
                currCard.setPoint(currPoint);
                playerMoved = true ;
                currCard = null ;
                currPoint = null ;
            }
        }
    }


    public void setBotlevel(Botlevel botlevel) {
        this.botlevel = botlevel;
    }
    private GameElement point2cellValue(Point2D point){
        return model.cellValues[(int)point.getY()][(int)point.getX()];
    }
    public void setCurrPoint(Point2D currPoint) {
        if(!(currPoint.getY()>=11 && currPoint.getY()<=17))
            return ;
        else if(currCard != null)
            return ;
        else
            this.currPoint = currPoint;
    }

    public void setCurrCard(Card currCard) {
        if(!data.playedCards.contains(currCard))
            this.currCard = currCard;
    }
    private void executeBot(){
        switch(botlevel){
            case RANDOME:

            case MEDIUM:

            case HARD:
        }
    }

    private void updateBoardCards(){


        for(GameElement m : data.playedCards){
            if(m instanceof Card){
//                Point2D mPoint = m.getPoint() ;
//                if(mPoint.getY() > 11){
//                    if(mPoint.getX())
//
//
//
//                }


            }
            else
            {

            }

        }










    }
}
