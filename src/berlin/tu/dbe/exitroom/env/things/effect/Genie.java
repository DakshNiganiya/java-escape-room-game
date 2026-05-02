package berlin.tu.dbe.exitroom.env.things.effect;

import berlin.tu.dbe.exitroom.env.things.base.Broom;
import berlin.tu.dbe.exitroom.env.things.base.Chair;
import berlin.tu.dbe.exitroom.env.things.base.StuffedPony;
import berlin.tu.dbe.exitroom.env.things.base.Table;

/**
 * a thing instantiated for the ExitRoom in reaction to some user action.
 * Don't read the code of this class unless you want to spoiler yourself.
 *
 * @author David
 */
public class Genie {


    public Object askAWish() {
        double rand = Math.random();
        System.out.println("You whisper your most intimate and secret wish into the genie's ear...");
        System.out.println("It answers: \"Oh, today's you your lucky day\"");
        if (rand < 0.15) return new StuffedPony();
        if (rand >= 0.15 && rand < 0.3) return new Chair();
        if (rand >= 0.3 && rand < 0.55) return new Broom();
        if (rand >= 0.55 && rand < 0.7) return new Snake();
        if (rand >= 0.7 && rand < 0.85) return new Ball();
        else return new Table();
    }

    public void askAdvice() {
        System.out.println("Not so easy, uuh? BURN in hell...hahahaha!!!");
    }
}
