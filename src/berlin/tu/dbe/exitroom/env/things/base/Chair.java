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
public class Chair {

    public void sit() {
        System.out.println("You sit for a moment. It feels great.");
    }

    public Ashes burn(Torch torch) {
        RoomExplorer.getInstance().getRoom().removeThing(this);
        System.out.println("You set fire to the chair, it burns down quickly. You're really sad - no more sitting for you.");
        return new Ashes();
    }

    public void createSparks(FlintStones b) {
        System.out.println("You bang the flintstones against each other but the"
                + " sparks do not suffice to set the chair on fire."
                + " Maybe, we can get some kindle?");
    }

    public void hitWithBroom(Broom b) {
        RoomExplorer.getInstance().getRoom().removeThing(b);
        RoomExplorer.getInstance().getRoom().removeThing(this);
        System.out.println("Crack...both the broom and their chair are broken into two pieces.");
        RoomExplorer.getInstance().getRoom().addThing(new LargePieceOfWood());
        RoomExplorer.getInstance().getRoom().addThing(new LargePieceOfWood());
        RoomExplorer.getInstance().getRoom().addThing(new LargePieceOfWood());
        RoomExplorer.getInstance().getRoom().addThing(new LargePieceOfWood());
    }

    public Object hitWithPieceOfWood(LargePieceOfWood l) {
        System.out.println("As you keep hitting the chair with the piece of wood it gradually breaks down into smaller pieces.");
        RoomExplorer.getInstance().getRoom().removeThing(l);
        return new Kindle();
    }


}
