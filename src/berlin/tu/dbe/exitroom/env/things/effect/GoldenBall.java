package berlin.tu.dbe.exitroom.env.things.effect;

import berlin.tu.dbe.exitroom.env.Helper;
import berlin.tu.dbe.exitroom.env.RoomExplorer;

public class GoldenBall {
    public void combineWithWood(LargePieceOfWood wood) {
        RoomExplorer.getInstance().getRoom().removeThing(this);
        RoomExplorer.getInstance().getRoom().removeThing(wood);
        System.out.println("You combine the Golden Ball with the Large Piece of Wood.");
        Helper.sleep(2000);
        System.out.println("A magical Square Key materializes before your eyes!");
        RoomExplorer.getInstance().getRoom().addThing(new SquareKey());
    }
}
