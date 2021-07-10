package model.cards.levelEnums;

public enum ArcherLevel {

    LEVEL_1(126, 2000),
    LEVEL_2(138, 2200),
    LEVEL_3(152, 2420),
    LEVEL_4(167, 2660),
    LEVEL_5(183, 2920);

    private int damage;
    private int hitPoint;

    private ArcherLevel(int damage, int hitPoint) {
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
