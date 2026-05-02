package berlin.tu.dbe.exitroom.env;

import berlin.tu.dbe.exitroom.env.things.CantBeCarried;
import berlin.tu.dbe.exitroom.env.things.HasPickupEffect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;


/**
 * singleton class which is used for walking through the ExitRoom scenario. Provides all necessary code for user
 * interaction and the resulting actions. Once a scenario has been configured (rooms, things, ...), a starting
 * class shall call the explore() method *once*.
 *
 * @author David
 */
public class RoomExplorer {

    private final static RoomExplorer instance = new RoomExplorer();
    private final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private final List<Object> carriedItems = new ArrayList<>();
    private Room room = null;

    private RoomExplorer() {

    }

    /**
     * @return a reference to the RoomExplorer instance
     */
    public static RoomExplorer getInstance() {
        return instance;
    }

    /**
     * checks whether input can be parsed to an integer and whether it's in the interval [min,max]
     *
     * @param input
     * @param min
     * @param max
     * @return the resulting integer or null if the condition above is violated
     */
    private static Integer toRangedInt(String input, int min, int max) {
        if (input == null || !input.trim().matches("\\d++")) return null;
        Integer tmp = Integer.parseInt(input.trim());
        if (tmp < min || tmp > max) return null;
        return tmp;
    }

    /**
     * calls getPublicInstanceMethods(clazz,1) and filters the output by the type of the parameter.
     * The result list contains only entries where the expected param is of type paramClazz
     *
     * @param clazz
     * @param paramClazz
     * @return
     */
    private static List<Method> getPublicInstanceMethodsForParamType(Class<?> clazz, Class<?> paramClazz) {
        Method[] methods = getPublicInstanceMethods(clazz, 1);
        List<Method> res = new ArrayList<>();
        for (Method m : methods) {
            Class<?>[] params = m.getParameterTypes();
            if (params != null && params.length == 1 && params[0].equals(paramClazz)) {
                res.add(m);
                // System.out.println("getPublicInstanceMethodsForParamType: adding " + m);
            }
        }
        return res;
    }

    /**
     * @param clazz
     * @param numPar number of params
     * @return an array only containing all public  instance methods from class clazz that expect exactly numPar params
     */
    private static Method[] getPublicInstanceMethods(Class<?> clazz, int numPar) {
        Method[] ms = clazz.getDeclaredMethods();
        List<Method> list = new ArrayList<>();
        for (Method m : ms) {
            int mod = m.getModifiers();
            if (Modifier.isPublic(mod) && !Modifier.isStatic(mod) && m.getParameterCount() == numPar && !m.getName().equals("onPickup")) {
                list.add(m);
                // System.out.println("getPublicInstanceMethods: adding " + m);
            }
        }
        return list.toArray(new Method[list.size()]);
    }

    /**
     * prints a list of all things in the current room
     */
    private void printThings() {
        if (room == null) {
            System.out.println("You're not in a room");
            return;
        }
        Object[] things = room.getThings();
        if (things.length == 0) {
            System.out.println("You're looking around but the room is empty.");
            return;
        }
        String out = "These are the things you can see: ";
        for (Object o : things) {
            out += o.getClass().getSimpleName() + ", ";
        }
        System.out.println(out.substring(0, out.length() - 2));
    }

    /**
     * prints the available methods of things in this room
     *
     * @throws IOException
     */
    private void analyzeItem() throws IOException {
        if (room == null) {
            System.out.println("You're not in a room");
            return;
        }
        Object[] things = room.getThings();
        if (things == null || things.length == 0) {
            System.out.println("You decided to analyze one of the things" + " in this room, but the room is empty. Try something else.");
            return;
        }
        System.out.println("You have decided to analyze one of the things in this room more closely." + "Give me the number of the thing you want to analyze:");
        String thingsStr = "";
        for (int i = 0; i < things.length; i++) {
            thingsStr += (i + 1) + " - " + things[i].getClass().getSimpleName() + ", ";
        }
        if (thingsStr.length() < 2) {
            System.out.println("Error: There were not enough things");
            return;
        }
        System.out.println(thingsStr.substring(0, thingsStr.length() - 2));
        String str = in.readLine();
        Integer index = toRangedInt(str, 1, things.length);
        if (index == null) {
            System.out.println("You typed " + str + "\nSeriously, you moron, are you too stupid to type a number?");
            return;
        }
        System.out.println("Item " + index + " looks like a " + things[index - 1].getClass().getSimpleName());
        System.out.println("Things that you can do with it:");
        StringBuffer sb = new StringBuffer();
        for (Method m : things[index - 1].getClass().getDeclaredMethods()) {
            sb.append("\t- " + m.getName());
        }
        System.out.println(sb);


    }

