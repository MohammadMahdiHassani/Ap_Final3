package model.cards.levelEnums;

import java.io.Serializable;

public enum InfernoLevel implements Serializable {
    LEVEL_1(20, 800),
    LEVEL_2(22, 880),
    LEVEL_3(24, 968),
    LEVEL_4(26, 1064),
    LEVEL_5(29, 1158);

    private int damage;
    private int hitPoint;

    private InfernoLevel(int damage, int hitPoint) {
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