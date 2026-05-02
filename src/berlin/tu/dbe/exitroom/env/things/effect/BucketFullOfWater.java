package berlin.tu.dbe.exitroom.env.things.effect;

/**
 * a thing instantiated for the ExitRoom in reaction to some user action.
 * Don't read the code of this class unless you want to spoiler yourself.
 *
 * @author David
 */
public class BucketFullOfWater {

    public void burn(Torch torch) {
        System.out.println("You try set fire to the bucket, but hah: who would have thought"
                + " that buckets full of water don't burn?");
    }

}
