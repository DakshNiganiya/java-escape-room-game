package berlin.tu.dbe.exitroom.env.things.base;

import berlin.tu.dbe.exitroom.env.RoomExplorer;
import berlin.tu.dbe.exitroom.env.things.effect.Ashes;
import berlin.tu.dbe.exitroom.env.things.effect.Ball;

public class GoldGlass {
    public void hit(Broom b){
        shatter();
    }

    public void kick(Ball ball){
        shatter();
    }
    private void shatter() {
        System.out.println(
                "You strike the shiny glass of gold. It shatters into small golden pieces.\n" +
                        "You're sad, but at least the pieces look valuable."
        );

        RoomExplorer.getInstance().getRoom().removeThing(this);

    }
}
