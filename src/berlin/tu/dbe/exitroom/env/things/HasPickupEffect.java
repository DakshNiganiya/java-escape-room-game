package berlin.tu.dbe.exitroom.env.things;

/**
 * classes implementing this interface can specify behavior that is automatically executed when a player
 * triggers the pick up action for an instance of this class.
 *
 * @author David
 */
public interface HasPickupEffect {

    void onPickup();
}
