package berlin.tu.dbe.exitroom.env.things.effect;

import berlin.tu.dbe.exitroom.env.RoomExplorer;

/**
 * a thing instantiated for the ExitRoom in reaction to some user action.
 * Don't read the code of this class unless you want to spoiler yourself.
 *
 * @author David
 */
public class RoastedSnake {

    public void eat() {
        System.out.println("You eat the snake. It tastes quite good.");
        RoomExplorer.getInstance().getRoom().removeThing(this);
    }

    public Ashes burn(Torch torch) {
        RoomExplorer.getInstance().getRoom().removeThing(this);
        System.out.println("You set fire to the roasted snake, it quickly becomes charcoal. Your stomach grumbles.");
        return new Ashes();
    }
}
