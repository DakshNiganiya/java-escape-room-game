package berlin.tu.dbe.exitroom.env.things.base;

import berlin.tu.dbe.exitroom.env.RoomExplorer;
import berlin.tu.dbe.exitroom.env.things.effect.Ball;
import berlin.tu.dbe.exitroom.env.things.effect.BurningKindle;
import berlin.tu.dbe.exitroom.env.things.effect.Torch;


/**
 * a thing auto-instantiated for the ExitRoom
 *
 * @author David
 */
public class Broom {

    public String sweepTable(Table table) {
        return "sweep sweep";
    }

    public Torch setOnFire(BurningKindle bk) {
        System.out.println("You set the broom on fire.");
        RoomExplorer.getInstance().getRoom().removeThing(this);
        return new Torch();
    }

    public void sweepRoom() {
        System.out.println("The room is now pretty clean.");
    }

    public void createSparks(FlintStones b) {
        System.out.println("You bang the flintstones against each other but the"
                + " sparks do not suffice to set the broom on fire."
                + " The broom will need need more heat to catch fire.");
    }

    public void playBaseball(Ball b) {
        System.out.println("Yeah, you're playing baseball.");
        System.out.println("Alone.");
        System.out.println("Locked inside.");
        System.out.println("With no means to escape.");
    }
}
