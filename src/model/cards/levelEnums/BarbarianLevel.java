package model.cards.levelEnums;

import java.io.Serializable;

public enum BarbarianLevel implements Serializable {

        LEVEL_1(33, 300),
        LEVEL_2(44, 330),
        LEVEL_3(90, 363),
        LEVEL_4(99, 438),
        LEVEL_5(109, 480);

        private int damage;
        private int hitPoint;

        private BarbarianLevel(int damage, int hitPoint) {
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

