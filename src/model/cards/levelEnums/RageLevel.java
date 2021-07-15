package model.cards.levelEnums;

import java.io.Serializable;

public enum RageLevel implements Serializable {

    LEVEL_1(6),
    LEVEL_2(6.5f),
    LEVEL_3(7),
    LEVEL_4(7.5f),
    LEVEL_5(8);

    private float duration;

    private RageLevel(float duration) {
        this.duration = duration;

    }

    public float getDuration() {
        return duration;
    }


}
