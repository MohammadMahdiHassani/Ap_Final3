package model.cards.levelEnums;

import java.io.Serializable;

public enum FireBallLevel implements Serializable {

    LEVEL_1(325),
    LEVEL_2(357),
    LEVEL_3(393),
    LEVEL_4(432),
    LEVEL_5(474);

    private int areaDamage;


    private FireBallLevel(int damage) {
        this.areaDamage = damage;
    }

    public int getDamage() {
        return areaDamage;
    }


}