package model;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import model.cards.CellValue;

public abstract class GameElement {
    private CellValue value ;
    private Point2D point ;
    private int range ;
    private boolean isDead ;
    public GameElement(CellValue value, int range) {
        this.value = value ;
        this.range = range ;
        isDead = false ;
    }

    public CellValue getValue() {
        return value;
    }

    public void setPoint(Point2D point) {
        this.point = point;
    }
    public Point2D getPoint() {
        return point;
    }

    public int getRange() {
        return range;
    }
    public void killCard(){
        isDead = true ;
    }
    public boolean isDead(){
        return isDead ;
    }
}

