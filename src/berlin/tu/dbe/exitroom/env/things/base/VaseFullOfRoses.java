package berlin.tu.dbe.exitroom.env.things.base;

import berlin.tu.dbe.exitroom.env.things.effect.Ball;
import berlin.tu.dbe.exitroom.env.things.effect.BloodDrops;
import berlin.tu.dbe.exitroom.env.things.effect.LargePieceOfWood;

/**
 * a thing auto-instantiated for the ExitRoom.
 * Don't read the code of this class unless you want to spoiler yourself.
 *
 * @author David
 */
public class VaseFullOfRoses {


    public Object touch() {
        System.out.println("Ouch, the thorns are quite sharp.");
        return new BloodDrops();
    }

    public void hit(Broom b) {
        System.out.println("You wanted to hit the vase but you can't, it's too pretty.");
    }

    public void hit(LargePieceOfWood l) {
        System.out.println("You wanted to hit the vase but you can't, it's too pretty.");
    }

    public void kickAgainst(Ball b) {
        System.out.println("You wanted to kick the ball against the vase but you can't, it's too pretty.");
    }
}
