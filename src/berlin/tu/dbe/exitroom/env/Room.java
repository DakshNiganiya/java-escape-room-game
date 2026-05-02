package berlin.tu.dbe.exitroom.env;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


/**
 * describes rooms in the ExitRoom scenario and their properties (name, contained things, adjacent rooms).
 * provides methods for modifying this. During the game, only the addThing() and removeThing() methods shall
 * be used. Changing the network of rooms at runtime is not recommended. If necessary the init() method can
 * be used to silently (without logging) add things to a room.
 *
 * @author David
 */
public class Room {

    /**
     * things in this room
     */
    private final Set<Object> things = ConcurrentHashMap.newKeySet();

    /**
     * name of the room
     */
    private final String name;
    /**
     * the rooms this one is connected to
     */
    private final Set<Room> adjacentRooms = new HashSet<>();

    /**
     * @param name name of the room
     */
    public Room(String name) {
        this.name = name;
    }

    /**
     * adds a new thing to the room
     *
     * @param obj thing to be added
     */
    public void addThing(Object obj) {
        things.add(obj);
        System.out.println("An instance of " + obj.getClass().getSimpleName() + " has appeared in room " + name);
    }

    /**
     * removes the specified thing from this room
     *
     * @param obj
     */
    public void removeThing(Object obj) {
        if (obj != null && things.remove(obj))
            System.out.println("An instance of " + obj.getClass().getSimpleName() + " has disappeared from room " + name);
        else if (obj != null)
            System.out.println(obj.getClass().getSimpleName() + " tried to disappear from room " + name + " but might still be there");
        else System.out.println("Can't remove an object that does not exist from room " + name);
    }

    /**
     * RoomExplorers can pick up things and drop them in other rooms
     *
     * @param obj
     */
    void pickupThing(Object obj) {
        if (obj != null && things.remove(obj))
            System.out.println("You're now carrying the " + obj.getClass().getSimpleName());
        else if (obj != null)
            System.out.println("You tried to pick up the " + obj.getClass().getSimpleName() + " in room " + name + ", due to magic it might still be there");
        else System.out.println("Can't pick up an object that does not exist from room " + name);
    }

    /**
     * drops all carried things in this room
     *
     * @param things
     */
    void drop(List<?> things) {
        String s = "You dropped ";
        if (things == null || things.size() == 0) {
            s += " nothing in room " + getName();
        } else {
            for (Object o : things) {
                s += o.getClass().getSimpleName() + ", ";
            }
            s = s.substring(0, s.length() - 2) + " in room " + getName();
            this.things.addAll(things);
        }
        System.out.println(s);
    }

    /**
     * initially set up this room with the specified sequence of things
     *
     * @param objs
     */
    public void init(Object... objs) {
        Collections.addAll(things, objs);
    }

    /**
     * connects both rooms
     *
     * @param other
     */
    public void connectToRoom(Room other) {
        if (other == null || other == this) return;
        adjacentRooms.add(other);
        other.adjacentRooms.add(this);
    }


    /**
     * @return an array of all things in this room
     */
    public Object[] getThings() {
        return things.toArray(new Object[things.size()]);
    }


    public Room[] getAdjacentRooms() {
        return adjacentRooms.toArray(new Room[adjacentRooms.size()]);
    }

    public String getName() {
        return name;
    }

}
