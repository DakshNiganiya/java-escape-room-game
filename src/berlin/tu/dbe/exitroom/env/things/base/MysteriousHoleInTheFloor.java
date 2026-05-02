package berlin.tu.dbe.exitroom.env.things.base;

import berlin.tu.dbe.exitroom.env.RoomExplorer;
import berlin.tu.dbe.exitroom.env.things.effect.*;

/**
 * a thing auto-instantiated for the ExitRoom.
 * Don't read the code of this class unless you want to spoiler yourself.
 *
 * @author David
 */
public class MysteriousHoleInTheFloor {

    public void lookClosely() {
        System.out.println("The hole looks quite deep, definitely not the way out.");
        System.out.println("But might be useful for getting rid of annoying stuff...");
    }

    public void jump() {
        System.out.println("I like jumping into deep and mysterious holes. Not.");
        System.out.println("So, sorry, but I'll pass.");
    }

    public void drop(Broom b) {
        System.out.println("You decided to drop the broom into the hole in the floor");
        RoomExplorer.getInstance().getRoom().removeThing(b);
        System.out.println("Mysteriously, you never hear a sound.");
    }

    public void drop(Bucket b) {
        System.out.println("No, you're not dropping the bucket into the hole. It is so pretty.");
    }

    public void drop(BucketFullOfWater b) {
        System.out.println("No, you're not dropping the bucket into the hole. It is so pretty, but we can pour the water away.");
        RoomExplorer.getInstance().getRoom().removeThing(b);
        RoomExplorer.getInstance().getRoom().addThing(new Bucket());
    }


    public void drop(VaseFullOfRoses b) {
        System.out.println("You decided to drop the vase into the hole in the floor");
        RoomExplorer.getInstance().getRoom().removeThing(b);
        System.out.println("And, dang - it's gone.");
    }

    public void drop(OldBottle b) {
        System.out.println("You drop the bottle into the mysterious hole");
        System.out.println("Suddenly there is a whooshing sound...");
        RoomExplorer.getInstance().getRoom().removeThing(b);
        RoomExplorer.getInstance().getRoom().addThing(new Genie());
    }

    public void drop(Chair b) {
        System.out.println("It takes a bit of squeezing but you manage to drop the vase into the hole in the floor");
        RoomExplorer.getInstance().getRoom().removeThing(b);
    }

    public void drop(Ball b) {
        System.out.println("No, not the ball!11!!!1111!!!");
    }

    public void drop(StuffedPony b) {
        System.out.println("Nope, too big.");
    }

    public void drop(Table b) {
        System.out.println("Nope, too big.");
    }

    public void drop(Wardrobe b) {
        System.out.println("Nope, too big and too heavy for moving.");
    }

    public void drop(Ashes b) {
        System.out.println("It's good to get rid of all that dirt.");
        RoomExplorer.getInstance().getRoom().removeThing(b);
    }

    public void drop(FlintStones b) {
        System.out.println("Better not, I guess I will need them to get out of here.");
    }

    public void drop(Snake s) {
        System.out.println("Away with that wilde beest!");
        s.onPickup();
        RoomExplorer.getInstance().getRoom().removeThing(s);
    }

    public void drop(BurningKindle b) {
        System.out.println("Nope, I'm not touching that.");
    }

    public void drop(Torch t) {
        System.out.println("Nope, I'm keeping my torch.");
    }

    public void drop(BloodDrops b) {
        System.out.println("Away with it");
        RoomExplorer.getInstance().getRoom().removeThing(b);
    }

    public void drop(Genie g) {
        System.out.println("The genie laughs...YOU think you can drop me down there?");
        RoomExplorer.getInstance().getRoom().removeThing(this);
    }

}
