package berlin.tu.dbe.exitroom.env.things.effect;

import berlin.tu.dbe.exitroom.env.Helper;
import berlin.tu.dbe.exitroom.env.RoomExplorer;
import berlin.tu.dbe.exitroom.env.things.HasPickupEffect;
import berlin.tu.dbe.exitroom.env.things.base.Broom;

/**
 * a thing instantiated for the ExitRoom in reaction to some user action.
 * Don't read the code of this class unless you want to spoiler yourself.
 *
 * @author David
 */
public class Snake implements HasPickupEffect {

    private boolean alive = true;

    @Override
    public void onPickup() {
        if (alive) {
            System.out.println("As you bend down to grab the snake, it coils around your arm.");
            Helper.sleep(2000);
            System.out.println("Then, it strikes and sinks its fangs into your hand.");
            Helper.sleep(2000);
            System.out.println("You feel the poison seeping into your hand.");
            Helper.sleep(2000);
            System.out.println("You feel the poison pulsing up your arm.");
            Helper.sleep(2000);
            System.out.println("You are starting to feel dizzy.");
            Helper.sleep(2000);
            System.out.println("You regret touching the snake.");
            Helper.sleep(2000);
            System.out.println("Why is the room turning?");
            Helper.sleep(2000);
            System.out.println("Your vision is getting blurry.");
            Helper.sleep(2000);
            System.out.println("The last thing you see is the snake moving towards your face...");
            Helper.sleep(2000);
            System.out.println("You're dead.");
            System.exit(1);
        }
    }

    public void touch() {
        if (!alive) {
            System.out.println("You touch the snake. It feels smooth and cold.");
            return;
        }
        System.out.println("As you bend down to touch the snake, it rears upwards and coils back to strike.");
        if (Math.random() < 0.8) {
            System.out.println("But you get lucky: As it strikes, you're able"
                    + " to pull back your hand just in time while it hisses at you.");
            return;
        }
        System.out.println("It strikes and sinks its fangs into your hand.");
        Helper.sleep(2000);
        System.out.println("You feel the poison seeping into your hand.");
        Helper.sleep(2000);
        System.out.println("You feel the poison pulsing up your arm.");
        Helper.sleep(2000);
        System.out.println("You are starting to feel dizzy.");
        Helper.sleep(2000);
        System.out.println("You regret touching the snake.");
        Helper.sleep(2000);
        System.out.println("Why is the room turning?");
        Helper.sleep(2000);
        System.out.println("Your vision is getting blurry.");
        Helper.sleep(2000);
        System.out.println("The last thing you see is the snake moving towards your face...");
        Helper.sleep(2000);
        System.out.println("You're dead.");
        System.exit(1);
    }

    public void hit(Broom b) {
        if (alive) {
            System.out.println("You get lucky and bludgeon the snake's head. It's now dead.");
            alive = false;
        } else {
            System.out.println("Hey, you moron, this snake was already dead. Do you think it will get deader?");
        }
    }

    public void hit(LargePieceOfWood l) {
        if (alive) {
            System.out.println("You get lucky and hit the snake on its head. It's now dead.");
            alive = false;
        } else {
            System.out.println("Hey, you moron, this snake was already dead. Do you think it will get deader?");
        }
    }

    public void setOnFire(Torch t) {
        if (alive) System.out.println("The snake slithers into the corner of the room and hisses at you.");
        else {
            System.out.println("After roasting the snake for a while, you notice a tasty smell.");
            RoomExplorer.getInstance().getRoom().removeThing(this);
            RoomExplorer.getInstance().getRoom().addThing(new RoastedSnake());
        }
    }

    public void eat() {
        if (alive) onPickup();
        else {
            System.out.println("You take a bite out of the dead snake.");
            Helper.sleep(2000);
            System.out.println("You chew and swallow. It leaves a weird aftertaste.");
            Helper.sleep(2000);
            System.out.println("You wonder whether this was a good idea.");
            Helper.sleep(2000);
            System.out.println("You're stomach makes a strange grumbling noise...");

            new Thread(() -> {
                Helper.sleep(1000 * 60 * 5);
                System.out.println("Suddenly you throw up blood.");
                Helper.sleep(2000);
                System.out.println("Now you regret eating that snake.");
                Helper.sleep(2000);
                System.out.println("You throw up more blood and start to feel dizzy.");
                Helper.sleep(2000);
                System.out.println("You pass out.");
                Helper.sleep(2000);
                System.out.println("You're dead");
                System.out.println("You're dead");
                System.exit(1);
            }).start();
        }
    }

}
