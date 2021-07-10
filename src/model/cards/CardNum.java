package model.cards;

import javafx.scene.image.Image;

public enum CardNum {

    GIANT( new Image("/view/photos/giant.png"),  new Image("/view/photos/giant.png")),
    ARCHER(new Image("/view/photos/archers.png") , new Image ("/view/photos/archers.png")) ,
    BARBERIAN(new Image("/view/photos/barbarians.png") , new Image ("/view/photos/barbarians.png")),
    WIZARD(new Image("/view/photos/wizard.png") , new Image("/view/photos/wizard.png")),
    MINI_PEKA(new Image("/view/photos/mini_pekka.png") , new Image("/view/photos/mini_pekka.png")),
    RAGE (new Image("/view/photos/rage.png") , new Image("/view/photos/rage.png")),
    ARROWS (new Image("/view/photos/arrows.png") , new Image("/view/photos/arrows.png")),
    FIREBALL (new Image("view/photos/fire_fireball.png") , new Image("view/photos/fire_fireball.png")),
    BABY_DRAGON (new Image("/view/photos/baby_dragon.png") , new Image("/view/photos/baby_dragon.png") ),
    VALKYRIE (new Image("view/photos/valkyrie.png") , new Image("view/photos/valkyrie.png") ),
    CANNON(new Image("view/photos/chaos_cannon.png") , new Image("view/photos/chaos_cannon.png")),
    INFERNO(new Image("view/photos/inferno_tower.png") , new Image("view/photos/inferno_tower.png"));

    private Image thumbnailImage ;
    private Image actionImage ;

    private CardNum(Image image_1 , Image image_2){
        thumbnailImage = image_1 ;
        actionImage = image_2 ;
    }
}
