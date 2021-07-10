package model.cards.levelEnums;

public enum RageLevel {

    LEVEL_1(6),
    LEVEL_2(6.5f),
    LEVEL_3(7),
    LEVEL_4(7.5f),
    LEVEL_5(8);

    private float duration;

    private RageLevel(float damage) {
        this.duration = damage;

    }

    public float getDuration() {
        return duration;
    }


}
