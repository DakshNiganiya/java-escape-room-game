package berlin.tu.dbe.exitroom.env.things.effect;

import berlin.tu.dbe.exitroom.env.Helper;

public class SquareKey {
    public void openDoor(){
        System.out.println("You see that a door has magically appeared at the end of the room.");
        Helper.sleep(3000);
        System.out.println("As you look closely, you see a keyhole which fits the square key you just obtained." +
                "\nYou find out that it does. Without thinking you insert the key in keyhole and open the door");
        Helper.sleep(3000);
        System.out.println("The door opens to the world outside. You have escaped!");
        System.exit(0);
    }
}
