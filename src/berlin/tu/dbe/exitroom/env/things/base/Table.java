package berlin.tu.dbe.exitroom.env.things.base;

import berlin.tu.dbe.exitroom.env.RoomExplorer;
import berlin.tu.dbe.exitroom.env.things.effect.Ashes;
import berlin.tu.dbe.exitroom.env.things.effect.Kindle;
import berlin.tu.dbe.exitroom.env.things.effect.LargePieceOfWood;
import berlin.tu.dbe.exitroom.env.things.effect.Torch;

/**
 * a thing auto-instantiated for the ExitRoom.
 * Don't read the code of this class unless you want to spoiler yourself.
 *
 * @author David
 */
public class Table {

    public void hitWithBroom(Broom b) {
        RoomExplorer.getInstance().getRoom().removeThing(b);
        System.out.println("Crack...the broom is broken into two pieces.");
        RoomExplorer.getInstance().getRoom().addThing(new LargePieceOfWood());
        RoomExplorer.getInstance().getRoom().addThing(new LargePieceOfWood());
    }

    public Object hitWithPieceOfWood(LargePieceOfWood l) {
        System.out.println("As you keep hitting the table with the piece of wood it gradually breaks down into smaller pieces.");
        RoomExplorer.getInstance().getRoom().removeThing(l);
        return new Kindle();
    }

    public void hit() {
        System.out.println("You hit the table hard with your hand - ouch, that hurts.");
    }

    public Ashes burn(Torch torch) {
        RoomExplorer.getInstance().getRoom().removeThing(this);
        System.out.println("You set fire to the table, it burns down quickly.");
        return new Ashes();
    }

    public void createSparks(FlintStones b) {
        System.out.println("You bang the flintstones against each other but the"
                + " sparks do not suffice to set the table on fire."
                + " It's too sturdy for that.");
    }

}
