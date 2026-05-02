package berlin.tu.dbe.exitroom.env;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * utility class for automatically loading and instantiating all classes from the specified package
 *
 * @author David
 */
public class AutoThingsLoader {

    private static final boolean debug = false;

    /**
     * @param pckge a packaae
     * @return a list of all classes in the specified package
     */
    private static List<Class<?>> loadThingsClassesFromPackage(String pckge) {
        String pckgeAsPath = pckge.replace(".", "/");
        List<Class<?>> result = new ArrayList<Class<?>>();
        if (debug) {
            System.out.println("trying directory " + pckgeAsPath);
        }
        File dir = new File("src/" + pckgeAsPath);
        if (debug) {
            System.out.println(dir);
        }
        for (File f : dir.listFiles()) {
            if (debug) System.out.println("processing file " + f);
            if (f.exists() && f.isFile() && f.getName().endsWith(".java")) {
                if (debug) System.out.println("found file: " + f);
                String classname = pckge + "." + f.getName();
                classname = classname.substring(0, classname.length() - 5);
                if (debug) System.out.println("built classname: " + classname);
                Class<?> clazz = null;
                try {
                    clazz = Class.forName(classname);
                    if (debug) System.out.println("resolved " + clazz);
                } catch (Exception e) {
                    System.out.println("Could not resolve class " + clazz + " in file " + f);
                    e.printStackTrace();
                }
                if (clazz != null && !clazz.isInterface()) result.add(clazz);
            }
        }
        return result;
    }

    /**
     * @param pckge a package
     * @return an array containing one instance each for every class found in
     * the specified package. Only classes with a public no-args constructor will be instantiated.
     */
    public static Object[] getInstancesForClassesInPackage(String pckge) {
        List<Class<?>> clazzes = loadThingsClassesFromPackage(pckge);
        List<Object> out = new ArrayList<>();
        for (Class<?> clazz : clazzes) {
            if (debug) System.out.println("Instantiating " + clazz);
            try {
                Object o = clazz.getDeclaredConstructor().newInstance();
                if (o != null) out.add(o);
            } catch (Exception e) {
                System.out.println("Could not instantiate class " + clazz);
                e.printStackTrace();
            }
        }
        return out.toArray(new Object[0]);

    }


}