    /**
     * reflectively calls a method on a thing in this room
     *
     * @throws IOException
     */
    private void useItem() throws IOException {
        if (room == null) {
            System.out.println("You're not in a room");
            return;
        }
        Object[] things = room.getThings();
        if (things == null || things.length == 0) {
            System.out.println("You decided to use one of the things" + " in this room, but the room is empty. Try something else.");
            return;
        }

        System.out.println("You have decided to use one of the things in this room. " + "Give me the number of the thing you want to use:");
        String thingsStr = "";
        for (int i = 0; i < things.length; i++) {
            thingsStr += (i + 1) + " - " + things[i].getClass().getSimpleName() + ", ";
        }
        if (thingsStr.length() < 2) {
            System.out.println("Error: There were not enough things");
            return;
        }
        System.out.println(thingsStr.substring(0, thingsStr.length() - 2));
        String str = in.readLine();
        Integer index = toRangedInt(str, 1, things.length);
        if (index == null) {
            System.out.println("You typed " + str + "\nSeriously, you moron, are you too stupid to type a number?");
            return;
        }
        Object thing = things[index - 1];
        // System.out.println("Item " + index + " looks like a " + thing.getClass().getSimpleName());
        System.out.println("Things that you can do with it:");
        StringBuffer sb = new StringBuffer();
        Method[] methods = getPublicInstanceMethods(thing.getClass(), 0);
        if (methods.length == 0) {
            System.out.println("There is nothing that you can do with it.");
            return;
        }
        int i = 1;
        for (Method m : methods) {
            sb.append(i++ + " - " + m.getName() + ", ");
        }
        System.out.println(sb.substring(0, sb.length() - 2));
        System.out.println("Give me the number of the option you want to try");
        str = in.readLine();

        Integer opIndex = toRangedInt(str, 1, methods.length);
        if (opIndex == null) {
            System.out.println("You typed " + str + "\nSeriously, you moron, are you too stupid to type a number?");
            return;
        }
        Method method = methods[opIndex - 1];
        try {
            Object callRes = method.invoke(thing);
            if (callRes == null) {
                //do nothing
            } else if (callRes instanceof String) {
                System.out.println("There was a sudden noise: " + callRes);
            } else {
                room.addThing(callRes);
            }
        } catch (Exception e) {
            System.out.println("Oooh, trying action " + method.getName() + " on item " + thing.getClass().getSimpleName() + " failed.");
        }

    }

