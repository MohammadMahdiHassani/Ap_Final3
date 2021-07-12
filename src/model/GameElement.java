package model;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import model.cards.CellValue;

public abstract class GameElement {
    private CellValue value ;
    private Point2D point ;

    public GameElement(CellValue value) {
        this.value = value ;
    }

    public CellValue getValue() {
        return value;
    }

    public void setPoint(Point2D point) {
        this.point = point;
    }
    public void addPoint(Point2D point){
        this.point = this.point.add(point) ;
    }

    public Point2D getPoint() {
        return point;
    }
}

