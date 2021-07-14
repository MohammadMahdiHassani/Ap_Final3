package model.cards.levelEnums;

import java.io.Serializable;

public enum ValkyrieLevel implements Serializable {
    LEVEL_1(120, 880),
    LEVEL_2(132, 968),
    LEVEL_3(145, 1064),
    LEVEL_4(159, 1170),
    LEVEL_5(175, 1284);

    private int damage;
    private int hitPoint;

    private ValkyrieLevel(int damage, int hitPoint) {
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