    /**
     * reflectively calls a method on a thing in this room with another thing as param
     *
     * @throws IOException
     */
    private void combineItems() throws IOException {
        if (room == null) {
            System.out.println("You're not in a room");
            return;
        }
        Object[] things = room.getThings();
        if (things == null || things.length <= 1) {
            System.out.println("You decided to combine two things" + " in this room, but the room is empty or contains only one thing. Try something else.");
            return;
        }

        System.out.println("You have decided to combine two of the things in this room. " + "Here are the things available in this room:");
        String thingsStr = "";
        for (int i = 0; i < things.length; i++) {
            thingsStr += (i + 1) + " - " + things[i].getClass().getSimpleName() + ", ";
        }
        if (thingsStr.length() < 2) {
            System.out.println("Error: There were not enough things");
            return;
        }
        System.out.println(thingsStr.substring(0, thingsStr.length() - 2));
        System.out.println("Give me the number of the first thing you want to use:");
        String str = in.readLine();
        Integer index = toRangedInt(str, 1, things.length);
        if (index == null) {
            System.out.println("You typed " + str + "\nAre you kidding me?");
            return;
        }
        System.out.println("Give me the number of the second thing you want to use:");
        str = in.readLine();
        Integer index2 = toRangedInt(str, 1, things.length);
        if (index2 == null) {
            System.out.println("You typed " + str + "\nI told you to give me one of the numbers I listed...stupid.");
            return;
        }
        Object thing1 = things[index - 1], thing2 = things[index2 - 1];
        List<Method> methodsOnThing1 = getPublicInstanceMethodsForParamType(thing1.getClass(), thing2.getClass());
        List<Method> methodsOnThing2 = getPublicInstanceMethodsForParamType(thing2.getClass(), thing1.getClass());
        String thing1Name = thing1.getClass().getSimpleName(), thing2Name = thing2.getClass().getSimpleName();
        System.out.println("You picked " + thing1Name + " and " + thing2Name);
        if (methodsOnThing1.size() == 0 && methodsOnThing2.size() == 0) {
            System.out.println("Unfortunately, you can't combine these two things...");
            return;
        }
        System.out.println("Things that you can do with them:");
        StringBuffer sb = new StringBuffer();
        int i = 1;
        for (Method m : methodsOnThing1) {
            sb.append("\n\t" + i++ + ": try " + m.getName() + " on the " + thing1Name + " with the help of the " + thing2Name);
        }
        for (Method m : methodsOnThing2) {
            sb.append("\n\t" + i++ + ": try " + m.getName() + " on the " + thing2Name + " with the help of the " + thing1Name);
        }
        System.out.println(sb);
        System.out.println("Give me the number of the option you want to try");
        str = in.readLine();
        Integer opIndex = toRangedInt(str, 1, methodsOnThing1.size() + methodsOnThing2.size());
        if (opIndex == null) {
            System.out.println("You typed " + str + "\nSeriously, you moron, are you too stupid to type a number?");
            return;
        }
        Object callee, param;
        Method method;
        if (opIndex <= methodsOnThing1.size()) {
            callee = thing1;
            param = thing2;
            method = methodsOnThing1.get(opIndex - 1);
        } else {
            callee = thing2;
            param = thing1;
            method = methodsOnThing2.get(opIndex - 1 - methodsOnThing1.size());
        }

        try {
            Object callRes = method.invoke(callee, param);
            if (callRes == null) {
                //do nothing
            } else if (callRes instanceof String) {
                System.out.println("There was a sudden noise: " + callRes);
            } else room.addThing(callRes);
        } catch (Exception e) {
            System.out.println("Oooh, trying action " + method.getName() + " on item " + callee.getClass().getSimpleName() + " failed.");
        }

    }

    /**
     * lets users interactively pick up items in this room
     */
    private void pickupItem() throws IOException {
        if (room == null) {
            System.out.println("You can only pick up items when you're in a room.");
            return;
        }
        Object[] things = room.getThings();
        if (things == null || things.length == 0) {
            System.out.println("There is nothing in this room that you could pick up.");
            return;
        }
        System.out.println("Here is the list of items in this room:");
        String thingsStr = "";
        for (int i = 0; i < things.length; i++) {
            thingsStr += (i + 1) + " - " + things[i].getClass().getSimpleName() + ", ";
        }
        System.out.println(thingsStr.substring(0, thingsStr.length() - 2));
        System.out.println("Type the numbers of things you would like to pick up separated with whitespaces."
                + " Any value not from this list will be ignored.");
        String str = in.readLine();
        String[] splits = str.split("\\h");
        for (String spl : splits) {
            Integer index = toRangedInt(spl, 1, things.length);
            if (index == null) continue;
            index = index - 1;
            if (things[index] != null) {
                if (things[index] instanceof CantBeCarried) {
                    System.out.println("You can't carry " + things[index].getClass().getSimpleName() + "s");
                } else {
                    if (things[index] instanceof HasPickupEffect) {
                        ((HasPickupEffect) things[index]).onPickup();
                    }
                    room.pickupThing(things[index]);
                    carriedItems.add(things[index]);
                }
            }
        }


    }

    /**
     * drops all currently carried items in this room
     */
    private void dropAllItems() {
        if (room == null) {
            System.out.println("You can only drop items when you're in a room.");
            return;
        }
        room.drop(carriedItems);
        carriedItems.clear();
    }

    /**
     * prints the list of connected rooms for the current room
     */
    private void printAdjacentRooms() {
        if (room == null) {
            System.out.println("We're apparently not in a room");
            return;
        }
        Room[] rooms = room.getAdjacentRooms();
        if (rooms.length == 0) System.out.println("There are no adjacent rooms.");
        else if (rooms.length == 1) System.out.println("You could go to room " + rooms[0].getName());
        else {
            StringBuffer sb = new StringBuffer();
            for (Room r : rooms) sb.append(r.getName() + ", ");
            System.out.println("The following rooms are connected to room " + room.getName() + ": " + sb.substring(0, sb.length() - 2));

        }

    }

