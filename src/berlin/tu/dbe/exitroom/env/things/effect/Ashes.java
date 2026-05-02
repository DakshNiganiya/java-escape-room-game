package berlin.tu.dbe.exitroom.env.things.effect;

import berlin.tu.dbe.exitroom.env.things.base.Broom;

public class Ashes {

    public void hit(Broom b) {
        System.out.println("By hitting the pile of ashes you create cloud of dust.");
        System.out.println("You cough.");
        System.out.println("You wait for a minute until the air clears and you can see again.");
    }

    public void hit(LargePieceOfWood b) {
        System.out.println("By hitting the pile of ashes you create cloud of dust.");
        System.out.println("You cough.");
        System.out.println("You wait for a minute until the air clears and you can see again.");
    }

    public void hit(Ball b) {
        System.out.println("By hitting the pile of ashes you create cloud of dust.");
        System.out.println("You cough.");
        System.out.println("You wait for a minute until the air clears and you can see again.");
    }


}
