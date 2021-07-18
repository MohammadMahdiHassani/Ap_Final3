package model.cards.levelEnums;

import java.io.Serializable;

public enum InfernoLevel implements Serializable {
    LEVEL_1(20, 400 , 800),
    LEVEL_2(22, 440 ,880),
    LEVEL_3(24, 484 ,968),
    LEVEL_4(26, 532 ,1064),
    LEVEL_5(29, 584 ,1158);

    private int minDamage;
    private int maxDamage;
    private int hitPoint;

    private InfernoLevel(int minDamage ,int maxDamage , int hitPoint) {
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.hitPoint = hitPoint;
    }

    public int getMinDamage() {
        return minDamage;
    }

    public int getMaxDamage() {
        return maxDamage;
    }

    public int getHitPoint() {
        return hitPoint;
    }
}