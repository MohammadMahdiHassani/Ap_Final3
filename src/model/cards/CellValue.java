package model.cards;

import javafx.scene.image.Image;

public enum CellValue {

    BRIDGE(new Image("/view/photos/BackGround_Arena/bridge.png") , new Image("/view/photos/BackGround_Arena/bridge.png")),
    HROAD(new Image("/view/photos/BackGround_Arena/roadTexture_13.png") , new Image("/view/photos/BackGround_Arena/roadTexture_13.png")),
    GIANT( new Image("/view/photos/giant.png"),  new Image("/view/photos/giant/myGiant_walk.png")),
    ARCHER(new Image("/view/photos/archers.png") , new Image ("/view/photos/archer/myArcher_walk.png")) ,
    BARBERIAN(new Image("/view/photos/barbarians.png") , new Image ("/view/photos/barbarian/myBarbarian_walk.png")),
    WIZARD(new Image("/view/photos/wizard.png") , new Image("/view/photos/wizard/myWizard_walk.png")),
    MINI_PEKA(new Image("/view/photos/mini_pekka.png") , new Image("/view/photos/mini_pekka/myMinipekka_walk.png")),
    RAGE (new Image("/view/photos/rage.png") , new Image("/view/photos/rage.png")),
    ARROWS (new Image("/view/photos/arrows.png") , new Image("/view/photos/arrows.png")),
    FIREBALL (new Image("/view/photos/fire_fireball.png") , new Image("/view/photos/fire_fireball.png")),
    BABY_DRAGON (new Image("/view/photos/baby_dragon.png") , new Image("/view/photos/baby_dragon/myBabydragon_walk.png") ),
    VALKYRIE (new Image("view/photos/valkyrie.png") , new Image("view/photos/valkyrie/myValkyrie_walk.png") ),
    CANNON(new Image("view/photos/chaos_cannon.png") , new Image("view/photos/building/cannon.png")),
    INFERNO(new Image("view/photos/inferno_tower.png") , new Image("view/photos/building/infernoTower.png")),
    GRASS(new Image("/view/photos/BackGround_Arena/grass.png") , new Image("/view/photos/BackGround_Arena/grass.png")),
    MYKINGTOWER(new Image("/view/photos/BackGround_Arena/king_tower.png") , new Image("/view/photos/tower/myKingtower.png")),
    MYARCHERTOWER(new Image("/view/photos/BackGround_Arena/archer_tower.png") , new Image("/view/photos/tower/myArchertower.png")),
    BOTKINGTOWER(new Image("/view/photos/BackGround_Arena/king_tower.png") , new Image("/view/photos/tower/botKingtower.png")),
    BOTARCHERTOWER(new Image("/view/photos/BackGround_Arena/archer_tower.png") , new Image("/view/photos/tower/botArchertower.png")),
    STONE(new Image("/view/photos/BackGround_Arena/stone.png") , new Image("/view/photos/BackGround_Arena/stone.png")),
    RIVER(new Image("/view/photos/BackGround_Arena/river2.png") , new Image("/view/photos/BackGround_Arena/river2.png")),
    ROAD(new Image("/view/photos/BackGround_Arena/roadTexture_01.png") , new Image("/view/photos/BackGround_Arena/roadTexture_01.png")),
    FENCE(new Image("/view/photos/BackGround_Arena/king_tower.png") , new Image("/view/photos/BackGround_Arena/king_tower.png")),
    SHRUB(new Image("/view/photos/BackGround_Arena/shrub.png") , new Image("/view/photos/BackGround_Arena/shrub.png")),
    TREE(new Image("/view/photos/BackGround_Arena/tree.png") , new Image("/view/photos/BackGround_Arena/tree.png")),
    HOME(new Image("/view/photos/BackGround_Arena/home.png") , new Image("/view/photos/BackGround_Arena/home.png")),
    TIME(new Image("/view/photos/BackGround_Arena/timeBackground1.jpg"),new Image("/view/photos/BackGround_Arena/timeBackground.jpg")),
    B_CROWN(new Image("/view/photos/BackGround_Arena/blueCrown.png"),new Image("/view/photos/BackGround_Arena/blueCrown.png")),
    R_CROWN(new Image("/view/photos/BackGround_Arena/redCrown.png"),new Image("/view/photos/BackGround_Arena/redCrown.png")),
    POINT(new Image("/view/photos/BackGround_Arena/timeBackground1.jpg"),new Image("/view/photos/BackGround_Arena/timeBackground1.jpg")),
    DAMAGE(new Image("/view/photos/tower/damageTower.png"),new Image("/view/photos/tower/damageTower.png")),
    EMPTY(null , null) ;
    private Image thumbnailImage ;
    private Image myWalk;
    private Image myAttack;
    private Image botWalk;
    private Image botAttack;


    CellValue(Image image_1, Image image_2){
        thumbnailImage = image_1 ;
        myWalk = image_2 ;
    }

    public Image getThumbnailImage() {
        return thumbnailImage;
    }

    public Image getMyWalk() {
        return myWalk;
    }
}
