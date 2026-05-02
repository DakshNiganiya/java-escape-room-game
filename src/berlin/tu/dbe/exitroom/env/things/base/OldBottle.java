package berlin.tu.dbe.exitroom.env.things.base;

import berlin.tu.dbe.exitroom.env.Helper;
import berlin.tu.dbe.exitroom.env.RoomExplorer;
import berlin.tu.dbe.exitroom.env.things.effect.Genie;

/**
 * a thing auto-instantiated for the ExitRoom.
 * Don't read the code of this class unless you want to spoiler yourself.
 *
 * @author David
 */
public class OldBottle {

    private static final String genie = Helper.readFileToString("genie.txt");


    public void rub() {
        System.out.println("You rub the bottle, suddenly:");
        System.out.println(genie);
        RoomExplorer.getInstance().getRoom().removeThing(this);
        RoomExplorer.getInstance().getRoom().addThing(new Genie());
    }
}
