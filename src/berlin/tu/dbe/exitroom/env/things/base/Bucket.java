package berlin.tu.dbe.exitroom.env.things.base;

import berlin.tu.dbe.exitroom.env.Helper;
import berlin.tu.dbe.exitroom.env.RoomExplorer;
import berlin.tu.dbe.exitroom.env.things.effect.Ashes;
import berlin.tu.dbe.exitroom.env.things.effect.BucketFullOfWater;
import berlin.tu.dbe.exitroom.env.things.effect.Torch;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * a thing auto-instantiated for the ExitRoom.
 * Don't read the code of this class unless you want to spoiler yourself.
 *
 * @author David
 */
public class Bucket {

    private static final String snowman = Helper.readFileToString("snowman.txt");

    public void fillWithWater(Sink s) {
        if (s.waterIsFlowing) {
            System.out.println("You have filled the bucket with water");
            RoomExplorer.getInstance().getRoom().removeThing(this);
            RoomExplorer.getInstance().getRoom().addThing(new BucketFullOfWater());
        } else {
            System.out.println("Maybe try opening the tap first?");
        }
    }

    public Ashes burn(Torch torch) {
        RoomExplorer.getInstance().getRoom().removeThing(this);
        System.out.println("You set fire to the bucket, it burns down quickly. You realize that you will miss that bucket.");
        return new Ashes();
    }

    public void combine(Broom b) {
        System.out.println("You take the broom in your hand and put the bucket on your hand.");
        System.out.println("You merrily jump around the room, singing that you're a snowman.");
        System.out.println(snowman);
    }


}
