package berlin.tu.dbe.exitroom.env.things.base;

import berlin.tu.dbe.exitroom.env.RoomExplorer;
import berlin.tu.dbe.exitroom.env.things.effect.Ashes;
import berlin.tu.dbe.exitroom.env.things.effect.Ball;
import berlin.tu.dbe.exitroom.env.things.effect.Torch;

/**
 * a thing auto-instantiated for the ExitRoom.
 * Don't read the code of this class unless you want to spoiler yourself.
 *
 * @author David
 */
public class StuffedPony {

    public void ride() {
        System.out.println("You ride the pony for a while. You have so much fun. You feel like a 5-year old again.");
    }

    public Ashes burn(Torch torch) {
        RoomExplorer.getInstance().getRoom().removeThing(this);
        System.out.println("You set fire to the stuffed pony, it burns down quickly.");
        System.out.println("You're very sad...all those memories of happier days...");
        return new Ashes();
    }

    public void kickAgainst(Ball b) {
        System.out.println("When you kick the ball against the stuffed pony, it falls over.");
        System.out.println("Full of sorrow, you again put it upright.");
        System.out.println("You gently tease the horse's head, mumbling apologies.");
    }

}
