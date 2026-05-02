package berlin.tu.dbe.exitroom.env.things.effect;

import berlin.tu.dbe.exitroom.env.Room;
import berlin.tu.dbe.exitroom.env.RoomExplorer;

/**
 * a thing instantiated for the ExitRoom in reaction to some user action.
 * Don't read the code of this class unless you want to spoiler yourself.
 *
 * @author David
 */
public class Ball {

    public void kick() {
        System.out.println("You kick the ball hard.");
        Room here = RoomExplorer.getInstance().getRoom();
        Room[] next = here.getAdjacentRooms();
        if (next == null || next.length == 0) {
            System.out.println("The ball bounces back from the wall and hits you in the face. Ouch.");
            return;
        }
        if (Math.random() < 0.5) {
            System.out.println("The ball bounces back from the wall and hits you in the face. Ouch.");
            return;
        }
        Room other = next[(int) (Math.random() * next.length)];
        System.out.println("You manage to kick the ball straight through the door into room " + other.getName());
        here.removeThing(this);
        other.init(this);
    }

    public Ashes burn(Torch torch) {
        RoomExplorer.getInstance().getRoom().removeThing(this);
        System.out.println("You set fire to the ball, it burns down quickly. You're really sad.");
        return new Ashes();
    }

}
