package berlin.tu.dbe.exitroom.env.things.effect;

import berlin.tu.dbe.exitroom.env.Helper;

public class hiddenKey {

    public void key(){
        System.out.println("You spot a a rusty old key lying on the floor.");
        Helper.sleep(1000);
        System.out.println("You bend down to pick it up.\n Suddenly a door appears before you and there's no handle, just a small keyhole");
        Helper.sleep(1000);
        System.out.println("You insert the key into the keyhole and twist it. \n The door opens automatically.");
        Helper.sleep(1000);
        System.out.println("A bright light surrounds you, leaving you impaired, unable to see.");
        Helper.sleep(1000);
        System.out.println("You have died.");
        Helper.sleep(5000);
        System.out.println("Haha, just kidding. \n Congratulations on finding the the hidden key. YOU ARE NOW FREE!");
        System.exit(0);
    }
}
