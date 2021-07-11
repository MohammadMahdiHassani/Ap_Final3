package model.cards.levelEnums;

public enum ArrowsLevel {
    LEVEL_1(144),
    LEVEL_2(156),
    LEVEL_3(174),
    LEVEL_4(189),
    LEVEL_5(210);

    private int areaDamage;


    private ArrowsLevel(int damage) {
        this.areaDamage = damage;
    }

    public int getDamage() {
        return areaDamage;
    }
}