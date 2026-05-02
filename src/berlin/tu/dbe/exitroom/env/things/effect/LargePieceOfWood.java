package berlin.tu.dbe.exitroom.env.things.effect;

import berlin.tu.dbe.exitroom.env.things.base.FlintStones;

/**
 * a thing instantiated for the ExitRoom in reaction to some user action.
 * Don't read the code of this class unless you want to spoiler yourself.
 *
 * @author David
 */
public class LargePieceOfWood {

    public void createSparks(FlintStones b) {
        System.out.println("You bang the flintstones against each other but the"
                + " sparks do not suffice to set the wood on fire."
                + " Maybe, we can get some kindle?");
    }

    public void playBaseball(Ball b) {
        System.out.println("Yeah, you're playing baseball.");
        System.out.println("Alone.");
        System.out.println("Locked inside.");
        System.out.println("With no means to escape.");
    }

}
