package berlin.tu.dbe.exitroom.env.things.base;

import berlin.tu.dbe.exitroom.env.Helper;
import berlin.tu.dbe.exitroom.env.things.CantBeCarried;
import berlin.tu.dbe.exitroom.env.things.effect.Torch;

public class Thing implements CantBeCarried {
    public void hit(Broom b){
        System.out.println("You hit the thing, nothing happens. Huh, you wonder why nothing has happened.");
        Helper.sleep(2000);
        System.out.println("Since nothing has happened you try to hit it again." +
                " \n As you raise the broom in the air you hear a hissing sound coming from the thing.");
        Helper.sleep(2000);
        System.out.println("A strong force knocks you off your feet and you slam into the wall behind you." +
                "\nThe force of the impact almost knocks you out.");
        Helper.sleep(1000);
        System.out.println("You see a scary looking monster who kills you before you can even utter a sound.");
        Helper.sleep(2000);
        System.out.println("Well done you moron. Who in their right mind would hit a thing of unknown origin, with a broom of all things.");
        Helper.sleep(2000);
        System.out.println("You deserve what happened to you.");
        Helper.sleep(3000);
        System.out.println("You're already dead, what the heck are you staring at the screen for?");
        Helper.sleep(2000);
        System.out.println("Moron");
        System.exit(1);
    }
    public void sparks(FlintStones stones){
        System.out.println("You strike the flintstones together and nothing happens. " +
                "\nYou keep striking them together, hoping for sparks to appear");
        Helper.sleep(2000);
        System.out.println("The repeated action of striking the flintstones makes you feel like a caveman.");
        Helper.sleep(2000);
        System.out.println("What? Did you think a few sparks would be enough to do anything to a Thing of unknown origin?");
        Helper.sleep(1000);
        System.out.println("Congratulations you failed! Go do something else now :)");
    }
    public void fire(Torch torch){
        System.out.println("You use the torch to try to burn the thing.");
        Helper.sleep(2000);
        System.out.println("Nothing happens.");
        Helper.sleep(2000);
        System.out.println("Still nothing happens.");
        Helper.sleep(2000);
        System.out.println("'WHY IS NOTHING HAPPENING?' , you scream out loud.");
        Helper.sleep(2000);
        System.out.println("The thing starts to burn. \nSince you're in a dungeon, maybe your anger was the key.");
        Helper.sleep(2000);
        System.out.println("The thing not only disappears but burns down the entire escape room.");
        Helper.sleep(2000);
        System.out.println("You're free it seems.");
        Helper.sleep(2000);
        System.out.println("You're alive as well, what a marvelous surprise. \n You've escaped");
        System.exit(0);
    }


}
