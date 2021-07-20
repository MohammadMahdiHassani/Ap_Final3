package model.cards;

import javafx.scene.image.Image;

public enum CellValue {

    BRIDGE(new Image("/view/photos/background/road.png"), new Image("/view/photos/background/river.png"), new Image("/view/photos/BackGround_Arena/bridge.png"), new Image("/view/photos/BackGround_Arena/bridge.png"), new Image("/view/photos/BackGround_Arena/bridge.png"), new Image("/view/photos/BackGround_Arena/bridge.png"), new Image("/view/photos/BackGround_Arena/bridge.png")),
    HROAD(new Image("/view/photos/backGround/road.png"), new Image("/view/photos/backGround/road5.png"), new Image("/view/photos/BackGround_Arena/bridge.png"), new Image("/view/photos/BackGround_Arena/bridge.png"), new Image("/view/photos/BackGround_Arena/bridge.png"), new Image("/view/photos/BackGround_Arena/bridge.png"), new Image("/view/photos/BackGround_Arena/bridge.png")),
    GIANT(new Image("/view/photos/giant.png"), new Image("/view/photos/giant/myGiant_walk.png"), new Image("/view/photos/giant/myGiant_attack.png"), new Image("/view/photos/giant/botGiant_walk.png"), new Image("/view/photos/giant/botGiant_attack.png"), new Image("/view/photos/giant/myGiant_walk2.png"), new Image("/view/photos/giant/botGiant_walk2.png")),
    ARCHER(new Image("/view/photos/archers.png"), new Image("/view/photos/archer/myArcher_walk.png"), new Image("/view/photos/archer/myArcher_attack.png"), new Image("/view/photos/archer/botArcher_walk.png"), new Image("/view/photos/archer/botArcher_attack.png"), new Image("/view/photos/archer/myArcher_walk2.png"), new Image("/view/photos/archer/botArcher_walk2.png")),
    BARBERIAN(new Image("/view/photos/barbarians.png"), new Image("/view/photos/barbarian/myBarbarian_walk.png"), new Image("/view/photos/barbarian/myBarbarian_attack.png"), new Image("/view/photos/barbarian/botBarbarian_walk.png"), new Image("/view/photos/barbarian/botBarbarian_attack.png"), new Image("/view/photos/barbarian/myBarbarian_walk2.png"), new Image("/view/photos/barbarian/botBarbarian_walk2.png")),
    WIZARD(new Image("/view/photos/wizard.png"), new Image("/view/photos/wizard/myWizard_walk.png"), new Image("/view/photos/wizard/myWizard_attack.png"), new Image("/view/photos/wizard/botWizard_walk.png"), new Image("/view/photos/wizard/botWizard_attack.png"), new Image("/view/photos/wizard/myWizard_walk2.png"), new Image("/view/photos/wizard/botWizard_walk2.png")),
    MINI_PEKA(new Image("/view/photos/mini_pekka.png"), new Image("/view/photos/mini_pekka/myMinipekka_walk.png"), new Image("/view/photos/mini_pekka/myMinipekka_attack.png"), new Image("/view/photos/mini_pekka/botMinipekka_walk.png"), new Image("/view/photos/mini_pekka/botMinipekka_attack.png"), new Image("/view/photos/mini_pekka/myMinipekka_walk2.png"), new Image("/view/photos/mini_pekka/botMinipekka_walk2.png")),
    RAGE(new Image("/view/photos/rage.png"), new Image("/view/photos/rage.png"), new Image("/view/photos/rage.png"), new Image("/view/photos/rage.png"), new Image("/view/photos/rage.png"), new Image("/view/photos/rage.png"), new Image("/view/photos/rage.png")),
    ARROWS(new Image("/view/photos/arrows.png"), new Image("/view/photos/arrows.png"), new Image("/view/photos/arrows.png"), new Image("/view/photos/arrows.png"), new Image("/view/photos/arrows.png"), new Image("/view/photos/arrows.png"), new Image("/view/photos/arrows.png")),
    FIREBALL(new Image("/view/photos/fire_fireball.png"), new Image("/view/photos/fire_fireball.png"), new Image("/view/photos/fire_fireball.png"), new Image("/view/photos/fire_fireball.png"), new Image("/view/photos/fire_fireball.png"), new Image("/view/photos/fire_fireball.png"), new Image("/view/photos/fire_fireball.png")),
    BABY_DRAGON(new Image("/view/photos/baby_dragon.png"), new Image("/view/photos/baby_dragon/myBabydragon_walk.png"), new Image("/view/photos/baby_dragon/myBabydragon_walk.png"), new Image("/view/photos/baby_dragon/botBabydragon_walk.png"), new Image("/view/photos/baby_dragon/botBabydragon_walk.png"), new Image("/view/photos/baby_dragon/myBabydragon_walk2.png"), new Image("/view/photos/baby_dragon/botBabydragon_walk2.png")),
    VALKYRIE(new Image("view/photos/valkyrie.png"), new Image("view/photos/valkyrie/myValkyrie_walk.png"), new Image("view/photos/valkyrie/myValkyrie_attack.png"), new Image("view/photos/valkyrie/botValkyrie_walk.png"), new Image("view/photos/valkyrie/botValkyrie_attack.png"), new Image("view/photos/valkyrie/myValkyrie_walk2.png"), new Image("view/photos/valkyrie/botValkyrie_walk2.png")),
    CANNON(new Image("view/photos/chaos_cannon.png"), new Image("view/photos/building/cannon.png"), new Image("view/photos/building/cannon.png"), new Image("view/photos/building/cannon.png"), new Image("view/photos/building/cannon.png"), new Image("view/photos/building/cannon.png"), new Image("view/photos/building/cannon.png")),
    INFERNO(new Image("view/photos/inferno_tower.png"), new Image("view/photos/building/infernoTower.png"), new Image("view/photos/building/infernoTower.png"), new Image("view/photos/building/infernoTower.png"), new Image("view/photos/building/infernoTower.png"), new Image("view/photos/building/infernoTower.png"), new Image("view/photos/building/infernoTower.png")),
    GRASS(new Image("/view/photos/background/road5.png"), new Image("/view/photos/background/road4.png"), new Image("/view/photos/BackGround_Arena/grass.png"), new Image("/view/photos/BackGround_Arena/grass.png"), new Image("/view/photos/BackGround_Arena/grass.png"), new Image("/view/photos/BackGround_Arena/grass.png"), new Image("/view/photos/BackGround_Arena/grass.png")),
    MYKINGTOWER(new Image("/view/photos/BackGround_Arena/king_tower.png"), new Image("/view/photos/tower/myKingtower.png"), new Image("/view/photos/tower/myKingtower.png"), new Image("/view/photos/tower/myKingtower.png"), new Image("/view/photos/tower/myKingtower.png"), new Image("/view/photos/tower/myKingtower.png"), new Image("/view/photos/tower/myKingtower.png")),
    MYARCHERTOWER(new Image("/view/photos/BackGround_Arena/archer_tower.png"), new Image("/view/photos/tower/myArchertower.png"), new Image("/view/photos/tower/myArchertower.png"), new Image("/view/photos/tower/myArchertower.png"), new Image("/view/photos/tower/myArchertower.png"), new Image("/view/photos/tower/myArchertower.png"), new Image("/view/photos/tower/myArchertower.png")),
    BOTKINGTOWER(new Image("/view/photos/BackGround_Arena/king_tower.png"), new Image("/view/photos/tower/botKingtower.png"), new Image("/view/photos/tower/botKingtower.png"), new Image("/view/photos/tower/botKingtower.png"), new Image("/view/photos/tower/botKingtower.png"), new Image("/view/photos/tower/botKingtower.png"), new Image("/view/photos/tower/botKingtower.png")),
    BOTARCHERTOWER(new Image("/view/photos/BackGround_Arena/archer_tower.png"), new Image("/view/photos/tower/botArchertower.png"), new Image("/view/photos/tower/botArchertower.png"), new Image("/view/photos/tower/botArchertower.png"), new Image("/view/photos/tower/botArchertower.png"), new Image("/view/photos/tower/botArchertower.png"), new Image("/view/photos/tower/botArchertower.png")),
    STONE(new Image("/view/photos/BackGround_Arena/stone.png"), new Image("/view/photos/BackGround_Arena/stone.png"), new Image("/view/photos/BackGround_Arena/stone.png"), new Image("/view/photos/BackGround_Arena/stone.png"), new Image("/view/photos/BackGround_Arena/stone.png"), new Image("/view/photos/BackGround_Arena/stone.png"), new Image("/view/photos/BackGround_Arena/stone.png")),
    RIVER(new Image("/view/photos/backGround/river.png"), new Image("/view/photos/backGround/river.png"), new Image("/view/photos/BackGround_Arena/river2.png"), new Image("/view/photos/BackGround_Arena/river2.png"), new Image("/view/photos/BackGround_Arena/river2.png"), new Image("/view/photos/BackGround_Arena/river2.png"), new Image("/view/photos/BackGround_Arena/river2.png")),
    ROAD(new Image("/view/photos/background/road.png"), new Image("/view/photos/background/road5.png"), new Image("/view/photos/BackGround_Arena/roadTexture_01.png"), new Image("/view/photos/BackGround_Arena/roadTexture_01.png"), new Image("/view/photos/BackGround_Arena/roadTexture_01.png"), new Image("/view/photos/BackGround_Arena/roadTexture_01.png"), new Image("/view/photos/BackGround_Arena/roadTexture_01.png")),
    FENCE(new Image("/view/photos/BackGround_Arena/king_tower.png"), new Image("/view/photos/BackGround_Arena/king_tower.png"), new Image("/view/photos/BackGround_Arena/king_tower.png"), new Image("/view/photos/BackGround_Arena/king_tower.png"), new Image("/view/photos/BackGround_Arena/king_tower.png"), new Image("/view/photos/BackGround_Arena/king_tower.png"), new Image("/view/photos/BackGround_Arena/king_tower.png")),
    SHRUB(new Image("/view/photos/BackGround_Arena/shrub.png"), new Image("/view/photos/BackGround_Arena/shrub.png"), new Image("/view/photos/BackGround_Arena/shrub.png"), new Image("/view/photos/BackGround_Arena/shrub.png"), new Image("/view/photos/BackGround_Arena/shrub.png"), new Image("/view/photos/BackGround_Arena/shrub.png"), new Image("/view/photos/BackGround_Arena/shrub.png")),
    TREE(new Image("/view/photos/background/tree.png"), new Image("/view/photos/BackGround_Arena/tree.png"), new Image("/view/photos/BackGround_Arena/tree.png"), new Image("/view/photos/BackGround_Arena/tree.png"), new Image("/view/photos/BackGround_Arena/tree.png"), new Image("/view/photos/BackGround_Arena/tree.png"), new Image("/view/photos/BackGround_Arena/tree.png")),
    HOME(new Image("/view/photos/background/grass.png"), new Image("/view/photos/BackGround_Arena/home.png"), new Image("/view/photos/BackGround_Arena/home.png"), new Image("/view/photos/BackGround_Arena/home.png"), new Image("/view/photos/BackGround_Arena/home.png"), new Image("/view/photos/BackGround_Arena/home.png"), new Image("/view/photos/BackGround_Arena/home.png")),
    TIME(new Image("/view/photos/BackGround_Arena/timeBackground1.jpg"), new Image("/view/photos/BackGround_Arena/timeBackground.jpg"), new Image("/view/photos/BackGround_Arena/timeBackground.jpg"), new Image("/view/photos/BackGround_Arena/timeBackground.jpg"), new Image("/view/photos/BackGround_Arena/timeBackground.jpg"), new Image("/view/photos/BackGround_Arena/timeBackground.jpg"), new Image("/view/photos/BackGround_Arena/timeBackground.jpg")),
    B_CROWN(new Image("/view/photos/BackGround_Arena/blueCrown.png"), new Image("/view/photos/BackGround_Arena/blueCrown.png"), new Image("/view/photos/BackGround_Arena/blueCrown.png"), new Image("/view/photos/BackGround_Arena/blueCrown.png"), new Image("/view/photos/BackGround_Arena/blueCrown.png"), new Image("/view/photos/BackGround_Arena/blueCrown.png"), new Image("/view/photos/BackGround_Arena/blueCrown.png")),
    R_CROWN(new Image("/view/photos/BackGround_Arena/redCrown.png"), new Image("/view/photos/BackGround_Arena/redCrown.png"), new Image("/view/photos/BackGround_Arena/redCrown.png"), new Image("/view/photos/BackGround_Arena/redCrown.png"), new Image("/view/photos/BackGround_Arena/redCrown.png"), new Image("/view/photos/BackGround_Arena/redCrown.png"), new Image("/view/photos/BackGround_Arena/redCrown.png")),
    POINT(new Image("/view/photos/BackGround_Arena/timeBackground1.jpg"), new Image("/view/photos/BackGround_Arena/timeBackground1.jpg"), new Image("/view/photos/BackGround_Arena/timeBackground1.jpg"), new Image("/view/photos/tower/damageTower.png"), new Image("/view/photos/BackGround_Arena/timeBackground1.jpg"), new Image("/view/photos/BackGround_Arena/timeBackground1.jpg"), new Image("/view/photos/BackGround_Arena/timeBackground1.jpg")),
    DAMAGE(new Image("/view/photos/tower/damageTower.png"), new Image("/view/photos/tower/damageTower.png"), new Image("/view/photos/tower/damageTower.png"), new Image("/view/photos/tower/damageTower.png"), new Image("/view/photos/tower/damageTower.png"), new Image("/view/photos/tower/damageTower.png"), new Image("/view/photos/tower/damageTower.png")),
    GRASS2(new Image("/view/photos/background/grass.png"), new Image("/view/photos/background/grass.png"), new Image("/view/photos/BackGround_Arena/grass.png"), new Image("/view/photos/BackGround_Arena/grass.png"), new Image("/view/photos/BackGround_Arena/grass.png"), new Image("/view/photos/BackGround_Arena/grass.png"), new Image("/view/photos/BackGround_Arena/grass.png")),
    GRASS3(new Image("/view/photos/background/road5.png"), new Image("/view/photos/background/road.png"), new Image("/view/photos/BackGround_Arena/grass.png"), new Image("/view/photos/BackGround_Arena/grass.png"), new Image("/view/photos/BackGround_Arena/grass.png"), new Image("/view/photos/BackGround_Arena/grass.png"), new Image("/view/photos/BackGround_Arena/grass.png")),
    EMPTY(null, null, null, null, null, null, null);
    private Image thumbnailImage;
    private Image myWalk;
    private Image myAttack;
    private Image botWalk;
    private Image botAttack;
    private Image myWalk2;
    private Image botWalk2;


    CellValue(Image image_1, Image image_2, Image image_3, Image image_4, Image image_5, Image image_6, Image image_7) {
        thumbnailImage = image_1;
        myWalk = image_2;
        myAttack = image_3;
        botWalk = image_4;
        botAttack = image_5;
        myWalk2 = image_6;
        botWalk2 = image_7;
    }

    public Image getThumbnailImage() {
        return thumbnailImage;
    }

    public Image getMyWalk() {
        return myWalk;
    }

    public Image getMyAttack() {
        return myAttack;
    }

    public Image getBotWalk() {
        return botWalk;
    }

    public Image getBotAttack() {
        return botAttack;
    }

    public Image getMyWalk2() {
        return myWalk2;
    }

    public Image getBotWalk2() {
        return botWalk2;
    }
}
