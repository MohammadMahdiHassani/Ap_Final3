package model.cards.levelEnums;

public enum ArcherLevel {

    LEVEL_1(33, 125),
    LEVEL_2(44, 127),
    LEVEL_3(48, 151),
    LEVEL_4(53, 166),
    LEVEL_5(58, 182);

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
