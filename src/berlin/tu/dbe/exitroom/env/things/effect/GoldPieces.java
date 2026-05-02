package berlin.tu.dbe.exitroom.env.things.effect;

import berlin.tu.dbe.exitroom.env.Helper;
import berlin.tu.dbe.exitroom.env.RoomExplorer;

public class GoldPieces {
    public void combineWithBall(Ball ball){
        RoomExplorer.getInstance().getRoom().removeThing(this);
        RoomExplorer.getInstance().getRoom().removeThing(ball);
        System.out.println("You decide to somehow attach the pieces of gold to the ball given by the Genie");
        Helper.sleep(3000);
        System.out.println("The ball starts glowing and transforms into a Golden Ball!");
        RoomExplorer.getInstance().getRoom().addThing(new GoldenBall());
    }
}