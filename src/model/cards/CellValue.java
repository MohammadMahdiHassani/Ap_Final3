package model.cards;

import javafx.scene.image.Image;

import java.io.Serializable;

public enum CellValue implements Serializable {

    GIANT( new Image("/view/photos/giant.png"),  new Image("/view/photos/giant.png")),
    ARCHER(new Image("/view/photos/archers.png") , new Image ("/view/photos/archers.png")) ,
    BARBERIAN(new Image("/view/photos/barbarians.png") , new Image ("/view/photos/barbarians.png")),
    WIZARD(new Image("/view/photos/wizard.png") , new Image("/view/photos/wizard.png")),
    MINI_PEKA(new Image("/view/photos/mini_pekka.png") , new Image("/view/photos/mini_pekka.png")),
    RAGE (new Image("/view/photos/rage.png") , new Image("/view/photos/rage.png")),
    ARROWS (new Image("/view/photos/arrows.png") , new Image("/view/photos/arrows.png")),
    FIREBALL (new Image("/view/photos/fire_fireball.png") , new Image("/view/photos/fire_fireball.png")),
    BABY_DRAGON (new Image("/view/photos/baby_dragon.png") , new Image("/view/photos/baby_dragon.png") ),
    VALKYRIE (new Image("view/photos/valkyrie.png") , new Image("view/photos/valkyrie.png") ),
    CANNON(new Image("view/photos/chaos_cannon.png") , new Image("view/photos/chaos_cannon.png")),
    INFERNO(new Image("view/photos/inferno_tower.png") , new Image("view/photos/inferno_tower.png")),
    GRASS(new Image("/view/photos/BackGround_Arena/grass.png") , new Image("/view/photos/BackGround_Arena/grass.png")),
    KINGTOWER(new Image("/view/photos/BackGround_Arena/king_tower.png") , new Image("/view/photos/BackGround_Arena/king_tower.png")),
    ARCHERTOWER(new Image("/view/photos/BackGround_Arena/archer_tower.png") , new Image("/view/photos/BackGround_Arena/archer_tower.png")),
    STONE(new Image("/view/photos/BackGround_Arena/stone.png") , new Image("/view/photos/BackGround_Arena/stone.png")),
    RIVER(new Image("/view/photos/BackGround_Arena/river.png") , new Image("/view/photos/BackGround_Arena/river.png")),
    ROAD(new Image("/view/photos/BackGround_Arena/road.png") , new Image("/view/photos/BackGround_Arena/road.png")),
    FENCE(new Image("/view/photos/BackGround_Arena/king_tower.png") , new Image("/view/photos/BackGround_Arena/king_tower.png")),
    SHRUB(new Image("/view/photos/BackGround_Arena/shrub.png") , new Image("/view/photos/BackGround_Arena/shrub.png")),
    TREE(new Image("/view/photos/BackGround_Arena/tree.png") , new Image("/view/photos/BackGround_Arena/tree.png")),
    HOME(new Image("/view/photos/BackGround_Arena/home.png") , new Image("/view/photos/BackGround_Arena/home.png")),
    TIME(new Image("/view/photos/BackGround_Arena/timeBackground1.jpg"),new Image("/view/photos/BackGround_Arena/timeBackground.jpg")),
    B_CROWN(new Image("/view/photos/BackGround_Arena/blueCrown.png"),new Image("/view/photos/BackGround_Arena/blueCrown.png")),
    R_CROWN(new Image("/view/photos/BackGround_Arena/redCrown.png"),new Image("/view/photos/BackGround_Arena/redCrown.png")),
    POINT(new Image("/view/photos/BackGround_Arena/timeBackground1.jpg"),new Image("/view/photos/BackGround_Arena/timeBackground1.jpg")),
    EMPTY(null , null) ;
    private Image thumbnailImage ;
    private Image actionImage ;

    CellValue(Image image_1, Image image_2){
        thumbnailImage = image_1 ;
        actionImage = image_2 ;
    }

    public Image getThumbnailImage() {
        return thumbnailImage;
    }

    public Image getActionImage() {
        return actionImage;
    }
}
