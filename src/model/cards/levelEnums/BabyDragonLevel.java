package model.cards.levelEnums;

import java.io.Serializable;

public enum BabyDragonLevel implements Serializable {
    LEVEL_1(100, 800),
    LEVEL_2(110, 880),
    LEVEL_3(121, 968),
    LEVEL_4(133, 1064),
    LEVEL_5(146, 1168);

    private int damage;
    private int hitPoint;

    private BabyDragonLevel(int damage, int hitPoint) {
        this.damage = damage;
        this.hitPoint = hitPoint;
    }

    public int getDamage() {
        return damage;
    }

    public int getHitPoint() {
        return hitPoint;
    }
}