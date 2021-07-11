package model.cards.levelEnums;

public enum WizardLevel {
    LEVEL_1(130, 340),
    LEVEL_2(143, 374),
    LEVEL_3(157, 411),
    LEVEL_4(172, 452),
    LEVEL_5(189, 496);

    private int damage;
    private int hitPoint;

    private WizardLevel(int damage, int hitPoint) {
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