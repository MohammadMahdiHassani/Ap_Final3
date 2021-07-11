package model.cards.levelEnums;

public enum CannonLevel {

    LEVEL_1(60, 380),
    LEVEL_2(66, 418),
    LEVEL_3(72, 459),
    LEVEL_4(79, 505),
    LEVEL_5(87, 554);

    private int damage;
    private int hitPoint;

    private CannonLevel(int damage, int hitPoint) {
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
