package berlin.tu.dbe.exitroom.env;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * This Utility Class contains some methods that enable you to have more interesting gameplay options, which you don't
 * yet know hot to implement.
 */
public class Helper {

    /**
     * Will sleep the specified amount of time
     * @param ms time in milliseconds.
     */
    public static void sleep(long ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            System.out.println("This will never happen");
        }
    }

    /**
     * Reads a file from the folder `res` in this project and returns its content as a String
     * @param filename the name of the file in the folder `res`. E.g., "genie.txt"
     * @return a String that contains the content of the file.
     */
    public static String readFileToString(String filename) {
        try {
            return Files.readString(Path.of("res", filename));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
