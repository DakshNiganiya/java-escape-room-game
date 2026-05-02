package berlin.tu.dbe.exitroom.env.things.base;

import berlin.tu.dbe.exitroom.env.things.CantBeCarried;

/**
 * a thing auto-instantiated for the ExitRoom.
 * Don't read the code of this class unless you want to spoiler yourself.
 *
 * @author David
 */
public class Sink implements CantBeCarried {

    boolean waterIsFlowing = false;

    public void turnTap() {
        if (waterIsFlowing) {
            System.out.println("The water stops flowing from the tap.");
        } else {
            System.out.println("The water is now flowing from the tap.");
        }
        waterIsFlowing = !waterIsFlowing;
    }
}
