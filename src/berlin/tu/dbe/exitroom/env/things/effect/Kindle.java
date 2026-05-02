package berlin.tu.dbe.exitroom.env.things.effect;

import berlin.tu.dbe.exitroom.env.RoomExplorer;
import berlin.tu.dbe.exitroom.env.things.base.FlintStones;

/**
 * a thing instantiated for the ExitRoom in reaction to some user action.
 * Don't read the code of this class unless you want to spoiler yourself.
 *
 * @author David
 */
public class Kindle {

    public BurningKindle setOnFire(FlintStones f) {
        System.out.println("You bang the flintstones against each other, sparks fly");
        System.out.println("Suddenly the kindle starts burning, there is a hint of smoke.");
        RoomExplorer.getInstance().getRoom().removeThing(this);
        return new BurningKindle();
    }

}
