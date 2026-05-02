package berlin.tu.dbe.exitroom.env.things.base;

import berlin.tu.dbe.exitroom.env.Helper;
import berlin.tu.dbe.exitroom.env.RoomExplorer;
import berlin.tu.dbe.exitroom.env.things.CantBeCarried;
import berlin.tu.dbe.exitroom.env.things.effect.Kindle;
import berlin.tu.dbe.exitroom.env.things.effect.LargePieceOfWood;
import berlin.tu.dbe.exitroom.env.things.effect.Snake;
import berlin.tu.dbe.exitroom.env.things.effect.Torch;


/**
 * a thing auto-instantiated for the ExitRoom.
 * Don't read the code of this class unless you want to spoiler yourself.
 *
 * @author David
 */
public class Wardrobe implements CantBeCarried {

    public void setOnFire(Torch t) {
        System.out.println("Using the torch you set the wardrobe on fire and watch it burn.");
        Helper.sleep(2000);
        System.out.println("As the wardrobe burns down you see...");
        Helper.sleep(2000);
        System.out.println("Could it be true?");
        Helper.sleep(2000);
        System.out.println("...");
        Helper.sleep(2000);
        System.out.println("There is a secret doorway that was hidden by the wardrobe.");
        Helper.sleep(2000);
        System.out.println("You step over the smoldering remains of the wardrobe.");
        Helper.sleep(2000);
        System.out.println("You can't believe it but you're FREE AT LAST!");
        Helper.sleep(2000);
        System.out.println("You made it.");
        Helper.sleep(2000);
        System.out.println("Alive.");
        Helper.sleep(2000);
        System.out.println("-------------------------------------------");
        System.exit(1);
    }

    public Snake open() {
        System.out.println("There is a hissing sound and a snake escapes into the room...it looks angry.");
        System.out.println("Quickly, you close the door again.");
        return new Snake();
    }

    public void createSparks(FlintStones w) {
        System.out.println("You bang the flintstones against each other but the"
                + " sparks do not suffice to set the wardrobe on fire."
                + " The wardrobe will need need more heat to catch fire.");
    }

    public void hitWithBroom(Broom b) {
        RoomExplorer.getInstance().getRoom().removeThing(b);
        System.out.println("Crack...the broom is broken into two pieces.");
        RoomExplorer.getInstance().getRoom().addThing(new LargePieceOfWood());
        RoomExplorer.getInstance().getRoom().addThing(new LargePieceOfWood());
        System.out.println("...and an angry snake comes out of the wardrobe");
        RoomExplorer.getInstance().getRoom().addThing(new Snake());
    }

    public void hitWithChair(Chair c) {
        RoomExplorer.getInstance().getRoom().removeThing(c);
        System.out.println("Crack...the chair is broken into two pieces.");
        RoomExplorer.getInstance().getRoom().addThing(new LargePieceOfWood());
        RoomExplorer.getInstance().getRoom().addThing(new LargePieceOfWood());
        System.out.println("...and an angry snake comes out of the wardrobe");
        RoomExplorer.getInstance().getRoom().addThing(new Snake());
    }


    public Object hitWithPieceOfWood(LargePieceOfWood l) {
        System.out.println("As you keep hitting the wardrobe with the piece of wood it gradually breaks down into smaller pieces.");
        RoomExplorer.getInstance().getRoom().removeThing(l);
        System.out.println("...and an angry snake comes out of the wardrobe");
        RoomExplorer.getInstance().getRoom().addThing(new Snake());
        return new Kindle();
    }


}
