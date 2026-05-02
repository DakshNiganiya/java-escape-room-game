package berlin.tu.dbe.exitroom.env.things.effect;

import berlin.tu.dbe.exitroom.env.Helper;
import berlin.tu.dbe.exitroom.env.RoomExplorer;
import berlin.tu.dbe.exitroom.env.things.CantBeCarried;
import berlin.tu.dbe.exitroom.env.things.base.Bucket;
import berlin.tu.dbe.exitroom.env.things.base.StuffedPony;
import berlin.tu.dbe.exitroom.env.things.base.Table;
import berlin.tu.dbe.exitroom.env.things.base.Wardrobe;

/**
 * a thing instantiated for the ExitRoom in reaction to some user action.
 * Don't read the code of this class unless you want to spoiler yourself.
 *
 * @author David
 */
public class BurningKindle implements CantBeCarried {

    private Thread killTimer = null;

    public Torch touchWithPieceOfWood(LargePieceOfWood l) {
        System.out.println("As you hold the piece of wood against the burning kindle, it suddenly starts burning.");
        RoomExplorer.getInstance().getRoom().removeThing(l);
        if (killTimer == null) {
            killTimer = getKillTimer();
            killTimer.start();
        }
        return new Torch();
    }

    public void extinguishFire(BucketFullOfWater b) {
        System.out.println("You pour the bucket on the kindle and extinguish the fire.");
        RoomExplorer.getInstance().getRoom().removeThing(this);
        RoomExplorer.getInstance().getRoom().addThing(new Kindle());
        RoomExplorer.getInstance().getRoom().removeThing(b);
        RoomExplorer.getInstance().getRoom().addThing(new Bucket());
        System.out.println("Unfortunately, the smoke is still getting denser...apparently, the floor is smoldering...");
    }

    public void setOnFire(Wardrobe w) {
        System.out.println("Are you crazy? You can't touch burning kindle...");
    }

    public void setOnFire(StuffedPony w) {
        System.out.println("Are you crazy? You can't touch burning kindle...");
    }

    public void setOnFire(Table w) {
        System.out.println("Are you crazy? You can't touch burning kindle...");
    }


    private Thread getKillTimer() {
        return new Thread(() -> {
            System.out.println("You start smelling smoke.");
            Helper.sleep(60 * 1000);
            System.out.println("The smoke is getting denser.");
            Helper.sleep(60 * 1000);
            System.out.println("The smoke is now so thick that you have trouble seeing the room. You're coughing.");
            Helper.sleep(30 * 1000);
            System.out.println("You're coughing again.");
            Helper.sleep(30 * 1000);
            System.out.println("You're coughing again.");
            Helper.sleep(30 * 1000);
            System.out.println("You can't stop coughing.");
            Helper.sleep(30 * 1000);
            System.out.println("The coughing is so bad that you can barely breath. You're starting to feel dizzy.");
            Helper.sleep(60 * 1000);
            System.out.println("You see the floor coming towards you. Rapidly.");
            Helper.sleep(2000);
            System.out.println("You lie on the floor, breathing is so hard.");
            Helper.sleep(2000);
            System.out.println("You just can't breath...");
            Helper.sleep(2000);
            System.out.println("The world is gradually turning black.");
            Helper.sleep(2000);
            System.out.println("You're dead.");
            System.exit(1);
        });
    }
}
