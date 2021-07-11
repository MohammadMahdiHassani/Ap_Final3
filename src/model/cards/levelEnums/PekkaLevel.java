package model.cards.levelEnums;

public enum PekkaLevel {
    LEVEL_1(325, 600),
    LEVEL_2(357, 660),
    LEVEL_3(393, 726),
    LEVEL_4(432, 798),
    LEVEL_5(474, 876);

    private int damage;
    private int hitPoint;

    private PekkaLevel(int damage, int hitPoint) {
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