package model.cards.troops;

import com.company.model.cards.troops.enums.Level;
import com.company.model.cards.troops.enums.Speed;
import com.company.model.cards.troops.enums.Target;

public class Troop {
    private String image;
    private String name;

    public Troop(String image, String name)
    {
        this.image = image;
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Level level ;

    private int hitPoint ;
    private int damage ;
    private Speed speed ;
    private int hitSpeed ;
    private Target target ;
    private int cost ;
    private int count ;

    @Override
    public String toString()
    {
        return name;
    }

}
