package model.cards.buildings;

import model.GameElement;
import model.cards.CellValue;
import model.cards.levelEnums.InfernoLevel;
import model.cards.troops.attributes.Target;

import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;

public class Inferno extends Building implements Serializable {
    public InfernoLevel infernoLevel;
    private int minDamage ;
    private int maxDamage ;
    private GameElement target = null;

    public Inferno(InfernoLevel infernoLevel) {
        super(CellValue.INFERNO, 5, 6, Target.ANY, 0.4f, 40, infernoLevel.getHitPoint(), infernoLevel.getMinDamage());
        this.infernoLevel = infernoLevel;
    }
    public void increaseDamage(){
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if(getDamage() > infernoLevel.getMaxDamage())
                    return ;
                     setDamage(getDamage() + (infernoLevel.getMaxDamage() - infernoLevel.getMinDamage())/5);
            }
        };

        timer.scheduleAtFixedRate(task , 0 , 2000);
    }

    public void setTarget(GameElement ele){
        if(target == null){
            target = ele ;
            setDamage(infernoLevel.getMinDamage());
            increaseDamage();
        }
        else if(target != ele) {
            target = ele;
            setDamage(infernoLevel.getMinDamage());
            increaseDamage();
        }
    }

    public int getCicleRadius(){
        return (getDamage() - infernoLevel.getMinDamage()) / (infernoLevel.getMaxDamage() - infernoLevel.getMinDamage()) * 2 + 3 ;

    }


}