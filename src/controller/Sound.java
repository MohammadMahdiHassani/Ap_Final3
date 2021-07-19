package controller;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;
import java.nio.file.Path;

public class Sound {
    private static Media media;
    private static MediaPlayer mediaPlayer;

    public void playMain(String sound) {
        if (sound.equals("LOGIN")) {
            media = new Media(getClass().getResource("/sound/login.wav").toExternalForm());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(10);
            mediaPlayer.play();
        } else if (sound.equals("MENU")) {
            media = new Media(getClass().getResource("/sound/menu.wav").toExternalForm());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(10);
            mediaPlayer.play();
        } else if (sound.equals("DECK")) {
            media = new Media(getClass().getResource("/sound/deck.wav").toExternalForm());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(10);
            mediaPlayer.play();
        } else if (sound.equals("CLICK")) {
            media = new Media(getClass().getResource("/sound/click2.wav").toExternalForm());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(1);
            mediaPlayer.play();
        } else if (sound.equals("LOADING")) {
            media = new Media(getClass().getResource("/sound/loading.wav").toExternalForm());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(1);
            mediaPlayer.play();
        } else if (sound.equals("GOTOARENA")) {
            media = new Media(getClass().getResource("/sound/goToArena.wav").toExternalForm());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(1);
            mediaPlayer.play();
        } else if (sound.equals("ARCHER_ATTACK")) {
            media = new Media(getClass().getResource("/sound/archer_attack.wav").toExternalForm());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(1);
            mediaPlayer.play();
        } else if (sound.equals("ARCHER_CHOOSE")) {
            media = new Media(getClass().getResource("/sound/archer_choose.wav").toExternalForm());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(1);
            mediaPlayer.play();
        } else if (sound.equals("CHOOSE_CART")) {
            media = new Media(getClass().getResource("/sound/choose_cart.wav").toExternalForm());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(1);
            mediaPlayer.play();
        } else if (sound.equals("ARROWS")) {
            media = new Media(getClass().getResource("/sound/arrows.wav").toExternalForm());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(1);
            mediaPlayer.play();
        } else if (sound.equals("ATTACK_BUTTON")) {
            media = new Media(getClass().getResource("/sound/attack_button.wav").toExternalForm());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(1);
            mediaPlayer.play();
        } else if (sound.equals("BABYDRAGON_ATTACK")) {
            media = new Media(getClass().getResource("/sound/babydragon_attack.wav").toExternalForm());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(1);
            mediaPlayer.play();
        } else if (sound.equals("BABYDRAGON_CHOOSE")) {
            media = new Media(getClass().getResource("/sound/babydragon_choose.wav").toExternalForm());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(1);
            mediaPlayer.play();
        } else if (sound.equals("BARBARIAN_ATTACK")) {
            media = new Media(getClass().getResource("/sound/barbarian_attack.wav").toExternalForm());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(1);
            mediaPlayer.play();
        } else if (sound.equals("BARBARIAN_CHOOSE")) {
            media = new Media(getClass().getResource("/sound/barbarian_choose.wav").toExternalForm());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(1);
            mediaPlayer.play();
        } else if (sound.equals("CANNON_ATTACK")) {
            media = new Media(getClass().getResource("/sound/cannon_attack.wav").toExternalForm());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(1);
            mediaPlayer.play();
        } else if (sound.equals("CANNON_CHOOSE")) {
            media = new Media(getClass().getResource("/sound/cannon_choose.wav").toExternalForm());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(1);
            mediaPlayer.play();
        } else if (sound.equals("CROWN")) {
            media = new Media(getClass().getResource("/sound/crown.wav").toExternalForm());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(1);
            mediaPlayer.play();
        } else if (sound.equals("DEATH")) {
            media = new Media(getClass().getResource("/sound/death.wav").toExternalForm());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(1);
            mediaPlayer.play();
        } else if (sound.equals("FIREBALL_ATTACK")) {
            media = new Media(getClass().getResource("/sound/fireball_attack.wav").toExternalForm());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(1);
            mediaPlayer.play();
        } else if (sound.equals("GIANT_ATTACK")) {
            media = new Media(getClass().getResource("/sound/giant_attack.wav").toExternalForm());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(1);
            mediaPlayer.play();
        } else if (sound.equals("GIANT_CHOOSE")) {
            media = new Media(getClass().getResource("/sound/giant_choose.wav").toExternalForm());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(1);
            mediaPlayer.play();
        } else if (sound.equals("INFERNO_ATTACK")) {
            media = new Media(getClass().getResource("/sound/inferno_attack.wav").toExternalForm());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(1);
            mediaPlayer.play();
        } else if (sound.equals("INFERNO_CHOOSE")) {
            media = new Media(getClass().getResource("/sound/inferno_choose.wav").toExternalForm());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(1);
            mediaPlayer.play();
        } else if (sound.equals("MINIPEKKA_ATTACK")) {
            media = new Media(getClass().getResource("/sound/minipekka_attack.wav").toExternalForm());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(1);
            mediaPlayer.play();
        } else if (sound.equals("MINIPEKKA_CHOOSE")) {
            media = new Media(getClass().getResource("/sound/minipekka_choose.wav").toExternalForm());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(1);
            mediaPlayer.play();
        } else if (sound.equals("RAGE")) {
            media = new Media(getClass().getResource("/sound/rage.wav").toExternalForm());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(1);
            mediaPlayer.play();
        } else if (sound.equals("VALKYRIE_ATTACK")) {
            media = new Media(getClass().getResource("/sound/valkyrie_attack.wav").toExternalForm());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(1);
            mediaPlayer.play();
        } else if (sound.equals("VALKYRIE_CHOOSE")) {
            media = new Media(getClass().getResource("/sound/valkyrie_choose.wav").toExternalForm());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(1);
            mediaPlayer.play();
        } else if (sound.equals("WIN")) {
            media = new Media(getClass().getResource("/sound/win.wav").toExternalForm());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(1);
            mediaPlayer.play();
        } else if (sound.equals("WIZARD_ATTACK")) {
            media = new Media(getClass().getResource("/sound/wizard_attack.wav").toExternalForm());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(1);
            mediaPlayer.play();
        } else if (sound.equals("WIZARD_CHOOSE")) {
            media = new Media(getClass().getResource("/sound/wizard_choose.wav").toExternalForm());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(1);
            mediaPlayer.play();
        } else if (sound.equals("KING_ACTIVE")) {
            media = new Media(getClass().getResource("/sound/king_active.wav").toExternalForm());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(1);
            mediaPlayer.play();
        } else if (sound.equals("KING_ATTACK")) {
            media = new Media(getClass().getResource("/sound/king_attack.wav").toExternalForm());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(1);
            mediaPlayer.play();
        } else if (sound.equals("ARCHERTOWER_ATTACK")) {
            media = new Media(getClass().getResource("/sound/archertower_attack.wav").toExternalForm());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(1);
            mediaPlayer.play();
        } else if (sound.equals("END")) {
            media = new Media(getClass().getResource("/sound/end.wav").toExternalForm());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(1);
            mediaPlayer.play();
        } else if (sound.equals("ONE")) {
            media = new Media(getClass().getResource("/sound/one.wav").toExternalForm());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(1);
            mediaPlayer.play();
        } else if (sound.equals("TWO")) {
            media = new Media(getClass().getResource("/sound/two.wav").toExternalForm());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(1);
            mediaPlayer.play();
        } else if (sound.equals("THREE")) {
            media = new Media(getClass().getResource("/sound/three.wav").toExternalForm());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(1);
            mediaPlayer.play();
        } else if (sound.equals("FOUR")) {
            media = new Media(getClass().getResource("/sound/four.wav").toExternalForm());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(1);
            mediaPlayer.play();
        } else if (sound.equals("FIVE")) {
            media = new Media(getClass().getResource("/sound/five.wav").toExternalForm());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(1);
            mediaPlayer.play();
        } else if (sound.equals("SIX")) {
            media = new Media(getClass().getResource("/sound/six.wav").toExternalForm());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(1);
            mediaPlayer.play();
        } else if (sound.equals("SEVEN")) {
            media = new Media(getClass().getResource("/sound/seven.wav").toExternalForm());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(1);
            mediaPlayer.play();
        } else if (sound.equals("NO")) {
            media = new Media(getClass().getResource("/sound/no.wav").toExternalForm());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(1);
            mediaPlayer.play();
        } else if (sound.equals("EIGHT")) {
            media = new Media(getClass().getResource("/sound/eight.wav").toExternalForm());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(1);
            mediaPlayer.play() ;
        } else if (sound.equals("NINE")) {
            media = new Media(getClass().getResource("/sound/nine.wav").toExternalForm());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(1);
            mediaPlayer.play();
        } else if (sound.equals("TEN")) {
            media = new Media(getClass().getResource("/sound/ten.wav").toExternalForm());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(1);
            mediaPlayer.play();
        }


    }

}