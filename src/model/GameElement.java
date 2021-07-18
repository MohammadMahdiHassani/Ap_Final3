package model;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import model.cards.CellValue;

import java.io.Serializable;

public abstract class GameElement implements Serializable {
    private CellValue value ;
    private Point2D point ;
    private int range ;
    private boolean isDead ;
    private boolean isAttack;



    public GameElement(CellValue value, int range) {
        this.value = value ;
        this.range = range ;
        isDead = false ;
        isAttack = false;
    }
    public boolean isAttack() {
        return isAttack;
    }

    public void setAttack(boolean attack) {
        isAttack = attack;
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

