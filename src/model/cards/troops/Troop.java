package model.cards.troops;
import javafx.scene.control.Cell;
import model.cards.CellValue;
import model.cards.troops.enums.Speed;
import model.cards.troops.enums.Target;
import javafx.scene.image.Image;
import model.cards.Card;


public abstract class Troop extends Card {


    private int hitPoint ;
    private int damage ;
    private Speed speed ;
    private float hitSpeed ;
    private Target target ;
    private int count ;


    public Troop( CellValue value , int hitPoint, int damage, Speed speed, float hitSpeed, Target target, int cost, int count ) {
        super(value , cost);
        this.hitPoint = hitPoint;
        this.damage = damage;
        this.speed = speed;
        this.hitSpeed = hitSpeed;
        this.target = target;
        this.count = count;
    }
}
