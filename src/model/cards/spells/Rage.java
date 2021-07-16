package model.cards.spells;

import javafx.scene.image.Image;
import model.GameElement;
import model.cards.CellValue;
import model.cards.buildings.Building;
import model.cards.levelEnums.RageLevel;
import model.cards.troops.BabyDragon;
import model.cards.troops.MiniPeka;
import model.cards.troops.Troop;
import model.cards.troops.attributes.Speed;
import model.towers.Tower;

import java.io.Serializable;
import java.util.ArrayList;

import static model.cards.troops.attributes.Speed.SLOW;

public class Rage extends Spell implements Serializable {
    private ArrayList<GameElement> ragedPlayers ;
    private float duration;
    private boolean isTimerStarted ;
    public Rage(RageLevel level) {
        super(CellValue.RAGE , 3, 5);
        duration = level.getDuration();
        ragedPlayers = new ArrayList<>() ;
        isTimerStarted = false ;
    }

    public float getDuration() {
        return duration;
    }

    public void startTimer(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    isTimerStarted = true ;
                    Thread.sleep((long) (duration * 1000L));
                    System.out.println(getValue() + " is sleeping ");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                reverseRage();
                killCard();
            }
        }).start() ;
    }
    public void executeRage(ArrayList<GameElement> targets)
    {
        for(GameElement i : targets){
            if(!ragedPlayers.contains(i)){
                ragedPlayers.add(i) ;
                if(i instanceof Building){
                    ((Building) i).setDamage((int) (((Building) i).getDamage() * 1.4));
                    ((Building) i).setHitSpeed((int) (((Building) i).getHitSpeed() * 0.6));
                }
                else if(i instanceof Tower){
                    ((Tower) i).setHitSpeed((int) (((Tower) i).getHitSpeed() * 0.6));
                    ((Tower) i).setDamage((int) (((Tower) i).getDamage() * 1.4));
                }
                else if(i instanceof Troop){
                    boostSpeed((Troop) i);
                    ((Troop) i).setDamage((int) (((Troop) i).getDamage() * 1.4));
                    ((Troop) i).setHitSpeed((int) (((Troop) i).getHitSpeed() * 0.6));
                    boostSpeed((Troop) i);
                }
            }
        }
    }
    private void boostSpeed(Troop troop){
            switch(troop.getSpeed()){
                case MEDIUM:
                    troop.setSpeed(Speed.FAST);
                case SLOW:
                    troop.setSpeed(Speed.MEDIUM);
            }
    }
    private void slowSpeed(Troop troop){
        if(troop instanceof MiniPeka || troop instanceof BabyDragon) {
        }
        else
        {
            switch(troop.getSpeed()){
                case FAST :
                    troop.setSpeed(Speed.MEDIUM);
                case MEDIUM :
                    troop.setSpeed(SLOW);
            }
        }
    }
    private void reverseRage(){
        for(GameElement i : ragedPlayers){
            if(i instanceof Building){
                ((Building) i).setDamage((int) (((Building) i).getDamage() / 1.4));
                ((Building) i).setHitSpeed((int) (((Building) i).getHitSpeed() / 0.6));
            }
            else if(i instanceof Tower){
                ((Tower) i).setHitSpeed((int) (((Tower) i).getHitSpeed() / 0.6));
                ((Tower) i).setDamage((int) (((Tower) i).getDamage() / 1.4));
            }
            else if(i instanceof Troop){
                slowSpeed((Troop) i);
                ((Troop) i).setDamage((int) (((Troop) i).getDamage() / 1.4));
                ((Troop) i).setHitSpeed((int) (((Troop) i).getHitSpeed() / 0.6));
            }
        }
    }
    public boolean isTimerStarted() {
        return isTimerStarted;
    }
}
