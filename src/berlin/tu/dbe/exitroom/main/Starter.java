package berlin.tu.dbe.exitroom.main;

import berlin.tu.dbe.exitroom.env.AutoThingsLoader;
import berlin.tu.dbe.exitroom.env.LabyrinthRoomType;
import berlin.tu.dbe.exitroom.env.Room;
import berlin.tu.dbe.exitroom.env.RoomExplorer;
import berlin.tu.dbe.exitroom.env.things.base.Sink;
import berlin.tu.dbe.exitroom.env.things.effect.hiddenKey;

/**
 * main class. Run this class' main method for a quick round of ExitRoom.
 * For extensions:
 * - all classes in package berlin.tu.dbe.exitroom.env.things.base will be treated as things and one instance each
 * will be added to the rooms
 * - if you want your extending classes to only create other things as part of an action, e.g., turning a cup into a
 * broken cup, then put those classes (e.g., class BrokenCup) into package berlin.tu.dbe.exitroom.env.things.effect
 * - the "use" command will run public no-args instance methods in your class
 * - the "combine" command will run public instance methods in your class which have exactly one parameter and for an
 * instance of that parameter is present in the same room. E.g., you want to call a someCup.breakWith(someHammer) via
 * the "combine" command. This will only be available in the context menu as an option if there are instances of Cup
 * and Hammer in the same room.
 * - be careful with return values of such methods: if the action shall cause a noise, you can return it as a String
 * and it will be printed on the console. Any other type (!) returned will be automatically added as a thing to the
 * current room. Avoid returning non-exitroom classes as, e.g., Integer etc. will also be added as a thing to the room.
 * - There is only one instance of the player class RoomExplorer which can always be retrieved via the
 * RoomExplorer.getInstance(); class method. For extensions, the RoomExplorer object provides access to the current
 * Room object via RoomExplorer.getInstance().getRoom();
 * - The Room class provides a number of instance methods. Extensions can work without access to class Room but if needed
 * Room provides instance methods for accessing its name, adjacent rooms, and things in the room. Extensions may also
 * call addThing() and removeThing() to add or remove one or more things from the respective room instead of relying
 * on the above-mentioned return type of "things" methods.
 *
 * @author David
 */
public class Starter {

    public static void main(String[] args) throws Exception {
        Room startRoom = buildScenario(false);
        RoomExplorer explorer = RoomExplorer.getInstance();
        explorer.setRoom(startRoom);
        explorer.explore();
    }
    public final LabyrinthRoomType[][] layout = new LabyrinthRoomType[][]{
            {LabyrinthRoomType.ENTRY, LabyrinthRoomType.ROOM, LabyrinthRoomType.EMPTY},
            {LabyrinthRoomType.ROOM, LabyrinthRoomType.EMPTY, LabyrinthRoomType.FINISH},
            {LabyrinthRoomType.ROOM, LabyrinthRoomType.ROOM, LabyrinthRoomType.ROOM}
    };
    private void generateLabyrinth(Room entryRoom){
        Room[][] rooms = new Room[layout.length][layout[0].length];
        for(int i = 0; i < layout.length; i++){
            for(int j = 0; j < layout[0].length; j++){
                LabyrinthRoomType roomType = layout[i][j];
                if(roomType == LabyrinthRoomType.EMPTY){
                    rooms[i][j] = null;
                    continue;
                }
                String roomName = "Room x=" + i + ",y=" + j;
                Room newRoom = new Room(roomName);
                rooms[i][j] = newRoom;
                if(roomType == LabyrinthRoomType.ENTRY){
                    entryRoom.connectToRoom(newRoom);
                }
                if (roomType == LabyrinthRoomType.FINISH){
                    newRoom.init(new hiddenKey());
                }
            }
        }
        for (int i = 0; i < layout.length; i++) {
            for (int j = 0; j < layout[0].length; j++) {

                Room currentRoom = rooms[i][j];
                if (currentRoom == null) continue;

                if (i - 1 >= 0 && rooms[i - 1][j] != null) {
                    currentRoom.connectToRoom(rooms[i - 1][j]);
                }
                if (i + 1 < layout.length && rooms[i + 1][j] != null) {
                    currentRoom.connectToRoom(rooms[i + 1][j]);
                }
                if (j - 1 >= 0 && rooms[i][j - 1] != null) {
                    currentRoom.connectToRoom(rooms[i][j - 1]);
                }
                if (j + 1 < layout[0].length && rooms[i][j + 1] != null) {
                    currentRoom.connectToRoom(rooms[i][j + 1]);
                }
            }
        }
    }



    private static Room buildScenario(boolean putAllThingsInStartroom) {
        System.out.println("Setting up ExitRoom...");
        Room kitchen = new Room("Kitchen"),
                corridor = new Room("Corridor"),
                livingroom = new Room("Living Room"),
                bedroom = new Room("Bedroom"),
                bathroom = new Room("Bathroom"),
                basement = new Room("Basement"),
                dungeon = new Room("Dungeon");
        Room[] rooms = {kitchen, corridor, livingroom, bedroom, bathroom, basement,dungeon};
        corridor.connectToRoom(kitchen);
        corridor.connectToRoom(livingroom);
        corridor.connectToRoom(bedroom);
        corridor.connectToRoom(bathroom);
        bedroom.connectToRoom(bathroom);
        corridor.connectToRoom(basement);
        corridor.connectToRoom(dungeon);
        //randomly distribute things
        Object[] things = AutoThingsLoader.getInstancesForClassesInPackage("berlin.tu.dbe.exitroom.env.things.base");
        if (things != null) {
            for (Object t : things) {
                if (t instanceof Sink) continue;
                if (putAllThingsInStartroom)
                    corridor.init(t);
                else rooms[(int) (Math.random() * rooms.length)].init(t);
            }
        }
        bathroom.init(new Sink());
        kitchen.init(new Sink());
        Starter starter = new Starter();
        starter.generateLabyrinth(corridor);
        return corridor;
    }
}