    /**
     * goes to one of the adjacent rooms if there are any
     */
    private void gotoRoom() throws IOException {
        if (room == null) {
            System.out.println("We're apparently not in a room");
            return;
        }
        Room[] rooms = room.getAdjacentRooms();
        if (rooms.length == 0) {
            System.out.println("There are no adjacent rooms.");
            return;
        }
        if (rooms.length == 1) {
            System.out.println("Entering room " + rooms[0].getName());
            this.setRoom(rooms[0]);
            return;
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < rooms.length; i++) sb.append((i + 1) + " - " + rooms[i].getName() + ", ");
        System.out.println("The following rooms are connected to room " + room.getName() + ": " + sb.substring(0, sb.length() - 2));
        System.out.println("Enter one of the room numbers:");
        String str = in.readLine();
        Integer index;
        if ((index = toRangedInt(str, 1, rooms.length)) != null) {
            System.out.println("Entering room " + rooms[index - 1].getName());
            this.setRoom(rooms[index - 1]);
        }

    }

    /**
     * lets users interactively explore the current room. Will return directly if no room is set.
     *
     *
     */
    public void explore() {
        try {
            if (this.room == null) {
                System.out.println("No room set, exiting.");
                return;
            }
            String commands = Commands.getCommandsInfo();
            String prompt = null;
            Commands command;
            System.out.println("You're entering room " + room.getName() + ".\nType \"info\" to get a list of things you can do");
            while ((prompt = in.readLine()) != null) {
                //System.out.println("command was " + prompt);
                command = Commands.getCommandForString(prompt);
                if (command == null) {
                    System.out.println("Try again...");
                    continue;
                }
                switch (command) {
                    case INFO:
                        System.out.println(Commands.getCommandsInfo());
                        break;
                    case EXIT:
                        System.out.println("Glad that you finally gave up, bye.");
                        return;
                    case LOOK_AROUND:
                        printThings();
                        break;
                    case ANALYZE_ITEM:
                        analyzeItem();
                        break;
                    case USE_ITEM:
                        useItem();
                        break;
                    case COMBINE_ITEMS:
                        combineItems();
                        break;
                    case PICK_UP_ITEM:
                        pickupItem();
                        break;
                    case DROP_ITEMS:
                        dropAllItems();
                        break;
                    case LIST_ROOMS:
                        printAdjacentRooms();
                        break;
                    case GOTO_ROOM:
                        gotoRoom();
                        break;
                    default:
                        System.out.println("Apparently, someone forgot to retrofit the command " + command);
                }
                System.out.println("What do you want to do next?");
            }
        } catch (IOException ioe) {
            System.out.println("There was an error while reading from keyboard, exiting after printing stacktrace");
            ioe.printStackTrace();
            System.exit(-1);
        }
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }


    private enum Commands {
        INFO("print a list of commands", "info"),
        LOOK_AROUND("look around the room", "explore"),
        ANALYZE_ITEM("study an item in the room", "study"),
        USE_ITEM("use an item in the room", "use"),
        COMBINE_ITEMS("use one item in the room with another one", "combine"),
        PICK_UP_ITEM("pick up an item in this room", "take"),
        DROP_ITEMS("drop all items you're carrying", "drop"),
        LIST_ROOMS("print a list of adjacent rooms", "rooms"),
        GOTO_ROOM("go to another room", "go"),
        EXIT("give up and exit the room", "exit");

        private final String description;
        private final String commandText;


        Commands(String description, String commandText) {
            this.description = description;
            this.commandText = commandText;
        }

        public static String getCommandsInfo() {
            String out = "These are the things you can do:";
            for (Commands c : Commands.values()) out += "\n\t- " + c.description + ": type " + c.commandText;
            return out;
        }

        /**
         * tries to match the input str to one of the commandText values
         *
         * @param str
         * @return the matching Commands value or null if no match is found
         */
        public static Commands getCommandForString(String str) {
            if (str == null) {
                System.out.println("Command string may not be null");
                return null;
            }
            for (Commands c : Commands.values()) {
                if (c.commandText.equals(str.trim().toLowerCase())) return c;
            }
            System.out.println("Input \"" + str + "\" is not a valid command, try typing " + INFO.commandText + " for a list of supported commands.");
            return null;
        }
    }


}

